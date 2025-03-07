package com.boojux.ftchatchannel.bean.domain.mysql;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FriendRequest {
    private String requestee;
    private String timestamp;
    private String requester;
    private String status;
    private String message;

    // getters and setters
}

