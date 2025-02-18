<<<<<<< HEAD
This is a small fun practice project which Mimics the car rental system. 
The purpose of this project is to challenge my understanding of Java's OOP concepts and Dependency injection, which gears me towards 
becoming a Springboot developer.
This project was entirely built using raw java (without a framework), JDBC (for database connectivity) and uses Maven for Dependency Management. 
Interaction with this program is mainly from the terminal.

The main entry point for this program is from the main class.
=======
# Car Rental System

## **Overview**

The Car Rental System is designed for Kiongozi Travellers to manage car rentals efficiently. It provides user-friendly interfaces for both customers and administrators to perform operations such as signing up, logging in, managing car rentals, and viewing bookings all via the Command line interface.

---

## **Features**

### **1. User Management**

- **Signup and Login**: Users can sign up and log in using email and password.
- **Password Hashing**: Passwords are securely hashed before being stored in the database.
- **Forgot Password**: Users can reset their password if forgotten.
- **Role-Based Access**: The system supports two roles:
  - Customer
  - Admin

### **2. Car Management (Admin Only)**

- Add new cars to the system.
- Delete cars from the system.
- Change the status of cars (e.g., available or unavailable).
- View all cars and filter available cars.

### **3. Booking Management**

- Customers can book available cars.
- Admins can manage booking statuses.

### **4. Database Integration**

- The system uses a MySQL database to store user, car, and booking information.
- Secure database connection using credentials stored in a `properties` file.

### **5. Validation and Exception Handling**

- Input validation for email format, user type, and null fields.
- Proper error messages for invalid inputs and system exceptions.

---

## **System Architecture**

The Car Rental System is implemented using Java with the following structure:

### **Packages:**

1. **`com.carrental.controllers`**:

   - Manages the flow of the application and user interactions.
   - Classes:
     - `MenuController`: Controls the main menu and user operations.

2. **`com.carrental.services`**:

   - Contains business logic for user, car, and booking services.
   - Classes:
     - `UserService`: Manages user-related operations.
     - `CarService`: Handles car-related operations.
     - `BookingService`: Manages booking-related operations.

3. **`com.carrental.models`**:

   - Defines the data models for the system.
   - Classes:
     - `User`: Represents a user with attributes such as first name, last name, email, password, and user type.
     - `Car`: Represents a car with details like model, registration number, and status.
     - `Booking`: Represents a booking with foreign keys to user and car.

4. **`com.carrental.database`**:

   - Manages database connectivity.
   - Classes:
     - `DatabaseConnection`: Handles the connection to the MySQL database.

5. **`com.carrental.dao`**:

   - Data Access Objects (DAO) for CRUD operations on the database.
   - Classes:
     - `UserDAO`: Handles user-related database operations.
     - `CarDAO`: Manages car-related database actions.
     - `BookingDAO`: Handles booking-related database queries.

6. **`com.carrental.utils`**:

   - Utility classes for validation and security.
   - Classes:
     - `InputValidator`: Validates email addresses and other inputs.
     - `PasswordValidator`: Hashes passwords using secure algorithms.

---

## **Password Hashing**

The system uses a secure password hashing method to ensure user credentials are protected:

- The `PasswordValidator` class hashes passwords using a cryptographic hash function (Bcrypt).
- Passwords are never stored in plain text.

---

## **Installation and Setup**

### **1. Prerequisites**

- Java 17 or later
- MySQL server installed
- MySQL Workbench (optional)
- Git

### **2. Clone the Repository**

```bash
git clone <repository_url>
cd car-rental-system
```

### **3. Database Setup**

1. Create a MySQL database named `rentalSystem`.
2. Import the provided SQL dump file:
   ```bash
   mysql -u root -p rentalSystem < rentalSystem.sql
   ```

### **4. Configure Database Credentials**

1. Navigate to `src/main/resources`.

2. Create a file named `db.properties` and add your database credentials:

   ```properties
   db.url=jdbc:mysql://localhost:3306/rentalSystem
   db.username=YOUR_USERNAME
   db.password=YOUR_PASSWORD
   ```

3. Ensure the `db.properties` file is added to `.gitignore` to prevent pushing sensitive credentials to GitHub.

### **5. Build and Run**

- Compile and run the application using your preferred IDE or the command line:
  ```bash
  mvn clean install
  mvn exec:java -Dexec.mainClass="com.carrental.Main"

  ```

### **6. Using the System**

1. Follow the prompts to sign up, log in, and manage cars or bookings.
2. Admin users will have access to additional features like managing cars and bookings.

---

## **How to Contribute**

1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature-branch
   ```
3. Make your changes and commit:
   ```bash
   git commit -m "Add new feature"
   ```
4. Push to your branch:
   ```bash
   git push origin feature-branch
   ```
5. Create a pull request.

---

## **License**

This project is licensed under the MIT License.

---

## **Contact**

For any inquiries or issues, please contact Amos Prosper Kwatuha.

0759064318 

akwatuha04\@gmail.com

Thanks. Have Fun coding
>>>>>>> 84dbfef (This is the final commit with the Database Dump and the full System code)

