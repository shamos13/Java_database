package com.carrental;
import com.carrental.DAO.carDAO;
import com.carrental.models.Car;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        carDAO carDAO = new carDAO();
        List<Car> cars = carDAO.getAllCars();

        for (Car car : cars){
            System.out.println(
                    String.format("""
                            Car_id:     %d
                            Model:      %s
                            Brand:      %s
                            Car_year:   %d
                            No. plate:  %s
                            Daily_price:%f
                            Status:     %s
                            Engine_Capacity: %d 
                            """,car.getId(),car.getModel(),car.getBrand(),car.getModelYear(),car.getRegistrationNumber(),car.getPricePerDay(),car.getCarStatus(),car.getEngineSize()
                    )
            );
            System.out.println("===================================================================");
        }



    }
}
