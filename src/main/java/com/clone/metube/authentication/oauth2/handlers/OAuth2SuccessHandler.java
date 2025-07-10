package com.clone.metube.authentication.oauth2.handlers;

import com.clone.metube.authentication.oauth2.dtos.CustomOAuth2User;
import com.clone.metube.global.auth.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        System.err.println("성공핸들러 진입");
        CustomOAuth2User customUser = (CustomOAuth2User) authentication.getPrincipal();
        String email = customUser.getEmail();

        String token = jwtTokenProvider.generateAccessToken(email);

        response.sendRedirect("http://localhost:3000/oauth2/redirect?token=" + token);
    }
}
