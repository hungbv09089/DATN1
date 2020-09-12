package com.example.datn1.model;

public class TopNew {
    private String title,image;

    public TopNew(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public TopNew() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
