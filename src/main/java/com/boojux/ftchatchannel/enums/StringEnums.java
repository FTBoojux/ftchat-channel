package com.boojux.ftchatchannel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StringEnums {
    RABBIT_MQ_EXCHANGE("ftchat.friend.add"),
    RABBIT_MQ_ROUTING_KEY("ftchat.friend.add"),
    ;
    private final String value;
}
