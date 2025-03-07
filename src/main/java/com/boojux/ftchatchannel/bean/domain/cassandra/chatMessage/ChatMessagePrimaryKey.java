package com.boojux.ftchatchannel.bean.domain.cassandra.chatMessage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.Date;
import java.util.Objects;

@PrimaryKeyClass
@Getter
@Setter
@ToString
public class ChatMessagePrimaryKey {
    @PrimaryKeyColumn(name = "conversation_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String conversationId;
    @PrimaryKeyColumn(name = "timestamp", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Date timestamp;
    @PrimaryKeyColumn(name = "message_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private String message_id;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessagePrimaryKey that = (ChatMessagePrimaryKey) o;
        return Objects.equals(conversationId, that.conversationId) && Objects.equals(timestamp, that.timestamp) && Objects.equals(message_id, that.message_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId, timestamp, message_id);
    }
}
