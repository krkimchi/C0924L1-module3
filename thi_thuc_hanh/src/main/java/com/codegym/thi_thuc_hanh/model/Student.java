package com.codegym.thi_thuc_hanh.model;

public class Student {
    private int studentId;
    private String fullName;
    private String className;

    public Student(int studentId, String fullName, String className) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.className = className;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
