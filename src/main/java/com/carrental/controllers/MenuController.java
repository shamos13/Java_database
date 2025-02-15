package com.carrental.controllers;

import com.carrental.Services.BookingService;
import com.carrental.Services.CarService;
import com.carrental.Services.UserService;
import com.carrental.models.Booking;
import com.carrental.models.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class MenuController {
    //this class controls the menu of the system
    private final Scanner scanner;
    private final UserService userService;
    private final CarService carService;
    private final BookingService bookingService;

    public MenuController(UserService userService, CarService carService, BookingService bookingService,Scanner scanner){
        this.userService = userService;
        this.carService = carService;
        this.bookingService = bookingService;
        this.scanner = scanner;
    }

    public void welcomeScreen()throws SQLException {
            System.out.println("""
                    +++++++++++++++++++++++++++++++++++++++++
                    Hi! welcome to kiongozi travellers
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
   public boolean newUser(){
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
        return userService.SignUp(user);
    }
    
   public void loginScreen() throws SQLException{
       System.out.println(String.format("""
                ======================================================================
                |\t\t\t\t\t Kiongozi Travellers
                ======================================================================
                Hi! welcome to Kiongozi travellers what would you like to do:
                1. Get available cars
                2. See my Bookings
                3. Request a new car
                4. Clear a booking
                """));
       System.out.print("Option: ");
       int option = scanner.nextInt();
       switch (option){
           case 1:
           carService.displayAvailableCars();
           break;

           case 2:
               bookingService.displayUserBookings(userService.getUserID());
               break;

           case 3:
               if (newBooking()){
                   System.out.println("You've successfully Rented a car");
               }
               else {
                   newBooking();
               }
               break;
           case 4:
               bookingService.displayUserBookings(userService.getUserID());
               System.out.print("Enter BookingID: ");
               int bookingID = scanner.nextInt();
               bookingService.clearBooking(bookingID,userService.getUserID());


       }

   }
   public boolean newBooking() throws SQLException {
       //Display available cars for booking
        carService.displayAvailableCars();
        System.out.print("Enter the CarID of the car you want: ");
        int carID = scanner.nextInt();
        scanner.nextLine();

        int userID = userService.getUserID();
        String startDate = "Input StartDate as (dd-MM-yyyy): ";
        LocalDate StartDate = bookingService.validateDate(scanner, startDate);
        String endDate = "Input EndDate as (dd-MM-yyyy): ";
        LocalDate EndDate = bookingService.validateDate(scanner, endDate);
        long difference = ChronoUnit.DAYS.between(StartDate,EndDate);
        long total_price = (long) (difference * (carService.getTotalPrice(carID)));
        String bookingStatus = "confirmed";
        Booking booking = new Booking(userID, StartDate, EndDate, total_price, bookingStatus, carID);
        return bookingService.addBooking(booking);
   }
}