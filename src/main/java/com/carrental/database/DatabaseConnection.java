package com.carrental.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//This class handles Database connectivity

public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "/db.properties";


    public static Connection getConnection() throws SQLException{
        Properties properties = new Properties();
        try (InputStream input = DatabaseConnection.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new IOException("Properties file not found: " + PROPERTIES_FILE);
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load database credentials", e);
        }
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, username, password);

    }


}
