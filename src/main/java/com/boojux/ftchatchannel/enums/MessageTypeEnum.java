package com.boojux.ftchatchannel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageTypeEnum {
    FRIEND_REQUEST(1),
    GROUP_JOIN_PASS(2),
    ;
    private final Integer type;
}
