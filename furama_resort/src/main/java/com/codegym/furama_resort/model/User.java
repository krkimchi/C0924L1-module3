package com.codegym.furama_resort.model;

import jdk.vm.ci.meta.Local;

import java.time.LocalDate;

public class User {
    private String username;
    private String password;
    private String fullName;
    private String type;
    private LocalDate birthday;
    private String gender;
    private String idNumber;
    private String phone;
    private String email;
    private String address;

    public User(String username, String password, String fullName, String type) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.type = type;
    }

    public User(String username, String password, String fullName, String type, LocalDate birthday, String gender, String idNumber, String phone, String email, String address) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.type = type;
        this.birthday = birthday;
        this.gender = gender;
        this.idNumber = idNumber;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}