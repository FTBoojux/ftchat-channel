package com.boojux.ftchatchannel.message.consumer;

import com.boojux.ftchatchannel.bean.DTO.messageSend.OfflineMessageDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OfflineMessageConsumer {
    @Resource
    private RedisTemplate<String, OfflineMessageDTO> redisTemplate;

    @RabbitListener(queues = "offline_message")
    public void offlineMessageConsumer(OfflineMessageDTO message){
        String redisKey = "offline:message:" + message.getConversation_id();
        redisTemplate.opsForList().leftPush(redisKey, message);
        log.info("offlineMessageConsumer:{}", message);
    }
}
