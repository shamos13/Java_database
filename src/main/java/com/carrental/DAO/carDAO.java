package com.carrental.DAO;
import com.carrental.models.Car;
import com.carrental.database.databaseConnection;

import java.sql.*;
import java.util.*;

public class carDAO {
    // initialize the database connection
      private Connection conn;
      public carDAO() throws SQLException{
          conn = databaseConnection.getConnection();
      }

  public boolean addCar(Car car){
      // This method adds a new car into the database
      String query = "INSERT INTO Cars (model, brand, car_year, registration_number, price_perDay car_status, engine_size) VALUES (?,?,?,?,?,?,?)";
      try{
          PreparedStatement psmt = conn.prepareStatement(query);
          psmt.setString(1,car.getModel());
          psmt.setString(2,car.getBrand());
          psmt.setInt(3,car.getModelYear());
          psmt.setString(4,car.getRegistrationNumber());
          psmt.setDouble(5,car.getPricePerDay());
          psmt.setString(6,car.getCarStatus());

          return psmt.executeUpdate() > 0;

      } catch (SQLException e) {
          e.printStackTrace();
      }
      return false;

  }

    // Get all cars from the cars table
    public List<Car> getAllCars() throws SQLException{
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Cars";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            cars.add(new Car(
                    rs.getInt("car_id"),
                    rs.getString("model"),
                    rs.getString("brand"),
                    rs.getInt("car_year"),
                    rs.getString("registration_number"),
                    rs.getDouble("price_perDay"),
                    rs.getString("car_status"),
                    rs.getInt("engine_size")
            ));
        }
        return cars;

    }


}
