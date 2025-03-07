package com.boojux.ftchatchannel.bean.domain.cassandra;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("message_read_status")
@Getter
@Setter
@ToString
public class MessageReadStatus {
    @PrimaryKeyColumn(name = "receiver_id", ordinal = 0)
    private String receiver_id;
    @PrimaryKeyColumn(name = "conversation_id", ordinal = 1)
    private String sender_id;
    private String message_id;
    private int status;
}
