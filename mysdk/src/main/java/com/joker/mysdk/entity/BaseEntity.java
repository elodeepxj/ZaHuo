package com.joker.mysdk.entity;

/**
 * Created by tv on 2018/6/29.
 */

public class BaseEntity {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
