package com.example.datn1.model;

public class Profile {
    private String name,numberPhone,address,mail,birthday,sex,cmnd;

    public Profile() {
    }

    public Profile(String name, String numberPhone, String address, String mail, String birthday, String sex,String cmnd) {
        this.name = name;
        this.numberPhone = numberPhone;
        this.address = address;
        this.mail = mail;
        this.birthday = birthday;
        this.cmnd = cmnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }
}
