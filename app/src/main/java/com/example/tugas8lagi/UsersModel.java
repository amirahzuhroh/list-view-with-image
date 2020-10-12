package com.example.tugas8lagi;

import java.io.Serializable;

public class UsersModel implements Serializable {

    private String userName;
    private int image;

    public UsersModel(String userName, int image) {
        this.userName = userName;
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
