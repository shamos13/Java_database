package com.carrental.models;

public class User {
    // A class that holds user dataFields
    private int id;
    private  String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String userType;

    // Constructor methods for user signup

    public User(String firstName, String lastName, String email, String password, String userType){
        this.firstName = firstName;
        //this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // Constructor for retrieving users
    public User(int id,String firstName,String middleName ,String lastName, String email, String userType){
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }

    // This constructor gets the email already stored
    public User(String email) {
        this.email = email;
    }

    // Getters and setters for each data fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
