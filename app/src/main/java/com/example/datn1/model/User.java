package com.example.datn1.model;

public class User {
    private String avatar, name, gmail;

    public User(String avatar, String name, String gmail) {
        this.avatar = avatar;
        this.name = name;
        this.gmail = gmail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
