package com.carrental.models;

import java.time.LocalDate;
import java.util.Date;

public class Booking {
    // Data fields
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private String status;
    private int userID;
    private int carID;
    private String carModel;
    private String carBrand;

    // This constructor is used for adding new bookings
    public Booking(int userID, LocalDate startDate, LocalDate endDate, double totalPrice, String status, int carID){
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.carID = carID;

    }
    // This constructor retrieves the values for bookings using only the user id
    public Booking(int userID){

        this.userID = userID;
    }
    //This retrieves the Booking ID
    public Booking(int bookingID, int UserID){
        this.id = bookingID;
        this.userID = UserID;
    }
    // This constructor gets the userBookings or the full booking table
    public Booking(int bookingId,int carID, String carModel,String carBrand, LocalDate startDate, LocalDate endDate, double totalPrice, String bookingStatus) {
        this.id = bookingId;
        this.carID = carID;
        this.carModel = carModel;
        this.carBrand = carBrand;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.status = bookingStatus;
    }

    // Accessor methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }
}

