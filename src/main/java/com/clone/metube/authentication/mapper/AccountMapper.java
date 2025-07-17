package com.clone.metube.authentication.mapper;

import com.clone.metube.authentication.dto.LoginRequest;
import com.clone.metube.authentication.dto.RegisterRequest;
import com.clone.metube.authentication.entitiy.Account;
import com.clone.metube.global.enums.AccountRole;

public class AccountMapper {
    public static Account toEntity(LoginRequest dto) {
        if (dto == null) return null;

        return Account.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(AccountRole.USER)
                .isOAuth2Account(false)
                .build();
    }

    public static Account toEntity(RegisterRequest dto) {
        if (dto == null) return null;

        return Account.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(AccountRole.USER)
                .isOAuth2Account(false)
                .build();
    }
}
