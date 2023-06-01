package com.example.sit708_my_application_01;

public class UserClass {

    private String UserId;
    private String FullName;
    private String Password;

    public UserClass(){}

    public UserClass(String userId, String fullName, String password) {
        UserId = userId;
        FullName = fullName;
        Password = password;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
