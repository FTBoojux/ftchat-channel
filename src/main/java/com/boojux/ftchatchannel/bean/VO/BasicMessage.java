package com.boojux.ftchatchannel.bean.VO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicMessage {
    private String sender;
    private String receiver;
    private String content;
    private String timestamp;
    private Integer type;
}
