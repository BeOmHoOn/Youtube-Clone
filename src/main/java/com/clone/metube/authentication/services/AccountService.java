package com.clone.metube.authentication.services;

import com.clone.metube.authentication.dto.*;
import com.clone.metube.authentication.enums.RequestResult;
import com.clone.metube.authentication.enums.RequestType;
import com.clone.metube.authentication.mapper.AccountMapper;
import com.clone.metube.authentication.entitiy.Account;
import com.clone.metube.authentication.redis.Redis;
import com.clone.metube.authentication.repositories.AccountDetailsRepository;
import com.clone.metube.global.auth.JwtTokenProvider;
import com.clone.metube.global.constants.CONSTANTS;
import com.clone.metube.global.exceptions.AccountNotFoundException;
import com.clone.metube.authentication.repositories.AccountRepository;
import com.clone.metube.global.exceptions.EmailAlreadyUseException;
import com.clone.metube.global.exceptions.RefreshTokenExpiredException;
import com.nimbusds.openid.connect.sdk.LogoutRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountDetailsRepository accountDetailsRepository;
    private final Redis redis;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        var newAccount = AccountMapper.toEntity(registerRequest);

        // 중복확인
        if (accountRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyUseException();
        }

        // 없으면 생성
        accountRepository.save(newAccount);

        var response = AccountResponse.builder()
                .requestType(RequestType.REGISTER)
                .requestResult(RequestResult.SUCCESS)
                .message("회원가입이 완료되었습니다.");

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        var email = loginRequest.getEmail();
        var password = pwdEncoder.encode(loginRequest.getPassword());

        Account account = accountRepository.findByEmailAndPassword(email, password)
                .orElseThrow(AccountNotFoundException::new);

        // 이후 jwt 토큰 도입
        var accessToken = jwtTokenProvider.generateAccessToken(email, false);
        var refreshToken = jwtTokenProvider.generateRefreshToken(email, false);

        var tokens = new LoginResponse(accessToken, refreshToken);

        var response = AccountResponse.builder()
                .requestType(RequestType.LOGIN)
                .requestResult(RequestResult.SUCCESS)
                .message("로그인되었습니다.")
                .response(tokens);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> logout(String accessToken, HttpServletResponse httpResponse) {
        var token = jwtTokenProvider.resolveAccessToken(accessToken);
        var tokenData = jwtTokenProvider.parseToken(token);
        var email = jwtTokenProvider.getEmail(tokenData);

        var user = accountRepository.findByEmail(email)
                .orElseThrow(AccountNotFoundException::new);

        if (redis.hasRefreshToken(email)) {
            redis.deleteRefreshToken(email);
            httpResponse.addCookie(createRefreshTokenCookie(null));
            //리프레시토큰 쿠키 제거
        }

        redis.blockAccessToken(accessToken, CONSTANTS.ACCESS_TOKEN_EXPIRY_MILLI);

        var response = AccountResponse.builder()
                .requestType(RequestType.LOGIN)
                .requestResult(RequestResult.SUCCESS)
                .message("로그아웃되었습니다.");

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> refreshingAccessToken(String refreshToken) {
        if (refreshToken == null) {
            throw new RefreshTokenExpiredException();
        }

        var tokenData = jwtTokenProvider.parseToken(refreshToken);
        var email = jwtTokenProvider.getEmail(tokenData);

        var account = accountRepository.findByEmail(email)
                .orElseThrow(AccountNotFoundException::new);

        var userRefreshToken = redis.getRefreshToken(email);

        if (!userRefreshToken.equals(refreshToken)) {
            throw new RefreshTokenExpiredException();
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(email, false);

        var response = AccountResponse.builder()
                .requestType(RequestType.REFRESH_TOKEN)
                .requestResult(RequestResult.SUCCESS)
                .message("엑세스토큰이 갱신되었습니다.")
                .response(newAccessToken);

        return ResponseEntity.ok(response);
    }

    private Cookie createRefreshTokenCookie(String refreshToken) {
        Cookie cookie = new Cookie("Refresh-Token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(CONSTANTS.REFRESH_TOKEN_EXPIRY_MILLI.intValue());

        return cookie;
    }
}
