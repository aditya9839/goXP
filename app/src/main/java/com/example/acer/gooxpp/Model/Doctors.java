package com.example.acer.gooxpp.Model;

/**
 * Created by acer on 23-Jun-18.
 */

public class Doctors {
    private int id;
    private String doctorName,availabilty;
    private double rating,price;
    private int image;

    public Doctors(int id, String doctorName, String availabilty, double rating, double price, int image) {
        this.id = id;
        this.doctorName = doctorName;
        this.availabilty = availabilty;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getAvailabilty() {
        return availabilty;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

}
