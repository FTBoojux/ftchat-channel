package com.boojux.ftchatchannel.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Objects;

public class RedisUtils {
    @Resource(name = "redisTemplate")
    private static ValueOperations<String, String> valueOperations;

    public static boolean checkToken(String userId, String token) {
        boolean result = false;
        String key = "user:" + userId + ":tokens";
        if (Boolean.TRUE.equals(valueOperations.getOperations().hasKey(key))) {
            result = Objects.requireNonNull(valueOperations.getOperations().boundListOps(key).range(0, -1)).contains(token);
        }
        return result;
    }
}
