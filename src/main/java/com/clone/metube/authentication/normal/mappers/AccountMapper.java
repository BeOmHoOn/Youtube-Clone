package com.clone.metube.authentication.normal.mappers;

import com.clone.metube.authentication.normal.dtos.AccountLoginRequest;
import com.clone.metube.authentication.normal.dtos.AccountRegisterRequest;
import com.clone.metube.authentication.normal.entities.Account;
import com.clone.metube.global.enums.UserRole;

public class AccountMapper {
    public static Account toEntity(AccountLoginRequest dto) {
        if (dto == null) return null;

        return Account.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(UserRole.USER)
                .build();
    }

    public static Account toEntity(AccountRegisterRequest dto) {
        if (dto == null) return null;

        return Account.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(UserRole.USER)
                .build();
    }
}
