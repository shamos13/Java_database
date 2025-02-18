/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.4.4-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: rentalSystem
-- ------------------------------------------------------
-- Server version	11.4.4-MariaDB-3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `Cars`
--

DROP TABLE IF EXISTS `Cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cars` (
  `car_id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(50) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `car_year` int(11) NOT NULL,
  `registration_number` varchar(20) NOT NULL,
  `price_perDay` double NOT NULL,
  `car_status` enum('Available','Booked','Maintenance') NOT NULL,
  `engine_size` int(11) NOT NULL COMMENT 'shows the engine size',
  PRIMARY KEY (`car_id`),
  UNIQUE KEY `unique` (`registration_number`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cars`
--

LOCK TABLES `Cars` WRITE;
/*!40000 ALTER TABLE `Cars` DISABLE KEYS */;
INSERT INTO `Cars` VALUES
(1,'Toyota','Premio',2017,'KDG122X',1200,'Available',1500),
(2,'Nissan','Xtrail',2019,'KDG186S',2000,'Available',2000),
(3,'Volkwswagen','Gti',2014,'KCZ354T',2000,'Available',2000),
(5,'Toyota','Supra',2010,'KBX133R',2400,'Maintenance',2400),
(7,'Nissan','Note',2018,'KDD621R',1000,'Available',1200);
/*!40000 ALTER TABLE `Cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookings` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `total_price` double NOT NULL,
  `booking_status` enum('pending','confirmed','cancelled','approved') DEFAULT NULL,
  `car_id` int(11) NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `bookings_Cars` (`car_id`),
  KEY `bookings_users` (`user_id`),
  CONSTRAINT `bookings_Cars` FOREIGN KEY (`car_id`) REFERENCES `Cars` (`car_id`),
  CONSTRAINT `bookings_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES
(4,24,'2025-06-12','2025-06-15',3000,'confirmed',7),
(6,28,'2025-08-18','2025-09-29',75600,'confirmed',3);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` enum('Customer','Admin') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `users_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'Amos','Prosper','Kwatuha','akwatuha04@gmail.com','$2a$10$hhXHuRtyR12e4N6f2FSLXO0YZYpM3W5kdnKXcInFByS7AkXsuLSoG','Admin'),
(23,'Douglas',NULL,'Andanje','dradog254@gmail.com','$2a$10$qbuJbjciUpLmAuCXFoVVEeRK620FC11SjmRY3JgSw4mUefObzRiIS','Customer'),
(24,'gerry',NULL,'kubs','kubs99@gmail.com','$2a$10$pZgAro8.i6tFPIrAe6HGCey6KFFxGMwTQx6N00qEUj2dhDJoDFGi6','Customer'),
(25,'Marion',NULL,'Moriasi','mmoriasi04@gmail.com','$2a$10$h5DWJ7qGjz/AHl3SzIrU/enxSJqtpoU/w0/nRy3I57ecRTavZluMK','Customer'),
(26,'mercy',NULL,'njambi','mnjambi12@gmail.com','$2a$10$mMjuzkCtphEpE2TDxY7VfOnI79TaKEykFssyPRqqSk9p1FA9ASqgi','Customer'),
(27,'Dennis',NULL,'Kogo','kogo12@gmail.com','$2a$10$06vb1w6bawo5dbpZJ0ugjOZ77ffpcuNtia80r98qv1iE6VgFSWm1C','Customer'),
(28,'Shalom',NULL,'nalima','shalom12@gmail.com','$2a$10$12RqMxtiDTt4IxnifzUCeuhUdMmLfXoK32invzhlR7o1xRyM6t17u','Customer');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-02-19  0:23:55
