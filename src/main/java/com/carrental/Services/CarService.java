package com.carrental.Services;
// This class handles the business logic for the CarDAO and car class
import com.carrental.DAO.CarDAO;
import com.carrental.models.Car;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class CarService {
    // This class handles all the business logic for the car
    private final CarDAO carDao;


    public CarService(CarDAO carDao) {
        this.carDao = carDao;
    }

    public boolean addCar(Car car) {
        // make sure the required strings are not null
        if (car.getBrand() == null || car.getModel() == null || car.getRegistrationNumber() == null || car.getEngineSize() == 0) {
            throw new IllegalArgumentException("Car properties can not be null");
        }
        if (car.getPricePerDay() <= 0) {
            throw new IllegalArgumentException("Price per Day can not be less or equal to 0");
        }
        if (car.getModelYear() < 2001) {
            throw new IllegalArgumentException("We do not accept old cars");
        }

        List<String> validStatus = Arrays.asList("Available", "Booked", "Maintenance");
        if (!validStatus.contains(car.getCarStatus())) {
            throw new IllegalArgumentException("'Available', 'Booked', 'Maintenance' are the only options available");
        }
        try {
            return carDao.addCar(car);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error addding car to the database", e);
        }
    }

    public boolean deleteCar(int car_id) throws SQLException {
        List<Integer> carIDs = carDao.getCarID();
        if (!carIDs.contains(car_id)) {
            System.out.println("Car with " + car_id + " does not exist!!");
        }
        return carDao.deleteCar(car_id);
    }

    public boolean updateCar(String carStatus, int car_id) throws SQLException {
        List<String> validStatus = Arrays.asList("Available", "Booked", "Maintenance");
        List<Integer> carIDs = carDao.getCarID();
        if (!validStatus.contains(carStatus)) {
            throw new IllegalArgumentException("Invalid car status");
        }
        if (!carIDs.contains(car_id)){
            throw  new IllegalArgumentException("Invalid ID!!");
        }
        try {
            return carDao.updateCar(carStatus, car_id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Update failed: ", e);
        }
    }

    //Adds new cars
    public boolean addNewCar() {
        Scanner input = new Scanner(System.in);
        System.out.print("Model: ");
        String model = input.nextLine();

        System.out.print("Brand: ");
        String brand = input.nextLine();

        System.out.print("Car year: ");
        int car_year = input.nextInt();
        input.nextLine();
        System.out.print("No. plate: ");
        String registrationNumber = input.nextLine();

        System.out.print("Daily Price: ");
        double pricePerDay = input.nextDouble();
        input.nextLine();
        System.out.print("Car Status: ");
        String carStatus = input.nextLine();
        System.out.print("Engine Size: ");
        int engineSize = input.nextInt();
        Car car = new Car(model, brand, car_year, registrationNumber, pricePerDay, carStatus, engineSize);
        input.close();
        return addCar(car);
    }

    // Display methods
    public void displayCars(List<Car> cars){
        // Print the header
        System.out.printf("%-5s | %-12s | %-12s | %-6s | %-10s | %-12s | %-12s | %-8s%n",
                "ID","Model","Brand","Year","No. Plate","Daily Price", "Status", "Engine Size");
        System.out.println("------------------------------------------------------------------------------------------------------");

        // Get all the cars
        for (Car car : cars){
            System.out.printf("%-5d | %-12s | %-12s | %-6d | %-10s | %-12.2f | %-12s | %-8d%n",
                    car.getId(),
                    car.getModel(),
                    car.getBrand(),
                    car.getModelYear(),
                    car.getRegistrationNumber(),
                    car.getPricePerDay(),
                    car.getCarStatus(),
                    car.getEngineSize());
        }

    }
    public void displayAllCars() throws SQLException{
        displayCars(carDao.getAllCars());
    }

    public void displayAvailableCars() throws  SQLException{
        displayCars(carDao.getAvailableCars());
        }
}