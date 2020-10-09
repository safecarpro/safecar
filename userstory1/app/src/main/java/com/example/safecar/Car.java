package com.example.safecar;

public class Car {
    private int id;
    private String model;
    private String agency;
    private String brand;
    private String price;
    private String kms;
    private String phn;
    private String location;
    private String email;
    private byte[] image;



    public Car(int id, String brand, String model, String price, String agency, String kms, String phn, String location, String email, byte[] image) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.agency = agency;
        this.kms = kms;
        this.phn = phn;
        this.location = location;
        this.email = email;
        this.image = image;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getkms() {
        return kms;
    }

    public void setkms(String kms) {
        this.kms = kms;
    }

    public String getPhn() {
        return phn;
    }

    public void setphn(String phn) {
        this.phn = phn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.price = email;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
