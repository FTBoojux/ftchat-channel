package com.boojux.ftchatchannel;

import com.boojux.ftchatchannel.bean.DTO.messageSend.OfflineMessageDTO;
import com.boojux.ftchatchannel.message.producer.MessageProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class MessageQueueTest {

    @Autowired
    private MessageProducer messageProducer;
    @Test
    public void sendOfflineMessageTest() {
        OfflineMessageDTO offlineMessageDTO = new OfflineMessageDTO();
        offlineMessageDTO.setConversation_id("123");
        offlineMessageDTO.setContent("hello");
        offlineMessageDTO.set_group_chat(false);
        offlineMessageDTO.setMessage_id("123");
        offlineMessageDTO.setMessage_type(1);
        offlineMessageDTO.setSender_id("123");
        offlineMessageDTO.setSentiment_analysis_result("positive");
        offlineMessageDTO.setTimestamp(new Date());
        messageProducer.sendMessage("offline_message_exchange", "offline_message", offlineMessageDTO);
    }
}
