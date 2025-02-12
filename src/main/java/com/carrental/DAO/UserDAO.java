package com.carrental.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.carrental.database.DatabaseConnection;
import com.carrental.models.User;


public class UserDAO {
    // Initialize the Database connection
    private Connection conn;

    public UserDAO() throws SQLException {
        conn = DatabaseConnection.getConnection();
    }

    public boolean AddUser(User user) {
        // This method adds a new user to the database
        String query = "INSERT INTO users (first_name, middle_name, last_name, email, password, user_type) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, user.getFirstName());
            // This condition covers for values that can be null
            if (user.getMiddleName() != null) {
                psmt.setString(2, user.getMiddleName());
            } else {
                psmt.setNull(2, Types.VARCHAR);
            }
            psmt.setString(3, user.getLastName());
            psmt.setString(4, user.getEmail());
            psmt.setString(5, user.getPassword());
            psmt.setString(6, user.getUserType());
            return psmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // logging service will be replaced here
            e.printStackTrace();
            throw new RuntimeException("Error adding user to database", e);
        }
    }

    // Get the email of all the users and store it in email list
    public String getPassword(String email) throws SQLException {
        String query = "SELECT password from users WHERE email=?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, email);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                return rs.getString("password");
            }
        }
        return null;
    }

    public List<String> getUserEmails() throws SQLException{
        List<String> userEmail = new ArrayList<>();
        String query = "SELECT email FROM users";
        try (PreparedStatement psmt = conn.prepareStatement(query)){
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                userEmail.add(rs.getString("email"));
            }
        }
        return userEmail;
    }

    public boolean UpdatePassword(String email, String hashedPassword) throws SQLException{
        String updateQuery = "UPDATE users SET password=? WHERE email=?";
        try (PreparedStatement psmt = conn.prepareStatement(updateQuery)){
            psmt.setString(1,hashedPassword);
            psmt.setString(2,email);
            return psmt.executeUpdate() > 0;
        }

    }
    // Get user id by email
    public int getUserID(String email) throws SQLException{
        String query = "SELECT user_id FROM users where email=?";
        try (PreparedStatement psmt = conn.prepareStatement(query)){
            psmt.setString(1,email);
            ResultSet resultSet = psmt.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("user_id");
            }
        }
        return 0;
    }

    // Validate if the user email is an admin
    public boolean isAdmin(String email) throws SQLException{
        String adminCheck = "SELECT user_type FROM users WHERE email=?";
        try(PreparedStatement psmt = conn.prepareStatement(adminCheck)){
            psmt.setString(1,email);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()){
                return "Admin".equals(rs.getString("user_type"));
            }

        }
        return false;
    }
    // These methods can only be run by an admin
    public List<User> getUsers() throws SQLException{
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement psmt = conn.prepareStatement(query);
        ResultSet rs = psmt.executeQuery()){
            while (rs.next()){
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("middle_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("user_type")
                ));
            }

        }
        return users;
    }

    public boolean deleteUser(int user_id) throws  SQLException{
        String deleteQuery =  "DELETE FROM users WHERE user_id=?";
        try (PreparedStatement psmt = conn.prepareStatement(deleteQuery)){
            psmt.setInt(1, user_id);
            return psmt.executeUpdate() > 0;
        }
    }

    public boolean emailExists(String email) throws  SQLException{
        String query = "SELECT COUNT(*) AS count FROM users WHERE email=?";
        try (PreparedStatement psmt = conn.prepareStatement(query)){
            psmt.setString(1,email);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()){
                int count = rs.getInt("count");
                return count > 0; //if email exists return True
            }
            else {
                return false;
            }
        }
    }

    /* This method hashed my already stored passwords
    public void updatePasswordsForUsers() {
        String selectQuery = "SELECT user_id, password FROM users WHERE user_id BETWEEN 1 AND 5";
        String updateQuery = "UPDATE users SET password = ? WHERE user_id = ?";

        try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
             ResultSet rs = selectStmt.executeQuery();
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            int updatedCount = 0;

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String storedPassword = rs.getString("password");

                // Check if password is already hashed
                if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$") || storedPassword.startsWith("$2y$")) {
                    System.out.println("User ID " + userId + " already has a hashed password. Skipping...");
                    continue;
                }

                // Hash the plain text password
                String hashedPassword = BCrypt.hashpw(storedPassword, BCrypt.gensalt());

                // Update the password in the database
                updateStmt.setString(1, hashedPassword);
                updateStmt.setInt(2, userId);
                updateStmt.executeUpdate();
                updatedCount++;
            }

            System.out.println("✅ Passwords updated for " + updatedCount + " users.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Error updating passwords: " + e.getMessage());
        }
    } */

}