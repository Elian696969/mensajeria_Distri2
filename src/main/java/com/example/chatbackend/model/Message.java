package com.example.chatbackend.model;

public class Message {
    private String from;
    private String content;
    private String time;
    
    // 🔹 Nuevo campo para distinguir el tipo de mensaje
    private String type; // Puede ser: "CHAT", "JOIN", "LEAVE"

    public Message() {}

    public Message(String from, String content, String time, String type) {
        this.from = from;
        this.content = content;
        this.time = time;
        this.type = type;
    }

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
