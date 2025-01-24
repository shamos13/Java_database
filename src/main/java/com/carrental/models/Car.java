package com.carrental.models;

public class Car {

    // This class contains all the properties of Car objects
    private int id;
    private String model;
    private String carStatus;
    private double pricePerDay;
    private int engineSize;
    private int modelYear;
    private String registrationNumber;
    private String brand;


    public Car(int id, String model, String brand, int car_year, String registration_number, double price_perDay, String car_status, int engine_size) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.modelYear = car_year;
        this.registrationNumber = registration_number;
        this.pricePerDay = price_perDay;
        this.carStatus = car_status;
        this.engineSize = engine_size;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }



    // Getters and setters for each data fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }
}
