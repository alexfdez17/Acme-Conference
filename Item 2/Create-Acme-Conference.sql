start transaction;

drop database if exists `Acme-Conference`;

create database `Acme-Conference`;
use `Acme-Conference`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%'identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete	on `Acme-Conference`.* 
	to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter,create temporary tables, lock tables, create view, create routine,alter routine, execute, trigger, show view on `Acme-Conference`.* 
	to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: acme-conference
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `start_moment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_attachments`
--

DROP TABLE IF EXISTS `activity_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_attachments` (
  `activity` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_attachments`
--

LOCK TABLES `activity_attachments` WRITE;
/*!40000 ALTER TABLE `activity_attachments` DISABLE KEYS */;
INSERT INTO `activity_attachments` VALUES (2409,'https://attachment.com'),(2412,'https://attachment.com'),(2413,'https://attachment.com'),(2414,'https://attachment.com'),(2422,'https://attachment.com'),(2424,'https://attachment.com'),(2426,'https://attachment.com'),(2427,'https://attachment.com'),(2429,'https://attachment.com'),(2432,'https://attachment.com'),(2433,'https://attachment.com'),(2434,'https://attachment.com'),(2442,'https://attachment.com'),(2445,'https://attachment.com'),(2446,'https://attachment.com'),(2447,'https://attachment.com'),(2455,'https://attachment.com'),(2456,'https://attachment.com');
/*!40000 ALTER TABLE `activity_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_speakers`
--

DROP TABLE IF EXISTS `activity_speakers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_speakers` (
  `activity` int(11) NOT NULL,
  `speakers` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_speakers`
--

LOCK TABLES `activity_speakers` WRITE;
/*!40000 ALTER TABLE `activity_speakers` DISABLE KEYS */;
INSERT INTO `activity_speakers` VALUES (2409,'Speaker1'),(2409,'Speaker2'),(2412,'Speaker3'),(2413,'Speaker4'),(2413,'Speaker5'),(2414,'Speaker6'),(2422,'Speaker1'),(2422,'Speaker2'),(2424,'Speaker3'),(2426,'Speaker3'),(2427,'Speaker4'),(2427,'Speaker5'),(2429,'Speaker1'),(2429,'Speaker2'),(2432,'Speaker3'),(2433,'Speaker4'),(2433,'Speaker5'),(2434,'Speaker6'),(2442,'Speaker1'),(2442,'Speaker2'),(2445,'Speaker3'),(2446,'Speaker4'),(2446,'Speaker5'),(2447,'Speaker6'),(2455,'Speaker3'),(2456,'Speaker4'),(2456,'Speaker5');
/*!40000 ALTER TABLE `activity_speakers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i7xei45auwq1f6vu25985riuh` (`user_account`),
  CONSTRAINT `FK_i7xei45auwq1f6vu25985riuh` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (2372,0,'address','email@email.com','Admin','Admin','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Admin',2334);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  `score` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rjptsoy3q9dcfysbnmy1g0g31` (`user_account`),
  CONSTRAINT `FK_rjptsoy3q9dcfysbnmy1g0g31` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (2374,0,'address','email@email.com','Author1','Author1','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Author1',2335,NULL),(2382,0,'address','email@email.com','Author2','Author2','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Author2',2336,NULL),(2389,0,'address','email@email.com','Author3','Author3','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Author3',2337,NULL);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_d285hl23ejq8efmum8hbvqrt2` (`parent`),
  CONSTRAINT `FK_d285hl23ejq8efmum8hbvqrt2` FOREIGN KEY (`parent`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (2348,0,'CONFERENCE',NULL),(2349,0,'Computer Science',2348),(2350,0,'Artificial Intelligence',2349),(2351,0,'Deep Learning',2350),(2352,0,'Instance-based learning',2350),(2353,0,'Regression',2350),(2354,0,'Software Engineering',2349),(2355,0,'Big Data',2354),(2356,0,'Big Processing',2354),(2357,0,'Methods',2354),(2358,0,'Tools',2354),(2359,0,'Physics',2348),(2360,0,'Cinematic',2359),(2361,0,'Electricity',2359),(2362,0,'Electronics',2359),(2363,0,'Biology',2348),(2364,0,'Animals',2363),(2365,0,'Plants',2363),(2366,0,'Fungi',2363);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `commentable` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (2415,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2409),(2416,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2412),(2417,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2414),(2418,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2408),(2419,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2408),(2420,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2408),(2435,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2429),(2436,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2432),(2437,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2434),(2438,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2428),(2439,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2428),(2440,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2428),(2448,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2442),(2449,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2445),(2450,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2447),(2451,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2441),(2452,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2441),(2453,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2441);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentable`
--

DROP TABLE IF EXISTS `commentable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commentable` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentable`
--

LOCK TABLES `commentable` WRITE;
/*!40000 ALTER TABLE `commentable` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference`
--

DROP TABLE IF EXISTS `conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conference` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `acronym` varchar(255) DEFAULT NULL,
  `camera_ready_deadline` datetime DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `fee` double NOT NULL,
  `is_final` bit(1) NOT NULL,
  `notification_deadline` datetime DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `submission_deadline` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `venue` varchar(255) DEFAULT NULL,
  `category` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_oxcc4cd5ieylqc84i08oy9l3e` (`fee`,`start_date`,`end_date`,`title`,`venue`,`summary`,`submission_deadline`,`notification_deadline`,`camera_ready_deadline`,`is_final`),
  KEY `FK_ldxxtwdsnf262yu1ctl1awe02` (`category`),
  CONSTRAINT `FK_ldxxtwdsnf262yu1ctl1awe02` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference`
--

LOCK TABLES `conference` WRITE;
/*!40000 ALTER TABLE `conference` DISABLE KEYS */;
INSERT INTO `conference` VALUES (2408,0,'Acronym1','2019-07-26 12:00:00','2019-07-31',100,'','2019-07-24 12:00:00','2019-07-26','2019-07-17 12:00:00','Summary1','Title1','Venue1',2349),(2421,0,'Acronym2','2019-11-30 12:00:00','2019-12-31',100,'','2019-11-20 12:00:00','2019-12-01','2019-11-10 12:00:00','Summary2','Title2','Venue2',2349),(2425,0,'Acronym3','2019-10-30 12:00:00','2019-11-30',100,'\0','2019-10-20 12:00:00','2019-11-01','2019-10-10 12:00:00','Summary3','Title3','Venue3',2354),(2428,0,'Acronym4','2019-11-20 12:00:00','2019-12-31',100,'','2019-11-10 12:00:00','2019-12-01','2019-07-01 12:00:00','Summary4','Title4','Venue4',2349),(2441,0,'Acronym5','2019-11-20 12:00:00','2019-12-31',100,'','2019-11-10 12:00:00','2019-12-01','2019-07-01 12:00:00','Summary5','Title5','Venue5',2349),(2454,0,'Acronym6','2019-10-30 12:00:00','2019-11-30',100,'\0','2019-10-20 12:00:00','2019-11-01','2019-10-10 12:00:00','Summary6','Title6','Venue6',2354);
/*!40000 ALTER TABLE `conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_activities`
--

DROP TABLE IF EXISTS `conference_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conference_activities` (
  `conference` int(11) NOT NULL,
  `activities` int(11) NOT NULL,
  KEY `FK_60tvmyyemxoce7qvrh79gn6s7` (`conference`),
  CONSTRAINT `FK_60tvmyyemxoce7qvrh79gn6s7` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_activities`
--

LOCK TABLES `conference_activities` WRITE;
/*!40000 ALTER TABLE `conference_activities` DISABLE KEYS */;
INSERT INTO `conference_activities` VALUES (2408,2409),(2408,2412),(2408,2413),(2408,2414),(2421,2422),(2421,2424),(2425,2426),(2425,2427),(2428,2429),(2428,2432),(2428,2433),(2428,2434),(2441,2442),(2441,2445),(2441,2446),(2441,2447),(2454,2455),(2454,2456);
/*!40000 ALTER TABLE `conference_activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expiration_month` int(11) NOT NULL,
  `expiration_year` int(11) NOT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (2459,0,'VISA',123,10,22,'Holder','4043492660454935'),(2460,0,'AMEX',409,1,21,'Holder','373809536446159'),(2461,0,'MASTER',774,9,22,'Holder','5301584370515122'),(2462,0,'DINERS',975,1,21,'Holder','30718500054540');
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `maximum_fee` double DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `author` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_as99orwbtvkmldwm2tk3mpwos` (`author`),
  CONSTRAINT `FK_as99orwbtvkmldwm2tk3mpwos` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
INSERT INTO `finder` VALUES (2375,0,NULL,'2020-01-01',NULL,NULL,'2019-01-01',2374);
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_conferences`
--

DROP TABLE IF EXISTS `finder_conferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_conferences` (
  `finder` int(11) NOT NULL,
  `conferences` int(11) NOT NULL,
  KEY `FK_nfuvovuvbk05r9vbyg5mbstxx` (`conferences`),
  KEY `FK_k666b9rxu2vqkol7uc9ostni9` (`finder`),
  CONSTRAINT `FK_k666b9rxu2vqkol7uc9ostni9` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_nfuvovuvbk05r9vbyg5mbstxx` FOREIGN KEY (`conferences`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_conferences`
--

LOCK TABLES `finder_conferences` WRITE;
/*!40000 ALTER TABLE `finder_conferences` DISABLE KEYS */;
INSERT INTO `finder_conferences` VALUES (2375,2408),(2375,2421);
/*!40000 ALTER TABLE `finder_conferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mezzage`
--

DROP TABLE IF EXISTS `mezzage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mezzage` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `is_broadcast` bit(1) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `owner` int(11) NOT NULL,
  `sender` int(11) NOT NULL,
  `topic` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_j3l6eu9nw9c7j654kkuvsw6uh` (`topic`),
  CONSTRAINT `FK_j3l6eu9nw9c7j654kkuvsw6uh` FOREIGN KEY (`topic`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mezzage`
--

LOCK TABLES `mezzage` WRITE;
/*!40000 ALTER TABLE `mezzage` DISABLE KEYS */;
INSERT INTO `mezzage` VALUES (2373,0,'Body','','2019-01-01 12:00:00','Subject',2372,2372,2371),(2376,0,'Body','\0','2019-01-01 12:00:00','Subject',2374,2374,2367),(2377,0,'Body','\0','2019-01-01 12:00:00','Subject',2374,2374,2368),(2378,0,'Body','\0','2019-01-01 12:00:00','Subject',2374,2374,2368),(2379,0,'Body','\0','2019-01-01 12:00:00','Subject',2374,2374,2369),(2380,0,'Body','\0','2019-01-01 12:00:00','Subject',2374,2374,2370),(2381,0,'Body','\0','2019-01-01 12:00:00','Subject',2374,2374,2371),(2383,0,'Body','\0','2019-01-01 12:00:00','Subject',2382,2382,2367),(2384,0,'Body','\0','2019-01-01 12:00:00','Subject',2382,2382,2369),(2385,0,'Body','\0','2019-01-01 12:00:00','Subject',2382,2382,2368),(2386,0,'Body','\0','2019-01-01 12:00:00','Subject',2382,2382,2371),(2387,0,'Body','\0','2019-01-01 12:00:00','Subject',2382,2382,2367),(2388,0,'Body','\0','2019-01-01 12:00:00','Subject',2382,2382,2370),(2390,0,'Body','\0','2019-01-01 12:00:00','Subject',2389,2389,2370),(2392,0,'Body','\0','2019-01-01 12:00:00','Subject',2391,2391,2369),(2393,0,'Body','\0','2019-01-01 12:00:00','Subject',2391,2391,2368),(2395,0,'Body','\0','2019-01-01 12:00:00','Subject',2394,2394,2367),(2397,0,'Body','\0','2019-01-01 12:00:00','Subject',2396,2396,2371),(2402,0,'Body','\0','2019-01-01 12:00:00','Subject',2401,2401,2371),(2403,0,'Body','\0','2019-01-01 12:00:00','Subject',2401,2401,2367),(2405,0,'Body','\0','2019-01-01 12:00:00','Subject',2404,2404,2367),(2407,0,'Body','\0','2019-01-01 12:00:00','Subject',2406,2406,2367);
/*!40000 ALTER TABLE `mezzage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mezzage_recipients`
--

DROP TABLE IF EXISTS `mezzage_recipients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mezzage_recipients` (
  `mezzage` int(11) NOT NULL,
  `recipients` int(11) NOT NULL,
  KEY `FK_sv7hkwnjnjocro2xpr9bk5qaf` (`mezzage`),
  CONSTRAINT `FK_sv7hkwnjnjocro2xpr9bk5qaf` FOREIGN KEY (`mezzage`) REFERENCES `mezzage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mezzage_recipients`
--

LOCK TABLES `mezzage_recipients` WRITE;
/*!40000 ALTER TABLE `mezzage_recipients` DISABLE KEYS */;
INSERT INTO `mezzage_recipients` VALUES (2373,2374),(2373,2391),(2373,2401),(2373,2382),(2373,2394),(2373,2404),(2373,2389),(2373,2396),(2373,2406),(2376,2382),(2376,2389),(2377,2391),(2378,2394),(2378,2396),(2379,2401),(2380,2404),(2380,2406),(2381,2382),(2381,2391),(2381,2406),(2381,2372),(2383,2374),(2383,2389),(2384,2391),(2384,2401),(2385,2374),(2386,2389),(2387,2372),(2388,2391),(2390,2372),(2392,2374),(2393,2389),(2395,2401),(2397,2401),(2402,2389),(2402,2374),(2403,2389),(2403,2374),(2405,2406),(2405,2396),(2407,2391),(2407,2394);
/*!40000 ALTER TABLE `mezzage_recipients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `panel`
--

DROP TABLE IF EXISTS `panel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `panel` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `start_moment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `panel`
--

LOCK TABLES `panel` WRITE;
/*!40000 ALTER TABLE `panel` DISABLE KEYS */;
INSERT INTO `panel` VALUES (2412,0,30,'Room2','2019-12-10 16:00:00','Summary','TitlePanel1'),(2413,0,60,'Room3','2019-12-10 20:00:00','Summary','TitlePanel2'),(2424,0,30,'Room2','2019-12-10 16:00:00','Summary','TitlePanel1'),(2426,0,30,'Room2','2019-11-10 16:00:00','Summary','TitlePanel1'),(2427,0,60,'Room3','2019-11-10 20:00:00','Summary','TitlePanel2'),(2432,0,30,'Room2','2019-12-10 16:00:00','Summary','TitlePanel1'),(2433,0,60,'Room3','2019-12-10 20:00:00','Summary','TitlePanel2'),(2445,0,30,'Room2','2019-12-10 16:00:00','Summary','TitlePanel1'),(2446,0,60,'Room3','2019-12-10 20:00:00','Summary','TitlePanel2'),(2455,0,30,'Room2','2019-11-10 16:00:00','Summary','TitlePanel1'),(2456,0,60,'Room3','2019-11-10 20:00:00','Summary','TitlePanel2');
/*!40000 ALTER TABLE `panel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `authors` varchar(255) DEFAULT NULL,
  `document` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
INSERT INTO `paper` VALUES (2457,0,'Author1, Author2','http://document.com','Summary','Paper1'),(2458,0,'Author1, Author2','http://document.com','Summary','CameraReadyPaper1');
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presentation`
--

DROP TABLE IF EXISTS `presentation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presentation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `start_moment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `authors` varchar(255) DEFAULT NULL,
  `document` varchar(255) DEFAULT NULL,
  `camera_ready_paper_summary` varchar(255) DEFAULT NULL,
  `camera_ready_paper_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presentation`
--

LOCK TABLES `presentation` WRITE;
/*!40000 ALTER TABLE `presentation` DISABLE KEYS */;
INSERT INTO `presentation` VALUES (2414,0,20,'Room4','2019-12-11 12:00:00','Summary','TitlePresentation1','Author1, Author2','http://document.com','Summary','Paper1'),(2434,0,20,'Room4','2019-12-11 12:00:00','Summary','TitlePresentation1','Author1, Author2','http://document.com','Summary','Paper1'),(2447,0,20,'Room4','2019-12-11 12:00:00','Summary','TitlePresentation1','Author1, Author2','http://document.com','Summary','Paper1');
/*!40000 ALTER TABLE `presentation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expiration_month` int(11) NOT NULL,
  `expiration_year` int(11) NOT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  `conference` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jpwxbrhvii9ekt338bmrdatfg` (`author`),
  KEY `FK_lc3odbpd5lgo7qc3w33ugwafj` (`conference`),
  CONSTRAINT `FK_lc3odbpd5lgo7qc3w33ugwafj` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_jpwxbrhvii9ekt338bmrdatfg` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (2467,0,'VISA',123,10,22,'Holder','4043492660454935',2374,2421),(2468,0,'VISA',123,10,22,'Holder','4043492660454935',2374,2428),(2469,0,'VISA',123,10,22,'Holder','4043492660454935',2374,2441),(2470,0,'VISA',123,10,22,'Holder','4043492660454935',2374,2408),(2477,0,'AMEX',409,1,21,'Holder','373809536446159',2382,2408),(2478,0,'AMEX',409,1,21,'Holder','373809536446159',2382,2421),(2479,0,'AMEX',409,1,21,'Holder','373809536446159',2382,2425),(2480,0,'AMEX',409,1,21,'Holder','373809536446159',2382,2428),(2481,0,'AMEX',409,1,21,'Holder','373809536446159',2382,2441),(2482,0,'AMEX',409,1,21,'Holder','373809536446159',2382,2454),(2483,0,'VISA',123,10,22,'Holder','4043492660454935',2389,2408),(2484,0,'VISA',123,10,22,'Holder','4043492660454935',2389,2421);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `decision` varchar(255) DEFAULT NULL,
  `originality` double NOT NULL,
  `quality` double NOT NULL,
  `readability` double NOT NULL,
  `reviewer` int(11) NOT NULL,
  `submission` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_26pbnwfa1gklnebnnsotvqt88` (`reviewer`),
  KEY `FK_a0lt5jqh9b7s1gw3q77nywxxn` (`submission`),
  CONSTRAINT `FK_a0lt5jqh9b7s1gw3q77nywxxn` FOREIGN KEY (`submission`) REFERENCES `submission` (`id`),
  CONSTRAINT `FK_26pbnwfa1gklnebnnsotvqt88` FOREIGN KEY (`reviewer`) REFERENCES `reviewer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (2485,0,'ACCEPT',9.9,9.9,9.9,2391,2463),(2486,0,'REJECT',0.9,0.9,0.9,2391,2465),(2487,0,'REJECT',0.1,0.1,0.1,2394,2463),(2488,0,'BORDER-LINE',5,5,5,2396,2463);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_comments`
--

DROP TABLE IF EXISTS `report_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_comments` (
  `report` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_rv98td83ckn33xg2dtq0jq62u` (`report`),
  CONSTRAINT `FK_rv98td83ckn33xg2dtq0jq62u` FOREIGN KEY (`report`) REFERENCES `report` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_comments`
--

LOCK TABLES `report_comments` WRITE;
/*!40000 ALTER TABLE `report_comments` DISABLE KEYS */;
INSERT INTO `report_comments` VALUES (2485,'comment1'),(2485,'comment2'),(2486,'comment1'),(2486,'comment2'),(2487,'comment1'),(2487,'comment2'),(2488,'comment1'),(2488,'comment2');
/*!40000 ALTER TABLE `report_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer`
--

DROP TABLE IF EXISTS `reviewer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ite8gbxlfjyy7g7wqqiyjhkmn` (`user_account`),
  CONSTRAINT `FK_ite8gbxlfjyy7g7wqqiyjhkmn` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer`
--

LOCK TABLES `reviewer` WRITE;
/*!40000 ALTER TABLE `reviewer` DISABLE KEYS */;
INSERT INTO `reviewer` VALUES (2391,0,'address','email@email.com','Reviewer1','Reviewer1','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Reviewer1',2338),(2394,0,'address','email@email.com','Reviewer2','Reviewer2','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Reviewer2',2339),(2396,0,'address','email@email.com','Reviewer3','Reviewer3','123456789','http://photo.com','Reviewer3',2340),(2398,0,'address','email@email.com','Reviewer4','Reviewer4','123456789','http://photo.com','Reviewer4',2344),(2399,0,'address','email@email.com','Reviewer5','Reviewer5','123456789','http://photo.com','Reviewer5',2345),(2400,0,'address','email@email.com','Reviewer6','Reviewer6','123456789','http://photo.com','Reviewer6',2346);
/*!40000 ALTER TABLE `reviewer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer_keywords`
--

DROP TABLE IF EXISTS `reviewer_keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewer_keywords` (
  `reviewer` int(11) NOT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  KEY `FK_d1mpqlt4vmfyn53hbyt5la0uv` (`reviewer`),
  CONSTRAINT `FK_d1mpqlt4vmfyn53hbyt5la0uv` FOREIGN KEY (`reviewer`) REFERENCES `reviewer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer_keywords`
--

LOCK TABLES `reviewer_keywords` WRITE;
/*!40000 ALTER TABLE `reviewer_keywords` DISABLE KEYS */;
INSERT INTO `reviewer_keywords` VALUES (2391,'keyword1'),(2391,'keyword2'),(2391,'keyword3'),(2394,'keyword1'),(2394,'keyword2'),(2394,'keyword3'),(2396,'keyword1'),(2396,'keyword2'),(2396,'keyword3'),(2398,'title1'),(2399,'title3'),(2400,'summary2');
/*!40000 ALTER TABLE `reviewer_keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (2410,0,'Summary','Title1'),(2411,0,'Summary2','Title2'),(2423,0,'Summary','Title1'),(2430,0,'Summary','Title1'),(2431,0,'Summary2','Title2'),(2443,0,'Summary','Title1'),(2444,0,'Summary2','Title2');
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section_pictures`
--

DROP TABLE IF EXISTS `section_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section_pictures` (
  `section` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_fpgvw49vb6tytfbfcghj3o8sv` (`section`),
  CONSTRAINT `FK_fpgvw49vb6tytfbfcghj3o8sv` FOREIGN KEY (`section`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section_pictures`
--

LOCK TABLES `section_pictures` WRITE;
/*!40000 ALTER TABLE `section_pictures` DISABLE KEYS */;
INSERT INTO `section_pictures` VALUES (2410,'https://picture1.jpg'),(2410,'https://picture2.jpg'),(2423,'https://picture1.jpg'),(2423,'https://picture2.jpg'),(2430,'https://picture1.jpg'),(2430,'https://picture2.jpg'),(2443,'https://picture1.jpg'),(2443,'https://picture2.jpg');
/*!40000 ALTER TABLE `section_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_du2w5ldt8rvlvxvtr7vyxk7g3` (`user_account`),
  CONSTRAINT `FK_du2w5ldt8rvlvxvtr7vyxk7g3` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
INSERT INTO `sponsor` VALUES (2401,0,'address','email@email.com','Sponsor1','Sponsor1','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Sponsor1',2341),(2404,0,'address','email@email.com','Sponsor2','Sponsor2','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Sponsor2',2342),(2406,0,'address','email@email.com','Sponsor3','Sponsor3','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Sponsor3',2343);
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsorship`
--

DROP TABLE IF EXISTS `sponsorship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsorship` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expiration_month` int(11) NOT NULL,
  `expiration_year` int(11) NOT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `targeturl` varchar(255) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  `sponsor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7okrey9gs578grrdgg4qph5rt` (`conference`),
  KEY `FK_huglhkud0ihqdljyou4eshra6` (`sponsor`),
  CONSTRAINT `FK_huglhkud0ihqdljyou4eshra6` FOREIGN KEY (`sponsor`) REFERENCES `sponsor` (`id`),
  CONSTRAINT `FK_7okrey9gs578grrdgg4qph5rt` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsorship`
--

LOCK TABLES `sponsorship` WRITE;
/*!40000 ALTER TABLE `sponsorship` DISABLE KEYS */;
INSERT INTO `sponsorship` VALUES (2489,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2408,2401),(2490,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2421,2401),(2491,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2425,2401),(2492,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2428,2401),(2493,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2441,2401),(2494,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2454,2401),(2495,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2408,2404),(2496,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2408,2404),(2497,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2421,2404),(2498,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2425,2404),(2499,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2428,2404),(2500,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2441,2404);
/*!40000 ALTER TABLE `sponsorship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission`
--

DROP TABLE IF EXISTS `submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `camera_ready_authors` varchar(255) DEFAULT NULL,
  `camera_ready_document` varchar(255) DEFAULT NULL,
  `camera_ready_summary` varchar(255) DEFAULT NULL,
  `camera_ready_title` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `authors` varchar(255) DEFAULT NULL,
  `document` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `reports_available` bit(1) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  `conference` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9ayhftkow8judgm0cblwdb9mi` (`ticker`),
  KEY `UK_o7h9tmc0x709fwwr2apynsdct` (`status`),
  KEY `FK_ssk77t9sokwi9utdru9hvodul` (`author`),
  KEY `FK_1vynnfw6cw1l40c8e342st672` (`conference`),
  CONSTRAINT `FK_1vynnfw6cw1l40c8e342st672` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_ssk77t9sokwi9utdru9hvodul` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
INSERT INTO `submission` VALUES (2463,0,'Author1, Author2','http://document.com','Summary','CameraReadyPaper1','2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','','ACCEPTED','AAA-O3L6',2374,2408),(2464,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-K3GU',2374,2421),(2465,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','REJECTED','AAA-A1B2',2374,2428),(2466,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','','UNDER-REVIEW','AAA-5A57',2374,2441),(2471,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-FHK7',2382,2421),(2472,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-LJK7',2382,2408),(2473,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-AAAA',2382,2425),(2474,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-12BB',2382,2428),(2475,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-5F6A',2382,2441),(2476,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-1234',2382,2425);
/*!40000 ALTER TABLE `submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission_reviewers`
--

DROP TABLE IF EXISTS `submission_reviewers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission_reviewers` (
  `submission` int(11) NOT NULL,
  `reviewers` int(11) NOT NULL,
  KEY `FK_rx31lgg0k67efoplhxv8len0c` (`reviewers`),
  KEY `FK_iwsj2ioiue7vmn5bnhxb2oatb` (`submission`),
  CONSTRAINT `FK_iwsj2ioiue7vmn5bnhxb2oatb` FOREIGN KEY (`submission`) REFERENCES `submission` (`id`),
  CONSTRAINT `FK_rx31lgg0k67efoplhxv8len0c` FOREIGN KEY (`reviewers`) REFERENCES `reviewer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission_reviewers`
--

LOCK TABLES `submission_reviewers` WRITE;
/*!40000 ALTER TABLE `submission_reviewers` DISABLE KEYS */;
INSERT INTO `submission_reviewers` VALUES (2463,2391),(2463,2394),(2463,2396),(2465,2391);
/*!40000 ALTER TABLE `submission_reviewers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_configuration`
--

DROP TABLE IF EXISTS `system_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  `welcome_message` varchar(255) DEFAULT NULL,
  `welcome_messagees` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_configuration`
--

LOCK TABLES `system_configuration` WRITE;
/*!40000 ALTER TABLE `system_configuration` DISABLE KEYS */;
INSERT INTO `system_configuration` VALUES (2347,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','+34','Acme Conference','Welcome to Acme Conference! Your scientific event manager','¡Bienvenidos a Acme Conference! Su gestor de eventos científicos');
/*!40000 ALTER TABLE `system_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_configuration_credit_card_makes`
--

DROP TABLE IF EXISTS `system_configuration_credit_card_makes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_configuration_credit_card_makes` (
  `system_configuration` int(11) NOT NULL,
  `credit_card_makes` varchar(255) DEFAULT NULL,
  KEY `FK_dphebyge12vq63lb6s7wj0a05` (`system_configuration`),
  CONSTRAINT `FK_dphebyge12vq63lb6s7wj0a05` FOREIGN KEY (`system_configuration`) REFERENCES `system_configuration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_configuration_credit_card_makes`
--

LOCK TABLES `system_configuration_credit_card_makes` WRITE;
/*!40000 ALTER TABLE `system_configuration_credit_card_makes` DISABLE KEYS */;
INSERT INTO `system_configuration_credit_card_makes` VALUES (2347,'VISA'),(2347,'MASTER'),(2347,'DINNERS'),(2347,'AMEX');
/*!40000 ALTER TABLE `system_configuration_credit_card_makes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_configuration_void_words`
--

DROP TABLE IF EXISTS `system_configuration_void_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_configuration_void_words` (
  `system_configuration` int(11) NOT NULL,
  `void_words` varchar(255) DEFAULT NULL,
  KEY `FK_gskbyn4ahedqpkopqnsc59f97` (`system_configuration`),
  CONSTRAINT `FK_gskbyn4ahedqpkopqnsc59f97` FOREIGN KEY (`system_configuration`) REFERENCES `system_configuration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_configuration_void_words`
--

LOCK TABLES `system_configuration_void_words` WRITE;
/*!40000 ALTER TABLE `system_configuration_void_words` DISABLE KEYS */;
INSERT INTO `system_configuration_void_words` VALUES (2347,'a'),(2347,'acá'),(2347,'ahí'),(2347,'ajena'),(2347,'ajeno'),(2347,'ajenas'),(2347,'ajenos'),(2347,'al'),(2347,'algo'),(2347,'algún'),(2347,'alguna'),(2347,'alguno'),(2347,'algunas'),(2347,'algunos'),(2347,'allá'),(2347,'allí'),(2347,'ambos'),(2347,'ante'),(2347,'antes'),(2347,'aquel'),(2347,'aquella'),(2347,'aquello'),(2347,'aquelas'),(2347,'aquellos'),(2347,'aquí'),(2347,'arriba'),(2347,'así'),(2347,'atrás'),(2347,'aun'),(2347,'aunque'),(2347,'bajo'),(2347,'bastante'),(2347,'bien'),(2347,'cabe'),(2347,'cada'),(2347,'casi'),(2347,'cierto'),(2347,'cierta'),(2347,'ciertos'),(2347,'ciertas'),(2347,'como'),(2347,'con'),(2347,'conmigo'),(2347,'conseguimos'),(2347,'conseguir'),(2347,'consigo'),(2347,'consigue'),(2347,'consiguen'),(2347,'consigues'),(2347,'contigo'),(2347,'contra'),(2347,'cual'),(2347,'cuales'),(2347,'cualquier'),(2347,'cualquiera'),(2347,'cualesquiera'),(2347,'cuan'),(2347,'cuando'),(2347,'cuanto'),(2347,'cuanta'),(2347,'cuantos'),(2347,'cuantas'),(2347,'de'),(2347,'dejar'),(2347,'del'),(2347,'demás'),(2347,'demasiada'),(2347,'demasiado'),(2347,'demasiadas'),(2347,'demasidos'),(2347,'dentro'),(2347,'desde'),(2347,'donde'),(2347,'dos'),(2347,'el'),(2347,'él'),(2347,'ella'),(2347,'ello'),(2347,'ellos'),(2347,'ellas'),(2347,'empleáis'),(2347,'emplean'),(2347,'emplear'),(2347,'empleas'),(2347,'empleo'),(2347,'en'),(2347,'encima'),(2347,'entonces'),(2347,'entre'),(2347,'era'),(2347,'eras'),(2347,'eramos'),(2347,'eran'),(2347,'eres'),(2347,'es'),(2347,'esa'),(2347,'ese'),(2347,'eso'),(2347,'esas'),(2347,'esos'),(2347,'esta'),(2347,'estas'),(2347,'estaba'),(2347,'estado'),(2347,'estáis'),(2347,'estamos'),(2347,'están'),(2347,'estar'),(2347,'este'),(2347,'esto'),(2347,'estos'),(2347,'estoy'),(2347,'etc'),(2347,'fin'),(2347,'fue'),(2347,'fueron'),(2347,'fui'),(2347,'fuimos'),(2347,'gueno'),(2347,'ha'),(2347,'hace'),(2347,'haces'),(2347,'hacéis'),(2347,'hacemos'),(2347,'hacen'),(2347,'hacer'),(2347,'hacia'),(2347,'hago'),(2347,'hasta'),(2347,'incluso'),(2347,'intenta'),(2347,'intentas'),(2347,'intentáis'),(2347,'intentamos'),(2347,'intentan'),(2347,'intentar'),(2347,'intento'),(2347,'ir'),(2347,'jamás'),(2347,'junto'),(2347,'juntos'),(2347,'la'),(2347,'lo'),(2347,'las'),(2347,'los'),(2347,'largo'),(2347,'más'),(2347,'me'),(2347,'menos'),(2347,'mi'),(2347,'mis'),(2347,'mía'),(2347,'mías'),(2347,'mientras'),(2347,'mío'),(2347,'míos'),(2347,'misma'),(2347,'mismo'),(2347,'mismas'),(2347,'mismos'),(2347,'modo'),(2347,'mucha'),(2347,'muchas'),(2347,'muchísima'),(2347,'muchísimo'),(2347,'muchísimas'),(2347,'muchísimos'),(2347,'mucho'),(2347,'muchos'),(2347,'muy'),(2347,'nada'),(2347,'ni'),(2347,'ningún'),(2347,'niguna'),(2347,'ninguno'),(2347,'ningunas'),(2347,'ningunos'),(2347,'no'),(2347,'nos'),(2347,'nosotras'),(2347,'nosotros'),(2347,'nuestra'),(2347,'nuestro'),(2347,'nuestras'),(2347,'nuestros'),(2347,'nunca'),(2347,'os'),(2347,'otra'),(2347,'otro'),(2347,'otras'),(2347,'otros'),(2347,'para'),(2347,'parecer'),(2347,'pero'),(2347,'poca'),(2347,'poco'),(2347,'pocas'),(2347,'pocos'),(2347,'podéis'),(2347,'podemos'),(2347,'poder'),(2347,'podría'),(2347,'podrías'),(2347,'podríais'),(2347,'podríamos'),(2347,'podrían'),(2347,'por'),(2347,'por qué'),(2347,'porque'),(2347,'primero'),(2347,'puede'),(2347,'pueden'),(2347,'puedo'),(2347,'pues'),(2347,'que'),(2347,'qué'),(2347,'querer'),(2347,'quién'),(2347,'quienes'),(2347,'quienesquiera'),(2347,'quienquiera'),(2347,'quizá'),(2347,'quizás'),(2347,'sabe'),(2347,'sabes'),(2347,'saben'),(2347,'sabéis'),(2347,'sabemos'),(2347,'saber'),(2347,'se'),(2347,'según'),(2347,'ser'),(2347,'si'),(2347,'sí'),(2347,'siempre'),(2347,'siendo'),(2347,'sin'),(2347,'sino'),(2347,'so'),(2347,'sobre'),(2347,'sois'),(2347,'solamente'),(2347,'solo'),(2347,'sólo'),(2347,'somos'),(2347,'soy'),(2347,'sr'),(2347,'sra'),(2347,'sres'),(2347,'sta'),(2347,'su'),(2347,'sus'),(2347,'suya'),(2347,'suyo'),(2347,'suyas'),(2347,'suyos'),(2347,'tal'),(2347,'tales'),(2347,'también'),(2347,'tampoco'),(2347,'tan'),(2347,'tanta'),(2347,'tanto'),(2347,'tantas'),(2347,'tantos'),(2347,'te'),(2347,'tenéis'),(2347,'tenemos'),(2347,'tener'),(2347,'tengo'),(2347,'ti'),(2347,'tiempo'),(2347,'tiene'),(2347,'tienen'),(2347,'toda'),(2347,'todo'),(2347,'todas'),(2347,'todos'),(2347,'tomar'),(2347,'trabaja'),(2347,'trabajo'),(2347,'trabajáis'),(2347,'trabajamos'),(2347,'trabajan'),(2347,'trabajar'),(2347,'trabajas'),(2347,'tras'),(2347,'tú'),(2347,'tu'),(2347,'tus'),(2347,'tuya'),(2347,'tuyo'),(2347,'tuyas'),(2347,'tuyos'),(2347,'último'),(2347,'ultimo'),(2347,'un'),(2347,'una'),(2347,'uno'),(2347,'unas'),(2347,'unos'),(2347,'usa'),(2347,'usas'),(2347,'usáis'),(2347,'usamos'),(2347,'usan'),(2347,'usar'),(2347,'uso'),(2347,'usted'),(2347,'ustedes'),(2347,'va'),(2347,'van'),(2347,'vais'),(2347,'valor'),(2347,'vamos'),(2347,'varias'),(2347,'varios'),(2347,'vaya'),(2347,'verdadera'),(2347,'vosotras'),(2347,'vosotros'),(2347,'voy'),(2347,'vuestra'),(2347,'vuestro'),(2347,'vuestras'),(2347,'vuestros'),(2347,'y'),(2347,'ya'),(2347,'yo'),(2347,'a'),(2347,'able'),(2347,'about'),(2347,'across'),(2347,'after'),(2347,'all'),(2347,'almost'),(2347,'also'),(2347,'am'),(2347,'among'),(2347,'an'),(2347,'and'),(2347,'any'),(2347,'are'),(2347,'as'),(2347,'at'),(2347,'be'),(2347,'because'),(2347,'been'),(2347,'but'),(2347,'by'),(2347,'can'),(2347,'cannot'),(2347,'could'),(2347,'dear'),(2347,'did'),(2347,'do'),(2347,'does'),(2347,'either'),(2347,'else'),(2347,'ever'),(2347,'every'),(2347,'for'),(2347,'from'),(2347,'get'),(2347,'got'),(2347,'had'),(2347,'has'),(2347,'have'),(2347,'he'),(2347,'her'),(2347,'hers'),(2347,'him'),(2347,'his'),(2347,'how'),(2347,'however'),(2347,'i'),(2347,'if'),(2347,'in'),(2347,'into'),(2347,'is'),(2347,'it'),(2347,'its'),(2347,'just'),(2347,'least'),(2347,'let'),(2347,'like'),(2347,'likely'),(2347,'may'),(2347,'me'),(2347,'might'),(2347,'most'),(2347,'must'),(2347,'my'),(2347,'neither'),(2347,'no'),(2347,'nor'),(2347,'not'),(2347,'of'),(2347,'off'),(2347,'often'),(2347,'on'),(2347,'only'),(2347,'or'),(2347,'other'),(2347,'our'),(2347,'own'),(2347,'rather'),(2347,'said'),(2347,'say'),(2347,'says'),(2347,'she'),(2347,'should'),(2347,'since'),(2347,'so'),(2347,'some'),(2347,'than'),(2347,'that'),(2347,'the'),(2347,'their'),(2347,'them'),(2347,'then'),(2347,'there'),(2347,'these'),(2347,'they'),(2347,'this'),(2347,'tis'),(2347,'to'),(2347,'too'),(2347,'twas'),(2347,'us'),(2347,'wants'),(2347,'was'),(2347,'we'),(2347,'were'),(2347,'what'),(2347,'when'),(2347,'where'),(2347,'which'),(2347,'while'),(2347,'who'),(2347,'whom'),(2347,'why'),(2347,'will'),(2347,'with'),(2347,'would'),(2347,'yet'),(2347,'you'),(2347,'your');
/*!40000 ALTER TABLE `system_configuration_void_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `namees` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_mbunn9erv8nmf5lk1r2nu0nex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (2367,0,'INQUIRY','PREGUNTA'),(2368,0,'REBUTTAL','REFUTACIÓN'),(2369,0,'REGISTRATION','REGISTRO'),(2370,0,'TOPICS','TEMAS'),(2371,0,'OTHER','OTRO');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorial`
--

DROP TABLE IF EXISTS `tutorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorial` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `start_moment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorial`
--

LOCK TABLES `tutorial` WRITE;
/*!40000 ALTER TABLE `tutorial` DISABLE KEYS */;
INSERT INTO `tutorial` VALUES (2409,0,60,'Room1','2019-12-10 12:00:00','Summary','TitleTutorial1'),(2422,0,60,'Room1','2019-12-10 12:00:00','Summary','TitleTutorial1'),(2429,0,60,'Room1','2019-12-10 12:00:00','Summary','TitleTutorial1'),(2442,0,60,'Room1','2019-12-10 12:00:00','Summary','TitleTutorial1');
/*!40000 ALTER TABLE `tutorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorial_sections`
--

DROP TABLE IF EXISTS `tutorial_sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorial_sections` (
  `tutorial` int(11) NOT NULL,
  `sections` int(11) NOT NULL,
  KEY `FK_fam9vwtnrx0m7vmnqngwoekdo` (`sections`),
  KEY `FK_8sl8cpfc93exnk3nv9a6okamu` (`tutorial`),
  CONSTRAINT `FK_8sl8cpfc93exnk3nv9a6okamu` FOREIGN KEY (`tutorial`) REFERENCES `tutorial` (`id`),
  CONSTRAINT `FK_fam9vwtnrx0m7vmnqngwoekdo` FOREIGN KEY (`sections`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorial_sections`
--

LOCK TABLES `tutorial_sections` WRITE;
/*!40000 ALTER TABLE `tutorial_sections` DISABLE KEYS */;
INSERT INTO `tutorial_sections` VALUES (2409,2410),(2409,2411),(2422,2423),(2429,2430),(2429,2431),(2442,2443),(2442,2444);
/*!40000 ALTER TABLE `tutorial_sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (2334,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(2335,0,'b312ba4ffd5245fa2a1ab819ec0d0347','author1'),(2336,0,'9bd97baef2b853ec00cc3cffd269f679','author2'),(2337,0,'c59a474d5ade296a15ebc40d6c4e8e11','author3'),(2338,0,'6ce19528a40dde9521d97cf7ba264eca','reviewer1'),(2339,0,'2693b57f0f59df94caacefb811e99851','reviewer2'),(2340,0,'315d31e7c8f3a136610aafa220d689be','reviewer3'),(2341,0,'42c63ad66d4dc07ed17753772bef96d6','sponsor1'),(2342,0,'3dc67f80a03324e01b1640f45d107485','sponsor2'),(2343,0,'857a54956061fdc1b88d7722cafe6519','sponsor3'),(2344,0,'5293e8663cbb7c157ff83eeae25177d3','reviewer4'),(2345,0,'86045e9e3b2e615d8d2484dc64b3f408','reviewer5'),(2346,0,'8c14745274135379448d482d5773ee31','reviewer6');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (2334,'ADMIN'),(2335,'AUTHOR'),(2336,'AUTHOR'),(2337,'AUTHOR'),(2338,'REVIEWER'),(2339,'REVIEWER'),(2340,'REVIEWER'),(2341,'SPONSOR'),(2342,'SPONSOR'),(2343,'SPONSOR'),(2344,'REVIEWER'),(2345,'REVIEWER'),(2346,'REVIEWER');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-30 16:46:23
