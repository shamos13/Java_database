package com.carrental.controllers;

import com.carrental.Services.CarService;
import com.carrental.Services.UserService;
import com.carrental.models.Car;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
    private final Scanner scanner;
    private final CarService carService;
    private final UserService userService;

    public AdminMenu(Scanner scanner, CarService carService, UserService userService){
        this.carService = carService;
        this.scanner = scanner;
        this.userService = userService;
    }

    public void welcomeScreen() throws SQLException {
        while (true) {
            System.out.println("""
                    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    |\t\t\t\tKiongozi Travellers Admin Control
                    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    Select a menu control option:
                    1. Manage Cars
                    2. Manage users
                    ( 0 to Exit)
                    """);
            System.out.print("Option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option==0){
                System.out.println("Thank you For Trusting US! Good Bye");
                break;
            }

            switch (option) {

                case 1:
                    manageCars();
                    break;
                case 2:
                    manageUsers();
                    break;
            }
        }
    }

    public void manageCars() throws SQLException{
        while (true) {
            System.out.println("""
                    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    |\t\t\t\t\t Admin Car manager
                    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    Would you like to:
                    1. Add a car
                    2. Delete a car
                    3. Change the Status of a Car
                    4. Change the Booking status
                    5. Get All Cars
                    6. Get Available cars
                    (00 to go back to main menu)
                    """);
            System.out.print("Option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 00) {
                welcomeScreen();
                break;
            }
            switch (option) {
                case 1:
                    addNewCar();
                    break;
                case 2:
                    deleteCar();
                    break;
                case 3:
                    break;
                case 5:
                    carService.displayAllCars();
                    break;
                case 6:
                    carService.displayAvailableCars();
                    break;
                default:
                    System.out.println("Invalid option!!");
                    break;

            }
            System.out.println("Press Enter to go back to the menu screen");
            scanner.nextLine();
        }
    }
    public boolean addNewCar(){
        System.out.println("=========== Add new Car=============\n+++++++++++++++++++++++++++++++++++++++");
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Brand: ");
        String Brand = scanner.nextLine();
        System.out.print("Car year: ");
        int carYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Registration Number: ");
        String registrationNumber = scanner.nextLine();
        System.out.print("Daily Price: ");
        double dailyPrice = scanner.nextDouble();
        scanner.nextLine();
        String carStatus = "Available";
        System.out.print("Engine Size: ");
        int engineSize = scanner.nextInt();
        Car car = new Car(model, Brand, carYear, registrationNumber, dailyPrice, carStatus, engineSize);
        return carService.addCar(car);
    }

    public boolean deleteCar() throws SQLException{
        carService.displayAllCars();
        System.out.println("---------------------------------------------------------");
        System.out.print("Enter the Car id of the car you want to delete: ");
        int carID = scanner.nextInt();
        return carService.deleteCar(carID);
    }
    public void manageUsers() throws SQLException {
        boolean success = false;
        while (!success) {
            userService.displayUsers();
            System.out.println("(Press 00 to go back to main menu)");
            try {
                System.out.print("Enter the userID you want to delete: ");
                int userID = scanner.nextInt();
                if (!userService.deleteUsers(userID)) {
                    System.out.println("Operation completed successfully!");
                    success = true;
                } else {
                    System.out.println("Error occurred during operation");
                }

            } catch (Exception e) {
                System.out.println("Invalid input please Enter a Valid USERID!");
                scanner.nextLine();
            }
        }
    }

}
