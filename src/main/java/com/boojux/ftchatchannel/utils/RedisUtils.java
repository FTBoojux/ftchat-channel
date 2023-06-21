package com.boojux.ftchatchannel.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
@Component
public class RedisUtils {
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;
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
            if (Objects.requireNonNull(redisTemplate.opsForList().range(key, 0, -1)).contains(token)) {
                result = true;
            }
        }
        return result;
    }
}
