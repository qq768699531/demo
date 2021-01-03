package com.mxcsystem.demo.entity.base;

public class Manager {
    private int ID;
    private String name;
    private String password;
    private String userID;
    private int status;

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
}
