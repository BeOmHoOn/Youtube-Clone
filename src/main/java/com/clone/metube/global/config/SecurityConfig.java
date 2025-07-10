package com.clone.metube.global.config;

import com.clone.metube.authentication.oauth2.handlers.OAuth2SuccessHandler;
import com.clone.metube.authentication.oauth2.services.OAuth2Service;
import com.clone.metube.global.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2Service oAuth2Service;
    private final JwtTokenProvider jwtTokenProvider; // JwtTokenProvider 주입

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .requestCache(requestCache -> requestCache.requestCache(new org.springframework.security.web.savedrequest.NullRequestCache()))
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2Service))
                        .successHandler(new OAuth2SuccessHandler(jwtTokenProvider)) // 핸들러 직접 생성
                        .authorizationEndpoint(authEndpoint -> authEndpoint
                                .baseUri("/oauth2/authorization") // -> /oauth2/authorization/naver
                        )
                        .redirectionEndpoint(redirEndpoint -> redirEndpoint
                                .baseUri("/login/oauth2/code/*") // OAuth2 로그인에서 리디렉션할 콜백 URI, API제공 사이트에 등록한 애플리케이션 설정과 맞춰야함
                        )
                );

        return http.build();
    }
}

