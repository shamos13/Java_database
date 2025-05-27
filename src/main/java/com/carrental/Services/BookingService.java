package com.carrental.Services;

import com.carrental.DAO.BookingDAO;
import com.carrental.models.Booking;

import java.awt.print.Book;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class BookingService {
    private final BookingDAO bookingDAO;

    public BookingService(BookingDAO bookingDAO){
        this.bookingDAO = bookingDAO;


    }
    public boolean addBooking(Booking booking){
        long numOfDays = ChronoUnit.DAYS.between(booking.getStartDate(),booking.getEndDate());
        if (booking.getStartDate().isBefore(LocalDate.now())){
            System.out.println("The start date needs to be Current!");
            return false;
        }
        if (booking.getEndDate().isBefore(booking.getStartDate())){
            System.out.println("The End date can not be before the start date");
            return false;
        }


        // Awards a 10% discount on days above a month
        if (numOfDays > 30){
            long totalPrice = (long) (booking.getTotalPrice() * 0.9);
            booking.setTotalPrice(totalPrice);

        }
        if (numOfDays > 90){
            System.out.println("You Can't Book a car for more than 3 months!");
            return false;

        }
        return bookingDAO.newBooking(booking);


    }

    public LocalDate validateDate(Scanner scanner, String prompt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true){
            System.out.print(prompt);
            String dateInput = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateInput, formatter);
                return date;
            }catch (DateTimeParseException e){
                System.out.println("Please use (dd-MM-yyyy) date format");

            }
        }

    }
    public boolean clearBooking(int bookingID,int userID) throws SQLException{
        Booking booking = new Booking(bookingID,userID);
        List<Integer> bookingIDs = bookingDAO.getBookingID(booking);
        if (!bookingIDs.contains(bookingID)){
            System.out.println("The Booking ID is not found!" );

        }
        return bookingDAO.clearBooking(bookingID);
    }

    public void displayBookings(List<Booking> bookings){
        System.out.printf("%-9s | %-6s | %-12s | %-12s | %-12s | %-12s | %-16s | %-10s%n",
                "BookingId","CarID","Model","Brand","Start Date","End Date","Total Price(ksh)","Status");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        for (Booking booking : bookings ){
            System.out.printf("%-9d | %-6d | %-12s | %-12s | %-12s | %-12s | %-16.2f | %-10s%n",
                    booking.getId(),
                    booking.getCarID(),
                    booking.getCarModel(),
                    booking.getCarBrand(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getTotalPrice(),
                    booking.getStatus());
        }
    }

    // displays user bookings by the userID
    public void displayUserBookings(int userID) throws SQLException {
        Booking booking = new Booking(userID);
        displayBookings(bookingDAO.getUserBooking(booking));
    }



}
