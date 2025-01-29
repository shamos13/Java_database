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

    // This constructor gets all the car details
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

    // This constructor method is used for getting car details before any update or delete
    public Car(int id, String model, String brand, String carStatus){
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.carStatus = carStatus;
    }

    public Car(String model, String brand, int carYear, String registrationNumber, double pricePerDay, String carStatus, int engineSize) {
        this.model = model;
        this.brand = brand;
        this.modelYear = carYear;
        this.registrationNumber = registrationNumber;
        this.pricePerDay = pricePerDay;
        this.carStatus = carStatus;
        this.engineSize = engineSize;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String setBrand(String brand) {
        this.brand = brand;
        return brand;
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

    public String setModel(String model) {
        this.model = model;
        return model;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public String setEngineSize(int engineSize) {
        this.engineSize = engineSize;
        return null;
    }

    public int getModelYear() {
        return modelYear;
    }

    public int setModelYear(int modelYear) {
        this.modelYear = modelYear;
        return modelYear;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public double setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
        return pricePerDay;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public String setCarStatus(String carStatus) {
        this.carStatus = carStatus;
        return carStatus;
    }
}
