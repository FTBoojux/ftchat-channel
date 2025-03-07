package com.boojux.ftchatchannel;

import cn.hutool.core.collection.CollectionUtil;
import com.boojux.ftchatchannel.bean.domain.cassandra.chatMessage.ChatMessage;
import com.boojux.ftchatchannel.bean.domain.mysql.Participant;
import com.boojux.ftchatchannel.repository.cassandra.ChatMessageRepository;
import com.boojux.ftchatchannel.repository.mysql.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CassandraTest {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Test
    public void cassandraTest(){
        List<ChatMessage> allByConversationId = chatMessageRepository.findAllByKeyConversationId("3");
        System.out.println(allByConversationId);
        assert CollectionUtil.isNotEmpty(allByConversationId);
    }
    @Test
    public void participantTest(){
        List<Participant> allByConversation = participantRepository.findAllByConversation(3);
        System.out.println(allByConversation);
        assert CollectionUtil.isNotEmpty(allByConversation);
    }
}
