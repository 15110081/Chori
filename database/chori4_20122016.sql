-- MySQL dump 10.13  Distrib 5.6.29, for Linux (x86_64)
--
-- Host: localhost    Database: choritest_db
-- ------------------------------------------------------
-- Server version	5.6.29

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
-- Table structure for table `accessory`
--

DROP TABLE IF EXISTS `accessory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accessory` (
  `ACCESSORYCODE` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ACCESSORYCHORICODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `UNITCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONTAINERUNITCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KIND` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DIMENSION` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL1` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL2` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL3` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL4` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `PERCONTAINER` int(11) DEFAULT NULL,
  `ACCESSORYGROUPCODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ACCESSORYCODE`),
  KEY `FK_ACCESSORY_REFERENCE_CONTAINERUNIT` (`CONTAINERUNITCODE`),
  KEY `FK_ACCESSORY_REFERENCE_ACCESSORYGROUP` (`ACCESSORYGROUPCODE`),
  KEY `FK_ACCESSORY_REFERENCE_USER` (`CREATOR`),
  KEY `FK_ACCESSORY_REFERENCE_UNIT` (`UNITCODE`),
  KEY `FK_ACCESSORY_REFERENCE_COLOR` (`COLORCODE`),
  CONSTRAINT `FK_ACCESSORY_REFERENCE_ACCESSORYGROUP` FOREIGN KEY (`ACCESSORYGROUPCODE`) REFERENCES `accessorygroup` (`ACCESSORYGROUPCODE`),
  CONSTRAINT `FK_ACCESSORY_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  CONSTRAINT `FK_ACCESSORY_REFERENCE_CONTAINERUNIT` FOREIGN KEY (`CONTAINERUNITCODE`) REFERENCES `unit` (`UNITCODE`),
  CONSTRAINT `FK_ACCESSORY_REFERENCE_UNIT` FOREIGN KEY (`UNITCODE`) REFERENCES `unit` (`UNITCODE`),
  CONSTRAINT `FK_ACCESSORY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessory`
--

LOCK TABLES `accessory` WRITE;
/*!40000 ALTER TABLE `accessory` DISABLE KEYS */;
INSERT INTO `accessory` VALUES ('AB red copper','AB red copper','admin','pcs','none','pcs','Rivet Rudholm','External','18L','Packing',NULL,NULL,NULL,NULL,'2016-12-01 19:28:00',NULL,'Rivet','Active'),('AC Silver','AC Silver','admin','pcs','none','pcs','AC Silver','External','','Packing',NULL,NULL,NULL,NULL,'2016-12-01 19:35:52',NULL,'Rivet','Active'),('button','B2456','admin','pcs','WHT','grs','button','External','18L','Manufacturing',NULL,NULL,NULL,NULL,'2016-12-01 18:17:24',144,'button','Active'),('Snap',NULL,'admin','pcs','Color 1','pcs','Snap YKK','Internal','3x2','Packing','','',NULL,NULL,'2016-07-07 00:00:00',0,'Snap','Active'),('Super dry','','admin','Gr','CB','Gr','Super dry','External','','Packing',NULL,NULL,NULL,NULL,'2016-12-01 19:23:37',NULL,'SUPER DRY','Active'),('zipper','#280','admin','pcs','CYLW','pcs','zipper','External','30cm','Manufacturing',NULL,NULL,NULL,NULL,'2016-12-01 18:19:57',1,'zipper','Active');
/*!40000 ALTER TABLE `accessory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accessoryconsumption`
--

DROP TABLE IF EXISTS `accessoryconsumption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accessoryconsumption` (
  `FACTORYCODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ACCESSORYCODE` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CONSUMPTION` float DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FACTORYCODE`,`ACCESSORYCODE`),
  KEY `FK_ACCESSORYCONSUMPTION_REFERENCE_USER` (`CREATOR`),
  KEY `FK_ACCESSORYCONSUMPTION_REFERENCE_ACCESSOR` (`ACCESSORYCODE`),
  CONSTRAINT `FK_ACCESSORYCONSUMPTION_REFERENCE_ACCESSOR` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  CONSTRAINT `FK_ACCESSORYCONSUMPTION_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  CONSTRAINT `FK_ACCESSORYCONSUMPTION_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessoryconsumption`
--

LOCK TABLES `accessoryconsumption` WRITE;
/*!40000 ALTER TABLE `accessoryconsumption` DISABLE KEYS */;
INSERT INTO `accessoryconsumption` VALUES ('DHG','AB red copper','admin',0,'2016-12-01 19:28:01'),('DHG','AC Silver','admin',0,'2016-12-01 19:35:53'),('DHG','button','admin',2,'2016-12-01 18:32:51'),('DHG','Snap','admin',2,'2016-12-01 18:32:51'),('DHG','Super dry','admin',0,'2016-12-01 19:23:37'),('DHG','zipper','admin',2,'2016-12-01 18:32:51'),('MC','AB red copper','admin',0,'2016-12-01 19:28:01'),('MC','AC Silver','admin',0,'2016-12-01 19:35:53'),('MC','button','admin',2,'2016-12-01 18:31:36'),('MC','Snap','admin',2,'2016-12-01 18:31:36'),('MC','Super dry','admin',0,'2016-12-01 19:23:37'),('MC','zipper','admin',2,'2016-12-01 18:31:36');
/*!40000 ALTER TABLE `accessoryconsumption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accessoryform`
--

DROP TABLE IF EXISTS `accessoryform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accessoryform` (
  `ACCESSORYFORMCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ACCESSORYFORMCODE`),
  KEY `FK_ACCESSORYFORM_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_ACCESSORYFORM_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessoryform`
--

LOCK TABLES `accessoryform` WRITE;
/*!40000 ALTER TABLE `accessoryform` DISABLE KEYS */;
/*!40000 ALTER TABLE `accessoryform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accessoryformdetail`
--

DROP TABLE IF EXISTS `accessoryformdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accessoryformdetail` (
  `ACCESSORYCODE` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ACCESSORYFORMCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ACCESSORYCODE`,`ACCESSORYFORMCODE`),
  KEY `FK_ACCESSORYFORMDETAIL_REFERENCE_ACCESSORFORM` (`ACCESSORYFORMCODE`),
  KEY `FK_ACCESSORYFORMDETAIL_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_ACCESSORYFORMDETAIL_REFERENCE_ACCESSORFORM` FOREIGN KEY (`ACCESSORYFORMCODE`) REFERENCES `accessoryform` (`ACCESSORYFORMCODE`),
  CONSTRAINT `FK_ACCESSORYFORMDETAIL_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  CONSTRAINT `FK_ACCESSORYFORMDETAIL_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessoryformdetail`
--

LOCK TABLES `accessoryformdetail` WRITE;
/*!40000 ALTER TABLE `accessoryformdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `accessoryformdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accessorygroup`
--

DROP TABLE IF EXISTS `accessorygroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accessorygroup` (
  `ACCESSORYGROUPCODE` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ACCESSORYGROUPCODE`),
  KEY `FK_ACCESSORYGROUP_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_ACCESSORYGROUP_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessorygroup`
--

LOCK TABLES `accessorygroup` WRITE;
/*!40000 ALTER TABLE `accessorygroup` DISABLE KEYS */;
INSERT INTO `accessorygroup` VALUES 
('button','admin','poly button','2016-12-01 18:14:40'),
('non woven','admin','non woven','2016-12-01 18:14:20'),
('Rivet','admin','','2016-12-01 19:14:00'),
('Snap','admin','Snap, snap button, rivet','2016-07-20 00:00:00'),
('STICKER','admin','STICKER','2016-12-01 18:53:16'),
('SUPER DRY','admin','SUPER DRY','2016-12-01 18:54:01'),
('zipper','admin','zipper','2016-12-01 18:14:04');
/*!40000 ALTER TABLE `accessorygroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accessoryordersignature`
--

DROP TABLE IF EXISTS `accessoryordersignature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accessoryordersignature` (
  `ACCORDERSIGNCODE` int(11) NOT NULL AUTO_INCREMENT,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEXT1` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEXT2` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ACCORDERSIGNCODE`),
  KEY `FK_ACCESSORYORDERSIGNATURE_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_ACCESSORYORDERSIGNATURE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessoryordersignature`
--

LOCK TABLES `accessoryordersignature` WRITE;
/*!40000 ALTER TABLE `accessoryordersignature` DISABLE KEYS */;
/*!40000 ALTER TABLE `accessoryordersignature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accessoryprice`
--

DROP TABLE IF EXISTS `accessoryprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accessoryprice` (
  `ACCESSORYPRICECODE` int(11) NOT NULL AUTO_INCREMENT,
  `ACCESSORYCODE` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ACCSUPPLIERCODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `FROMDATE` date DEFAULT NULL,
  `TODATE` date DEFAULT NULL,
  `UNITPRICEPERUNIT` float(20,4) DEFAULT NULL,
  `CURRENCYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ACCESSORYPRICECODE`),
  KEY `FK_ACCESSORYPRICE_REFERENCE_ACCESSORY` (`ACCESSORYCODE`),
  KEY `FK_ACCESSORYPRICE_REFERENCE_ACCESSORYSUPPLIER` (`ACCSUPPLIERCODE`),
  KEY `FK_ACCESSORYPRICE_REFERENCE_CURRENCY` (`CURRENCYCODE`),
  KEY `FK_ACCESSORYPRICE_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_ACCESSORYPRICE_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  CONSTRAINT `FK_ACCESSORYPRICE_REFERENCE_ACCESSORYSUPPLIER` FOREIGN KEY (`ACCSUPPLIERCODE`) REFERENCES `accessorysupplier` (`ACCSUPPLIERCODE`),
  CONSTRAINT `FK_ACCESSORYPRICE_REFERENCE_CURRENCY` FOREIGN KEY (`CURRENCYCODE`) REFERENCES `currency` (`CURRENCYCODE`),
  CONSTRAINT `FK_ACCESSORYPRICE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessoryprice`
--

LOCK TABLES `accessoryprice` WRITE;
/*!40000 ALTER TABLE `accessoryprice` DISABLE KEYS */;
INSERT INTO `accessoryprice` VALUES 
(1,'Snap','YKK','2016-10-31 00:00:00','2016-11-30 00:01:00',0.035,'USD','admin','2016-10-21 17:41:22',''),
(12,'Snap','YKK','2016-12-01 00:01:00','2016-12-31 23:59:59',0.100,'USD','admin','2016-12-01 18:21:31','');
/*!40000 ALTER TABLE `accessoryprice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accessorysupplier`
--

DROP TABLE IF EXISTS `accessorysupplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accessorysupplier` (
  `ACCSUPPLIERCODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHORTNAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ACCSUPPLIERCODE`),
  KEY `FK_ACCESSORYSUPPLIER_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_ACCESSORYSUPPLIER_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessorysupplier`
--

LOCK TABLES `accessorysupplier` WRITE;
/*!40000 ALTER TABLE `accessorysupplier` DISABLE KEYS */;
INSERT INTO `accessorysupplier` VALUES 
('YKK','admin','YKK','YKK Vietnam Co. Ltd','1/. 7th floor, TMS Bldg, 172 Hai Ba Trung st, Dakao Ward, Dist 1, HCMC 2/. 4th Floor, AB Tower, 76 Le Lai St., Ben Thanh Ward, Dist. 1, HCMC, Viet Nam ','0084-8-38303124','0084-8-38303139','','','Active','2016-10-07 10:26:18','');
/*!40000 ALTER TABLE `accessorysupplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accessorysuppliercontact`
--

DROP TABLE IF EXISTS `accessorysuppliercontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accessorysuppliercontact` (
  `ACCSUPPLIERCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT,
  `ACCSUPPLIERCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ACCSUPPLIERCONTACTCODE`),
  KEY `FK_ACCESSORYSUPPLIERCONTACT_REFERENCE_USER` (`CREATOR`),
  KEY `FK_ACCESSORYSUPPLIERCONTACT_REFERENCE_ACCESSORYSUPPLIER` (`ACCSUPPLIERCODE`),
  CONSTRAINT `FK_ACCESSORYSUPPLIERCONTACT_REFERENCE_ACCESSORYSUPPLIER` FOREIGN KEY (`ACCSUPPLIERCODE`) REFERENCES `accessorysupplier` (`ACCSUPPLIERCODE`),
  CONSTRAINT `FK_ACCESSORYSUPPLIERCONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accessorysuppliercontact`
--

LOCK TABLES `accessorysuppliercontact` WRITE;
/*!40000 ALTER TABLE `accessorysuppliercontact` DISABLE KEYS */;
INSERT INTO `accessorysuppliercontact` VALUES (1,'YKK','admin','Ms Kim Anh','KimAnh@ykk.com.vn','2016-07-20 00:00:00',''),
(2,'YKK','admin','Mr Rachel (Kim Loan)','KimLoan@ykk.com.vn','2016-07-20 00:00:00','0933986634');
/*!40000 ALTER TABLE `accessorysuppliercontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `AGENTCODE` int(11) NOT NULL AUTO_INCREMENT,
  `SHORTNAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`AGENTCODE`),
  KEY `FK_AGENT_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_AGENT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES (1,'Chori Agent VietNam','Chori Agent in VietNam','admin','Thai','083242232','1231253543','143124','Active','2016-07-20 00:00:00',''),(2,'Chori Agent Thailand','Chori Agent in Thailand','admin','VietNam','083242232','1231253543','143124','Active','2016-07-20 00:00:00','');
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agentcontact`
--

DROP TABLE IF EXISTS `agentcontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agentcontact` (
  `AGENTCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT,
  `AGENTCODE` int(11) NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` timestamp NULL DEFAULT NULL,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`AGENTCONTACTCODE`),
  KEY `FK_AGENTCONTACT_REFERENCE_AGENT` (`AGENTCODE`),
  KEY `FK_AGENTCONTACT_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_AGENTCONTACT_REFERENCE_AGENT` FOREIGN KEY (`AGENTCODE`) REFERENCES `agent` (`AGENTCODE`),
  CONSTRAINT `FK_AGENTCONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agentcontact`
--

LOCK TABLES `agentcontact` WRITE;
/*!40000 ALTER TABLE `agentcontact` DISABLE KEYS */;
/*!40000 ALTER TABLE `agentcontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `BRANDCODE` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMERCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BRANDNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`BRANDCODE`),
  KEY `FK_BRAND_REFERENCE_USER` (`CREATOR`),
  KEY `FK_BRAND_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  CONSTRAINT `FK_BRAND_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  CONSTRAINT `FK_BRAND_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'AL HARAMAIN','admin','AL HARAMAIN','2016-10-01 08:27:51');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color`
--

DROP TABLE IF EXISTS `color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `color` (
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`COLORCODE`),
  KEY `FK_COLOR_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_COLOR_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` VALUES ('CB','admin','Sea Blue','2016-07-20 00:00:00'),('CBLK','admin','Black','2016-07-20 00:00:00'),('Color 1','admin','Color 1','2016-11-14 11:29:43'),('CYLW','admin','Yellow','2016-07-20 00:00:00'),('none','admin','no color','2016-12-01 19:23:48'),('Trans-gld','admin','Transparent/gold','2016-12-01 18:59:32'),('Trans-Sil','admin','Transparent/Silver','2016-12-01 18:59:10'),('WHT','admin','White','2016-07-20 00:00:00');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctnrtype`
--

DROP TABLE IF EXISTS `ctnrtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctnrtype` (
  `CTNRCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CTNRCODE`),
  KEY `FK_CTNRTYPE_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_CTNRTYPE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctnrtype`
--

LOCK TABLES `ctnrtype` WRITE;
/*!40000 ALTER TABLE `ctnrtype` DISABLE KEYS */;
INSERT INTO `ctnrtype` VALUES ('FA5531','admin','Container FA5531','2016-07-20 00:00:00'),('FS5621','admin','Container FS5621','2016-07-20 00:00:00');
/*!40000 ALTER TABLE `ctnrtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `CURRENCYCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CURRENCYCODE`),
  KEY `FK_CURRENCY_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_CURRENCY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES ('USD','admin','US Dollar','2016-07-20 00:00:00'),('VND','admin','VietnamDong','2016-07-20 00:00:00');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currencyexchange`
--

DROP TABLE IF EXISTS `currencyexchange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currencyexchange` (
  `CURRENCYEXCODE` int(11) NOT NULL AUTO_INCREMENT,
  `CURRENCYCODESOURCE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CURRENCYCODEDESTINATION` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AMOUNT` double DEFAULT NULL,
  `EXCHANGEDATE` date DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CURRENCYEXCODE`),
  KEY `FK_CUREXSOURCE_REFERENCE_CURRENCY` (`CURRENCYCODESOURCE`),
  KEY `FK_CURREXDESTINATION_REFERENCE_CURRENCY` (`CURRENCYCODEDESTINATION`),
  CONSTRAINT `FK_CUREXSOURCE_REFERENCE_CURRENCY` FOREIGN KEY (`CURRENCYCODESOURCE`) REFERENCES `currency` (`CURRENCYCODE`),
  CONSTRAINT `FK_CURREXDESTINATION_REFERENCE_CURRENCY` FOREIGN KEY (`CURRENCYCODEDESTINATION`) REFERENCES `currency` (`CURRENCYCODE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currencyexchange`
--

LOCK TABLES `currencyexchange` WRITE;
/*!40000 ALTER TABLE `currencyexchange` DISABLE KEYS */;
INSERT INTO `currencyexchange` VALUES 
(1,'VND','USD',0.223,'2016-11-20','2016-11-20 00:00:00'),
(2,'USD','VND',22.500,'2016-11-20','2016-11-20 00:00:00');
/*!40000 ALTER TABLE `currencyexchange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `CUSTOMERCODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHORTNAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`CUSTOMERCODE`),
  KEY `FK_CUSTOMER_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_CUSTOMER_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('AL HARAMAIN','admin','AL HARAMAIN','AL HARAMAIN','ABDUL RAHEEM HUSSAIN TURDI EST, AL FAISALIYAH AL SHARAFIYAH STORE AREA, TAIF, SAUDI ARABIA','96627504543','99627504593','','Active','2016-10-27 13:47:44',''),('Al Shiaka','admin','Al Shiaka','Al Shiaka','DUNIA ALSWAF TRADING LLC P.O.BOX 12238 JEDDAH , 21473 K.S.A','6391334','6391335','','Active','2016-10-07 15:54:36',''),('Ezary','admin','Ezary Abdulgani','Ezary Abdulgani','ALAMOUDI TARDING CO. P.O. BOX 19297 JEDDAH 21435 SAUDI ARABIA','012-6446050','012-6430465','','Active','2016-09-22 15:22:02','Ezary Lab coat'),('Mr. Mustafa','admin','Mr. Mustafa','Mr. Mustafa','	OFFICE 417,FOURTH FLOOR,HAMIDAH, COMPLEX NAIF ROAD,DUBAI,UAE','00971-4-2366799','00971-4-2731199','','Active','2016-10-07 15:54:36','');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customeraccountinformation`
--

DROP TABLE IF EXISTS `customeraccountinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customeraccountinformation` (
  `CUSTOMERACCOUNTINFOCODE` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMERCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANKNAME` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANKBRANCH` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCOUNTNUMBER` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SWIFTCODE` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CUSTOMERACCOUNTINFOCODE`),
  KEY `FK_CUSTOMERACCOUNTINFO_REFERENCE_USER` (`CREATOR`),
  KEY `FK_CUSTOMERACCOUNTINFO_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  CONSTRAINT `FK_CUSTOMERACCOUNTINFO_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  CONSTRAINT `FK_CUSTOMERACCOUNTINFO_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customeraccountinformation`
--

LOCK TABLES `customeraccountinformation` WRITE;
/*!40000 ALTER TABLE `customeraccountinformation` DISABLE KEYS */;
/*!40000 ALTER TABLE `customeraccountinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customercontact`
--

DROP TABLE IF EXISTS `customercontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customercontact` (
  `CUSCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMERCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` timestamp NULL DEFAULT NULL,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`CUSCONTACTCODE`),
  KEY `FK_CUSTOMER_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  KEY `FK_CUSTOMERCONTACT_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_CUSTOMERCONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`),
  CONSTRAINT `FK_CUSTOMER_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customercontact`
--

LOCK TABLES `customercontact` WRITE;
/*!40000 ALTER TABLE `customercontact` DISABLE KEYS */;
INSERT INTO `customercontact` VALUES (1,'AL HARAMAIN','admin','Mr. Ibrahim','raqem1979@gmail.com','2016-07-19 03:00:00','966530362036'),(2,'Ezary','admin','Mr Rashad','AMOUDIHA@HOTMAIL.COM','2016-07-19 03:00:00','0553038224'),(3,'Mr. Mustafa','admin','Mr. Mustafa','chorihcm17@gmail.com','2016-09-22 01:17:37','00971-4-2366799');
/*!40000 ALTER TABLE `customercontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination`
--

DROP TABLE IF EXISTS `destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `destination` (
  `DESTINATIONCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`DESTINATIONCODE`),
  KEY `FK_DESTINAT_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_DESTINAT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination`
--

LOCK TABLES `destination` WRITE;
/*!40000 ALTER TABLE `destination` DISABLE KEYS */;
INSERT INTO `destination` VALUES ('Jebel Ali','admin','United Arab Emirates','2016-10-07 16:07:10'),('Jeddah','admin','Saudi Arabia','2016-07-20 00:00:00');
/*!40000 ALTER TABLE `destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estimatetime`
--

DROP TABLE IF EXISTS `estimatetime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estimatetime` (
  `ESTIMATETIMECODE` int(11) NOT NULL AUTO_INCREMENT,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PICONPLETION` int(11) DEFAULT NULL,
  `PACKINGACCDELV` int(11) DEFAULT NULL,
  `MANUACCDELV` int(11) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ESTIMATETIMECODE`),
  KEY `FK_ESTIMATETIME_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_ESTIMATETIME_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estimatetime`
--

LOCK TABLES `estimatetime` WRITE;
/*!40000 ALTER TABLE `estimatetime` DISABLE KEYS */;
INSERT INTO `estimatetime` VALUES (1,'admin',7,7,7,'2016-07-20 00:00:00');
/*!40000 ALTER TABLE `estimatetime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `externalaccessorystock`
--

DROP TABLE IF EXISTS `externalaccessorystock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `externalaccessorystock` (
  `EXTERNALACCESSORYSTOCKCODE` int(11) NOT NULL AUTO_INCREMENT,
  `ORDERSHEETNO` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSORYCODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AVAILABLEQTY` float DEFAULT NULL,
  PRIMARY KEY (`EXTERNALACCESSORYSTOCKCODE`),
  KEY `FK_EXTERNALACCSTOCK_REFERENCE_ORDEREXTERNALACCESSORY` (`ORDERSHEETNO`),
  KEY `FK_EXTERNALACCSTOCK_REFERENCE_ACCESSORY` (`ACCESSORYCODE`),
  KEY `FK_EXTERNALACCSTOCK_REFERENCE_FACTORY` (`FACTORYCODE`),
  CONSTRAINT `FK_EXTERNALACCSTOCK_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  CONSTRAINT `FK_EXTERNALACCSTOCK_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  CONSTRAINT `FK_EXTERNALACCSTOCK_REFERENCE_ORDEREXTERNALACCESSORY` FOREIGN KEY (`ORDERSHEETNO`) REFERENCES `orderexternalaccessory` (`ORDERSHEETNO`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `externalaccessorystock`
--

LOCK TABLES `externalaccessorystock` WRITE;
/*!40000 ALTER TABLE `externalaccessorystock` DISABLE KEYS */;
/*!40000 ALTER TABLE `externalaccessorystock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabricinformation`
--

DROP TABLE IF EXISTS `fabricinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fabricinformation` (
  `FABRICNO` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `FABRICITEM` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CUSTOMERCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FABRICSUPCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CURRENCYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WIDTHCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ISCHORI` tinyint(1) DEFAULT NULL,
  `COMPONENT` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ESTIMATEDELVDATE` datetime DEFAULT NULL,
  `ACTUALDELVDATE` datetime DEFAULT NULL,
  `FABRICINVOICENO` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FABRICIMGURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `AGENTCODE` int(11) DEFAULT NULL,
  `VOUCHERRECEIVEDDATE` datetime DEFAULT NULL,
  `VOUCHERSENTDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`FABRICNO`),
  KEY `FK_FABRICINFORMATION_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  KEY `FK_FABRICINFORMATION_REFERENCE_FABRICSUPPLIER` (`FABRICSUPCODE`),
  KEY `FK_FABRICINFORMATION_REFERENCE_FACTORY` (`FACTORYCODE`),
  KEY `FK_FABRICINFORMATION_REFERENCE_CURRENCY` (`CURRENCYCODE`),
  KEY `FK_FABRICINFORMATION_REFERENCE_WIDTH` (`WIDTHCODE`),
  KEY `FK_FABRICINFORMATION_REFERENCE_USER` (`CREATOR`),
  KEY `FK_FABRICINFORMATION_REFERENCE_AGENT` (`AGENTCODE`),
  CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_AGENT` FOREIGN KEY (`AGENTCODE`) REFERENCES `agent` (`AGENTCODE`),
  CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_CURRENCY` FOREIGN KEY (`CURRENCYCODE`) REFERENCES `currency` (`CURRENCYCODE`),
  CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_FABRICSUPPLIER` FOREIGN KEY (`FABRICSUPCODE`) REFERENCES `fabricsupplier` (`FABRICSUPCODE`),
  CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`),
  CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_WIDTH` FOREIGN KEY (`WIDTHCODE`) REFERENCES `width` (`WIDTHCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabricinformation`
--

LOCK TABLES `fabricinformation` WRITE;
/*!40000 ALTER TABLE `fabricinformation` DISABLE KEYS */;
/*!40000 ALTER TABLE `fabricinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabricinformationdetail`
--

DROP TABLE IF EXISTS `fabricinformationdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fabricinformationdetail` (
  `FABRICNO` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `UNITPRICE` float(20,4) DEFAULT NULL,
  `YARDINBL` double DEFAULT NULL,
  `IMGURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FABRICNO`,`COLORCODE`),
  KEY `FK_FABRICINFORMATIONDEATIL_REFERENCE_COLOR` (`COLORCODE`),
  KEY `FK_FABRICINFORMATIONDETAIL_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_FABRICINFORMATIONDEATIL_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  CONSTRAINT `FK_FABRICINFORMATIONDETAIL_REFERENCE_FABRICINFORMATION` FOREIGN KEY (`FABRICNO`) REFERENCES `fabricinformation` (`FABRICNO`),
  CONSTRAINT `FK_FABRICINFORMATIONDETAIL_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabricinformationdetail`
--

LOCK TABLES `fabricinformationdetail` WRITE;
/*!40000 ALTER TABLE `fabricinformationdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `fabricinformationdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabricsupplier`
--

DROP TABLE IF EXISTS `fabricsupplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fabricsupplier` (
  `FABRICSUPCODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHORTNAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`FABRICSUPCODE`),
  KEY `FK_FABRICSUPPLIER_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_FABRICSUPPLIER_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabricsupplier`
--

LOCK TABLES `fabricsupplier` WRITE;
/*!40000 ALTER TABLE `fabricsupplier` DISABLE KEYS */;
INSERT INTO `fabricsupplier` VALUES ('DAELIM','admin','DAELIM','DAELIM TEXTILE CO LTD.','RM NO 304, SHAMS B/D, 9-1, 3-GA, HOEHYUN -DONG, JUNG-GU, SEOUL, KOREA','82-2-7762593','82-2-7785203','','Active','2016-10-27 13:52:51',''),('TAW','admin','TAEKWANG','TAEKWANG INDUSTRIAL CO., LTD.','TAEKWANG INDUSTRIAL CO., LTD.162-1, JANGCHUNG-DONG 20GA JUNG-GU SEOUL 100-392, KOREA','2-3406-03300','2-2273 9159','','Active','2016-09-22 15:39:10',''),('TTF','admin','TTF','Taiwan Taffeta Fabric Co. Ltd','8/F 70-1, Hsi Ning North Road Taipei 103 Taiwan','00886-2-25568282','00886-2-25567948','','Active','2016-07-20 00:00:00','');
/*!40000 ALTER TABLE `fabricsupplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabricsuppliercontact`
--

DROP TABLE IF EXISTS `fabricsuppliercontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fabricsuppliercontact` (
  `FABRICSUPPLIERCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT,
  `FABRICSUPPLIERCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`FABRICSUPPLIERCONTACTCODE`),
  KEY `FK_FABRICSUPPLIERCONTACT_REFERENCE_USER` (`CREATOR`),
  KEY `FK_FABRICSUPPLIERCONTACT_REFERENCE_FABRICSU` (`FABRICSUPPLIERCODE`),
  CONSTRAINT `FK_FABRICSUPPLIERCONTACT_REFERENCE_FABRICSU` FOREIGN KEY (`FABRICSUPPLIERCODE`) REFERENCES `fabricsupplier` (`FABRICSUPCODE`),
  CONSTRAINT `FK_FABRICSUPPLIERCONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabricsuppliercontact`
--

LOCK TABLES `fabricsuppliercontact` WRITE;
/*!40000 ALTER TABLE `fabricsuppliercontact` DISABLE KEYS */;
INSERT INTO `fabricsuppliercontact` VALUES (1,'DAELIM','admin','MR. K.O KIM','korobetex@gmail.com','2016-07-20 00:00:00',''),(2,'DAELIM','admin','MR. J.Y KIM','jinokim81@gmail.com','2016-07-20 00:00:00',''),(3,'TTF','admin','Mr Phil','phil@ttfco.com','2016-07-20 00:00:00',''),(4,'TAW','admin','Mr Park/ MR. SHIM','acetex@taekwang.co.kr','2016-09-22 15:30:59','');
/*!40000 ALTER TABLE `fabricsuppliercontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factory`
--

DROP TABLE IF EXISTS `factory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factory` (
  `FACTORYCODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHORTNAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`FACTORYCODE`),
  KEY `FK_FACTORY_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_FACTORY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factory`
--

LOCK TABLES `factory` WRITE;
/*!40000 ALTER TABLE `factory` DISABLE KEYS */;
INSERT INTO `factory` VALUES ('DHG','admin','Dung Hanh','Dung Hanh Garment Co. Ltd','1/8D Tien Lan Hamlet, Ba Diem Area, Hoc Mon Dist, Ho Chi Minh city, Vietnam','0084-8-35901632','0084-8-35901633','','Active','2016-09-22 15:26:17',''),('DV','admin','DAI VIET','DAI VIET GARMENT JOINT STOCK COMPANY','62 Tan Thanh St, Tan Thanh ward, Tan Phu dist, Ho Chi Minh city','3 8496016','38429860','','Active','2016-07-20 00:00:00',''),('MC','admin','MINH CHAU','MINH CHAU','MINH CHAU GARMENT CO.,LTD 41/3 TRUNG CHANH 2 HAMLET, TRUNG CHANH COMMUNE, HOC MON DIST, HCMC, VIETNAM','62838777','62838878','','Active','2016-07-20 00:00:00',''),('TV','admin','TAN VIET','TAN VIET FACTORY','Viet Nam','213214','1231253543','','Active','2016-07-20 00:00:00','');
/*!40000 ALTER TABLE `factory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factoryaccountinformation`
--

DROP TABLE IF EXISTS `factoryaccountinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factoryaccountinformation` (
  `FACTORYACCOUNTINFOCODE` int(11) NOT NULL AUTO_INCREMENT,
  `FACTORYCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANKNAME` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANKBRANCH` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCOUNTNUMBER` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SWIFTCODE` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FACTORYACCOUNTINFOCODE`),
  KEY `FK_FACTORYACCOUNTINFO_REFERENCE_USER` (`CREATOR`),
  KEY `FK_FACTORYACCOUNTINFO_REFERENCE_FACTORY` (`FACTORYCODE`),
  CONSTRAINT `FK_FACTORYACCOUNTINFO_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  CONSTRAINT `FK_FACTORYACCOUNTINFO_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factoryaccountinformation`
--

LOCK TABLES `factoryaccountinformation` WRITE;
/*!40000 ALTER TABLE `factoryaccountinformation` DISABLE KEYS */;
/*!40000 ALTER TABLE `factoryaccountinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factorycontact`
--

DROP TABLE IF EXISTS `factorycontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factorycontact` (
  `FACTORYCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT,
  `FACTORYCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`FACTORYCONTACTCODE`),
  KEY `FK_FACTORYCONTACT_REFERENCE_USER` (`CREATOR`),
  KEY `FK_FACTORYCONTACT_REFERENCE_FACTORY` (`FACTORYCODE`),
  CONSTRAINT `FK_FACTORYCONTACT_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  CONSTRAINT `FK_FACTORYCONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factorycontact`
--

LOCK TABLES `factorycontact` WRITE;
/*!40000 ALTER TABLE `factorycontact` DISABLE KEYS */;
INSERT INTO `factorycontact` VALUES (1,'MC','admin','Ms Thuy','msthuy@mcm.vn','2016-08-17 00:00:00','0916999068'),(2,'MC','admin','Ms Hai','hai@mcm.vn','2016-08-17 00:00:00','0916666106'),(3,'DHG','admin','Ms Hanh','v.k.hanh@dunghanhgarment.com','2016-08-17 00:00:00','0913813265'),(4,'DHG','admin','Ms Khanh Phuong','k.phuong@dunghanhgarment.com','2016-08-17 00:00:00','0918037488'),(5,'DV','admin','Dai Viet','daiviet@col.com','2016-08-21 00:00:00','');
/*!40000 ALTER TABLE `factorycontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fpi`
--

DROP TABLE IF EXISTS `fpi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fpi` (
  `FPICODE` int(11) NOT NULL AUTO_INCREMENT,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RECEIVEDDATE` datetime DEFAULT NULL,
  `CUSTOMERCONFIRMATIONSTATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`FPICODE`),
  KEY `FK_FPI_REFERENCE_USER` (`CREATOR`),
  KEY `FK_FPI_REFERENCE_PI` (`LOTNUMBER`),
  CONSTRAINT `FK_FPI_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`),
  CONSTRAINT `FK_FPI_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fpi`
--

LOCK TABLES `fpi` WRITE;
/*!40000 ALTER TABLE `fpi` DISABLE KEYS */;
/*!40000 ALTER TABLE `fpi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fpidetail`
--

DROP TABLE IF EXISTS `fpidetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fpidetail` (
  `FPIDETAILCODE` int(11) NOT NULL AUTO_INCREMENT,
  `FPICODE` int(11) DEFAULT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `FPIVALUE` int(11) DEFAULT NULL,
  PRIMARY KEY (`FPIDETAILCODE`),
  KEY `FK_FPIDETAI_REFERENCE_FPI` (`FPICODE`),
  KEY `FK_FPIDETAI_REFERENCE_COLOR` (`COLORCODE`),
  KEY `FK_FPIDETAI_REFERENCE_GARMENTS` (`GARMENTSTYLECODE`),
  KEY `FK_FPIDETAI_REFERENCE_SIZE` (`SIZECODE`),
  CONSTRAINT `FK_FPIDETAI_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  CONSTRAINT `FK_FPIDETAI_REFERENCE_FPI` FOREIGN KEY (`FPICODE`) REFERENCES `fpi` (`FPICODE`),
  CONSTRAINT `FK_FPIDETAI_REFERENCE_GARMENTS` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  CONSTRAINT `FK_FPIDETAI_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fpidetail`
--

LOCK TABLES `fpidetail` WRITE;
/*!40000 ALTER TABLE `fpidetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `fpidetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `function`
--

DROP TABLE IF EXISTS `function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `function` (
  `FUNCTIONID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `FUNCTIONNAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`FUNCTIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `function`
--

LOCK TABLES `function` WRITE;
/*!40000 ALTER TABLE `function` DISABLE KEYS */;
INSERT INTO `function` VALUES ('Accessory Consumption','Access Wasted Percentage Management Page','Access Wasted Percentage Management Page'),('Accessory Group','Access Accessory Group Management Page','Access Accessory Group Management Page'),('Accessory Information','Access Accessory Information Management Page','Access Accessory Information Management Page'),('Accessory Order Signature','Access Accessory Signature Management Page','Access Accessory Signature Management Page'),('Accessory Price','Access Accessory Price Management Page','Access Accessory Price Management Page'),('Accessory Supplier','Access Accessory Supplier Management Page','Access Accessory Supplier Management Page'),('Agent Management','Access Chori Office Management Page','Access Chori OfficeManagement Page'),('Color Management','Access Color Management Page','Access Color Management Page'),('Container Type','Access Container Type Management Page','Access Container Type Management Page'),('Currency Exchange','Access Currency Exchange Management Page','Access Currency Exchange Management Page'),('Currency Management','Access Currency Management Page','Access Currency Management Page'),('Customer Management','Access Customer Management Page','Access Customer Management Page'),('Destination Management','Access Destination Management Page','Access Destination Management Page'),('ETOC Management','Access ETOC Management Page','Access ETOC Management Page'),('Fabric Information','Access Fabric Information Management Page','Access Fabric Information Management Page'),('Fabric Supplier','Access Fabric Supplier Management Page','Access Fabric Supplier Management Page'),('Factory Management','Access Factory Management Page','Access Factory Management Page'),('FPI Information','Access FPI Information Management Page','Access FPI Information Management Page'),('Garment Consumption','Access Garment Consumption Management Page','Access Garment Consumption Management Page'),('Garment Kind','Access Garment Kind Management Page','Access Garment Kind Management Page'),('Garment Style','Access Garment Style Management Page','Access Garment Style Management Page'),('Groups & Functions','Access Groups & Functions Management Page','Access Groups & Functions Management Page'),('Order General Accessories','Access Order General Accessories Page','Access Order General Accessories Page'),('Packing Guide','Access Packing Guide Management','Access Packing Guide Management'),('PI Information','Access PI Information Management Page','Access PI Information Management Page'),('RFPI Information','Access RFPI Information Management Page','Access RFPI Information Management Page'),('Shipping Line','Access Shipping Line Management Page','Access Shipping Line Management Page'),('Size Management','Access Size Management Page','Access Size Management Page'),('Type Management','Access Size Group Management Page','Access Size Group Management Page'),('Unit Management','Access Unit Management Page','Access Unit Management Page'),('User Management','Access User Management Page','Access User Management Page'),('Width Management','Access Width Management Page','Access Width Management Page');
/*!40000 ALTER TABLE `function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garmentconsumption`
--

DROP TABLE IF EXISTS `garmentconsumption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `garmentconsumption` (
  `GARMENTCONSUMPTIONCODE` int(11) NOT NULL AUTO_INCREMENT,
  `GARMENTSTYLECODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CUSTOMERCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`GARMENTCONSUMPTIONCODE`),
  KEY `FK_GARMENTCONSUMPTION_REFERENCE_SIZE` (`SIZECODE`),
  KEY `FK_GARMENTCONSUMPTION_REFERENCE_GARMENTS` (`GARMENTSTYLECODE`),
  KEY `FK_GARMENTCONSUMPTION_REFERENCE_USER` (`CREATOR`),
  KEY `FK_GARMENTCONSUMPTION_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  CONSTRAINT `FK_GARMENTCONSUMPTION_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  CONSTRAINT `FK_GARMENTCONSUMPTION_REFERENCE_GARMENTS` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  CONSTRAINT `FK_GARMENTCONSUMPTION_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`),
  CONSTRAINT `FK_GARMENTCONSUMPTION_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garmentconsumption`
--

LOCK TABLES `garmentconsumption` WRITE;
/*!40000 ALTER TABLE `garmentconsumption` DISABLE KEYS */;
/*!40000 ALTER TABLE `garmentconsumption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garmentconsumptiondetail`
--

DROP TABLE IF EXISTS `garmentconsumptiondetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `garmentconsumptiondetail` (
  `GARMENTCONSUMPTIONDETAILCODE` int(11) NOT NULL AUTO_INCREMENT,
  `GARMENTCONSUMPTIONCODE` int(11) DEFAULT NULL,
  `WIDTHCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONVALUE` float DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`GARMENTCONSUMPTIONDETAILCODE`),
  KEY `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_GARMENTC` (`GARMENTCONSUMPTIONCODE`),
  KEY `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_WIDTH` (`WIDTHCODE`),
  KEY `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_GARMENTC` FOREIGN KEY (`GARMENTCONSUMPTIONCODE`) REFERENCES `garmentconsumption` (`GARMENTCONSUMPTIONCODE`),
  CONSTRAINT `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`),
  CONSTRAINT `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_WIDTH` FOREIGN KEY (`WIDTHCODE`) REFERENCES `width` (`WIDTHCODE`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garmentconsumptiondetail`
--

LOCK TABLES `garmentconsumptiondetail` WRITE;
/*!40000 ALTER TABLE `garmentconsumptiondetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `garmentconsumptiondetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garmentkind`
--

DROP TABLE IF EXISTS `garmentkind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `garmentkind` (
  `GARMENTKINDCODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`GARMENTKINDCODE`),
  KEY `FK_GARMENTKIND_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_GARMENTKIND_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garmentkind`
--

LOCK TABLES `garmentkind` WRITE;
/*!40000 ALTER TABLE `garmentkind` DISABLE KEYS */;
INSERT INTO `garmentkind` VALUES ('Labcoat','admin','Labcoat','2016-07-20 00:00:00'),('Shirt','admin','Shirt','2016-07-20 00:00:00'),('THOBE','admin','A4 2_2 China collar, hem sleeves','2016-07-20 00:00:00');
/*!40000 ALTER TABLE `garmentkind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garmentstyle`
--

DROP TABLE IF EXISTS `garmentstyle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `garmentstyle` (
  `GARMENTSTYLECODE` varchar(103) COLLATE utf8_unicode_ci NOT NULL,
  `GARMENTKINDCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CUSTOMERCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `IMGURL1` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL2` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL3` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL4` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL5` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SEWINGGUIDE` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PACKINGGUIDE` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REFERPRICEUNIT` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REFERPRICE` float(20,4) DEFAULT NULL,
  `CURRENCYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`GARMENTSTYLECODE`),
  KEY `FK_GARMENTSTYLE_REFERENCE_FACTORY` (`FACTORYCODE`),
  KEY `FK_GARMENTSTYLE_REFERENCE_GARMENTKIND` (`GARMENTKINDCODE`),
  KEY `FK_GARMENTSTYLE_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  KEY `FK_GARMENTSTYLE_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_GARMENTSTYLE_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  CONSTRAINT `FK_GARMENTSTYLE_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  CONSTRAINT `FK_GARMENTSTYLE_REFERENCE_GARMENTKIND` FOREIGN KEY (`GARMENTKINDCODE`) REFERENCES `garmentkind` (`GARMENTKINDCODE`),
  CONSTRAINT `FK_GARMENTSTYLE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garmentstyle`
--

LOCK TABLES `garmentstyle` WRITE;
/*!40000 ALTER TABLE `garmentstyle` DISABLE KEYS */;
INSERT INTO `garmentstyle` VALUES ('A4-0-0@@@AL HARAMAIN','THOBE','admin','DV','AL HARAMAIN','China collar hem sleeve','2016-07-20 00:00:00','','','','','',NULL,NULL,NULL,18.000,'2'),
('A4-2-2@@@AL HARAMAIN','THOBE','admin','DV','AL HARAMAIN','China collar hem sleeve','2016-07-20 00:00:00','','','','','',NULL,NULL,NULL,0.000,'2'),
('B4-2-2@@@AL HARAMAIN','THOBE','admin','DV','AL HARAMAIN','china collar, cuff sleeve','2016-12-01 18:03:11','',NULL,NULL,NULL,NULL,'','rolling',NULL,26.000,'USD'),
('Thobe@@@Al Shiaka','Shirt','admin','DV','Al Shiaka','A4 2-2','2016-10-01 15:52:28','','','','','','China collar -Hem sleeves','Roll packing',NULL,0.000,'2'),
('Thobe Mustafa@@@Mr. Mustafa','THOBE','admin','DV','Mr. Mustafa','','2016-11-30 14:12:05',NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,'USD');
/*!40000 ALTER TABLE `garmentstyle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garmentstyleaccessorydetail`
--

DROP TABLE IF EXISTS `garmentstyleaccessorydetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `garmentstyleaccessorydetail` (
  `GARMENTSTYLEACCESSORYDETAILCODE` int(11) NOT NULL AUTO_INCREMENT,
  `GARMENTSTYLECODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSORYCODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `USEDVALUE` float DEFAULT NULL,
  `ISCONFIGBYTYPE` tinyint(1) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`GARMENTSTYLEACCESSORYDETAILCODE`),
  KEY `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_GARMENTS` (`GARMENTSTYLECODE`),
  KEY `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_ACCESSOR` (`ACCESSORYCODE`),
  KEY `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_SIZE` (`SIZECODE`),
  KEY `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_ACCESSOR` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  CONSTRAINT `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_GARMENTS` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  CONSTRAINT `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`),
  CONSTRAINT `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garmentstyleaccessorydetail`
--

LOCK TABLES `garmentstyleaccessorydetail` WRITE;
/*!40000 ALTER TABLE `garmentstyleaccessorydetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `garmentstyleaccessorydetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garmentstylereferprice`
--

DROP TABLE IF EXISTS `garmentstylereferprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `garmentstylereferprice` (
  `GARMENTSTYLECODE` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `TYPECODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `REFERPRICE` float(20,4) DEFAULT NULL,
  `UNITCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`GARMENTSTYLECODE`,`TYPECODE`),
  KEY `FK_GARSTYLE_REFERENCE_TYPE` (`TYPECODE`),
  KEY `FK_GARSTYLE_REFERENCE_GARMENTSTYLE` (`GARMENTSTYLECODE`),
  KEY `FK_GARSTYLE_REFERENCE_UNITCODE` (`UNITCODE`),
  CONSTRAINT `FK_GARMENTSTYLEREFPRICE_REFERENCE_TYPE` FOREIGN KEY (`TYPECODE`) REFERENCES `type` (`TYPECODE`),
  CONSTRAINT `FK_GARSTYLEREFPRICE_REFERENCE_GARMENTSTYLE` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  CONSTRAINT `FK_GARSTYLEREFPRICE_REFERENCE_UNIT` FOREIGN KEY (`UNITCODE`) REFERENCES `unit` (`UNITCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garmentstylereferprice`
--

LOCK TABLES `garmentstylereferprice` WRITE;
/*!40000 ALTER TABLE `garmentstylereferprice` DISABLE KEYS */;
/*!40000 ALTER TABLE `garmentstylereferprice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logofchange`
--

DROP TABLE IF EXISTS `logofchange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logofchange` (
  `LOGOFCHANGECODE` int(11) NOT NULL AUTO_INCREMENT,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SUBJECT` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FROMEMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TOEMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CCMAILSTRING` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ATTACHEDFILEURL1` text COLLATE utf8_unicode_ci,
  `ATTACHEDFILEURL2` text COLLATE utf8_unicode_ci,
  `ATTACHEDFILEURL3` text COLLATE utf8_unicode_ci,
  `ATTACHEDFILEURL4` text COLLATE utf8_unicode_ci,
  `ATTACHEDFILEURL5` text COLLATE utf8_unicode_ci,
  `ATTACHEDFILEURL6` text COLLATE utf8_unicode_ci,
  `ATTACHEDFILEURL7` text COLLATE utf8_unicode_ci,
  `ATTACHEDFILEURL8` text COLLATE utf8_unicode_ci,
  `ATTACHEDFILEURL9` text COLLATE utf8_unicode_ci,
  `ATTACHEDFILEURL10` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`LOGOFCHANGECODE`),
  KEY `FK_LOGOFCHANGE_REFERENCE_PI` (`LOTNUMBER`),
  CONSTRAINT `FK_LOGOFCHANGE_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logofchange`
--

LOCK TABLES `logofchange` WRITE;
/*!40000 ALTER TABLE `logofchange` DISABLE KEYS */;
/*!40000 ALTER TABLE `logofchange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderexternalaccessory`
--

DROP TABLE IF EXISTS `orderexternalaccessory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderexternalaccessory` (
  `ORDERSHEETNO` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ACCSUPPLIERCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSORYCODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDERQUANTITY` int(11) DEFAULT NULL,
  `ACTUALDELVQUANTITY` int(11) DEFAULT NULL,
  `ORDERDATE` datetime DEFAULT NULL,
  `ESTIMATEDELVDATE` datetime DEFAULT NULL,
  `ACTUALDELVDATE` datetime DEFAULT NULL,
  `STATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PAYMENTSTATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRICE` float(20,4) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `UNITPRICE` float(20,4) DEFAULT '0.000',
  PRIMARY KEY (`ORDERSHEETNO`),
  KEY `FK_ORDEREXTERNALACCESSORY_REFERENCE_USER` (`CREATOR`),
  KEY `FK_ORDEREXTERNALACCESSORY_REFERENCE_ACCESSORYSUPPLIER` (`ACCSUPPLIERCODE`),
  KEY `FK_ORDEREXTERNALACCESSORY_REFERENCE_FACTORY` (`FACTORYCODE`),
  KEY `FK_ORDEREXTERNALACCESSORY_REFERENCE_ACCESSORY` (`ACCESSORYCODE`),
  CONSTRAINT `FK_ORDEREXTERNALACCESSORY_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  CONSTRAINT `FK_ORDEREXTERNALACCESSORY_REFERENCE_ACCESSORYSUPPLIER` FOREIGN KEY (`ACCSUPPLIERCODE`) REFERENCES `accessorysupplier` (`ACCSUPPLIERCODE`),
  CONSTRAINT `FK_ORDEREXTERNALACCESSORY_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  CONSTRAINT `FK_ORDEREXTERNALACCESSORY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderexternalaccessory`
--

LOCK TABLES `orderexternalaccessory` WRITE;
/*!40000 ALTER TABLE `orderexternalaccessory` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderexternalaccessory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderinternalaccessory`
--

DROP TABLE IF EXISTS `orderinternalaccessory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderinternalaccessory` (
  `ORDERSHEETNO` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ACCSUPPLIERCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDERDATE` date DEFAULT NULL,
  `ESTIMATEDELVDATE` date DEFAULT NULL,
  `ACTUALDELVDATE` date DEFAULT NULL,
  `STATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHIPPINGSTATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `INVOICENUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `UNITPRICE` double(20,4) DEFAULT '0.0000',
  PRIMARY KEY (`ORDERSHEETNO`),
  KEY `FK_ORDERINTERNALACCESSORY_REFERENCE_ACCESSORYSUPPLIER` (`ACCSUPPLIERCODE`),
  KEY `FK_ORDERINTERNALACCESSORY_REFERENCE_FACTORY` (`FACTORYCODE`),
  KEY `FK_ORDERINTERNALACCESSORY_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_ORDERINTERNALACCESSORY_REFERENCE_ACCESSORYSUPPLIER` FOREIGN KEY (`ACCSUPPLIERCODE`) REFERENCES `accessorysupplier` (`ACCSUPPLIERCODE`),
  CONSTRAINT `FK_ORDERINTERNALACCESSORY_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  CONSTRAINT `FK_ORDERINTERNALACCESSORY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderinternalaccessory`
--

LOCK TABLES `orderinternalaccessory` WRITE;
/*!40000 ALTER TABLE `orderinternalaccessory` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderinternalaccessory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderinternalaccessorydetail`
--

DROP TABLE IF EXISTS `orderinternalaccessorydetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderinternalaccessorydetail` (
  `ACCESSORYCODE` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ORDERSHEETNO` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDERQUANTITY` double DEFAULT NULL,
  `ACTUALDELVQUANTITY` double DEFAULT NULL,
  `PRICE` double(20,4) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `UNITPRICE` double(20,4) DEFAULT NULL,
  PRIMARY KEY (`ACCESSORYCODE`,`ORDERSHEETNO`),
  KEY `FK_ORDERINTERNALACCESSORYDETAIL_REFERENCE_ORDERINTERNALACC` (`ORDERSHEETNO`),
  KEY `FK_ORDERINTERNALACCESSORYDETAIL_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_ORDERINTERNALACCESSORYDETAIL_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  CONSTRAINT `FK_ORDERINTERNALACCESSORYDETAIL_REFERENCE_ORDERINTERNALACC` FOREIGN KEY (`ORDERSHEETNO`) REFERENCES `orderinternalaccessory` (`ORDERSHEETNO`),
  CONSTRAINT `FK_ORDERINTERNALACCESSORYDETAIL_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderinternalaccessorydetail`
--

LOCK TABLES `orderinternalaccessorydetail` WRITE;
/*!40000 ALTER TABLE `orderinternalaccessorydetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderinternalaccessorydetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packingguide`
--

DROP TABLE IF EXISTS `packingguide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `packingguide` (
  `Code` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Description` longtext COLLATE utf8_unicode_ci,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Code`),
  KEY `FK_PACKINGGUIDE_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_PACKINGGUIDE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packingguide`
--

LOCK TABLES `packingguide` WRITE;
/*!40000 ALTER TABLE `packingguide` DISABLE KEYS */;
INSERT INTO `packingguide` VALUES ('Guide001','Guide001 Description','2016-10-22 15:17:34','admin');
/*!40000 ALTER TABLE `packingguide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pi`
--

DROP TABLE IF EXISTS `pi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pi` (
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DESTINATIONCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CUSTOMER1CODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONSIGNEEE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PIGRIDCODE` int(11) DEFAULT NULL,
  `NONEORDERACCESSORIES` tinyint(1) DEFAULT NULL,
  `PIRECEIVEDDATE` date DEFAULT NULL,
  `PIESTSHIPDATE` date DEFAULT NULL,
  `PIATTACHEDFILEURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PACKINGGUIDE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SEWINGGUIDEURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PACKINGGUIDEURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `SHIPPINGSTATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BRANDCODE` int(11) DEFAULT NULL,
  `MFGSTARTEDDATE` date DEFAULT NULL,
  `MFGFINISHEDDATE` date DEFAULT NULL,
  PRIMARY KEY (`LOTNUMBER`),
  KEY `FK_PI_REFERENCE_PIGRID` (`PIGRIDCODE`),
  KEY `FK_PI_REFERENCE_USER` (`CREATOR`),
  KEY `FK_PI_REFERENCE_DESTINATION` (`DESTINATIONCODE`),
  KEY `FK_PI_REFERENCE_FACTORY` (`FACTORYCODE`),
  KEY `FK_PICUSTOMER1_REFERENCE_CUSTOMER` (`CUSTOMER1CODE`),
  KEY `FK_PICONSIGNEE_REFERENCE_CUSTOMER` (`CONSIGNEEE`),
  KEY `FK_PIBRAND_REFERENCE_BRAND` (`BRANDCODE`),
  KEY `FK_PI_REFERENCE_PACKINGGUIDE` (`PACKINGGUIDE`),
  CONSTRAINT `FK_PIBRAND_REFERENCE_BRAND` FOREIGN KEY (`BRANDCODE`) REFERENCES `brand` (`BRANDCODE`),
  CONSTRAINT `FK_PICONSIGNEE_REFERENCE_CUSTOMER` FOREIGN KEY (`CONSIGNEEE`) REFERENCES `customer` (`CUSTOMERCODE`),
  CONSTRAINT `FK_PICUSTOMER1_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMER1CODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  CONSTRAINT `FK_PI_REFERENCE_DESTINATION` FOREIGN KEY (`DESTINATIONCODE`) REFERENCES `destination` (`DESTINATIONCODE`),
  CONSTRAINT `FK_PI_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  CONSTRAINT `FK_PI_REFERENCE_PACKINGGUIDE` FOREIGN KEY (`PACKINGGUIDE`) REFERENCES `packingguide` (`Code`),
  CONSTRAINT `FK_PI_REFERENCE_PIGRID` FOREIGN KEY (`PIGRIDCODE`) REFERENCES `pigrid` (`PIGRIDCODE`),
  CONSTRAINT `FK_PI_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pi`
--

LOCK TABLES `pi` WRITE;
/*!40000 ALTER TABLE `pi` DISABLE KEYS */;
/*!40000 ALTER TABLE `pi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piassignexternalaccessory`
--

DROP TABLE IF EXISTS `piassignexternalaccessory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `piassignexternalaccessory` (
  `PIASSIGNEXTERNALACCESSORYCODE` int(11) NOT NULL AUTO_INCREMENT,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSORYCODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDERSHEETNO` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EXTERNALACCESSORYSTOCKCODE` int(11) DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PIGRIDDETAIL` int(11) DEFAULT NULL,
  `ESTIMATEQTY` float DEFAULT NULL,
  `ORDERQTY` float DEFAULT NULL,
  `STOCKASSIGNQTY` float DEFAULT NULL,
  `ACTUALASSIGNQTY` float DEFAULT NULL,
  `SPECIFICCONSUMPTION` float DEFAULT NULL,
  `SPECIFICEQUIVALENT` float DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `GARMENTSTYLEACCESSORYDETAILCODE` int(11) DEFAULT NULL,
  `UNITPRICE` float(20,4) DEFAULT '0.000',
  PRIMARY KEY (`PIASSIGNEXTERNALACCESSORYCODE`),
  KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_PI` (`LOTNUMBER`),
  KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_ACCESSORY` (`ACCESSORYCODE`),
  KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_USER` (`CREATOR`),
  KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_ORDEREXTERNALACCESSORY` (`ORDERSHEETNO`),
  KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_EXTERNALACCSTOCK` (`EXTERNALACCESSORYSTOCKCODE`),
  KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_GARMENTSTYLE` (`GARMENTSTYLECODE`),
  KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_COLOR` (`COLORCODE`),
  KEY `FK_PIASSIGNEXTERNALACC_REFERENCE_GARMENTSTYLEACCDETAIL` (`GARMENTSTYLEACCESSORYDETAILCODE`),
  KEY `FK_PIASSIGNEXTERNALACC_REFERENCE_PIGRIDDETAIL` (`PIGRIDDETAIL`),
  CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_EXTERNALACCSTOCK` FOREIGN KEY (`EXTERNALACCESSORYSTOCKCODE`) REFERENCES `externalaccessorystock` (`EXTERNALACCESSORYSTOCKCODE`),
  CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_GARMENTSTYLE` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_ORDEREXTERNALACCESSORY` FOREIGN KEY (`ORDERSHEETNO`) REFERENCES `orderexternalaccessory` (`ORDERSHEETNO`),
  CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`),
  CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`),
  CONSTRAINT `FK_PIASSIGNEXTERNALACC_REFERENCE_GARMENTSTYLEACCDETAIL` FOREIGN KEY (`GARMENTSTYLEACCESSORYDETAILCODE`) REFERENCES `garmentstyleaccessorydetail` (`GARMENTSTYLEACCESSORYDETAILCODE`),
  CONSTRAINT `FK_PIASSIGNEXTERNALACC_REFERENCE_PIGRIDDETAIL` FOREIGN KEY (`PIGRIDDETAIL`) REFERENCES `pigriddetail` (`PIGRIDDETAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piassignexternalaccessory`
--

LOCK TABLES `piassignexternalaccessory` WRITE;
/*!40000 ALTER TABLE `piassignexternalaccessory` DISABLE KEYS */;
/*!40000 ALTER TABLE `piassignexternalaccessory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piassignfabric`
--

DROP TABLE IF EXISTS `piassignfabric`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `piassignfabric` (
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `FABRICNO` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`LOTNUMBER`,`FABRICNO`),
  KEY `FK_PIASSIGN_REFERENCE_USER` (`CREATOR`),
  KEY `FK_PIASSIGNFABRIC_REFERENCE_FABRICINFORMATION` (`FABRICNO`),
  CONSTRAINT `FK_PIASSIGNFABRIC_REFERENCE_FABRICINFORMATION` FOREIGN KEY (`FABRICNO`) REFERENCES `fabricinformation` (`FABRICNO`),
  CONSTRAINT `FK_PIASSIGN_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`),
  CONSTRAINT `FK_PIASSIGN_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piassignfabric`
--

LOCK TABLES `piassignfabric` WRITE;
/*!40000 ALTER TABLE `piassignfabric` DISABLE KEYS */;
/*!40000 ALTER TABLE `piassignfabric` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piassignfabricdetail`
--

DROP TABLE IF EXISTS `piassignfabricdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `piassignfabricdetail` (
  `PIASSIGNFABRICDETAILCODE` int(11) NOT NULL AUTO_INCREMENT,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FABRICNO` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ASSIGNQUANTITY` int(11) DEFAULT NULL,
  `ASSIGNQTYPERCENT` float(20,4) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PIASSIGNFABRICDETAILCODE`),
  KEY `FK_PIASSIGNFABRICDETAIL_REFERENCE_COLOR` (`COLORCODE`),
  KEY `FK_PIASSIGNFABRICDETAIL_REFERENCE_PIASSIGNFABRIC` (`LOTNUMBER`,`FABRICNO`),
  KEY `FK_PIASSIGNFABRICDETAIL_REFERENCE_GARMENTSTYLE` (`GARMENTSTYLECODE`),
  CONSTRAINT `FK_PIASSIGNFABRICDETAIL_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  CONSTRAINT `FK_PIASSIGNFABRICDETAIL_REFERENCE_GARMENTSTYLE` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  CONSTRAINT `FK_PIASSIGNFABRICDETAIL_REFERENCE_PIASSIGNFABRIC` FOREIGN KEY (`LOTNUMBER`, `FABRICNO`) REFERENCES `piassignfabric` (`LOTNUMBER`, `FABRICNO`)
) ENGINE=InnoDB AUTO_INCREMENT=54322 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piassignfabricdetail`
--

LOCK TABLES `piassignfabricdetail` WRITE;
/*!40000 ALTER TABLE `piassignfabricdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `piassignfabricdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piassigninternalaccessories`
--

DROP TABLE IF EXISTS `piassigninternalaccessories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `piassigninternalaccessories` (
  `PIINTERNALACCESSORIES` int(11) NOT NULL AUTO_INCREMENT,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSORYCODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PIINTERNALACCESSORIES`),
  KEY `FK_PIORDERINTERNALACC_REFERENCE_PI` (`LOTNUMBER`),
  KEY `FK_PIORDERINTERNALACC_REFERENCE_ACCESSORY` (`ACCESSORYCODE`),
  KEY `FK_PIORDERINTERNALACC_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_PIORDERINTERNALACC_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  CONSTRAINT `FK_PIORDERINTERNALACC_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`),
  CONSTRAINT `FK_PIORDERINTERNALACC_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piassigninternalaccessories`
--

LOCK TABLES `piassigninternalaccessories` WRITE;
/*!40000 ALTER TABLE `piassigninternalaccessories` DISABLE KEYS */;
/*!40000 ALTER TABLE `piassigninternalaccessories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piassigninternalaccessoriesdetail`
--

DROP TABLE IF EXISTS `piassigninternalaccessoriesdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `piassigninternalaccessoriesdetail` (
  `PIINTERNALACCDETAILCODE` int(11) NOT NULL AUTO_INCREMENT,
  `PIINTERNALACCESSORIES` int(11) DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ASSIGNQUANTITY` double DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PIINTERNALACCDETAILCODE`),
  KEY `FK_PIORDERINTERNALACCDEATIL_REFERENCE_PIORDERINTERNALACC` (`PIINTERNALACCESSORIES`),
  KEY `FK_PIORDERINTERNALACCDETAIL_REFERENCE_GARMENTSTYLE` (`GARMENTSTYLECODE`),
  KEY `FK_PIORDERINTERNALACCDETAIL_REFERENCE_SIZE` (`SIZECODE`),
  KEY `FK_PIORDERINTERNALACCDETAIL_REFERENCE_COLOR` (`COLORCODE`),
  CONSTRAINT `FK_PIORDERINTERNALACCDEATIL_REFERENCE_PIORDERINTERNALACC` FOREIGN KEY (`PIINTERNALACCESSORIES`) REFERENCES `piassigninternalaccessories` (`PIINTERNALACCESSORIES`),
  CONSTRAINT `FK_PIORDERINTERNALACCDETAIL_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  CONSTRAINT `FK_PIORDERINTERNALACCDETAIL_REFERENCE_GARMENTSTYLE` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  CONSTRAINT `FK_PIORDERINTERNALACCDETAIL_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piassigninternalaccessoriesdetail`
--

LOCK TABLES `piassigninternalaccessoriesdetail` WRITE;
/*!40000 ALTER TABLE `piassigninternalaccessoriesdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `piassigninternalaccessoriesdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piassigninternalaccessoriesoforders`
--

DROP TABLE IF EXISTS `piassigninternalaccessoriesoforders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `piassigninternalaccessoriesoforders` (
  `PIASSIGNINTERNALACCESSORIESOFORDERSCODE` int(11) NOT NULL AUTO_INCREMENT,
  `PIINTERNALACCESSORIES` int(11) DEFAULT NULL,
  `ORDERSHEETNO` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDERASSIGNQUANTITY` double DEFAULT NULL,
  PRIMARY KEY (`PIASSIGNINTERNALACCESSORIESOFORDERSCODE`),
  KEY `FK_PIASSIGNINTACCORDERS_REF_PIASSIGNINTACC` (`PIINTERNALACCESSORIES`),
  KEY `FK_PIASSIGNINTACCORDER_REF_ORDERINTACC` (`ORDERSHEETNO`),
  CONSTRAINT `FK_PIASSIGNINTACCORDERS_REF_PIASSIGNINTACC` FOREIGN KEY (`PIINTERNALACCESSORIES`) REFERENCES `piassigninternalaccessories` (`PIINTERNALACCESSORIES`),
  CONSTRAINT `FK_PIASSIGNINTACCORDER_REF_ORDERINTACC` FOREIGN KEY (`ORDERSHEETNO`) REFERENCES `orderinternalaccessory` (`ORDERSHEETNO`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piassigninternalaccessoriesoforders`
--

LOCK TABLES `piassigninternalaccessoriesoforders` WRITE;
/*!40000 ALTER TABLE `piassigninternalaccessoriesoforders` DISABLE KEYS */;
/*!40000 ALTER TABLE `piassigninternalaccessoriesoforders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pigrid`
--

DROP TABLE IF EXISTS `pigrid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pigrid` (
  `PIGRIDCODE` int(11) NOT NULL AUTO_INCREMENT,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PIGRIDCODE`),
  KEY `FK_PIGRID_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_PIGRID_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=1137 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pigrid`
--

LOCK TABLES `pigrid` WRITE;
/*!40000 ALTER TABLE `pigrid` DISABLE KEYS */;
/*!40000 ALTER TABLE `pigrid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pigriddetail`
--

DROP TABLE IF EXISTS `pigriddetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pigriddetail` (
  `PIGRIDDETAIL` int(11) NOT NULL AUTO_INCREMENT,
  `PIGRIDCODE` int(11) DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BARCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PCS` int(11) DEFAULT NULL,
  `FAVALUE` int(11) DEFAULT NULL,
  PRIMARY KEY (`PIGRIDDETAIL`),
  KEY `FK_PIGRIDDE_REFERENCE_SIZE` (`SIZECODE`),
  KEY `FK_PIGRIDDE_REFERENCE_GARMENTS` (`GARMENTSTYLECODE`),
  KEY `FK_PIGRIDDE_REFERENCE_COLOR` (`COLORCODE`),
  KEY `FK_PIGRIDDE_REFERENCE_PIGRID` (`PIGRIDCODE`),
  CONSTRAINT `FK_PIGRIDDE_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  CONSTRAINT `FK_PIGRIDDE_REFERENCE_GARMENTS` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  CONSTRAINT `FK_PIGRIDDE_REFERENCE_PIGRID` FOREIGN KEY (`PIGRIDCODE`) REFERENCES `pigrid` (`PIGRIDCODE`),
  CONSTRAINT `FK_PIGRIDDE_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pigriddetail`
--

LOCK TABLES `pigriddetail` WRITE;
/*!40000 ALTER TABLE `pigriddetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `pigriddetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rfpi`
--

DROP TABLE IF EXISTS `rfpi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rfpi` (
  `RFPIGRID` int(11) NOT NULL AUTO_INCREMENT,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RECEIVEDDATE` datetime DEFAULT NULL,
  `CUSTOMERCONFIRMATIONSTATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`RFPIGRID`),
  KEY `FK_RFPI_REFERENCE_PI` (`LOTNUMBER`),
  KEY `FK_RFPI_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_RFPI_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`),
  CONSTRAINT `FK_RFPI_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rfpi`
--

LOCK TABLES `rfpi` WRITE;
/*!40000 ALTER TABLE `rfpi` DISABLE KEYS */;
/*!40000 ALTER TABLE `rfpi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rfpidetail`
--

DROP TABLE IF EXISTS `rfpidetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rfpidetail` (
  `RFPIDETAIL` int(11) NOT NULL AUTO_INCREMENT,
  `RFPIGRID` int(11) DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `RFPIVALUE` int(11) DEFAULT NULL,
  PRIMARY KEY (`RFPIDETAIL`),
  KEY `FK_RFPIDETA_REFERENCE_RFPI` (`RFPIGRID`),
  KEY `FK_RFPIDETA_REFERENCE_GARMENTS` (`GARMENTSTYLECODE`),
  KEY `FK_RFPIDETA_REFERENCE_COLOR` (`COLORCODE`),
  KEY `FK_RFPIDETA_REFERENCE_SIZE` (`SIZECODE`),
  CONSTRAINT `FK_RFPIDETA_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  CONSTRAINT `FK_RFPIDETA_REFERENCE_GARMENTS` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  CONSTRAINT `FK_RFPIDETA_REFERENCE_RFPI` FOREIGN KEY (`RFPIGRID`) REFERENCES `rfpi` (`RFPIGRID`),
  CONSTRAINT `FK_RFPIDETA_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rfpidetail`
--

LOCK TABLES `rfpidetail` WRITE;
/*!40000 ALTER TABLE `rfpidetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `rfpidetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ROLEID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ROLENAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ROLEID`),
  KEY `FK_ROLE_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_ROLE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('AD','admin','Admin','Administrator','2016-07-20 00:00:00');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolefunction`
--

DROP TABLE IF EXISTS `rolefunction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolefunction` (
  `ROLEID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `FUNCTIONID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ROLEID`,`FUNCTIONID`),
  KEY `FK_ROLEFUNC_REFERENCE_FUNCTION` (`FUNCTIONID`),
  CONSTRAINT `FK_ROLEFUNC_REFERENCE_FUNCTION` FOREIGN KEY (`FUNCTIONID`) REFERENCES `function` (`FUNCTIONID`),
  CONSTRAINT `FK_ROLEFUNC_REFERENCE_ROLE` FOREIGN KEY (`ROLEID`) REFERENCES `role` (`ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolefunction`
--

LOCK TABLES `rolefunction` WRITE;
/*!40000 ALTER TABLE `rolefunction` DISABLE KEYS */;
INSERT INTO `rolefunction` VALUES ('AD','Accessory Consumption'),('AD','Accessory Group'),('AD','Accessory Information'),('AD','Accessory Order Signature'),('AD','Accessory Price'),('AD','Accessory Supplier'),('AD','Agent Management'),('AD','Color Management'),('AD','Container Type'),('AD','Currency Exchange'),('AD','Currency Management'),('AD','Customer Management'),('AD','Destination Management'),('AD','ETOC Management'),('AD','Fabric Information'),('AD','Fabric Supplier'),('AD','Factory Management'),('AD','FPI Information'),('AD','Garment Consumption'),('AD','Garment Kind'),('AD','Garment Style'),('AD','Groups & Functions'),('AD','Order General Accessories'),('AD','Packing Guide'),('AD','PI Information'),('AD','RFPI Information'),('AD','Shipping Line'),('AD','Size Management'),('AD','Type Management'),('AD','Unit Management'),('AD','User Management'),('AD','Width Management');
/*!40000 ALTER TABLE `rolefunction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shippingline`
--

DROP TABLE IF EXISTS `shippingline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shippingline` (
  `SHIPPINGLINECODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHORTNAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SHIPPINGLINECODE`),
  KEY `FK_SHIPPING_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_SHIPPING_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shippingline`
--

LOCK TABLES `shippingline` WRITE;
/*!40000 ALTER TABLE `shippingline` DISABLE KEYS */;
INSERT INTO `shippingline` VALUES ('CMA','admin','CMA','CMA CGM Vietnam JSC','8th Floor, CONTINENTAL Tower, No 81-85 Ham Nghi Street Nguyen Thai Binh Ward, District 1 Ho Chi Minh City Vietnam','+84-8-3 914 8400','+84-8-3 915 1716','','Active','2016-07-07 00:00:00',''),('NHS','admin','NHS','NEW HORIZON SERVICE CO., LTD','NEW HORIZON SERVICE CO., LTD 386-388 Hoang Dieu., District 4., Hochiminh City, Vietnam','84-8-39433196','84-8-39433194','','Active','2016-07-08 00:00:00',''),('NM','admin','NM','NM SHIPPING CO., LTD','NM SHIPPING CO., LTD 3/F, Melody Tower, 422 Ung Van Khiem St, Binh Thanh Dist, Hochiminh City, Vietnam','84 8 38997228','84 8 38997494','','Active','2016-07-07 00:00:00','');
/*!40000 ALTER TABLE `shippingline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shippinglinecontact`
--

DROP TABLE IF EXISTS `shippinglinecontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shippinglinecontact` (
  `SHIPPINGLINECONTACTCODE` int(11) NOT NULL AUTO_INCREMENT,
  `SHIPPINGLINECODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `TEL` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SHIPPINGLINECONTACTCODE`),
  KEY `FK_SHIPPING_REFERENCE_SHIPPING` (`SHIPPINGLINECODE`),
  KEY `FK_SHIPPINGLINECONTACT_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_SHIPPINGLINECONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`),
  CONSTRAINT `FK_SHIPPING_REFERENCE_SHIPPING` FOREIGN KEY (`SHIPPINGLINECODE`) REFERENCES `shippingline` (`SHIPPINGLINECODE`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shippinglinecontact`
--

LOCK TABLES `shippinglinecontact` WRITE;
/*!40000 ALTER TABLE `shippinglinecontact` DISABLE KEYS */;
INSERT INTO `shippinglinecontact` VALUES (1,'CMA','admin','Mr Pham Ngoc Tinh','sgn.pntinh@cma-cgm.com','2016-07-08 00:00:00','0084908441655'),(2,'NHS','admin','Ms. Thanh','ob@newhorizonlogs.com','2016-07-08 00:00:00','+84 0938073936'),(3,'NM','admin','Ms.Ngoc','cus@nmshipping.com.vn','2016-07-08 00:00:00','+84090.4653778'),(4,'NM','admin','Mr. Quy','quy.nguyen@nmshipping.com.vn','2016-07-08 00:00:00','+84 0903.146376'),(5,'NM','admin','Ms.Hong','docs@nmshipping.com.vn','2016-07-08 00:00:00','+84 0917.692.211');
/*!40000 ALTER TABLE `shippinglinecontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `size` (
  `SIZECODE` int(11) NOT NULL AUTO_INCREMENT,
  `SIZENAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CUSTOMERCODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `GARMENTKINDCODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TYPECODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`SIZECODE`),
  KEY `FK_SIZE_REFERENCE_TYPE` (`TYPECODE`),
  KEY `FK_SIZE_REFERENCE_USER` (`CREATOR`),
  KEY `FK_SIZE_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  KEY `FK_SIZE_REFERENCE_GARMENTK` (`GARMENTKINDCODE`),
  CONSTRAINT `FK_SIZE_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  CONSTRAINT `FK_SIZE_REFERENCE_GARMENTK` FOREIGN KEY (`GARMENTKINDCODE`) REFERENCES `garmentkind` (`GARMENTKINDCODE`),
  CONSTRAINT `FK_SIZE_REFERENCE_TYPE` FOREIGN KEY (`TYPECODE`) REFERENCES `type` (`TYPECODE`),
  CONSTRAINT `FK_SIZE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (41,'20','AL HARAMAIN','THOBE','Boy','admin','2016-11-30 13:44:05'),(42,'22','AL HARAMAIN','THOBE','Boy','admin','2016-11-30 13:44:09'),(43,'24','AL HARAMAIN','THOBE','Boy','admin','2016-11-30 13:44:13'),(44,'26','AL HARAMAIN','THOBE','Boy','admin','2016-11-30 13:44:17'),(45,'28','AL HARAMAIN','THOBE','Boy','admin','2016-11-30 13:44:23'),(46,'30','AL HARAMAIN','THOBE','Boy','admin','2016-11-30 13:44:27'),(47,'32','AL HARAMAIN','THOBE','Boy','admin','2016-11-30 13:44:35'),(48,'34','AL HARAMAIN','THOBE','Boy','admin','2016-11-30 13:44:39'),(49,'36','AL HARAMAIN','THOBE','Boy','admin','2016-11-30 13:44:44'),(50,'38','AL HARAMAIN','THOBE','Boy','admin','2016-11-30 13:44:48'),(51,'20','Al Shiaka','THOBE','Boy','admin','2016-11-30 13:45:04'),(52,'22','Al Shiaka','THOBE','Boy','admin','2016-11-30 13:45:04'),(53,'24','Al Shiaka','THOBE','Boy','admin','2016-11-30 13:45:04'),(54,'26','Al Shiaka','THOBE','Boy','admin','2016-11-30 13:45:04'),(55,'28','Al Shiaka','THOBE','Boy','admin','2016-11-30 13:45:04'),(56,'30','Al Shiaka','THOBE','Boy','admin','2016-11-30 13:45:04'),(57,'32','Al Shiaka','THOBE','Boy','admin','2016-11-30 13:45:04'),(58,'34','Al Shiaka','THOBE','Boy','admin','2016-11-30 13:45:04'),(59,'36','Al Shiaka','THOBE','Boy','admin','2016-11-30 13:45:04'),(60,'38','Al Shiaka','THOBE','Boy','admin','2016-11-30 13:45:04'),(61,'20/S','Mr. Mustafa','THOBE','Boy','admin','2016-11-30 13:45:33'),(62,'24','AL HARAMAIN','Labcoat','Boy','admin','2016-12-02 09:28:22');
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `TYPECODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TYPECODE`),
  KEY `FK_TYPE_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_TYPE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES ('Boy','admin','Boy','2016-07-20 00:00:00'),('Child','admin','Child','2016-07-20 00:00:00'),('Men','admin','Men','2016-07-20 00:00:00'),('None','admin','None','2016-10-01 15:36:31'),('Youth','admin','Youth','2016-07-20 00:00:00');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `UNITCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UNITCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES ('Gr','Gram','2016-12-01 19:22:18'),('grs','Gross','2016-07-07 00:00:00'),('inches','Inches','2016-07-20 00:00:00'),('pcs','Piece','2016-07-07 00:00:00');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USERNAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ROLEID` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AGENTCODE` int(11) DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PASSWORD` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FIRSTNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LASTNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAILPASSWORD` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PHONE` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CCMAILSTRING` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`USERNAME`),
  KEY `FK_USER_REFERENCE_ROLE` (`ROLEID`),
  KEY `FK_USER_REFERENCE_AGENT` (`AGENTCODE`),
  KEY `FK_USER_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_USER_REFERENCE_AGENT` FOREIGN KEY (`AGENTCODE`) REFERENCES `agent` (`AGENTCODE`),
  CONSTRAINT `FK_USER_REFERENCE_ROLE` FOREIGN KEY (`ROLEID`) REFERENCES `role` (`ROLEID`),
  CONSTRAINT `FK_USER_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','AD',1,'admin','$2a$10$R0sA2XcMYxzarn/jdJ1Sw.NAzzWuLT5LmBZKfTgg7mruhH4ig.2GK','Administrator','Administrator','bleachclone69@gmail.com','huancuibap','01642329994','Active',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `width`
--

DROP TABLE IF EXISTS `width`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `width` (
  `WIDTHCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `UNITCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WIDTHVALUES` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`WIDTHCODE`),
  KEY `FK_WIDTH_REFERENCE_UNIT` (`UNITCODE`),
  KEY `FK_WIDTH_REFERENCE_USER` (`CREATOR`),
  CONSTRAINT `FK_WIDTH_REFERENCE_UNIT` FOREIGN KEY (`UNITCODE`) REFERENCES `unit` (`UNITCODE`),
  CONSTRAINT `FK_WIDTH_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `width`
--

LOCK TABLES `width` WRITE;
/*!40000 ALTER TABLE `width` DISABLE KEYS */;
INSERT INTO `width` VALUES ('4.4','inches','admin','4.4','2016-11-30 14:11:05');
/*!40000 ALTER TABLE `width` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-02 17:32:09
