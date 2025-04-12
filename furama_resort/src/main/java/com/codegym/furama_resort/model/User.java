package com.codegym.furama_resort.model;

public class User {
    private String username;
    private String password;
    private String fullName;
    private String type;

    public User(String username, String password, String fullName, String type) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}