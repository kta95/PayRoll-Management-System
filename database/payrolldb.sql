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
  KEY `fk_employee_id_idx` (`ad_employee_id`),
  KEY `fkkf_attd_id_idx` (`ad_attendance_id`),
  CONSTRAINT `fk_employee_id` FOREIGN KEY (`ad_employee_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkkf_attd_id` FOREIGN KEY (`ad_attendance_id`) REFERENCES `attendance` (`attendance_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allowance_details`
--

LOCK TABLES `allowance_details` WRITE;
/*!40000 ALTER TABLE `allowance_details` DISABLE KEYS */;
INSERT INTO `allowance_details` VALUES (7,1,'65500.0','0','otsukaresamadesu','10000','20000','10000',1);
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
  `leaves` varchar(45) DEFAULT '0',
  `hour_late` varchar(45) DEFAULT '0',
  `hour_overtime` varchar(45) DEFAULT '0',
  `attd_emp_id` int NOT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `attd_fk_emp_id_idx` (`attd_emp_id`),
  CONSTRAINT `attd_fk_emp_id` FOREIGN KEY (`attd_emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,'20','1','January, 2022','2','3','4',1),(3,'20','1','January, 2022','2','1','2',2),(4,'20','0','January, 2022','1','2','3',3),(6,'20','0','February, 2022','0','0','0',3);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deduction_details`
--

LOCK TABLES `deduction_details` WRITE;
/*!40000 ALTER TABLE `deduction_details` DISABLE KEYS */;
INSERT INTO `deduction_details` VALUES (6,1,1,'50000.0','25000.0','','109375.0');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'HR'),(2,'security'),(3,'Network'),(4,'Programming'),(5,'Administration'),(6,'Marketing');
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
  `employee_code` char(45) DEFAULT NULL,
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
  `leave_days` varchar(45) DEFAULT '0',
  PRIMARY KEY (`emp_id`),
  KEY `emp_jobposition_id_idx` (`emp_position_id`),
  KEY `emp_department_id_idx` (`emp_department_id`),
  CONSTRAINT `emp_fk_department_id` FOREIGN KEY (`emp_department_id`) REFERENCES `department` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `emp_jobposition_id` FOREIGN KEY (`emp_position_id`) REFERENCES `job_position` (`position_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,NULL,'Kyaw Thet Aung','Male','1995-07-31','09785085329','billionairekta@gmail.com','Yangon','2022-04-01','ADMIN','kta','kta',1,2,1,'4'),(2,NULL,'Mike Miller','Male','1983-07-05','1012351409','mike@yourmomhouse.com','Tokyo','2015-07-25','ADMIN','mike','mike',1,1,1,'8'),(3,NULL,'Sato Keiko','Female','1991-02-01','01023482470','keikonya@jpmail.com','Osaka','2017-09-16','USER',NULL,NULL,1,5,3,'7'),(4,NULL,'Matsumoto Tadashi','Male','1977-05-28','01024987520','mtmt@jpmail.com','Tokyo','1994-08-06','ADMIN','admin','admin',1,3,1,'8'),(5,NULL,'Oda Nobunaga','Male','1980-04-04','01210214506','oda@shogun.com','Nara','2016-07-02','USER',NULL,NULL,1,1,4,'9');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_position`
--

LOCK TABLES `job_position` WRITE;
/*!40000 ALTER TABLE `job_position` DISABLE KEYS */;
INSERT INTO `job_position` VALUES (1,'Senior Developer',600000),(2,'programmer',500000),(3,'Manager',1000000),(4,'HR clerk',400000),(5,'Junior Developer',300000),(6,'Project Leader',800000);
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
  UNIQUE KEY `attendance_id_UNIQUE` (`attendance_id`),
  KEY `payroll_emp_id_idx` (`emp_id`),
  KEY `payroll_attendance_id_idx` (`attendance_id`),
  KEY `payroll_allowance_id_idx` (`allowance_id`),
  KEY `payroll_deduction_id_idx` (`deduction_id`),
  CONSTRAINT `payroll_allowance_id` FOREIGN KEY (`allowance_id`) REFERENCES `allowance_details` (`ad_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payroll_attendance_id` FOREIGN KEY (`attendance_id`) REFERENCES `attendance` (`attendance_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payroll_deduction_id` FOREIGN KEY (`deduction_id`) REFERENCES `deduction_details` (`deduction_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payroll_emp_id` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
INSERT INTO `payroll` VALUES (5,1,1,7,6,'2022-07-24','565500.0','456125.0');
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

-- Dump completed on 2022-07-25  9:56:45
