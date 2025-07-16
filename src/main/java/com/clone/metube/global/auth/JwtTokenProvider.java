package com.clone.metube.global.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import com.clone.metube.global.constants.CONSTANTS;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    private Key signingKey;

    @PostConstruct
    public void init() {
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateRefreshToken(String email, boolean isOAuth2Login) {
        Date issueAt = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(System.currentTimeMillis() + CONSTANTS.REFRESH_TOKEN_EXPIRY_MILLI);

        return Jwts.builder()
                .setSubject(email)
                .setClaims(Map.of("isOAuth2Login", isOAuth2Login))
                .setIssuedAt(issueAt)
                .setExpiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }

    public String generateAccessToken(String email, boolean isOAuth2Login) {
        Date issueAt = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(System.currentTimeMillis() + CONSTANTS.ACCESS_TOKEN_EXPIRY_MILLI);

        return Jwts.builder()
                .setSubject(email)
                .setClaims(Map.of("isOAuth2Login", isOAuth2Login))
                .setIssuedAt(issueAt)
                .setExpiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }

    public String getEmail(Claims token) {
        return token.getSubject();
    }

    public boolean isOAuth2Login(Claims token) {
        return token.get("isOAuth2Login").equals(Boolean.TRUE);
    }

    public Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(Claims token) {
        return token.getExpiration().before(new Date());
    }

    public String resolveAccessToken(String header) {
        String token;

        try {
            token = (header != null && header.startsWith("Bearer "))
                    ? header.substring(7) : null;

        } catch (Exception e) {
            token = null;
        }

        return token;
    }
}
