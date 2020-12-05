package com.example.datn1.model;

public class Account {
    String username,password,fullname,doctorID;
    String[] profileID;
    Boolean firstSignIn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String[] getProfileID() {
        return profileID;
    }

    public void setProfileID(String[] profileID) {
        this.profileID = profileID;
    }

    public Boolean getFirstSignIn() {
        return firstSignIn;
    }

    public void setFirstSignIn(Boolean firstSignIn) {
        this.firstSignIn = firstSignIn;
    }
}