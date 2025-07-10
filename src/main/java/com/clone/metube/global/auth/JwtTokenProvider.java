package com.clone.metube.global.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    private Key signingKey;

    @PostConstruct
    public void init() {
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private final Long ACCESS_TOKEN_EXPIRY_MILLI = 5L * 60L * 1000L; // 5분
    private final Long REFRESH_TOKEN_EXPIRY_MILLI = 24L * 60L * 60L * 1000L; // 24시간

    public String generateRefreshToken(String email) {
        Date issueAt = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRY_MILLI);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(issueAt)
                .setExpiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }

    public String generateAccessToken(String email) {
        Date issueAt = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRY_MILLI);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(issueAt)
                .setExpiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }

    public String getUserId(Claims token) {
        return token.getSubject();
    }

    public Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
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
