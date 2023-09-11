package com.boojux.ftchatchannel.bean.DTO.backend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TokenResp extends BackParent {
    private AccessTokenDTO data;
}
