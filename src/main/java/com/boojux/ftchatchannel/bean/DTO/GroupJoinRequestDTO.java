package com.boojux.ftchatchannel.bean.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupJoinRequestDTO {
    private String groupId;
    private String requester;
    private Boolean agree;
}
