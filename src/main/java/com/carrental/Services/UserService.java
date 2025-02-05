package com.carrental.Services;

import com.carrental.DAO.UserDAO;
import com.carrental.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// This class validates user login, signup validation and handles all the database logic

public class UserService {
    private final UserDAO userDAO;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+@[A-Za-z.]+$";

    public UserService(UserDAO userDAO)throws SQLException{
        this.userDAO = userDAO;
    }

    public boolean SignUp(User user){
        if (user.getFirstName() == null || user.getLastName() == null){
            throw new IllegalArgumentException("Name values can not be null!!");
        }
        if (user.getPassword()==null){
            throw new IllegalArgumentException("Password can not be null");
        }
        List<String> validOption = Arrays.asList("Customer", "Admin");
        if (!validOption.contains(user.getUserType())){
            throw new IllegalArgumentException("Invalid user type");
        }
       /* if (isValidEmail(user.getEmail())){
            throw new IllegalArgumentException("Invalid email address");
        }*/
        // Hash the password before storage
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userDAO.AddUser(user);
    }


    // Checks and confirms email is valid through regex
    public static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Hash passwords
    private String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Validate user login credentials
    public boolean validateLogin(String UserEmail, String password){
        try {
        String storedPassword = userDAO.getPassword(UserEmail);
        // Checks whether the email is valid
            if (storedPassword == null) {
                throw new IllegalArgumentException("Invalid email or password");
            }
            if (BCrypt.checkpw(password, storedPassword)) {
                System.out.println("Login successful");
                return true;
            }
            else{
                System.out.println("Wrong password!!");
                return false;
            }
        }
        catch (SQLException e){
            throw new RuntimeException("Error!!",e);
        }

    }

    public boolean resetPassword(String email, String newPassword) throws SQLException {

        if (userDAO.getUserEmails().contains(email)){
            try {
                String hashedPassword = hashPassword(newPassword);
                return userDAO.UpdatePassword(email, hashedPassword);
            } catch (SQLException e) {
                throw new RuntimeException("Can't update user password",e);
            }
        }
        else {
            throw new IllegalArgumentException("Email not found");
        }
    }
    public void loginDisplay() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();
        validateLogin(email, password);
    }
}
