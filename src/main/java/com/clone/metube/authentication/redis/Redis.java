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

    public void saveRefreshToken(String target, String refreshToken, long expirySecond) {
        redisTemplate.opsForValue().set(
                CONSTANTS.REFRESH_TOKEN_PREFIX + target,
                refreshToken,
                Duration.ofSeconds(expirySecond)
        );
    }

    public String getRefreshToken(String target) {
        return redisTemplate.opsForValue().get(
                CONSTANTS.REFRESH_TOKEN_PREFIX + target
        );
    }

    public void deleteRefreshToken(String target) {
        redisTemplate.delete(
                CONSTANTS.REFRESH_TOKEN_PREFIX + target
        );
    }

    public void blockAccessToken(String accessToken, long expirySecond) {
        redisTemplate.opsForValue().set(
                CONSTANTS.BLACKLIST_ACCESS_TOKEN_PREFIX + accessToken,
                "Logout",
                Duration.ofSeconds(expirySecond)
        );
    }

    public boolean hasRefreshToken(String target) {
        return redisTemplate.hasKey(
                CONSTANTS.REFRESH_TOKEN_PREFIX + target
        );
    }

    public boolean isAccessTokenBlocked(String accessToken) {
        return redisTemplate.hasKey(
                CONSTANTS.BLACKLIST_ACCESS_TOKEN_PREFIX + accessToken
        );
    }
}
