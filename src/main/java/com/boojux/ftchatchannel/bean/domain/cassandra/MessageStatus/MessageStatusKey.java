package com.boojux.ftchatchannel.bean.domain.cassandra.MessageStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
@Getter
@Setter
@ToString
public class MessageStatusKey {
    @PrimaryKeyColumn(name = "receiver_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String receiver_id;
    @PrimaryKeyColumn(name = "sender_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String sender_id;

}
