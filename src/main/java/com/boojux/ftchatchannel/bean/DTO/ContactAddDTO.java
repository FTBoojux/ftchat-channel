package com.boojux.ftchatchannel.bean.DTO;

public class ContactAddDTO {
    private String targetId;
    private String message;

    @Override
    public String toString() {
        return "ContactAddDTO{" +
                "targetId='" + targetId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
