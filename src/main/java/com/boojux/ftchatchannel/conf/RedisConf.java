package com.boojux.ftchatchannel.conf;

import com.boojux.ftchatchannel.bean.DTO.messageSend.OfflineMessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConf {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer keySerializer = new StringRedisSerializer();
        template.setKeySerializer(keySerializer);
        template.setHashKeySerializer(keySerializer);

        Jackson2JsonRedisSerializer<OfflineMessageDTO> valueSerializer = new Jackson2JsonRedisSerializer<>(OfflineMessageDTO.class);
        template.setValueSerializer(valueSerializer);
        template.setHashValueSerializer(valueSerializer);

        return template;
    }
}
