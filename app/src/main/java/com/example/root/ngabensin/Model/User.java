package com.example.root.ngabensin.Model;

/**
 * Created by adit on 06/01/18.
 */

public class User {
    private String Email;
    private String Name;
    private String Password;
    private String NameKendaraan;
    private String JenisKendaraan;


//    public User(String s, String toString, String string) {
//
//    }

    public User(String name, String password,String email ) {
        Name = name;
        Password = password;
        Email = email;
//        NameKendaraan = nmkendaraan;
//        JenisKendaraan = jkendaraan;
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

//    public String getNmKendaraan() {
//        return NameKendaraan;
//    }
//    public void setNmKendaraan(String nmkendaraan) {NameKendaraan = nmkendaraan;}
//
//
//    public String getJKendaraan() {
//        return JenisKendaraan;
//    }
//    public void setJkendaraan(String jkendaraan) {JenisKendaraan = jkendaraan;}


    public User() {
    }
}

