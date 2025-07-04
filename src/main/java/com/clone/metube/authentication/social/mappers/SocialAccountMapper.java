package com.clone.metube.authentication.social.mappers;

import com.clone.metube.authentication.social.dtos.LoginRequest;
import com.clone.metube.authentication.social.dtos.RegisterRequest;
import com.clone.metube.authentication.social.entities.SocialAccount;
import com.clone.metube.global.enums.UserRole;

public class SocialAccountMapper {
    public static SocialAccount toEntity(LoginRequest dto) {
        if (dto == null) return null;

        return SocialAccount.builder()
                .email(dto.getEmail())
                .provider(dto.getProvider())
                .providerId(dto.getProviderId())
                .role(UserRole.USER)
                .build();
    }

    public static SocialAccount toEntity(RegisterRequest dto) {
        if (dto == null) return null;

        return SocialAccount.builder()
                .email(dto.getEmail())
                .provider(dto.getProvider())
                .providerId(dto.getProviderId())
                .role(UserRole.USER)
                .build();
    }
}
