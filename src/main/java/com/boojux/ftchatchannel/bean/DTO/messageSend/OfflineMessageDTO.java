package com.boojux.ftchatchannel.bean.DTO.messageSend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OfflineMessageDTO {
    private String conversation_id;
    private Date timestamp;
    private String message_id;
    private String content;
    private boolean is_group_chat;
    private int message_type;
    private String sender_id;
    private String sentiment_analysis_result;
}
