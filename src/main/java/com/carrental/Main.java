package com.carrental;
import com.carrental.DAO.CarDAO;
import com.carrental.DAO.UserDAO;
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
        //MenuController menuController = new MenuController(usc,cs,input);
        //menuController.welcomeScreen();
        AdminMenu adm = new AdminMenu(input,cs);
        adm.welcomeScreen();

    }

}
