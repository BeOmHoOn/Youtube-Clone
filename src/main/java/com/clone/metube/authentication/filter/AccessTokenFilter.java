package com.clone.metube.authentication.filter;

import com.clone.metube.authentication.normal.repositories.AccountRepository;
import com.clone.metube.authentication.oauth2.repositories.OAuth2AccountRepository;
import com.clone.metube.authentication.redis.Redis;
import com.clone.metube.global.auth.JwtTokenProvider;
import com.clone.metube.global.enums.UserRole;
import com.clone.metube.global.exceptions.AccessTokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AccessTokenFilter extends OncePerRequestFilter {
    private final AccountRepository accountRepository;
    private final OAuth2AccountRepository oAuth2AccountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final Redis redis;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveAccessToken(request.getHeader("Authorization"));

        if (token != null && !redis.isAccessTokenBlocked(token)) {

            Claims tokenData = jwtTokenProvider.parseToken(token);
            String email = jwtTokenProvider.getEmail(tokenData);
            boolean isOAuth2 = jwtTokenProvider.isOAuth2Login(tokenData);

            UsernamePasswordAuthenticationToken authenticationToken = null;

            if (isOAuth2) {
                var oAuth2Account = oAuth2AccountRepository.findByEmail(email).orElse(null);

                if (oAuth2Account != null) {
                    String role = oAuth2Account.getRole().name();
                    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

                    authenticationToken = new UsernamePasswordAuthenticationToken(oAuth2Account, null, authorities);
                }
            } else {
                var account = accountRepository.findByEmail(email);

                authenticationToken = new UsernamePasswordAuthenticationToken(account, null, null);
            }

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
