-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: localhost    Database: COFFEE_SHOP
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `AUTHORITIES`
--

DROP TABLE IF EXISTS `AUTHORITIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AUTHORITIES` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ROLE_ID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_AUTH_USER` (`USERNAME`),
  KEY `FK_AUTH_ROLE` (`ROLE_ID`),
  CONSTRAINT `FK_AUTH_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLES` (`ID`),
  CONSTRAINT `FK_AUTH_USER` FOREIGN KEY (`USERNAME`) REFERENCES `USERS` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUTHORITIES`
--

LOCK TABLES `AUTHORITIES` WRITE;
/*!40000 ALTER TABLE `AUTHORITIES` DISABLE KEYS */;
INSERT INTO `AUTHORITIES` VALUES (1,'vantht','STAFF'),(2,'vantht','DIRECTOR'),(3,'vantht','CUSTOMER'),(4,'ha123','CUSTOMER'),(5,'ha123','STAFF'),(6,'kim090','CUSTOMER');
/*!40000 ALTER TABLE `AUTHORITIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CATEGORIES`
--

DROP TABLE IF EXISTS `CATEGORIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CATEGORIES` (
  `CATEGORY_ID` int NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CATEGORIES`
--

LOCK TABLES `CATEGORIES` WRITE;
/*!40000 ALTER TABLE `CATEGORIES` DISABLE KEYS */;
INSERT INTO `CATEGORIES` VALUES (1,'Hot Coffees','2022-12-14 23:10:10'),(2,'Hot Tea','2022-12-14 23:10:10'),(3,'Cold Coffees','2022-12-14 23:10:10'),(4,'Iced Teas','2022-12-14 23:10:10'),(5,'Cold Drinks','2022-12-14 23:10:10'),(6,'Blended Beverages','2022-12-14 23:10:10');
/*!40000 ALTER TABLE `CATEGORIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDER_DETAILS`
--

DROP TABLE IF EXISTS `ORDER_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ORDER_DETAILS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int NOT NULL,
  `PRODUCT_ID` int NOT NULL,
  `QUANTITY` int NOT NULL,
  `PRICE` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ODERDE_PROD` (`PRODUCT_ID`),
  KEY `FK_ODERDE_ORDER` (`ORDER_ID`),
  CONSTRAINT `FK_ODERDE_ORDER` FOREIGN KEY (`ORDER_ID`) REFERENCES `ORDERS` (`ORDER_ID`),
  CONSTRAINT `FK_ODERDE_PROD` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `PRODUCTS` (`PRODUCT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDER_DETAILS`
--

LOCK TABLES `ORDER_DETAILS` WRITE;
/*!40000 ALTER TABLE `ORDER_DETAILS` DISABLE KEYS */;
INSERT INTO `ORDER_DETAILS` VALUES (1,1,24,2,5.3),(2,1,25,1,5.3),(3,1,26,3,10.3),(4,2,41,1,90),(23,12,34,1,11);
/*!40000 ALTER TABLE `ORDER_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERS`
--

DROP TABLE IF EXISTS `ORDERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ORDERS` (
  `ORDER_ID` int NOT NULL AUTO_INCREMENT,
  `DATE` datetime NOT NULL,
  `TOTAL` float DEFAULT NULL,
  `ADDRESS` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `FK_ORDER_USER_idx` (`USERNAME`),
  CONSTRAINT `FK_ORDER_USER` FOREIGN KEY (`USERNAME`) REFERENCES `USERS` (`USERNAME`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDERS`
--

LOCK TABLES `ORDERS` WRITE;
/*!40000 ALTER TABLE `ORDERS` DISABLE KEYS */;
INSERT INTO `ORDERS` VALUES (1,'2022-09-21 13:10:10',100,'123 Quartar Street','vantht'),(2,'2021-01-18 13:10:10',900,'123 Quartar Street','vantht'),(12,'2022-12-08 15:26:26',11,'123 Quatar','vantht');
/*!40000 ALTER TABLE `ORDERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCTS`
--

DROP TABLE IF EXISTS `PRODUCTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRODUCTS` (
  `PRODUCT_ID` int NOT NULL AUTO_INCREMENT,
  `PRODUCT_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `IMAGE` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CATEGORY_ID` int NOT NULL,
  `PRICE` float NOT NULL,
  `DISCOUNT` int DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`PRODUCT_ID`),
  KEY `FK_CategoryProduct` (`CATEGORY_ID`),
  CONSTRAINT `FK_CategoryProduct` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `CATEGORIES` (`CATEGORY_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCTS`
--

LOCK TABLES `PRODUCTS` WRITE;
/*!40000 ALTER TABLE `PRODUCTS` DISABLE KEYS */;
INSERT INTO `PRODUCTS` VALUES (23,'Caffè Americano','Espresso shots topped with hot water create a light layer of crema culminating in this wonderfully rich cup with depth and nuance','caffe_americano.jpg',1,10,NULL,'2022-12-14 23:10:10'),(24,'Cappuccino','Dark, rich espresso lies in wait under a smoothed and stretched layer of thick milk foam. An alchemy of barista artistry and craft','cappuccino.jpg',1,5.3,10,'2022-12-14 23:10:10'),(25,'Flat White','Smooth ristretto shots of espresso get the perfect amount of steamed whole milk to create a not-too-strong, not-too-creamy, just-right flavor','flat_white.jpg',1,12,25,'2022-12-14 23:10:10'),(26,'Matcha Tea Latte','Smooth and creamy matcha sweetened just right and served with steamed milk. This favorite will transport your senses to pure green delight','matcha_tea_latte.jpg',2,6.5,NULL,'2022-01-14 23:10:10'),(27,'Chai Tea Latte','Black tea infused with cinnamon, clove and other warming spices is combined with steamed milk and topped with foam for the perfect balance of sweet and spicy. An iconic chai cup','chai_tea_latte.jpg',2,6.9,10,'2022-01-14 23:10:10'),(28,'Earl Grey Tea','We take a strong black tea base and add the essence of bergamot, a citrus fruit with subtle lemon and floral lavender notes, to create this aromatically awesome tea flavor','earl_grey_tea.jpg',2,4,NULL,'2022-06-20 23:10:10'),(29,'Vanilla Sweet Cream Cold Brew','Our slow-steeped custom blend of Starbucks® Cold Brew coffee accented with vanilla and topped with a delicate float of house-made vanilla sweet cream that cascades throughout the cup. It\'s over-the-top and super-smooth.','cold_brew.jpg',3,12,25,'2022-06-27 19:25:10'),(30,'Vanilla Sweet Cream Nitro Cold Brew','Served cold, straight from the tap, our Nitro Cold Brew is topped with a float of house-made vanilla sweet cream. The result: a cascade of velvety coffee more sippable than ever','vanilla_sweet_cream.jpg',3,9,10,'2022-06-27 19:25:10'),(31,'Iced Coffee with Milk','Freshly brewed Iced Coffee Blend with milk served chilled and sweetened over ice. An absolutely, seriously, refreshingly lift to any day','iced_coffee_milk.jpg',3,4,NULL,'2022-06-27 19:25:10'),(32,'Iced Coffee','Iced Coffee Blend served chilled and sweetened over ice. An absolutely, seriously, refreshingly lift to any day','iced_coffee.jpg',3,15.5,30,'2022-06-27 19:25:10'),(33,'Iced Chocolate Almondmilk Shaken Espresso','Blonde espresso meets cocoa and notes of malt, shaken together and topped with almondmilk and ice for an invigorating treat to power you through your day','iced_chocolate.jpg',3,12,10,'2022-06-27 19:25:10'),(34,'Iced Starbucks Blonde Vanilla Latte','Seriously smooth and subtly sweet Starbucks® Blonde Espresso, milk, ice and vanilla syrup come together to create a delightful twist on a beloved espresso classic','blonde_vanilla_latte.jpg',3,11,NULL,'2022-06-27 19:25:10'),(35,'Iced London Fog Tea Latte','Green tea blended with mint, lemongrass and lemon verbena, and given a good shake with ice. Lightly flavored and oh-so-refreshing!','sample.png',4,6,NULL,'2022-06-27 19:25:10'),(36,'Iced Chai Tea Latte','Green tea blended with mint, lemongrass and lemon verbena, and given a good shake with ice. Lightly flavored and oh-so-refreshing!','iced_green_tea.jpg',4,6,10,'2022-06-27 19:25:10'),(37,'Iced Green Tea','Green tea blended with mint, lemongrass and lemon verbena, and given a good shake with ice. Lightly flavored and oh-so-refreshing!','iced_green_tea.jpg',4,5,NULL,'2022-06-27 19:25:10'),(38,'Pink Drink Starbucks Refreshers Beverage','Awaken your taste buds with the zing of refreshing lemonade infused with a hint of delicious strawberry flavor and blended with ice. A light, fresh beverage that puts a little zip in your step','iced_green_tea.jpg',5,7.5,NULL,'2022-06-25 19:25:10'),(39,'Blended Strawberry Lemonade','Awaken your taste buds with the zing of refreshing lemonade infused with a hint of delicious strawberry flavor and blended with ice. A light, fresh beverage that puts a little zip in your step','blended_strawberry_lemonade.jpg',5,10.5,15,'2022-06-25 19:25:10'),(40,'SBC Milk','Awaken your taste buds with the zing of refreshing lemonade infused with a hint of delicious strawberry flavor and blended with ice. A light, fresh beverage that puts a little zip in your step','sample.png',5,6,20,'2022-06-25 19:25:10'),(41,'Chocolate Cookie Crumble Crème Frappuccino','Mocha sauce and Frappuccino chips are blended with milk and ice, layered on top of whipped cream and chocolate cookie crumble and topped with vanilla whipped cream','chocolate_cookie.jpg',5,8,10,'2022-04-20 19:25:10'),(42,'Java Chip Frappuccino Blended Beverage','Mocha sauce and Frappuccino chips are blended with milk and ice, layered on top of whipped cream and chocolate cookie crumble and topped with vanilla whipped cream','java_chip.jpg',5,5,NULL,'2022-04-20 19:25:10'),(43,'Apple Crisp Oatmilk Frappuccino','Mocha sauce and Frappuccino® chips are blended with milk and ice, layered on top of whipped cream and chocolate cookie crumble and topped with vanilla whipped cream','apple_crisp.jpg',5,12.5,NULL,'2022-04-20 19:25:10');
/*!40000 ALTER TABLE `PRODUCTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLES`
--

DROP TABLE IF EXISTS `ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ROLES` (
  `ID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLES`
--

LOCK TABLES `ROLES` WRITE;
/*!40000 ALTER TABLE `ROLES` DISABLE KEYS */;
INSERT INTO `ROLES` VALUES ('CUSTOMER','CUSTOMER'),('DIRECTOR','DIRECTOR'),('STAFF','STAFF');
/*!40000 ALTER TABLE `ROLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USERS` (
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PASSWORDS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `GENDER` bit(1) DEFAULT NULL,
  `FULLNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ADDRESS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES ('ha123','tranha@gmail.com','abc123',_binary '\0','Trần Ngọc Hà',NULL,'2022-09-21 13:10:10'),('kim090','nhkim@gmail.com','abc123',_binary '','Nguyễn Kim','90/221 A Street','2022-12-14 23:10:10'),('vantht','van@gmail.com','abc123',_binary '','Trần Hoàng Thuý Vân','123 Quartar Street','2020-01-01 10:10:10');
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'COFFEE_SHOP'
--

--
-- Dumping routines for database 'COFFEE_SHOP'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-08 17:22:47
