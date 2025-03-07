package com.boojux.ftchatchannel.bean.domain.cassandra;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

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
    @PrimaryKeyColumn(name = "conversation_id", ordinal = 0)
    private String conversation_id;
    @PrimaryKeyColumn(name = "message_id", ordinal = 2)
    private String message_id;
    private String content;
    private String sender_id;
    private String sentiment_analysis_result;
    @PrimaryKeyColumn(name = "timestamp", ordinal = 1)
    private Date timestamp;
    private Boolean is_group_chat;
    private Integer message_type;
}
