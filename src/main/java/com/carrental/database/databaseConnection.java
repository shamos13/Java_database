package com.carrental.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    // This class handles the database connectivity
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Tarak#23h!";
    private static final String URL = "jdbc:mysql://localhost:3306/rentalSystem";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);

    }


}
