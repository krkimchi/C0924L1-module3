package com.codegym.furama_resort.model;

public class EducationDegree {
    private int id;
    private String name;

    public EducationDegree(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
