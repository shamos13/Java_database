package com.carrental.Services;

import com.carrental.DAO.UserDAO;
import com.carrental.models.User;
import com.carrental.utils.InputValidator;
import com.carrental.utils.PasswordValidator;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// This class validates user login, signup validation and handles all the database logic

public class UserService {
    private final UserDAO userDAO;
    private final Scanner scanner;
    private String loggedInEmail;


    public UserService(UserDAO userDAO) throws SQLException {
        this.userDAO = userDAO;
        this.scanner = new Scanner(System.in);
    }

    public boolean SignUp(User user) {
        if (user.getFirstName() == null || user.getLastName() == null) {
            System.out.println("Name values can not be null!");
            return false;
        }
        if (user.getPassword() == null) {
            System.out.println("Password can not be null");
            return false;
        }
        List<String> validOption = Arrays.asList("Customer", "Admin");
        if (!validOption.contains(user.getUserType())) {
            System.out.println("Invalid user type can either be 'Customer' or 'Admin'");
            return false;
        }
        if (!InputValidator.isValidEmail(user.getEmail())) {
            System.out.println("Invalid email address");
            return false;
        }
        try {
            if (userDAO.emailExists(user.getEmail())){
                System.out.println("Email already exists");
                return false;
            }
            // Hash the passwords before storage
            String hashedPassword = PasswordValidator.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            if (userDAO.AddUser(user)){
                System.out.println("Registration successful! You can now login!");
                return true;
            }
            else {
                System.out.println("Registration unsuccessful! Email may already exist");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    // Validate user login credentials
    public boolean validateLogin() throws SQLException {
        String validatedEmail = verifyEmail();

        if (validatedEmail == null){
            return false;
        }
        if ( validatePassword(scanner, userDAO.getPassword(validatedEmail))){
            loggedInEmail = validatedEmail;
            return true;
        }
        return false;
    }

    // Gets a valid email from the user
    public static String getValidEmail(Scanner scanner){
        while (true){
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            if (InputValidator.isValidEmail(email)){
                return email;
            }
            else {
                System.out.println("Invalid Email! Try again!!");
            }
        }

    }
    // Check if the email exists in the database and validates it at the same time
    public String verifyEmail() throws SQLException {
        String email = getValidEmail(scanner);
        if (!userDAO.getUserEmails().contains(email.toLowerCase())) {
            System.out.println("Email not found. Are you a registered user?");
            return null;
        }
        return email;
    }
    // This method validates the password
    public static boolean validatePassword(Scanner scanner, String hashedPassword){
        for (int i=0;i<3;i++){
            System.out.print("Password: ");
            String password = scanner.nextLine();

            // If the user enters the wrong password give them three tries
            if (PasswordValidator.checkPassword(password, hashedPassword)){
                return true;
            }else  {
                int tries = 2-i;
                if (tries > 0) {
                    System.out.println("Incorrect password! " + tries + " tries left.");
                }
            }
        }
        System.out.println("Access Denied!! Too many incorrect attempts! \nReset your password");
        return false;
    }

    public boolean resetPassword() throws SQLException {
        String email = verifyEmail();
        if (email == null){
            return false;
        }
        System.out.print("New Password: ");
        String newPassword = scanner.nextLine();
        String hashedPassword = PasswordValidator.hashPassword(newPassword);
        return userDAO.UpdatePassword(email, hashedPassword);
    }
    public int getUserID() throws SQLException{
        if (loggedInEmail != null) {
            return userDAO.getUserID(loggedInEmail);
        }
        else {
            throw new RuntimeException("Unable to get User ID! Please restart The System");
        }
    }

    public void adminPrivileges(String email) throws SQLException{
        if (userDAO.isAdmin(email)){


        }
    }
}


