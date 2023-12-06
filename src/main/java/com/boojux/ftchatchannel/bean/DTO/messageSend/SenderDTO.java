package com.boojux.ftchatchannel.bean.DTO.messageSend;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SenderDTO {
    private String avatar;
    private String bio;
    @SerializedName("conversation_id")
    private int sentimentAnalysisEnabled;
    private String username;
}
