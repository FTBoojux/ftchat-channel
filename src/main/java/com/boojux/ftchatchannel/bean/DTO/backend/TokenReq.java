package com.boojux.ftchatchannel.bean.DTO.backend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TokenReq {
    private String email;
    private String password;
}
