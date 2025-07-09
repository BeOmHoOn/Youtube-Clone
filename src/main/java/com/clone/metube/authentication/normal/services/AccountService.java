package com.clone.metube.authentication.normal.services;

import com.clone.metube.authentication.normal.mappers.AccountMapper;
import com.clone.metube.authentication.normal.dtos.AccountLoginRequest;
import com.clone.metube.authentication.normal.dtos.AccountLoginResponse;
import com.clone.metube.authentication.normal.dtos.AccountRegisterRequest;
import com.clone.metube.authentication.normal.dtos.AccountRegisterResponse;
import com.clone.metube.authentication.normal.entities.Account;
import com.clone.metube.authentication.normal.exceptions.AccountNotFoundException;
import com.clone.metube.authentication.normal.repositories.AccountRepository;
import com.clone.metube.global.enums.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

    public AccountRegisterResponse register(AccountRegisterRequest registerRequest) {
        accountRepository.save(AccountMapper.toEntity(registerRequest));

        return new AccountRegisterResponse();
    }

    public AccountLoginResponse login(AccountLoginRequest loginRequest) throws AccountNotFoundException {
        var email = loginRequest.getEmail();
        var password = pwdEncoder.encode(loginRequest.getPassword());

        Account account = accountRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new AccountNotFoundException(ExceptionMessage.ACCOUNT_NOT_FOUND.getMessage()));

        // 이후 jwt 토큰 도입

        return new AccountLoginResponse();
    }
}
