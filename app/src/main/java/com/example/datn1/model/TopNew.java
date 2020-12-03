package com.example.datn1.model;

public class TopNew {
    private String titletopview,imagetopview,subtitle,url;

    public TopNew(String titletopview, String imagetopview, String subtitle, String url) {
        this.titletopview = titletopview;
        this.imagetopview = imagetopview;
        this.subtitle = subtitle;
        this.url = url;
    }

    public String getTitletopview() {
        return titletopview;
    }

    public void setTitletopview(String titletopview) {
        this.titletopview = titletopview;
    }

    public String getImagetopview() {
        return imagetopview;
    }

    public void setImagetopview(String imagetopview) {
        this.imagetopview = imagetopview;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
