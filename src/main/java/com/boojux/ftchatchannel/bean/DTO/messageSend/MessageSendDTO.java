package com.boojux.ftchatchannel.bean.DTO.messageSend;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageSendDTO {
    private String content;
    @SerializedName("conversation_id")
    private String conversationId;
    @SerializedName("message_id")
    private String messageId;
    @SerializedName("message_type")
    private long messageType;
    private SenderDTO sender;
    @SerializedName("sentiment_analysis_result")
    private String sentimentAnalysisResult;
    /**
     * left or right
     */
    private String side;
    private String timestamp;
}
