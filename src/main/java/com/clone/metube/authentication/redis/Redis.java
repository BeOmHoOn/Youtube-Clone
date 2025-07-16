package com.clone.metube.authentication.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import com.clone.metube.global.constants.CONSTANTS;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class Redis {
    private final StringRedisTemplate redisTemplate;

    public void saveRefreshToken(String userId, String refreshToken, long expirySecond) {
        redisTemplate.opsForValue().set(
                CONSTANTS.REFRESH_TOKEN_PREFIX + userId,
                refreshToken,
                Duration.ofSeconds(expirySecond)
        );
    }

    public String getRefreshToken(String userId) {
        return redisTemplate.opsForValue().get(
                CONSTANTS.REFRESH_TOKEN_PREFIX + userId
        );
    }

    public void deleteRefreshToken(String userId) {
        redisTemplate.delete(
                CONSTANTS.REFRESH_TOKEN_PREFIX + userId
        );
    }

    public void blockAccessToken(String accessToken, long expirySecond) {
        redisTemplate.opsForValue().set(
                CONSTANTS.BLOCK_ACCESS_TOKEN_PREFIX + accessToken,
                "Sign Out",
                Duration.ofSeconds(expirySecond)
        );
    }

    public boolean hasRefreshToken(String userId) {
        return redisTemplate.hasKey(
                CONSTANTS.REFRESH_TOKEN_PREFIX + userId
        );
    }

    public boolean isAccessTokenBlocked(String accessToken) {
        return redisTemplate.hasKey(
                CONSTANTS.BLOCK_ACCESS_TOKEN_PREFIX + accessToken
        );
    }
}
