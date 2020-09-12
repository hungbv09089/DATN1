package com.example.datn1.model;

public class Suckhoe {
    private String imgsuckhoe,titlesuckhoe;

    public Suckhoe(String imgsuckhoe, String titlesuckhoe) {
        this.imgsuckhoe = imgsuckhoe;
        this.titlesuckhoe = titlesuckhoe;
    }

    public Suckhoe() {
    }

    public String getImgsuckhoe() {
        return imgsuckhoe;
    }

    public void setImgsuckhoe(String imgsuckhoe) {
        this.imgsuckhoe = imgsuckhoe;
    }

    public String getTitlesuckhoe() {
        return titlesuckhoe;
    }

    public void setTitlesuckhoe(String titlesuckhoe) {
        this.titlesuckhoe = titlesuckhoe;
    }
}
