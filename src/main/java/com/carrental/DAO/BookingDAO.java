package com.carrental.DAO;

import com.carrental.Services.UserService;
import com.carrental.database.DatabaseConnection;
import com.carrental.models.Booking;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private final Connection connection;

    public BookingDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public boolean newBooking(Booking booking){
        String query = "INSERT INTO bookings(user_id, start_date,end_date,total_price,booking_status,car_id) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement psmt = connection.prepareStatement(query)){
           psmt.setInt(1, booking.getUserID());
           psmt.setDate(2, Date.valueOf(booking.getStartDate()));// Converts it to local Date
           psmt.setDate(3, Date.valueOf(booking.getEndDate()));
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
    public List<Booking> getUserBooking(Booking booking) throws SQLException{
        String query = "SELECT bookings.booking_id,Cars.model, Cars.brand,  bookings.start_date, bookings.end_date, bookings.total_price, bookings.booking_status, Cars.car_id FROM Cars inner join bookings on Cars.car_id = bookings.car_id WHERE bookings.user_id=?";
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement psmt = connection.prepareStatement(query)){
            psmt.setInt(1,booking.getUserID());
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                bookings.add(new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("car_id"),
                        rs.getString("Cars.model"),
                        rs.getString("Cars.brand"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getDouble("total_price"),
                        rs.getString("booking_status")
                        ));
            }
            return bookings;
        }
    }
    public List<Integer> getBookingID(Booking booking) throws SQLException{
        String query = "SELECT booking_id FROM bookings WHERE user_id=?";
        try (PreparedStatement psmt = connection.prepareStatement(query)){
            psmt.setInt(1,booking.getUserID());
            ResultSet rs = psmt.executeQuery();
            List<Integer> bookingID = new ArrayList<>();
            while (rs.next()){
                bookingID.add(rs.getInt("booking_id"));
            }
            return bookingID;
        }
    }
    public boolean clearBooking(int bookingID) throws  SQLException{
        String query = "DELETE FROM bookings WHERE booking_id=?";
        try (PreparedStatement psmt = connection.prepareStatement(query)){
            psmt.setInt(1,bookingID);
            return psmt.executeUpdate() > 0;

        }
    }

}
