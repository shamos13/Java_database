package com.carrental;
import com.carrental.DAO.BookingDAO;
import com.carrental.DAO.CarDAO;
import com.carrental.DAO.UserDAO;
import com.carrental.Services.BookingService;
import com.carrental.Services.CarService;
import com.carrental.Services.UserService;
import com.carrental.controllers.MenuController;


import java.sql.SQLException;
import java.util.Scanner;

public class Main  {
// This is the main entry point for the program

    public static void main(String[] args) throws SQLException{
        Scanner input = new Scanner(System.in);
        CarDAO carDAO = new CarDAO();
        CarService cs = new CarService(carDAO);
        UserDAO userDAO = new UserDAO();
        UserService usc = new UserService(userDAO);
        BookingDAO bookingDAO = new BookingDAO();
        BookingService bs = new BookingService(bookingDAO);
        MenuController menuController = new MenuController(usc,cs,bs,input);
        menuController.welcomeScreen();


    }

}
