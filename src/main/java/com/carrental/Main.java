package com.carrental;
import com.carrental.DAO.CarDAO;
import com.carrental.DAO.UserDAO;
import com.carrental.Services.CarService;
import com.carrental.Services.UserService;
import com.carrental.models.User;

import javax.xml.transform.Source;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main  {
    Scanner input = new Scanner(System.in);
    CarDAO carDAO = new CarDAO();
    CarService cs = new CarService(carDAO);
    UserDAO userDAO = new UserDAO();
    UserService usc = new UserService(userDAO);

    public Main() throws SQLException {
    }

    public static void main(String[] args) throws SQLException{

        Main m = new Main();
        m.welcomeScreen();
    }
    public void loginScreen() throws SQLException{
        System.out.println(String.format("""
                ======================================================================
                |\t\t Kiongozi Travellers                                           |
                ======================================================================
                Hi <user> welcome to Kiongozi travellers what would you like to do:
                1. Get available cars
                2. See my Bookings
                3. Request a new car
                4. Add a new car
                5. Get all cars
                6. Update a car
                7. Delete a car
                """));
        System.out.print("Option: ");
        int option = input.nextInt();
        switch (option){
            case 1:
                cs.displayAvailableCars();
                break;

            case 2:
               // getEmail();
                break;

            case 4:
                cs.addNewCar();
                break;

            case 5:
                cs.displayAllCars();
                break;
            case 6:

                Scanner input = new Scanner(System.in);
                System.out.print("Car_id: ");
                int id = input.nextInt();
                input.nextLine();
                System.out.print("Status: ");
                String st = input.nextLine();
                cs.updateCar(st,id);
                break;
            case 7:
                cs.displayAllCars();
                input = new Scanner(System.in);
                System.out.print("Car_id: ");
                id = input.nextInt();
                cs.deleteCar(id);
                cs.displayAllCars();
                break;

            default:
                System.out.println("Invalid option!!!");
        }
    }
    public void welcomeScreen() throws SQLException{
        System.out.println(String.format("""
                +++++++++++++++++++++++++++++++++++++++++
                Hi <user> welcome to kiongozi travellers
                +++++++++++++++++++++++++++++++++++++++++
                Would you like to:
                1. Signup 
                2. login
                3. Forgot password
                """));
        System.out.print("Option: ");
        int option = input.nextInt();
        switch (option){
            case 1:
                newUser();
                break;
            case 2:
                usc.loginDisplay();
                loginScreen();
                break;

            case 3:
                reset();
                //usc.resetPassword("akwatuha046@gmail.com", "tarak#23h!");
                break;

            default:
                System.out.println("Invalid option!!");
                break;
        }
    }
    public boolean newUser(){
        Scanner input = new Scanner(System.in);
        System.out.print("First name: ");
        String firstName = input.nextLine();
        System.out.print("Last name: ");
        String lastName = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();
        System.out.print("User Type: ");
        String userInput = input.nextLine();
        User user = new User(firstName,lastName,email,password,userInput);
        return usc.SignUp(user);

    }
    public boolean reset() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("New Password: ");
        String password = input.nextLine();
        return usc.resetPassword(email, password);
    }



}
