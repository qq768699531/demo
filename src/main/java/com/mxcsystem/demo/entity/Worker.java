package com.mxcsystem.demo.entity;

public class Worker {
    private int ID;
    private String name;
    private String password;
    private String departments;
    private String position;
    private String year;
    private String userID;
    private int status;
    private String icon;

    public int getID () {
        return ID;
    }

    public void setID (int ID) {
        this.ID = ID;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getDepartments () {
        return departments;
    }

    public void setDepartments (String departments) {
        this.departments = departments;
    }

    public String getPosition () {
        return position;
    }

    public void setPosition (String position) {
        this.position = position;
    }

    public String getYear () {
        return year;
    }

    public void setYear (String year) {
        this.year = year;
    }

    public String getUserID () {
        return userID;
    }

    public void setUserID (String userID) {
        this.userID = userID;
    }

    public int getStatus () {
        return status;
    }

    public void setStatus (int status) {
        this.status = status;
    }

    public String getIcon () {
        return icon;
    }

    public void setIcon (String icon) {
        this.icon = icon;
    }

    public Worker (String name, String password) {
        this.name = name;
        this.password = password;
    }
}
