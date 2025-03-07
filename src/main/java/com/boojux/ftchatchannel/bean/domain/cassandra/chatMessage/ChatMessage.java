package com.boojux.ftchatchannel.bean.domain.cassandra.chatMessage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * create table chat_message
 * (
 *     conversation_id           text,
 *     timestamp                 timestamp,
 *     message_id                text,
 *     content                   text,
 *     is_group_chat             boolean,
 *     message_type              int,
 *     sender_id                 text,
 *     sentiment_analysis_result text,
 *     primary key (conversation_id, timestamp, message_id)
 * )
 *             with clustering order by (timestamp desc, message_id asc);
 */
@Table("chat_message")
@Getter
@Setter
@ToString
public class ChatMessage {
    @PrimaryKey
    private ChatMessagePrimaryKey key;
    private String content;
    private String sender_id;
    private String sentiment_analysis_result;
    private Boolean is_group_chat;
    private Integer message_type;
}
