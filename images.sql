-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: images
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL,
  `color1` varchar(100) NOT NULL,
  `color2` varchar(100) NOT NULL,
  `color3` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,'http://i.imgur.com/TKLs9lo.jpg','fbfbfb','9d9a99','4e3a3c'),(2,'https://i.redd.it/1nlgrn49x7ry.jpg','2b261e','caba9f','756b5b'),(3,'https://i.redd.it/4m5yk8gjrtzy.jpg','c0a598','250f0b','734a36'),(4,'http://i.imgur.com/lcEUZHv.jpg','fbfafc','454549','cbbeb7'),(5,'http://i.imgur.com/FApqk3D.jpg','fefffe','35f53','e96814'),(6,'https://i.redd.it/s5viyluv421z.jpg','f3e8d0','704c2b','b9864e'),(7,'https://i.redd.it/h5t3qddvvzry.jpg','6f5c4e','13100e','ae9c8c'),(8,'http://i.imgur.com/sXhEQez.jpg','fafafa','b0b0b','838383'),(9,'https://i.redd.it/w5q6gldnvcuy.jpg','1f1d2e','e0cfcf','947378'),(10,'https://i.redd.it/fsuv32a1cc9y.jpg','4c3733','f8f4f2','9f8375'),(11,'https://i.redd.it/xae65ypfqycy.jpg','efebeb','c2453c','723836'),(12,'http://i.imgur.com/puEqa4C.jpg','8a8493','3a2925','e75135'),(13,'http://i.imgur.com/tyDhTll.jpg','d6cdbe','221a13','74674f'),(14,'https://i.redd.it/cwljeujku33z.jpg','e4dfda','473f3f','c6a47a'),(15,'http://i.imgur.com/M3NOzLC.jpg','928c7d','614930','fdfdfb'),(16,'https://i.redd.it/ihczg3pmle3z.jpg','a7b09f','3d473b','818c7a'),(17,'https://i.redd.it/cpkq97klre4z.jpg','857f6e','a4a190','545344'),(18,'http://i.imgur.com/RycdbMO.jpg','8b8d8a','353e46','cfd0ca'),(19,'https://i.redd.it/n9jq5j2tatcy.jpg','3e3f3c','9f987e','d4d9d5'),(20,'http://i.imgur.com/6pnYQUv.jpg','27292b','a1a2a3','872de'),(21,'https://i.redd.it/sn7ufk9fsv4z.jpg','b8b6b2','7c5c57','272425'),(22,'http://i.imgur.com/pt9rmrv.jpg','d4a38b','1c2f44','fae8d9'),(23,'https://i.redd.it/rubsfuf64lvy.jpg','3b393c','b3ac9c','dce4ee'),(24,'https://i.redd.it/d8021b5i2moy.jpg','c7b9a2','261d20','87624c'),(25,'https://i.redd.it/nrafqoujmety.jpg','342a28','c9a8a5','906956'),(26,'https://i.redd.it/c5pk0vnpg3ty.jpg','6f81cb','f5f5f7','1d1e31'),(27,'https://i.redd.it/ru12lvaf7jyy.jpg','2d2120','baaeaf','846561'),(28,'http://i.imgur.com/qvnjbVw.jpg','a4a8ad','828176','5a513b'),(29,'https://i.imgur.com/HRgRMOw.jpg','488790','a7a89c','4b4d45'),(30,'https://i.redd.it/ax4eueu0szpy.jpg','c3b6bb','2d3637','968684'),(31,'https://i.redd.it/guul6eld2soy.jpg','c5c0af','887b69','36383f'),(32,'https://i.redd.it/0qow2dksb34z.jpg','3a4429','7d8250','b5b39c'),(33,'https://i.redd.it/7ofdqmp53bzy.jpg','a1907c','4a3a31','d5cbc0'),(34,'https://i.redd.it/fyqzavufvjwy.jpg','d4b291','4b3428','947764'),(35,'https://i.redd.it/tb5pckm6e72y.jpg','2f2b26','d4d5cd','9f9c93'),(41,'https://i.redd.it/ftd3sx5ah13z.jpg','574d49','d2b7a8','b25940'),(49,'http://i.imgur.com/Gh2I3pq.jpg','cbbaa9','42342a','af9886'),(56,'https://i.redd.it/m4cfqp8wfv5z.jpg','25282d','d4d2d2','8b7f7a'),(59,'https://i.redd.it/lsuw4p2ncyny.jpg','a17f53','3e2c20','d5d0c3'),(64,'http://i.imgur.com/enhDnTM.jpg','b9a39a','98080','7ba448');
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-21 15:11:47
