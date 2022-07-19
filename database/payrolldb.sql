-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: payrolldb
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allowance_details`
--

DROP TABLE IF EXISTS `allowance_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allowance_details` (
  `ad_id` int NOT NULL AUTO_INCREMENT,
  `ad_employee_id` int NOT NULL,
  `allowance_amount` varchar(45) NOT NULL,
  `longevity` varchar(45) DEFAULT '0',
  `description` varchar(45) DEFAULT NULL,
  `skills` varchar(45) DEFAULT '0',
  `hra` varchar(45) DEFAULT '0',
  `ta` varchar(45) DEFAULT '0',
  `ad_attendance_id` int NOT NULL,
  PRIMARY KEY (`ad_id`),
  UNIQUE KEY `ad_employee_id_UNIQUE` (`ad_employee_id`),
  KEY `fk_employee_id_idx` (`ad_employee_id`),
  KEY `fkkf_attd_id_idx` (`ad_attendance_id`),
  CONSTRAINT `fk_employee_id` FOREIGN KEY (`ad_employee_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkkf_attd_id` FOREIGN KEY (`ad_attendance_id`) REFERENCES `attendance` (`attendance_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allowance_details`
--

LOCK TABLES `allowance_details` WRITE;
/*!40000 ALTER TABLE `allowance_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `allowance_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `attendance_id` int NOT NULL AUTO_INCREMENT,
  `present` varchar(45) DEFAULT '0',
  `absent` varchar(45) DEFAULT '0',
  `month` varchar(45) DEFAULT '0',
  `leave_day` varchar(45) DEFAULT '10',
  `leaves` varchar(45) DEFAULT '0',
  `hour_late` varchar(45) DEFAULT '0',
  `hour_overtime` varchar(45) DEFAULT '0',
  `attd_emp_id` int NOT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `attd_fk_emp_id_idx` (`attd_emp_id`),
  CONSTRAINT `attd_fk_emp_id` FOREIGN KEY (`attd_emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deduction_details`
--

DROP TABLE IF EXISTS `deduction_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deduction_details` (
  `deduction_id` int NOT NULL AUTO_INCREMENT,
  `dd_emp_id` int NOT NULL,
  `dd_attd_id` int NOT NULL,
  `tax` varchar(45) DEFAULT NULL,
  `SSC` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `deduction_amount` varchar(45) NOT NULL,
  PRIMARY KEY (`deduction_id`),
  KEY `deduk_fk_emp_id_idx` (`dd_emp_id`),
  KEY `deduk_fk_attd_id_idx` (`dd_attd_id`),
  CONSTRAINT `deduk_fk_attd_id` FOREIGN KEY (`dd_attd_id`) REFERENCES `attendance` (`attendance_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `deduk_fk_emp_id` FOREIGN KEY (`dd_emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deduction_details`
--

LOCK TABLES `deduction_details` WRITE;
/*!40000 ALTER TABLE `deduction_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `deduction_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `dept_id` int NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(45) NOT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'HR'),(2,'security'),(3,'Network'),(4,'Programming'),(5,'Administration');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(45) NOT NULL,
  `emp_gender` varchar(45) NOT NULL,
  `emp_dob` date DEFAULT NULL,
  `emp_phone` varchar(45) NOT NULL,
  `emp_email` varchar(45) NOT NULL,
  `emp_address` varchar(45) NOT NULL,
  `hired_date` date DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `active` tinyint NOT NULL,
  `emp_position_id` int NOT NULL,
  `emp_department_id` int NOT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `emp_jobposition_id_idx` (`emp_position_id`),
  KEY `emp_department_id_idx` (`emp_department_id`),
  CONSTRAINT `emp_fk_department_id` FOREIGN KEY (`emp_department_id`) REFERENCES `department` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `emp_jobposition_id` FOREIGN KEY (`emp_position_id`) REFERENCES `job_position` (`position_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Kyaw Thet Aung','Male','1995-07-30','09785085329','billionairekta@gmail.com','Yangon','2022-04-01','USER','kta','kta',1,2,4),(2,'Mike Miller','Male','1984-10-03','010236549','itsmike@cocomail.com','Tokyo','2009-05-16','USER',NULL,NULL,1,1,3),(3,'Satou Keiko','Female','1986-08-06','010326475','satou999@Jmail.com','Tokyo','2002-02-04','USER',NULL,NULL,1,1,5),(4,'Yamada Ichiro','Male','1969-10-01','0106487885','yamada1ro@gmail.com','Tokyo','1989-07-06','USER',NULL,NULL,1,3,5),(5,'Matsumoto Tadashi','Male','1966-09-10','01036487220','mtmttds@yolomail.com','Tokyo','1982-07-05','USER',NULL,NULL,1,3,2),(6,'Gupta','Male','1979-04-07','010788455122','indianoone@slumail.com','Tokyo','2011-06-17','USER',NULL,NULL,1,1,4);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_position`
--

DROP TABLE IF EXISTS `job_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_position` (
  `position_id` int NOT NULL AUTO_INCREMENT,
  `position_title` varchar(45) NOT NULL,
  `basic_salary` double NOT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_position`
--

LOCK TABLES `job_position` WRITE;
/*!40000 ALTER TABLE `job_position` DISABLE KEYS */;
INSERT INTO `job_position` VALUES (1,'Senior Developer',2000000),(2,'programmer',500000),(3,'Manager',8000000),(4,'HR clerk',300000);
/*!40000 ALTER TABLE `job_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payroll` (
  `payroll_id` int NOT NULL AUTO_INCREMENT,
  `emp_id` int NOT NULL,
  `attendance_id` int NOT NULL,
  `allowance_id` int NOT NULL,
  `deduction_id` int NOT NULL,
  `date` date NOT NULL,
  `gross_salary` varchar(45) NOT NULL,
  `net_salary` varchar(45) NOT NULL,
  PRIMARY KEY (`payroll_id`),
  KEY `payroll_emp_id_idx` (`emp_id`),
  KEY `payroll_attendance_id_idx` (`attendance_id`),
  KEY `payroll_allowance_id_idx` (`allowance_id`),
  KEY `payroll_deduction_id_idx` (`deduction_id`),
  CONSTRAINT `payroll_allowance_id` FOREIGN KEY (`allowance_id`) REFERENCES `allowance_details` (`ad_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payroll_attendance_id` FOREIGN KEY (`attendance_id`) REFERENCES `attendance` (`attendance_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payroll_deduction_id` FOREIGN KEY (`deduction_id`) REFERENCES `deduction_details` (`deduction_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payroll_emp_id` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-19 22:49:31
