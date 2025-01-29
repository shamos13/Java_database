package com.carrental;
import com.carrental.DAO.carDAO;
import com.carrental.models.Car;
import com.carrental.Services.carService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main  {
    Scanner input = new Scanner(System.in);
    carDAO carDAO = new carDAO();
    carService cs = new carService(carDAO);


    public Main() throws SQLException {
    }

    public static void main(String[] args) throws SQLException{

        Main m = new Main();
        m.welcomeScreen();
    }
    public void loginScreen() throws SQLException{
        System.out.println(String.format("""
                ======================================================================
                |\t\t Kiongozi Travellers                                           |
                ======================================================================
                Hi <user> welcome to Kiongozi travellers what would you like to do:
                1. Get available cars
                2. See my Bookings
                3. Request a new car
                4. Add a new car
                5. Get all cars
                6. Update a car
                7. Delete a car
                """));
        System.out.print("Option: ");
        int option = input.nextInt();
        switch (option){
            case 1:
                availableCars();
                break;
            case 4:
                cs.addNewCar();
                break;

            case 5:
                cs.displayAllCars();
                break;
            case 6:
                getAllCars();
                Scanner input = new Scanner(System.in);
                System.out.print("Car_id: ");
                int id = input.nextInt();
                input.nextLine();
                System.out.print("Status: ");
                String st = input.nextLine();
                cs.updateCar(st,id);
                break;
            case 7:
                getAllCars();
                input = new Scanner(System.in);
                System.out.print("Car_id: ");
                id = input.nextInt();
                cs.deleteCar(id);
                getAllCars();
                break;

            default:
                System.out.println("Invalid option!!!");
        }
    }
    public void welcomeScreen() throws SQLException{
        System.out.println(String.format("""
                +++++++++++++++++++++++++++++++++++++++++
                Hi <user> welcome to kiongozi travellers
                +++++++++++++++++++++++++++++++++++++++++
                Would you like to:
                1. Signup 
                2. login
                """));
        System.out.print("Option: ");
        int option = input.nextInt();
        switch (option){
            case 1:
                // logic goes here
                break;
            case 2:
                loginScreen();
                break;

            default:
                System.out.println("Invalid option!!");
                break;
        }
    }
    public void availableCars() throws SQLException{
        List<Car> cars = carDAO.getAvailableCars();

        for (Car car : cars){
            System.out.println(
                    String.format("""
                            Car_id:     %d
                            Model:      %s
                            Brand:      %s
                            Car_year:   %d
                            No. plate:  %s
                            Dailyprice: $%.2f
                            Status:     %s
                            Engine_Capacity: %d cc
                            """,car.getId(),car.getModel(),car.getBrand(),car.getModelYear(),car.getRegistrationNumber(),car.getPricePerDay(),car.getCarStatus(),car.getEngineSize()
                    )
            );
            System.out.println("===================================================================");
        }
    }
    public void getAllCars() throws SQLException{
        List<Car> cars = carDAO.getAllCars();
        for (Car car : cars){
            System.out.println(
                    String.format("""
                            Car_id:     %d
                            Model:      %s
                            Brand:      %s
                            Car_year:   %d
                            No. plate:  %s
                            Dailyprice: $%.2f
                            Status:     %s
                            Engine_Capacity: %d cc
                            """,car.getId(),car.getModel(),car.getBrand(),car.getModelYear(),car.getRegistrationNumber(),car.getPricePerDay(),car.getCarStatus(),car.getEngineSize()
                    )
            );
            System.out.println("===================================================================");
        }

    }
}
