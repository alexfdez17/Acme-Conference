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
INSERT INTO `activity_attachments` VALUES (2845,'https://attachment.com'),(2848,'https://attachment.com'),(2849,'https://attachment.com'),(2850,'https://attachment.com'),(2858,'https://attachment.com'),(2860,'https://attachment.com'),(2862,'https://attachment.com'),(2863,'https://attachment.com'),(2865,'https://attachment.com'),(2868,'https://attachment.com'),(2869,'https://attachment.com'),(2870,'https://attachment.com'),(2878,'https://attachment.com'),(2881,'https://attachment.com'),(2882,'https://attachment.com'),(2883,'https://attachment.com'),(2891,'https://attachment.com'),(2892,'https://attachment.com');
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
INSERT INTO `activity_speakers` VALUES (2845,'Speaker1'),(2845,'Speaker2'),(2848,'Speaker3'),(2849,'Speaker4'),(2849,'Speaker5'),(2850,'Speaker6'),(2858,'Speaker1'),(2858,'Speaker2'),(2860,'Speaker3'),(2862,'Speaker3'),(2863,'Speaker4'),(2863,'Speaker5'),(2865,'Speaker1'),(2865,'Speaker2'),(2868,'Speaker3'),(2869,'Speaker4'),(2869,'Speaker5'),(2870,'Speaker6'),(2878,'Speaker1'),(2878,'Speaker2'),(2881,'Speaker3'),(2882,'Speaker4'),(2882,'Speaker5'),(2883,'Speaker6'),(2891,'Speaker3'),(2892,'Speaker4'),(2892,'Speaker5');
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
INSERT INTO `administrator` VALUES (2785,0,'address','email@email.com','Admin','Admin','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Admin',2747);
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
INSERT INTO `author` VALUES (2792,0,'address','email@email.com','Author1','Author1','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Author1',2748,NULL),(2800,0,'address','email@email.com','Author2','Author2','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Author2',2749,NULL),(2807,0,'address','email@email.com','Author3','Author3','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Author3',2750,NULL);
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
INSERT INTO `category` VALUES (2761,0,'CONFERENCE',NULL),(2762,0,'Computer Science',2761),(2763,0,'Artificial Intelligence',2762),(2764,0,'Deep Learning',2763),(2765,0,'Instance-based learning',2763),(2766,0,'Regression',2763),(2767,0,'Software Engineering',2762),(2768,0,'Big Data',2767),(2769,0,'Big Processing',2767),(2770,0,'Methods',2767),(2771,0,'Tools',2767),(2772,0,'Physics',2761),(2773,0,'Cinematic',2772),(2774,0,'Electricity',2772),(2775,0,'Electronics',2772),(2776,0,'Biology',2761),(2777,0,'Animals',2776),(2778,0,'Plants',2776),(2779,0,'Fungi',2776);
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
INSERT INTO `comment` VALUES (2851,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2845),(2852,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2848),(2853,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2850),(2854,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2844),(2855,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2844),(2856,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2844),(2871,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2865),(2872,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2868),(2873,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2870),(2874,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2864),(2875,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2864),(2876,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2864),(2884,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2878),(2885,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2881),(2886,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2883),(2887,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2877),(2888,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2877),(2889,0,'author1','2019-07-03 15:00:00','Text Comment','TitleComment',2877);
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
INSERT INTO `conference` VALUES (2844,0,'Acronym1','2019-07-26 12:00:00','2019-07-31',100,'','2019-07-24 12:00:00','2019-07-26','2019-07-17 12:00:00','This science conference focuses on technologies to deal with nowadays data','Conference on Data','Venue1',2767),(2857,0,'Acronym2','2019-11-30 12:00:00','2019-12-31',100,'','2019-11-20 12:00:00','2019-12-01','2019-11-10 12:00:00','Data Science is a hot topic nowadays; find the latest research results in this conference','International Data Science Conference','Venue2',2767),(2861,0,'Acronym3','2019-10-30 12:00:00','2019-11-30',100,'\0','2019-10-20 12:00:00','2019-11-01','2019-10-10 12:00:00','Nowhere have you found more papers on big data science','World Multi-Conference on Big Data Science','Venue3',2767),(2864,0,'Acronym4','2019-11-20 12:00:00','2019-12-31',100,'','2019-11-10 12:00:00','2019-12-01','2019-07-01 12:00:00','A conference about artificial intelligence and its role on computer science','Artificial Intelligence on Computer Science','Venue4',2762),(2877,0,'Acronym5','2019-11-20 12:00:00','2019-12-31',100,'','2019-11-10 12:00:00','2019-12-01','2019-07-01 12:00:00','Data science and its influence on business and macro economy','Data, business and economy','Venue5',2767),(2890,0,'Acronym6','2019-10-30 12:00:00','2019-11-30',100,'\0','2019-10-20 12:00:00','2019-11-01','2019-10-10 12:00:00','A conference about the discovery of the scutoid','Scutoid\'s discovery','Venue6',2776);
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
INSERT INTO `conference_activities` VALUES (2844,2845),(2844,2848),(2844,2849),(2844,2850),(2857,2858),(2857,2860),(2861,2862),(2861,2863),(2864,2865),(2864,2868),(2864,2869),(2864,2870),(2877,2878),(2877,2881),(2877,2882),(2877,2883),(2890,2891),(2890,2892);
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
INSERT INTO `credit_card` VALUES (2896,0,'VISA',123,10,22,'Holder','4043492660454935'),(2897,0,'AMEX',409,1,21,'Holder','373809536446159'),(2898,0,'MASTER',774,9,22,'Holder','5301584370515122'),(2899,0,'DINERS',975,1,21,'Holder','30718500054540');
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
INSERT INTO `finder` VALUES (2793,0,NULL,'2020-01-01',NULL,NULL,'2019-01-01',2792);
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
INSERT INTO `finder_conferences` VALUES (2793,2844),(2793,2857);
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
INSERT INTO `mezzage` VALUES (2786,0,'Body','','2019-01-01 12:00:00','Subject',2785,2785,2784),(2787,0,'Body','\0','2019-01-01 12:00:00','Subject',2785,2785,2784),(2788,0,'Body','\0','2019-01-01 12:00:00','Subject',2785,2785,2783),(2789,0,'Body','\0','2019-01-01 12:00:00','Subject',2785,2785,2782),(2790,0,'Body','\0','2019-01-01 12:00:00','Subject',2785,2785,2781),(2791,0,'Body','\0','2019-01-01 12:00:00','Subject',2785,2785,2780),(2794,0,'Body','\0','2019-01-01 12:00:00','Subject',2792,2792,2780),(2795,0,'Body','\0','2019-01-01 12:00:00','Subject',2792,2792,2781),(2796,0,'Body','\0','2019-01-01 12:00:00','Subject',2792,2792,2781),(2797,0,'Body','\0','2019-01-01 12:00:00','Subject',2792,2792,2782),(2798,0,'Body','\0','2019-01-01 12:00:00','Subject',2792,2792,2783),(2799,0,'Body','\0','2019-01-01 12:00:00','Subject',2792,2792,2784),(2801,0,'Body','\0','2019-01-01 12:00:00','Subject',2800,2800,2780),(2802,0,'Body','\0','2019-01-01 12:00:00','Subject',2800,2800,2782),(2803,0,'Body','\0','2019-01-01 12:00:00','Subject',2800,2800,2781),(2804,0,'Body','\0','2019-01-01 12:00:00','Subject',2800,2800,2784),(2805,0,'Body','\0','2019-01-01 12:00:00','Subject',2800,2800,2780),(2806,0,'Body','\0','2019-01-01 12:00:00','Subject',2800,2800,2783),(2808,0,'Body','\0','2019-01-01 12:00:00','Subject',2807,2807,2783),(2810,0,'Body','\0','2019-01-01 12:00:00','Subject',2809,2809,2782),(2811,0,'Body','\0','2019-01-01 12:00:00','Subject',2809,2809,2781),(2812,0,'Body','\0','2019-01-01 12:00:00','Subject',2809,2809,2780),(2813,0,'Body','\0','2019-01-01 12:00:00','Subject',2809,2809,2781),(2814,0,'Body','\0','2019-01-01 12:00:00','Subject',2809,2809,2783),(2815,0,'Body','\0','2019-01-01 12:00:00','Subject',2809,2809,2784),(2817,0,'Body','\0','2019-01-01 12:00:00','Subject',2816,2816,2780),(2818,0,'Body','\0','2019-01-01 12:00:00','Subject',2816,2816,2782),(2819,0,'Body','\0','2019-01-01 12:00:00','Subject',2816,2816,2781),(2820,0,'Body','\0','2019-01-01 12:00:00','Subject',2816,2816,2783),(2821,0,'Body','\0','2019-01-01 12:00:00','Subject',2816,2816,2782),(2822,0,'Body','\0','2019-01-01 12:00:00','Subject',2816,2816,2784),(2824,0,'Body','\0','2019-01-01 12:00:00','Subject',2823,2823,2784),(2829,0,'Body','\0','2019-01-01 12:00:00','Subject',2828,2828,2784),(2830,0,'Body','\0','2019-01-01 12:00:00','Subject',2828,2828,2780),(2831,0,'Body','\0','2019-01-01 12:00:00','Subject',2828,2828,2780),(2832,0,'Body','\0','2019-01-01 12:00:00','Subject',2828,2828,2782),(2833,0,'Body','\0','2019-01-01 12:00:00','Subject',2828,2828,2781),(2834,0,'Body','\0','2019-01-01 12:00:00','Subject',2828,2828,2784),(2836,0,'Body','\0','2019-01-01 12:00:00','Subject',2835,2835,2780),(2837,0,'Body','\0','2019-01-01 12:00:00','Subject',2835,2835,2781),(2838,0,'Body','\0','2019-01-01 12:00:00','Subject',2835,2835,2782),(2839,0,'Body','\0','2019-01-01 12:00:00','Subject',2835,2835,2783),(2840,0,'Body','\0','2019-01-01 12:00:00','Subject',2835,2835,2784),(2841,0,'Body','\0','2019-01-01 12:00:00','Subject',2835,2835,2780),(2843,0,'Body','\0','2019-01-01 12:00:00','Subject',2842,2842,2780);
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
INSERT INTO `mezzage_recipients` VALUES (2786,2792),(2786,2809),(2786,2828),(2786,2800),(2786,2816),(2786,2835),(2786,2807),(2786,2823),(2786,2842),(2787,2792),(2788,2809),(2789,2828),(2790,2800),(2791,2816),(2794,2800),(2794,2807),(2795,2809),(2796,2816),(2796,2823),(2797,2828),(2798,2835),(2798,2842),(2799,2800),(2799,2809),(2799,2842),(2799,2785),(2801,2792),(2801,2807),(2802,2809),(2802,2828),(2803,2792),(2804,2807),(2805,2785),(2806,2809),(2808,2785),(2810,2792),(2811,2807),(2812,2807),(2813,2828),(2814,2785),(2815,2792),(2817,2828),(2818,2785),(2819,2800),(2820,2792),(2821,2809),(2822,2835),(2824,2828),(2829,2807),(2829,2792),(2830,2807),(2830,2792),(2831,2835),(2831,2842),(2832,2823),(2832,2792),(2833,2785),(2834,2800),(2834,2842),(2836,2842),(2836,2823),(2837,2842),(2837,2823),(2838,2842),(2838,2823),(2839,2842),(2839,2823),(2840,2842),(2840,2823),(2841,2842),(2841,2823),(2843,2809),(2843,2816);
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
INSERT INTO `panel` VALUES (2848,0,30,'Room2','2019-12-10 16:00:00','Summary','TitlePanel1'),(2849,0,60,'Room3','2019-12-10 20:00:00','Summary','TitlePanel2'),(2860,0,30,'Room2','2019-12-10 16:00:00','Summary','TitlePanel1'),(2862,0,30,'Room2','2019-11-10 16:00:00','Summary','TitlePanel1'),(2863,0,60,'Room3','2019-11-10 20:00:00','Summary','TitlePanel2'),(2868,0,30,'Room2','2019-12-10 16:00:00','Summary','TitlePanel1'),(2869,0,60,'Room3','2019-12-10 20:00:00','Summary','TitlePanel2'),(2881,0,30,'Room2','2019-12-10 16:00:00','Summary','TitlePanel1'),(2882,0,60,'Room3','2019-12-10 20:00:00','Summary','TitlePanel2'),(2891,0,30,'Room2','2019-11-10 16:00:00','Summary','TitlePanel1'),(2892,0,60,'Room3','2019-11-10 20:00:00','Summary','TitlePanel2');
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
INSERT INTO `paper` VALUES (2893,0,'Author1, Author2','http://document.com','Summary','Paper1'),(2894,0,'Author1, Author2','http://document.com','This paper is about the conference about data science','Paper about data'),(2895,0,'Author1','http://document.com','Paper about data','Camera ready paper');
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
INSERT INTO `presentation` VALUES (2850,0,20,'Room4','2019-12-11 12:00:00','Summary','TitlePresentation1','Author1, Author2','http://document.com','Summary','Paper1'),(2870,0,20,'Room4','2019-12-11 12:00:00','Summary','TitlePresentation1','Author1, Author2','http://document.com','Summary','Paper1'),(2883,0,20,'Room4','2019-12-11 12:00:00','Summary','TitlePresentation1','Author1, Author2','http://document.com','Summary','Paper1');
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
INSERT INTO `registration` VALUES (2904,0,'VISA',123,10,22,'Holder','4043492660454935',2792,2857),(2905,0,'VISA',123,10,22,'Holder','4043492660454935',2792,2864),(2906,0,'VISA',123,10,22,'Holder','4043492660454935',2792,2877),(2907,0,'VISA',123,10,22,'Holder','4043492660454935',2792,2844),(2914,0,'AMEX',409,1,21,'Holder','373809536446159',2800,2844),(2915,0,'AMEX',409,1,21,'Holder','373809536446159',2800,2857),(2916,0,'AMEX',409,1,21,'Holder','373809536446159',2800,2861),(2917,0,'AMEX',409,1,21,'Holder','373809536446159',2800,2864),(2918,0,'AMEX',409,1,21,'Holder','373809536446159',2800,2877),(2919,0,'AMEX',409,1,21,'Holder','373809536446159',2800,2890),(2920,0,'VISA',123,10,22,'Holder','4043492660454935',2807,2844),(2921,0,'VISA',123,10,22,'Holder','4043492660454935',2807,2857);
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
INSERT INTO `report` VALUES (2922,0,'ACCEPT',9.9,9.9,9.9,2809,2900),(2923,0,'REJECT',0.9,0.9,0.9,2809,2902),(2924,0,'REJECT',0.1,0.1,0.1,2816,2900),(2925,0,'BORDER-LINE',5,5,5,2823,2900);
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
INSERT INTO `report_comments` VALUES (2922,'comment1'),(2922,'comment2'),(2923,'comment1'),(2923,'comment2'),(2924,'comment1'),(2924,'comment2'),(2925,'comment1'),(2925,'comment2');
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
INSERT INTO `reviewer` VALUES (2809,0,'address','email@email.com','Reviewer1','Reviewer1','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Reviewer1',2751),(2816,0,'address','email@email.com','Reviewer2','Reviewer2','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Reviewer2',2752),(2823,0,'address','email@email.com','Reviewer3','Reviewer3','123456789','http://photo.com','Reviewer3',2753),(2825,0,'address','email@email.com','Reviewer4','Reviewer4','123456789','http://photo.com','Reviewer4',2757),(2826,0,'address','email@email.com','Reviewer5','Reviewer5','123456789','http://photo.com','Reviewer5',2758),(2827,0,'address','email@email.com','Reviewer6','Reviewer6','123456789','http://photo.com','Reviewer6',2759);
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
INSERT INTO `reviewer_keywords` VALUES (2809,'data'),(2809,'science'),(2809,'computer'),(2816,'science'),(2816,'economy'),(2823,'business'),(2823,'computer'),(2825,'data'),(2826,'biology'),(2826,'nature'),(2827,'architecture'),(2827,'design');
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
INSERT INTO `section` VALUES (2846,0,'Summary','Title1'),(2847,0,'Summary2','Title2'),(2859,0,'Summary','Title1'),(2866,0,'Summary','Title1'),(2867,0,'Summary2','Title2'),(2879,0,'Summary','Title1'),(2880,0,'Summary2','Title2');
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
INSERT INTO `section_pictures` VALUES (2846,'https://picture1.jpg'),(2846,'https://picture2.jpg'),(2859,'https://picture1.jpg'),(2859,'https://picture2.jpg'),(2866,'https://picture1.jpg'),(2866,'https://picture2.jpg'),(2879,'https://picture1.jpg'),(2879,'https://picture2.jpg');
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
INSERT INTO `sponsor` VALUES (2828,0,'address','email@email.com','Sponsor1','Sponsor1','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Sponsor1',2754),(2835,0,'address','email@email.com','Sponsor2','Sponsor2','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Sponsor2',2755),(2842,0,'address','email@email.com','Sponsor3','Sponsor3','123456789','https://i.ibb.co/GVpZCtM/acme-conference.png','Sponsor3',2756);
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
INSERT INTO `sponsorship` VALUES (2926,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2844,2828),(2927,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2857,2828),(2928,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2861,2828),(2929,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2864,2828),(2930,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2877,2828),(2931,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','MASTER',774,9,22,'Holder','5301584370515122','https://www.targeturl.com',2890,2828),(2932,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2844,2835),(2933,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2844,2835),(2934,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2857,2835),(2935,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2861,2835),(2936,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2864,2835),(2937,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','DINERS',975,1,21,'Holder','30718500054540','https://www.targeturl.com',2877,2835);
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
INSERT INTO `submission` VALUES (2900,0,'Author1, Author2','http://document.com','This paper is about the conference about data science','Paper about data','2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','','ACCEPTED','AAA-O3L6',2792,2844),(2901,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-K3GU',2792,2857),(2902,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','REJECTED','AAA-A1B2',2792,2864),(2903,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','','UNDER-REVIEW','AAA-5A57',2792,2877),(2908,0,'Author1','http://document.com','Paper about data','Camera ready paper','2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','ACCEPTED','AAA-FHK7',2800,2857),(2909,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-LJK7',2800,2844),(2910,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-AAAA',2800,2861),(2911,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-12BB',2800,2864),(2912,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-5F6A',2800,2877),(2913,0,NULL,NULL,NULL,NULL,'2019-07-03 12:00:00','Author1, Author2','http://document.com','Summary','Paper1','\0','UNDER-REVIEW','AAA-1234',2800,2861);
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
INSERT INTO `submission_reviewers` VALUES (2900,2809),(2900,2816),(2900,2823),(2902,2809),(2908,2809),(2908,2816),(2908,2823);
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
INSERT INTO `system_configuration` VALUES (2760,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','+34','Acme Conference','Welcome to Acme Conference! Your scientific event manager','¡Bienvenidos a Acme Conference! Su gestor de eventos científicos');
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
INSERT INTO `system_configuration_credit_card_makes` VALUES (2760,'VISA'),(2760,'MASTER'),(2760,'DINNERS'),(2760,'AMEX');
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
INSERT INTO `system_configuration_void_words` VALUES (2760,'a'),(2760,'acá'),(2760,'ahí'),(2760,'ajena'),(2760,'ajeno'),(2760,'ajenas'),(2760,'ajenos'),(2760,'al'),(2760,'algo'),(2760,'algún'),(2760,'alguna'),(2760,'alguno'),(2760,'algunas'),(2760,'algunos'),(2760,'allá'),(2760,'allí'),(2760,'ambos'),(2760,'ante'),(2760,'antes'),(2760,'aquel'),(2760,'aquella'),(2760,'aquello'),(2760,'aquelas'),(2760,'aquellos'),(2760,'aquí'),(2760,'arriba'),(2760,'así'),(2760,'atrás'),(2760,'aun'),(2760,'aunque'),(2760,'bajo'),(2760,'bastante'),(2760,'bien'),(2760,'cabe'),(2760,'cada'),(2760,'casi'),(2760,'cierto'),(2760,'cierta'),(2760,'ciertos'),(2760,'ciertas'),(2760,'como'),(2760,'con'),(2760,'conmigo'),(2760,'conseguimos'),(2760,'conseguir'),(2760,'consigo'),(2760,'consigue'),(2760,'consiguen'),(2760,'consigues'),(2760,'contigo'),(2760,'contra'),(2760,'cual'),(2760,'cuales'),(2760,'cualquier'),(2760,'cualquiera'),(2760,'cualesquiera'),(2760,'cuan'),(2760,'cuando'),(2760,'cuanto'),(2760,'cuanta'),(2760,'cuantos'),(2760,'cuantas'),(2760,'de'),(2760,'dejar'),(2760,'del'),(2760,'demás'),(2760,'demasiada'),(2760,'demasiado'),(2760,'demasiadas'),(2760,'demasidos'),(2760,'dentro'),(2760,'desde'),(2760,'donde'),(2760,'dos'),(2760,'el'),(2760,'él'),(2760,'ella'),(2760,'ello'),(2760,'ellos'),(2760,'ellas'),(2760,'empleáis'),(2760,'emplean'),(2760,'emplear'),(2760,'empleas'),(2760,'empleo'),(2760,'en'),(2760,'encima'),(2760,'entonces'),(2760,'entre'),(2760,'era'),(2760,'eras'),(2760,'eramos'),(2760,'eran'),(2760,'eres'),(2760,'es'),(2760,'esa'),(2760,'ese'),(2760,'eso'),(2760,'esas'),(2760,'esos'),(2760,'esta'),(2760,'estas'),(2760,'estaba'),(2760,'estado'),(2760,'estáis'),(2760,'estamos'),(2760,'están'),(2760,'estar'),(2760,'este'),(2760,'esto'),(2760,'estos'),(2760,'estoy'),(2760,'etc'),(2760,'fin'),(2760,'fue'),(2760,'fueron'),(2760,'fui'),(2760,'fuimos'),(2760,'gueno'),(2760,'ha'),(2760,'hace'),(2760,'haces'),(2760,'hacéis'),(2760,'hacemos'),(2760,'hacen'),(2760,'hacer'),(2760,'hacia'),(2760,'hago'),(2760,'hasta'),(2760,'incluso'),(2760,'intenta'),(2760,'intentas'),(2760,'intentáis'),(2760,'intentamos'),(2760,'intentan'),(2760,'intentar'),(2760,'intento'),(2760,'ir'),(2760,'jamás'),(2760,'junto'),(2760,'juntos'),(2760,'la'),(2760,'lo'),(2760,'las'),(2760,'los'),(2760,'largo'),(2760,'más'),(2760,'me'),(2760,'menos'),(2760,'mi'),(2760,'mis'),(2760,'mía'),(2760,'mías'),(2760,'mientras'),(2760,'mío'),(2760,'míos'),(2760,'misma'),(2760,'mismo'),(2760,'mismas'),(2760,'mismos'),(2760,'modo'),(2760,'mucha'),(2760,'muchas'),(2760,'muchísima'),(2760,'muchísimo'),(2760,'muchísimas'),(2760,'muchísimos'),(2760,'mucho'),(2760,'muchos'),(2760,'muy'),(2760,'nada'),(2760,'ni'),(2760,'ningún'),(2760,'niguna'),(2760,'ninguno'),(2760,'ningunas'),(2760,'ningunos'),(2760,'no'),(2760,'nos'),(2760,'nosotras'),(2760,'nosotros'),(2760,'nuestra'),(2760,'nuestro'),(2760,'nuestras'),(2760,'nuestros'),(2760,'nunca'),(2760,'os'),(2760,'otra'),(2760,'otro'),(2760,'otras'),(2760,'otros'),(2760,'para'),(2760,'parecer'),(2760,'pero'),(2760,'poca'),(2760,'poco'),(2760,'pocas'),(2760,'pocos'),(2760,'podéis'),(2760,'podemos'),(2760,'poder'),(2760,'podría'),(2760,'podrías'),(2760,'podríais'),(2760,'podríamos'),(2760,'podrían'),(2760,'por'),(2760,'por qué'),(2760,'porque'),(2760,'primero'),(2760,'puede'),(2760,'pueden'),(2760,'puedo'),(2760,'pues'),(2760,'que'),(2760,'qué'),(2760,'querer'),(2760,'quién'),(2760,'quienes'),(2760,'quienesquiera'),(2760,'quienquiera'),(2760,'quizá'),(2760,'quizás'),(2760,'sabe'),(2760,'sabes'),(2760,'saben'),(2760,'sabéis'),(2760,'sabemos'),(2760,'saber'),(2760,'se'),(2760,'según'),(2760,'ser'),(2760,'si'),(2760,'sí'),(2760,'siempre'),(2760,'siendo'),(2760,'sin'),(2760,'sino'),(2760,'so'),(2760,'sobre'),(2760,'sois'),(2760,'solamente'),(2760,'solo'),(2760,'sólo'),(2760,'somos'),(2760,'soy'),(2760,'sr'),(2760,'sra'),(2760,'sres'),(2760,'sta'),(2760,'su'),(2760,'sus'),(2760,'suya'),(2760,'suyo'),(2760,'suyas'),(2760,'suyos'),(2760,'tal'),(2760,'tales'),(2760,'también'),(2760,'tampoco'),(2760,'tan'),(2760,'tanta'),(2760,'tanto'),(2760,'tantas'),(2760,'tantos'),(2760,'te'),(2760,'tenéis'),(2760,'tenemos'),(2760,'tener'),(2760,'tengo'),(2760,'ti'),(2760,'tiempo'),(2760,'tiene'),(2760,'tienen'),(2760,'toda'),(2760,'todo'),(2760,'todas'),(2760,'todos'),(2760,'tomar'),(2760,'trabaja'),(2760,'trabajo'),(2760,'trabajáis'),(2760,'trabajamos'),(2760,'trabajan'),(2760,'trabajar'),(2760,'trabajas'),(2760,'tras'),(2760,'tú'),(2760,'tu'),(2760,'tus'),(2760,'tuya'),(2760,'tuyo'),(2760,'tuyas'),(2760,'tuyos'),(2760,'último'),(2760,'ultimo'),(2760,'un'),(2760,'una'),(2760,'uno'),(2760,'unas'),(2760,'unos'),(2760,'usa'),(2760,'usas'),(2760,'usáis'),(2760,'usamos'),(2760,'usan'),(2760,'usar'),(2760,'uso'),(2760,'usted'),(2760,'ustedes'),(2760,'va'),(2760,'van'),(2760,'vais'),(2760,'valor'),(2760,'vamos'),(2760,'varias'),(2760,'varios'),(2760,'vaya'),(2760,'verdadera'),(2760,'vosotras'),(2760,'vosotros'),(2760,'voy'),(2760,'vuestra'),(2760,'vuestro'),(2760,'vuestras'),(2760,'vuestros'),(2760,'y'),(2760,'ya'),(2760,'yo'),(2760,'a'),(2760,'able'),(2760,'about'),(2760,'across'),(2760,'after'),(2760,'all'),(2760,'almost'),(2760,'also'),(2760,'am'),(2760,'among'),(2760,'an'),(2760,'and'),(2760,'any'),(2760,'are'),(2760,'as'),(2760,'at'),(2760,'be'),(2760,'because'),(2760,'been'),(2760,'but'),(2760,'by'),(2760,'can'),(2760,'cannot'),(2760,'could'),(2760,'dear'),(2760,'did'),(2760,'do'),(2760,'does'),(2760,'either'),(2760,'else'),(2760,'ever'),(2760,'every'),(2760,'for'),(2760,'from'),(2760,'get'),(2760,'got'),(2760,'had'),(2760,'has'),(2760,'have'),(2760,'he'),(2760,'her'),(2760,'hers'),(2760,'him'),(2760,'his'),(2760,'how'),(2760,'however'),(2760,'i'),(2760,'if'),(2760,'in'),(2760,'into'),(2760,'is'),(2760,'it'),(2760,'its'),(2760,'just'),(2760,'least'),(2760,'let'),(2760,'like'),(2760,'likely'),(2760,'may'),(2760,'me'),(2760,'might'),(2760,'most'),(2760,'must'),(2760,'my'),(2760,'neither'),(2760,'no'),(2760,'nor'),(2760,'not'),(2760,'of'),(2760,'off'),(2760,'often'),(2760,'on'),(2760,'only'),(2760,'or'),(2760,'other'),(2760,'our'),(2760,'own'),(2760,'rather'),(2760,'said'),(2760,'say'),(2760,'says'),(2760,'she'),(2760,'should'),(2760,'since'),(2760,'so'),(2760,'some'),(2760,'than'),(2760,'that'),(2760,'the'),(2760,'their'),(2760,'them'),(2760,'then'),(2760,'there'),(2760,'these'),(2760,'they'),(2760,'this'),(2760,'tis'),(2760,'to'),(2760,'too'),(2760,'twas'),(2760,'us'),(2760,'wants'),(2760,'was'),(2760,'we'),(2760,'were'),(2760,'what'),(2760,'when'),(2760,'where'),(2760,'which'),(2760,'while'),(2760,'who'),(2760,'whom'),(2760,'why'),(2760,'will'),(2760,'with'),(2760,'would'),(2760,'yet'),(2760,'you'),(2760,'your');
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
INSERT INTO `topic` VALUES (2780,0,'INQUIRY','PREGUNTA'),(2781,0,'REBUTTAL','REFUTACIÓN'),(2782,0,'REGISTRATION','REGISTRO'),(2783,0,'TOPICS','TEMAS'),(2784,0,'OTHER','OTRO');
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
INSERT INTO `tutorial` VALUES (2845,0,60,'Room1','2019-12-10 12:00:00','Summary','TitleTutorial1'),(2858,0,60,'Room1','2019-12-10 12:00:00','Summary','TitleTutorial1'),(2865,0,60,'Room1','2019-12-10 12:00:00','Summary','TitleTutorial1'),(2878,0,60,'Room1','2019-12-10 12:00:00','Summary','TitleTutorial1');
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
INSERT INTO `tutorial_sections` VALUES (2845,2846),(2845,2847),(2858,2859),(2865,2866),(2865,2867),(2878,2879),(2878,2880);
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
INSERT INTO `user_account` VALUES (2747,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(2748,0,'b312ba4ffd5245fa2a1ab819ec0d0347','author1'),(2749,0,'9bd97baef2b853ec00cc3cffd269f679','author2'),(2750,0,'c59a474d5ade296a15ebc40d6c4e8e11','author3'),(2751,0,'6ce19528a40dde9521d97cf7ba264eca','reviewer1'),(2752,0,'2693b57f0f59df94caacefb811e99851','reviewer2'),(2753,0,'315d31e7c8f3a136610aafa220d689be','reviewer3'),(2754,0,'42c63ad66d4dc07ed17753772bef96d6','sponsor1'),(2755,0,'3dc67f80a03324e01b1640f45d107485','sponsor2'),(2756,0,'857a54956061fdc1b88d7722cafe6519','sponsor3'),(2757,0,'5293e8663cbb7c157ff83eeae25177d3','reviewer4'),(2758,0,'86045e9e3b2e615d8d2484dc64b3f408','reviewer5'),(2759,0,'8c14745274135379448d482d5773ee31','reviewer6');
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
INSERT INTO `user_account_authorities` VALUES (2747,'ADMIN'),(2748,'AUTHOR'),(2749,'AUTHOR'),(2750,'AUTHOR'),(2751,'REVIEWER'),(2752,'REVIEWER'),(2753,'REVIEWER'),(2754,'SPONSOR'),(2755,'SPONSOR'),(2756,'SPONSOR'),(2757,'REVIEWER'),(2758,'REVIEWER'),(2759,'REVIEWER');
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

-- Dump completed on 2019-08-31 18:50:10

commit;
