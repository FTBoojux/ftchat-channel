package com.boojux.ftchatchannel.bean.DTO;

public class TokenDTO {
    private String token;

    @Override
    public String toString() {
        return "TokenDTO{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
