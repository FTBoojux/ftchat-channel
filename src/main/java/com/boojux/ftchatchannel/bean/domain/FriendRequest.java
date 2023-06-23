package com.boojux.ftchatchannel.bean.domain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import java.util.UUID;

@Table("friend_requests")
@Getter
@Setter
@ToString
public class FriendRequest {
    @PrimaryKeyColumn(name = "requestee", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID requestee;
    @PrimaryKeyColumn(name = "timestamp", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID timestamp;
    private UUID requester;
    private String status;
    private String message;

    // getters and setters
}

