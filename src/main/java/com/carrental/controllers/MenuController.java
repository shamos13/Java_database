package com.carrental.controllers;

import com.carrental.Services.BookingService;
import com.carrental.Services.CarService;
import com.carrental.Services.UserService;
import com.carrental.models.User;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuController {
    //this class controls the menu of the system
    private final Scanner scanner;
    private final UserService userService;
    private final CarService carService;
    //private final BookingService bookingService;

    public MenuController(UserService userService, CarService carService, Scanner scanner){
        this.userService = userService;
        this.carService = carService;
        //this.bookingService = bookingService;
        this.scanner = scanner;
    }

    public void welcomeScreen()throws SQLException {
            System.out.println("""
                    +++++++++++++++++++++++++++++++++++++++++
                    Hi <user> welcome to kiongozi travellers
                    +++++++++++++++++++++++++++++++++++++++++
                    Would you like to:
                    1. Signup\s
                    2. login
                    3. Forgot password
                   \s""");
            System.out.print("option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    newUser();
                    break;
                case 2:
                    if (userService.validateLogin()){
                        loginScreen();
                    }

                    break;
                case 3:
                    userService.resetPassword();
            }

        }

   // sets the parameter for the new user
   public void newUser(){
        System.out.println("=========== Sign Up =============\n+++++++++++++++++++++++++++++++++++++++");
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        String userInput = "Customer";
        User user = new User(firstName,lastName,email,password,userInput);
        userService.SignUp(user);

        if (userService.SignUp(user)){
            System.out.println("Registration Successful, You can now log in");
        }
        else {
            System.out.println("Registration unsuccessful, Email may already exist!");
        }
    }

   public void loginScreen() throws SQLException{
       System.out.println(String.format("""
                ======================================================================
                |\t\t\t\t\t Kiongozi Travellers
                ======================================================================
                Hi <user> welcome to Kiongozi travellers what would you like to do:
                1. Get available cars
                2. See my Bookings
                3. Request a new car
                """));
       System.out.print("Option: ");
       int option = scanner.nextInt();
       switch (option){
           case 1:
           carService.displayAvailableCars();
           break;

           case 2:
               break;

           case 3:
               break;
       }
   }
}