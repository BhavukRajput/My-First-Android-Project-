package com.example.androidproject;

public class users {
    String Name,UserName,Email,Phoneno,Password;

    public users() {
    }

    public users(String name, String userName, String email, String phoneno, String password) {
        Name = name;
        UserName = userName;
        Email = email;
        Phoneno = phoneno;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(String phoneno) {
        Phoneno = phoneno;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}


