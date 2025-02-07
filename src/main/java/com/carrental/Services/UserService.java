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


    public UserService(UserDAO userDAO) throws SQLException {
        this.userDAO = userDAO;
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
        //Check if the User is added.
        boolean isUserAdded = userDAO.AddUser(user);
        if (isUserAdded){
            // Hash the password before storage
            String hashedPassword = PasswordValidator.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
        }
        return isUserAdded;
    }


    // Validate user login credentials
    public boolean validateLogin() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String validatedEmail = verifyEmail(scanner);

        if (validatedEmail == null){
            System.out.println("Invalid email");
            return false;
        }
        return validatePassword(scanner, userDAO.getPassword(validatedEmail));
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
    public String verifyEmail(Scanner scanner) throws SQLException {
        String email = getValidEmail(scanner);
        if (userDAO.getUserEmails().contains(email.toLowerCase())) {
            return email;
        }else {
            System.out.println("Email not found. Are you a registered user?");
            return null;
        }
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
        Scanner scanner = new Scanner(System.in);
        String email = verifyEmail(scanner);
        if (email == null){
            System.out.println("Invalid email");
            return false;
        }
        System.out.print("New Password: ");
        String newPassword = scanner.nextLine();
        String hashedPassword = PasswordValidator.hashPassword(newPassword);
        return userDAO.UpdatePassword(email, hashedPassword);
    }
    public void adminPrivileges(String email) throws SQLException{
        if (userDAO.isAdmin(email)){

        }
    }
}
    /*public void loginDisplay() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();
        validateLogin(email, password);
    }*/

