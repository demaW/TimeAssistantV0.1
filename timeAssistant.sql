CREATE DATABASE  IF NOT EXISTS `time_assistant` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `time_assistant`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: time_assistant
-- ------------------------------------------------------
-- Server version	5.1.73-community

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
-- Table structure for table `hours`
--

DROP TABLE IF EXISTS `hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hours` (
  `userId` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `hours` int(11) DEFAULT NULL,
  `hoursId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`hoursId`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hours`
--

LOCK TABLES `hours` WRITE;
/*!40000 ALTER TABLE `hours` DISABLE KEYS */;
INSERT INTO `hours` VALUES (10,'2014-01-08',3,1),(10,'2014-01-09',6,2),(10,'2014-01-10',6,3),(10,'2014-01-11',5,4),(10,'2014-01-12',7,5),(10,'2014-01-13',5,6),(10,'2014-01-14',5,7),(10,'2014-01-15',2,8),(10,'2014-01-16',5,9),(10,'2014-01-17',3,10),(10,'2014-01-18',4,11),(10,'2014-01-19',8,12),(10,'2014-01-20',7,13),(10,'2014-01-21',6,14),(10,'2014-01-22',2,15),(10,'2014-01-23',6,16),(10,'2014-01-24',8,17),(10,'2014-01-25',7,18),(10,'2014-01-26',6,19),(10,'2014-01-27',3,20),(10,'2014-01-28',7,21),(10,'2014-01-29',3,22),(10,'2014-01-30',2,23),(10,'2014-01-31',3,24),(10,'2014-02-01',6,25),(10,'2014-02-02',8,26),(10,'2014-02-03',6,27),(10,'2014-02-04',4,28),(10,'2014-02-05',8,29),(10,'2014-02-06',5,30),(10,'2014-02-07',5,31),(10,'2014-02-08',5,32),(10,'2014-02-09',2,33),(10,'2014-02-10',3,34),(10,'2014-02-11',4,35),(10,'2014-02-12',3,36),(10,'2014-02-13',4,37),(10,'2014-02-14',7,38),(10,'2014-02-15',7,39),(10,'2014-02-16',6,40),(10,'2014-02-17',4,41),(10,'2014-02-18',4,42),(10,'2014-02-19',4,43),(10,'2014-02-20',2,44),(10,'2014-02-21',2,45),(10,'2014-02-22',6,46),(10,'2014-02-23',8,47),(10,'2014-02-24',4,48),(10,'2014-02-25',3,49),(10,'2014-02-26',5,50),(10,'2014-02-27',5,51),(10,'2014-02-28',7,52),(10,'2014-03-01',4,53),(10,'2014-03-02',2,54),(10,'2014-03-03',2,55),(10,'2014-03-04',6,56),(10,'2014-03-05',8,57),(10,'2014-03-06',2,58),(10,'2014-03-07',2,59),(10,'2014-03-08',2,60),(10,'2014-03-09',5,61),(10,'2014-03-10',7,62),(10,'2014-03-11',4,63),(10,'2014-03-12',5,64),(10,'2014-03-13',3,65),(10,'2014-03-14',5,66),(10,'2014-03-15',8,67),(10,'2014-03-16',2,68),(10,'2014-03-17',5,69),(10,'2014-03-18',5,70),(10,'2014-03-19',6,71),(10,'2014-03-20',5,72),(10,'2014-03-21',5,73),(10,'2014-03-22',6,74),(10,'2014-03-23',8,75),(10,'2014-03-24',3,76),(10,'2014-03-25',8,77),(10,'2014-03-26',2,78),(10,'2014-03-27',8,79),(10,'2014-03-28',4,80),(10,'2014-03-29',7,81),(10,'2014-03-30',2,82),(10,'2014-03-31',6,83),(10,'2014-04-01',4,84),(10,'2014-04-02',6,85),(4,'2014-04-04',70,86),(2,'2014-04-04',31,87),(8,'2014-04-04',3,88),(8,'2014-04-04',6,89),(10,'2014-04-05',7,90),(10,'2014-04-05',5,91),(10,'2014-04-05',6,92),(10,'2014-04-06',3,93);
/*!40000 ALTER TABLE `hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) DEFAULT NULL,
  `notes` varchar(60) DEFAULT NULL,
  `project_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'Some project','The Greatest Project','The Greatest Project'),(2,'New project','Awesome project','Awesome project'),(3,'Perfect project','perfect','Perfect project'),(4,'The best','New project','New project'),(6,'asdasda','dasdadad','asdadsas');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'user'),(2,'manager'),(3,'admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `estimate_time` int(11) DEFAULT NULL,
  `real_time` int(11) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `description` varchar(60) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `finished` date DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FK_ow5ffabbvop1vyuv2m4gr65ul` (`employee_id`),
  KEY `FK_dd8icgfs47uvsbcb0yshew6iu` (`project_id`),
  CONSTRAINT `FK_dd8icgfs47uvsbcb0yshew6iu` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK_ow5ffabbvop1vyuv2m4gr65ul` FOREIGN KEY (`employee_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,77,0,'NEW','some task description','Some Task',1,1,'2014-01-02','2014-01-24',NULL),(2,4,0,'NEW','vell formed task description','Task',2,1,'2014-01-02','2014-01-16',NULL),(3,77,0,'NEW','powerfull programming language','Another Task',2,2,'2014-01-09','2014-01-16',NULL),(4,55,0,'NEW','very usefull if you want to grow as professional developer','Finish task',1,2,'2014-01-15','2014-01-24',NULL),(5,40,0,'NEW','I wish!','finish time assistant project',6,4,'2014-01-01','2014-01-24',NULL),(7,44,0,'NEW','To write new inspiration verses','Kobzar version 2',1,1,'2014-01-10','2014-01-24',NULL),(8,77,0,'NEW','I guess it will very actual today','To write continue of Zapovit',1,3,'2014-04-09','2014-04-24',NULL),(9,45,31,'IN Progress','It can be done only by Columb ;)','Close america',10,3,'2014-04-09','2014-04-24','2014-04-04'),(10,33,0,'NEW','make communication with canibals','Traveling to new Zeland',3,3,'2014-04-09','2014-04-24',NULL),(11,777,70,'FINISHED','make long ocean jorney','Yorney',4,3,'2014-04-09','2014-04-24','2014-04-04'),(12,777,0,'NEW','Ukraine should be fridom','Start rebellion ',1,2,'2014-01-05','2014-04-30',NULL),(13,778,7,'IN Progress','I know he able to do it.','Fly to moone',10,4,'2014-04-16','2014-05-02','2014-04-05'),(14,7,3,'FINISHED','science research ','make smt',10,4,'2014-04-10','2014-04-18','2014-04-06'),(15,789,5,'IN Progress','to walk around the world','world wide jorney',10,4,'2014-04-14','2014-11-27','2014-04-05'),(16,785,0,'NEW','change colorful neck less  to a gold \r\nA lot of gold!!!','Selling',2,1,'2014-04-22','2015-02-25',NULL),(17,78,6,'IN Progress','new task','task',10,1,'2014-04-23','2014-04-30','2014-04-05'),(18,45,0,'NEW','ndfjjkj','taska',12,3,'2014-04-02','2014-04-30',NULL),(19,74,6,'FINISHED','fghjj','desc',8,1,'2014-04-21','2014-04-28','2014-04-04'),(20,23,0,'NEW','sdasdasd','sasda',1,6,'2014-04-08','2014-04-01',NULL);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_710sbdrijuexyu9n0pcjillxf` (`employee_id`),
  KEY `FK_bsgqgukhojgbhthllr7mvkect` (`project_id`),
  CONSTRAINT `FK_710sbdrijuexyu9n0pcjillxf` FOREIGN KEY (`employee_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_bsgqgukhojgbhthllr7mvkect` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,2),(5,5,2),(6,6,3),(7,7,3),(8,8,4),(9,9,4),(10,9,4),(11,8,4),(12,4,4),(13,2,1),(14,10,1),(15,12,3),(16,8,1),(17,1,6);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `password` varchar(80) NOT NULL,
  `position` varchar(30) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `salary_rate` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4w3luja5yf4agc4pip3gkddv3` (`role_id`),
  CONSTRAINT `FK_4w3luja5yf4agc4pip3gkddv3` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'qwe@qwe.ru','Taras','Shevchenko','2b5443f0c75a06559da953d028fe560f','Jun',1,45),(2,'columb@gmail.com','Christoforo','Columb','2b5443f0c75a06559da953d028fe560f','captain',1,85),(3,'james.cook@gmail.com','james','cook','e3e90fd6d2a7c4661a1a3acf2f60bc6d','captain',2,45),(4,'ferdinand.magelan@gmail.com','ferdinando','magelan','0783c59b448db8def8448af321a29c99','traveller',1,96),(5,'amundsen.scott@gmail.com','amudsen','scott','21f63c6e971cd913a9c147e8652ca659','traveller',2,25),(6,'admin@gmail.com','admin','admin','21232f297a57a5a743894a0e4a801fc3','develop',3,48),(7,'marco.polo@gmail.com','marco','polo','b53759f3ce692de7aff1b5779d3964da','traveller',1,24),(8,'darvin@gmail.com','Charlz','Darvin','a1eca4fe979809220c2e9dcff718558f','evolutioner',1,87),(9,'gagarin@gmail.com','Yuriy','Gagarin','36d673902aa213bffab614926154a141','cosmonaut',1,48),(10,'user@gmail.com','user','user','ee11cbb19052e40b07aac0ca060c23ee','user',1,48),(11,'test@gmail.com','тфтфьь','раррлод','b56a18e0eacdf51aa2a5306b0f533204','щлло',1,58),(12,'nazar.lelyak@gmail.com','nazar','lol','f21b2728da8bc2672255c758aaf2cdd3','test',1,5),(13,'adminus@gmail.com','admin','admin','21232f297a57a5a743894a0e4a801fc3','admin',3,25),(16,'manager@gmail.com','manager','manager','1d0258c2440a8d19e716292b231e3190','manager',2,10),(18,'admin@gmail.com','asdasd','sdasd','21232f297a57a5a743894a0e4a801fc3','asda',1,22),(19,'qqq@qqq.qqqasdas','asda','asd','7815696ecbf1c96e6894b779456d330e','sad',1,2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-06 22:42:38
