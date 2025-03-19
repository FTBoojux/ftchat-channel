package com.boojux.ftchatchannel.syncTask;

import cn.hutool.core.collection.CollectionUtil;
import com.boojux.ftchatchannel.bean.DTO.messageSend.OfflineMessageDTO;
import com.boojux.ftchatchannel.bean.domain.cassandra.MessageStatus.MessageReadStatus;
import com.boojux.ftchatchannel.bean.domain.cassandra.MessageStatus.MessageStatusKey;
import com.boojux.ftchatchannel.repository.cassandra.MessageReadStatusRepository;
import com.boojux.ftchatchannel.utils.RedisUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class RedisToCassandraSyncTask {
    Logger logger = Logger.getLogger(RedisToCassandraSyncTask.class.getName());
    @Resource
    private RedisTemplate<String, OfflineMessageDTO> redisTemplate;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private MessageReadStatusRepository messageReadStatusRepository;
    @Scheduled(fixedDelay = 60000)
    public void syncOfflineMessageToCassandra() {
        logger.info("syncOfflineMessageToCassandra start");
        Set<String> keys = redisUtils.scanKeys("offline:message:*");
        for (String key : keys) {
            List<OfflineMessageDTO> values = redisTemplate.opsForList().range(key, 0, -1);
            if (CollectionUtil.isNotEmpty(values)){
                List<MessageReadStatus> messageReadStatusList = values.stream().map(value -> {
                    MessageReadStatus messageReadStatus = new MessageReadStatus();
                    MessageStatusKey messageStatusKey = new MessageStatusKey();
                    messageStatusKey.setSender_id(value.getSender_id());
                    messageStatusKey.setReceiver_id(value.getConversation_id());
                    messageReadStatus.setMessageStatusKey(messageStatusKey);
                    messageReadStatus.setMessage_id(value.getMessage_id());
                    messageReadStatus.setStatus(0);
                    return messageReadStatus;
                }).toList();
                messageReadStatusRepository.saveAll(messageReadStatusList);
            }
            redisTemplate.delete(key);

        }

    }
}
