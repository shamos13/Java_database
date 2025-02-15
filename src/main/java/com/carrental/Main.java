package com.carrental;
import com.carrental.DAO.BookingDAO;
import com.carrental.DAO.CarDAO;
import com.carrental.DAO.UserDAO;
import com.carrental.Services.BookingService;
import com.carrental.Services.CarService;
import com.carrental.Services.UserService;
import com.carrental.controllers.AdminMenu;
import com.carrental.controllers.MenuController;
import com.carrental.models.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Main  {


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
        //AdminMenu adm = new AdminMenu(input,cs);
        //adm.welcomeScreen();

    }

}
