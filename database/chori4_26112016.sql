-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2016 at 07:43 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chori8`
--

-- --------------------------------------------------------

--
-- Table structure for table `accessory`
--

CREATE TABLE `accessory` (
  `ACCESSORYCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ACCESSORYCHORICODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `ACCESSORYGROUPCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accessory`
--

INSERT INTO `accessory` (`ACCESSORYCODE`, `ACCESSORYCHORICODE`, `CREATOR`, `UNITCODE`, `COLORCODE`, `CONTAINERUNITCODE`, `NAME`, `KIND`, `DIMENSION`, `MODE`, `IMGURL1`, `IMGURL2`, `IMGURL3`, `IMGURL4`, `CREATEDATE`, `PERCONTAINER`, `ACCESSORYGROUPCODE`, `STATUS`) VALUES
('123', '', 'admin', 'grs', 'CB', 'grs', '123', 'Internal', '', 'Packing', NULL, NULL, NULL, NULL, '2016-11-25 13:40:31', NULL, 'BOX', 'Active'),
('BTNR', NULL, 'admin', 'pcs', 'CR', 'pcs', 'Button Red', 'External', '3x2', 'Manufacturing', 'il_fullxfull.81048469BTNR1475579668146.jpg', '', NULL, NULL, '2016-07-07 00:00:00', 1000, 'BUTTON', 'Active'),
('BTNW', NULL, 'admin', 'grs', 'CR', 'grs', 'Button White', 'External', '2x3x4', 'Manufacturing', '125035BA_White_BTNW1473238917347.jpg', '', NULL, NULL, '2016-07-07 00:00:00', 1000, 'BUTTON', 'Active'),
('BX1', NULL, 'admin', 'grs', 'CB', 'grs', 'Boxes#1Red', 'Internal', '23', 'Packing', 'Pearlised Red 100 x 280 x 200mm Rigid Box & LidBX11473238935334.jpg', NULL, NULL, NULL, '2016-08-27 11:50:08', 2123, 'BOX', 'Active'),
('BX3', NULL, 'admin', 'pcs', 'CR', 'pcs', 'Boxes#3Blue', 'Internal', '3x3x4', 'Packing', '1506485_651578608284800_6077629969122353861_nBX31474538495228.jpg', NULL, NULL, NULL, '2016-07-20 00:00:00', 1000, 'BOX', 'Active'),
('Keo', '', 'admin', 'grs', 'CB', 'grs', 'Keo', 'Internal', '', 'Packing', '15178163_716387735180729_1815523172585249343_nKeo1479975317811.jpg', NULL, NULL, NULL, '2016-11-24 15:15:17', NULL, 'BOX', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `accessoryconsumption`
--

CREATE TABLE `accessoryconsumption` (
  `FACTORYCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ACCESSORYCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CONSUMPTION` float DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accessoryconsumption`
--

INSERT INTO `accessoryconsumption` (`FACTORYCODE`, `ACCESSORYCODE`, `CREATOR`, `CONSUMPTION`, `CREATEDATE`) VALUES
('FAC0001', '123', 'admin', 0, '2016-11-25 13:40:31'),
('FAC0001', 'BTNR', 'admin', 4, '2016-08-27 10:55:22'),
('FAC0001', 'BTNW', 'admin', 3.5, '2016-08-27 10:55:21'),
('FAC0001', 'BX1', 'admin', 0, '2016-08-27 11:50:08'),
('FAC0001', 'BX3', 'admin', 1, '2016-08-27 10:55:22'),
('FAC0001', 'Keo', 'admin', 0, '2016-11-24 15:15:17'),
('FAC0002', '123', 'admin', 0, '2016-11-25 13:40:31'),
('FAC0002', 'BTNR', 'admin', 2.1, '2016-08-27 10:55:53'),
('FAC0002', 'BTNW', 'admin', 2.3, '2016-08-27 10:55:53'),
('FAC0002', 'BX1', 'admin', 0, '2016-08-27 11:50:08'),
('FAC0002', 'BX3', 'admin', 1.4, '2016-08-27 10:55:53'),
('FAC0002', 'Keo', 'admin', 0, '2016-11-24 15:15:17');

-- --------------------------------------------------------

--
-- Table structure for table `accessoryform`
--

CREATE TABLE `accessoryform` (
  `ACCESSORYFORMCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `accessoryformdetail`
--

CREATE TABLE `accessoryformdetail` (
  `ACCESSORYCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ACCESSORYFORMCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `accessorygroup`
--

CREATE TABLE `accessorygroup` (
  `ACCESSORYGROUPCODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accessorygroup`
--

INSERT INTO `accessorygroup` (`ACCESSORYGROUPCODE`, `CREATOR`, `DESCRIPTION`, `CREATEDATE`) VALUES
('BOX', 'admin', 'BOX DESCRIPTION', '2016-07-20 00:00:00'),
('BUTTON', 'admin', 'BUTTON DESCRIPTION', '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `accessoryordersignature`
--

CREATE TABLE `accessoryordersignature` (
  `ACCORDERSIGNCODE` int(11) NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEXT1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEXT2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accessoryordersignature`
--

INSERT INTO `accessoryordersignature` (`ACCORDERSIGNCODE`, `CREATOR`, `NAME`, `TEXT1`, `TEXT2`, `IMGURL`, `CREATEDATE`) VALUES
(1, 'admin', 'Nguyen Duy', 'Nguyen Duy', 'Nguyen Duy', '15178163_716387735180729_1815523172585249343_n11480135487926.jpg', '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `accessoryprice`
--

CREATE TABLE `accessoryprice` (
  `ACCESSORYPRICECODE` int(11) NOT NULL,
  `ACCESSORYCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ACCSUPPLIERCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `FROMDATE` datetime DEFAULT NULL,
  `TODATE` datetime DEFAULT NULL,
  `UNITPRICEPERUNIT` float(20,3) DEFAULT NULL,
  `CURRENCYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accessoryprice`
--

INSERT INTO `accessoryprice` (`ACCESSORYPRICECODE`, `ACCESSORYCODE`, `ACCSUPPLIERCODE`, `FROMDATE`, `TODATE`, `UNITPRICEPERUNIT`, `CURRENCYCODE`, `CREATOR`, `CREATEDATE`, `REMARK`) VALUES
(4, 'BTNR', 'AS0001', '2016-10-01 00:00:00', '2016-10-11 23:59:59', 2500.000, 'VND', 'admin', '2016-10-21 17:41:22', ''),
(5, 'BTNR', 'AS0001', '2016-10-12 00:00:00', '2016-11-30 23:59:59', 2400.000, 'VND', 'admin', '2016-10-21 17:42:00', ''),
(6, 'BTNW', 'AS0001', '2016-10-21 00:00:00', '2016-10-26 23:59:59', 2500.000, 'VND', 'admin', '2016-10-21 17:42:38', ''),
(7, 'BX1', 'AS0001', '2016-10-20 00:00:00', '2016-11-30 23:59:59', 2500.000, 'VND', 'admin', '2016-10-21 17:43:09', ''),
(8, 'BTNR', 'AS0002', '2016-10-01 00:00:00', '2016-12-21 23:59:59', 2400.000, 'VND', 'admin', '2016-10-21 17:43:24', ''),
(9, 'BTNR', 'AS0003', '2016-11-01 00:01:00', '2016-11-30 23:59:59', 2500.000, 'VND', 'admin', '2016-11-02 15:28:05', ''),
(10, 'Keo', 'AS0001', '2016-11-02 00:01:00', '2016-11-30 23:59:59', 200.000, 'USD', 'admin', '2016-11-24 15:17:31', ''),
(11, 'Keo', 'AS0003', '2016-10-31 00:00:00', '2016-11-30 23:59:59', 100.000, 'USD', 'admin', '2016-11-24 15:18:03', '');

-- --------------------------------------------------------

--
-- Table structure for table `accessorysupplier`
--

CREATE TABLE `accessorysupplier` (
  `ACCSUPPLIERCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHORTNAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accessorysupplier`
--

INSERT INTO `accessorysupplier` (`ACCSUPPLIERCODE`, `CREATOR`, `SHORTNAME`, `LONGNAME`, `ADDRESS`, `TEL`, `FAX`, `TAXNO`, `STATUS`, `CREATEDATE`, `REMARK`) VALUES
('AS0001', 'admin', 'Equifax', 'Equifax Company', '62 Tan Thanh St, Tan Phu Dis', '083242232', '123125354', '432534532', 'Active', '2016-10-07 10:26:18', ''),
('AS0002', 'admin', 'Genentech', 'Genentech Company', '62 Tan Thanh St, Tan Phu Dis', '083242232', '123125354', '432534532', 'Active', '2016-09-22 15:42:25', NULL),
('AS0003', 'admin', 'Aurora', 'AURORA ACCESSORY SUPPLIER', '62 Tan Thanh St, Tan Phu Dis', '083242232', '1231253543', '43253453245', 'Active', '2016-09-22 16:58:57', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `accessorysuppliercontact`
--

CREATE TABLE `accessorysuppliercontact` (
  `ACCSUPPLIERCONTACTCODE` int(11) NOT NULL,
  `ACCSUPPLIERCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accessorysuppliercontact`
--

INSERT INTO `accessorysuppliercontact` (`ACCSUPPLIERCONTACTCODE`, `ACCSUPPLIERCODE`, `CREATOR`, `NAME`, `EMAIL`, `CREATEDATE`, `TEL`) VALUES
(1, 'AS0001', 'admin', 'Nguyễn Huy Hoàng', 'hoanghn@equifax.com', '2016-07-20 00:00:00', ''),
(2, 'AS0001', 'admin', 'Trần Thái Công', 'congtt@equifax.com', '2016-07-20 00:00:00', ''),
(3, 'AS0002', 'admin', 'Nguyễn Khánh Phi', 'phink@genentech.com', '2016-07-20 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `agent`
--

CREATE TABLE `agent` (
  `AGENTCODE` int(11) NOT NULL,
  `SHORTNAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `agent`
--

INSERT INTO `agent` (`AGENTCODE`, `SHORTNAME`, `LONGNAME`, `CREATOR`, `ADDRESS`, `TEL`, `FAX`, `TAXNO`, `STATUS`, `CREATEDATE`, `REMARK`) VALUES
(1, 'Chori Agent VietNam', 'Chori Agent in VietNam', 'admin', NULL, '083242232', '1231253543', '143124', 'Active', '2016-07-20 00:00:00', NULL),
(2, 'Chori Agent Thailand', 'Chori Agent in Thailand', 'admin', NULL, '083242232', '1231253543', '143124', 'Active', '2016-07-20 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `agentcontact`
--

CREATE TABLE `agentcontact` (
  `AGENTCONTACTCODE` int(11) NOT NULL,
  `AGENTCODE` int(11) NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` timestamp NULL DEFAULT NULL,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `BRANDCODE` int(11) NOT NULL,
  `CUSTOMERCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BRANDNAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`BRANDCODE`, `CUSTOMERCODE`, `CREATOR`, `BRANDNAME`, `CREATEDATE`) VALUES
(1, 'Ahmad Abbas', 'admin', 'Ahmad', '2016-10-01 08:27:51'),
(2, 'Ahmad Abbas', 'admin', 'Abbas', '2016-10-01 08:27:51'),
(5, 'Asafa', 'admin', 'Asafa Brand Name 1', '2016-10-31 17:00:00'),
(6, 'Asafa', 'admin', 'Asafa Brand Name 2', '2016-11-06 17:00:00'),
(7, 'Saquiri', 'admin', 'Saquiri Brand Name 1', '2016-09-04 17:00:00'),
(8, 'Saquiri', 'admin', 'Saquiri Brand Name 2', '2016-11-06 17:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `color`
--

CREATE TABLE `color` (
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `color`
--

INSERT INTO `color` (`COLORCODE`, `CREATOR`, `DESCRIPTION`, `CREATEDATE`) VALUES
('CB', 'admin', 'Blue', '2016-07-20 00:00:00'),
('CBLK', 'admin', 'Black', '2016-07-20 00:00:00'),
('CR', 'admin', 'RED', '2016-07-07 00:00:00'),
('CWHT', 'admin', 'White', '2016-07-20 00:00:00'),
('CX', 'admin', '', '2016-11-14 11:29:43'),
('CYLW', 'admin', 'Yellow', '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `ctnrtype`
--

CREATE TABLE `ctnrtype` (
  `CTNRCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ctnrtype`
--

INSERT INTO `ctnrtype` (`CTNRCODE`, `CREATOR`, `DESCRIPTION`, `CREATEDATE`) VALUES
('FA5531', 'admin', 'Container FA5531', '2016-07-20 00:00:00'),
('FS5621', 'admin', 'Container FS5621', '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `currency`
--

CREATE TABLE `currency` (
  `CURRENCYCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `currency`
--

INSERT INTO `currency` (`CURRENCYCODE`, `CREATOR`, `NAME`, `CREATEDATE`) VALUES
('USD', 'admin', 'US Dollar', '2016-07-20 00:00:00'),
('VND', 'admin', 'VietnamDong', '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `currencyexchange`
--

CREATE TABLE `currencyexchange` (
  `CURRENCYEXCODE` int(11) NOT NULL,
  `CURRENCYCODESOURCE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CURRENCYCODEDESTINATION` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AMOUNT` float DEFAULT NULL,
  `EXCHANGEDATE` date DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `currencyexchange`
--

INSERT INTO `currencyexchange` (`CURRENCYEXCODE`, `CURRENCYCODESOURCE`, `CURRENCYCODEDESTINATION`, `AMOUNT`, `EXCHANGEDATE`, `CREATEDATE`) VALUES
(1, 'VND', 'USD', 0.0000449, '2016-07-20', '2016-07-20 00:00:00'),
(2, 'USD', 'VND', 222222, '2016-07-20', '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CUSTOMERCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHORTNAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CUSTOMERCODE`, `CREATOR`, `SHORTNAME`, `LONGNAME`, `ADDRESS`, `TEL`, `FAX`, `TAXNO`, `STATUS`, `CREATEDATE`, `REMARK`) VALUES
('321', 'admin', '213', NULL, '', '', '', '', 'Active', '2016-10-27 13:47:44', ''),
('abc', 'admin', '13', '', 'SKLDJA', '0849857', '784936783', 'KJFSKGJ', 'Active', '2016-10-07 15:54:36', ''),
('Ahmad Abbas', 'admin', 'Ahmad Abbas', 'Ahmad Abbas', 'Saudi Arabic', '359848349', '546575688', '234345342', 'Active', '2016-09-22 15:22:02', ''),
('Asafa', 'admin', 'Asafa', 'Asafa Mohamed', 'Palestine', '088329485', '348573722', '324532453', 'Active', '2016-07-20 00:00:00', ''),
('Babak Payami', 'admin', 'Babak Payami', 'Babak Payami', 'Egypt', '938294918', '214985894', '439825382', 'Active', '2016-09-22 15:23:35', ''),
('Manuel', 'admin', 'Manuel', 'Manuel Siris', 'Saudi Arabic', '1245435', '5435423', '3453245', 'Active', '2016-09-17 02:01:06', ''),
('Saquiri', 'admin', 'Saquiri', 'Samir Saquiri', 'Israel', '243435342', '541345', '34253425', 'In-Active', '2016-07-20 00:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `customeraccountinformation`
--

CREATE TABLE `customeraccountinformation` (
  `CUSTOMERACCOUNTINFOCODE` int(11) NOT NULL,
  `CUSTOMERCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANKNAME` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANKBRANCH` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCOUNTNUMBER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SWIFTCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `customeraccountinformation`
--

INSERT INTO `customeraccountinformation` (`CUSTOMERACCOUNTINFOCODE`, `CUSTOMERCODE`, `BANKNAME`, `BANKBRANCH`, `ACCOUNTNUMBER`, `ADDRESS`, `SWIFTCODE`, `CREATOR`, `CREATEDATE`) VALUES
(1, 'abc', 'Dong A', 'HCM', '98745889285', 'Tp.HCM', '9w485', 'admin', '2016-10-07 15:54:37');

-- --------------------------------------------------------

--
-- Table structure for table `customercontact`
--

CREATE TABLE `customercontact` (
  `CUSCONTACTCODE` int(11) NOT NULL,
  `CUSTOMERCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` timestamp NULL DEFAULT NULL,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `customercontact`
--

INSERT INTO `customercontact` (`CUSCONTACTCODE`, `CUSTOMERCODE`, `CREATOR`, `NAME`, `EMAIL`, `CREATEDATE`, `TEL`) VALUES
(1, 'Asafa', 'admin', 'Asafa Mohamed', 'asafa@gmail.com', '2016-07-19 03:00:00', ''),
(2, 'Saquiri', 'admin', 'Samir Saquiri', 'saquiri@gmail.com', '2016-07-19 03:00:00', ''),
(3, 'Asafa', 'admin', 'Alexander Thien', 'alexthien@gmail.com', '2016-09-22 01:17:37', ''),
(4, 'Manuel', 'admin', 'Manuel', 'manuel@gmail.com', '2016-09-22 01:18:34', ''),
(5, 'Ahmad Abbas', 'admin', 'Ahmad Harris', 'harris@gmail.com', '2016-09-22 01:22:02', ''),
(6, 'Ahmad Abbas', 'admin', 'Ahmad Abbas', 'amas@gmail.com', '2016-09-22 01:22:02', ''),
(7, 'Babak Payami', 'admin', 'Babak Payami', 'babak@gmail.com', '2016-09-22 01:23:35', ''),
(8, 'abc', 'admin', 'Nguyen Van A', 'a@yahoo.com', NULL, '93486743896'),
(9, 'abc', 'admin', 'Nguyen van b', 'b@gmail.com', NULL, '925872857');

-- --------------------------------------------------------

--
-- Table structure for table `destination`
--

CREATE TABLE `destination` (
  `DESTINATIONCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `destination`
--

INSERT INTO `destination` (`DESTINATIONCODE`, `CREATOR`, `DESCRIPTION`, `CREATEDATE`) VALUES
('ABC', 'admin', 'Afghanistan', '2016-10-07 16:07:10'),
('Bangkok', 'admin', 'Thailand', '2016-07-20 00:00:00'),
('Dubai', 'admin', 'UAE', '2016-07-20 00:00:00'),
('HoChiMinh', 'admin', 'VietName', '2016-07-20 00:00:00'),
('Jerusalem', 'admin', 'Israel', '2016-07-20 00:00:00'),
('Riyadh', 'admin', 'Saudi Arabia', '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `estimatetime`
--

CREATE TABLE `estimatetime` (
  `ESTIMATETIMECODE` int(11) NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PICONPLETION` int(11) DEFAULT NULL,
  `PACKINGACCDELV` int(11) DEFAULT NULL,
  `MANUACCDELV` int(11) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `estimatetime`
--

INSERT INTO `estimatetime` (`ESTIMATETIMECODE`, `CREATOR`, `PICONPLETION`, `PACKINGACCDELV`, `MANUACCDELV`, `CREATEDATE`) VALUES
(1, 'admin', 7, 7, 7, '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `externalaccessorystock`
--

CREATE TABLE `externalaccessorystock` (
  `EXTERNALACCESSORYSTOCKCODE` int(11) NOT NULL,
  `ORDERSHEETNO` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AVAILABLEQTY` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `externalaccessorystock`
--

INSERT INTO `externalaccessorystock` (`EXTERNALACCESSORYSTOCKCODE`, `ORDERSHEETNO`, `ACCESSORYCODE`, `FACTORYCODE`, `AVAILABLEQTY`) VALUES
(15, 'Lot1_3', NULL, NULL, 13300);

-- --------------------------------------------------------

--
-- Table structure for table `fabricinformation`
--

CREATE TABLE `fabricinformation` (
  `FABRICNO` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `FABRICITEM` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CUSTOMERCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FABRICSUPCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `VOUCHERSENTDATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fabricinformation`
--

INSERT INTO `fabricinformation` (`FABRICNO`, `FABRICITEM`, `CUSTOMERCODE`, `FABRICSUPCODE`, `FACTORYCODE`, `CURRENCYCODE`, `WIDTHCODE`, `CREATOR`, `ISCHORI`, `COMPONENT`, `ESTIMATEDELVDATE`, `ACTUALDELVDATE`, `FABRICINVOICENO`, `FABRICIMGURL`, `REMARK`, `CREATEDATE`, `AGENTCODE`, `VOUCHERRECEIVEDDATE`, `VOUCHERSENTDATE`) VALUES
('A1', '255', 'Asafa', 'FABRICSUP0001', 'FAC0001', 'USD', '4.4 inches', 'admin', 1, 'Kiet', '2016-08-30 00:00:00', '2016-08-30 00:00:00', 'FInvoice1', NULL, NULL, '2016-08-28 00:00:00', NULL, NULL, NULL),
('A2', '321', 'Saquiri', 'FABRICSUP0002', 'FAC0001', 'USD', '4.4 inches', 'admin', 0, 'Duy', '2016-08-30 00:00:00', '2016-08-31 00:00:00', 'FInvoice2', NULL, NULL, '2016-08-30 00:00:00', NULL, NULL, NULL),
('A3', '123', 'Asafa', 'Duane Reade', 'FAC0001', 'USD', '4.4 inches', 'admin', 0, '123', '2016-10-03 07:00:00', '2016-11-19 07:00:00', 'FE213', NULL, '213', '2016-11-24 15:01:54', NULL, '2016-10-27 07:00:00', '2016-11-15 07:00:00'),
('a5', '', '321', '432', 'FAC0002', 'USD', '4.4 inches', 'admin', 0, '', NULL, NULL, '', NULL, '', '2016-11-24 15:02:32', NULL, '2016-10-27 07:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `fabricinformationdetail`
--

CREATE TABLE `fabricinformationdetail` (
  `FABRICNO` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `UNITPRICE` float(20,3) DEFAULT NULL,
  `YARDINBL` double DEFAULT NULL,
  `IMGURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fabricinformationdetail`
--

INSERT INTO `fabricinformationdetail` (`FABRICNO`, `COLORCODE`, `CREATOR`, `UNITPRICE`, `YARDINBL`, `IMGURL`, `CREATEDATE`) VALUES
('A1', 'CB', 'admin', 5.000, 2000, NULL, '2016-08-30 00:00:00'),
('A1', 'CR', 'admin', 5.000, 2000, NULL, '2016-08-30 00:00:00'),
('A2', 'CB', 'admin', 5.000, 2000, NULL, '2016-08-30 00:00:00'),
('A2', 'CR', 'admin', 3.000, 1850, NULL, '2016-08-30 00:00:00'),
('A3', 'CB', 'admin', 100000.000, 5000, NULL, '2016-11-24 15:01:54'),
('A3', 'CBLK', 'admin', 10000.000, 5000, NULL, '2016-11-24 15:01:54'),
('a5', 'CB', 'admin', 455.000, 45, NULL, '2016-11-24 15:02:32');

-- --------------------------------------------------------

--
-- Table structure for table `fabricsupplier`
--

CREATE TABLE `fabricsupplier` (
  `FABRICSUPCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHORTNAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fabricsupplier`
--

INSERT INTO `fabricsupplier` (`FABRICSUPCODE`, `CREATOR`, `SHORTNAME`, `LONGNAME`, `ADDRESS`, `TEL`, `FAX`, `TAXNO`, `STATUS`, `CREATEDATE`, `REMARK`) VALUES
('432', 'admin', '432', '', '', '', '', '', 'Active', '2016-10-27 13:52:51', ''),
('Duane Reade', 'admin', 'Duane Reade', 'Duane Reade', 'Japan', '242142142', '132522314', '421424214', 'Active', '2016-09-22 15:39:10', ''),
('FABRICSUP0001', 'admin', 'Blaupunkt', 'Blaupunkt Company', 'Japan', '083242232', '31243214', '143124', 'Active', '2016-07-20 00:00:00', ''),
('FABRICSUP0002', 'admin', 'Crabtree & Evelyn', 'Crabtree & Evelyn', 'Viet Nam', '083242232', '31243214', '143124', 'Active', '2016-07-20 00:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `fabricsuppliercontact`
--

CREATE TABLE `fabricsuppliercontact` (
  `FABRICSUPPLIERCONTACTCODE` int(11) NOT NULL,
  `FABRICSUPPLIERCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fabricsuppliercontact`
--

INSERT INTO `fabricsuppliercontact` (`FABRICSUPPLIERCONTACTCODE`, `FABRICSUPPLIERCODE`, `CREATOR`, `NAME`, `EMAIL`, `CREATEDATE`, `TEL`) VALUES
(1, 'FABRICSUP0001', 'admin', 'Võ Trung Hiếu', 'hieuvt@blaupunkt.com', '2016-07-20 00:00:00', ''),
(2, 'FABRICSUP0001', 'admin', 'Trần Thế Phi', 'phitt@blaupunkt.com', '2016-09-22 15:30:59', ''),
(3, 'FABRICSUP0002', 'admin', 'Nguyễn Khắc Duy', 'duynk@cne.com', '2016-09-22 15:35:13', ''),
(4, 'Duane Reade', 'admin', 'Duane', 'duane@dr.com', '2016-09-22 15:39:10', '');

-- --------------------------------------------------------

--
-- Table structure for table `factory`
--

CREATE TABLE `factory` (
  `FACTORYCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHORTNAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `factory`
--

INSERT INTO `factory` (`FACTORYCODE`, `CREATOR`, `SHORTNAME`, `LONGNAME`, `ADDRESS`, `TEL`, `FAX`, `TAXNO`, `STATUS`, `CREATEDATE`, `REMARK`) VALUES
('Amdahl Corporation', 'admin', 'Amdahl Corporation', 'Amdahl Corporation', 'Viet Nam', '412422423', '214214241', '153321421', 'Active', '2016-09-22 15:26:17', ''),
('FAC0001', 'admin', 'TAN VIET', 'TAN VIET FACTORY', 'Viet Nam', '213214', '1231253543', '143124', 'Active', '2016-07-20 00:00:00', ''),
('FAC0002', 'admin', 'DAI VIET', 'DAI VIET GARMENT JOINT STOCK CO', '62 Tan Thanh St, Tan Thanh ward, Tan Phu dist, Ho Chi Minh city', '3 8496016', '38429860', '143124', 'Active', '2016-07-20 00:00:00', ''),
('FAC0003', 'admin', 'MONNARY', 'MONARY FACTORY', 'Viet Nam', '083242232', '1231253543', '143124', 'Active', '2016-07-20 00:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `factoryaccountinformation`
--

CREATE TABLE `factoryaccountinformation` (
  `FACTORYACCOUNTINFOCODE` int(11) NOT NULL,
  `FACTORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANKNAME` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANKBRANCH` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCOUNTNUMBER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SWIFTCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `factoryaccountinformation`
--

INSERT INTO `factoryaccountinformation` (`FACTORYACCOUNTINFOCODE`, `FACTORYCODE`, `BANKNAME`, `BANKBRANCH`, `ACCOUNTNUMBER`, `ADDRESS`, `SWIFTCODE`, `CREATOR`, `CREATEDATE`) VALUES
(1, 'Amdahl Corporation', 'USBank', 'New York', '3683893', '123 New York', 'NY16165USA', 'admin', '2016-10-01 15:32:20');

-- --------------------------------------------------------

--
-- Table structure for table `factorycontact`
--

CREATE TABLE `factorycontact` (
  `FACTORYCONTACTCODE` int(11) NOT NULL,
  `FACTORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `factorycontact`
--

INSERT INTO `factorycontact` (`FACTORYCONTACTCODE`, `FACTORYCODE`, `CREATOR`, `NAME`, `EMAIL`, `CREATEDATE`, `TEL`) VALUES
(1, 'FAC0001', 'admin', 'Dai Viet', 'daiviet@col.com', '2016-08-17 00:00:00', ''),
(2, 'FAC0003', 'admin', 'Tan Duc', 'tanduc@company.com', '2016-08-18 00:00:00', ''),
(3, 'FAC0002', 'admin', 'Duy Kh?nh', 'duykhanh@daiviet.com.vn', '2016-08-21 00:00:00', ''),
(4, 'Amdahl Corporation', 'admin', 'Amdahl Corporation', 'amdahl@amdahl.com', '2016-09-22 15:26:17', '');

-- --------------------------------------------------------

--
-- Table structure for table `fpi`
--

CREATE TABLE `fpi` (
  `FPICODE` int(11) NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RECEIVEDDATE` datetime DEFAULT NULL,
  `CUSTOMERCONFIRMATIONSTATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fpi`
--

INSERT INTO `fpi` (`FPICODE`, `CREATOR`, `LOTNUMBER`, `RECEIVEDDATE`, `CUSTOMERCONFIRMATIONSTATUS`, `CREATEDATE`, `VERSION`) VALUES
(1, 'admin', 'Lot1', '2016-08-31 00:00:00', 'No', '2016-08-22 00:00:00', 1),
(2, 'admin', 'Lot1', '2016-08-31 00:00:00', 'Yes', '2016-08-26 00:00:00', 2),
(3, 'admin', 'Lot2', '2016-08-31 00:00:00', 'Yes', '2016-08-24 00:00:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `fpidetail`
--

CREATE TABLE `fpidetail` (
  `FPIDETAILCODE` int(11) NOT NULL,
  `FPICODE` int(11) DEFAULT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `FPIVALUE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fpidetail`
--

INSERT INTO `fpidetail` (`FPIDETAILCODE`, `FPICODE`, `COLORCODE`, `GARMENTSTYLECODE`, `SIZECODE`, `FPIVALUE`) VALUES
(34, 3, 'CR', 'South', 4, 945),
(35, 2, 'CB', 'Middle East', 14, 750),
(36, 2, 'CB', 'Northen West', 1, 500),
(37, 2, 'CR', 'South', 1, 500),
(38, 2, 'CB', 'Middle East', 20, 500);

-- --------------------------------------------------------

--
-- Table structure for table `function`
--

CREATE TABLE `function` (
  `FUNCTIONID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `FUNCTIONNAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `function`
--

INSERT INTO `function` (`FUNCTIONID`, `FUNCTIONNAME`, `DESCRIPTION`) VALUES
('Accessory Consumption', 'Access Wasted Percentage Management Page', 'Access Wasted Percentage Management Page'),
('Accessory Group', 'Access Accessory Group Management Page', 'Access Accessory Group Management Page'),
('Accessory Information', 'Access Accessory Information Management Page', 'Access Accessory Information Management Page'),
('Accessory Order Signature', 'Access Accessory Signature Management Page', 'Access Accessory Signature Management Page'),
('Accessory Price', 'Access Accessory Price Management Page', 'Access Accessory Price Management Page'),
('Accessory Supplier', 'Access Accessory Supplier Management Page', 'Access Accessory Supplier Management Page'),
('Agent Management', 'Access Chori Office Management Page', 'Access Chori OfficeManagement Page'),
('Color Management', 'Access Color Management Page', 'Access Color Management Page'),
('Container Type', 'Access Container Type Management Page', 'Access Container Type Management Page'),
('Currency Exchange', 'Access Currency Exchange Management Page', 'Access Currency Exchange Management Page'),
('Currency Management', 'Access Currency Management Page', 'Access Currency Management Page'),
('Customer Management', 'Access Customer Management Page', 'Access Customer Management Page'),
('Destination Management', 'Access Destination Management Page', 'Access Destination Management Page'),
('ETOC Management', 'Access ETOC Management Page', 'Access ETOC Management Page'),
('Fabric Information', 'Access Fabric Information Management Page', 'Access Fabric Information Management Page'),
('Fabric Supplier', 'Access Fabric Supplier Management Page', 'Access Fabric Supplier Management Page'),
('Factory Management', 'Access Factory Management Page', 'Access Factory Management Page'),
('FPI Information', 'Access FPI Information Management Page', 'Access FPI Information Management Page'),
('Garment Consumption', 'Access Garment Consumption Management Page', 'Access Garment Consumption Management Page'),
('Garment Kind', 'Access Garment Kind Management Page', 'Access Garment Kind Management Page'),
('Garment Style', 'Access Garment Style Management Page', 'Access Garment Style Management Page'),
('Groups & Functions', 'Access Groups & Functions Management Page', 'Access Groups & Functions Management Page'),
('Order General Accessories', 'Access Order General Accessories Page', 'Access Order General Accessories Page'),
('Packing Guide', 'Access Packing Guide Management', 'Access Packing Guide Management'),
('PI Information', 'Access PI Information Management Page', 'Access PI Information Management Page'),
('RFPI Information', 'Access RFPI Information Management Page', 'Access RFPI Information Management Page'),
('Shipping Line', 'Access Shipping Line Management Page', 'Access Shipping Line Management Page'),
('Size Management', 'Access Size Management Page', 'Access Size Management Page'),
('Type Management', 'Access Size Group Management Page', 'Access Size Group Management Page'),
('Unit Management', 'Access Unit Management Page', 'Access Unit Management Page'),
('User Management', 'Access User Management Page', 'Access User Management Page'),
('Width Management', 'Access Width Management Page', 'Access Width Management Page');

-- --------------------------------------------------------

--
-- Table structure for table `garmentconsumption`
--

CREATE TABLE `garmentconsumption` (
  `GARMENTCONSUMPTIONCODE` int(11) NOT NULL,
  `GARMENTSTYLECODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CUSTOMERCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `garmentconsumption`
--

INSERT INTO `garmentconsumption` (`GARMENTCONSUMPTIONCODE`, `GARMENTSTYLECODE`, `DESCRIPTION`, `CREATOR`, `CUSTOMERCODE`, `SIZECODE`, `CREATEDATE`) VALUES
(3, 'Middle East', '234', 'admin', 'Asafa', 1, '2016-10-01 16:13:35'),
(4, 'Northen West', '', 'admin', 'Asafa', 1, '2016-11-19 16:48:55'),
(5, 'South', '', 'admin', 'Asafa', 1, '2016-11-19 16:49:32'),
(6, 'Middle East', '', 'admin', 'Asafa', 20, '2016-11-25 13:12:09'),
(7, 'Middle East', '', 'admin', 'Asafa', 37, '2016-11-25 13:14:45'),
(8, 'Middle East', '', 'admin', 'Asafa', 36, '2016-11-25 13:15:06');

-- --------------------------------------------------------

--
-- Table structure for table `garmentconsumptiondetail`
--

CREATE TABLE `garmentconsumptiondetail` (
  `GARMENTCONSUMPTIONDETAILCODE` int(11) NOT NULL,
  `GARMENTCONSUMPTIONCODE` int(11) DEFAULT NULL,
  `WIDTHCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONVALUE` float DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `garmentconsumptiondetail`
--

INSERT INTO `garmentconsumptiondetail` (`GARMENTCONSUMPTIONDETAILCODE`, `GARMENTCONSUMPTIONCODE`, `WIDTHCODE`, `CREATOR`, `CONVALUE`, `CREATEDATE`) VALUES
(5, 3, '4.4 inches', 'admin', 4.2, '2016-10-01 16:13:35'),
(6, 3, '5.5 inches', 'admin', 3.2, '2016-10-01 16:13:35'),
(7, 4, '5.5 inches', 'admin', 2, '2016-11-19 16:48:55'),
(8, 4, '4.4 inches', 'admin', 1, '2016-11-19 16:48:55'),
(9, 5, '5.5 inches', 'admin', 2, '2016-11-19 16:49:32'),
(10, 5, '4.4 inches', 'admin', 2, '2016-11-19 16:49:32'),
(11, 6, '4.4 inches', 'admin', 0, '2016-11-25 13:12:10'),
(12, 6, '5.5 inches', 'admin', 0, '2016-11-25 13:12:10'),
(13, 6, '3.3 onches', 'admin', 0, '2016-11-25 13:12:10'),
(14, 8, '4.4 inches', 'admin', 1, '2016-11-25 13:15:06'),
(15, 8, '5.5 inches', 'admin', 1, '2016-11-25 13:15:06'),
(16, 8, '3.3 onches', 'admin', 1, '2016-11-25 13:15:06'),
(20, 3, '6.6 inches', 'admin', 0, '2016-11-25 15:21:03'),
(21, 4, '6.6 inches', 'admin', 0, '2016-11-25 15:21:04'),
(22, 5, '6.6 inches', 'admin', 0, '2016-11-25 15:21:04'),
(23, 6, '6.6 inches', 'admin', 0, '2016-11-25 15:21:04'),
(24, 7, '6.6 inches', 'admin', 0, '2016-11-25 15:21:04'),
(25, 8, '6.6 inches', 'admin', 2, '2016-11-25 15:21:04');

-- --------------------------------------------------------

--
-- Table structure for table `garmentkind`
--

CREATE TABLE `garmentkind` (
  `GARMENTKINDCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `garmentkind`
--

INSERT INTO `garmentkind` (`GARMENTKINDCODE`, `CREATOR`, `DESCRIPTION`, `CREATEDATE`) VALUES
('Bra', 'admin', 'Bra', '2016-07-20 00:00:00'),
('Jacket', 'admin', 'Jacket', '2016-07-20 00:00:00'),
('Shirt', 'admin', 'Shirt', '2016-07-20 00:00:00'),
('T-Shirt', 'admin', 'T-Shirt', '2016-07-20 00:00:00'),
('Underwear', 'admin', 'Underwear', '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `garmentstyle`
--

CREATE TABLE `garmentstyle` (
  `GARMENTSTYLECODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `GARMENTKINDCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CUSTOMERCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `IMGURL1` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL2` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL3` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL4` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMGURL5` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SEWINGGUIDE` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PACKINGGUIDE` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REFERPRICE` float(20,3) DEFAULT NULL,
  `CURRENCYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `garmentstyle`
--

INSERT INTO `garmentstyle` (`GARMENTSTYLECODE`, `GARMENTKINDCODE`, `CREATOR`, `FACTORYCODE`, `CUSTOMERCODE`, `DESCRIPTION`, `CREATEDATE`, `IMGURL1`, `IMGURL2`, `IMGURL3`, `IMGURL4`, `IMGURL5`, `SEWINGGUIDE`, `PACKINGGUIDE`, `REFERPRICE`, `CURRENCYCODE`) VALUES
('Middle East', 'Shirt', 'admin', 'FAC0001', 'Asafa', 'Middle East', '2016-07-20 00:00:00', '', '', '', '', '', NULL, NULL, 42000.000, NULL),
('middle east 1', 'Shirt', 'admin', 'FAC0001', 'Asafa', 'Middle East', '2016-10-01 15:52:28', '', '', '', '', '', NULL, NULL, 42000.000, NULL),
('Northen West', 'Shirt', 'admin', 'FAC0001', 'Asafa', 'Middle East', '2016-07-20 00:00:00', '', '', '', '', '', NULL, NULL, 44500.000, NULL),
('South', 'Bra', 'admin', 'FAC0001', 'Asafa', 'Top West', '2016-07-20 00:00:00', '', '', '', '', '', NULL, NULL, 52000.000, NULL),
('Top West', 'T-Shirt', 'admin', 'FAC0002', 'Saquiri', 'Top West', '2016-07-20 00:00:00', '', '', '', '', '', NULL, NULL, 78000.000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `garmentstyleaccessorydetail`
--

CREATE TABLE `garmentstyleaccessorydetail` (
  `GARMENTSTYLEACCESSORYDETAILCODE` int(11) NOT NULL,
  `GARMENTSTYLECODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `USEDVALUE` float DEFAULT NULL,
  `ISCONFIGBYTYPE` tinyint(1) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `garmentstyleaccessorydetail`
--

INSERT INTO `garmentstyleaccessorydetail` (`GARMENTSTYLEACCESSORYDETAILCODE`, `GARMENTSTYLECODE`, `ACCESSORYCODE`, `SIZECODE`, `CREATOR`, `USEDVALUE`, `ISCONFIGBYTYPE`, `CREATEDATE`) VALUES
(1, 'Middle East', 'BTNR', 3, 'admin', 7, NULL, '2016-08-22 00:00:00'),
(2, 'Northen West', 'BX1', 1, 'admin', 6, NULL, '2016-08-14 00:00:00'),
(4, 'Middle East', 'BX3', 1, 'admin', 3, NULL, '2016-10-01 15:50:30'),
(5, 'Middle East', 'BX3', 3, 'admin', 3, NULL, '2016-10-01 15:50:38'),
(6, 'middle east 1', 'BTNR', 3, 'admin', 7, NULL, '2016-10-01 15:52:28'),
(7, 'middle east 1', 'BTNR', 14, 'admin', 213, NULL, '2016-10-01 15:52:28'),
(8, 'middle east 1', 'BX3', 14, 'admin', 3, NULL, '2016-10-01 15:52:28'),
(9, 'middle east 1', 'BX3', 20, 'admin', 3, NULL, '2016-10-01 15:52:28'),
(10, 'Northen West', 'BX3', 1, 'admin', 4, NULL, '2016-10-14 16:58:41'),
(11, 'South', 'BTNR', 1, 'admin', 12, NULL, '2016-10-25 17:55:15'),
(12, 'South', 'BTNR', 2, 'admin', 34, NULL, '2016-10-25 17:55:21'),
(13, 'Middle East', 'BX3', 14, 'admin', 3, NULL, '2016-11-19 13:43:07'),
(14, 'Middle East', 'BX3', 20, 'admin', 5, NULL, '2016-11-19 13:43:30'),
(15, 'Middle East', 'BTNR', 14, 'admin', 5, NULL, '2016-11-19 13:46:54'),
(16, 'Middle East', 'BTNR', 20, 'admin', 10, NULL, '2016-11-19 13:47:01');

-- --------------------------------------------------------

--
-- Table structure for table `logofchange`
--

CREATE TABLE `logofchange` (
  `LOGOFCHANGECODE` int(11) NOT NULL,
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
  `ATTACHEDFILEURL10` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orderexternalaccessory`
--

CREATE TABLE `orderexternalaccessory` (
  `ORDERSHEETNO` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ACCSUPPLIERCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDERQUANTITY` int(11) DEFAULT NULL,
  `ACTUALDELVQUANTITY` int(11) DEFAULT NULL,
  `ORDERDATE` datetime DEFAULT NULL,
  `ESTIMATEDELVDATE` datetime DEFAULT NULL,
  `ACTUALDELVDATE` datetime DEFAULT NULL,
  `STATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PAYMENTSTATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRICE` float(20,3) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `UNITPRICE` float(20,3) DEFAULT '0.000'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `orderexternalaccessory`
--

INSERT INTO `orderexternalaccessory` (`ORDERSHEETNO`, `ACCSUPPLIERCODE`, `FACTORYCODE`, `ACCESSORYCODE`, `CREATOR`, `ORDERQUANTITY`, `ACTUALDELVQUANTITY`, `ORDERDATE`, `ESTIMATEDELVDATE`, `ACTUALDELVDATE`, `STATUS`, `PAYMENTSTATUS`, `REMARK`, `PRICE`, `CREATEDATE`, `UNITPRICE`) VALUES
('Lot1_1', 'AS0002', 'FAC0001', 'BTNR', 'admin', 10000, 0, '2016-11-24 07:00:00', '2016-11-24 07:00:00', '2016-11-24 07:00:00', '', 'No', '', 2400.000, '2016-11-24 14:05:34', NULL),
('Lot1_2', 'AS0001', 'FAC0001', 'BTNR', 'admin', 200, 0, '2016-11-24 07:00:00', '2016-11-24 07:00:00', '2016-11-24 07:00:00', '', 'No', '', 0.000, '2016-11-24 14:17:00', NULL),
('Lot1_3', 'AS0001', 'FAC0001', 'BTNR', 'admin', 13300, 0, '2016-11-24 07:00:00', '2016-11-24 07:00:00', '2016-11-24 07:00:00', '', 'No', '', 0.000, '2016-11-24 15:36:31', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `orderinternalaccessory`
--

CREATE TABLE `orderinternalaccessory` (
  `ORDERSHEETNO` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ACCSUPPLIERCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDERDATE` datetime DEFAULT NULL,
  `ESTIMATEDELVDATE` datetime DEFAULT NULL,
  `ACTUALDELVDATE` datetime DEFAULT NULL,
  `STATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `INVOICENUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `UNITPRICE` float(20,3) DEFAULT '0.000'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `orderinternalaccessory`
--

INSERT INTO `orderinternalaccessory` (`ORDERSHEETNO`, `ACCSUPPLIERCODE`, `FACTORYCODE`, `CREATOR`, `ORDERDATE`, `ESTIMATEDELVDATE`, `ACTUALDELVDATE`, `STATUS`, `REMARK`, `CREATEDATE`, `INVOICENUMBER`, `UNITPRICE`) VALUES
('INT241120161', 'AS0001', 'FAC0002', 'admin', NULL, NULL, NULL, 'Not-Ordered', '', '2016-11-24 15:22:53', '', 0.000),
('INT251120161', 'AS0001', 'FAC0002', 'admin', '2016-11-16 07:00:00', '2016-11-02 07:00:00', NULL, 'Not-Ordered', '', '2016-11-25 17:29:57', '', 0.000),
('INT251120162', 'AS0001', 'FAC0001', 'admin', NULL, NULL, NULL, 'Not-Ordered', '', '2016-11-25 17:38:36', '', 0.000),
('osheetno5', 'AS0001', 'FAC0001', 'admin', '2016-08-15 00:00:00', '2016-08-17 00:00:00', '2016-08-18 00:00:00', 'delivered', NULL, '2016-08-11 00:00:00', NULL, 0.000),
('osheetno6', 'AS0003', 'FAC0001', 'admin', '2016-08-22 00:00:00', '2016-08-24 00:00:00', '2016-08-24 00:00:00', 'delivered', NULL, '2016-08-20 00:00:00', NULL, 0.000),
('osheetno7', 'AS0001', 'FAC0001', 'admin', '2016-10-14 00:00:00', '2016-10-14 00:00:00', '2016-10-14 00:00:00', 'delivered', NULL, '2016-10-14 17:17:57', NULL, 0.000),
('osheetno8', 'AS0001', 'FAC0002', 'admin', '2016-10-15 00:00:00', '2016-10-15 00:00:00', '2016-10-13 00:00:00', 'delivered', NULL, '2016-10-15 13:45:48', '24132', 0.000);

-- --------------------------------------------------------

--
-- Table structure for table `orderinternalaccessorydetail`
--

CREATE TABLE `orderinternalaccessorydetail` (
  `ACCESSORYCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ORDERSHEETNO` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDERQUANTITY` int(11) DEFAULT NULL,
  `ACTUALDELVQUANTITY` int(11) DEFAULT NULL,
  `PRICE` float(20,3) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `UNITPRICE` float(20,3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `orderinternalaccessorydetail`
--

INSERT INTO `orderinternalaccessorydetail` (`ACCESSORYCODE`, `ORDERSHEETNO`, `CREATOR`, `ORDERQUANTITY`, `ACTUALDELVQUANTITY`, `PRICE`, `CREATEDATE`, `UNITPRICE`) VALUES
('BTNW', 'osheetno5', 'admin', 2000, 2000, 2000000.000, '2016-08-20 00:00:00', 1000.000),
('BX1', 'INT241120161', 'admin', 200, NULL, 500000.000, '2016-11-24 15:21:23', 2500.000),
('BX1', 'osheetno5', 'admin', 10000, 10000, 5000000.000, '2016-10-01 00:00:00', 500.000),
('BX1', 'osheetno6', 'admin', 10000, 10000, 5000000.000, '2016-08-26 00:00:00', 500.000),
('BX3', 'INT241120161', 'admin', NULL, NULL, NULL, '2016-11-24 15:22:53', NULL),
('BX3', 'osheetno5', 'admin', 10000, 10000, 5000000.000, '2016-10-14 17:46:09', 500.000),
('BX3', 'osheetno6', 'admin', 20000, 20000, 50000000.000, '2016-10-14 17:10:58', 2500.000),
('BX3', 'osheetno7', 'admin', 10000, 10000, 10000000.000, '2016-10-15 13:42:31', 1000.000),
('BX3', 'osheetno8', 'admin', 10000, 10000, 10000000.000, '2016-10-15 13:46:10', 1000.000),
('Keo', 'INT241120161', 'admin', 100, NULL, 20000.000, '2016-11-24 15:21:23', 200.000),
('Keo', 'INT251120161', 'admin', 5000, 5000, 1000000.000, '2016-11-25 17:29:57', 200.000),
('Keo', 'INT251120162', 'admin', 6000, 6000, 1200000.000, '2016-11-25 17:30:25', 200.000);

-- --------------------------------------------------------

--
-- Table structure for table `packingguide`
--

CREATE TABLE `packingguide` (
  `Code` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Description` longtext COLLATE utf8_unicode_ci,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `packingguide`
--

INSERT INTO `packingguide` (`Code`, `Description`, `CREATEDATE`, `CREATOR`) VALUES
('Guide001', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '2016-10-22 15:17:34', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `pi`
--

CREATE TABLE `pi` (
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DESTINATIONCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACTORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CUSTOMER1CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONSIGNEEE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PIGRIDCODE` int(11) DEFAULT NULL,
  `NONEORDERACCESSORIES` tinyint(1) DEFAULT NULL,
  `PIRECEIVEDDATE` datetime DEFAULT NULL,
  `PIESTSHIPDATE` datetime DEFAULT NULL,
  `PIATTACHEDFILEURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PACKINGGUIDE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SEWINGGUIDEURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PACKINGGUIDEURL` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `SHIPPINGSTATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BRANDCODE` int(11) DEFAULT NULL,
  `MFGSTARTEDDATE` datetime DEFAULT NULL,
  `MFGFINISHEDDATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `pi`
--

INSERT INTO `pi` (`LOTNUMBER`, `DESTINATIONCODE`, `FACTORYCODE`, `CUSTOMER1CODE`, `CONSIGNEEE`, `CREATOR`, `PIGRIDCODE`, `NONEORDERACCESSORIES`, `PIRECEIVEDDATE`, `PIESTSHIPDATE`, `PIATTACHEDFILEURL`, `STATUS`, `PACKINGGUIDE`, `SEWINGGUIDEURL`, `PACKINGGUIDEURL`, `REMARK`, `CREATEDATE`, `SHIPPINGSTATUS`, `BRANDCODE`, `MFGSTARTEDDATE`, `MFGFINISHEDDATE`) VALUES
('Lot1', 'Bangkok', 'FAC0001', 'Asafa', 'Asafa', 'admin', 777, 0, '2024-07-03 00:00:00', '2024-07-03 00:00:00', 'New Text DocumentLot11475580880147.txt', 'Normal', NULL, NULL, NULL, 'Remark for Lot1', '2016-08-30 00:00:00', 'Yes', 5, '2018-02-02 00:00:00', '2019-04-02 00:00:00'),
('Lot2', 'Dubai', 'FAC0002', 'Asafa', 'Asafa', 'admin', 1104, 0, '2018-12-04 00:00:00', '2018-06-04 00:00:00', 'UserGuide_IMTALot21475581980102.docx', 'Normal', NULL, NULL, NULL, 'Remark for Lot2', '2016-08-30 00:00:00', 'Yes', 7, '2016-02-10 00:00:00', '2016-04-10 00:00:00'),
('Lot3', 'Bangkok', 'FAC0001', 'Asafa', 'Asafa', 'admin', 111, 0, '2017-09-06 00:00:00', '2017-09-06 00:00:00', NULL, 'Urgent', NULL, 'sewingguide', 'packingguide', 'Remark for Lot3', '2016-10-19 16:28:10', 'No', 6, '2017-09-06 00:00:00', '2017-09-07 00:00:00'),
('Lot4', 'Bangkok', 'FAC0001', 'Asafa', 'Asafa', 'admin', 1134, 0, '2016-09-06 00:00:00', '2016-11-29 00:00:00', NULL, 'Urgent', NULL, 'sewingguide', 'packingguide', 'Remark for Lot4', '2016-10-19 16:28:10', 'No', 6, '2016-11-07 00:00:00', '2016-11-29 00:00:00'),
('Lot5', 'Dubai', 'FAC0001', 'Asafa', 'Asafa', 'admin', 1120, 0, '2016-11-01 00:00:00', '2018-11-06 00:00:00', NULL, 'Urgent', 'Guide001', NULL, NULL, '123', '2016-11-24 14:29:54', NULL, 5, '2016-10-02 00:00:00', '2017-10-11 00:00:00'),
('Lot6', 'Bangkok', 'FAC0001', 'Asafa', 'Asafa', 'admin', 1129, 0, '2017-09-06 00:00:00', '2017-09-06 00:00:00', NULL, 'Urgent', NULL, 'sewingguide', 'packingguide', 'Remark for Lot3', '2016-10-19 16:28:10', 'No', 6, '2017-09-06 00:00:00', '2017-09-07 00:00:00'),
('Lot7', 'Dubai', 'FAC0001', 'Asafa', 'Asafa', 'admin', 1135, 0, '2018-04-07 00:00:00', '2016-06-12 00:00:00', NULL, 'Normal', 'Guide001', NULL, NULL, 'aswqe', '2016-11-26 11:55:26', NULL, 6, '2016-01-11 00:00:00', '2017-04-11 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `piassignexternalaccessory`
--

CREATE TABLE `piassignexternalaccessory` (
  `PIASSIGNEXTERNALACCESSORYCODE` int(11) NOT NULL,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDERSHEETNO` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EXTERNALACCESSORYSTOCKCODE` int(11) DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `UNITPRICE` float(20,3) DEFAULT '0.000'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `piassignexternalaccessory`
--

INSERT INTO `piassignexternalaccessory` (`PIASSIGNEXTERNALACCESSORYCODE`, `LOTNUMBER`, `ACCESSORYCODE`, `ORDERSHEETNO`, `EXTERNALACCESSORYSTOCKCODE`, `CREATOR`, `GARMENTSTYLECODE`, `COLORCODE`, `PIGRIDDETAIL`, `ESTIMATEQTY`, `ORDERQTY`, `STOCKASSIGNQTY`, `ACTUALASSIGNQTY`, `SPECIFICCONSUMPTION`, `SPECIFICEQUIVALENT`, `CREATEDATE`, `GARMENTSTYLEACCESSORYDETAILCODE`, `UNITPRICE`) VALUES
(34, 'Lot1', 'BTNR', 'Lot1_3', NULL, 'admin', 'Middle East', 'CB', 1, 3850, 10000, 200, 4000, NULL, NULL, '2016-11-19 13:47:28', 15, NULL),
(35, 'Lot1', 'BTNR', 'Lot1_3', NULL, 'admin', 'South', 'CR', 4, 6060, 10000, 200, 6000, NULL, NULL, '2016-11-19 13:47:28', 11, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `piassignfabric`
--

CREATE TABLE `piassignfabric` (
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `FABRICNO` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `piassignfabric`
--

INSERT INTO `piassignfabric` (`LOTNUMBER`, `FABRICNO`, `CREATOR`, `CREATEDATE`) VALUES
('Lot1', 'A1', 'admin', '2016-08-30 00:00:00'),
('Lot1', 'A2', 'admin', '2016-08-30 00:00:00'),
('Lot2', 'A2', 'admin', '2016-08-30 00:00:00'),
('Lot5', 'A3', 'admin', '2016-11-24 15:05:10');

-- --------------------------------------------------------

--
-- Table structure for table `piassignfabricdetail`
--

CREATE TABLE `piassignfabricdetail` (
  `PIASSIGNFABRICDETAILCODE` int(11) NOT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FABRICNO` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ASSIGNQUANTITY` int(11) DEFAULT NULL,
  `ASSIGNQTYPERCENT` float(20,3) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `piassignfabricdetail`
--

INSERT INTO `piassignfabricdetail` (`PIASSIGNFABRICDETAILCODE`, `COLORCODE`, `LOTNUMBER`, `FABRICNO`, `GARMENTSTYLECODE`, `ASSIGNQUANTITY`, `ASSIGNQTYPERCENT`, `CREATEDATE`) VALUES
(12345, 'CB', 'Lot1', 'A1', 'Middle East', 800, NULL, '2016-08-30 00:00:00'),
(54321, 'CBLK', 'Lot2', 'A2', 'South', 1355, NULL, '2016-08-30 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `piassigninternalaccessories`
--

CREATE TABLE `piassigninternalaccessories` (
  `PIINTERNALACCESSORIES` int(11) NOT NULL,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSORYCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `piassigninternalaccessories`
--

INSERT INTO `piassigninternalaccessories` (`PIINTERNALACCESSORIES`, `LOTNUMBER`, `ACCESSORYCODE`, `CREATOR`, `CREATEDATE`) VALUES
(84, 'Lot1', 'BX1', 'admin', '2016-09-10 11:00:05'),
(125, 'Lot1', 'BX3', 'admin', '2016-10-19 15:21:35'),
(129, 'Lot3', 'BX1', 'admin', '2016-11-04 17:10:30'),
(130, 'Lot3', 'BX3', 'admin', '2016-11-04 17:10:30'),
(132, 'Lot2', 'BX1', 'admin', '2016-11-04 17:17:24'),
(137, 'Lot2', 'BX3', 'admin', '2016-11-04 17:26:25'),
(159, 'Lot4', 'Keo', 'admin', '2016-11-25 19:01:23'),
(163, 'Lot7', 'Keo', 'admin', '2016-11-26 11:58:55');

-- --------------------------------------------------------

--
-- Table structure for table `piassigninternalaccessoriesdetail`
--

CREATE TABLE `piassigninternalaccessoriesdetail` (
  `PIINTERNALACCDETAILCODE` int(11) NOT NULL,
  `PIINTERNALACCESSORIES` int(11) DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ASSIGNQUANTITY` int(11) DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `piassigninternalaccessoriesdetail`
--

INSERT INTO `piassigninternalaccessoriesdetail` (`PIINTERNALACCDETAILCODE`, `PIINTERNALACCESSORIES`, `GARMENTSTYLECODE`, `COLORCODE`, `ASSIGNQUANTITY`, `SIZECODE`, `CREATEDATE`) VALUES
(55, 84, 'Middle East', 'CB', 1000, 3, '2016-09-10 11:00:05'),
(56, 84, 'Northen West', 'CB', 3000, 1, '2016-09-10 11:00:05'),
(57, 84, 'South', 'CR', 0, 1, '2016-09-10 11:00:05'),
(58, 84, 'Middle East', 'CB', 0, 1, '2016-09-10 11:00:05'),
(145, 125, 'Middle East', 'CB', 0, 3, '2016-10-19 15:21:35'),
(146, 125, 'Northen West', 'CB', 0, 1, '2016-10-19 15:21:35'),
(147, 125, 'South', 'CR', 0, 1, '2016-10-19 15:21:35'),
(148, 125, 'Middle East', 'CB', 0, 1, '2016-10-19 15:21:35'),
(152, 129, 'Middle East', 'CB', 600, 1, '2016-11-04 17:10:30'),
(153, 130, 'Middle East', 'CB', 1515, 1, '2016-11-04 17:10:30'),
(155, 132, 'Middle East', 'CB', 0, 1, '2016-11-04 17:17:24'),
(160, 137, 'Middle East', 'CB', 0, 1, '2016-11-04 17:26:25'),
(265, 159, 'Middle East', 'CB', 1, 34, '2016-11-25 19:01:23'),
(266, 159, 'Middle East', 'CB', 0, 35, '2016-11-25 19:01:23'),
(267, 159, 'Northen West', 'CB', 1, 36, '2016-11-25 19:01:23'),
(268, 159, 'Northen West', 'CB', 1, 35, '2016-11-25 19:01:23'),
(269, 159, 'Northen West', 'CR', 1, 37, '2016-11-25 19:01:23'),
(270, 159, 'Middle East', 'CB', 1, 35, '2016-11-25 19:01:23'),
(276, 163, 'Middle East', 'CB', 200, 34, '2016-11-26 11:58:55'),
(277, 163, 'Middle East', 'CB', 200, 35, '2016-11-26 11:58:55'),
(278, 163, 'Northen West', 'CB', 500, 36, '2016-11-26 11:58:55'),
(279, 163, 'Northen West', 'CB', 200, 35, '2016-11-26 11:58:55'),
(280, 163, 'Northen West', 'CR', 500, 37, '2016-11-26 11:58:55');

-- --------------------------------------------------------

--
-- Table structure for table `piassigninternalaccessoriesoforders`
--

CREATE TABLE `piassigninternalaccessoriesoforders` (
  `PIASSIGNINTERNALACCESSORIESOFORDERSCODE` int(11) NOT NULL,
  `PIINTERNALACCESSORIES` int(11) DEFAULT NULL,
  `ORDERSHEETNO` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDERASSIGNQUANTITY` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `piassigninternalaccessoriesoforders`
--

INSERT INTO `piassigninternalaccessoriesoforders` (`PIASSIGNINTERNALACCESSORIESOFORDERSCODE`, `PIINTERNALACCESSORIES`, `ORDERSHEETNO`, `ORDERASSIGNQUANTITY`) VALUES
(12, 84, 'osheetno6', 4000),
(19, 125, 'osheetno5', 1000),
(21, 129, 'osheetno5', 600),
(22, 130, 'osheetno5', 1515),
(28, 159, 'INT251120162', 2000),
(29, 163, 'INT251120162', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `pigrid`
--

CREATE TABLE `pigrid` (
  `PIGRIDCODE` int(11) NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `pigrid`
--

INSERT INTO `pigrid` (`PIGRIDCODE`, `CREATOR`, `CREATEDATE`) VALUES
(111, 'admin', '2016-10-19 16:28:51'),
(777, 'admin', '2016-08-28 00:00:00'),
(1104, 'admin', '2016-11-19 13:26:41'),
(1120, 'admin', '2016-11-24 14:56:38'),
(1129, NULL, '2016-11-25 16:52:18'),
(1134, 'admin', '2016-11-25 18:48:31'),
(1135, 'admin', '2016-11-26 11:57:49');

-- --------------------------------------------------------

--
-- Table structure for table `pigriddetail`
--

CREATE TABLE `pigriddetail` (
  `PIGRIDDETAIL` int(11) NOT NULL,
  `PIGRIDCODE` int(11) DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BARCODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PCS` int(11) DEFAULT NULL,
  `FAVALUE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `pigriddetail`
--

INSERT INTO `pigriddetail` (`PIGRIDDETAIL`, `PIGRIDCODE`, `SIZECODE`, `GARMENTSTYLECODE`, `COLORCODE`, `BARCODE`, `PCS`, `FAVALUE`) VALUES
(1, 777, 14, 'Middle East', 'CB', NULL, 760, 770),
(2, 777, 1, 'Northen West', 'CB', NULL, 500, 510),
(4, 777, 1, 'South', 'CR', NULL, 500, 505),
(5, 777, 20, 'Middle East', 'CB', NULL, 500, 502),
(60, 1104, 34, 'Middle East', 'CB', NULL, 300, NULL),
(61, 1104, 35, 'Middle East', 'CB', NULL, 150, NULL),
(62, 1104, 36, 'Northen West', 'CB', NULL, 100, NULL),
(63, 1104, 37, 'Northen West', 'CB', NULL, 100, NULL),
(64, 1104, 38, 'Northen West', 'CB', NULL, 150, NULL),
(138, 1120, 34, 'Middle East', 'CB', NULL, 300, NULL),
(139, 1120, 36, 'Northen West', 'CB', NULL, 100, NULL),
(140, 1120, 37, 'Northen West', 'CB', NULL, 100, NULL),
(141, 1120, 38, 'Northen West', 'CB', NULL, 150, NULL),
(178, 1129, 34, 'Middle East', 'CB', 'BC4515', 300, NULL),
(179, 1129, 35, 'Middle East', 'CB', 'BC5656', 150, NULL),
(180, 1129, 36, 'Northen West', 'CB', 'BC1113', 100, NULL),
(181, 1129, 35, 'Northen West', 'CB', 'BC5656', 150, NULL),
(182, 1129, 37, 'Northen West', 'CR', 'BC4515', 100, NULL),
(183, 1129, 38, 'Northen West', 'CR', 'BC4535', 150, NULL),
(206, 1134, 34, 'Middle East', 'CB', '', 300, NULL),
(207, 1134, 35, 'Middle East', 'CB', '', 150, NULL),
(208, 1134, 36, 'Northen West', 'CB', 'BC1113', 100, NULL),
(209, 1134, 35, 'Northen West', 'CB', 'BC5656', 150, NULL),
(210, 1134, 37, 'Northen West', 'CR', 'BC4515', 100, NULL),
(211, 1134, 35, 'Middle East', 'CB', 'BC4535', 250, NULL),
(212, 1135, 34, 'Middle East', 'CB', '', 300, NULL),
(213, 1135, 35, 'Middle East', 'CB', '', 150, NULL),
(214, 1135, 36, 'Northen West', 'CB', 'BC1113', 100, NULL),
(215, 1135, 35, 'Northen West', 'CB', 'BC5656', 150, NULL),
(216, 1135, 37, 'Northen West', 'CR', 'BC4515', 100, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `rfpi`
--

CREATE TABLE `rfpi` (
  `RFPIGRID` int(11) NOT NULL,
  `LOTNUMBER` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RECEIVEDDATE` datetime DEFAULT NULL,
  `CUSTOMERCONFIRMATIONSTATUS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rfpidetail`
--

CREATE TABLE `rfpidetail` (
  `RFPIDETAIL` int(11) NOT NULL,
  `RFPIGRID` int(11) DEFAULT NULL,
  `GARMENTSTYLECODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COLORCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SIZECODE` int(11) DEFAULT NULL,
  `RFPIVALUE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `ROLEID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ROLENAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`ROLEID`, `CREATOR`, `ROLENAME`, `DESCRIPTION`, `CREATEDATE`) VALUES
('AD', 'admin', 'Admin', 'Administrator', '2016-07-20 00:00:00'),
('CT', 'admin', 'Chori Thái', NULL, NULL),
('EM', 'admin', 'Employee', 'Employee', '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `rolefunction`
--

CREATE TABLE `rolefunction` (
  `ROLEID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `FUNCTIONID` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `rolefunction`
--

INSERT INTO `rolefunction` (`ROLEID`, `FUNCTIONID`) VALUES
('AD', 'Accessory Consumption'),
('AD', 'Accessory Group'),
('AD', 'Accessory Information'),
('AD', 'Accessory Order Signature'),
('AD', 'Accessory Price'),
('AD', 'Accessory Supplier'),
('AD', 'Agent Management'),
('AD', 'Color Management'),
('AD', 'Container Type'),
('AD', 'Currency Exchange'),
('AD', 'Currency Management'),
('AD', 'Customer Management'),
('AD', 'Destination Management'),
('AD', 'ETOC Management'),
('AD', 'Fabric Information'),
('AD', 'Fabric Supplier'),
('AD', 'Factory Management'),
('AD', 'FPI Information'),
('AD', 'Garment Consumption'),
('AD', 'Garment Kind'),
('AD', 'Garment Style'),
('AD', 'Groups & Functions'),
('AD', 'Order General Accessories'),
('AD', 'Packing Guide'),
('AD', 'PI Information'),
('AD', 'RFPI Information'),
('AD', 'Shipping Line'),
('AD', 'Size Management'),
('AD', 'Type Management'),
('AD', 'Unit Management'),
('AD', 'User Management'),
('AD', 'Width Management'),
('CT', 'Customer Management');

-- --------------------------------------------------------

--
-- Table structure for table `shippingline`
--

CREATE TABLE `shippingline` (
  `SHIPPINGLINECODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SHORTNAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `LONGNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAXNO` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `shippingline`
--

INSERT INTO `shippingline` (`SHIPPINGLINECODE`, `CREATOR`, `SHORTNAME`, `LONGNAME`, `ADDRESS`, `TEL`, `FAX`, `TAXNO`, `STATUS`, `CREATEDATE`, `REMARK`) VALUES
('SPL001', 'admin', 'Evergreen Marineeqw', 'Evergreen Marine Company', 'Luzhu District, Taoyuan City, Taiwanleqwe', '321214123', '412421433', '214241214', 'Active', '2016-07-07 00:00:00', '123213'),
('SPL002', 'USER0001', 'NYK Line', 'NYK Line Company', 'Marunouchi, Chiyoda-ku, Tokyo 100-0005, Japan', '23414423', '32421445', '22314214', 'Active', '2016-07-08 00:00:00', 'wqewqe'),
('SPL003', 'admin', 'HMM', 'Hyundai Merchant Marine', 'Seoul, South Korea', '321212', '4124214', '214241', 'Active', '2016-07-07 00:00:00', 'wewqe');

-- --------------------------------------------------------

--
-- Table structure for table `shippinglinecontact`
--

CREATE TABLE `shippinglinecontact` (
  `SHIPPINGLINECONTACTCODE` int(11) NOT NULL,
  `SHIPPINGLINECODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `TEL` char(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `shippinglinecontact`
--

INSERT INTO `shippinglinecontact` (`SHIPPINGLINECONTACTCODE`, `SHIPPINGLINECODE`, `CREATOR`, `NAME`, `EMAIL`, `CREATEDATE`, `TEL`) VALUES
(1, 'SPL001', 'admin', 'Radcliff', 'radcliff@gmail.com', '2016-07-08 00:00:00', '123'),
(2, 'SPL001', 'admin', 'Vadik', 'vadik@evergreen.com', '2016-07-08 00:00:00', '5425425'),
(4, 'SPL002', 'admin', 'nykline', 'nykline@nykline.com', '2016-07-08 00:00:00', '4324'),
(6, 'SPL003', 'admin', 'Hyundai Merchant', 'hyundaimarine@hmm.com', '2016-07-08 00:00:00', '435');

-- --------------------------------------------------------

--
-- Table structure for table `size`
--

CREATE TABLE `size` (
  `SIZECODE` int(11) NOT NULL,
  `SIZENAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CUSTOMERCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `GARMENTKINDCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `TYPECODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `size`
--

INSERT INTO `size` (`SIZECODE`, `SIZENAME`, `CUSTOMERCODE`, `GARMENTKINDCODE`, `TYPECODE`, `CREATOR`, `CREATEDATE`) VALUES
(1, '8/14', 'Asafa', 'Bra', 'Boy', 'admin', '2016-07-20 00:00:00'),
(2, '10/14', 'Asafa', 'Bra', 'Child', 'admin', '2016-07-20 00:00:00'),
(3, 'L', 'Saquiri', 'Jacket', 'Boy', 'admin', '2016-07-20 00:00:00'),
(4, 'S', 'Saquiri', 'Jacket', 'Boy', 'admin', '2016-07-20 00:00:00'),
(5, 'XL', 'Saquiri', 'Shirt', 'Men', 'admin', '2016-07-20 00:00:00'),
(6, 'XXL', 'Manuel', 'Shirt', 'Boy', 'admin', '2016-09-21 15:31:52'),
(7, 'XL', 'Manuel', 'Shirt', 'Boy', 'admin', '2016-09-21 15:31:54'),
(12, 'SM', 'Manuel', 'Shirt', 'Child', 'admin', '2016-09-21 15:33:20'),
(14, '12/56', 'Asafa', 'Shirt', 'Men', 'admin', '2016-09-21 16:48:00'),
(20, '24/48', 'Asafa', 'Shirt', 'Child', 'admin', '2016-09-21 17:05:21'),
(26, 'XXL', 'Ahmad Abbas', 'Shirt', 'Boy', 'admin', '2016-09-22 16:04:26'),
(27, 'XL', 'Ahmad Abbas', 'Shirt', 'Boy', 'admin', '2016-09-22 16:04:26'),
(28, 'SM', 'Ahmad Abbas', 'Shirt', 'Child', 'admin', '2016-09-22 16:04:26'),
(29, 'M', 'Ahmad Abbas', 'Shirt', 'Child', 'admin', '2016-09-22 16:04:43'),
(30, '8/14', 'Manuel', 'Bra', 'Boy', 'admin', '2016-10-21 15:02:28'),
(31, '10/14', 'Manuel', 'Bra', 'Child', 'admin', '2016-10-21 15:02:29'),
(33, '24/48', 'Manuel', 'Shirt', 'Child', 'admin', '2016-10-21 15:02:29'),
(34, 'X1', 'Asafa', 'Shirt', 'Boy', 'admin', '2016-11-10 19:34:11'),
(35, 'X2', 'Asafa', 'Shirt', 'Boy', 'admin', '2016-11-10 19:34:11'),
(36, 'X3', 'Asafa', 'Shirt', 'Boy', 'admin', '2016-11-10 19:34:11'),
(37, 'X4', 'Asafa', 'Shirt', 'Boy', 'admin', '2016-11-10 19:34:11'),
(38, 'X5', 'Asafa', 'Shirt', 'Boy', 'admin', '2016-11-14 11:30:14'),
(40, 'ewq213', '321', 'Bra', 'Boy', 'admin', '2016-11-25 02:48:28');

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE `type` (
  `TYPECODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`TYPECODE`, `CREATOR`, `DESCRIPTION`, `CREATEDATE`) VALUES
('Boy', 'admin', 'Boy', '2016-07-20 00:00:00'),
('Child', 'admin', 'Child', '2016-07-20 00:00:00'),
('Men', 'admin', 'Men', '2016-07-20 00:00:00'),
('None', 'admin', 'None', '2016-10-01 15:36:31'),
('Youth', 'admin', 'Youth', '2016-07-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `unit`
--

CREATE TABLE `unit` (
  `UNITCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `unit`
--

INSERT INTO `unit` (`UNITCODE`, `NAME`, `CREATEDATE`) VALUES
('grs', 'Gross', '2016-07-07 00:00:00'),
('inches', 'Inches', '2016-07-20 00:00:00'),
('pcs', 'Piece', '2016-07-07 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `USERNAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ROLEID` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AGENTCODE` int(11) DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PASSWORD` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FIRSTNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LASTNAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAILPASSWORD` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PHONE` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CCMAILSTRING` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`USERNAME`, `ROLEID`, `AGENTCODE`, `CREATOR`, `PASSWORD`, `FIRSTNAME`, `LASTNAME`, `EMAIL`, `EMAILPASSWORD`, `PHONE`, `STATUS`, `CCMAILSTRING`) VALUES
('admin', 'AD', 1, 'admin', '$2a$10$R0sA2XcMYxzarn/jdJ1Sw.NAzzWuLT5LmBZKfTgg7mruhH4ig.2GK', 'Administrator', 'Administrator', 'bleachclone69@gmail.com', 'huancuibap', '01642329994', 'Active', 1),
('thai', 'CT', 1, 'admin', '$2a$10$coVw/Y4NMHzq1RG0vEvSVOB492fqyC3jglHmtrZUkV8CXoboT0s46', '', '', '', NULL, '', 'Active', 0),
('thai1', 'CT', 1, 'admin', '$2a$10$/ZmqRz0UOlvw2CvU2KKne.RkgCgvRSlr9EpUvF4HvAhqKtA49JJim', 'An', 'NGuyen', 'an@gmail.com', NULL, '2587892', 'Active', 0),
('USER0001', 'EM', 1, 'admin', '$2a$10$RqAqtkfGn/xG9B4Rj8HfJubnsZP7mpvASudWopOWxkLyRgxR1JRoG', 'USER0001', 'USER0001', 'USER0001@chori.com.vn', 'USER0001', '01683247657', 'Active', 1),
('USER0002', 'EM', 2, 'admin', '$2a$10$vIehUYdDH7yogwUHd8ghieLzaQgKsOx/.BFLV3pwuu3kcegwPcCca', 'USER0002', 'USER0002', 'USER0002@chori.com.vn', 'USER0002', '09012432485', 'In-Active', 1);

-- --------------------------------------------------------

--
-- Table structure for table `width`
--

CREATE TABLE `width` (
  `WIDTHCODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `UNITCODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WIDTHVALUES` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `width`
--

INSERT INTO `width` (`WIDTHCODE`, `UNITCODE`, `CREATOR`, `WIDTHVALUES`, `CREATEDATE`) VALUES
('3.3 onches', 'grs', 'admin', '3.3', '2016-11-25 13:11:27'),
('4.4 inches', 'inches', 'admin', '4.4', '2016-07-20 00:00:00'),
('5.5 inches', 'inches', 'admin', '5.5', '2016-07-20 00:00:00'),
('6.6 inches', 'grs', 'admin', '6.6', '2016-11-25 15:21:03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accessory`
--
ALTER TABLE `accessory`
  ADD PRIMARY KEY (`ACCESSORYCODE`),
  ADD KEY `FK_ACCESSORY_REFERENCE_CONTAINERUNIT` (`CONTAINERUNITCODE`),
  ADD KEY `FK_ACCESSORY_REFERENCE_ACCESSORYGROUP` (`ACCESSORYGROUPCODE`),
  ADD KEY `FK_ACCESSORY_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_ACCESSORY_REFERENCE_UNIT` (`UNITCODE`),
  ADD KEY `FK_ACCESSORY_REFERENCE_COLOR` (`COLORCODE`);

--
-- Indexes for table `accessoryconsumption`
--
ALTER TABLE `accessoryconsumption`
  ADD PRIMARY KEY (`FACTORYCODE`,`ACCESSORYCODE`),
  ADD KEY `FK_ACCESSORYCONSUMPTION_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_ACCESSORYCONSUMPTION_REFERENCE_ACCESSOR` (`ACCESSORYCODE`);

--
-- Indexes for table `accessoryform`
--
ALTER TABLE `accessoryform`
  ADD PRIMARY KEY (`ACCESSORYFORMCODE`),
  ADD KEY `FK_ACCESSORYFORM_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `accessoryformdetail`
--
ALTER TABLE `accessoryformdetail`
  ADD PRIMARY KEY (`ACCESSORYCODE`,`ACCESSORYFORMCODE`),
  ADD KEY `FK_ACCESSORYFORMDETAIL_REFERENCE_ACCESSORFORM` (`ACCESSORYFORMCODE`),
  ADD KEY `FK_ACCESSORYFORMDETAIL_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `accessorygroup`
--
ALTER TABLE `accessorygroup`
  ADD PRIMARY KEY (`ACCESSORYGROUPCODE`),
  ADD KEY `FK_ACCESSORYGROUP_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `accessoryordersignature`
--
ALTER TABLE `accessoryordersignature`
  ADD PRIMARY KEY (`ACCORDERSIGNCODE`),
  ADD KEY `FK_ACCESSORYORDERSIGNATURE_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `accessoryprice`
--
ALTER TABLE `accessoryprice`
  ADD PRIMARY KEY (`ACCESSORYPRICECODE`),
  ADD KEY `FK_ACCESSORYPRICE_REFERENCE_ACCESSORY` (`ACCESSORYCODE`),
  ADD KEY `FK_ACCESSORYPRICE_REFERENCE_ACCESSORYSUPPLIER` (`ACCSUPPLIERCODE`),
  ADD KEY `FK_ACCESSORYPRICE_REFERENCE_CURRENCY` (`CURRENCYCODE`),
  ADD KEY `FK_ACCESSORYPRICE_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `accessorysupplier`
--
ALTER TABLE `accessorysupplier`
  ADD PRIMARY KEY (`ACCSUPPLIERCODE`),
  ADD KEY `FK_ACCESSORYSUPPLIER_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `accessorysuppliercontact`
--
ALTER TABLE `accessorysuppliercontact`
  ADD PRIMARY KEY (`ACCSUPPLIERCONTACTCODE`),
  ADD KEY `FK_ACCESSORYSUPPLIERCONTACT_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_ACCESSORYSUPPLIERCONTACT_REFERENCE_ACCESSORYSUPPLIER` (`ACCSUPPLIERCODE`);

--
-- Indexes for table `agent`
--
ALTER TABLE `agent`
  ADD PRIMARY KEY (`AGENTCODE`),
  ADD KEY `FK_AGENT_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `agentcontact`
--
ALTER TABLE `agentcontact`
  ADD PRIMARY KEY (`AGENTCONTACTCODE`),
  ADD KEY `FK_AGENTCONTACT_REFERENCE_AGENT` (`AGENTCODE`),
  ADD KEY `FK_AGENTCONTACT_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`BRANDCODE`),
  ADD KEY `FK_BRAND_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_BRAND_REFERENCE_CUSTOMER` (`CUSTOMERCODE`);

--
-- Indexes for table `color`
--
ALTER TABLE `color`
  ADD PRIMARY KEY (`COLORCODE`),
  ADD KEY `FK_COLOR_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `ctnrtype`
--
ALTER TABLE `ctnrtype`
  ADD PRIMARY KEY (`CTNRCODE`),
  ADD KEY `FK_CTNRTYPE_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `currency`
--
ALTER TABLE `currency`
  ADD PRIMARY KEY (`CURRENCYCODE`),
  ADD KEY `FK_CURRENCY_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `currencyexchange`
--
ALTER TABLE `currencyexchange`
  ADD PRIMARY KEY (`CURRENCYEXCODE`),
  ADD KEY `FK_CUREXSOURCE_REFERENCE_CURRENCY` (`CURRENCYCODESOURCE`),
  ADD KEY `FK_CURREXDESTINATION_REFERENCE_CURRENCY` (`CURRENCYCODEDESTINATION`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CUSTOMERCODE`),
  ADD KEY `FK_CUSTOMER_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `customeraccountinformation`
--
ALTER TABLE `customeraccountinformation`
  ADD PRIMARY KEY (`CUSTOMERACCOUNTINFOCODE`),
  ADD KEY `FK_CUSTOMERACCOUNTINFO_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_CUSTOMERACCOUNTINFO_REFERENCE_CUSTOMER` (`CUSTOMERCODE`);

--
-- Indexes for table `customercontact`
--
ALTER TABLE `customercontact`
  ADD PRIMARY KEY (`CUSCONTACTCODE`),
  ADD KEY `FK_CUSTOMER_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  ADD KEY `FK_CUSTOMERCONTACT_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`DESTINATIONCODE`),
  ADD KEY `FK_DESTINAT_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `estimatetime`
--
ALTER TABLE `estimatetime`
  ADD PRIMARY KEY (`ESTIMATETIMECODE`),
  ADD KEY `FK_ESTIMATETIME_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `externalaccessorystock`
--
ALTER TABLE `externalaccessorystock`
  ADD PRIMARY KEY (`EXTERNALACCESSORYSTOCKCODE`),
  ADD KEY `FK_EXTERNALACCSTOCK_REFERENCE_ORDEREXTERNALACCESSORY` (`ORDERSHEETNO`),
  ADD KEY `FK_EXTERNALACCSTOCK_REFERENCE_ACCESSORY` (`ACCESSORYCODE`),
  ADD KEY `FK_EXTERNALACCSTOCK_REFERENCE_FACTORY` (`FACTORYCODE`);

--
-- Indexes for table `fabricinformation`
--
ALTER TABLE `fabricinformation`
  ADD PRIMARY KEY (`FABRICNO`),
  ADD KEY `FK_FABRICINFORMATION_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  ADD KEY `FK_FABRICINFORMATION_REFERENCE_FABRICSUPPLIER` (`FABRICSUPCODE`),
  ADD KEY `FK_FABRICINFORMATION_REFERENCE_FACTORY` (`FACTORYCODE`),
  ADD KEY `FK_FABRICINFORMATION_REFERENCE_CURRENCY` (`CURRENCYCODE`),
  ADD KEY `FK_FABRICINFORMATION_REFERENCE_WIDTH` (`WIDTHCODE`),
  ADD KEY `FK_FABRICINFORMATION_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_FABRICINFORMATION_REFERENCE_AGENT` (`AGENTCODE`);

--
-- Indexes for table `fabricinformationdetail`
--
ALTER TABLE `fabricinformationdetail`
  ADD PRIMARY KEY (`FABRICNO`,`COLORCODE`),
  ADD KEY `FK_FABRICINFORMATIONDEATIL_REFERENCE_COLOR` (`COLORCODE`),
  ADD KEY `FK_FABRICINFORMATIONDETAIL_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `fabricsupplier`
--
ALTER TABLE `fabricsupplier`
  ADD PRIMARY KEY (`FABRICSUPCODE`),
  ADD KEY `FK_FABRICSUPPLIER_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `fabricsuppliercontact`
--
ALTER TABLE `fabricsuppliercontact`
  ADD PRIMARY KEY (`FABRICSUPPLIERCONTACTCODE`),
  ADD KEY `FK_FABRICSUPPLIERCONTACT_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_FABRICSUPPLIERCONTACT_REFERENCE_FABRICSU` (`FABRICSUPPLIERCODE`);

--
-- Indexes for table `factory`
--
ALTER TABLE `factory`
  ADD PRIMARY KEY (`FACTORYCODE`),
  ADD KEY `FK_FACTORY_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `factoryaccountinformation`
--
ALTER TABLE `factoryaccountinformation`
  ADD PRIMARY KEY (`FACTORYACCOUNTINFOCODE`),
  ADD KEY `FK_FACTORYACCOUNTINFO_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_FACTORYACCOUNTINFO_REFERENCE_FACTORY` (`FACTORYCODE`);

--
-- Indexes for table `factorycontact`
--
ALTER TABLE `factorycontact`
  ADD PRIMARY KEY (`FACTORYCONTACTCODE`),
  ADD KEY `FK_FACTORYCONTACT_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_FACTORYCONTACT_REFERENCE_FACTORY` (`FACTORYCODE`);

--
-- Indexes for table `fpi`
--
ALTER TABLE `fpi`
  ADD PRIMARY KEY (`FPICODE`),
  ADD KEY `FK_FPI_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_FPI_REFERENCE_PI` (`LOTNUMBER`);

--
-- Indexes for table `fpidetail`
--
ALTER TABLE `fpidetail`
  ADD PRIMARY KEY (`FPIDETAILCODE`),
  ADD KEY `FK_FPIDETAI_REFERENCE_FPI` (`FPICODE`),
  ADD KEY `FK_FPIDETAI_REFERENCE_COLOR` (`COLORCODE`),
  ADD KEY `FK_FPIDETAI_REFERENCE_GARMENTS` (`GARMENTSTYLECODE`),
  ADD KEY `FK_FPIDETAI_REFERENCE_SIZE` (`SIZECODE`);

--
-- Indexes for table `function`
--
ALTER TABLE `function`
  ADD PRIMARY KEY (`FUNCTIONID`);

--
-- Indexes for table `garmentconsumption`
--
ALTER TABLE `garmentconsumption`
  ADD PRIMARY KEY (`GARMENTCONSUMPTIONCODE`),
  ADD KEY `FK_GARMENTCONSUMPTION_REFERENCE_SIZE` (`SIZECODE`),
  ADD KEY `FK_GARMENTCONSUMPTION_REFERENCE_GARMENTS` (`GARMENTSTYLECODE`),
  ADD KEY `FK_GARMENTCONSUMPTION_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_GARMENTCONSUMPTION_REFERENCE_CUSTOMER` (`CUSTOMERCODE`);

--
-- Indexes for table `garmentconsumptiondetail`
--
ALTER TABLE `garmentconsumptiondetail`
  ADD PRIMARY KEY (`GARMENTCONSUMPTIONDETAILCODE`),
  ADD KEY `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_GARMENTC` (`GARMENTCONSUMPTIONCODE`),
  ADD KEY `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_WIDTH` (`WIDTHCODE`),
  ADD KEY `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `garmentkind`
--
ALTER TABLE `garmentkind`
  ADD PRIMARY KEY (`GARMENTKINDCODE`),
  ADD KEY `FK_GARMENTKIND_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `garmentstyle`
--
ALTER TABLE `garmentstyle`
  ADD PRIMARY KEY (`GARMENTSTYLECODE`),
  ADD KEY `FK_GARMENTSTYLE_REFERENCE_FACTORY` (`FACTORYCODE`),
  ADD KEY `FK_GARMENTSTYLE_REFERENCE_GARMENTKIND` (`GARMENTKINDCODE`),
  ADD KEY `FK_GARMENTSTYLE_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  ADD KEY `FK_GARMENTSTYLE_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `garmentstyleaccessorydetail`
--
ALTER TABLE `garmentstyleaccessorydetail`
  ADD PRIMARY KEY (`GARMENTSTYLEACCESSORYDETAILCODE`),
  ADD KEY `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_GARMENTS` (`GARMENTSTYLECODE`),
  ADD KEY `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_ACCESSOR` (`ACCESSORYCODE`),
  ADD KEY `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_SIZE` (`SIZECODE`),
  ADD KEY `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `logofchange`
--
ALTER TABLE `logofchange`
  ADD PRIMARY KEY (`LOGOFCHANGECODE`),
  ADD KEY `FK_LOGOFCHANGE_REFERENCE_PI` (`LOTNUMBER`);

--
-- Indexes for table `orderexternalaccessory`
--
ALTER TABLE `orderexternalaccessory`
  ADD PRIMARY KEY (`ORDERSHEETNO`),
  ADD KEY `FK_ORDEREXTERNALACCESSORY_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_ORDEREXTERNALACCESSORY_REFERENCE_ACCESSORYSUPPLIER` (`ACCSUPPLIERCODE`),
  ADD KEY `FK_ORDEREXTERNALACCESSORY_REFERENCE_FACTORY` (`FACTORYCODE`),
  ADD KEY `FK_ORDEREXTERNALACCESSORY_REFERENCE_ACCESSORY` (`ACCESSORYCODE`);

--
-- Indexes for table `orderinternalaccessory`
--
ALTER TABLE `orderinternalaccessory`
  ADD PRIMARY KEY (`ORDERSHEETNO`),
  ADD KEY `FK_ORDERINTERNALACCESSORY_REFERENCE_ACCESSORYSUPPLIER` (`ACCSUPPLIERCODE`),
  ADD KEY `FK_ORDERINTERNALACCESSORY_REFERENCE_FACTORY` (`FACTORYCODE`),
  ADD KEY `FK_ORDERINTERNALACCESSORY_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `orderinternalaccessorydetail`
--
ALTER TABLE `orderinternalaccessorydetail`
  ADD PRIMARY KEY (`ACCESSORYCODE`,`ORDERSHEETNO`),
  ADD KEY `FK_ORDERINTERNALACCESSORYDETAIL_REFERENCE_ORDERINTERNALACC` (`ORDERSHEETNO`),
  ADD KEY `FK_ORDERINTERNALACCESSORYDETAIL_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `packingguide`
--
ALTER TABLE `packingguide`
  ADD PRIMARY KEY (`Code`),
  ADD KEY `FK_PACKINGGUIDE_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `pi`
--
ALTER TABLE `pi`
  ADD PRIMARY KEY (`LOTNUMBER`),
  ADD KEY `FK_PI_REFERENCE_PIGRID` (`PIGRIDCODE`),
  ADD KEY `FK_PI_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_PI_REFERENCE_DESTINATION` (`DESTINATIONCODE`),
  ADD KEY `FK_PI_REFERENCE_FACTORY` (`FACTORYCODE`),
  ADD KEY `FK_PICUSTOMER1_REFERENCE_CUSTOMER` (`CUSTOMER1CODE`),
  ADD KEY `FK_PICONSIGNEE_REFERENCE_CUSTOMER` (`CONSIGNEEE`),
  ADD KEY `FK_PIBRAND_REFERENCE_BRAND` (`BRANDCODE`),
  ADD KEY `FK_PI_REFERENCE_PACKINGGUIDE` (`PACKINGGUIDE`);

--
-- Indexes for table `piassignexternalaccessory`
--
ALTER TABLE `piassignexternalaccessory`
  ADD PRIMARY KEY (`PIASSIGNEXTERNALACCESSORYCODE`),
  ADD KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_PI` (`LOTNUMBER`),
  ADD KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_ACCESSORY` (`ACCESSORYCODE`),
  ADD KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_ORDEREXTERNALACCESSORY` (`ORDERSHEETNO`),
  ADD KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_EXTERNALACCSTOCK` (`EXTERNALACCESSORYSTOCKCODE`),
  ADD KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_GARMENTSTYLE` (`GARMENTSTYLECODE`),
  ADD KEY `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_COLOR` (`COLORCODE`),
  ADD KEY `FK_PIASSIGNEXTERNALACC_REFERENCE_GARMENTSTYLEACCDETAIL` (`GARMENTSTYLEACCESSORYDETAILCODE`),
  ADD KEY `FK_PIASSIGNEXTERNALACC_REFERENCE_PIGRIDDETAIL` (`PIGRIDDETAIL`);

--
-- Indexes for table `piassignfabric`
--
ALTER TABLE `piassignfabric`
  ADD PRIMARY KEY (`LOTNUMBER`,`FABRICNO`),
  ADD KEY `FK_PIASSIGN_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_PIASSIGNFABRIC_REFERENCE_FABRICINFORMATION` (`FABRICNO`);

--
-- Indexes for table `piassignfabricdetail`
--
ALTER TABLE `piassignfabricdetail`
  ADD PRIMARY KEY (`PIASSIGNFABRICDETAILCODE`),
  ADD KEY `FK_PIASSIGNFABRICDETAIL_REFERENCE_COLOR` (`COLORCODE`),
  ADD KEY `FK_PIASSIGNFABRICDETAIL_REFERENCE_PIASSIGNFABRIC` (`LOTNUMBER`,`FABRICNO`),
  ADD KEY `FK_PIASSIGNFABRICDETAIL_REFERENCE_GARMENTSTYLE` (`GARMENTSTYLECODE`);

--
-- Indexes for table `piassigninternalaccessories`
--
ALTER TABLE `piassigninternalaccessories`
  ADD PRIMARY KEY (`PIINTERNALACCESSORIES`),
  ADD KEY `FK_PIORDERINTERNALACC_REFERENCE_PI` (`LOTNUMBER`),
  ADD KEY `FK_PIORDERINTERNALACC_REFERENCE_ACCESSORY` (`ACCESSORYCODE`),
  ADD KEY `FK_PIORDERINTERNALACC_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `piassigninternalaccessoriesdetail`
--
ALTER TABLE `piassigninternalaccessoriesdetail`
  ADD PRIMARY KEY (`PIINTERNALACCDETAILCODE`),
  ADD KEY `FK_PIORDERINTERNALACCDEATIL_REFERENCE_PIORDERINTERNALACC` (`PIINTERNALACCESSORIES`),
  ADD KEY `FK_PIORDERINTERNALACCDETAIL_REFERENCE_GARMENTSTYLE` (`GARMENTSTYLECODE`),
  ADD KEY `FK_PIORDERINTERNALACCDETAIL_REFERENCE_SIZE` (`SIZECODE`),
  ADD KEY `FK_PIORDERINTERNALACCDETAIL_REFERENCE_COLOR` (`COLORCODE`);

--
-- Indexes for table `piassigninternalaccessoriesoforders`
--
ALTER TABLE `piassigninternalaccessoriesoforders`
  ADD PRIMARY KEY (`PIASSIGNINTERNALACCESSORIESOFORDERSCODE`),
  ADD KEY `FK_PIASSIGNINTACCORDERS_REF_PIASSIGNINTACC` (`PIINTERNALACCESSORIES`),
  ADD KEY `FK_PIASSIGNINTACCORDER_REF_ORDERINTACC` (`ORDERSHEETNO`);

--
-- Indexes for table `pigrid`
--
ALTER TABLE `pigrid`
  ADD PRIMARY KEY (`PIGRIDCODE`),
  ADD KEY `FK_PIGRID_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `pigriddetail`
--
ALTER TABLE `pigriddetail`
  ADD PRIMARY KEY (`PIGRIDDETAIL`),
  ADD KEY `FK_PIGRIDDE_REFERENCE_SIZE` (`SIZECODE`),
  ADD KEY `FK_PIGRIDDE_REFERENCE_GARMENTS` (`GARMENTSTYLECODE`),
  ADD KEY `FK_PIGRIDDE_REFERENCE_COLOR` (`COLORCODE`),
  ADD KEY `FK_PIGRIDDE_REFERENCE_PIGRID` (`PIGRIDCODE`);

--
-- Indexes for table `rfpi`
--
ALTER TABLE `rfpi`
  ADD PRIMARY KEY (`RFPIGRID`),
  ADD KEY `FK_RFPI_REFERENCE_PI` (`LOTNUMBER`),
  ADD KEY `FK_RFPI_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `rfpidetail`
--
ALTER TABLE `rfpidetail`
  ADD PRIMARY KEY (`RFPIDETAIL`),
  ADD KEY `FK_RFPIDETA_REFERENCE_RFPI` (`RFPIGRID`),
  ADD KEY `FK_RFPIDETA_REFERENCE_GARMENTS` (`GARMENTSTYLECODE`),
  ADD KEY `FK_RFPIDETA_REFERENCE_COLOR` (`COLORCODE`),
  ADD KEY `FK_RFPIDETA_REFERENCE_SIZE` (`SIZECODE`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`ROLEID`),
  ADD KEY `FK_ROLE_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `rolefunction`
--
ALTER TABLE `rolefunction`
  ADD PRIMARY KEY (`ROLEID`,`FUNCTIONID`),
  ADD KEY `FK_ROLEFUNC_REFERENCE_FUNCTION` (`FUNCTIONID`);

--
-- Indexes for table `shippingline`
--
ALTER TABLE `shippingline`
  ADD PRIMARY KEY (`SHIPPINGLINECODE`),
  ADD KEY `FK_SHIPPING_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `shippinglinecontact`
--
ALTER TABLE `shippinglinecontact`
  ADD PRIMARY KEY (`SHIPPINGLINECONTACTCODE`),
  ADD KEY `FK_SHIPPING_REFERENCE_SHIPPING` (`SHIPPINGLINECODE`),
  ADD KEY `FK_SHIPPINGLINECONTACT_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`SIZECODE`),
  ADD KEY `FK_SIZE_REFERENCE_TYPE` (`TYPECODE`),
  ADD KEY `FK_SIZE_REFERENCE_USER` (`CREATOR`),
  ADD KEY `FK_SIZE_REFERENCE_CUSTOMER` (`CUSTOMERCODE`),
  ADD KEY `FK_SIZE_REFERENCE_GARMENTK` (`GARMENTKINDCODE`);

--
-- Indexes for table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`TYPECODE`),
  ADD KEY `FK_TYPE_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`UNITCODE`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`USERNAME`),
  ADD KEY `FK_USER_REFERENCE_ROLE` (`ROLEID`),
  ADD KEY `FK_USER_REFERENCE_AGENT` (`AGENTCODE`),
  ADD KEY `FK_USER_REFERENCE_USER` (`CREATOR`);

--
-- Indexes for table `width`
--
ALTER TABLE `width`
  ADD PRIMARY KEY (`WIDTHCODE`),
  ADD KEY `FK_WIDTH_REFERENCE_UNIT` (`UNITCODE`),
  ADD KEY `FK_WIDTH_REFERENCE_USER` (`CREATOR`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accessoryordersignature`
--
ALTER TABLE `accessoryordersignature`
  MODIFY `ACCORDERSIGNCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `accessoryprice`
--
ALTER TABLE `accessoryprice`
  MODIFY `ACCESSORYPRICECODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `accessorysuppliercontact`
--
ALTER TABLE `accessorysuppliercontact`
  MODIFY `ACCSUPPLIERCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `agent`
--
ALTER TABLE `agent`
  MODIFY `AGENTCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `agentcontact`
--
ALTER TABLE `agentcontact`
  MODIFY `AGENTCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `BRANDCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `currencyexchange`
--
ALTER TABLE `currencyexchange`
  MODIFY `CURRENCYEXCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `customeraccountinformation`
--
ALTER TABLE `customeraccountinformation`
  MODIFY `CUSTOMERACCOUNTINFOCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `customercontact`
--
ALTER TABLE `customercontact`
  MODIFY `CUSCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `estimatetime`
--
ALTER TABLE `estimatetime`
  MODIFY `ESTIMATETIMECODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `externalaccessorystock`
--
ALTER TABLE `externalaccessorystock`
  MODIFY `EXTERNALACCESSORYSTOCKCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `fabricsuppliercontact`
--
ALTER TABLE `fabricsuppliercontact`
  MODIFY `FABRICSUPPLIERCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `factoryaccountinformation`
--
ALTER TABLE `factoryaccountinformation`
  MODIFY `FACTORYACCOUNTINFOCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `factorycontact`
--
ALTER TABLE `factorycontact`
  MODIFY `FACTORYCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `fpi`
--
ALTER TABLE `fpi`
  MODIFY `FPICODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `fpidetail`
--
ALTER TABLE `fpidetail`
  MODIFY `FPIDETAILCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT for table `garmentconsumption`
--
ALTER TABLE `garmentconsumption`
  MODIFY `GARMENTCONSUMPTIONCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `garmentconsumptiondetail`
--
ALTER TABLE `garmentconsumptiondetail`
  MODIFY `GARMENTCONSUMPTIONDETAILCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `garmentstyleaccessorydetail`
--
ALTER TABLE `garmentstyleaccessorydetail`
  MODIFY `GARMENTSTYLEACCESSORYDETAILCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `logofchange`
--
ALTER TABLE `logofchange`
  MODIFY `LOGOFCHANGECODE` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `piassignexternalaccessory`
--
ALTER TABLE `piassignexternalaccessory`
  MODIFY `PIASSIGNEXTERNALACCESSORYCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `piassignfabricdetail`
--
ALTER TABLE `piassignfabricdetail`
  MODIFY `PIASSIGNFABRICDETAILCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54322;
--
-- AUTO_INCREMENT for table `piassigninternalaccessories`
--
ALTER TABLE `piassigninternalaccessories`
  MODIFY `PIINTERNALACCESSORIES` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=164;
--
-- AUTO_INCREMENT for table `piassigninternalaccessoriesdetail`
--
ALTER TABLE `piassigninternalaccessoriesdetail`
  MODIFY `PIINTERNALACCDETAILCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=281;
--
-- AUTO_INCREMENT for table `piassigninternalaccessoriesoforders`
--
ALTER TABLE `piassigninternalaccessoriesoforders`
  MODIFY `PIASSIGNINTERNALACCESSORIESOFORDERSCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `pigrid`
--
ALTER TABLE `pigrid`
  MODIFY `PIGRIDCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1136;
--
-- AUTO_INCREMENT for table `pigriddetail`
--
ALTER TABLE `pigriddetail`
  MODIFY `PIGRIDDETAIL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=217;
--
-- AUTO_INCREMENT for table `rfpi`
--
ALTER TABLE `rfpi`
  MODIFY `RFPIGRID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `rfpidetail`
--
ALTER TABLE `rfpidetail`
  MODIFY `RFPIDETAIL` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `shippinglinecontact`
--
ALTER TABLE `shippinglinecontact`
  MODIFY `SHIPPINGLINECONTACTCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `size`
--
ALTER TABLE `size`
  MODIFY `SIZECODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `accessory`
--
ALTER TABLE `accessory`
  ADD CONSTRAINT `FK_ACCESSORY_REFERENCE_ACCESSORYGROUP` FOREIGN KEY (`ACCESSORYGROUPCODE`) REFERENCES `accessorygroup` (`ACCESSORYGROUPCODE`),
  ADD CONSTRAINT `FK_ACCESSORY_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  ADD CONSTRAINT `FK_ACCESSORY_REFERENCE_CONTAINERUNIT` FOREIGN KEY (`CONTAINERUNITCODE`) REFERENCES `unit` (`UNITCODE`),
  ADD CONSTRAINT `FK_ACCESSORY_REFERENCE_UNIT` FOREIGN KEY (`UNITCODE`) REFERENCES `unit` (`UNITCODE`),
  ADD CONSTRAINT `FK_ACCESSORY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `accessoryconsumption`
--
ALTER TABLE `accessoryconsumption`
  ADD CONSTRAINT `FK_ACCESSORYCONSUMPTION_REFERENCE_ACCESSOR` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  ADD CONSTRAINT `FK_ACCESSORYCONSUMPTION_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  ADD CONSTRAINT `FK_ACCESSORYCONSUMPTION_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `accessoryform`
--
ALTER TABLE `accessoryform`
  ADD CONSTRAINT `FK_ACCESSORYFORM_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `accessoryformdetail`
--
ALTER TABLE `accessoryformdetail`
  ADD CONSTRAINT `FK_ACCESSORYFORMDETAIL_REFERENCE_ACCESSORFORM` FOREIGN KEY (`ACCESSORYFORMCODE`) REFERENCES `accessoryform` (`ACCESSORYFORMCODE`),
  ADD CONSTRAINT `FK_ACCESSORYFORMDETAIL_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  ADD CONSTRAINT `FK_ACCESSORYFORMDETAIL_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `accessorygroup`
--
ALTER TABLE `accessorygroup`
  ADD CONSTRAINT `FK_ACCESSORYGROUP_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `accessoryordersignature`
--
ALTER TABLE `accessoryordersignature`
  ADD CONSTRAINT `FK_ACCESSORYORDERSIGNATURE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `accessoryprice`
--
ALTER TABLE `accessoryprice`
  ADD CONSTRAINT `FK_ACCESSORYPRICE_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  ADD CONSTRAINT `FK_ACCESSORYPRICE_REFERENCE_ACCESSORYSUPPLIER` FOREIGN KEY (`ACCSUPPLIERCODE`) REFERENCES `accessorysupplier` (`ACCSUPPLIERCODE`),
  ADD CONSTRAINT `FK_ACCESSORYPRICE_REFERENCE_CURRENCY` FOREIGN KEY (`CURRENCYCODE`) REFERENCES `currency` (`CURRENCYCODE`),
  ADD CONSTRAINT `FK_ACCESSORYPRICE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `accessorysupplier`
--
ALTER TABLE `accessorysupplier`
  ADD CONSTRAINT `FK_ACCESSORYSUPPLIER_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `accessorysuppliercontact`
--
ALTER TABLE `accessorysuppliercontact`
  ADD CONSTRAINT `FK_ACCESSORYSUPPLIERCONTACT_REFERENCE_ACCESSORYSUPPLIER` FOREIGN KEY (`ACCSUPPLIERCODE`) REFERENCES `accessorysupplier` (`ACCSUPPLIERCODE`),
  ADD CONSTRAINT `FK_ACCESSORYSUPPLIERCONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `agent`
--
ALTER TABLE `agent`
  ADD CONSTRAINT `FK_AGENT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `agentcontact`
--
ALTER TABLE `agentcontact`
  ADD CONSTRAINT `FK_AGENTCONTACT_REFERENCE_AGENT` FOREIGN KEY (`AGENTCODE`) REFERENCES `agent` (`AGENTCODE`),
  ADD CONSTRAINT `FK_AGENTCONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `brand`
--
ALTER TABLE `brand`
  ADD CONSTRAINT `FK_BRAND_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  ADD CONSTRAINT `FK_BRAND_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `color`
--
ALTER TABLE `color`
  ADD CONSTRAINT `FK_COLOR_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `ctnrtype`
--
ALTER TABLE `ctnrtype`
  ADD CONSTRAINT `FK_CTNRTYPE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `currency`
--
ALTER TABLE `currency`
  ADD CONSTRAINT `FK_CURRENCY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `currencyexchange`
--
ALTER TABLE `currencyexchange`
  ADD CONSTRAINT `FK_CUREXSOURCE_REFERENCE_CURRENCY` FOREIGN KEY (`CURRENCYCODESOURCE`) REFERENCES `currency` (`CURRENCYCODE`),
  ADD CONSTRAINT `FK_CURREXDESTINATION_REFERENCE_CURRENCY` FOREIGN KEY (`CURRENCYCODEDESTINATION`) REFERENCES `currency` (`CURRENCYCODE`);

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `FK_CUSTOMER_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `customeraccountinformation`
--
ALTER TABLE `customeraccountinformation`
  ADD CONSTRAINT `FK_CUSTOMERACCOUNTINFO_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  ADD CONSTRAINT `FK_CUSTOMERACCOUNTINFO_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `customercontact`
--
ALTER TABLE `customercontact`
  ADD CONSTRAINT `FK_CUSTOMERCONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`),
  ADD CONSTRAINT `FK_CUSTOMER_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`);

--
-- Constraints for table `destination`
--
ALTER TABLE `destination`
  ADD CONSTRAINT `FK_DESTINAT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `estimatetime`
--
ALTER TABLE `estimatetime`
  ADD CONSTRAINT `FK_ESTIMATETIME_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `externalaccessorystock`
--
ALTER TABLE `externalaccessorystock`
  ADD CONSTRAINT `FK_EXTERNALACCSTOCK_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  ADD CONSTRAINT `FK_EXTERNALACCSTOCK_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  ADD CONSTRAINT `FK_EXTERNALACCSTOCK_REFERENCE_ORDEREXTERNALACCESSORY` FOREIGN KEY (`ORDERSHEETNO`) REFERENCES `orderexternalaccessory` (`ORDERSHEETNO`);

--
-- Constraints for table `fabricinformation`
--
ALTER TABLE `fabricinformation`
  ADD CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_AGENT` FOREIGN KEY (`AGENTCODE`) REFERENCES `agent` (`AGENTCODE`),
  ADD CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_CURRENCY` FOREIGN KEY (`CURRENCYCODE`) REFERENCES `currency` (`CURRENCYCODE`),
  ADD CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  ADD CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_FABRICSUPPLIER` FOREIGN KEY (`FABRICSUPCODE`) REFERENCES `fabricsupplier` (`FABRICSUPCODE`),
  ADD CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  ADD CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`),
  ADD CONSTRAINT `FK_FABRICINFORMATION_REFERENCE_WIDTH` FOREIGN KEY (`WIDTHCODE`) REFERENCES `width` (`WIDTHCODE`);

--
-- Constraints for table `fabricinformationdetail`
--
ALTER TABLE `fabricinformationdetail`
  ADD CONSTRAINT `FK_FABRICINFORMATIONDEATIL_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  ADD CONSTRAINT `FK_FABRICINFORMATIONDETAIL_REFERENCE_FABRICINFORMATION` FOREIGN KEY (`FABRICNO`) REFERENCES `fabricinformation` (`FABRICNO`),
  ADD CONSTRAINT `FK_FABRICINFORMATIONDETAIL_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `fabricsupplier`
--
ALTER TABLE `fabricsupplier`
  ADD CONSTRAINT `FK_FABRICSUPPLIER_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `fabricsuppliercontact`
--
ALTER TABLE `fabricsuppliercontact`
  ADD CONSTRAINT `FK_FABRICSUPPLIERCONTACT_REFERENCE_FABRICSU` FOREIGN KEY (`FABRICSUPPLIERCODE`) REFERENCES `fabricsupplier` (`FABRICSUPCODE`),
  ADD CONSTRAINT `FK_FABRICSUPPLIERCONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `factory`
--
ALTER TABLE `factory`
  ADD CONSTRAINT `FK_FACTORY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `factoryaccountinformation`
--
ALTER TABLE `factoryaccountinformation`
  ADD CONSTRAINT `FK_FACTORYACCOUNTINFO_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  ADD CONSTRAINT `FK_FACTORYACCOUNTINFO_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `factorycontact`
--
ALTER TABLE `factorycontact`
  ADD CONSTRAINT `FK_FACTORYCONTACT_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  ADD CONSTRAINT `FK_FACTORYCONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `fpi`
--
ALTER TABLE `fpi`
  ADD CONSTRAINT `FK_FPI_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`),
  ADD CONSTRAINT `FK_FPI_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `fpidetail`
--
ALTER TABLE `fpidetail`
  ADD CONSTRAINT `FK_FPIDETAI_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  ADD CONSTRAINT `FK_FPIDETAI_REFERENCE_FPI` FOREIGN KEY (`FPICODE`) REFERENCES `fpi` (`FPICODE`),
  ADD CONSTRAINT `FK_FPIDETAI_REFERENCE_GARMENTS` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  ADD CONSTRAINT `FK_FPIDETAI_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`);

--
-- Constraints for table `garmentconsumption`
--
ALTER TABLE `garmentconsumption`
  ADD CONSTRAINT `FK_GARMENTCONSUMPTION_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  ADD CONSTRAINT `FK_GARMENTCONSUMPTION_REFERENCE_GARMENTS` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  ADD CONSTRAINT `FK_GARMENTCONSUMPTION_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`),
  ADD CONSTRAINT `FK_GARMENTCONSUMPTION_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `garmentconsumptiondetail`
--
ALTER TABLE `garmentconsumptiondetail`
  ADD CONSTRAINT `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_GARMENTC` FOREIGN KEY (`GARMENTCONSUMPTIONCODE`) REFERENCES `garmentconsumption` (`GARMENTCONSUMPTIONCODE`),
  ADD CONSTRAINT `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`),
  ADD CONSTRAINT `FK_GARMENTCONSUMPTIONDETAIL_REFERENCE_WIDTH` FOREIGN KEY (`WIDTHCODE`) REFERENCES `width` (`WIDTHCODE`);

--
-- Constraints for table `garmentkind`
--
ALTER TABLE `garmentkind`
  ADD CONSTRAINT `FK_GARMENTKIND_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `garmentstyle`
--
ALTER TABLE `garmentstyle`
  ADD CONSTRAINT `FK_GARMENTSTYLE_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  ADD CONSTRAINT `FK_GARMENTSTYLE_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  ADD CONSTRAINT `FK_GARMENTSTYLE_REFERENCE_GARMENTKIND` FOREIGN KEY (`GARMENTKINDCODE`) REFERENCES `garmentkind` (`GARMENTKINDCODE`),
  ADD CONSTRAINT `FK_GARMENTSTYLE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `garmentstyleaccessorydetail`
--
ALTER TABLE `garmentstyleaccessorydetail`
  ADD CONSTRAINT `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_ACCESSOR` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  ADD CONSTRAINT `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_GARMENTS` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  ADD CONSTRAINT `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`),
  ADD CONSTRAINT `FK_GARMENTSTYLEACCESSORYDETAIL_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `logofchange`
--
ALTER TABLE `logofchange`
  ADD CONSTRAINT `FK_LOGOFCHANGE_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`);

--
-- Constraints for table `orderexternalaccessory`
--
ALTER TABLE `orderexternalaccessory`
  ADD CONSTRAINT `FK_ORDEREXTERNALACCESSORY_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  ADD CONSTRAINT `FK_ORDEREXTERNALACCESSORY_REFERENCE_ACCESSORYSUPPLIER` FOREIGN KEY (`ACCSUPPLIERCODE`) REFERENCES `accessorysupplier` (`ACCSUPPLIERCODE`),
  ADD CONSTRAINT `FK_ORDEREXTERNALACCESSORY_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  ADD CONSTRAINT `FK_ORDEREXTERNALACCESSORY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `orderinternalaccessory`
--
ALTER TABLE `orderinternalaccessory`
  ADD CONSTRAINT `FK_ORDERINTERNALACCESSORY_REFERENCE_ACCESSORYSUPPLIER` FOREIGN KEY (`ACCSUPPLIERCODE`) REFERENCES `accessorysupplier` (`ACCSUPPLIERCODE`),
  ADD CONSTRAINT `FK_ORDERINTERNALACCESSORY_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  ADD CONSTRAINT `FK_ORDERINTERNALACCESSORY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `orderinternalaccessorydetail`
--
ALTER TABLE `orderinternalaccessorydetail`
  ADD CONSTRAINT `FK_ORDERINTERNALACCESSORYDETAIL_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  ADD CONSTRAINT `FK_ORDERINTERNALACCESSORYDETAIL_REFERENCE_ORDERINTERNALACC` FOREIGN KEY (`ORDERSHEETNO`) REFERENCES `orderinternalaccessory` (`ORDERSHEETNO`),
  ADD CONSTRAINT `FK_ORDERINTERNALACCESSORYDETAIL_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `packingguide`
--
ALTER TABLE `packingguide`
  ADD CONSTRAINT `FK_PACKINGGUIDE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `pi`
--
ALTER TABLE `pi`
  ADD CONSTRAINT `FK_PIBRAND_REFERENCE_BRAND` FOREIGN KEY (`BRANDCODE`) REFERENCES `brand` (`BRANDCODE`),
  ADD CONSTRAINT `FK_PICONSIGNEE_REFERENCE_CUSTOMER` FOREIGN KEY (`CONSIGNEEE`) REFERENCES `customer` (`CUSTOMERCODE`),
  ADD CONSTRAINT `FK_PICUSTOMER1_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMER1CODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  ADD CONSTRAINT `FK_PI_REFERENCE_DESTINATION` FOREIGN KEY (`DESTINATIONCODE`) REFERENCES `destination` (`DESTINATIONCODE`),
  ADD CONSTRAINT `FK_PI_REFERENCE_FACTORY` FOREIGN KEY (`FACTORYCODE`) REFERENCES `factory` (`FACTORYCODE`),
  ADD CONSTRAINT `FK_PI_REFERENCE_PACKINGGUIDE` FOREIGN KEY (`PACKINGGUIDE`) REFERENCES `packingguide` (`Code`),
  ADD CONSTRAINT `FK_PI_REFERENCE_PIGRID` FOREIGN KEY (`PIGRIDCODE`) REFERENCES `pigrid` (`PIGRIDCODE`),
  ADD CONSTRAINT `FK_PI_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `piassignexternalaccessory`
--
ALTER TABLE `piassignexternalaccessory`
  ADD CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  ADD CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  ADD CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_EXTERNALACCSTOCK` FOREIGN KEY (`EXTERNALACCESSORYSTOCKCODE`) REFERENCES `externalaccessorystock` (`EXTERNALACCESSORYSTOCKCODE`),
  ADD CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_GARMENTSTYLE` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  ADD CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_ORDEREXTERNALACCESSORY` FOREIGN KEY (`ORDERSHEETNO`) REFERENCES `orderexternalaccessory` (`ORDERSHEETNO`),
  ADD CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`),
  ADD CONSTRAINT `FK_PIASSIGNEXTERNALACCESSORY_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`),
  ADD CONSTRAINT `FK_PIASSIGNEXTERNALACC_REFERENCE_GARMENTSTYLEACCDETAIL` FOREIGN KEY (`GARMENTSTYLEACCESSORYDETAILCODE`) REFERENCES `garmentstyleaccessorydetail` (`GARMENTSTYLEACCESSORYDETAILCODE`),
  ADD CONSTRAINT `FK_PIASSIGNEXTERNALACC_REFERENCE_PIGRIDDETAIL` FOREIGN KEY (`PIGRIDDETAIL`) REFERENCES `pigriddetail` (`PIGRIDDETAIL`);

--
-- Constraints for table `piassignfabric`
--
ALTER TABLE `piassignfabric`
  ADD CONSTRAINT `FK_PIASSIGNFABRIC_REFERENCE_FABRICINFORMATION` FOREIGN KEY (`FABRICNO`) REFERENCES `fabricinformation` (`FABRICNO`),
  ADD CONSTRAINT `FK_PIASSIGN_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`),
  ADD CONSTRAINT `FK_PIASSIGN_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `piassignfabricdetail`
--
ALTER TABLE `piassignfabricdetail`
  ADD CONSTRAINT `FK_PIASSIGNFABRICDETAIL_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  ADD CONSTRAINT `FK_PIASSIGNFABRICDETAIL_REFERENCE_GARMENTSTYLE` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  ADD CONSTRAINT `FK_PIASSIGNFABRICDETAIL_REFERENCE_PIASSIGNFABRIC` FOREIGN KEY (`LOTNUMBER`,`FABRICNO`) REFERENCES `piassignfabric` (`LOTNUMBER`, `FABRICNO`);

--
-- Constraints for table `piassigninternalaccessories`
--
ALTER TABLE `piassigninternalaccessories`
  ADD CONSTRAINT `FK_PIORDERINTERNALACC_REFERENCE_ACCESSORY` FOREIGN KEY (`ACCESSORYCODE`) REFERENCES `accessory` (`ACCESSORYCODE`),
  ADD CONSTRAINT `FK_PIORDERINTERNALACC_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`),
  ADD CONSTRAINT `FK_PIORDERINTERNALACC_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `piassigninternalaccessoriesdetail`
--
ALTER TABLE `piassigninternalaccessoriesdetail`
  ADD CONSTRAINT `FK_PIORDERINTERNALACCDEATIL_REFERENCE_PIORDERINTERNALACC` FOREIGN KEY (`PIINTERNALACCESSORIES`) REFERENCES `piassigninternalaccessories` (`PIINTERNALACCESSORIES`),
  ADD CONSTRAINT `FK_PIORDERINTERNALACCDETAIL_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  ADD CONSTRAINT `FK_PIORDERINTERNALACCDETAIL_REFERENCE_GARMENTSTYLE` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  ADD CONSTRAINT `FK_PIORDERINTERNALACCDETAIL_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`);

--
-- Constraints for table `piassigninternalaccessoriesoforders`
--
ALTER TABLE `piassigninternalaccessoriesoforders`
  ADD CONSTRAINT `FK_PIASSIGNINTACCORDERS_REF_PIASSIGNINTACC` FOREIGN KEY (`PIINTERNALACCESSORIES`) REFERENCES `piassigninternalaccessories` (`PIINTERNALACCESSORIES`),
  ADD CONSTRAINT `FK_PIASSIGNINTACCORDER_REF_ORDERINTACC` FOREIGN KEY (`ORDERSHEETNO`) REFERENCES `orderinternalaccessory` (`ORDERSHEETNO`);

--
-- Constraints for table `pigrid`
--
ALTER TABLE `pigrid`
  ADD CONSTRAINT `FK_PIGRID_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `pigriddetail`
--
ALTER TABLE `pigriddetail`
  ADD CONSTRAINT `FK_PIGRIDDE_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  ADD CONSTRAINT `FK_PIGRIDDE_REFERENCE_GARMENTS` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  ADD CONSTRAINT `FK_PIGRIDDE_REFERENCE_PIGRID` FOREIGN KEY (`PIGRIDCODE`) REFERENCES `pigrid` (`PIGRIDCODE`),
  ADD CONSTRAINT `FK_PIGRIDDE_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`);

--
-- Constraints for table `rfpi`
--
ALTER TABLE `rfpi`
  ADD CONSTRAINT `FK_RFPI_REFERENCE_PI` FOREIGN KEY (`LOTNUMBER`) REFERENCES `pi` (`LOTNUMBER`),
  ADD CONSTRAINT `FK_RFPI_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `rfpidetail`
--
ALTER TABLE `rfpidetail`
  ADD CONSTRAINT `FK_RFPIDETA_REFERENCE_COLOR` FOREIGN KEY (`COLORCODE`) REFERENCES `color` (`COLORCODE`),
  ADD CONSTRAINT `FK_RFPIDETA_REFERENCE_GARMENTS` FOREIGN KEY (`GARMENTSTYLECODE`) REFERENCES `garmentstyle` (`GARMENTSTYLECODE`),
  ADD CONSTRAINT `FK_RFPIDETA_REFERENCE_RFPI` FOREIGN KEY (`RFPIGRID`) REFERENCES `rfpi` (`RFPIGRID`),
  ADD CONSTRAINT `FK_RFPIDETA_REFERENCE_SIZE` FOREIGN KEY (`SIZECODE`) REFERENCES `size` (`SIZECODE`);

--
-- Constraints for table `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `FK_ROLE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `rolefunction`
--
ALTER TABLE `rolefunction`
  ADD CONSTRAINT `FK_ROLEFUNC_REFERENCE_FUNCTION` FOREIGN KEY (`FUNCTIONID`) REFERENCES `function` (`FUNCTIONID`),
  ADD CONSTRAINT `FK_ROLEFUNC_REFERENCE_ROLE` FOREIGN KEY (`ROLEID`) REFERENCES `role` (`ROLEID`);

--
-- Constraints for table `shippingline`
--
ALTER TABLE `shippingline`
  ADD CONSTRAINT `FK_SHIPPING_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `shippinglinecontact`
--
ALTER TABLE `shippinglinecontact`
  ADD CONSTRAINT `FK_SHIPPINGLINECONTACT_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`),
  ADD CONSTRAINT `FK_SHIPPING_REFERENCE_SHIPPING` FOREIGN KEY (`SHIPPINGLINECODE`) REFERENCES `shippingline` (`SHIPPINGLINECODE`);

--
-- Constraints for table `size`
--
ALTER TABLE `size`
  ADD CONSTRAINT `FK_SIZE_REFERENCE_CUSTOMER` FOREIGN KEY (`CUSTOMERCODE`) REFERENCES `customer` (`CUSTOMERCODE`),
  ADD CONSTRAINT `FK_SIZE_REFERENCE_GARMENTK` FOREIGN KEY (`GARMENTKINDCODE`) REFERENCES `garmentkind` (`GARMENTKINDCODE`),
  ADD CONSTRAINT `FK_SIZE_REFERENCE_TYPE` FOREIGN KEY (`TYPECODE`) REFERENCES `type` (`TYPECODE`),
  ADD CONSTRAINT `FK_SIZE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `type`
--
ALTER TABLE `type`
  ADD CONSTRAINT `FK_TYPE_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_USER_REFERENCE_AGENT` FOREIGN KEY (`AGENTCODE`) REFERENCES `agent` (`AGENTCODE`),
  ADD CONSTRAINT `FK_USER_REFERENCE_ROLE` FOREIGN KEY (`ROLEID`) REFERENCES `role` (`ROLEID`),
  ADD CONSTRAINT `FK_USER_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `width`
--
ALTER TABLE `width`
  ADD CONSTRAINT `FK_WIDTH_REFERENCE_UNIT` FOREIGN KEY (`UNITCODE`) REFERENCES `unit` (`UNITCODE`),
  ADD CONSTRAINT `FK_WIDTH_REFERENCE_USER` FOREIGN KEY (`CREATOR`) REFERENCES `user` (`USERNAME`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
