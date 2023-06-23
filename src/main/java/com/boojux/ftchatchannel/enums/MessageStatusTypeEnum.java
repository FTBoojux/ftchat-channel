package com.boojux.ftchatchannel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageStatusTypeEnum {

    UNREAD("UNREAD", "未读"),
    READ("READ", "已读"),
    ;

    private final String status;
    private final String description;

}
