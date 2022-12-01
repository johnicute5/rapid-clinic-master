package com.example.model;

public class NotificationResponse {
    public NotificationResponse() {
    }

    public NotificationResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
}
