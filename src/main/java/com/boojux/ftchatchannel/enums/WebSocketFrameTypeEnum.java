package com.boojux.ftchatchannel.enums;

public enum WebSocketFrameTypeEnum {
    AUTHORIZATION(0, "身份验证"),
    ;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    private final Integer type;
    private final String desc;

    @Override
    public String toString() {
        return "WebSocketFrameTypeEnum{" +
                "type=" + type +
                ", desc='" + desc + '\'' +
                '}';
    }

    WebSocketFrameTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
