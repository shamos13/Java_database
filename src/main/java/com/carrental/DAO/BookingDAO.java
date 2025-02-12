package com.carrental.DAO;

import com.carrental.Services.UserService;
import com.carrental.database.DatabaseConnection;
import com.carrental.models.Booking;

import java.io.PipedReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDAO {
    private final Connection connection;

    public BookingDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public boolean newBooking(Booking booking){
        String query = "INSERT INTO bookings(user_id, start_date,end_date,total_price,booking_status,car_id) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement psmt = connection.prepareStatement(query)){
           psmt.setInt(1, booking.getUserID());
           psmt.setDate(2, new Date(booking.getStartDate().getTime()));
           psmt.setDate(3,new Date(booking.getEndDate().getTime()));
           psmt.setDouble(4,booking.getTotalPrice());
           psmt.setString(5,booking.getStatus());
           psmt.setInt(6,booking.getCarID());

           return psmt.executeUpdate() > 0;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
