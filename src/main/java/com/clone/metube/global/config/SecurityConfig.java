package com.clone.metube.global.config;

import com.clone.metube.authentication.oauth2.services.SocialAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final SocialAccountService socialAccountService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .userInfoEndpoint(userInfo -> userInfo.userService(socialAccountService)
                        )
                        .authorizationEndpoint(authEndpoint -> authEndpoint
                                .baseUri("/oauth2/authorization") // ex) /oauth2/authorization/naver
                        )
                        .redirectionEndpoint(redirEndpoint -> redirEndpoint
                                .baseUri("/login/oauth2/code/*") // 네이버에서 리디렉션할 콜백 URI
                        )
                );

        return http.build();
    }
}
