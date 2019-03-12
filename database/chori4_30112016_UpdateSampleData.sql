-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2016 at 08:18 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chori9`
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
('Snap', NULL, 'admin', 'pcs', 'Color 1', 'pcs', 'Snap YKK', 'Internal', '3x2', 'Packing', '', '', NULL, NULL, '2016-07-07 00:00:00', 0, 'Snap', 'Active');

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
('Snap', 'admin', 'Snap, snap button, rivet', '2016-07-20 00:00:00');

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
(1, 'Snap', 'YKK', '2016-10-31 00:00:00', NULL, 0.035, 'USD', 'admin', '2016-10-21 17:41:22', '');

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
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accessorysupplier`
--

INSERT INTO `accessorysupplier` (`ACCSUPPLIERCODE`, `CREATOR`, `SHORTNAME`, `LONGNAME`, `ADDRESS`, `TEL`, `FAX`, `TAXNO`, `EMAIL`, `STATUS`, `CREATEDATE`, `REMARK`) VALUES
('YKK', 'admin', 'YKK', 'YKK Vietnam Co. Ltd', '1/. 7th floor, TMS Bldg, 172 Hai Ba Trung st, Dakao Ward, Dist 1, HCMC 2/. 4th Floor, AB Tower, 76 Le Lai St., Ben Thanh Ward, Dist. 1, HCMC, Viet Nam ', '0084-8-38303124', '0084-8-38303139', '', '', 'Active', '2016-10-07 10:26:18', '');

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
(1, 'YKK', 'admin', 'Ms Kim Anh', 'KimAnh@ykk.com.vn', '2016-07-20 00:00:00', ''),
(2, 'YKK', 'admin', 'Mr Rachel (Kim Loan)', 'KimLoan@ykk.com.vn', '2016-07-20 00:00:00', '0933986634');

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
(1, 'Chori Agent VietNam', 'Chori Agent in VietNam', 'admin', 'Thai', '083242232', '1231253543', '143124', 'Active', '2016-07-20 00:00:00', ''),
(2, 'Chori Agent Thailand', 'Chori Agent in Thailand', 'admin', 'VietNam', '083242232', '1231253543', '143124', 'Active', '2016-07-20 00:00:00', '');

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
(1, 'AL HARAMAIN', 'admin', 'AL HARAMAIN', '2016-10-01 08:27:51');

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
('CB', 'admin', 'Sea Blue', '2016-07-20 00:00:00'),
('CBLK', 'admin', 'Black', '2016-07-20 00:00:00'),
('Color 1', 'admin', 'Color 1', '2016-11-14 11:29:43'),
('CYLW', 'admin', 'Yellow', '2016-07-20 00:00:00'),
('WHT', 'admin', 'White', '2016-07-20 00:00:00');

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
(1, 'VND', 'USD', 0.0000449, '2016-11-20', '2016-11-20 00:00:00'),
(2, 'USD', 'VND', 222222, '2016-11-20', '2016-11-20 00:00:00');

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
('AL HARAMAIN', 'admin', 'AL HARAMAIN', 'AL HARAMAIN', 'ABDUL RAHEEM HUSSAIN TURDI EST, AL FAISALIYAH AL SHARAFIYAH STORE AREA, TAIF, SAUDI ARABIA', '96627504543', '99627504593', '', 'Active', '2016-10-27 13:47:44', ''),
('Al Shiaka', 'admin', 'Al Shiaka', 'Al Shiaka', 'DUNIA ALSWAF TRADING LLC P.O.BOX 12238 JEDDAH , 21473 K.S.A', '6391334', '6391335', '', 'Active', '2016-10-07 15:54:36', ''),
('Ezary', 'admin', 'Ezary Abdulgani', 'Ezary Abdulgani', 'ALAMOUDI TARDING CO. P.O. BOX 19297 JEDDAH 21435 SAUDI ARABIA', '012-6446050', '012-6430465', '', 'Active', '2016-09-22 15:22:02', 'Ezary Lab coat'),
('Mr. Mustafa', 'admin', 'Mr. Mustafa', 'Mr. Mustafa', '	OFFICE 417,FOURTH FLOOR,HAMIDAH, COMPLEX NAIF ROAD,DUBAI,UAE', '00971-4-2366799', '00971-4-2731199', '', 'Active', '2016-10-07 15:54:36', '');

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
(1, 'AL HARAMAIN', 'admin', 'Mr. Ibrahim', 'raqem1979@gmail.com', '2016-07-19 03:00:00', '966530362036'),
(2, 'Ezary', 'admin', 'Mr Rashad', 'AMOUDIHA@HOTMAIL.COM', '2016-07-19 03:00:00', '0553038224'),
(3, 'Mr. Mustafa', 'admin', 'Mr. Mustafa', 'chorihcm17@gmail.com', '2016-09-22 01:17:37', '00971-4-2366799');

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
('Jebel Ali', 'admin', 'United Arab Emirates', '2016-10-07 16:07:10'),
('Jeddah', 'admin', 'Saudi Arabia', '2016-07-20 00:00:00');

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
('DAELIM', 'admin', 'DAELIM', 'DAELIM TEXTILE CO LTD.', 'RM NO 304, SHAMS B/D, 9-1, 3-GA, HOEHYUN -DONG, JUNG-GU, SEOUL, KOREA', '82-2-7762593', '82-2-7785203', '', 'Active', '2016-10-27 13:52:51', ''),
('TAW', 'admin', 'TAEKWANG', 'TAEKWANG INDUSTRIAL CO., LTD.', 'TAEKWANG INDUSTRIAL CO., LTD.162-1, JANGCHUNG-DONG 20GA JUNG-GU SEOUL 100-392, KOREA', '2-3406-03300', '2-2273 9159', '', 'Active', '2016-09-22 15:39:10', ''),
('TTF', 'admin', 'TTF', 'Taiwan Taffeta Fabric Co. Ltd', '8/F 70-1, Hsi Ning North Road Taipei 103 Taiwan', '00886-2-25568282', '00886-2-25567948', '', 'Active', '2016-07-20 00:00:00', '');

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
(1, 'DAELIM', 'admin', 'MR. K.O KIM', 'korobetex@gmail.com', '2016-07-20 00:00:00', ''),
(2, 'DAELIM', 'admin', 'MR. J.Y KIM', 'jinokim81@gmail.com', '2016-07-20 00:00:00', ''),
(3, 'TTF', 'admin', 'Mr Phil', 'phil@ttfco.com', '2016-07-20 00:00:00', ''),
(4, 'TAW', 'admin', 'Mr Park/ MR. SHIM', 'acetex@taekwang.co.kr', '2016-09-22 15:30:59', '');

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
('DHG', 'admin', 'Dung Hanh', 'Dung Hanh Garment Co. Ltd', '1/8D Tien Lan Hamlet, Ba Diem Area, Hoc Mon Dist, Ho Chi Minh city, Vietnam', '0084-8-35901632', '0084-8-35901633', '', 'Active', '2016-09-22 15:26:17', ''),
('DV', 'admin', 'DAI VIET', 'DAI VIET GARMENT JOINT STOCK COMPANY', '62 Tan Thanh St, Tan Thanh ward, Tan Phu dist, Ho Chi Minh city', '3 8496016', '38429860', '', 'Active', '2016-07-20 00:00:00', ''),
('MC', 'admin', 'MINH CHAU', 'MINH CHAU', 'MINH CHAU GARMENT CO.,LTD 41/3 TRUNG CHANH 2 HAMLET, TRUNG CHANH COMMUNE, HOC MON DIST, HCMC, VIETNAM', '62838777', '62838878', '', 'Active', '2016-07-20 00:00:00', ''),
('TV', 'admin', 'TAN VIET', 'TAN VIET FACTORY', 'Viet Nam', '213214', '1231253543', '', 'Active', '2016-07-20 00:00:00', '');

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
(1, 'MC', 'admin', 'Ms Thuy', 'msthuy@mcm.vn', '2016-08-17 00:00:00', '0916999068'),
(2, 'MC', 'admin', 'Ms Hai', 'hai@mcm.vn', '2016-08-17 00:00:00', '0916666106'),
(3, 'DHG', 'admin', 'Ms Hanh', 'v.k.hanh@dunghanhgarment.com', '2016-08-17 00:00:00', '0913813265'),
(4, 'DHG', 'admin', 'Ms Khanh Phuong', 'k.phuong@dunghanhgarment.com', '2016-08-17 00:00:00', '0918037488'),
(5, 'DV', 'admin', 'Dai Viet', 'daiviet@col.com', '2016-08-21 00:00:00', '');

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
('Labcoat', 'admin', 'Labcoat', '2016-07-20 00:00:00'),
('Shirt', 'admin', 'Shirt', '2016-07-20 00:00:00'),
('THOBE', 'admin', 'A4 2_2 China collar, hem sleeves', '2016-07-20 00:00:00');

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
('A4-0-0', 'THOBE', 'admin', 'DV', 'AL HARAMAIN', 'China collar hem sleeve', '2016-07-20 00:00:00', '', '', '', '', '', NULL, NULL, 18.000, '2'),
('A4-2-2', 'THOBE', 'admin', 'DV', 'AL HARAMAIN', 'China collar hem sleeve', '2016-07-20 00:00:00', '', '', '', '', '', NULL, NULL, 0.000, '2'),
('Thobe', 'Shirt', 'admin', 'DV', 'Al Shiaka', 'A4 2-2', '2016-10-01 15:52:28', '', '', '', '', '', 'China collar -Hem sleeves', 'Roll packing', 0.000, '2'),
('Thobe Mustafa', 'THOBE', 'admin', 'DV', 'Mr. Mustafa', '', '2016-11-30 14:12:05', NULL, NULL, NULL, NULL, NULL, '', '', NULL, 'USD');

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
('Guide001', 'Guide001 Description', '2016-10-22 15:17:34', 'admin');

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

-- --------------------------------------------------------

--
-- Table structure for table `pigrid`
--

CREATE TABLE `pigrid` (
  `PIGRIDCODE` int(11) NOT NULL,
  `CREATOR` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
('AD', 'admin', 'Admin', 'Administrator', '2016-07-20 00:00:00');

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
('AD', 'Width Management');

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
('CMA', 'admin', 'CMA', 'CMA CGM Vietnam JSC', '8th Floor, CONTINENTAL Tower, No 81-85 Ham Nghi Street Nguyen Thai Binh Ward, District 1 Ho Chi Minh City Vietnam', '+84-8-3 914 8400', '+84-8-3 915 1716', '', 'Active', '2016-07-07 00:00:00', ''),
('NHS', 'admin', 'NHS', 'NEW HORIZON SERVICE CO., LTD', 'NEW HORIZON SERVICE CO., LTD 386-388 Hoang Dieu., District 4., Hochiminh City, Vietnam', '84-8-39433196', '84-8-39433194', '', 'Active', '2016-07-08 00:00:00', ''),
('NM', 'admin', 'NM', 'NM SHIPPING CO., LTD', 'NM SHIPPING CO., LTD 3/F, Melody Tower, 422 Ung Van Khiem St, Binh Thanh Dist, Hochiminh City, Vietnam', '84 8 38997228', '84 8 38997494', '', 'Active', '2016-07-07 00:00:00', '');

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
(1, 'CMA', 'admin', 'Mr Pham Ngoc Tinh', 'sgn.pntinh@cma-cgm.com', '2016-07-08 00:00:00', '0084908441655'),
(2, 'NHS', 'admin', 'Ms. Thanh', 'ob@newhorizonlogs.com', '2016-07-08 00:00:00', '+84 0938073936'),
(3, 'NM', 'admin', 'Ms.Ngoc', 'cus@nmshipping.com.vn', '2016-07-08 00:00:00', '+84090.4653778'),
(4, 'NM', 'admin', 'Mr. Quy', 'quy.nguyen@nmshipping.com.vn', '2016-07-08 00:00:00', '+84 0903.146376'),
(5, 'NM', 'admin', 'Ms.Hong', 'docs@nmshipping.com.vn', '2016-07-08 00:00:00', '+84 0917.692.211');

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
(41, '20', 'AL HARAMAIN', 'THOBE', 'Boy', 'admin', '2016-11-30 13:44:05'),
(42, '22', 'AL HARAMAIN', 'THOBE', 'Boy', 'admin', '2016-11-30 13:44:09'),
(43, '24', 'AL HARAMAIN', 'THOBE', 'Boy', 'admin', '2016-11-30 13:44:13'),
(44, '26', 'AL HARAMAIN', 'THOBE', 'Boy', 'admin', '2016-11-30 13:44:17'),
(45, '28', 'AL HARAMAIN', 'THOBE', 'Boy', 'admin', '2016-11-30 13:44:23'),
(46, '30', 'AL HARAMAIN', 'THOBE', 'Boy', 'admin', '2016-11-30 13:44:27'),
(47, '32', 'AL HARAMAIN', 'THOBE', 'Boy', 'admin', '2016-11-30 13:44:35'),
(48, '34', 'AL HARAMAIN', 'THOBE', 'Boy', 'admin', '2016-11-30 13:44:39'),
(49, '36', 'AL HARAMAIN', 'THOBE', 'Boy', 'admin', '2016-11-30 13:44:44'),
(50, '38', 'AL HARAMAIN', 'THOBE', 'Boy', 'admin', '2016-11-30 13:44:48'),
(51, '20', 'Al Shiaka', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:04'),
(52, '22', 'Al Shiaka', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:04'),
(53, '24', 'Al Shiaka', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:04'),
(54, '26', 'Al Shiaka', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:04'),
(55, '28', 'Al Shiaka', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:04'),
(56, '30', 'Al Shiaka', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:04'),
(57, '32', 'Al Shiaka', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:04'),
(58, '34', 'Al Shiaka', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:04'),
(59, '36', 'Al Shiaka', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:04'),
(60, '38', 'Al Shiaka', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:04'),
(61, '20/S', 'Mr. Mustafa', 'THOBE', 'Boy', 'admin', '2016-11-30 13:45:33');

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
('admin', 'AD', 1, 'admin', '$2a$10$R0sA2XcMYxzarn/jdJ1Sw.NAzzWuLT5LmBZKfTgg7mruhH4ig.2GK', 'Administrator', 'Administrator', 'bleachclone69@gmail.com', 'huancuibap', '01642329994', 'Active', 1);

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
('4.4', 'inches', 'admin', '4.4', '2016-11-30 14:11:05');

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
  MODIFY `EXTERNALACCESSORYSTOCKCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
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
  MODIFY `FACTORYCONTACTCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
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
  MODIFY `GARMENTCONSUMPTIONCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `garmentconsumptiondetail`
--
ALTER TABLE `garmentconsumptiondetail`
  MODIFY `GARMENTCONSUMPTIONDETAILCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
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
  MODIFY `PIASSIGNEXTERNALACCESSORYCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
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
  MODIFY `PIGRIDCODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1137;
--
-- AUTO_INCREMENT for table `pigriddetail`
--
ALTER TABLE `pigriddetail`
  MODIFY `PIGRIDDETAIL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=221;
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
  MODIFY `SIZECODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;
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
