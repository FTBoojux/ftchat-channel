package com.boojux.ftchatchannel.repository.cassandra;

import com.boojux.ftchatchannel.bean.domain.cassandra.chatMessage.ChatMessage;
import com.boojux.ftchatchannel.bean.domain.cassandra.chatMessage.ChatMessagePrimaryKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends CassandraRepository<ChatMessage, ChatMessagePrimaryKey> {
    List<ChatMessage> findAllByKeyConversationId(String conversationId);
}
