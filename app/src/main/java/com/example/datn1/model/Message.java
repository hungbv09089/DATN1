package com.example.datn1.model;

import java.util.Objects;

public class Message {
    private String content, username;

    public Message(String content, String username) {
        this.content = content;
        this.username = username;
    }

    public Message() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getContent().equals(message.getContent()) &&
                getUsername().equals(message.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent(), getUsername());
    }
}
