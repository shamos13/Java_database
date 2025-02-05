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

  public boolean addCar(Car car) throws SQLException {
      // This method adds a new car into the database
      String query = "INSERT INTO Cars (model, brand, car_year, registration_number, price_perDay, car_status, engine_size) VALUES (?,?,?,?,?,?,?)";
      try{
          PreparedStatement psmt = conn.prepareStatement(query);
         psmt.setString(1,car.getModel());
         psmt.setString(2,car.getBrand());
         psmt.setInt(3,car.getModelYear());
         psmt.setString(4,car.getRegistrationNumber());
         psmt.setDouble(5,car.getPricePerDay());
         psmt.setString(6,car.getCarStatus());
         psmt.setInt(7,car.getEngineSize());
          return psmt.executeUpdate() > 0;

      } catch (SQLException e) {
          System.out.println("Error Message: " + e.getMessage());
      }
      conn.close();
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

    // Get all the available cars from the table
    public List<Car>  getAvailableCars() throws SQLException{
          List<Car> availableCars = new ArrayList<>();
          String query = "SELECT * FROM Cars WHERE car_status='Available'";
          Statement st = conn.createStatement();
          ResultSet rs = st.executeQuery(query);

          while(rs.next()){
              availableCars.add(new Car(
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
          return availableCars;
    }

    public boolean updateCar(String car_status, int car_id) throws SQLException {
          try{
               String updateQuery = "UPDATE Cars SET car_status=? where car_id=?";
               PreparedStatement psmt = conn.prepareStatement(updateQuery);
               psmt.setString(1,car_status);
               psmt.setInt(2,car_id);

               return psmt.executeUpdate() > 0;
          } catch (SQLException e){
              System.out.println("ERROR: " + e.getMessage());
          }

          return false;
    }

    public boolean deleteCar(int car_id){
          String deleteQuery = "DELETE FROM Cars WHERE car_id=?";
          try(PreparedStatement psmt = conn.prepareStatement(deleteQuery)){
              psmt.setInt(1,car_id);
              return psmt.executeUpdate() > 0;
          }
          catch (SQLException e){
              e.printStackTrace();

          }
          return false;
    }

    public List<Integer> getCarID()throws SQLException{
          List<Integer> carIDs = new ArrayList<>();
          String sql = "SELECT car_id FROM Cars";
          PreparedStatement psmt = conn.prepareStatement(sql);
          ResultSet rs = psmt.executeQuery();

          while (rs.next()){
              carIDs.add(rs.getInt("car_id"));
          }

          return carIDs;
    }


}
