package com.base.auth.utils;

import java.util.Date;
import java.util.Map;

public class ApiValidationError {
    private Date timestamp = new Date();
    private int code;
    private String title;
    private Map<String, String> message;


    public ApiValidationError(int code, String title, Map<String, String> message) {
        this.code = code;
        this.title = title;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
