CREATE DATABASE  IF NOT EXISTS `stock_financial` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `stock_financial`;
-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: stock_financial
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `stock_daily`
--

DROP TABLE IF EXISTS `stock_daily`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_daily` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_stock` int(11) NOT NULL,
  `datetime` datetime NOT NULL,
  `price_open` double NOT NULL,
  `price_close` double NOT NULL,
  `price_change` double NOT NULL,
  `volume` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1hqy31gx3xmqtk59pideyl52e` (`id_stock`),
  CONSTRAINT `FK1hqy31gx3xmqtk59pideyl52e` FOREIGN KEY (`id_stock`) REFERENCES `stock` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_daily`
--

LOCK TABLES `stock_daily` WRITE;
/*!40000 ALTER TABLE `stock_daily` DISABLE KEYS */;
INSERT INTO `stock_daily` VALUES (18,3,'2019-10-16 10:46:12',160,180,154,1700),(19,3,'2019-10-16 10:47:44',160,180,155,1700),(20,3,'2019-10-16 10:55:33',160,180,100,1700),(21,3,'2019-10-16 18:30:51',160,180,80,1700);
/*!40000 ALTER TABLE `stock_daily` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-17  0:05:58
