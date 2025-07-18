package com.clone.metube.authentication.oauth2.services;

import com.clone.metube.authentication.oauth2.dtos.CustomOAuth2User;
import com.clone.metube.authentication.oauth2.entities.OAuth2Account;
import com.clone.metube.authentication.oauth2.interfaces.OAuth2UserInfo;
import com.clone.metube.authentication.oauth2.repositories.OAuth2AccountRepository;
import com.clone.metube.authentication.oauth2.userinfos.NaverUserInfo;
import com.clone.metube.global.enums.AccountRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final OAuth2AccountRepository oAuth2AccountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        var oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuth2UserInfo oAuth2UserInfo;

        String provider = userRequest.getClientRegistration().getRegistrationId();

        switch (provider) {
            case "naver":
                oAuth2UserInfo = new NaverUserInfo(attributes);
                break;

            default:
                throw new OAuth2AuthenticationException("소셜 로그인을 지원하지 않는 사이트입니다.");
        }

        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();
        String providerId = oAuth2UserInfo.getProviderId();
        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(AccountRole.USER.name()));

        var oAuth2Account = oAuth2AccountRepository.findByEmail(email)
                .orElseGet(() -> oAuth2AccountRepository.save(
                                OAuth2Account.builder()
                                        .email(email)
                                        .provider(provider)
                                        .providerId(providerId)
                                        .role(AccountRole.USER)
                                        .build()
                        )
                );

        var customUser = new CustomOAuth2User(email, name, attributes, authorities);

        return customUser;
    }
}
