package com.boojux.ftchatchannel.bean.domain.cassandra.MessageStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("message_read_status")
@Getter
@Setter
@ToString
public class MessageReadStatus {
    @PrimaryKey
    private MessageStatusKey messageStatusKey;
    private String message_id;
    private int status;
}
