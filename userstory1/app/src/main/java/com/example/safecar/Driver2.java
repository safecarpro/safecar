package com.example.safecar;

public class Driver2 {

    private int id;

    private String name;
    private String address;
    private String age;
    private String gender;
    private String price;
    private String badge;
    private String location;
    private String yoe;
    private String phno;
    private String email;
    private byte[] image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPrice() {
        return price;
    }

    public String getBadge() {
        return badge;
    }

    public String getLocation() {
        return location;
    }

    public String getYoe() {
        return yoe;
    }

    public String getPhno() {
        return phno;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getImage() {
        return image;
    }

    public Driver2(int id, String name, String address, String age, String gender, String price, String badge, String location, String yoe, String phno, String email, byte[] image)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.badge = badge;
        this.location = location;
        this.yoe = yoe;
        this.phno = phno;
        this.email = email;
        this.image = image;
    }

}
