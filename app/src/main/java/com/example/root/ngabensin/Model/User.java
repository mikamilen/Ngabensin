package com.example.root.ngabensin.Model;

/**
 * Created by adit on 06/01/18.
 */

public class User {
    private String Email;
    private String Name;
    private String Password;


    public User() {

    }

    public User(String name, String password,String email) {
        Name = name;
        Password = password;
        Email = email;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {Email = email;}



}

