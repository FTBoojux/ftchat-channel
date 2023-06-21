package com.boojux.ftchatchannel.bean;

public class BaseWebSocketFrame <T>{
    private Integer type;
    private String token;
    private T data;

    @Override
    public String toString() {
        return "BaseWebSocketFrame{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
