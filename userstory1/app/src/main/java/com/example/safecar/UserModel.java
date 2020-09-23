package com.example.safecar;


public class UserModel {
    public String user_id;
    public String username;
    public String address;
    public String gender;
    public String email;
    public String phnno;
    public String password;
    public String location;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public UserModel(String user_id, String username, String address,String gender, String email, String phnno, String password, String location) {
        this.user_id = user_id;
        this.username = username;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.phnno = phnno;
        this.password = password;
        this.location = location;
    }
}
