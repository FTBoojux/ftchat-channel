package com.boojux.ftchatchannel.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class RedisUtils {
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private JwtUtils jwtUtils;
    public boolean checkToken(String token) {
        boolean result = false;
        String userId = jwtUtils.getValueFromJwt(token, "user_id");
        String key = userId + "-tokens";
//        if (!Objects.isNull(userId) && Boolean.TRUE.equals(valueOperations.getOperations().hasKey(key))) {
//            result = Objects.requireNonNull(valueOperations.getOperations().boundListOps(key).range(0, -1)).contains(token);
//        }
        if (!Objects.isNull(userId)) {
            List<String> tokens = stringRedisTemplate.opsForList().range(key, 0, -1);
            if (Objects.requireNonNull(tokens).contains(token)) {
                result = true;
            }
        }
        return result;
    }

    public Set<String> scanKeys(String pattern) {
        Set<String> keys = new HashSet<>();
        ScanOptions scanOptions = ScanOptions.scanOptions()
                .match(pattern)
                .count(1000)
                .build();
        try(Cursor<String> cursor= redisTemplate.scan(scanOptions)) {
            while (cursor.hasNext()) {
                keys.add(cursor.next());
            }
        }
        return keys;
    }
}
