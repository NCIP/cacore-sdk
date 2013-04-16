/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
L*/

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema cacoresdk
--

-- 
-- CREATE DATABASE IF NOT EXISTS cacoresdk
-- USE cacoresdk;
-- 

--
-- Definition of table `ADDRESS`
--

DROP TABLE IF EXISTS `ADDRESS`;
CREATE TABLE `ADDRESS` (
  `ID` int(8) NOT NULL,
  `ZIP` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ADDRESS`
--

/*!40000 ALTER TABLE `ADDRESS` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ADDRESS` (`ID`,`ZIP`) VALUES 
 (1,'Zip1'),
 (2,'Zip2'),
 (3,'Zip3'),
 (4,'Zip4'),
 (5,'Zip5');
COMMIT;
/*!40000 ALTER TABLE `ADDRESS` ENABLE KEYS */;


--
-- Definition of table `ALBUM`
--

DROP TABLE IF EXISTS `ALBUM`;
CREATE TABLE `ALBUM` (
  `ID` int(8) NOT NULL,
  `TITLE` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ALBUM`
--

/*!40000 ALTER TABLE `ALBUM` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ALBUM` (`ID`,`TITLE`) VALUES 
 ('1','Venetian Oboe Concertos'),
 ('2','The Cello');
COMMIT;
/*!40000 ALTER TABLE `ALBUM` ENABLE KEYS */;


--
-- Definition of table `ALBUM_SONG`
--

DROP TABLE IF EXISTS `ALBUM_SONG`;
CREATE TABLE `ALBUM_SONG` (
  `ALBUM_ID` int(8) NOT NULL,
  `SONG_ID` int(8) NOT NULL,
  PRIMARY KEY  (`ALBUM_ID`,`SONG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ALBUM_SONG`
--

/*!40000 ALTER TABLE `ALBUM_SONG` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ALBUM_SONG` (`ALBUM_ID`,`SONG_ID`) VALUES 
 (1,1),
 (1,2),
 (1,3),
 (1,4),
 (1,5),
 (1,6),
 (2,7),
 (2,8),
 (2,9),
 (2,10),
 (2,11),
 (3,14),
 (4,15),
 (6,17),
 (7,18);
COMMIT;
/*!40000 ALTER TABLE `ALBUM_SONG` ENABLE KEYS */;


--
-- Definition of table `ALL_DATA_TYPE`
--

DROP TABLE IF EXISTS `ALL_DATA_TYPE`;
CREATE TABLE `ALL_DATA_TYPE` (
  `ID` int(8) NOT NULL,
  `INT_VALUE` int(8) default NULL,
  `STRING_VALUE` varchar(50) default NULL,
  `DOUBLE_VALUE` decimal(8,2) default NULL,
  `FLOAT_VALUE` decimal(8,2) default NULL,
  `DATE_VALUE` datetime default NULL,
  `BOOLEAN_VALUE` varchar(1) default NULL,
  `CLOB_VALUE` longtext,
  `BLOB_VALUE` blob,
  `CHARACTER_VALUE` char(1) default NULL,
  `LONG_VALUE` decimal(38,0) default NULL,
  `DOUBLE_PRIMITIVE_VALUE` decimal(8,2) default NULL,
  `INT_PRIMITIVE_VALUE` int(8) default NULL,
  `DATE_PRIMITIVE_VALUE` datetime default NULL,
  `STRING_PRIMITIVE_VALUE` varchar(50) default NULL,
  `FLOAT_PRIMITIVE_VALUE` decimal(8,2) default NULL,
  `BOOLEAN_PRIMITIVE_VALUE` varchar(1) default NULL,
  `CHARACTER_PRIMITIVE_VALUE` char(1) default NULL,
  `LONG_PRIMITIVE_VALUE` decimal(38,0) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ALL_DATA_TYPE`
--

/*!40000 ALTER TABLE `ALL_DATA_TYPE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ALL_DATA_TYPE` (`ID`,`INT_VALUE`,`STRING_VALUE`,`DOUBLE_VALUE`,`FLOAT_VALUE`,`DATE_VALUE`,`BOOLEAN_VALUE`,`CLOB_VALUE`,`CHARACTER_VALUE`,`LONG_VALUE`,`DOUBLE_PRIMITIVE_VALUE`,`INT_PRIMITIVE_VALUE`,`DATE_PRIMITIVE_VALUE`,`STRING_PRIMITIVE_VALUE`,`FLOAT_PRIMITIVE_VALUE`,`BOOLEAN_PRIMITIVE_VALUE`,`CHARACTER_PRIMITIVE_VALUE`,`LONG_PRIMITIVE_VALUE`) VALUES 
 (1,-1,',./-+/*&&()||==\'\"%\"!\\','-1.10','1.10','2011-11-11 00:00:00','1','0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012','a','1000001','10001.00',11,'2007-10-01 00:00:00','abc','10.01','1','a','1000001'),
 (2,0,'\'Steve\'s Test\'','0.00','222.22','2012-12-12 00:00:00','0','0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012','b','1000002','10002.00',12,'2007-10-02 00:00:00','def','10.02','0','b','1000002'),
 (3,1,'~!@#$%^&*()_+-={}|:\"<>?[]\\;\',./-+/*&&()||==\'\"%\"!\\\'','1.10','333.33','2003-03-03 00:00:00','1','0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012','c','1000003','10003.00',13,'2007-10-03 00:00:00','ghi','10.03','1','c','1000003'),
 (4,10000,'01234567890123456789012345678901234567890123456789','10000.00','444.44','2004-04-04 00:00:00','0','0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012','d','1000004','10004.00',14,'2007-10-04 00:00:00','jkl','10.04','0','d','1000004'),
 (5,5,'String_Value5','555.55','555.55','2005-05-05 00:00:00','1','0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012','e','1000005','10005.00',15,'2007-10-05 00:00:00','mno','10.05','1','e','1000005');
COMMIT;
/*!40000 ALTER TABLE `ALL_DATA_TYPE` ENABLE KEYS */;


--
-- Definition of table `ALL_DATA_TYPE_STRING_COLL`
--

DROP TABLE IF EXISTS `ALL_DATA_TYPE_STRING_COLL`;
CREATE TABLE `ALL_DATA_TYPE_STRING_COLL` (
  `ALL_DATA_TYPE_ID` int(8) NOT NULL,
  `STRING_VALUE` varchar(50) default NULL,
  KEY `FK_ALL_DATA_TYPE_ALL_DATA_TYPE` (`ALL_DATA_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ALL_DATA_TYPE_STRING_COLL`
--

/*!40000 ALTER TABLE `ALL_DATA_TYPE_STRING_COLL` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ALL_DATA_TYPE_STRING_COLL` (`ALL_DATA_TYPE_ID`,`STRING_VALUE`) VALUES 
 (1,'String_Collection 1'),
 (1,'String_Collection 2'),
 (1,'String_Collection 3');
COMMIT;
/*!40000 ALTER TABLE `ALL_DATA_TYPE_STRING_COLL` ENABLE KEYS */;


--
-- Definition of table `ALL_VALIDATION_TYPE`
--

DROP TABLE IF EXISTS `ALL_VALIDATION_TYPE`;
CREATE TABLE `ALL_VALIDATION_TYPE` (
  `ID` int(8) NOT NULL,
  `EMAIL` varchar(50) default NULL,
  `FUTURE` datetime default NULL,
  `LENGTH` varchar(50) default NULL,
  `MAX_NUMERIC` decimal(22,0) default NULL,
  `PAST` datetime default NULL,
  `MAX_STRING` varchar(50) default NULL,
  `MIN_NUMERIC` decimal(22,0) default NULL,
  `MIN_STRING` varchar(50) default NULL,
  `NOT_EMPTY` varchar(50) default NULL,
  `NOT_NULL` varchar(50) default NULL,
  `RANGE_STRING` varchar(50) default NULL,
  `RANGE_NUMERIC` decimal(22,0) default NULL,
  `PATTERN` varchar(50) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `sys_c0068335` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ALL_VALIDATION_TYPE`
--

/*!40000 ALTER TABLE `ALL_VALIDATION_TYPE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ALL_VALIDATION_TYPE` (`ID`,`EMAIL`,`FUTURE`,`LENGTH`,`MAX_NUMERIC`,`PAST`,`MAX_STRING`,`MIN_NUMERIC`,`MIN_STRING`,`NOT_EMPTY`,`NOT_NULL`,`RANGE_STRING`,`RANGE_NUMERIC`,`PATTERN`) VALUES 
 (1,'name@mail.nih.gov','2008-05-15 00:00:00','123','999',NULL,'999','1','1','abc','abc','3','3','cat'),
 (10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'asfdasdf'),
 (11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'asfdasdf',NULL,'asfdasdf'),
 (12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Invalid Value',NULL,'DEPT');
COMMIT;
/*!40000 ALTER TABLE `ALL_VALIDATION_TYPE` ENABLE KEYS */;


--
-- Definition of table `ASSISTANT`
--

DROP TABLE IF EXISTS `ASSISTANT`;
CREATE TABLE `ASSISTANT` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `PROFESSOR_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_ASSISTANT_PROFESSOR` (`PROFESSOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ASSISTANT`
--

/*!40000 ALTER TABLE `ASSISTANT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ASSISTANT` (`ID`,`NAME`,`PROFESSOR_ID`) VALUES 
 (1,'Assistant_Name1',1),
 (2,'Assistant_Name2',2),
 (3,'Assistant_Name3',3),
 (4,'Assistant_Name4',6),
 (5,'Assistant_Name5',7),
 (6,'Assistant_Name6',7),
 (7,'Assistant_Name7',11),
 (8,'Assistant_Name8',12),
 (9,'Assistant_Name9',12);
COMMIT;
/*!40000 ALTER TABLE `ASSISTANT` ENABLE KEYS */;


--
-- Definition of table `ASSISTANT_PROFESSOR`
--

DROP TABLE IF EXISTS `ASSISTANT_PROFESSOR`;
CREATE TABLE `ASSISTANT_PROFESSOR` (
  `PROFESSOR_ID` int(4) NOT NULL,
  `JOINING_YEAR` int(4) default NULL,
  PRIMARY KEY  (`PROFESSOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ASSISTANT_PROFESSOR`
--

/*!40000 ALTER TABLE `ASSISTANT_PROFESSOR` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ASSISTANT_PROFESSOR` (`PROFESSOR_ID`,`JOINING_YEAR`) VALUES 
 (11,11),
 (12,12),
 (13,13),
 (14,14),
 (15,15);
COMMIT;
/*!40000 ALTER TABLE `ASSISTANT_PROFESSOR` ENABLE KEYS */;


--
-- Definition of table `ASSOCIATE_PROFESSOR`
--

DROP TABLE IF EXISTS `ASSOCIATE_PROFESSOR`;
CREATE TABLE `ASSOCIATE_PROFESSOR` (
  `PROFESSOR_ID` int(8) NOT NULL,
  `YEARS_SERVED` int(4) default NULL,
  PRIMARY KEY  (`PROFESSOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ASSOCIATE_PROFESSOR`
--

/*!40000 ALTER TABLE `ASSOCIATE_PROFESSOR` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ASSOCIATE_PROFESSOR` (`PROFESSOR_ID`,`YEARS_SERVED`) VALUES 
 (6,6),
 (7,7),
 (8,8),
 (9,9),
 (10,10);
COMMIT;
/*!40000 ALTER TABLE `ASSOCIATE_PROFESSOR` ENABLE KEYS */;


--
-- Definition of table `AUTHOR`
--

DROP TABLE IF EXISTS `AUTHOR`;
CREATE TABLE `AUTHOR` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `AUTHOR`
--

/*!40000 ALTER TABLE `AUTHOR` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `AUTHOR` (`ID`,`NAME`) VALUES 
 (1,'Author1'),
 (2,'Author2'),
 (3,'Author3'),
 (4,'Author4'),
 (5,'Author5');
COMMIT;
/*!40000 ALTER TABLE `AUTHOR` ENABLE KEYS */;


--
-- Definition of table `AUTHOR_BOOK`
--

DROP TABLE IF EXISTS `AUTHOR_BOOK`;
CREATE TABLE `AUTHOR_BOOK` (
  `AUTHOR_ID` int(8) NOT NULL,
  `BOOK_ID` int(8) NOT NULL,
  PRIMARY KEY  (`AUTHOR_ID`,`BOOK_ID`),
  KEY `FK_AUTHOR_BOOK_BOOK` (`BOOK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `AUTHOR_BOOK`
--

/*!40000 ALTER TABLE `AUTHOR_BOOK` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `AUTHOR_BOOK` (`AUTHOR_ID`,`BOOK_ID`) VALUES 
 (1,1),
 (2,2),
 (3,3);
COMMIT;
/*!40000 ALTER TABLE `AUTHOR_BOOK` ENABLE KEYS */;


--
-- Definition of table `BAG`
--

DROP TABLE IF EXISTS `BAG`;
CREATE TABLE `BAG` (
  `ID` int(8) NOT NULL,
  `STYLE` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BAG`
--

/*!40000 ALTER TABLE `BAG` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `BAG` (`ID`,`STYLE`) VALUES 
 (1,'Baguettes'),
 (2,'Barrel'),
 (3,'Beach'),
 (4,'Bowler'),
 (5,'Bucket'),
 (6,'Duffel'),
 (7,'Evening'),
 (8,'Flap'),
 (9,'Hobos'),
 (10,'Pochettes'),
 (11,'Satchel');
COMMIT;
/*!40000 ALTER TABLE `BAG` ENABLE KEYS */;


--
-- Definition of table `BAG_HANDLE`
--

DROP TABLE IF EXISTS `BAG_HANDLE`;
CREATE TABLE `BAG_HANDLE` (
  `BAG_ID` int(8) NOT NULL,
  `HANDLE_ID` int(8) NOT NULL,
  PRIMARY KEY  (`BAG_ID`,`HANDLE_ID`),
  UNIQUE KEY `UQ_BAG_HANDLE_BAG_ID` (`BAG_ID`),
  UNIQUE KEY `UQ_BAG_HANDLE_HANDLE_ID` (`HANDLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BAG_HANDLE`
--

/*!40000 ALTER TABLE `BAG_HANDLE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `BAG_HANDLE` (`BAG_ID`,`HANDLE_ID`) VALUES 
 (1,1),
 (2,2),
 (3,3),
 (4,4),
 (5,5),
 (6,6),
 (7,7),
 (8,8),
 (9,9),
 (10,10);
COMMIT;
/*!40000 ALTER TABLE `BAG_HANDLE` ENABLE KEYS */;


--
-- Definition of table `BANK`
--

DROP TABLE IF EXISTS `BANK`;
CREATE TABLE `BANK` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BANK`
--

/*!40000 ALTER TABLE `BANK` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `BANK` (`ID`,`NAME`) VALUES 
 (1,'Bank1'),
 (2,'Bank2'),
 (3,'Bank3'),
 (4,'Bank4');
COMMIT;
/*!40000 ALTER TABLE `BANK` ENABLE KEYS */;


--
-- Definition of table `BOOK`
--

DROP TABLE IF EXISTS `BOOK`;
CREATE TABLE `BOOK` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BOOK`
--

/*!40000 ALTER TABLE `BOOK` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `BOOK` (`ID`,`NAME`) VALUES 
 (1,'Book1'),
 (2,'Book2'),
 (3,'Book3'),
 (4,'Book4'),
 (5,'Book5');
COMMIT;
/*!40000 ALTER TABLE `BOOK` ENABLE KEYS */;


--
-- Definition of table `BRIDE`
--

DROP TABLE IF EXISTS `BRIDE`;
CREATE TABLE `BRIDE` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BRIDE`
--

/*!40000 ALTER TABLE `BRIDE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `BRIDE` (`ID`,`NAME`) VALUES 
 (1,'Bride 1'),
 (2,'Bride 2'),
 (3,'Bride 3'),
 (4,'Bride 4');
COMMIT;
/*!40000 ALTER TABLE `BRIDE` ENABLE KEYS */;


--
-- Definition of table `BRIDE_FATHER_IN_LAW`
--

DROP TABLE IF EXISTS `BRIDE_FATHER_IN_LAW`;
CREATE TABLE `BRIDE_FATHER_IN_LAW` (
  `BRIDE_ID` int(8) NOT NULL,
  `IN_LAW_ID` int(8) NOT NULL,
  PRIMARY KEY  (`BRIDE_ID`,`IN_LAW_ID`),
  UNIQUE KEY `UQ_BRIDE_FATHER_IN_L_BRIDE_ID` (`BRIDE_ID`),
  UNIQUE KEY `UQ_BRIDE_FATHER_IN__IN_LAW_ID` (`IN_LAW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BRIDE_FATHER_IN_LAW`
--

/*!40000 ALTER TABLE `BRIDE_FATHER_IN_LAW` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `BRIDE_FATHER_IN_LAW` (`BRIDE_ID`,`IN_LAW_ID`) VALUES 
 (1,1),
 (2,3);
COMMIT;
/*!40000 ALTER TABLE `BRIDE_FATHER_IN_LAW` ENABLE KEYS */;


--
-- Definition of table `BRIDE_MOTHER_IN_LAW`
--

DROP TABLE IF EXISTS `BRIDE_MOTHER_IN_LAW`;
CREATE TABLE `BRIDE_MOTHER_IN_LAW` (
  `BRIDE_D` int(8) NOT NULL,
  `IN_LAW_ID` int(8) NOT NULL,
  PRIMARY KEY  (`BRIDE_D`,`IN_LAW_ID`),
  UNIQUE KEY `UQ_BRIDE_MOTHER_IN_LA_BRIDE_D` (`BRIDE_D`),
  UNIQUE KEY `UQ_BRIDE_MOTHER_IN__IN_LAW_ID` (`IN_LAW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BRIDE_MOTHER_IN_LAW`
--

/*!40000 ALTER TABLE `BRIDE_MOTHER_IN_LAW` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `BRIDE_MOTHER_IN_LAW` (`BRIDE_D`,`IN_LAW_ID`) VALUES 
 (1,2),
 (3,5);
COMMIT;
/*!40000 ALTER TABLE `BRIDE_MOTHER_IN_LAW` ENABLE KEYS */;


--
-- Definition of table `BUTTON`
--

DROP TABLE IF EXISTS `BUTTON`;
CREATE TABLE `BUTTON` (
  `ID` int(8) NOT NULL,
  `HOLES` int(8) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BUTTON`
--

/*!40000 ALTER TABLE `BUTTON` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `BUTTON` (`ID`,`HOLES`) VALUES 
 (1,2),
 (2,4);
COMMIT;
/*!40000 ALTER TABLE `BUTTON` ENABLE KEYS */;


--
-- Definition of table `CALCULATOR`
--

DROP TABLE IF EXISTS `CALCULATOR`;
CREATE TABLE `CALCULATOR` (
  `ID` int(8) NOT NULL,
  `DISCRIMINATOR` varchar(150) default NULL,
  `BRAND` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CALCULATOR`
--

/*!40000 ALTER TABLE `CALCULATOR` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CALCULATOR` (`ID`,`DISCRIMINATOR`,`BRAND`) VALUES 
 (1,'financial','NCR'),
 (2,'scientific','Texas Instruments'),
 (3,'graphics','HP');
COMMIT;
/*!40000 ALTER TABLE `CALCULATOR` ENABLE KEYS */;


--
-- Definition of table `CARD`
--

DROP TABLE IF EXISTS `CARD`;
CREATE TABLE `CARD` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `SUIT_ID` int(8) default NULL,
  `IMAGE` longtext,
  PRIMARY KEY  (`ID`),
  KEY `FK_CARD_SUIT` (`SUIT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CARD`
--

/*!40000 ALTER TABLE `CARD` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CARD` (`ID`,`NAME`,`SUIT_ID`,`IMAGE`) VALUES 
 (1,'Ace',1,'My Ace'),
 (2,'Two',1,NULL),
 (3,'Three',1,NULL),
 (4,'Four',1,NULL),
 (5,'Five',1,NULL),
 (6,'Six',1,NULL),
 (7,'Seven',1,NULL),
 (8,'Eight',1,NULL),
 (9,'Nine',1,NULL),
 (10,'Ten',1,NULL),
 (11,'Jack',1,NULL),
 (12,'Queen',1,NULL),
 (13,'King',1,NULL),
 (14,'Ace',2,NULL),
 (15,'Two',2,NULL),
 (16,'Three',2,NULL),
 (17,'Four',2,NULL),
 (18,'Five',2,NULL),
 (19,'Six',2,NULL),
 (20,'Seven',2,NULL),
 (21,'Eight',2,NULL),
 (22,'Nine',2,NULL),
 (23,'Ten',2,NULL),
 (24,'Jack',2,NULL),
 (25,'Queen',2,NULL),
 (26,'King',2,NULL),
 (27,'Ace',3,NULL),
 (28,'Two',3,NULL),
 (29,'Three',3,NULL),
 (30,'Four',3,NULL),
 (31,'Five',3,NULL),
 (32,'Six',3,NULL),
 (33,'Seven',3,NULL),
 (34,'Eight',3,NULL),
 (35,'Nine',3,NULL),
 (36,'Ten',3,NULL),
 (37,'Jack',3,NULL),
 (38,'Queen',3,NULL),
 (39,'King',3,NULL),
 (40,'Ace',4,NULL),
 (41,'Two',4,NULL),
 (42,'Three',4,NULL),
 (43,'Four',4,NULL),
 (44,'Five',4,NULL),
 (45,'Six',4,NULL),
 (46,'Seven',4,NULL),
 (47,'Eight',4,NULL),
 (48,'Nine',4,NULL),
 (49,'Ten',4,NULL),
 (50,'Jack',4,NULL),
 (51,'Queen',4,NULL),
 (52,'King',4,NULL),
 (53,'Joker',NULL,NULL);
COMMIT;
/*!40000 ALTER TABLE `CARD` ENABLE KEYS */;


--
-- Definition of table `CASH`
--

DROP TABLE IF EXISTS `CASH`;
CREATE TABLE `CASH` (
  `PAYMENT_ID` int(8) NOT NULL,
  PRIMARY KEY  (`PAYMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CASH`
--

/*!40000 ALTER TABLE `CASH` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CASH` (`PAYMENT_ID`) VALUES 
 (1),
 (2);
COMMIT;
/*!40000 ALTER TABLE `CASH` ENABLE KEYS */;


--
-- Definition of table `CHAIN`
--

DROP TABLE IF EXISTS `CHAIN`;
CREATE TABLE `CHAIN` (
  `ID` int(8) NOT NULL,
  `METAL` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CHAIN`
--

/*!40000 ALTER TABLE `CHAIN` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CHAIN` (`ID`,`METAL`) VALUES 
 (1,'Gold'),
 (2,'Silver'),
 (3,'Bronze');
COMMIT;
/*!40000 ALTER TABLE `CHAIN` ENABLE KEYS */;


--
-- Definition of table `CHAIN_PENDANT`
--

DROP TABLE IF EXISTS `CHAIN_PENDANT`;
CREATE TABLE `CHAIN_PENDANT` (
  `CHAIN_ID` int(8) NOT NULL,
  `PENDANT_ID` int(8) NOT NULL,
  PRIMARY KEY  (`CHAIN_ID`,`PENDANT_ID`),
  UNIQUE KEY `UQ_CHAIN_PENDANT_CHAIN_ID` (`CHAIN_ID`),
  UNIQUE KEY `UQ_CHAIN_PENDANT_PENDANT_ID` (`PENDANT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CHAIN_PENDANT`
--

/*!40000 ALTER TABLE `CHAIN_PENDANT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CHAIN_PENDANT` (`CHAIN_ID`,`PENDANT_ID`) VALUES 
 (1,1),
 (2,2),
 (3,3);
COMMIT;
/*!40000 ALTER TABLE `CHAIN_PENDANT` ENABLE KEYS */;


--
-- Definition of table `CHARACTER_KEY`
--

DROP TABLE IF EXISTS `CHARACTER_KEY`;
CREATE TABLE `CHARACTER_KEY` (
  `ID` char(1) NOT NULL default '',
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CHARACTER_KEY`
--

/*!40000 ALTER TABLE `CHARACTER_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CHARACTER_KEY` (`ID`,`NAME`) VALUES 
 ('9','CharacterKey_Name 9'),
 (';','CharacterKey _Name;'),
 ('a','CharacterKey_Name a'),
 ('B','CharacterKey_Name B');
COMMIT;
/*!40000 ALTER TABLE `CHARACTER_KEY` ENABLE KEYS */;


--
-- Definition of table `CHARACTER_PRIMITIVE_KEY`
--

DROP TABLE IF EXISTS `CHARACTER_PRIMITIVE_KEY`;
CREATE TABLE `CHARACTER_PRIMITIVE_KEY` (
  `ID` char(1) NOT NULL default '',
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CHARACTER_PRIMITIVE_KEY`
--

/*!40000 ALTER TABLE `CHARACTER_PRIMITIVE_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CHARACTER_PRIMITIVE_KEY` (`ID`,`NAME`) VALUES 
 ('6','Character_Primitive_Key_Name 6'),
 ('d','Character_Primitive_Key_Name d'),
 ('L','Character_Primitive_Key_Name L'),
 ('[','Character_Primitive_Key_Name [');
COMMIT;
/*!40000 ALTER TABLE `CHARACTER_PRIMITIVE_KEY` ENABLE KEYS */;


--
-- Definition of table `CHEF`
--

DROP TABLE IF EXISTS `CHEF`;
CREATE TABLE `CHEF` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `RESTAURANT_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_CHEF_RESTAURANT` (`RESTAURANT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CHEF`
--

/*!40000 ALTER TABLE `CHEF` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CHEF` (`ID`,`NAME`,`RESTAURANT_ID`) VALUES 
 (1,'Chef1',1),
 (2,'Chef2',2),
 (3,'Chef3',2),
 (4,'Chef4',NULL);
COMMIT;
/*!40000 ALTER TABLE `CHEF` ENABLE KEYS */;


--
-- Definition of table `CHILD`
--

DROP TABLE IF EXISTS `CHILD`;
CREATE TABLE `CHILD` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `FATHER_ID` int(8) default NULL,
  `MOTHER_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `UQ_CHILD_FATHER_ID` (`FATHER_ID`),
  UNIQUE KEY `UQ_CHILD_MOTHER_ID` (`MOTHER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CHILD`
--

/*!40000 ALTER TABLE `CHILD` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CHILD` (`ID`,`NAME`,`FATHER_ID`,`MOTHER_ID`) VALUES 
 (1,'Child_Name1',1,2),
 (2,'Child_Name2',3,4),
 (3,'Child_Name3',5,NULL),
 (4,'Child_Name4',NULL,6),
 (5,'Child_Name5',NULL,NULL);
COMMIT;
/*!40000 ALTER TABLE `CHILD` ENABLE KEYS */;


--
-- Definition of table `COMPUTER`
--

DROP TABLE IF EXISTS `COMPUTER`;
CREATE TABLE `COMPUTER` (
  `ID` int(8) NOT NULL,
  `TYPE` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `COMPUTER`
--

/*!40000 ALTER TABLE `COMPUTER` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `COMPUTER` (`ID`,`TYPE`) VALUES 
 (1,'Computer_Type1'),
 (2,'Computer_Type2'),
 (3,'Computer_Type3'),
 (4,'Computer_Type4'),
 (5,'Computer_Type5');
COMMIT;
/*!40000 ALTER TABLE `COMPUTER` ENABLE KEYS */;


--
-- Definition of table `CREDIT`
--

DROP TABLE IF EXISTS `CREDIT`;
CREATE TABLE `CREDIT` (
  `PAYMENT_ID` int(8) NOT NULL,
  `CARD_NUMBER` varchar(50) default NULL,
  `BANK_ID` int(8) default NULL,
  PRIMARY KEY  (`PAYMENT_ID`),
  KEY `FK_CREDIT_BANK` (`BANK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CREDIT`
--

/*!40000 ALTER TABLE `CREDIT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CREDIT` (`PAYMENT_ID`,`CARD_NUMBER`,`BANK_ID`) VALUES 
 (3,'3',3),
 (4,'4',4);
COMMIT;
/*!40000 ALTER TABLE `CREDIT` ENABLE KEYS */;


--
-- Definition of table `CRT_MONITOR`
--

DROP TABLE IF EXISTS `CRT_MONITOR`;
CREATE TABLE `CRT_MONITOR` (
  `MONITOR_ID` int(8) NOT NULL,
  `REFRESH_RATE` int(8) default NULL,
  PRIMARY KEY  (`MONITOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CRT_MONITOR`
--

/*!40000 ALTER TABLE `CRT_MONITOR` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CRT_MONITOR` (`MONITOR_ID`,`REFRESH_RATE`) VALUES 
 (1,45);
COMMIT;
/*!40000 ALTER TABLE `CRT_MONITOR` ENABLE KEYS */;

--
-- Definition of table `CURRENCY`
--

DROP TABLE IF EXISTS `CURRENCY`;
CREATE TABLE `CURRENCY` (
  `ID` int(8) NOT NULL,
  `DISCRIMINATOR` varchar(150) default NULL,
  `COUNTRY` varchar(50) default NULL,
  `VALUE` int(8) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CURRENCY`
--

/*!40000 ALTER TABLE `CURRENCY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CURRENCY` (`ID`,`DISCRIMINATOR`,`COUNTRY`,`VALUE`) VALUES 
 (1,'Note','USA',1),
 (2,'Note','Germany',2),
 (3,'Note','Spain',3);
COMMIT;
/*!40000 ALTER TABLE `CURRENCY` ENABLE KEYS */;


--
-- Definition of table `DECK`
--

DROP TABLE IF EXISTS `DECK`;
CREATE TABLE `DECK` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DECK`
--

/*!40000 ALTER TABLE `DECK` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `DECK` (`ID`,`NAME`) VALUES 
 (1,'My Deck 1');
COMMIT;
/*!40000 ALTER TABLE `DECK` ENABLE KEYS */;


--
-- Definition of table `DESIGNER`
--

DROP TABLE IF EXISTS `DESIGNER`;
CREATE TABLE `DESIGNER` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DESIGNER`
--

/*!40000 ALTER TABLE `DESIGNER` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `DESIGNER` (`ID`,`NAME`) VALUES 
 (1,'Gucci'),
 (2,'Prada'),
 (3,'Sergio Rossi');
COMMIT;
/*!40000 ALTER TABLE `DESIGNER` ENABLE KEYS */;


--
-- Definition of table `DESSERT`
--

DROP TABLE IF EXISTS `DESSERT`;
CREATE TABLE `DESSERT` (
  `ID` int(8) NOT NULL,
  `TOPPING` varchar(50) default NULL,
  `FILLING` varchar(50) default NULL,
  `DISCRIMINATOR` varchar(150) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DESSERT`
--

/*!40000 ALTER TABLE `DESSERT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `DESSERT` (`ID`,`TOPPING`,`FILLING`,`DISCRIMINATOR`) VALUES 
 (1,'Peanuts',NULL,'IceCream'),
 (2,'Sprinkles',NULL,'IceCream'),
 (3,NULL,'Apples','Pie'),
 (4,NULL,'Cherries','Pie');
COMMIT;
/*!40000 ALTER TABLE `DESSERT` ENABLE KEYS */;


--
-- Definition of table `DESSERT_UTENSIL`
--

DROP TABLE IF EXISTS `DESSERT_UTENSIL`;
CREATE TABLE `DESSERT_UTENSIL` (
  `DESSERT_ID` int(8) NOT NULL,
  `UTENSIL_ID` int(8) NOT NULL,
  PRIMARY KEY  (`DESSERT_ID`,`UTENSIL_ID`),
  KEY `FK_DESSERT_UTENSIL_UTENSIL` (`UTENSIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DESSERT_UTENSIL`
--

/*!40000 ALTER TABLE `DESSERT_UTENSIL` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `DESSERT_UTENSIL` (`DESSERT_ID`,`UTENSIL_ID`) VALUES 
 (1,1),
 (3,1),
 (2,2),
 (3,2),
 (4,2),
 (1,3),
 (4,3);
COMMIT;
/*!40000 ALTER TABLE `DESSERT_UTENSIL` ENABLE KEYS */;


--
-- Definition of table `DISPLAY`
--

DROP TABLE IF EXISTS `DISPLAY`;
CREATE TABLE `DISPLAY` (
  `ID` int(8) NOT NULL,
  `WIDTH` int(8) default NULL,
  `HEIGHT` int(8) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DISPLAY`
--

/*!40000 ALTER TABLE `DISPLAY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `DISPLAY` (`ID`,`WIDTH`,`HEIGHT`) VALUES 
 (1,1,1),
 (2,2,2),
 (3,3,3),
 (4,4,4),
 (5,5,5);
COMMIT;
/*!40000 ALTER TABLE `DISPLAY` ENABLE KEYS */;


--
-- Definition of table `DOG`
--

DROP TABLE IF EXISTS `DOG`;
CREATE TABLE `DOG` (
  `ID` int(8) NOT NULL,
  `BREED` varchar(50) default NULL,
  `GENDER` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DOG`
--

/*!40000 ALTER TABLE `DOG` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `DOG` (`ID`,`BREED`,`GENDER`) VALUES 
 (1,'Poodle','Male'),
 (2,'Chihuahua','Female'),
 (3,'St. Bernard','Male');
COMMIT;
/*!40000 ALTER TABLE `DOG` ENABLE KEYS */;


--
-- Definition of table `DOUBLE_KEY`
--

DROP TABLE IF EXISTS `DOUBLE_KEY`;
CREATE TABLE `DOUBLE_KEY` (
  `ID` decimal(8,2) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DOUBLE_KEY`
--

/*!40000 ALTER TABLE `DOUBLE_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `DOUBLE_KEY` (`ID`,`NAME`) VALUES 
 ('1.10','Double_Key_Name1.1'),
 ('2.20','Double_Key_Name2.2'),
 ('3.30','Double_Key_Name3.3'),
 ('4.40','Double_Key_Name4.4'),
 ('5.50','Double_Key_Name5.5');
COMMIT;
/*!40000 ALTER TABLE `DOUBLE_KEY` ENABLE KEYS */;


--
-- Definition of table `DOUBLE_PRIMITIVE_KEY`
--

DROP TABLE IF EXISTS `DOUBLE_PRIMITIVE_KEY`;
CREATE TABLE `DOUBLE_PRIMITIVE_KEY` (
  `ID` decimal(8,2) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DOUBLE_PRIMITIVE_KEY`
--

/*!40000 ALTER TABLE `DOUBLE_PRIMITIVE_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `DOUBLE_PRIMITIVE_KEY` (`ID`,`NAME`) VALUES 
 ('1.10','Double_Primitive_Key 1.1'),
 ('2.20','Double_Primitive_Key 2.2');
COMMIT;
/*!40000 ALTER TABLE `DOUBLE_PRIMITIVE_KEY` ENABLE KEYS */;


--
-- Definition of table `ELEMENT`
--

DROP TABLE IF EXISTS `ELEMENT`;
CREATE TABLE `ELEMENT` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `PARENT_ELEMENT_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `UQ_ELEMENT_PARENT_ELEMENT_ID` (`PARENT_ELEMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ELEMENT`
--

/*!40000 ALTER TABLE `ELEMENT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ELEMENT` (`ID`,`NAME`,`PARENT_ELEMENT_ID`) VALUES 
 (1,'Name1',NULL),
 (2,'Name2',1),
 (3,'Element',NULL),
 (4,'Element',NULL);
COMMIT;
/*!40000 ALTER TABLE `ELEMENT` ENABLE KEYS */;


--
-- Definition of table `EMPLOYEE`
--

DROP TABLE IF EXISTS `EMPLOYEE`;
CREATE TABLE `EMPLOYEE` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `EMPLOYEE`
--

/*!40000 ALTER TABLE `EMPLOYEE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `EMPLOYEE` (`ID`,`NAME`) VALUES 
 (1,'Employee_Name1'),
 (2,'Employee_Name2'),
 (3,'Employee_Name3'),
 (4,'Employee_Name4'),
 (5,'Employee_Name5'),
 (6,'Employee_Name6'),
 (7,'Employee_Name7'),
 (8,'Employee_Name8'),
 (9,'Employee_Name9'),
 (10,'Employee_Name10');
COMMIT;
/*!40000 ALTER TABLE `EMPLOYEE` ENABLE KEYS */;


--
-- Definition of table `EMPLOYEE_PROJECT`
--

DROP TABLE IF EXISTS `EMPLOYEE_PROJECT`;
CREATE TABLE `EMPLOYEE_PROJECT` (
  `EMPLOYEE_ID` int(8) NOT NULL,
  `PROJECT_ID` int(8) NOT NULL,
  PRIMARY KEY  (`EMPLOYEE_ID`,`PROJECT_ID`),
  KEY `FK_EMPLOYEE_PROJECT_PROJECT` (`PROJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `EMPLOYEE_PROJECT`
--

/*!40000 ALTER TABLE `EMPLOYEE_PROJECT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `EMPLOYEE_PROJECT` (`EMPLOYEE_ID`,`PROJECT_ID`) VALUES 
 (1,1),
 (2,2),
 (3,2),
 (4,4),
 (4,5),
 (6,5);
COMMIT;
/*!40000 ALTER TABLE `EMPLOYEE_PROJECT` ENABLE KEYS */;


--
-- Definition of table `FISH`
--

DROP TABLE IF EXISTS `FISH`;
CREATE TABLE `FISH` (
  `ID` int(8) NOT NULL,
  `GENERA` varchar(50) default NULL,
  `PRIMARY_COLOR` varchar(50) default NULL,
  `FIN_SIZE` int(8) default NULL,
  `DISCRIMINATOR` varchar(150) default NULL,
  `TANK_ID` int(8) default NULL,
  `TANK_DISCRIMINATOR` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FISH`
--

/*!40000 ALTER TABLE `FISH` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `FISH` (`ID`,`GENERA`,`PRIMARY_COLOR`,`FIN_SIZE`,`DISCRIMINATOR`,`TANK_ID`,`TANK_DISCRIMINATOR`) VALUES 
 (1,'Hemichromis','blue',NULL,'DiscusFish',1,'FreshwaterFishTank'),
 (2,'Hemichromis','red',NULL,'DiscusFish',2,'FreshwaterFishTank'),
 (3,'Pterophyllum',NULL,3,'AngelFish',3,'SaltwaterFishTank'),
 (4,'Pterophyllum',NULL,5,'AngelFish',4,'SaltwaterFishTank');
COMMIT;
/*!40000 ALTER TABLE `FISH` ENABLE KEYS */;


--
-- Definition of table `FLIGHT`
--

DROP TABLE IF EXISTS `FLIGHT`;
CREATE TABLE `FLIGHT` (
  `ID` int(8) NOT NULL,
  `DESTINATION` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FLIGHT`
--

/*!40000 ALTER TABLE `FLIGHT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `FLIGHT` (`ID`,`DESTINATION`) VALUES 
 (1,'Baltimore, MD'),
 (2,'San Francisco, CA'),
 (3,'Maui, HI');
COMMIT;
/*!40000 ALTER TABLE `FLIGHT` ENABLE KEYS */;


--
-- Definition of table `FLIGHT_PASSANGER`
--

DROP TABLE IF EXISTS `FLIGHT_PASSANGER`;
CREATE TABLE `FLIGHT_PASSANGER` (
  `FLIGHT_ID` int(8) NOT NULL,
  `PASSANGER_ID` int(8) NOT NULL,
  PRIMARY KEY  (`FLIGHT_ID`,`PASSANGER_ID`),
  KEY `FK_FLIGHT_PASSANGER_PASSANGER` (`PASSANGER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FLIGHT_PASSANGER`
--

/*!40000 ALTER TABLE `FLIGHT_PASSANGER` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `FLIGHT_PASSANGER` (`FLIGHT_ID`,`PASSANGER_ID`) VALUES 
 (1,1),
 (1,2);
COMMIT;
/*!40000 ALTER TABLE `FLIGHT_PASSANGER` ENABLE KEYS */;


--
-- Definition of table `FLOAT_KEY`
--

DROP TABLE IF EXISTS `FLOAT_KEY`;
CREATE TABLE `FLOAT_KEY` (
  `ID` decimal(8,2) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FLOAT_KEY`
--

/*!40000 ALTER TABLE `FLOAT_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `FLOAT_KEY` (`ID`,`NAME`) VALUES 
 ('1.10','Float_Key_Name1.1'),
 ('2.20','Float_Key_Name2.2'),
 ('3.30','Float_Key_Name3.3'),
 ('4.40','Float_Key_Name4.4'),
 ('5.50','Float_Key_Name5.5');
COMMIT;
/*!40000 ALTER TABLE `FLOAT_KEY` ENABLE KEYS */;


--
-- Definition of table `FLOAT_PRIMITIVE_KEY`
--

DROP TABLE IF EXISTS `FLOAT_PRIMITIVE_KEY`;
CREATE TABLE `FLOAT_PRIMITIVE_KEY` (
  `ID` decimal(8,2) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FLOAT_PRIMITIVE_KEY`
--

/*!40000 ALTER TABLE `FLOAT_PRIMITIVE_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `FLOAT_PRIMITIVE_KEY` (`ID`,`NAME`) VALUES 
 ('1.10','Float_Key_Name 1.1'),
 ('2.20','Float_Key_Name 2.2'),
 ('3.30','Float_Key_Name 3.3');
COMMIT;
/*!40000 ALTER TABLE `FLOAT_PRIMITIVE_KEY` ENABLE KEYS */;


--
-- Definition of table `FRESHWATER_FISH_TANK`
--

DROP TABLE IF EXISTS `FRESHWATER_FISH_TANK`;
CREATE TABLE `FRESHWATER_FISH_TANK` (
  `ID` int(8) NOT NULL,
  `SHAPE` varchar(50) default NULL,
  `NUM_GALLONS` int(8) default NULL,
  `FILTER_MODEL` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FRESHWATER_FISH_TANK`
--

/*!40000 ALTER TABLE `FRESHWATER_FISH_TANK` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `FRESHWATER_FISH_TANK` (`ID`,`SHAPE`,`NUM_GALLONS`,`FILTER_MODEL`) VALUES 
 (1,'Rectangular',10,'350B Penguin Bio-Wheel Filter'),
 (2,'Hexagonal',7,'200B Penguin Bio-Wheel Filter');
COMMIT;
/*!40000 ALTER TABLE `FRESHWATER_FISH_TANK` ENABLE KEYS */;


--
-- Definition of table `GOVERMENT`
--

DROP TABLE IF EXISTS `GOVERMENT`;
CREATE TABLE `GOVERMENT` (
  `ID` int(8) NOT NULL,
  `DISCRIMINATOR` varchar(150) default NULL,
  `COUNTRY` varchar(50) default NULL,
  `PRIME_MINISTER` varchar(50) default NULL,
  `PRESIDENT` varchar(50) default NULL,
  `DEMOCRATIC_DISCRIMINATOR` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `GOVERMENT`
--

/*!40000 ALTER TABLE `GOVERMENT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `GOVERMENT` (`ID`,`DISCRIMINATOR`,`COUNTRY`,`PRIME_MINISTER`,`PRESIDENT`,`DEMOCRATIC_DISCRIMINATOR`) VALUES 
 (1,'PresidentialGovt','USA',NULL,'George W. Bush',NULL),
 (2,'ParliamantaryGovt','England','Tony Blair',NULL,NULL),
 (3,'CommunistGovt','Cuba',NULL,NULL,NULL);
COMMIT;
/*!40000 ALTER TABLE `GOVERMENT` ENABLE KEYS */;


--
-- Definition of table `GRADUATE_STUDENT`
--

DROP TABLE IF EXISTS `GRADUATE_STUDENT`;
CREATE TABLE `GRADUATE_STUDENT` (
  `STUDENT_ID` int(8) NOT NULL,
  `PROJECT_NAME` varchar(50) default NULL,
  PRIMARY KEY  (`STUDENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `GRADUATE_STUDENT`
--

/*!40000 ALTER TABLE `GRADUATE_STUDENT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `GRADUATE_STUDENT` (`STUDENT_ID`,`PROJECT_NAME`) VALUES 
 (6,'Project_Name6'),
 (7,'Project_Name7'),
 (8,'Project_Name8'),
 (9,'Project_Name9'),
 (10,'Project_Name10');
COMMIT;
/*!40000 ALTER TABLE `GRADUATE_STUDENT` ENABLE KEYS */;


--
-- Definition of table `GRAPHIC_CALCULATOR`
--

DROP TABLE IF EXISTS `GRAPHIC_CALCULATOR`;
CREATE TABLE `GRAPHIC_CALCULATOR` (
  `CALCULATOR_ID` int(8) NOT NULL,
  PRIMARY KEY  (`CALCULATOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `GRAPHIC_CALCULATOR`
--

/*!40000 ALTER TABLE `GRAPHIC_CALCULATOR` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `GRAPHIC_CALCULATOR` (`CALCULATOR_ID`) VALUES 
 (3);
COMMIT;
/*!40000 ALTER TABLE `GRAPHIC_CALCULATOR` ENABLE KEYS */;


--
-- Definition of table `HAND`
--

DROP TABLE IF EXISTS `HAND`;
CREATE TABLE `HAND` (
  `ID` int(8) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `HAND`
--

/*!40000 ALTER TABLE `HAND` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `HAND` (`ID`) VALUES 
 (1),
 (2),
 (3),
 (4),
 (5);
COMMIT;
/*!40000 ALTER TABLE `HAND` ENABLE KEYS */;


--
-- Definition of table `HAND_CARD`
--

DROP TABLE IF EXISTS `HAND_CARD`;
CREATE TABLE `HAND_CARD` (
  `HAND_ID` int(8) NOT NULL,
  `CARD_ID` int(8) NOT NULL,
  PRIMARY KEY  (`HAND_ID`,`CARD_ID`),
  KEY `FK_HAND_CARD_CARD` (`CARD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `HAND_CARD`
--

/*!40000 ALTER TABLE `HAND_CARD` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `HAND_CARD` (`HAND_ID`,`CARD_ID`) VALUES 
 (1,1),
 (2,2),
 (2,3),
 (2,5),
 (3,6),
 (3,14),
 (3,15),
 (1,25),
 (4,26),
 (4,27),
 (4,30),
 (5,39),
 (5,40),
 (5,41),
 (1,52);
COMMIT;
/*!40000 ALTER TABLE `HAND_CARD` ENABLE KEYS */;


--
-- Definition of table `HANDLE`
--

DROP TABLE IF EXISTS `HANDLE`;
CREATE TABLE `HANDLE` (
  `ID` int(8) NOT NULL,
  `COLOR` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `HANDLE`
--

/*!40000 ALTER TABLE `HANDLE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `HANDLE` (`ID`,`COLOR`) VALUES 
 (1,'Black'),
 (2,'Brown'),
 (3,'Blue'),
 (4,'White'),
 (5,'Red'),
 (6,'Yellow'),
 (7,'Green'),
 (8,'Beige'),
 (9,'Purple'),
 (10,'Orange'),
 (11,'Teal'),
 (12,'Burgundy');
COMMIT;
/*!40000 ALTER TABLE `HANDLE` ENABLE KEYS */;


--
-- Definition of table `HARD_DRIVE`
--

DROP TABLE IF EXISTS `HARD_DRIVE`;
CREATE TABLE `HARD_DRIVE` (
  `ID` int(8) NOT NULL,
  `DRIVE_SIZE` int(8) default NULL,
  `COMPUTER_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_HARD_DRIVE_COMPUTER` (`COMPUTER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `HARD_DRIVE`
--

/*!40000 ALTER TABLE `HARD_DRIVE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `HARD_DRIVE` (`ID`,`DRIVE_SIZE`,`COMPUTER_ID`) VALUES 
 (1,1,1),
 (2,2,2),
 (3,3,2);
COMMIT;
/*!40000 ALTER TABLE `HARD_DRIVE` ENABLE KEYS */;


--
-- Definition of table `HI_VALUE`
--

DROP TABLE IF EXISTS `HI_VALUE`;
CREATE TABLE `HI_VALUE` (
  `NEXT_VALUE` decimal(22,0) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `HI_VALUE`
--

/*!40000 ALTER TABLE `HI_VALUE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `HI_VALUE` (`NEXT_VALUE`) VALUES 
 ('8');
COMMIT;
/*!40000 ALTER TABLE `HI_VALUE` ENABLE KEYS */;


--
-- Definition of table `HL7_DATA_TYPE`
--

DROP TABLE IF EXISTS `HL7_DATA_TYPE`;
CREATE TABLE `HL7_DATA_TYPE` (
  `ID` int(8) NOT NULL,
  `ROOT` varchar(50) default NULL,
  `EXTENSION` varchar(50) default NULL,
  `XML` varchar(512) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `HL7_DATA_TYPE`
--

/*!40000 ALTER TABLE `HL7_DATA_TYPE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `HL7_DATA_TYPE` (`ID`,`ROOT`,`EXTENSION`,`XML`) VALUES 
 (1,'My Root','My Ext',NULL),
 (2,'My Root 2','My Ext 2',NULL);
COMMIT;
/*!40000 ALTER TABLE `HL7_DATA_TYPE` ENABLE KEYS */;


--
-- Definition of table `HUMAN`
--

DROP TABLE IF EXISTS `HUMAN`;
CREATE TABLE `HUMAN` (
  `MAMMAL_ID` int(8) NOT NULL,
  `DIET` varchar(50) default NULL,
  PRIMARY KEY  (`MAMMAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `HUMAN`
--

/*!40000 ALTER TABLE `HUMAN` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `HUMAN` (`MAMMAL_ID`,`DIET`) VALUES 
 (1,'DIET1'),
 (2,'DIET2'),
 (3,'DIET3'),
 (4,'DIET4');
COMMIT;
/*!40000 ALTER TABLE `HUMAN` ENABLE KEYS */;


--
-- Definition of table `IN_LAW`
--

DROP TABLE IF EXISTS `IN_LAW`;
CREATE TABLE `IN_LAW` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `IN_LAW`
--

/*!40000 ALTER TABLE `IN_LAW` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `IN_LAW` (`ID`,`NAME`) VALUES 
 (1,' Father-in-Law Bride 1'),
 (2,'Mother-in-Law Bride 1'),
 (3,'Father-in-Law Bride 2'),
 (5,'Mother-in-Law Bride 3');
COMMIT;
/*!40000 ALTER TABLE `IN_LAW` ENABLE KEYS */;


--
-- Definition of table `INTEGER_KEY`
--

DROP TABLE IF EXISTS `INTEGER_KEY`;
CREATE TABLE `INTEGER_KEY` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `INTEGER_KEY`
--

/*!40000 ALTER TABLE `INTEGER_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `INTEGER_KEY` (`ID`,`NAME`) VALUES 
 (1,'Integer_Key_Name1'),
 (2,'Integer_Key_Name2'),
 (3,'Integer_Key_Name3'),
 (4,'Integer_Key_Name4'),
 (5,'Integer_Key_Name5');
COMMIT;
/*!40000 ALTER TABLE `INTEGER_KEY` ENABLE KEYS */;


--
-- Definition of table `INTEGER_PRIMITIVE_KEY`
--

DROP TABLE IF EXISTS `INTEGER_PRIMITIVE_KEY`;
CREATE TABLE `INTEGER_PRIMITIVE_KEY` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `INTEGER_PRIMITIVE_KEY`
--

/*!40000 ALTER TABLE `INTEGER_PRIMITIVE_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `INTEGER_PRIMITIVE_KEY` (`ID`,`NAME`) VALUES 
 (1,'Integer_Primitive_Key_Name 1'),
 (2,'Integer_Primitive_Key_Name 2');
COMMIT;
/*!40000 ALTER TABLE `INTEGER_PRIMITIVE_KEY` ENABLE KEYS */;

--
-- Definition of table `KEYCHAIN`
--

DROP TABLE IF EXISTS `KEYCHAIN`;
CREATE TABLE `KEYCHAIN` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `KEYCHAIN`
--

/*!40000 ALTER TABLE `KEYCHAIN` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `KEYCHAIN` (`ID`,`NAME`) VALUES 
 (1,'Keychain_Name1'),
 (2,'Keychain_Name2'),
 (3,'Keychain_Name3'),
 (4,'Keychain_Name4'),
 (5,'Keychain_Name5');
COMMIT;
/*!40000 ALTER TABLE `KEYCHAIN` ENABLE KEYS */;

--
-- Definition of table `LATCH_KEY`
--

DROP TABLE IF EXISTS `LATCH_KEY`;
CREATE TABLE `LATCH_KEY` (
  `ID` int(8) NOT NULL,
  `TYPE` varchar(50) default NULL,
  `KEYCHAIN_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_LATCH_KEY_KEYCHAIN` (`KEYCHAIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `LATCH_KEY`
--

/*!40000 ALTER TABLE `LATCH_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `LATCH_KEY` (`ID`,`TYPE`,`KEYCHAIN_ID`) VALUES 
 (1,'Key_Type1',1),
 (2,'Key_Type2',2),
 (3,'Key_Type3',2);
COMMIT;
/*!40000 ALTER TABLE `LATCH_KEY` ENABLE KEYS */;


--
-- Definition of table `LCD_MONITOR`
--

DROP TABLE IF EXISTS `LCD_MONITOR`;
CREATE TABLE `LCD_MONITOR` (
  `MONITOR_ID` int(8) NOT NULL,
  `DPI_SUPPORTED` int(8) default NULL,
  PRIMARY KEY  (`MONITOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `LCD_MONITOR`
--

/*!40000 ALTER TABLE `LCD_MONITOR` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `LCD_MONITOR` (`MONITOR_ID`,`DPI_SUPPORTED`) VALUES 
 (2,2323),
 (3,1212);
COMMIT;
/*!40000 ALTER TABLE `LCD_MONITOR` ENABLE KEYS */;


--
-- Definition of table `LOG_MESSAGE`
--

DROP TABLE IF EXISTS `LOG_MESSAGE`;
CREATE TABLE `LOG_MESSAGE` (
  `LOG_ID` bigint(200) NOT NULL auto_increment,
  `APPLICATION` varchar(25) default NULL,
  `SERVER` varchar(50) default NULL,
  `CATEGORY` varchar(255) default NULL,
  `THREAD` varchar(255) default NULL,
  `USERNAME` varchar(255) default NULL,
  `SESSION_ID` varchar(255) default NULL,
  `MSG` text,
  `THROWABLE` text,
  `NDC` text,
  `CREATED_ON` bigint(20) NOT NULL default '0',
  `OBJECT_ID` varchar(255) default NULL,
  `OBJECT_NAME` varchar(255) default NULL,
  `ORGANIZATION` varchar(255) default NULL,
  `OPERATION` varchar(50) default NULL,
  PRIMARY KEY  (`LOG_ID`),
  KEY `APPLICATION_LOGTAB_INDX` (`APPLICATION`),
  KEY `SERVER_LOGTAB_INDX` (`SERVER`),
  KEY `THREAD_LOGTAB_INDX` (`THREAD`),
  KEY `CREATED_ON_LOGTAB_INDX` (`CREATED_ON`),
  KEY `LOGID_LOGTAB_INDX` (`LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1883 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `LOG_MESSAGE`
--

/*!40000 ALTER TABLE `LOG_MESSAGE` DISABLE KEYS */;
-- SET AUTOCOMMIT=0;
-- INSERT INTO `LOG_MESSAGE` (`LOG_ID`,`APPLICATION`,`SERVER`,`CATEGORY`,`THREAD`,`USERNAME`,`SESSION_ID`,`MSG`,`THROWABLE`,`NDC`,`CREATED_ON`,`OBJECT_ID`,`OBJECT_NAME`,`ORGANIZATION`,`OPERATION`) VALUES 
-- (1357,'example','Dan-PC','INFO','http-8080-Processor22','user1','','Successful Login attempt for user user1','','',1214592986727,'','','','');
-- COMMIT;
/*!40000 ALTER TABLE `LOG_MESSAGE` ENABLE KEYS */;


--
-- Definition of table `LONG_KEY`
--

DROP TABLE IF EXISTS `LONG_KEY`;
CREATE TABLE `LONG_KEY` (
  `ID` decimal(38,0) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `LONG_KEY`
--

/*!40000 ALTER TABLE `LONG_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `LONG_KEY` (`ID`,`NAME`) VALUES 
 ('1234567890987650000','Long_Key_NAME 1234567890987654321');
COMMIT;
/*!40000 ALTER TABLE `LONG_KEY` ENABLE KEYS */;


--
-- Definition of table `LONG_PRIMITIVE_KEY`
--

DROP TABLE IF EXISTS `LONG_PRIMITIVE_KEY`;
CREATE TABLE `LONG_PRIMITIVE_KEY` (
  `ID` decimal(38,0) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `LONG_PRIMITIVE_KEY`
--

/*!40000 ALTER TABLE `LONG_PRIMITIVE_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `LONG_PRIMITIVE_KEY` (`ID`,`NAME`) VALUES 
 ('987654321234568000','Long_Primitive_Key_NAME 987654321234567890');
COMMIT;
/*!40000 ALTER TABLE `LONG_PRIMITIVE_KEY` ENABLE KEYS */;


--
-- Definition of table `LUGGAGE`
--

DROP TABLE IF EXISTS `LUGGAGE`;
CREATE TABLE `LUGGAGE` (
  `ID` int(8) NOT NULL,
  `DISCRIMINATOR` varchar(150) default NULL,
  `CAPACITY` int(8) default NULL,
  `KEY_CODE` int(8) default NULL,
  `EXPANDABLE` varchar(1) default NULL,
  `WHEEL_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_LUGGAGE_WHEEL` (`WHEEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `LUGGAGE`
--

/*!40000 ALTER TABLE `LUGGAGE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `LUGGAGE` (`ID`,`DISCRIMINATOR`,`CAPACITY`,`KEY_CODE`,`EXPANDABLE`,`WHEEL_ID`) VALUES 
 (1,'HardTop',75,627,NULL,1),
 (2,'HardTop',75,985,NULL,3),
 (3,'SoftTop',55,NULL,'1',1),
 (4,'SoftTop',35,NULL,'0',2),
 (5,'HardTopType',100,890,NULL,1);
COMMIT;
/*!40000 ALTER TABLE `LUGGAGE` ENABLE KEYS */;


--
-- Definition of table `MAMMAL`
--

DROP TABLE IF EXISTS `MAMMAL`;
CREATE TABLE `MAMMAL` (
  `ID` int(8) NOT NULL,
  `HAIR_COLOR` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `MAMMAL`
--

/*!40000 ALTER TABLE `MAMMAL` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `MAMMAL` (`ID`,`HAIR_COLOR`) VALUES 
 (1,'Hair_Color1'),
 (2,'Hair_Color2'),
 (3,'Hair_Color3'),
 (4,'Hair_Color4'),
 (5,'Hair_Color5');
COMMIT;
/*!40000 ALTER TABLE `MAMMAL` ENABLE KEYS */;


--
-- Definition of table `MONITOR`
--

DROP TABLE IF EXISTS `MONITOR`;
CREATE TABLE `MONITOR` (
  `DISPLAY_ID` int(8) NOT NULL,
  `BRAND` varchar(50) default NULL,
  PRIMARY KEY  (`DISPLAY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `MONITOR`
--

/*!40000 ALTER TABLE `MONITOR` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `MONITOR` (`DISPLAY_ID`,`BRAND`) VALUES 
 (1,'A'),
 (2,'B'),
 (3,'C'),
 (4,'D');
COMMIT;
/*!40000 ALTER TABLE `MONITOR` ENABLE KEYS */;


--
-- Definition of table `NO_ID_KEY`
--

DROP TABLE IF EXISTS `NO_ID_KEY`;
CREATE TABLE `NO_ID_KEY` (
  `MY_KEY` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`MY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `NO_ID_KEY`
--

/*!40000 ALTER TABLE `NO_ID_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `NO_ID_KEY` (`MY_KEY`,`NAME`) VALUES 
 (1,'NoIdKey 1'),
 (2,'NoIdKey 2');
COMMIT;
/*!40000 ALTER TABLE `NO_ID_KEY` ENABLE KEYS */;


--
-- Definition of table `OBJECT_ATTRIBUTE`
--

DROP TABLE IF EXISTS `OBJECT_ATTRIBUTE`;
CREATE TABLE `OBJECT_ATTRIBUTE` (
  `OBJECT_ATTRIBUTE_ID` bigint(200) NOT NULL auto_increment,
  `CURRENT_VALUE` varchar(255) default NULL,
  `PREVIOUS_VALUE` varchar(255) default NULL,
  `ATTRIBUTE` varchar(255) NOT NULL,
  PRIMARY KEY  (`OBJECT_ATTRIBUTE_ID`),
  KEY `OAID_INDX` (`OBJECT_ATTRIBUTE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `OBJECT_ATTRIBUTE`
--

/*!40000 ALTER TABLE `OBJECT_ATTRIBUTE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
COMMIT;
/*!40000 ALTER TABLE `OBJECT_ATTRIBUTE` ENABLE KEYS */;


--
-- Definition of table `OBJECTATTRIBUTES`
--

DROP TABLE IF EXISTS `OBJECTATTRIBUTES`;
CREATE TABLE `OBJECTATTRIBUTES` (
  `LOG_ID` bigint(200) NOT NULL default '0',
  `OBJECT_ATTRIBUTE_ID` bigint(200) NOT NULL default '0',
  KEY `Index_2` (`LOG_ID`),
  KEY `FK_OBJECTATTRIBUTES_2` (`OBJECT_ATTRIBUTE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `OBJECTATTRIBUTES`
--

/*!40000 ALTER TABLE `OBJECTATTRIBUTES` DISABLE KEYS */;
SET AUTOCOMMIT=0;
COMMIT;
/*!40000 ALTER TABLE `OBJECTATTRIBUTES` ENABLE KEYS */;


--
-- Definition of table `ORDERLINE`
--

DROP TABLE IF EXISTS `ORDERLINE`;
CREATE TABLE `ORDERLINE` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ORDERLINE`
--

/*!40000 ALTER TABLE `ORDERLINE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ORDERLINE` (`ID`,`NAME`) VALUES 
 (1,'Orderline_Name1'),
 (2,'Orderline_Name2'),
 (3,'Orderline_Name3'),
 (4,'Orderline_Name4'),
 (5,'Orderline_Name5');
COMMIT;
/*!40000 ALTER TABLE `ORDERLINE` ENABLE KEYS */;


--
-- Definition of table `ORGANIZATION`
--

DROP TABLE IF EXISTS `ORGANIZATION`;
CREATE TABLE `ORGANIZATION` (
  `ID` int(8) NOT NULL,
  `DISCRIMINATOR` varchar(150) default NULL,
  `NAME` varchar(50) default NULL,
  `AGENCY_BUDGET` int(8) default NULL,
  `CEO` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ORGANIZATION`
--

/*!40000 ALTER TABLE `ORGANIZATION` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ORGANIZATION` (`ID`,`DISCRIMINATOR`,`NAME`,`AGENCY_BUDGET`,`CEO`) VALUES 
 (1,'govt','Public Org Name',1000,NULL),
 (2,'pvt','Private Org Name',NULL,'Private CEO Name');
COMMIT;
/*!40000 ALTER TABLE `ORGANIZATION` ENABLE KEYS */;


--
-- Definition of table `PARENT`
--

DROP TABLE IF EXISTS `PARENT`;
CREATE TABLE `PARENT` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PARENT`
--

/*!40000 ALTER TABLE `PARENT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `PARENT` (`ID`,`NAME`) VALUES 
 (1,'Parent_Name1'),
 (2,'Parent_Name2'),
 (3,'Parent_Name3'),
 (4,'Parent_Name4'),
 (5,'Parent_Name5'),
 (6,'Parent_Name6'),
 (7,'Parent_Name7'),
 (8,'Parent_Name8'),
 (9,'Parent_Name9'),
 (10,'Parent_Name10');
COMMIT;
/*!40000 ALTER TABLE `PARENT` ENABLE KEYS */;


--
-- Definition of table `PASSANGER`
--

DROP TABLE IF EXISTS `PASSANGER`;
CREATE TABLE `PASSANGER` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PASSANGER`
--

/*!40000 ALTER TABLE `PASSANGER` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `PASSANGER` (`ID`,`NAME`) VALUES 
 (1,'John Doe'),
 (2,'Jane Doe');
COMMIT;
/*!40000 ALTER TABLE `PASSANGER` ENABLE KEYS */;


--
-- Definition of table `PAYMENT`
--

DROP TABLE IF EXISTS `PAYMENT`;
CREATE TABLE `PAYMENT` (
  `ID` int(8) NOT NULL,
  `AMOUNT` int(8) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PAYMENT`
--

/*!40000 ALTER TABLE `PAYMENT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `PAYMENT` (`ID`,`AMOUNT`) VALUES 
 (1,1),
 (2,2),
 (3,3),
 (4,4),
 (5,5);
COMMIT;
/*!40000 ALTER TABLE `PAYMENT` ENABLE KEYS */;


--
-- Definition of table `PENDANT`
--

DROP TABLE IF EXISTS `PENDANT`;
CREATE TABLE `PENDANT` (
  `ID` int(8) NOT NULL,
  `SHAPE` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PENDANT`
--

/*!40000 ALTER TABLE `PENDANT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `PENDANT` (`ID`,`SHAPE`) VALUES 
 (1,'Circle Pearl'),
 (2,'Heart Opal'),
 (3,'Oval Carnelian Shell Cameo');
COMMIT;
/*!40000 ALTER TABLE `PENDANT` ENABLE KEYS */;


--
-- Definition of table `PERSON`
--

DROP TABLE IF EXISTS `PERSON`;
CREATE TABLE `PERSON` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `ADDRESS_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `UQ_PERSON_ADDRESS_ID` (`ADDRESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PERSON`
--

/*!40000 ALTER TABLE `PERSON` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `PERSON` (`ID`,`NAME`,`ADDRESS_ID`) VALUES 
 (1,'Person_Name1',1),
 (2,'Person_Name2',2),
 (3,'Person_Name3',3),
 (4,'Person_Name4',NULL),
 (5,'Person_Name5',NULL);
COMMIT;
/*!40000 ALTER TABLE `PERSON` ENABLE KEYS */;

--
-- Definition of table `PRIVATE_TEACHER`
--

DROP TABLE IF EXISTS `PRIVATE_TEACHER`;
CREATE TABLE `PRIVATE_TEACHER` (
  `TEACHER_ID` int(4) NOT NULL,
  `YEARS_EXPERIENCE` int(4) default NULL,
  PRIMARY KEY  (`TEACHER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PRIVATE_TEACHER`
--

/*!40000 ALTER TABLE `PRIVATE_TEACHER` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `PRIVATE_TEACHER` (`TEACHER_ID`,`YEARS_EXPERIENCE`) VALUES 
 (1,5),
 (2,10),
 (3,15);
COMMIT;
/*!40000 ALTER TABLE `PRIVATE_TEACHER` ENABLE KEYS */;


--
-- Definition of table `PRODUCT`
--

DROP TABLE IF EXISTS `PRODUCT`;
CREATE TABLE `PRODUCT` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `ORDERLINE_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `UQ_PRODUCT_ORDERLINE_ID` (`ORDERLINE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PRODUCT`
--

/*!40000 ALTER TABLE `PRODUCT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `PRODUCT` (`ID`,`NAME`,`ORDERLINE_ID`) VALUES 
 (1,'Product_Name1',1),
 (2,'Product_Name2',2),
 (3,'Product_Name3',NULL);
COMMIT;
/*!40000 ALTER TABLE `PRODUCT` ENABLE KEYS */;


--
-- Definition of table `PROFESSOR`
--

DROP TABLE IF EXISTS `PROFESSOR`;
CREATE TABLE `PROFESSOR` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PROFESSOR`
--

/*!40000 ALTER TABLE `PROFESSOR` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `PROFESSOR` (`ID`,`NAME`) VALUES 
 (1,'Professor_Name1'),
 (2,'Professor_Name2'),
 (3,'Professor_Name3'),
 (4,'Professor_Name4'),
 (5,'Professor_Name5'),
 (6,'Professor_Name6'),
 (7,'Professor_Name7'),
 (8,'Professor_Name8'),
 (9,'Professor_Name9'),
 (10,'Professor_Name10'),
 (11,'Professor_Name11'),
 (12,'Professor_Name12'),
 (13,'Professor_Name13'),
 (14,'Professor_Name14'),
 (15,'Professor_Name15');
COMMIT;
/*!40000 ALTER TABLE `PROFESSOR` ENABLE KEYS */;


--
-- Definition of table `PROJECT`
--

DROP TABLE IF EXISTS `PROJECT`;
CREATE TABLE `PROJECT` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PROJECT`
--

/*!40000 ALTER TABLE `PROJECT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `PROJECT` (`ID`,`NAME`) VALUES 
 (1,'Project_Name1'),
 (2,'Project_Name2'),
 (3,'Project_Name3'),
 (4,'Project_Name4'),
 (5,'Project_Name5'),
 (6,'Project_Name6'),
 (7,'Project_Name7'),
 (8,'Project_Name8'),
 (9,'Project_Name9'),
 (10,'Project_Name10');
COMMIT;
/*!40000 ALTER TABLE `PROJECT` ENABLE KEYS */;


--
-- Definition of table `PUPIL`
--

DROP TABLE IF EXISTS `PUPIL`;
CREATE TABLE `PUPIL` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `TEACHER_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_PUPIL_TEACHER` (`TEACHER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PUPIL`
--

/*!40000 ALTER TABLE `PUPIL` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `PUPIL` (`ID`,`NAME`,`TEACHER_ID`) VALUES 
 (1,'Pupil_Name_1',1),
 (2,'Pupil_Name_2',1),
 (3,'Pupil_Name_3',3),
 (4,'Pupil_Name_4',3);
COMMIT;
/*!40000 ALTER TABLE `PUPIL` ENABLE KEYS */;

--
-- Definition of table `RESTAURANT`
--

DROP TABLE IF EXISTS `RESTAURANT`;
CREATE TABLE `RESTAURANT` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RESTAURANT`
--

/*!40000 ALTER TABLE `RESTAURANT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `RESTAURANT` (`ID`,`NAME`) VALUES 
 (1,'Rest1'),
 (2,'Rest2'),
 (3,'Rest3'),
 (4,'Rest4'),
 (5,'Rest5');
COMMIT;
/*!40000 ALTER TABLE `RESTAURANT` ENABLE KEYS */;


--
-- Definition of table `SALTWATER_FISH_TANK`
--

DROP TABLE IF EXISTS `SALTWATER_FISH_TANK`;
CREATE TABLE `SALTWATER_FISH_TANK` (
  `ID` int(8) NOT NULL,
  `SHAPE` varchar(50) default NULL,
  `NUM_GALLONS` int(8) default NULL,
  `PROTEIN_SKIMMER_MODEL` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SALTWATER_FISH_TANK`
--

/*!40000 ALTER TABLE `SALTWATER_FISH_TANK` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `SALTWATER_FISH_TANK` (`ID`,`SHAPE`,`NUM_GALLONS`,`PROTEIN_SKIMMER_MODEL`) VALUES 
 (3,'Rectangular',60,'Berlin X2 Turbo Skimmer'),
 (4,'Hexagonal',20,'Prizm Pro Deluxe Skimmer');
COMMIT;
/*!40000 ALTER TABLE `SALTWATER_FISH_TANK` ENABLE KEYS */;


--
-- Definition of table `SALTWATER_FISH_TANK_SUBSTRATE`
--

DROP TABLE IF EXISTS `SALTWATER_FISH_TANK_SUBSTRATE`;
CREATE TABLE `SALTWATER_FISH_TANK_SUBSTRATE` (
  `SALTWATER_FISH_TANK_ID` int(8) NOT NULL,
  `SUBSTRATE_ID` int(8) NOT NULL,
  PRIMARY KEY  (`SALTWATER_FISH_TANK_ID`,`SUBSTRATE_ID`),
  KEY `FK_SWFT_SUBSTRATE_SUBSTRATE` (`SUBSTRATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SALTWATER_FISH_TANK_SUBSTRATE`
--

/*!40000 ALTER TABLE `SALTWATER_FISH_TANK_SUBSTRATE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `SALTWATER_FISH_TANK_SUBSTRATE` (`SALTWATER_FISH_TANK_ID`,`SUBSTRATE_ID`) VALUES 
 (3,1),
 (3,2),
 (4,3),
 (4,4);
COMMIT;
/*!40000 ALTER TABLE `SALTWATER_FISH_TANK_SUBSTRATE` ENABLE KEYS */;


--
-- Definition of table `SHIRT`
--

DROP TABLE IF EXISTS `SHIRT`;
CREATE TABLE `SHIRT` (
  `ID` int(8) NOT NULL,
  `STYLE` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SHIRT`
--

/*!40000 ALTER TABLE `SHIRT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `SHIRT` (`ID`,`STYLE`) VALUES 
 (1,'Collar'),
 (2,'Western'),
 (3,'T-Shirt');
COMMIT;
/*!40000 ALTER TABLE `SHIRT` ENABLE KEYS */;


--
-- Definition of table `SHIRT_BUTTON`
--

DROP TABLE IF EXISTS `SHIRT_BUTTON`;
CREATE TABLE `SHIRT_BUTTON` (
  `SHIRT_ID` int(8) NOT NULL,
  `BUTTON_ID` int(8) NOT NULL,
  PRIMARY KEY  (`SHIRT_ID`,`BUTTON_ID`),
  UNIQUE KEY `UQ_SHIRT_BUTTON_SHIRT_ID` (`SHIRT_ID`),
  KEY `FK_SHIRT_BUTTON_BUTTON` (`BUTTON_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SHIRT_BUTTON`
--

/*!40000 ALTER TABLE `SHIRT_BUTTON` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `SHIRT_BUTTON` (`SHIRT_ID`,`BUTTON_ID`) VALUES 
 (1,1),
 (2,2);
COMMIT;
/*!40000 ALTER TABLE `SHIRT_BUTTON` ENABLE KEYS */;


--
-- Definition of table `SHOES`
--

DROP TABLE IF EXISTS `SHOES`;
CREATE TABLE `SHOES` (
  `ID` int(8) NOT NULL,
  `DISCRIMINATOR` varchar(150) default NULL,
  `COLOR` varchar(50) default NULL,
  `SPORTS_TYPE` varchar(50) default NULL,
  `DESIGNER_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_SHOES_DESIGNER` (`DESIGNER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SHOES`
--

/*!40000 ALTER TABLE `SHOES` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `SHOES` (`ID`,`DISCRIMINATOR`,`COLOR`,`SPORTS_TYPE`,`DESIGNER_ID`) VALUES 
 (1,'DesignerShoes','White',NULL,2),
 (2,'SportsShoes','Red','BasketBall',NULL),
 (3,'DesignerShoes','Black',NULL,3);
COMMIT;
/*!40000 ALTER TABLE `SHOES` ENABLE KEYS */;


--
-- Definition of table `SONG`
--

DROP TABLE IF EXISTS `SONG`;
CREATE TABLE `SONG` (
  `ID` int(8) NOT NULL,
  `TITLE` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SONG`
--

/*!40000 ALTER TABLE `SONG` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `SONG` (`ID`,`TITLE`) VALUES 
 (1,'Albinoni:  Concerto in B Flat, OP. 7 No. 3'),
 (2,'Albinoni:  Concerto in D Major, Op. 7 No. 6'),
 (3,'Marcello:  Concerto in D Minor'),
 (4,'Vivaldi:  Concerto in F Major F VII 2'),
 (5,'Vivaldi:  Concerto in A Minor F VII 5'),
 (6,'Cimarosa/Benjamin:  Concerto in C Minor'),
 (7,'Rubenstein: Melody in F, Op. 3 No. 1'),
 (8,'Schubert: Ave Maria'),
 (9,'Rimsky-Korsakov:  The Flight of the Bumble Bee'),
 (10,'Schumann:  Traumerei'),
 (11,'Dvorak:  Songs My Mother Taught Me'),
 (12,'Saint-Seans:  The Swan');
COMMIT;
/*!40000 ALTER TABLE `SONG` ENABLE KEYS */;


--
-- Definition of table `STRING_KEY`
--

DROP TABLE IF EXISTS `STRING_KEY`;
CREATE TABLE `STRING_KEY` (
  `ID` varchar(50) NOT NULL default '',
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `STRING_KEY`
--

/*!40000 ALTER TABLE `STRING_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `STRING_KEY` (`ID`,`NAME`) VALUES 
 ('ID1','String_Key_Name1'),
 ('ID2','String_Key_Name2'),
 ('ID3','String_Key_Name3'),
 ('ID4','String_Key_Name4'),
 ('ID5','String_Key_Name5');
COMMIT;
/*!40000 ALTER TABLE `STRING_KEY` ENABLE KEYS */;


--
-- Definition of table `STRING_PRIMITIVE_KEY`
--

DROP TABLE IF EXISTS `STRING_PRIMITIVE_KEY`;
CREATE TABLE `STRING_PRIMITIVE_KEY` (
  `ID` varchar(50) NOT NULL default '',
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `STRING_PRIMITIVE_KEY`
--

/*!40000 ALTER TABLE `STRING_PRIMITIVE_KEY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `STRING_PRIMITIVE_KEY` (`ID`,`NAME`) VALUES 
 ('id1','String_Primitive_Key id1'),
 ('id2','String_Primitive_Key id2');
COMMIT;
/*!40000 ALTER TABLE `STRING_PRIMITIVE_KEY` ENABLE KEYS */;


--
-- Definition of table `STUDENT`
--

DROP TABLE IF EXISTS `STUDENT`;
CREATE TABLE `STUDENT` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `STUDENT`
--

/*!40000 ALTER TABLE `STUDENT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `STUDENT` (`ID`,`NAME`) VALUES 
 (1,'Student_Name1'),
 (2,'Student_Name2'),
 (3,'Student_Name3'),
 (4,'Student_Name4'),
 (5,'Student_Name5'),
 (6,'Student_Name6'),
 (7,'Student_Name7'),
 (8,'Student_Name8'),
 (9,'Student_Name9'),
 (10,'Student_Name10');
COMMIT;
/*!40000 ALTER TABLE `STUDENT` ENABLE KEYS */;


--
-- Definition of table `SUBSTRATE`
--

DROP TABLE IF EXISTS `SUBSTRATE`;
CREATE TABLE `SUBSTRATE` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SUBSTRATE`
--

/*!40000 ALTER TABLE `SUBSTRATE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `SUBSTRATE` (`ID`,`NAME`) VALUES 
 (1,'Live Rock'),
 (2,'Sand'),
 (3,'Crushed Coral'),
 (4,'River Pebbles');
COMMIT;
/*!40000 ALTER TABLE `SUBSTRATE` ENABLE KEYS */;

--
-- Definition of table `SUIT`
--

DROP TABLE IF EXISTS `SUIT`;
CREATE TABLE `SUIT` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `DECK_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_SUIT_DECK` (`DECK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SUIT`
--

/*!40000 ALTER TABLE `SUIT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `SUIT` (`ID`,`NAME`,`DECK_ID`) VALUES 
 (1,'Spade',1),
 (2,'Flower',1),
 (3,'Diamond',1),
 (4,'Heart',1);
COMMIT;
/*!40000 ALTER TABLE `SUIT` ENABLE KEYS */;


--
-- Definition of table `TANK_ACCESSORY`
--

DROP TABLE IF EXISTS `TANK_ACCESSORY`;
CREATE TABLE `TANK_ACCESSORY` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TANK_ACCESSORY`
--

/*!40000 ALTER TABLE `TANK_ACCESSORY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `TANK_ACCESSORY` (`ID`,`NAME`) VALUES 
 (1,'Filter'),
 (2,'Heater'),
 (3,'Lighting'),
 (4,'Protein Skimmer');
COMMIT;
/*!40000 ALTER TABLE `TANK_ACCESSORY` ENABLE KEYS */;


--
-- Definition of table `TANK_TANK_ACCESSORY`
--

DROP TABLE IF EXISTS `TANK_TANK_ACCESSORY`;
CREATE TABLE `TANK_TANK_ACCESSORY` (
  `TANK_ID` int(8) NOT NULL,
  `TANK_ACCESSORY_ID` int(8) NOT NULL,
  `TANK_DISCRIMINATOR` varchar(150) NOT NULL,
  PRIMARY KEY  (`TANK_ID`,`TANK_ACCESSORY_ID`),
  KEY `FK_TANK_TANK_ACCESSORY` (`TANK_ACCESSORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TANK_TANK_ACCESSORY`
--

/*!40000 ALTER TABLE `TANK_TANK_ACCESSORY` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `TANK_TANK_ACCESSORY` (`TANK_ID`,`TANK_ACCESSORY_ID`,`TANK_DISCRIMINATOR`) VALUES 
 (1,1,'FreshwaterFishTank'),
 (1,2,'FreshwaterFishTank'),
 (2,2,'FreshwaterFishTank'),
 (2,3,'FreshwaterFishTank'),
 (3,1,'SaltwaterFishTank'),
 (3,4,'SaltwaterFishTank'),
 (4,1,'SaltwaterFishTank'),
 (4,2,'SaltwaterFishTank'),
 (4,4,'SaltwaterFishTank');
COMMIT;
/*!40000 ALTER TABLE `TANK_TANK_ACCESSORY` ENABLE KEYS */;


--
-- Definition of table `TEACHER`
--

DROP TABLE IF EXISTS `TEACHER`;
CREATE TABLE `TEACHER` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `NULL_FLAVOR` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TEACHER`
--

/*!40000 ALTER TABLE `TEACHER` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `TEACHER` (`ID`,`NAME`) VALUES 
 (1,'Teacher_Name1'),
 (2,'Teacher_Name2'),
 (3,'Teacher_Name3');
COMMIT;
/*!40000 ALTER TABLE `TEACHER` ENABLE KEYS */;


--
-- Definition of table `TENURED_PROFESSOR`
--

DROP TABLE IF EXISTS `TENURED_PROFESSOR`;
CREATE TABLE `TENURED_PROFESSOR` (
  `PROFESSOR_ID` int(8) NOT NULL,
  `TENURED_YEAR` int(4) default NULL,
  PRIMARY KEY  (`PROFESSOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TENURED_PROFESSOR`
--

/*!40000 ALTER TABLE `TENURED_PROFESSOR` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `TENURED_PROFESSOR` (`PROFESSOR_ID`,`TENURED_YEAR`) VALUES 
 (1,1),
 (2,2),
 (3,3),
 (4,4),
 (5,5);
COMMIT;
/*!40000 ALTER TABLE `TENURED_PROFESSOR` ENABLE KEYS */;


--
-- Definition of table `UNDERGRADUATE_STUDENT`
--

DROP TABLE IF EXISTS `UNDERGRADUATE_STUDENT`;
CREATE TABLE `UNDERGRADUATE_STUDENT` (
  `STUDENT_ID` int(8) NOT NULL,
  PRIMARY KEY  (`STUDENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `UNDERGRADUATE_STUDENT`
--

/*!40000 ALTER TABLE `UNDERGRADUATE_STUDENT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `UNDERGRADUATE_STUDENT` (`STUDENT_ID`) VALUES 
 (1),
 (2),
 (3),
 (4),
 (5);
COMMIT;
/*!40000 ALTER TABLE `UNDERGRADUATE_STUDENT` ENABLE KEYS */;


--
-- Definition of table `UTENSIL`
--

DROP TABLE IF EXISTS `UTENSIL`;
CREATE TABLE `UTENSIL` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `UTENSIL`
--

/*!40000 ALTER TABLE `UTENSIL` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `UTENSIL` (`ID`,`NAME`) VALUES 
 (1,'Spoon'),
 (2,'Knife'),
 (3,'Fork');
COMMIT;
/*!40000 ALTER TABLE `UTENSIL` ENABLE KEYS */;


--
-- Definition of table `WHEEL`
--

DROP TABLE IF EXISTS `WHEEL`;
CREATE TABLE `WHEEL` (
  `ID` int(8) NOT NULL,
  `RADIUS` int(8) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `WHEEL`
--

/*!40000 ALTER TABLE `WHEEL` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `WHEEL` (`ID`,`RADIUS`) VALUES 
 (1,1),
 (2,5),
 (3,10);
COMMIT;
/*!40000 ALTER TABLE `WHEEL` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

-- DISABLE FOREIGN KEY CHECKS
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `AD_DATATYPE`;
CREATE TABLE AD_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_AL_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_DAL_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE3_AL_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE3_AL_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE3_AL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE4_AL1_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE4_AL2_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE4_AL1_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE4_AL2_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE4_AL1_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE4_AL2_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE5_AL_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE5_AL_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE5_AL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE5_DAL_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE5_DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE5_DAL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE5_CTY_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE5_CTY_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE5_CTY_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE6_ADL_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_AL_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_BNN_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_BNR_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_BNS_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_CAR_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_CEN_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_CNT_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_CPA_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_CTY_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_DEL_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_DINSTA_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_DINSTQ_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_DIR_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_DMOD_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_DMODID_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_INT_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_POB_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_PRE_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_SAL_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_STA_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_STB_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_STR_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_STTYP_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_UNID_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_UNIT_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_ZIP_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_ADL_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE6_BNS_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE6_BNS_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE6_DAL_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE6_DAL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE6_INT_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE6_INT_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE6_STB_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE6_STB_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE6_ZIP_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE6_ZIP_CODE VARCHAR(50)  DEFAULT NULL,
  AD_DATATYPE_VALUE8_ID INT(8) DEFAULT NULL,
  PRIMARY KEY  (ID),
  KEY FK_AD_DATATYPE_VALUE8 (AD_DATATYPE_VALUE8_ID),
  CONSTRAINT FK_AD_DATATYPE_VALUE8 FOREIGN KEY (AD_DATATYPE_VALUE8_ID) REFERENCES AD_DATATYPE_VALUE8 (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `AD_DATATYPE_VALUE8`;
CREATE TABLE AD_DATATYPE_VALUE8 (
  ID INT(8) NOT NULL,
  AL_VALUE VARCHAR(50)  DEFAULT NULL,
  AL_CODE VARCHAR(50)  DEFAULT NULL,
  AL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  DAL_CODE VARCHAR(50)  DEFAULT NULL,
  DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  DAL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  CTY_VALUE VARCHAR(50)  DEFAULT NULL,
  CTY_CODE VARCHAR(50)  DEFAULT NULL,
  CTY_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `AD_DATATYPE_VALUE9`;
CREATE TABLE AD_DATATYPE_VALUE9 (
  ID INT(8) NOT NULL,
  AL_VALUE VARCHAR(50)  DEFAULT NULL,
  AL_CODE VARCHAR(50)  DEFAULT NULL,
  AL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  DAL_CODE VARCHAR(50)  DEFAULT NULL,
  DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  DAL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  CTY_VALUE VARCHAR(50)  DEFAULT NULL,
  CTY_CODE VARCHAR(50)  DEFAULT NULL,
  CTY_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `AD_DATATYPE_VALUE7`;
CREATE TABLE AD_DATATYPE_VALUE7 (
  AD_DATATYPE_ID INT(8) DEFAULT NULL,
  AL_VALUE VARCHAR(50)  DEFAULT NULL,
  AL_CODE VARCHAR(50)  DEFAULT NULL,
  AL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  DAL_CODE VARCHAR(50)  DEFAULT NULL,
  DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  DAL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  CTY_VALUE VARCHAR(50)  DEFAULT NULL,
  CTY_CODE VARCHAR(50)  DEFAULT NULL,
  CTY_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  KEY FK_AD_DATATYPE_VAL_AD_DATATYPE (AD_DATATYPE_ID),
  CONSTRAINT FK_AD_DATATYPE_VAL_AD_DATATYPE FOREIGN KEY (AD_DATATYPE_ID) REFERENCES AD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `AD_AD_DATATYPE_VALUE9`;
CREATE TABLE AD_AD_DATATYPE_VALUE9 (
  AD_DATATYPE_ID INT(8) NOT NULL,
  AD_DATATYPE_VALUE9_ID INT(8) NOT NULL,
  PRIMARY KEY  (AD_DATATYPE_VALUE9_ID,AD_DATATYPE_ID),
  KEY FK_AD_AD_DATATYPE__AD_DATATYPE (AD_DATATYPE_ID),
  CONSTRAINT FK_AD_AD_DATATYPE__AD_DATATYPE FOREIGN KEY (AD_DATATYPE_ID) REFERENCES AD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_AD_AD_DATATYP_AD_DATATYPE_V FOREIGN KEY (AD_DATATYPE_VALUE9_ID) REFERENCES AD_DATATYPE_VALUE9 (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `BL_DATATYPE`;
CREATE TABLE BL_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(1)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE VARCHAR(1)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `BL_NONNULL_DATATYPE`;
CREATE TABLE BL_NONNULL_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(1)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;


DROP TABLE IF EXISTS `CD_DATATYPE_VALUE7`;
CREATE TABLE CD_DATATYPE_VALUE7 (
  ID INT(8) NOT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  CODE VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_NAME VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  DISPLAY_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  DISPLAY_VALUE VARCHAR(50)  DEFAULT NULL,
  ORIG_TXT_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  ORIG_TXT_VALUE VARCHAR(50)  DEFAULT NULL ,
  ORIG_TXT_DESCRIPTION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `CD_DATATYPE_VALUE8`;
CREATE TABLE CD_DATATYPE_VALUE8 (
  ID INT(8) NOT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  CODE VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_NAME VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  DISPLAY_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  DISPLAY_VALUE VARCHAR(50)  DEFAULT NULL,
  ORIG_TXT_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  ORIG_TXT_VALUE VARCHAR(50)  DEFAULT NULL ,
  ORIG_TXT_DESCRIPTION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `CD_DATATYPE`;
CREATE TABLE CD_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE4_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE4_CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE4_CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  VALUE4_CODE_SYSTEM_NAME VARCHAR(50)  DEFAULT NULL,
  VALUE4_DISPLAY_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_DISPLAY_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE4_ORIG_TXT_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_ORIG_TXT_VALUE VARCHAR(50)  DEFAULT NULL ,
  VALUE4_ORIG_TXT_DESCRIPTION VARCHAR(50)  DEFAULT NULL,
  VALUE5_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE5_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE5_CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE5_CODE_SYSTEM_NAME VARCHAR(50)  DEFAULT NULL,
  VALUE5_CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  VALUE5_DISPLAY_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE5_DISPLAY_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE5_ORIG_TXT_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE5_ORIG_TXT_VALUE VARCHAR(50)  DEFAULT NULL ,
  VALUE5_ORIG_TXT_DESCRIPTION VARCHAR(50)  DEFAULT NULL,
  CD_DATATYPE_VALUE7_ID INT(8) DEFAULT NULL,
  PRIMARY KEY  (ID),
  KEY FK_CD_DATATYPE_VALUE7 (CD_DATATYPE_VALUE7_ID),
  CONSTRAINT FK_CD_DATATYPE_VALUE7 FOREIGN KEY (CD_DATATYPE_VALUE7_ID) REFERENCES CD_DATATYPE_VALUE7 (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `CD_DATATYPE_VALUE6`;
CREATE TABLE CD_DATATYPE_VALUE6 (
  CD_DATATYPE_ID INT(8) NOT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  CODE VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_NAME VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  DISPLAY_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  DISPLAY_VALUE VARCHAR(50)  DEFAULT NULL,
  ORIG_TXT_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  ORIG_TXT_VALUE VARCHAR(50)  DEFAULT NULL ,
  ORIG_TXT_DESCRIPTION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (CD_DATATYPE_ID),
  CONSTRAINT FK_CD_DATATYPE_VAL_CD_DATATYPE FOREIGN KEY (CD_DATATYPE_ID) REFERENCES CD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `CD_CD_DATATYPE_VALUE8`;
CREATE TABLE CD_CD_DATATYPE_VALUE8 (
  CD_DATA_TYPE_ID INT(8) NOT NULL,
  CD_DATATYPE_VALUE8_ID INT(8) NOT NULL,
  PRIMARY KEY  (CD_DATATYPE_VALUE8_ID,CD_DATA_TYPE_ID),
  KEY FK_CD_CD_DATATYPE__CD_DATATYPE (CD_DATA_TYPE_ID),
  CONSTRAINT FK_CD_CD_DATATYPE__CD_DATATYPE FOREIGN KEY (CD_DATA_TYPE_ID) REFERENCES CD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_CD_CD_DATATYP_CD_DATATYPE_V FOREIGN KEY (CD_DATATYPE_VALUE8_ID) REFERENCES CD_DATATYPE_VALUE8 (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_AD_DATATYPE`;
CREATE TABLE DSET_AD_DATATYPE (
  ID INT(8) NOT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_AD_VALUE1`;
CREATE TABLE DSET_AD_VALUE1 (
  DSET_AD_DATATYPE_ID INT(8) NOT NULL,
  ADXP_AL_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_AD_DATATYPE_ID),
  CONSTRAINT FK_DSER_AD_DATATY_VAL_AD_DA FOREIGN KEY (DSET_AD_DATATYPE_ID) REFERENCES DSET_AD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_AD_VALUE2`;
CREATE TABLE DSET_AD_VALUE2 (
  DSET_AD_DATATYPE_ID INT(8) NOT NULL,
  ADXP_DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_CODE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_AD_DATATYPE_ID),
  CONSTRAINT FK_DSER_AD_DATAT_VAL_AD_DATAT FOREIGN KEY (DSET_AD_DATATYPE_ID) REFERENCES DSET_AD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_AD_VALUE3`;
CREATE TABLE DSET_AD_VALUE3 (
  DSET_AD_DATATYPE_ID INT(8) NOT NULL,
  ADXP_AL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_AD_DATATYPE_ID),
  CONSTRAINT FK_DSER_AD_DATAT_VAL_AD3 FOREIGN KEY (DSET_AD_DATATYPE_ID) REFERENCES DSET_AD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_AD_VALUE4`;
CREATE TABLE DSET_AD_VALUE4 (
  DSET_AD_DATATYPE_ID INT(8) NOT NULL,
  ADXP_AL1_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL2_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL1_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL2_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL2_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_AL1_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_AD_DATATYPE_ID),
  CONSTRAINT FK_DSER_AD_TYPE_VAL_AD4 FOREIGN KEY (DSET_AD_DATATYPE_ID) REFERENCES DSET_AD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_AD_VALUE5`;
CREATE TABLE DSET_AD_VALUE5 (
  DSET_AD_DATATYPE_ID INT(8) NOT NULL,
  ADXP_AL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CTY_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CTY_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_CTY_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_AD_DATATYPE_ID),
  CONSTRAINT FK_DSER_AD_DATAT_VAL_AD5 FOREIGN KEY (DSET_AD_DATATYPE_ID) REFERENCES DSET_AD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_AD_VALUE6`;
CREATE TABLE DSET_AD_VALUE6 (
  DSET_AD_DATATYPE_ID INT(8) NOT NULL,
  ADXP_ADL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_BNN_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_BNR_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_BNS_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CAR_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CEN_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CNT_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CPA_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CTY_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_DEL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_DINSTA_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_DINSTQ_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_DIR_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_DMOD_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_DMODID_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_INT_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_POB_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_PRE_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_SAL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_STA_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_STB_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_STR_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_STTYP_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_UNID_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_UNIT_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_ZIP_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_ADL_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_BNS_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_BNS_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_INT_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_INT_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_STB_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_STB_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_ZIP_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_ZIP_CODE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_AD_DATATYPE_ID),
  CONSTRAINT FK_DSER_AD_TYPE_VAL_AD6 FOREIGN KEY (DSET_AD_DATATYPE_ID) REFERENCES DSET_AD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_AD_VALUE7`;
CREATE TABLE DSET_AD_VALUE7 (
  ID INT(8) NOT NULL,
  DSET_AD_DATATYPE_ID INT(8) DEFAULT NULL,
  ADXP_AL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CTY_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CTY_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_CTY_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID),
  KEY FK_DSET_AD_VALUE_DSET_AD7 (DSET_AD_DATATYPE_ID),
  CONSTRAINT FK_DSET_AD_VALUE_DSET_AD7 FOREIGN KEY (DSET_AD_DATATYPE_ID) REFERENCES DSET_AD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_AD_VALUE8`;
CREATE TABLE DSET_AD_VALUE8 (
  ID INT(8) NOT NULL,
  ADXP_AL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_AL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ADXP_DAL_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CTY_VALUE VARCHAR(50)  DEFAULT NULL,
  ADXP_CTY_CODE VARCHAR(50)  DEFAULT NULL,
  ADXP_CTY_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_AD_DSET_AD_VALUE8`;
CREATE TABLE DSET_AD_DSET_AD_VALUE8 (
  DSET_AD_DATATYPE_ID INT(8) NOT NULL,
  DSET_AD_VALUE8_ID INT(8) NOT NULL,
  PRIMARY KEY  (DSET_AD_VALUE8_ID,DSET_AD_DATATYPE_ID),
  KEY FK_DSER_AD_DSET__DSET_AD_DATA (DSET_AD_DATATYPE_ID),
  CONSTRAINT FK_DSER_AD_DSET__DSET_AD_DATA FOREIGN KEY (DSET_AD_DATATYPE_ID) REFERENCES DSET_AD_DATATYPE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_DSER_AD_DSET__DSET_AD_VALU FOREIGN KEY (DSET_AD_VALUE8_ID) REFERENCES DSET_AD_VALUE8 (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_CD`;
CREATE TABLE DSET_CD (
  ID INT(8) NOT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_CD_VALUE1`;
CREATE TABLE DSET_CD_VALUE1 (
  DSET_CD_ID INT(8) NOT NULL,
  CODE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_CD_ID),
  CONSTRAINT FK_DSET_CD_VALUE1_DSET_CD FOREIGN KEY (DSET_CD_ID) REFERENCES DSET_CD (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_CD_VALUE2`;
CREATE TABLE DSET_CD_VALUE2 (
  DSET_CD_ID INT(8) NOT NULL,
  CODE VARCHAR(50)  DEFAULT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_CD_ID),
  CONSTRAINT FK_DSET_CD_VALUE2_DSET_CD FOREIGN KEY (DSET_CD_ID) REFERENCES DSET_CD (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_CD_VALUE3`;
CREATE TABLE DSET_CD_VALUE3 (
  DSET_CD_ID INT(8) NOT NULL,
  CODE VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_NAME VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_CD_ID),
  CONSTRAINT FK_DSET_CD_VALUE3_DSET_CD FOREIGN KEY (DSET_CD_ID) REFERENCES DSET_CD (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_CD_VALUE4`;
CREATE TABLE DSET_CD_VALUE4 (
  DSET_CD_ID INT(8) NOT NULL,
  CODE VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_NAME VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  DISPLAYABLE_VALUE VARCHAR(50)  DEFAULT NULL,
  ORIGINALTEXT_VALUE VARCHAR(50)  DEFAULT NULL,
  ORIGINALTEXT_DESC VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_CD_ID),
  CONSTRAINT FK_DSET_CD_VALUE4_DSET_CD FOREIGN KEY (DSET_CD_ID) REFERENCES DSET_CD (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_CD_VALUE5`;
CREATE TABLE DSET_CD_VALUE5 (
  DSET_CD_ID INT(8) NOT NULL,
  CODE VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_NAME VARCHAR(50)  DEFAULT NULL,
  CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  DISPLAYABLE_VALUE VARCHAR(50)  DEFAULT NULL,
  ORIGINALTEXT_VALUE VARCHAR(50)  DEFAULT NULL,
  ORIGINALTEXT_DESC VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_CD_ID),
  CONSTRAINT FK_DSET_CD_VALUE5_DSET_CD FOREIGN KEY (DSET_CD_ID) REFERENCES DSET_CD (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_CD_VALUE6`;
CREATE TABLE DSET_CD_VALUE6 (
  ID INT(8) NOT NULL,
  DSET_CD_ID INT(8) DEFAULT NULL,
  CODE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID),
  KEY FK_DSET_CD_VALUE6_DSET_CD (DSET_CD_ID),
  CONSTRAINT FK_DSET_CD_VALUE6_DSET_CD FOREIGN KEY (DSET_CD_ID) REFERENCES DSET_CD (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_CD_VALUE7`;
CREATE TABLE DSET_CD_VALUE7 (
  ID INT(8) NOT NULL,
  CODE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_CD_CD_VALUE7`;
CREATE TABLE DSET_CD_CD_VALUE7 (
  DSET_CD_ID INT(8) NOT NULL,
  DSET_CD_VALUE7_ID INT(8) NOT NULL,
  PRIMARY KEY  (DSET_CD_ID,DSET_CD_VALUE7_ID),
  KEY FK_DSET_CD_CD_VA_DSET_CD5 (DSET_CD_VALUE7_ID),
  CONSTRAINT FK_DSET_CD_CD_VALUE7_DSET_CD FOREIGN KEY (DSET_CD_ID) REFERENCES DSET_CD (ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_DSET_CD_CD_VA_DSET_CD5 FOREIGN KEY (DSET_CD_VALUE7_ID) REFERENCES DSET_CD_VALUE7 (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_EN`;
CREATE TABLE DSET_EN (
  ID INT(8) NOT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_EN_VALUE1`;
CREATE TABLE DSET_EN_VALUE1 (
  DSET_EN_DATATYPE_ID INT(8) NOT NULL,
  ENXP_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_EN_DATATYPE_ID),
  CONSTRAINT FK_EN_TYPE_VAL_EN_TYPE FOREIGN KEY (DSET_EN_DATATYPE_ID) REFERENCES DSET_EN (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_EN_VALUE2`;
CREATE TABLE DSET_EN_VALUE2 (
  DSET_EN_DATATYPE_ID INT(8) NOT NULL,
  ENXP_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_EN_DATATYPE_ID),
  CONSTRAINT FK_EN_TYPE_VAL2_EN_TYPE FOREIGN KEY (DSET_EN_DATATYPE_ID) REFERENCES DSET_EN (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_EN_VALUE3`;
CREATE TABLE DSET_EN_VALUE3 (
  DSET_EN_DATATYPE_ID INT(8) NOT NULL,
  ENXP_PN_CODE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODESYSTEMVERSION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_EN_DATATYPE_ID),
  CONSTRAINT FK_EN_TYPE_VAL3_EN_TYPE FOREIGN KEY (DSET_EN_DATATYPE_ID) REFERENCES DSET_EN (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_EN_VALUE4`;
CREATE TABLE DSET_EN_VALUE4 (
  DSET_EN_DATATYPE_ID INT(8) NOT NULL,
  ENXP_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_ENPQ VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_EN_DATATYPE_ID),
  CONSTRAINT FK_EN_DATATYPE_VAL4_EN_DATAT FOREIGN KEY (DSET_EN_DATATYPE_ID) REFERENCES DSET_EN (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_EN_VALUE5`;
CREATE TABLE DSET_EN_VALUE5 (
  DSET_EN_DATATYPE_ID INT(8) NOT NULL,
  ENXP_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN2_VALUE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN2_CODE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ENXP_PN2_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ENXP_PN2_CODESYSTEMVERSION VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODESYSTEMVERSION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_EN_DATATYPE_ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_EN_VALUE6`;
CREATE TABLE DSET_EN_VALUE6 (
  DSET_EN_DATATYPE_ID INT(8) NOT NULL,
  ENXP_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODESYSTEMVERSION VARCHAR(50)  DEFAULT NULL,
  ENXP_ON_VALUE VARCHAR(50)  DEFAULT NULL,
  ENXP_ON_CODE VARCHAR(50)  DEFAULT NULL,
  ENXP_ON_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ENXP_ON_CODESYSTEMVERSION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_EN_DATATYPE_ID),
  CONSTRAINT FK_EN_DATATYPE_VAL6_EN_DATAT FOREIGN KEY (DSET_EN_DATATYPE_ID) REFERENCES DSET_EN (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_EN_VALUE7`;
CREATE TABLE DSET_EN_VALUE7 (
  DSET_EN_DATATYPE_ID INT(8) NOT NULL,
  ENXP_PN_CODE VARCHAR(50)  DEFAULT NULL,
  ENXP_ON_CODE VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  ENXP_ON_VALUE VARCHAR(50)  DEFAULT NULL,
  ENXP_ON_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODESYSTEM VARCHAR(50)  DEFAULT NULL,
  ENXP_ON_CODESYSTEMVERSION VARCHAR(50)  DEFAULT NULL,
  ENXP_PN_CODESYSTEMVERSION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_EN_DATATYPE_ID),
  CONSTRAINT FK_EN_DATATYPE_VAL7_EN_DATAT FOREIGN KEY (DSET_EN_DATATYPE_ID) REFERENCES DSET_EN (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_II`;
CREATE TABLE DSET_II (
  ID INT(8) NOT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_II_VALUE1`;
CREATE TABLE DSET_II_VALUE1 (
  DSET_II_ID INT(8) NOT NULL,
  EXTENSION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_II_ID),
  CONSTRAINT FK_DSET_II_VALUE1_DSETII FOREIGN KEY (DSET_II_ID) REFERENCES DSET_II (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_II_VALUE2`;
CREATE TABLE DSET_II_VALUE2 (
  DSET_II_ID INT(8) NOT NULL,
  EXTENSION VARCHAR(50)  DEFAULT NULL,
  ROOT VARCHAR(50)  DEFAULT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_II_ID),
  CONSTRAINT FK_DSET_II_VALUE2_DSETII FOREIGN KEY (DSET_II_ID) REFERENCES DSET_II (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_II_VALUE3`;
CREATE TABLE DSET_II_VALUE3 (
  DSET_II_ID INT(8) NOT NULL,
  EXTENSION VARCHAR(50)  DEFAULT NULL,
  IDENTIFIER_NAME VARCHAR(50)  DEFAULT NULL,
  DISPLAYABLE_VALUE VARCHAR(1)  DEFAULT NULL,
  RELIABILITY VARCHAR(50)  DEFAULT NULL,
  SCOPE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_II_ID),
  CONSTRAINT FK_DSET_II_VALUE3_DSETII FOREIGN KEY (DSET_II_ID) REFERENCES DSET_II (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_II_VALUE4`;
CREATE TABLE DSET_II_VALUE4 (
  DSET_II_ID INT(8) NOT NULL,
  ROOT VARCHAR(50)  DEFAULT NULL,
  EXTENSION VARCHAR(50)  DEFAULT NULL,
  IDENTIFIER_NAME VARCHAR(50)  DEFAULT NULL,
  RELIABILITY VARCHAR(50)  DEFAULT NULL,
  SCOPE VARCHAR(50)  DEFAULT NULL,
  DISPLAYABLE_VALUE VARCHAR(1)  DEFAULT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_II_ID),
  CONSTRAINT FK_DSET_II_VALUE4_DSETII FOREIGN KEY (DSET_II_ID) REFERENCES DSET_II (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_II_VALUE5`;
CREATE TABLE DSET_II_VALUE5 (
  ID INT(8) NOT NULL,
  DSET_II_ID INT(8) DEFAULT NULL,
  EXTENSION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID),
  KEY FK_DSET_II_VALUE5_DSET_II (DSET_II_ID),
  CONSTRAINT FK_DSET_II_VALUE5_DSET_II FOREIGN KEY (DSET_II_ID) REFERENCES DSET_II (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_II_VALUE6`;
CREATE TABLE DSET_II_VALUE6 (
  ID INT(8) NOT NULL,
  EXTENSION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_II_II_VALUE6`;
CREATE TABLE DSET_II_II_VALUE6 (
  DSET_II_ID INT(8) NOT NULL,
  DSET_II_VALUE6_ID INT(8) NOT NULL,
  PRIMARY KEY  (DSET_II_VALUE6_ID,DSET_II_ID),
  KEY FK_DSET_II_II_VALUE6_DSET_II (DSET_II_ID),
  CONSTRAINT FK_DSET_II_II_VALUE6_DSET_II FOREIGN KEY (DSET_II_ID) REFERENCES DSET_II (ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_DSET_II_II_VA_DSET_II_VAL FOREIGN KEY (DSET_II_VALUE6_ID) REFERENCES DSET_II_VALUE6 (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL`;
CREATE TABLE DSET_TEL (
  ID INT(8) NOT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_EMAIL`;
CREATE TABLE DSET_TEL_EMAIL (
  ID INT(8) NOT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_EMAIL_VALUE1`;
CREATE TABLE DSET_TEL_EMAIL_VALUE1 (
  DSET_TEL_EMAIL_ID INT(8) NOT NULL,
  TEL_EMAIL_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_TEL_EMAIL_ID),
  CONSTRAINT FK_DSET_TEL_EMAI_DSET_TEL_EMA FOREIGN KEY (DSET_TEL_EMAIL_ID) REFERENCES DSET_TEL_EMAIL (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_EMAIL_VALUE2`;
CREATE TABLE DSET_TEL_EMAIL_VALUE2 (
  DSET_TEL_EMAIL_ID INT(8) DEFAULT NULL,
  TEL_EMAIL_VALUE VARCHAR(50)  DEFAULT NULL,
  TEL_EMAIL_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  KEY FK_TEL_EMAIL_VAL_DSET_TEL_EMA (DSET_TEL_EMAIL_ID),
  CONSTRAINT FK_TEL_EMAIL_VAL_DSET_TEL_EMA FOREIGN KEY (DSET_TEL_EMAIL_ID) REFERENCES DSET_TEL_EMAIL (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_PERSON`;
CREATE TABLE DSET_TEL_PERSON (
  ID INT(8) NOT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_PERSON_VALUE1`;
CREATE TABLE DSET_TEL_PERSON_VALUE1 (
  DSET_TEL_PERSON_ID INT(8) NOT NULL,
  TEL_PERSON_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_TEL_PERSON_ID),
  CONSTRAINT FK_DSET_TEL_PERS_DSET_TEL_PER FOREIGN KEY (DSET_TEL_PERSON_ID) REFERENCES DSET_TEL_PERSON (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_PHONE`;
CREATE TABLE DSET_TEL_PHONE (
  ID INT(8) NOT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_PHONE_VALUE1`;
CREATE TABLE DSET_TEL_PHONE_VALUE1 (
  DSET_TEL_PHONE_ID INT(8) NOT NULL,
  TEL_PHONE_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_TEL_PHONE_ID),
  CONSTRAINT FK_DSET_TEL_PHON_DSET_TEL_PHO FOREIGN KEY (DSET_TEL_PHONE_ID) REFERENCES DSET_TEL_PHONE (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_URL`;
CREATE TABLE DSET_TEL_URL (
  ID INT(8) NOT NULL,
  NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_URL_VALUE1`;
CREATE TABLE DSET_TEL_URL_VALUE1 (
  DSET_TEL_URL_ID INT(8) NOT NULL,
  TEL_URL_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_TEL_URL_ID),
  CONSTRAINT FK_DSET_TEL_URL_V_DSET_TEL_URL FOREIGN KEY (DSET_TEL_URL_ID) REFERENCES DSET_TEL_URL (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_VALUE1`;
CREATE TABLE DSET_TEL_VALUE1 (
  DSET_TEL_ID INT(8) NOT NULL,
  TEL_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (DSET_TEL_ID),
  CONSTRAINT FK_DSET_TEL_VALUE1_DSET_TEL FOREIGN KEY (DSET_TEL_ID) REFERENCES DSET_TEL (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_VALUE3`;
CREATE TABLE DSET_TEL_VALUE3 (
  ID INT(8) NOT NULL,
  TEL_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_TEL_VALUE_3`;
CREATE TABLE DSET_TEL_TEL_VALUE_3 (
  DSET_TEL_ID INT(8) NOT NULL,
  DSET_TEL_VALUE3_ID INT(8) NOT NULL,
  PRIMARY KEY  (DSET_TEL_ID,DSET_TEL_VALUE3_ID),
  KEY FK_DSET_TEL_TEL__DSET_TEL_VAL (DSET_TEL_VALUE3_ID),
  CONSTRAINT FK_DSET_TEL_TEL_VALU_DSET_TEL FOREIGN KEY (DSET_TEL_ID) REFERENCES DSET_TEL (ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_DSET_TEL_TEL__DSET_TEL_VAL FOREIGN KEY (DSET_TEL_VALUE3_ID) REFERENCES DSET_TEL_VALUE3 (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `DSET_TEL_VALUE2`;
CREATE TABLE DSET_TEL_VALUE2 (
  ID INT(8) NOT NULL,
  DSET_TEL_ID INT(8) DEFAULT NULL,
  TEL_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID),
  KEY FK_DSET_TEL_VALUE2_DSET_TEL (DSET_TEL_ID),
  CONSTRAINT FK_DSET_TEL_VALUE2_DSET_TEL FOREIGN KEY (DSET_TEL_ID) REFERENCES DSET_TEL (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `ED_DATATYPE`;
CREATE TABLE ED_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_DATA LONGBLOB,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_DATA LONGBLOB,
  VALUE2_COMPRESSION VARCHAR(50)  DEFAULT NULL,
  VALUE3_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE3_DATA LONGBLOB,
  VALUE3_COMPRESSION VARCHAR(50)  DEFAULT NULL,
  VALUE3_DESCRIPTION VARCHAR(50)  DEFAULT NULL,
  VALUE3_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `ED_TEXT_DATATYPE`;
CREATE TABLE ED_TEXT_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE3_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE3_DATA LONGBLOB,
  VALUE3_COMPRESSION VARCHAR(50)  DEFAULT NULL,
  VALUE3_DESCRIPTION VARCHAR(50)  DEFAULT NULL,
  VALUE3_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `EN_DATATYPE`;
CREATE TABLE EN_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_PN_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE3_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE3_PN_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE3_PN_CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE3_PN_CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  VALUE4_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE4_PN_ENPQ VARCHAR(50)  DEFAULT NULL,
  VALUE5_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE5_PN2_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE5_PN_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE5_PN2_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE5_PN_CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE5_PN2_CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE5_PN_CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  VALUE5_PN2_CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  VALUE6_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_ON_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE6_PN_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE6_ON_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE6_PN_CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE6_ON_CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE6_PN_CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  VALUE6_ON_CODE_SYSTEM_VERSION VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `EN_ON_DATATYPE`;
CREATE TABLE EN_ON_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_ON_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `EN_PN_DATATYPE`;
CREATE TABLE EN_PN_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_PN_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `II_DATATYPE`;
CREATE TABLE II_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_EXTENSION VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_ROOT VARCHAR(50)  DEFAULT NULL,
  VALUE2_EXTENSION VARCHAR(50)  DEFAULT NULL,
  VALUE3_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE3_EXTENSION VARCHAR(50)  DEFAULT NULL,
  VALUE3_IDENTIFIER_NAME VARCHAR(50)  DEFAULT NULL,
  VALUE3_RELIABILITY VARCHAR(50)  DEFAULT NULL,
  VALUE3_SCOPE VARCHAR(50)  DEFAULT NULL,
  VALUE3_DISPLAYABLE VARCHAR(1)  DEFAULT NULL,
  VALUE4_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_ROOT VARCHAR(50)  DEFAULT NULL,
  VALUE4_EXTENSION VARCHAR(50)  DEFAULT NULL,
  VALUE4_IDENTIFIER_NAME VARCHAR(50)  DEFAULT NULL,
  VALUE4_RELIABILITY VARCHAR(50)  DEFAULT NULL,
  VALUE4_SCOPE VARCHAR(50)  DEFAULT NULL,
  VALUE4_DISPLAYABLE VARCHAR(1)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `INT_DATATYPE`;
CREATE TABLE INT_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE DECIMAL(22,0) DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE DECIMAL(22,0) DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `IVL_INT`;
CREATE TABLE IVL_INT (
  ID INT(8) NOT NULL,
  VALUE1_LOW_VALUE DECIMAL(22,0) DEFAULT NULL,
  VALUE1_HIGH_VALUE DECIMAL(22,0) DEFAULT NULL,
  VALUE2_HIGH_VALUE DECIMAL(22,0) DEFAULT NULL,
  VALUE2_LOWCLOSED VARCHAR(1)  DEFAULT NULL,
  VALUE3_ANY_VALUE DECIMAL(22,0) DEFAULT NULL,
  VALUE3_LOW_VALUE DECIMAL(22,0) DEFAULT NULL,
  VALUE3_HIGH_CLOSED VARCHAR(1)  DEFAULT NULL,
  VALUE4_LOW_VALUE DECIMAL(22,0) DEFAULT NULL,
  VALUE4_HIGH_VALUE DECIMAL(22,0) DEFAULT NULL,
  VALUE4_WIDTH_VALUE INT(8) DEFAULT NULL,
  VALUE4_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `IVL_PQ`;
CREATE TABLE IVL_PQ (
  ID INT(8) NOT NULL,
  VALUE1_LOW_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE2_LOW_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE2_LOW_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE2_LOW_UNIT VARCHAR(50)  DEFAULT NULL,
  VALUE3_LOW_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE3_LOW_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE3_LOW_UNIT VARCHAR(50)  DEFAULT NULL,
  VALUE3_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_HIGH_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE4_HIGH_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE4_HIGH_UNIT VARCHAR(50)  DEFAULT NULL,
  VALUE4_HIGH_CLOSED VARCHAR(1)  DEFAULT NULL,
  VALUE4_HIGH_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_LOW_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE4_LOW_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE4_LOW_UNIT VARCHAR(50)  DEFAULT NULL,
  VALUE4_LOW_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_LOW_CLOSED VARCHAR(1)  DEFAULT NULL,
  VALUE4_WIDTH_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE4_WIDTH_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE4_WIDTH_UNIT VARCHAR(50)  DEFAULT NULL,
  VALUE4_WIDTH_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `IVL_PQV`;
CREATE TABLE IVL_PQV (
  ID INT(8) NOT NULL,
  VALUE1_LOW_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE1_HIGH_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE2_LOW_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE2_LOW_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE2_HIGH_CLOSED VARCHAR(1)  DEFAULT NULL,
  VALUE3_LOW_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE3_LOW_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE3_HIGH_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE3_HIGH_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE3_HIGH_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_HIGH_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE4_HIGH_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE4_HIGH_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_LOW_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE4_LOW_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE4_LOW_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_WIDTH_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE4_WIDTH_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE4_WIDTH_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `IVL_REAL`;
CREATE TABLE IVL_REAL (
  ID INT(8) DEFAULT NULL,
  VALUE1_LOW_VALUE DOUBLE DEFAULT NULL,
  VALUE1_HIGH_VALUE DOUBLE DEFAULT NULL,
  VALUE2_HIGH_VALUE DOUBLE DEFAULT NULL,
  VALUE2_LOW_CLOSED VARCHAR(1)  DEFAULT NULL,
  VALUE3_ANY_VALUE DOUBLE DEFAULT NULL,
  VALUE3_HIGH_VALUE DOUBLE DEFAULT NULL,
  VALUE3_HIGH_CLOSED VARCHAR(1)  DEFAULT NULL,
  VALUE3_LOW_VALUE DOUBLE DEFAULT NULL,
  VALUE3_LOW_CLOSED VARCHAR(1)  DEFAULT NULL,
  VALUE3_WIDTH_VALUE DOUBLE DEFAULT NULL,
  VALUE3_WIDTH_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE3_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `IVL_TS`;
CREATE TABLE IVL_TS (
  ID INT(8) DEFAULT NULL,
  VALUE1_LOW_VALUE DATETIME DEFAULT NULL,
  VALUE1_HIGH_VALUE DATETIME DEFAULT NULL,
  VALUE2_HIGH_VALUE DATETIME DEFAULT NULL,
  VALUE2_LOW_CLOSED VARCHAR(1)  DEFAULT NULL,
  VALUE3_HIGH_VALUE DATETIME DEFAULT NULL,
  VALUE3_LOW_VALUE DATETIME DEFAULT NULL,
  VALUE3_WIDTH_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE3_WIDTH_VALUE INT(8) DEFAULT NULL,
  VALUE3_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `PQV_DATATYPE`;
CREATE TABLE PQV_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE2_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE3_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE3_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE4_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE4_PRECISION DECIMAL(22,0) DEFAULT NULL,
  VALUE4_VALUE DECIMAL(8,2) DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `PQ_DATATYPE`;
CREATE TABLE PQ_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE1_UNIT VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE3_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE3_UNIT VARCHAR(50)  DEFAULT NULL,
  VALUE3_VALUE DECIMAL(8,2) DEFAULT NULL,
  VALUE3_PRECISION DECIMAL(22,0) DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `REAL_DATATYPE`;
CREATE TABLE REAL_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE DOUBLE DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE DOUBLE DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `SC_DATATYPE`;
CREATE TABLE SC_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE1_CODE_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_CODE_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_CODE_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE2_CODE_CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE2_CODE_CODE_SYSTEM_NAME VARCHAR(50)  DEFAULT NULL,
  VALUE2_CODE_CODE_SYSTEM_VER VARCHAR(50)  DEFAULT NULL,
  VALUE3_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE3_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE_CODE VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE_CODE_SYSTEM VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE_CODE_SYSTEM_NAME VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE_CODE_SYSTEM_VER VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE_DISPLAY_NFLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE_DISPLAY_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE_ORIG_TXT_NFLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE_ORIG_TXT_DESC VARCHAR(50)  DEFAULT NULL,
  VALUE3_CODE_ORIG_TXT_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `ST_DATATYPE`;
CREATE TABLE ST_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `ST_NT_DATATYPE`;
CREATE TABLE ST_NT_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `TEL_DATATYPE`;
CREATE TABLE TEL_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `TEL_EMAIL_DATATYPE`;
CREATE TABLE TEL_EMAIL_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `TEL_PERSON_DATATYPE`;
CREATE TABLE TEL_PERSON_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `TEL_PHONE_DATATYPE`;
CREATE TABLE TEL_PHONE_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `TEL_URL_DATATYPE`;
CREATE TABLE TEL_URL_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE VARCHAR(50)  DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE VARCHAR(50)  DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;

DROP TABLE IF EXISTS `TS_DATATYPE`;
CREATE TABLE TS_DATATYPE (
  ID INT(8) NOT NULL,
  VALUE1_VALUE DATETIME DEFAULT NULL,
  VALUE2_NULL_FLAVOR VARCHAR(50)  DEFAULT NULL,
  VALUE2_VALUE DATETIME DEFAULT NULL,
  PRIMARY KEY  (ID)
) ENGINE=INNODB DEFAULT CHARSET=LATIN1;



-- ----------------------------------------------------------------------
-- SQL DATA BULK TRANSFER SCRIPT GENERATED BY THE MYSQL MIGRATION TOOLKIT
-- ----------------------------------------------------------------------

INSERT INTO AD_AD_DATATYPE_VALUE9(AD_DATATYPE_ID, AD_DATATYPE_VALUE9_ID)
VALUES (44, 2),
  (45, 2),
  (46, 2),
  (47, 2),
  (48, 2);

INSERT INTO AD_DATATYPE(ID, VALUE1_AL_VALUE, VALUE2_DAL_VALUE, VALUE2_DAL_CODE, VALUE3_AL_VALUE, VALUE3_AL_CODE, VALUE3_AL_CODESYSTEM, VALUE4_AL1_VALUE, VALUE4_AL2_VALUE, VALUE4_AL1_CODE, VALUE4_AL2_CODE, VALUE4_AL1_CODESYSTEM, VALUE4_AL2_CODESYSTEM, VALUE5_AL_VALUE, VALUE5_AL_CODE, VALUE5_AL_CODESYSTEM, VALUE5_DAL_CODE, VALUE5_DAL_VALUE, VALUE5_DAL_CODESYSTEM, VALUE5_CTY_VALUE, VALUE5_CTY_CODE, VALUE5_CTY_CODESYSTEM, VALUE6_ADL_VALUE, VALUE6_AL_VALUE, VALUE6_BNN_VALUE, VALUE6_BNR_VALUE, VALUE6_BNS_VALUE, VALUE6_CAR_VALUE, VALUE6_CEN_VALUE, VALUE6_CNT_VALUE, VALUE6_CPA_VALUE, VALUE6_CTY_VALUE, VALUE6_DAL_VALUE, VALUE6_DEL_VALUE, VALUE6_DINSTA_VALUE, VALUE6_DINSTQ_VALUE, VALUE6_DIR_VALUE, VALUE6_DMOD_VALUE, VALUE6_DMODID_VALUE, VALUE6_INT_VALUE, VALUE6_POB_VALUE, VALUE6_PRE_VALUE, VALUE6_SAL_VALUE, VALUE6_STA_VALUE, VALUE6_STB_VALUE, VALUE6_STR_VALUE, VALUE6_STTYP_VALUE, VALUE6_UNID_VALUE, VALUE6_UNIT_VALUE, VALUE6_ZIP_VALUE, VALUE6_ADL_CODE, VALUE6_BNS_CODE, VALUE6_BNS_CODESYSTEM, VALUE6_DAL_CODE, VALUE6_DAL_CODESYSTEM, VALUE6_INT_CODE, VALUE6_INT_CODESYSTEM, VALUE6_STB_CODE, VALUE6_STB_CODESYSTEM, VALUE6_ZIP_CODESYSTEM, VALUE6_ZIP_CODE, AD_DATATYPE_VALUE8_ID)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, '1 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, '2 E Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, '3 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, '4 Sun Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, '5 Sun Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, '5th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, '6th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, '7th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, '8th Floor', 'NCI5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, '9th Floor', 'NCI6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, 'E Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, 'E Jefferson Street', 'NCI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, 'Jefferson Street', 'NCI1', 'codeSystem1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, 'F Jefferson Street', 'NCI2', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, 'G Jefferson Street', 'NCI3', 'codeSystem3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, 'E Jefferson Street', NULL, 'NCI1', NULL, 'codeSystem1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, 'F Jefferson Street', NULL, 'NCI2', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'E Jefferson Street', NULL, 'NCI-DC1', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, 'F Jefferson Street', 'F Jefferson Street', NULL, 'NCI-DC2', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, NULL, 'G Jefferson Street', 'G Jefferson Street', 'NCI3', 'NCI-DC3', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (22, NULL, NULL, NULL, NULL, NULL, NULL, 'H Jefferson Street', 'H Jefferson Street', 'NCI4', 'NCI-DC4', 'codeSystem4', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, NULL, NULL, NULL, NULL, NULL, 'I Jefferson Street', 'I Jefferson Street', 'NCI5', 'NCI-DC5', 'codeSystem5', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', NULL, NULL, NULL, 'Delivery line1', NULL, 'Rockville', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE1', NULL, 'DAL_CODE1', 'Delivery line1', NULL, 'Rockville', 'CITY_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', NULL, 'AL_CODE_SYSTEM1', NULL, 'Delivery line1', 'DAL_CODE_SYSTEM1', 'Rockville', NULL, 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE2', 'AL_CODE_SYSTEM1', 'DAL_CODE2', 'Delivery line1', 'DAL_CODE_SYSTEM2', 'Rockville', 'CITY_CODE2', 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE3', 'AL_CODE_SYSTEM1', 'DAL_CODE3', 'Delivery line1', 'DAL_CODE_SYSTEM3', 'Rockville', 'CITY_CODE3', 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE1', 'VALUE6_AL_VALUE1', 'VALUE6_BNN_VALUE1', 'VALUE6_BNR_VALUE1', 'VALUE6_BNS_VALUE1', 'VALUE6_CAR_VALUE1', 'VALUE6_CEN_VALUE1', 'VALUE6_CNT_VALUE1', 'VALUE6_CPA_VALUE1', 'VALUE6_CTY_VALUE1', 'VALUE6_DAL_VALUE1', 'VALUE6_DEL_VALUE1', 'VALUE6_DINSTA_VALUE1', 'VALUE6_DINSTQ_VALUE1', 'VALUE6_DIR_VALUE1', 'VALUE6_DMOD_VALUE1', 'VALUE6_DMODID_VALUE1', 'VALUE6_INT_VALUE1', 'VALUE6_POB_VALUE1', 'VALUE6_PRE_VALUE1', 'VALUE6_SAL_VALUE1', 'VALUE6_STA_VALUE1', 'VALUE6_STB_VALUE1', 'VALUE6_STR_VALUE1', 'VALUE6_STTYP_VALUE1', 'VALUE6_UNID_VALUE1', 'VALUE6_UNIT_VALUE1', 'VALUE6_ZIP_VALUE1', 'VALUE6_ADL_CODE1', 'VALUE6_BNS_CODE1', 'VALUE6_BNS_CODESYSTEM1', 'VALUE6_DAL_CODE1', 'VALUE6_DAL_CODESYSTEM1', 'VALUE6_INT_CODE1', 'VALUE6_INT_CODESYSTEM1', 'VALUE6_STB_CODE1', 'VALUE6_STB_CODESYSTEM1', 'VALUE6_ZIP_CODESYSTEM1', 'VALUE6_ZIP_CODE1', NULL),
  (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE2', 'VALUE6_AL_VALUE2', 'VALUE6_BNN_VALUE2', 'VALUE6_BNR_VALUE2', 'VALUE6_BNS_VALUE2', 'VALUE6_CAR_VALUE2', 'VALUE6_CEN_VALUE2', 'VALUE6_CNT_VALUE2', 'VALUE6_CPA_VALUE2', 'VALUE6_CTY_VALUE2', 'VALUE6_DAL_VALUE2', 'VALUE6_DEL_VALUE2', 'VALUE6_DINSTA_VALUE2', 'VALUE6_DINSTQ_VALUE2', 'VALUE6_DIR_VALUE2', 'VALUE6_DMOD_VALUE2', 'VALUE6_DMODID_VALUE2', 'VALUE6_INT_VALUE2', 'VALUE6_POB_VALUE2', 'VALUE6_PRE_VALUE2', 'VALUE6_SAL_VALUE2', 'VALUE6_STA_VALUE2', 'VALUE6_STB_VALUE2', 'VALUE6_STR_VALUE2', 'VALUE6_STTYP_VALUE2', 'VALUE6_UNID_VALUE2', 'VALUE6_UNIT_VALUE2', 'VALUE6_ZIP_VALUE2', 'VALUE6_ADL_CODE2', 'VALUE6_BNS_CODE2', 'VALUE6_BNS_CODESYSTEM2', 'VALUE6_DAL_CODE2', 'VALUE6_DAL_CODESYSTEM2', 'VALUE6_INT_CODE2', 'VALUE6_INT_CODESYSTEM2', 'VALUE6_STB_CODE2', 'VALUE6_STB_CODESYSTEM2', 'VALUE6_ZIP_CODESYSTEM2', 'VALUE6_ZIP_CODE2', NULL),
  (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE3', 'VALUE6_AL_VALUE3', 'VALUE6_BNN_VALUE3', 'VALUE6_BNR_VALUE3', 'VALUE6_BNS_VALUE3', 'VALUE6_CAR_VALUE3', 'VALUE6_CEN_VALUE3', 'VALUE6_CNT_VALUE3', 'VALUE6_CPA_VALUE3', 'VALUE6_CTY_VALUE3', 'VALUE6_DAL_VALUE3', 'VALUE6_DEL_VALUE3', 'VALUE6_DINSTA_VALUE3', 'VALUE6_DINSTQ_VALUE3', 'VALUE6_DIR_VALUE3', 'VALUE6_DMOD_VALUE3', 'VALUE6_DMODID_VALUE3', 'VALUE6_INT_VALUE3', 'VALUE6_POB_VALUE3', 'VALUE6_PRE_VALUE3', 'VALUE6_SAL_VALUE3', 'VALUE6_STA_VALUE3', 'VALUE6_STB_VALUE3', 'VALUE6_STR_VALUE3', 'VALUE6_STTYP_VALUE3', 'VALUE6_UNID_VALUE3', 'VALUE6_UNIT_VALUE3', 'VALUE6_ZIP_VALUE3', 'VALUE6_ADL_CODE3', 'VALUE6_BNS_CODE3', 'VALUE6_BNS_CODESYSTEM3', 'VALUE6_DAL_CODE3', 'VALUE6_DAL_CODESYSTEM3', 'VALUE6_INT_CODE3', 'VALUE6_INT_CODESYSTEM3', 'VALUE6_STB_CODE3', 'VALUE6_STB_CODESYSTEM3', 'VALUE6_ZIP_CODESYSTEM3', 'VALUE6_ZIP_CODE3', NULL),
  (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE4', 'VALUE6_AL_VALUE4', 'VALUE6_BNN_VALUE4', 'VALUE6_BNR_VALUE4', 'VALUE6_BNS_VALUE4', 'VALUE6_CAR_VALUE4', 'VALUE6_CEN_VALUE4', 'VALUE6_CNT_VALUE4', 'VALUE6_CPA_VALUE4', 'VALUE6_CTY_VALUE4', 'VALUE6_DAL_VALUE4', 'VALUE6_DEL_VALUE4', 'VALUE6_DINSTA_VALUE4', 'VALUE6_DINSTQ_VALUE4', 'VALUE6_DIR_VALUE4', 'VALUE6_DMOD_VALUE4', 'VALUE6_DMODID_VALUE4', 'VALUE6_INT_VALUE4', 'VALUE6_POB_VALUE4', 'VALUE6_PRE_VALUE4', 'VALUE6_SAL_VALUE4', 'VALUE6_STA_VALUE4', 'VALUE6_STB_VALUE4', 'VALUE6_STR_VALUE4', 'VALUE6_STTYP_VALUE4', 'VALUE6_UNID_VALUE4', 'VALUE6_UNIT_VALUE4', 'VALUE6_ZIP_VALUE4', 'VALUE6_ADL_CODE4', 'VALUE6_BNS_CODE4', 'VALUE6_BNS_CODESYSTEM4', 'VALUE6_DAL_CODE4', 'VALUE6_DAL_CODESYSTEM4', 'VALUE6_INT_CODE4', 'VALUE6_INT_CODESYSTEM4', 'VALUE6_STB_CODE4', 'VALUE6_STB_CODESYSTEM4', 'VALUE6_ZIP_CODESYSTEM4', 'VALUE6_ZIP_CODE4', NULL),
  (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE5', 'VALUE6_AL_VALUE5', 'VALUE6_BNN_VALUE5', 'VALUE6_BNR_VALUE5', 'VALUE6_BNS_VALUE5', 'VALUE6_CAR_VALUE5', 'VALUE6_CEN_VALUE5', 'VALUE6_CNT_VALUE5', 'VALUE6_CPA_VALUE5', 'VALUE6_CTY_VALUE5', 'VALUE6_DAL_VALUE5', 'VALUE6_DEL_VALUE5', 'VALUE6_DINSTA_VALUE5', 'VALUE6_DINSTQ_VALUE5', 'VALUE6_DIR_VALUE5', 'VALUE6_DMOD_VALUE5', 'VALUE6_DMODID_VALUE5', 'VALUE6_INT_VALUE5', 'VALUE6_POB_VALUE5', 'VALUE6_PRE_VALUE5', 'VALUE6_SAL_VALUE5', 'VALUE6_STA_VALUE5', 'VALUE6_STB_VALUE5', 'VALUE6_STR_VALUE5', 'VALUE6_STTYP_VALUE5', 'VALUE6_UNID_VALUE5', 'VALUE6_UNIT_VALUE5', 'VALUE6_ZIP_VALUE5', 'VALUE6_ADL_CODE5', 'VALUE6_BNS_CODE5', 'VALUE6_BNS_CODESYSTEM5', 'VALUE6_DAL_CODE5', 'VALUE6_DAL_CODESYSTEM5', 'VALUE6_INT_CODE5', 'VALUE6_INT_CODESYSTEM5', 'VALUE6_STB_CODE5', 'VALUE6_STB_CODESYSTEM5', 'VALUE6_ZIP_CODESYSTEM5', 'VALUE6_ZIP_CODE5', NULL),
  (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
  (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2),
  (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
  (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4),
  (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
  (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (46, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (47, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (48, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO AD_DATATYPE_VALUE7(AD_DATATYPE_ID, AL_VALUE, AL_CODE, AL_CODESYSTEM, DAL_CODE, DAL_VALUE, DAL_CODESYSTEM, CTY_VALUE, CTY_CODE, CTY_CODESYSTEM)
VALUES (34, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (35, NULL, NULL, NULL, 'DAL_CODE1', 'DAL_VALUE1', 'DAL_CODESYSTEM1', 'CTY_VALUE1', 'CTY_CODE1', 'CTY_CODESYSTEM1'),
  (36, 'AL_VALUE2', 'AL_CODE2', 'AL_CODESYSTEM2', 'DAL_CODE2', 'DAL_VALUE2', 'DAL_CODESYSTEM2', NULL, NULL, NULL),
  (37, 'AL_VALUE3', 'AL_CODE3', 'AL_CODESYSTEM3', 'DAL_CODE3', 'DAL_VALUE3', 'DAL_CODESYSTEM3', 'CTY_VALUE3', 'CTY_CODE3', 'CTY_CODESYSTEM3'),
  (38, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');

INSERT INTO AD_DATATYPE_VALUE8(ID, AL_VALUE, AL_CODE, AL_CODESYSTEM, DAL_CODE, DAL_VALUE, DAL_CODESYSTEM, CTY_VALUE, CTY_CODE, CTY_CODESYSTEM)
VALUES (1, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (2, NULL, NULL, NULL, 'DAL_CODE1', 'DAL_VALUE1', 'DAL_CODESYSTEM1', 'CTY_VALUE1', 'CTY_CODE1', 'CTY_CODESYSTEM1'),
  (3, 'AL_VALUE2', 'AL_CODE2', 'AL_CODESYSTEM2', NULL, NULL, NULL, 'CTY_VALUE2', 'CTY_CODE2', 'CTY_CODESYSTEM2'),
  (4, 'AL_VALUE3', 'AL_CODE3', 'AL_CODESYSTEM3', 'DAL_CODE3', 'DAL_VALUE3', 'DAL_CODESYSTEM3', NULL, NULL, NULL),
  (5, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');

INSERT INTO AD_DATATYPE_VALUE9(ID, AL_VALUE, AL_CODE, AL_CODESYSTEM, DAL_CODE, DAL_VALUE, DAL_CODESYSTEM, CTY_VALUE, CTY_CODE, CTY_CODESYSTEM)
VALUES (1, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');

INSERT INTO BL_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, '1', NULL, NULL),
  (3, NULL, NULL, '1'),
  (4, NULL, 'INV', null);

INSERT INTO BL_NONNULL_DATATYPE(ID, VALUE1_VALUE)
VALUES (1, '0'),
  (2, '1');

INSERT INTO CD_CD_DATATYPE_VALUE8(CD_DATA_TYPE_ID, CD_DATATYPE_VALUE8_ID)
VALUES (40, 1),
  (41, 1),
  (42, 1),
  (43, 1),
  (44, 1);

INSERT INTO CD_DATATYPE(ID, VALUE1_CODE, VALUE2_NULL_FLAVOR, VALUE2_CODE, VALUE3_CODE, VALUE4_NULL_FLAVOR, VALUE4_CODE, VALUE4_CODE_SYSTEM, VALUE4_CODE_SYSTEM_VERSION, VALUE4_CODE_SYSTEM_NAME, VALUE4_DISPLAY_NULL_FLAVOR, VALUE4_DISPLAY_VALUE, VALUE4_ORIG_TXT_NULL_FLAVOR, VALUE4_ORIG_TXT_VALUE, VALUE4_ORIG_TXT_DESCRIPTION, VALUE5_NULL_FLAVOR, VALUE5_CODE, VALUE5_CODE_SYSTEM, VALUE5_CODE_SYSTEM_NAME, VALUE5_CODE_SYSTEM_VERSION, VALUE5_DISPLAY_NULL_FLAVOR, VALUE5_DISPLAY_VALUE, VALUE5_ORIG_TXT_NULL_FLAVOR, VALUE5_ORIG_TXT_VALUE, VALUE5_ORIG_TXT_DESCRIPTION, CD_DATATYPE_VALUE7_ID)
VALUES (1, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 'CODE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 'CODE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, NULL, NULL, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, NULL, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, 'CODE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, 'CODE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE1', 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE2', NULL, NULL, 'VALUE4_ORIG_TXT_DESCRIPTION1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, 'CODE4', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE2', NULL, 'VALUE4_ORIG_TXT_VALUE1', 'VALUE4_ORIG_TXT_DESCRIPTION2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE4_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE3', NULL, 'VALUE4_ORIG_TXT_VALUE2', 'VALUE4_ORIG_TXT_DESCRIPTION3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE4_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE3', 'VALUE4_ORIG_TXT_DESCRIPTION4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (22, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE4_CODE_SYSTEM', 'VALUE4_CODE_SYSTEM_VERSION1', NULL, NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE4', 'VALUE4_ORIG_TXT_DESCRIPTION5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE4_CODE_SYSTEM', 'VALUE4_CODE_SYSTEM_VERSION1', 'VALUE4_CODE_SYSTEM_NAME', NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE5', 'VALUE4_ORIG_TXT_DESCRIPTION6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE1', 'VALUE5_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE2', 'VALUE5_CODE_SYSTEM2', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE1', NULL, NULL, NULL, NULL),
  (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, 'VALUE5_CODE_SYSTEM_NAME1', NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, NULL, 'VALUE5_ORIG_TXT_DESCRIPTION1', NULL),
  (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE4', 'VALUE5_CODE_SYSTEM3', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, 'VALUE5_ORIG_TXT_VALUE1', 'VALUE5_ORIG_TXT_DESCRIPTION2', NULL),
  (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME2', NULL, NULL, 'VALUE5_DISPLAY_VALUE3', NULL, 'VALUE5_ORIG_TXT_VALUE2', 'VALUE5_ORIG_TXT_DESCRIPTION3', NULL),
  (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE5_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE3', 'VALUE5_ORIG_TXT_DESCRIPTION4', NULL),
  (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE5_CODE_SYSTEM', NULL, 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION5', NULL),
  (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME', 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION6', NULL),
  (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE1', 'VALUE5_CODE_SYSTEM1', NULL, NULL, 'NI', NULL, 'NI', NULL, NULL, 1),
  (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE2', 'VALUE5_CODE_SYSTEM2', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE1', 'NI', NULL, NULL, 2),
  (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, 'VALUE5_CODE_SYSTEM_NAME1', NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, NULL, 'VALUE5_ORIG_TXT_DESCRIPTION1', 3),
  (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE4', 'VALUE5_CODE_SYSTEM3', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, 'VALUE5_ORIG_TXT_VALUE1', 'VALUE5_ORIG_TXT_DESCRIPTION2', 4),
  (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME2', NULL, NULL, 'VALUE5_DISPLAY_VALUE3', NULL, 'VALUE5_ORIG_TXT_VALUE2', 'VALUE5_ORIG_TXT_DESCRIPTION3', 5),
  (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE5_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE3', 'VALUE5_ORIG_TXT_DESCRIPTION4', 6),
  (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE5_CODE_SYSTEM', NULL, 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION5', 7),
  (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME', 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION6', 8),
  (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (45, NULL, NULL, 'CODE61', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (46, NULL, NULL, 'CODE62', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (47, NULL, NULL, 'CODE63', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (48, NULL, NULL, 'CODE64', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (49, NULL, NULL, 'CODE65', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (50, NULL, NULL, 'CODE66', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO CD_DATATYPE_VALUE6(CD_DATATYPE_ID, NULL_FLAVOR, CODE, CODE_SYSTEM, CODE_SYSTEM_NAME, CODE_SYSTEM_VERSION, DISPLAY_NULL_FLAVOR, DISPLAY_VALUE, ORIG_TXT_NULL_FLAVOR, ORIG_TXT_VALUE, ORIG_TXT_DESCRIPTION)
VALUES (45, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE1', 'NI', NULL, NULL),
  (46, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE2', NULL, 'ORIG_TXT_VALUE1', NULL),
  (47, NULL, 'CODE3', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE3', NULL, 'ORIG_TXT_VALUE2', 'ORIG_TXT_DESCRIPTION1'),
  (48, NULL, 'CODE4', 'CODE_SYSTEM_61', NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE4', NULL, 'ORIG_TXT_VALUE3', 'ORIG_TXT_DESCRIPTION2'),
  (49, NULL, 'CODE5', 'CODE_SYSTEM_62', 'CODE_SYSTEM_NAME_61', NULL, NULL, 'VALUE6_DISPLAY_VALUE5', NULL, 'ORIG_TXT_VALUE4', 'ORIG_TXT_DESCRIPTION3'),
  (50, NULL, 'CODE1', 'CODE_SYSTEM_63', 'CODE_SYSTEM_NAME_62', 'CODE_SYSTEM_VERSION_61', NULL, 'VALUE6_DISPLAY_VALUE6', NULL, NULL, NULL);

INSERT INTO CD_DATATYPE_VALUE7(ID, NULL_FLAVOR, CODE, CODE_SYSTEM, CODE_SYSTEM_NAME, CODE_SYSTEM_VERSION, DISPLAY_NULL_FLAVOR, DISPLAY_VALUE, ORIG_TXT_NULL_FLAVOR, ORIG_TXT_VALUE, ORIG_TXT_DESCRIPTION)
VALUES (1, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE1', NULL, 'ORIG_TXT_VALUE1', NULL),
  (2, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE2', NULL, 'ORIG_TXT_VALUE2', 'ORIG_TXT_DESCRIPTION1'),
  (3, NULL, 'CODE3', 'CODE_SYSTEM_1', NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE3', NULL, 'ORIG_TXT_VALUE3', 'ORIG_TXT_DESCRIPTION2'),
  (4, NULL, 'CODE4', 'CODE_SYSTEM_2', 'CODE_SYSTEM_NAME_1', NULL, NULL, 'VALUE7_DISPLAY_VALUE4', NULL, 'ORIG_TXT_VALUE4', 'ORIG_TXT_DESCRIPTION3'),
  (5, NULL, 'CODE5', 'CODE_SYSTEM_3', 'CODE_SYSTEM_NAME_2', 'CODE_SYSTEM_VERSION_1', NULL, 'VALUE7_DISPLAY_VALUE5', NULL, 'ORIG_TXT_VALUE5', 'ORIG_TXT_DESCRIPTION4'),
  (6, NULL, 'CODE6', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE6', NULL, NULL, 'ORIG_TXT_DESCRIPTION5'),
  (7, NULL, 'CODE7', NULL, NULL, NULL, 'NI', NULL, NULL, 'ORIG_TXT_VALUE7', 'ORIG_TXT_DESCRIPTION6'),
  (8, NULL, 'CODE8', 'CODE_SYSTEM_4', NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE8', NULL, 'ORIG_TXT_VALUE8', 'ORIG_TXT_DESCRIPTION7');

INSERT INTO CD_DATATYPE_VALUE8(ID, NULL_FLAVOR, CODE, CODE_SYSTEM, CODE_SYSTEM_NAME, CODE_SYSTEM_VERSION, DISPLAY_NULL_FLAVOR, DISPLAY_VALUE, ORIG_TXT_NULL_FLAVOR, ORIG_TXT_VALUE, ORIG_TXT_DESCRIPTION)
VALUES (1, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE8_DISPLAY_VALUE1', 'NI', NULL, NULL);

INSERT INTO DSET_AD_DATATYPE(ID)
VALUES (1),
  (2),
  (3),
  (4),
  (5),
  (6),
  (7),
  (8),
  (9),
  (10),
  (11),
  (12),
  (13),
  (14),
  (15),
  (16),
  (17),
  (18),
  (19),
  (20),
  (21),
  (22),
  (23),
  (24),
  (25),
  (26),
  (27),
  (28),
  (29),
  (30),
  (31),
  (32),
  (33),
  (34),
  (35),
  (36),
  (37),
  (38),
  (39),
  (40),
  (41),
  (42),
  (43),
  (44),
  (45),
  (46),
  (47),
  (48),
  (49),
  (50),
  (51),
  (52),
  (53),
  (54),
  (55),
  (56),
  (57),
  (58),
  (59),
  (60),
  (61),
  (62);

INSERT INTO DSET_AD_DSET_AD_VALUE8(DSET_AD_DATATYPE_ID, DSET_AD_VALUE8_ID)
VALUES (59, 1),
  (60, 1),
  (61, 1),
  (62, 7),
  (62, 8),
  (62, 9),
  (62, 10);

INSERT INTO DSET_AD_VALUE1(DSET_AD_DATATYPE_ID, ADXP_AL_VALUE)
VALUES (1, NULL),
  (2, '1 Jefferson Street'),
  (3, '2 Jefferson Street'),
  (4, '3 Jefferson Street'),
  (5, '4 Sun Street'),
  (6, '5 Sun Street');

INSERT INTO DSET_AD_VALUE2(DSET_AD_DATATYPE_ID, ADXP_DAL_VALUE, ADXP_DAL_CODE)
VALUES (7, 'Suite 100', NULL),
  (8, 'Suite 101', NULL),
  (9, NULL, 'CODE1'),
  (10, 'Suite 103', 'CODE2'),
  (11, 'Suite 104', 'CODE3');

INSERT INTO DSET_AD_VALUE3(DSET_AD_DATATYPE_ID, ADXP_AL_VALUE, ADXP_AL_CODE, ADXP_AL_CODESYSTEM)
VALUES (12, '1 Jefferson Street', NULL, NULL),
  (13, '2 Jefferson Street', 'CODE1', NULL),
  (14, NULL, 'CODE2', NULL),
  (15, '3 Jefferson Street', 'CODE3', NULL),
  (16, '4 Jefferson Street', 'CODE4', 'CODE_SYSTEM');

INSERT INTO DSET_AD_VALUE4(DSET_AD_DATATYPE_ID, ADXP_AL1_VALUE, ADXP_AL2_VALUE, ADXP_AL1_CODE, ADXP_AL2_CODE, ADXP_AL2_CODESYSTEM, ADXP_AL1_CODESYSTEM)
VALUES (17, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL),
  (18, '101 Jefferson Street', NULL, 'NCI101', NULL, NULL, NULL),
  (19, '102 Jefferson Street', NULL, 'NCI102', NULL, NULL, 'ADXP_AL1_CODESYSTEM1'),
  (20, NULL, '200 Executive Blvd', NULL, NULL, NULL, NULL),
  (21, NULL, '201 Executive Blvd', NULL, 'NCI201', NULL, NULL),
  (22, NULL, '202 Executive Blvd', NULL, 'NCI202', 'ADXP_AL2_CODESYSTEM1', NULL),
  (23, '100 Jefferson Street', '200 Executive Blvd', NULL, NULL, NULL, NULL),
  (24, '101 Jefferson Street', '201 Executive Blvd', 'NCI101', 'NCI201', NULL, NULL),
  (25, '102 Jefferson Street', '202 Executive Blvd', 'NCI102', 'NCI202', 'ADXP_AL2_CODESYSTEM1', 'ADXP_AL1_CODESYSTEM1');

INSERT INTO DSET_AD_VALUE5(DSET_AD_DATATYPE_ID, ADXP_AL_VALUE, ADXP_AL_CODE, ADXP_AL_CODESYSTEM, ADXP_DAL_CODE, ADXP_DAL_CODESYSTEM, ADXP_DAL_VALUE, ADXP_CTY_VALUE, ADXP_CTY_CODE, ADXP_CTY_CODESYSTEM)
VALUES (26, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (27, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (28, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (29, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL100', NULL, NULL, NULL, NULL, NULL),
  (30, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL101', 'ADXP_DAL_CODESYSTEM1', NULL, NULL, NULL, NULL),
  (31, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM2', 'Suite 500', NULL, NULL, NULL),
  (32, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM3', 'Suite 501', 'Rockville', NULL, NULL),
  (33, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM4', 'Suite 502', 'Rockville', 'RCK', NULL),
  (34, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS'),
  (35, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');

INSERT INTO DSET_AD_VALUE6(DSET_AD_DATATYPE_ID, ADXP_ADL_VALUE, ADXP_AL_VALUE, ADXP_BNN_VALUE, ADXP_BNR_VALUE, ADXP_BNS_VALUE, ADXP_CAR_VALUE, ADXP_CEN_VALUE, ADXP_CNT_VALUE, ADXP_CPA_VALUE, ADXP_CTY_VALUE, ADXP_DAL_VALUE, ADXP_DEL_VALUE, ADXP_DINSTA_VALUE, ADXP_DINSTQ_VALUE, ADXP_DIR_VALUE, ADXP_DMOD_VALUE, ADXP_DMODID_VALUE, ADXP_INT_VALUE, ADXP_POB_VALUE, ADXP_PRE_VALUE, ADXP_SAL_VALUE, ADXP_STA_VALUE, ADXP_STB_VALUE, ADXP_STR_VALUE, ADXP_STTYP_VALUE, ADXP_UNID_VALUE, ADXP_UNIT_VALUE, ADXP_ZIP_VALUE, ADXP_ADL_CODE, ADXP_BNS_CODE, ADXP_BNS_CODESYSTEM, ADXP_DAL_CODE, ADXP_DAL_CODESYSTEM, ADXP_INT_CODE, ADXP_INT_CODESYSTEM, ADXP_STB_CODE, ADXP_STB_CODESYSTEM, ADXP_ZIP_CODESYSTEM, ADXP_ZIP_CODE)
VALUES (36, 'ADXP_ADL_VALUE1', 'ADXP_AL_VALUE1', 'ADXP_BNN_VALUE1', 'ADXP_BNR_VALUE1', 'ADXP_BNS_VALUE1', 'ADXP_CAR_VALUE1', 'ADXP_CEN_VALUE1', 'ADXP_CNT_VALUE1', 'ADXP_CPA_VALUE1', 'ADXP_CTY_VALUE1', 'ADXP_DAL_VALUE1', 'ADXP_DEL_VALUE1', 'ADXP_DINSTA_VALUE1', 'ADXP_DINSTQ_VALUE1', 'ADXP_DIR_VALUE1', 'ADXP_DMOD_VALUE1', 'ADXP_DMODID_VALUE1', 'ADXP_INT_VALUE1', 'ADXP_POB_VALUE1', 'ADXP_PRE_VALUE1', 'ADXP_SAL_VALUE1', 'ADXP_STA_VALUE1', 'ADXP_STB_VALUE1', 'ADXP_STR_VALUE1', 'ADXP_STTYP_VALUE1', 'ADXP_UNID_VALUE1', 'ADXP_UNIT_VALUE1', 'ADXP_ZIP_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (37, 'ADXP_ADL_VALUE2', 'ADXP_AL_VALUE2', 'ADXP_BNN_VALUE2', 'ADXP_BNR_VALUE2', 'ADXP_BNS_VALUE2', 'ADXP_CAR_VALUE2', 'ADXP_CEN_VALUE2', 'ADXP_CNT_VALUE2', 'ADXP_CPA_VALUE2', 'ADXP_CTY_VALUE2', 'ADXP_DAL_VALUE2', 'ADXP_DEL_VALUE2', 'ADXP_DINSTA_VALUE2', 'ADXP_DINSTQ_VALUE2', 'ADXP_DIR_VALUE2', 'ADXP_DMOD_VALUE2', 'ADXP_DMODID_VALUE2', 'ADXP_INT_VALUE2', 'ADXP_POB_VALUE2', 'ADXP_PRE_VALUE2', 'ADXP_SAL_VALUE2', 'ADXP_STA_VALUE2', 'ADXP_STB_VALUE2', 'ADXP_STR_VALUE2', 'ADXP_STTYP_VALUE2', 'ADXP_UNID_VALUE2', 'ADXP_UNIT_VALUE2', 'ADXP_ZIP_VALUE2', 'ADXP_ADL_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (38, 'ADXP_ADL_VALUE3', 'ADXP_AL_VALUE3', 'ADXP_BNN_VALUE3', 'ADXP_BNR_VALUE3', 'ADXP_BNS_VALUE3', 'ADXP_CAR_VALUE3', 'ADXP_CEN_VALUE3', 'ADXP_CNT_VALUE3', 'ADXP_CPA_VALUE3', 'ADXP_CTY_VALUE3', 'ADXP_DAL_VALUE3', 'ADXP_DEL_VALUE3', 'ADXP_DINSTA_VALUE3', 'ADXP_DINSTQ_VALUE3', 'ADXP_DIR_VALUE3', 'ADXP_DMOD_VALUE3', 'ADXP_DMODID_VALUE3', 'ADXP_INT_VALUE3', 'ADXP_POB_VALUE3', 'ADXP_PRE_VALUE3', 'ADXP_SAL_VALUE3', 'ADXP_STA_VALUE3', 'ADXP_STB_VALUE3', 'ADXP_STR_VALUE3', 'ADXP_STTYP_VALUE3', 'ADXP_UNID_VALUE3', 'ADXP_UNIT_VALUE3', 'ADXP_ZIP_VALUE3', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (39, 'ADXP_ADL_VALUE4', 'ADXP_AL_VALUE4', 'ADXP_BNN_VALUE4', 'ADXP_BNR_VALUE4', 'ADXP_BNS_VALUE4', 'ADXP_CAR_VALUE4', 'ADXP_CEN_VALUE4', 'ADXP_CNT_VALUE4', 'ADXP_CPA_VALUE4', 'ADXP_CTY_VALUE4', 'ADXP_DAL_VALUE4', 'ADXP_DEL_VALUE4', 'ADXP_DINSTA_VALUE4', 'ADXP_DINSTQ_VALUE4', 'ADXP_DIR_VALUE4', 'ADXP_DMOD_VALUE4', 'ADXP_DMODID_VALUE4', 'ADXP_INT_VALUE4', 'ADXP_POB_VALUE4', 'ADXP_PRE_VALUE4', 'ADXP_SAL_VALUE4', 'ADXP_STA_VALUE4', 'ADXP_STB_VALUE4', 'ADXP_STR_VALUE4', 'ADXP_STTYP_VALUE4', 'ADXP_UNID_VALUE4', 'ADXP_UNIT_VALUE4', 'ADXP_ZIP_VALUE4', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (40, 'ADXP_ADL_VALUE5', 'ADXP_AL_VALUE5', 'ADXP_BNN_VALUE5', 'ADXP_BNR_VALUE5', 'ADXP_BNS_VALUE5', 'ADXP_CAR_VALUE5', 'ADXP_CEN_VALUE5', 'ADXP_CNT_VALUE5', 'ADXP_CPA_VALUE5', 'ADXP_CTY_VALUE5', 'ADXP_DAL_VALUE5', 'ADXP_DEL_VALUE5', 'ADXP_DINSTA_VALUE5', 'ADXP_DINSTQ_VALUE5', 'ADXP_DIR_VALUE5', 'ADXP_DMOD_VALUE5', 'ADXP_DMODID_VALUE5', 'ADXP_INT_VALUE5', 'ADXP_POB_VALUE5', 'ADXP_PRE_VALUE5', 'ADXP_SAL_VALUE5', 'ADXP_STA_VALUE5', 'ADXP_STB_VALUE5', 'ADXP_STR_VALUE5', 'ADXP_STTYP_VALUE5', 'ADXP_UNID_VALUE5', 'ADXP_UNIT_VALUE5', 'ADXP_ZIP_VALUE5', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (41, 'ADXP_ADL_VALUE6', 'ADXP_AL_VALUE6', 'ADXP_BNN_VALUE6', 'ADXP_BNR_VALUE6', 'ADXP_BNS_VALUE6', 'ADXP_CAR_VALUE6', 'ADXP_CEN_VALUE6', 'ADXP_CNT_VALUE6', 'ADXP_CPA_VALUE6', 'ADXP_CTY_VALUE6', 'ADXP_DAL_VALUE6', 'ADXP_DEL_VALUE6', 'ADXP_DINSTA_VALUE6', 'ADXP_DINSTQ_VALUE6', 'ADXP_DIR_VALUE6', 'ADXP_DMOD_VALUE6', 'ADXP_DMODID_VALUE6', 'ADXP_INT_VALUE6', 'ADXP_POB_VALUE6', 'ADXP_PRE_VALUE6', 'ADXP_SAL_VALUE6', 'ADXP_STA_VALUE6', 'ADXP_STB_VALUE6', 'ADXP_STR_VALUE6', 'ADXP_STTYP_VALUE6', 'ADXP_UNID_VALUE6', 'ADXP_UNIT_VALUE6', 'ADXP_ZIP_VALUE6', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', NULL, NULL, NULL, NULL, NULL, NULL),
  (42, 'ADXP_ADL_VALUE7', 'ADXP_AL_VALUE7', 'ADXP_BNN_VALUE7', 'ADXP_BNR_VALUE7', 'ADXP_BNS_VALUE7', 'ADXP_CAR_VALUE7', 'ADXP_CEN_VALUE7', 'ADXP_CNT_VALUE7', 'ADXP_CPA_VALUE7', 'ADXP_CTY_VALUE7', 'ADXP_DAL_VALUE7', 'ADXP_DEL_VALUE7', 'ADXP_DINSTA_VALUE7', 'ADXP_DINSTQ_VALUE7', 'ADXP_DIR_VALUE7', 'ADXP_DMOD_VALUE7', 'ADXP_DMODID_VALUE7', 'ADXP_INT_VALUE7', 'ADXP_POB_VALUE7', 'ADXP_PRE_VALUE7', 'ADXP_SAL_VALUE7', 'ADXP_STA_VALUE7', 'ADXP_STB_VALUE7', 'ADXP_STR_VALUE7', 'ADXP_STTYP_VALUE7', 'ADXP_UNID_VALUE7', 'ADXP_UNIT_VALUE7', 'ADXP_ZIP_VALUE7', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', NULL, NULL, NULL, NULL, NULL),
  (43, 'ADXP_ADL_VALUE8', 'ADXP_AL_VALUE8', 'ADXP_BNN_VALUE8', 'ADXP_BNR_VALUE8', 'ADXP_BNS_VALUE8', 'ADXP_CAR_VALUE8', 'ADXP_CEN_VALUE8', 'ADXP_CNT_VALUE8', 'ADXP_CPA_VALUE8', 'ADXP_CTY_VALUE8', 'ADXP_DAL_VALUE8', 'ADXP_DEL_VALUE8', 'ADXP_DINSTA_VALUE8', 'ADXP_DINSTQ_VALUE8', 'ADXP_DIR_VALUE8', 'ADXP_DMOD_VALUE8', 'ADXP_DMODID_VALUE8', 'ADXP_INT_VALUE8', 'ADXP_POB_VALUE8', 'ADXP_PRE_VALUE8', 'ADXP_SAL_VALUE8', 'ADXP_STA_VALUE8', 'ADXP_STB_VALUE8', 'ADXP_STR_VALUE8', 'ADXP_STTYP_VALUE8', 'ADXP_UNID_VALUE8', 'ADXP_UNIT_VALUE8', 'ADXP_ZIP_VALUE8', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', NULL, NULL, NULL, NULL),
  (44, 'ADXP_ADL_VALUE9', 'ADXP_AL_VALUE9', 'ADXP_BNN_VALUE9', 'ADXP_BNR_VALUE9', 'ADXP_BNS_VALUE9', 'ADXP_CAR_VALUE9', 'ADXP_CEN_VALUE9', 'ADXP_CNT_VALUE9', 'ADXP_CPA_VALUE9', 'ADXP_CTY_VALUE9', 'ADXP_DAL_VALUE9', 'ADXP_DEL_VALUE9', 'ADXP_DINSTA_VALUE9', 'ADXP_DINSTQ_VALUE9', 'ADXP_DIR_VALUE9', 'ADXP_DMOD_VALUE9', 'ADXP_DMODID_VALUE9', 'ADXP_INT_VALUE9', 'ADXP_POB_VALUE9', 'ADXP_PRE_VALUE9', 'ADXP_SAL_VALUE9', 'ADXP_STA_VALUE9', 'ADXP_STB_VALUE9', 'ADXP_STR_VALUE9', 'ADXP_STTYP_VALUE9', 'ADXP_UNID_VALUE9', 'ADXP_UNIT_VALUE9', 'ADXP_ZIP_VALUE9', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', NULL, NULL, NULL),
  (45, 'ADXP_ADL_VALUE10', 'ADXP_AL_VALUE10', 'ADXP_BNN_VALUE10', 'ADXP_BNR_VALUE10', 'ADXP_BNS_VALUE10', 'ADXP_CAR_VALUE10', 'ADXP_CEN_VALUE10', 'ADXP_CNT_VALUE10', 'ADXP_CPA_VALUE10', 'ADXP_CTY_VALUE10', 'ADXP_DAL_VALUE10', 'ADXP_DEL_VALUE10', 'ADXP_DINSTA_VALUE10', 'ADXP_DINSTQ_VALUE10', 'ADXP_DIR_VALUE10', 'ADXP_DMOD_VALUE10', 'ADXP_DMODID_VALUE10', 'ADXP_INT_VALUE10', 'ADXP_POB_VALUE10', 'ADXP_PRE_VALUE10', 'ADXP_SAL_VALUE10', 'ADXP_STA_VALUE10', 'ADXP_STB_VALUE10', 'ADXP_STR_VALUE10', 'ADXP_STTYP_VALUE10', 'ADXP_UNID_VALUE10', 'ADXP_UNIT_VALUE10', 'ADXP_ZIP_VALUE10', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', NULL, NULL),
  (46, 'ADXP_ADL_VALUE11', 'ADXP_AL_VALUE11', 'ADXP_BNN_VALUE11', 'ADXP_BNR_VALUE11', 'ADXP_BNS_VALUE11', 'ADXP_CAR_VALUE11', 'ADXP_CEN_VALUE11', 'ADXP_CNT_VALUE11', 'ADXP_CPA_VALUE11', 'ADXP_CTY_VALUE11', 'ADXP_DAL_VALUE11', 'ADXP_DEL_VALUE11', 'ADXP_DINSTA_VALUE11', 'ADXP_DINSTQ_VALUE11', 'ADXP_DIR_VALUE11', 'ADXP_DMOD_VALUE11', 'ADXP_DMODID_VALUE11', 'ADXP_INT_VALUE11', 'ADXP_POB_VALUE11', 'ADXP_PRE_VALUE11', 'ADXP_SAL_VALUE11', 'ADXP_STA_VALUE11', 'ADXP_STB_VALUE11', 'ADXP_STR_VALUE11', 'ADXP_STTYP_VALUE11', 'ADXP_UNID_VALUE11', 'ADXP_UNIT_VALUE11', 'ADXP_ZIP_VALUE11', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', 'ADXP_ZIP_CODESYSTEM', NULL),
  (47, 'ADXP_ADL_VALUE12', 'ADXP_AL_VALUE12', 'ADXP_BNN_VALUE12', 'ADXP_BNR_VALUE12', 'ADXP_BNS_VALUE12', 'ADXP_CAR_VALUE12', 'ADXP_CEN_VALUE12', 'ADXP_CNT_VALUE12', 'ADXP_CPA_VALUE12', 'ADXP_CTY_VALUE12', 'ADXP_DAL_VALUE12', 'ADXP_DEL_VALUE12', 'ADXP_DINSTA_VALUE12', 'ADXP_DINSTQ_VALUE12', 'ADXP_DIR_VALUE12', 'ADXP_DMOD_VALUE12', 'ADXP_DMODID_VALUE12', 'ADXP_INT_VALUE12', 'ADXP_POB_VALUE12', 'ADXP_PRE_VALUE12', 'ADXP_SAL_VALUE12', 'ADXP_STA_VALUE12', 'ADXP_STB_VALUE12', 'ADXP_STR_VALUE12', 'ADXP_STTYP_VALUE12', 'ADXP_UNID_VALUE12', 'ADXP_UNIT_VALUE12', 'ADXP_ZIP_VALUE12', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', 'ADXP_ZIP_CODESYSTEM', 'ADXP_ZIP_CODE');

INSERT INTO DSET_AD_VALUE7(ID, DSET_AD_DATATYPE_ID, ADXP_AL_VALUE, ADXP_AL_CODE, ADXP_AL_CODESYSTEM, ADXP_DAL_CODE, ADXP_DAL_CODESYSTEM, ADXP_DAL_VALUE, ADXP_CTY_VALUE, ADXP_CTY_CODE, ADXP_CTY_CODESYSTEM)
VALUES (1, 49, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 50, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 51, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 52, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL101', NULL, NULL, NULL, NULL, NULL),
  (5, 53, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM5', NULL, NULL, NULL, NULL),
  (6, 54, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM5', 'Suite 501', NULL, NULL, NULL),
  (7, 55, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM5', 'Suite 502', 'Rockville', NULL, NULL),
  (8, 56, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', NULL),
  (9, 57, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL106', 'ADXP_DAL_CODESYSTEM5', 'Suite 504', 'Rockville', 'RCK', 'RCK_CODE_SYS'),
  (10, 58, '109 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM8', 'NCIDAL107', 'ADXP_DAL_CODESYSTEM5', 'Suite 505', 'Rockville', 'RCK', 'RCK_CODE_SYS');

INSERT INTO DSET_AD_VALUE8(ID, ADXP_AL_VALUE, ADXP_AL_CODE, ADXP_AL_CODESYSTEM, ADXP_DAL_CODE, ADXP_DAL_CODESYSTEM, ADXP_DAL_VALUE, ADXP_CTY_VALUE, ADXP_CTY_CODE, ADXP_CTY_CODESYSTEM)
VALUES (1, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (4, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL100', NULL, NULL, NULL, NULL, NULL),
  (5, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL101', 'ADXP_DAL_CODESYSTEM1', NULL, NULL, NULL, NULL),
  (6, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM2', 'Suite 500', NULL, NULL, NULL),
  (7, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM3', 'Suite 501', 'Rockville', NULL, NULL),
  (8, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM4', 'Suite 502', 'Rockville', 'RCK', NULL),
  (9, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS'),
  (10, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');

INSERT INTO DSET_CD(ID)
VALUES (1),
  (2),
  (3),
  (4),
  (5),
  (6),
  (7),
  (8),
  (9),
  (10),
  (11),
  (12),
  (13),
  (14),
  (15),
  (16),
  (17),
  (18),
  (19),
  (20),
  (21),
  (22),
  (23),
  (24),
  (25),
  (26),
  (27),
  (28),
  (29),
  (30),
  (31),
  (32),
  (33),
  (34),
  (35);

INSERT INTO DSET_CD_CD_VALUE7(DSET_CD_ID, DSET_CD_VALUE7_ID)
VALUES (31, 1),
  (32, 2),
  (33, 3),
  (34, 4),
  (35, 5);

INSERT INTO DSET_CD_VALUE1(DSET_CD_ID, CODE)
VALUES (1, 'CODE1'),
  (2, 'CODE2'),
  (3, 'CODE3'),
  (4, 'CODE4'),
  (5, 'CODE5');

INSERT INTO DSET_CD_VALUE2(DSET_CD_ID, CODE, NULL_FLAVOR)
VALUES (6, 'CODE1', NULL),
  (7, 'CODE2', NULL),
  (8, 'CODE3', NULL),
  (9, NULL, 'NI'),
  (10, NULL, 'NI');

INSERT INTO DSET_CD_VALUE3(DSET_CD_ID, CODE, CODE_SYSTEM, CODE_SYSTEM_NAME)
VALUES (11, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1'),
  (12, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2'),
  (13, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3'),
  (14, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4'),
  (15, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5');

INSERT INTO DSET_CD_VALUE4(DSET_CD_ID, CODE, CODE_SYSTEM, CODE_SYSTEM_NAME, CODE_SYSTEM_VERSION, DISPLAYABLE_VALUE, ORIGINALTEXT_VALUE, ORIGINALTEXT_DESC)
VALUES (16, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1', 'CODE_SYSTEM_VERSION1', 'CODE1', 'OrgTextVal1', 'OrgTextDesc1'),
  (17, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2', 'CODE_SYSTEM_VERSION2', 'CODE2', 'OrgTextVal2', 'OrgTextDesc2'),
  (18, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3', 'CODE_SYSTEM_VERSION3', 'CODE3', 'OrgTextVal3', 'OrgTextDesc3'),
  (19, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4', 'CODE_SYSTEM_VERSION4', 'CODE4', 'OrgTextVal4', 'OrgTextDesc4'),
  (20, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5', 'CODE_SYSTEM_VERSION5', 'CODE5', 'OrgTextVal5', 'OrgTextDesc5');

INSERT INTO DSET_CD_VALUE5(DSET_CD_ID, CODE, CODE_SYSTEM, CODE_SYSTEM_NAME, CODE_SYSTEM_VERSION, DISPLAYABLE_VALUE, ORIGINALTEXT_VALUE, ORIGINALTEXT_DESC)
VALUES (21, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1', 'CODE_SYSTEM_VERSION1', 'CODE1', 'OrgTextVal1', 'OrgTextDesc1'),
  (22, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2', 'CODE_SYSTEM_VERSION2', 'CODE2', 'OrgTextVal2', 'OrgTextDesc2'),
  (23, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3', 'CODE_SYSTEM_VERSION3', 'CODE3', 'OrgTextVal3', 'OrgTextDesc3'),
  (24, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4', 'CODE_SYSTEM_VERSION4', 'CODE4', 'OrgTextVal4', 'OrgTextDesc4'),
  (25, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5', 'CODE_SYSTEM_VERSION5', 'CODE5', 'OrgTextVal5', 'OrgTextDesc5');

INSERT INTO DSET_CD_VALUE6(ID, DSET_CD_ID, CODE)
VALUES (1, 26, 'CODE1'),
  (2, 27, 'CODE2'),
  (3, 28, 'CODE3'),
  (4, 29, 'CODE4'),
  (5, 30, 'CODE5');

INSERT INTO DSET_CD_VALUE7(ID, CODE)
VALUES (1, 'CODE1'),
  (2, 'CODE2'),
  (3, 'CODE3'),
  (4, 'CODE4'),
  (5, 'CODE5');

INSERT INTO DSET_II(ID)
VALUES (1),
  (2),
  (3),
  (4),
  (5),
  (6),
  (7),
  (8),
  (9),
  (10),
  (11),
  (12),
  (13),
  (14),
  (15),
  (16),
  (17),
  (18),
  (19),
  (20),
  (21),
  (22),
  (23),
  (24),
  (25),
  (26),
  (27),
  (28),
  (29);

INSERT INTO DSET_II_II_VALUE6(DSET_II_ID, DSET_II_VALUE6_ID)
VALUES (26, 1),
  (27, 1),
  (28, 1),
  (29, 2),
  (29, 3);

INSERT INTO DSET_II_VALUE1(DSET_II_ID, EXTENSION)
VALUES (1, 'Extension1'),
  (2, 'Extension2'),
  (3, 'Extension3'),
  (4, 'Extension4'),
  (5, 'Extension5');

INSERT INTO DSET_II_VALUE2(DSET_II_ID, EXTENSION, ROOT, NULL_FLAVOR)
VALUES (6, NULL, NULL, 'NI'),
  (7, 'Extension2', 'ROOT2', NULL),
  (8, 'Extension3', 'ROOT3', NULL),
  (9, 'Extension4', 'ROOT4', NULL),
  (10, 'Extension5', 'ROOT5', NULL);

INSERT INTO DSET_II_VALUE3(DSET_II_ID, EXTENSION, IDENTIFIER_NAME, DISPLAYABLE_VALUE, RELIABILITY, SCOPE)
VALUES (11, 'Extension1', NULL, NULL, NULL, NULL),
  (12, NULL, 'IDENTIFIER_NAME2', NULL, NULL, NULL),
  (13, 'Extension3', 'IDENTIFIER_NAME3', NULL, NULL, NULL),
  (14, 'Extension4', 'IDENTIFIER_NAME4', NULL, NULL, NULL),
  (15, 'Extension5', 'IDENTIFIER_NAME5', NULL, NULL, NULL);

INSERT INTO DSET_II_VALUE4(DSET_II_ID, ROOT, EXTENSION, IDENTIFIER_NAME, RELIABILITY, SCOPE, DISPLAYABLE_VALUE, NULL_FLAVOR)
VALUES (16, 'Root1', 'Extension1', NULL, NULL, NULL, '1', NULL),
  (17, 'Root2', 'Extension2', 'IDENTIFIER_NAME2', NULL, NULL, '1', NULL),
  (18, 'Root3', NULL, 'IDENTIFIER_NAME3', 'ISS', NULL, '1', NULL),
  (19, 'Root4', 'Extension4', 'IDENTIFIER_NAME4', 'ISS', 'BUSN', '0', NULL),
  (20, 'Root5', 'Extension5', 'IDENTIFIER_NAME5', 'ISS', 'BUSN', '1', NULL);

INSERT INTO DSET_II_VALUE5(ID, DSET_II_ID, EXTENSION)
VALUES (1, 21, 'Extension1'),
  (2, 22, 'Extension2'),
  (3, 23, 'Extension3'),
  (4, 24, 'Extension4'),
  (5, 25, 'Extension5');

INSERT INTO DSET_II_VALUE6(ID, EXTENSION)
VALUES (1, 'Extension1'),
  (2, 'Extension4'),
  (3, 'Extension5');

INSERT INTO DSET_TEL(ID, NULL_FLAVOR)
VALUES (0, NULL),
  (1, NULL),
  (2, NULL),
  (3, NULL),
  (4, NULL),
  (5, NULL),
  (6, NULL),
  (7, NULL),
  (8, NULL),
  (9, NULL),
  (10, NULL),
  (11, NULL),
  (12, NULL),
  (13, NULL),
  (14, NULL);

INSERT INTO DSET_TEL_EMAIL(ID, NULL_FLAVOR)
VALUES (0, NULL),
  (1, NULL),
  (2, NULL),
  (3, NULL),
  (4, NULL),
  (5, NULL),
  (6, NULL),
  (7, NULL),
  (8, NULL),
  (9, NULL);

INSERT INTO DSET_TEL_EMAIL_VALUE1(DSET_TEL_EMAIL_ID, TEL_EMAIL_VALUE)
VALUES (0, 'TEL_EMAIL_VALUE1'),
  (1, 'TEL_EMAIL_VALUE2'),
  (2, 'TEL_EMAIL_VALUE3'),
  (3, 'TEL_EMAIL_VALUE4'),
  (4, 'TEL_EMAIL_VALUE5'),
  (5, 'TEL_EMAIL_VALUE1'),
  (6, 'TEL_EMAIL_VALUE2'),
  (7, 'TEL_EMAIL_VALUE3'),
  (8, 'TEL_EMAIL_VALUE4'),
  (9, 'TEL_EMAIL_VALUE5');

INSERT INTO DSET_TEL_PERSON(ID, NULL_FLAVOR)
VALUES (0, NULL),
  (1, NULL),
  (2, NULL),
  (3, NULL),
  (4, NULL);

INSERT INTO DSET_TEL_PERSON_VALUE1(DSET_TEL_PERSON_ID, TEL_PERSON_VALUE)
VALUES (0, 'TEL_PERSON_VALUE1'),
  (1, 'TEL_PERSON_VALUE2'),
  (2, 'TEL_PERSON_VALUE3'),
  (3, 'TEL_PERSON_VALUE4'),
  (4, 'TEL_PERSON_VALUE5');

INSERT INTO DSET_TEL_PHONE(ID, NULL_FLAVOR)
VALUES (0, NULL),
  (1, NULL),
  (2, NULL),
  (3, NULL),
  (4, NULL);

INSERT INTO DSET_TEL_PHONE_VALUE1(DSET_TEL_PHONE_ID, TEL_PHONE_VALUE)
VALUES (0, 'TEL_PHONE_VALUE1'),
  (1, 'TEL_PHONE_VALUE2'),
  (2, 'TEL_PHONE_VALUE3'),
  (3, 'TEL_PHONE_VALUE4'),
  (4, 'TEL_PHONE_VALUE5');

INSERT INTO DSET_TEL_TEL_VALUE_3(DSET_TEL_ID, DSET_TEL_VALUE3_ID)
VALUES (10, 1),
  (11, 1),
  (12, 1),
  (13, 1),
  (14, 1);

INSERT INTO DSET_TEL_URL(ID, NULL_FLAVOR)
VALUES (0, NULL),
  (1, NULL),
  (2, NULL),
  (3, NULL),
  (4, NULL);

INSERT INTO DSET_TEL_URL_VALUE1(DSET_TEL_URL_ID, TEL_URL_VALUE)
VALUES (0, 'TEL_URL_VALUE1'),
  (1, 'TEL_URL_VALUE2'),
  (2, 'TEL_URL_VALUE3'),
  (3, 'TEL_URL_VALUE4'),
  (4, 'TEL_URL_VALUE5');

INSERT INTO DSET_TEL_VALUE1(DSET_TEL_ID, TEL_VALUE)
VALUES (0, 'tel://123-456-7891'),
  (1, 'tel://123-456-7892'),
  (2, 'tel://123-456-7893'),
  (3, 'tel://123-456-7894'),
  (4, 'tel://123-456-7895');

INSERT INTO DSET_TEL_VALUE2(ID, DSET_TEL_ID, TEL_VALUE)
VALUES (1, 5, 'tel://123-456-7891'),
  (2, 6, 'tel://123-456-7892'),
  (3, 7, 'tel://123-456-7893'),
  (4, 8, 'tel://123-456-7894'),
  (5, 9, 'tel://123-456-7895');

INSERT INTO DSET_TEL_VALUE3(ID, TEL_VALUE)
VALUES (1, 'tel://123-456-7892');

INSERT INTO ED_DATATYPE(ID, VALUE1_DATA, VALUE2_NULL_FLAVOR, VALUE2_DATA, VALUE2_COMPRESSION, VALUE3_NULL_FLAVOR, VALUE3_DATA, VALUE3_COMPRESSION, VALUE3_DESCRIPTION, VALUE3_VALUE)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, '110101010010', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, '100101010011', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, '100001010010', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, '110001010011', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, '110001011110', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, '110101010011', NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, '010101010011', NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, '000101010011', 'GZ', NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, '110001010010', 'GZ', NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, NULL, NULL, NULL, 'GZ', NULL, NULL),
  (13, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, NULL, '110001010111', 'GZ', NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, '110001010011', 'GZ', 'DESCRIPTION', NULL),
  (16, NULL, NULL, NULL, NULL, NULL, '110001010010', 'GZ', 'DESCRIPTION', 'VALUE3_VALUE_A');

INSERT INTO ED_TEXT_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE, VALUE3_NULL_FLAVOR, VALUE3_DATA, VALUE3_COMPRESSION, VALUE3_DESCRIPTION, VALUE3_VALUE)
VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, 'ED_TEXT_VALUE1_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, 'ED_TEXT_VALUE1_VALUE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, 'ED_TEXT_VALUE1_VALUE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, 'ED_TEXT_VALUE1_VALUE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (22, 'ED_TEXT_VALUE1_VALUE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, 'ED_TEXT_VALUE2_VALUE1', NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, 'ED_TEXT_VALUE2_VALUE2', NULL, NULL, NULL, NULL, NULL),
  (26, NULL, NULL, 'ED_TEXT_VALUE2_VALUE3', NULL, NULL, NULL, NULL, NULL),
  (27, NULL, NULL, 'ED_TEXT_VALUE2_VALUE4', NULL, NULL, NULL, NULL, NULL),
  (28, NULL, NULL, NULL, NULL, NULL, 'GZ', NULL, NULL),
  (29, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL),
  (30, NULL, NULL, NULL, NULL, '110001011010', 'GZ', NULL, NULL),
  (31, NULL, NULL, NULL, NULL, '110111010011', 'GZ', 'DESCRIPTION', NULL),
  (32, NULL, NULL, NULL, NULL, '110001010010', 'GZ', 'DESCRIPTION', 'VALUE3_VALUE_A');

INSERT INTO EN_DATATYPE(ID, VALUE1_PN_VALUE, VALUE2_PN_VALUE, VALUE2_PN_CODE, VALUE3_PN_VALUE, VALUE3_PN_CODE, VALUE3_PN_CODE_SYSTEM, VALUE3_PN_CODE_SYSTEM_VERSION, VALUE4_PN_VALUE, VALUE4_PN_ENPQ, VALUE5_PN_VALUE, VALUE5_PN2_VALUE, VALUE5_PN_CODE, VALUE5_PN2_CODE, VALUE5_PN_CODE_SYSTEM, VALUE5_PN2_CODE_SYSTEM, VALUE5_PN_CODE_SYSTEM_VERSION, VALUE5_PN2_CODE_SYSTEM_VERSION, VALUE6_PN_VALUE, VALUE6_ON_VALUE, VALUE6_PN_CODE, VALUE6_ON_CODE, VALUE6_PN_CODE_SYSTEM, VALUE6_ON_CODE_SYSTEM, VALUE6_PN_CODE_SYSTEM_VERSION, VALUE6_ON_CODE_SYSTEM_VERSION)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'Mr. John Doe Jr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 'Mr. John Doe Double Jr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 'Mr. John Doe Sr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 'Mr. John Doe Super Sr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 'Mr. John Doe II', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, 'Mr. John Doe Jr1.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, 'Mr. John Doe Jr2.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, 'Mr. John Doe Jr3.', 'JDJ3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, 'Mr. John Doe Jr4.', 'JDJ4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, 'Mr. John Doe Jr5.', 'JDJ5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, 'Mrs. Sarah Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, 'Mrs. Sarah Doe II', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, 'Mrs. Sarah Doe III', 'MSD1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, 'Mrs. Sarah Doe IV', 'MSD2', 'VALUE3_PN_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, 'Mrs. Sarah Doe V', 'MSD3', 'VALUE3_PN_CODE_SYSTEM2', '1.3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE3', 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE4', 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE1', 'SP,BR,NB', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', NULL, 'JDJ1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', NULL, 'JDJ1', NULL, 'VALUE5_PN_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', NULL, 'JDJ1', NULL, 'VALUE5_PN_CODE_SYSTEM1', NULL, '2.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', NULL, 'VALUE5_PN2_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VII', NULL, 'VALUE5_PN2_CODE2', NULL, 'VALUE5_PN2_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VIII', NULL, 'VALUE5_PN2_CODE3', NULL, 'VALUE5_PN2_CODE_SYSTEM2', NULL, '2.2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', 'Mr. John Doe VI', 'VALUE5_PN_CODE1', 'VALUE5_PN2_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', 'Mr. John Doe VII', 'VALUE5_PN_CODE2', 'VALUE5_PN2_CODE2', 'VALUE5_PN_CODE_SYSTEM1', 'VALUE5_PN2_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', 'Mr. John Doe VIII', 'VALUE5_PN_CODE3', 'VALUE5_PN2_CODE3', 'VALUE5_PN_CODE_SYSTEM2', 'VALUE5_PN2_CODE_SYSTEM2', '2.1', '2.2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', NULL, 'VALUE6_PN_CODE1', NULL, NULL, NULL, NULL, NULL),
  (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', NULL, 'VALUE6_PN_CODE2', NULL, 'VALUE6_PN_CODE_SYSTEM1', NULL, NULL, NULL),
  (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', NULL, 'VALUE6_PN_CODE3', NULL, 'VALUE6_PN_CODE_SYSTEM2', NULL, '3.1', NULL),
  (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL),
  (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', NULL, 'VALUE6_ON_CODE1', NULL, NULL, NULL, NULL),
  (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VII', NULL, 'VALUE6_ON_CODE1', NULL, 'VALUE6_ON_CODE_SYSTEM1', NULL, NULL),
  (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VIII', NULL, 'VALUE6_ON_CODE2', NULL, 'VALUE6_ON_CODE_SYSTEM2', NULL, '1.1'),
  (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL),
  (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', 'Mr. John Doe VI', 'VALUE6_PN_CODE1', 'VALUE6_ON_CODE1', NULL, NULL, NULL, NULL),
  (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', 'Mr. John Doe VII', 'VALUE6_PN_CODE2', 'VALUE6_ON_CODE2', 'VALUE6_PN_CODE_SYSTEM2', 'VALUE6_ON_CODE_SYSTEM2', NULL, NULL),
  (45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', 'Mr. John Doe VIII', 'VALUE6_PN_CODE3', 'VALUE6_ON_CODE3', 'VALUE6_PN_CODE_SYSTEM3', 'VALUE6_ON_CODE_SYSTEM3', '2.1', '1.1');

INSERT INTO EN_ON_DATATYPE(ID, VALUE1_ON_VALUE)
VALUES (1, NULL),
  (2, 'NCI1'),
  (3, 'NCI2'),
  (4, 'NCI3'),
  (5, 'NCI4'),
  (6, 'NCI5');

INSERT INTO EN_PN_DATATYPE(ID, VALUE1_PN_VALUE)
VALUES (1, NULL),
  (2, 'Mr. John Doe'),
  (3, 'Mr. John Doe II'),
  (4, 'Mr. John Doe III'),
  (5, 'Mr. John Doe IV'),
  (6, 'Mr. John Doe V');

INSERT INTO II_DATATYPE(ID, VALUE1_EXTENSION, VALUE2_NULL_FLAVOR, VALUE2_ROOT, VALUE2_EXTENSION, VALUE3_NULL_FLAVOR, VALUE3_EXTENSION, VALUE3_IDENTIFIER_NAME, VALUE3_RELIABILITY, VALUE3_SCOPE, VALUE3_DISPLAYABLE, VALUE4_NULL_FLAVOR, VALUE4_ROOT, VALUE4_EXTENSION, VALUE4_IDENTIFIER_NAME, VALUE4_RELIABILITY, VALUE4_SCOPE, VALUE4_DISPLAYABLE)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'II_Extension', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, NULL, NULL, 'II_VALUE2_ROOT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, NULL, NULL, 'II_VALUE2_ROOT', 'II_VALUE2_EXTENSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, NULL, NULL, 'UNK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', 'ISS', 'BUSN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', 'VRF', 'BUSN', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'INV', NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'UNV', NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'ISS', 'BUSN', NULL),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'VRF', 'BUSN', '0');

INSERT INTO INT_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, 1, NULL, NULL),
  (3, 2, NULL, NULL),
  (4, 3, NULL, NULL),
  (5, 4, NULL, NULL),
  (6, 5, NULL, NULL),
  (7, NULL, NULL, 6),
  (8, NULL, NULL, 7),
  (9, NULL, NULL, 8),
  (10, NULL, 'NI', NULL),
  (11, NULL, 'NI', NULL),
  (12, NULL, 'NI', NULL);

INSERT INTO IVL_INT(ID, VALUE1_LOW_VALUE, VALUE1_HIGH_VALUE, VALUE2_HIGH_VALUE, VALUE2_LOWCLOSED, VALUE3_ANY_VALUE, VALUE3_LOW_VALUE, VALUE3_HIGH_CLOSED, VALUE4_LOW_VALUE, VALUE4_HIGH_VALUE, VALUE4_WIDTH_VALUE, VALUE4_NULL_FLAVOR)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, NULL, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, 10, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, NULL, null, 11, '1', NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, null, 12, '1', NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, null, 13, '1', NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, 8, null, '1', NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, NULL, 9, null, '1', NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, 11, 5, 'NI'),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, 12, 4, 'NI'),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7, 13, 3, 'NI'),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8, 14, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9, NULL, NULL, 'NI');

INSERT INTO IVL_PQ(ID, VALUE1_LOW_VALUE, VALUE2_LOW_VALUE, VALUE2_LOW_PRECISION, VALUE2_LOW_UNIT, VALUE3_LOW_VALUE, VALUE3_LOW_PRECISION, VALUE3_LOW_UNIT, VALUE3_NULL_FLAVOR, VALUE4_HIGH_VALUE, VALUE4_HIGH_PRECISION, VALUE4_HIGH_UNIT, VALUE4_HIGH_CLOSED, VALUE4_HIGH_NULL_FLAVOR, VALUE4_LOW_VALUE, VALUE4_LOW_PRECISION, VALUE4_LOW_UNIT, VALUE4_LOW_NULL_FLAVOR, VALUE4_LOW_CLOSED, VALUE4_WIDTH_VALUE, VALUE4_WIDTH_PRECISION, VALUE4_WIDTH_UNIT, VALUE4_WIDTH_NULL_FLAVOR, VALUE4_NULL_FLAVOR)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 1.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 2.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 3.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 4.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 5.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, 221.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, 222.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, 221.1, 2, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, 222.1, NULL, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, 223.1, 2, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, 224.1, 2, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, 224.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, 224.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, 2, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, NULL, 224.1, 2, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5.1, 2, 'VALUE4_HIGH_UNIT1', '1', NULL, 1.1, 2, 'VALUE4_LOW_UNIT', NULL, '1', 5.1, 2, 'VALUE4_WIDTH_UNIT1', NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.1, 2, 'VALUE4_HIGH_UNIT2', '1', NULL, 1.1, 2, 'VALUE4_LOW_UNIT', NULL, '1', 5.1, 2, 'VALUE4_WIDTH_UNIT2', NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, 'NI', 'NA'),
  (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8.1, 2, 'VALUE4_HIGH_UNIT4', '1', NULL, NULL, NULL, NULL, 'NI', '1', 5, 2, 'VALUE4_WIDTH_UNIT4', NULL, NULL),
  (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9.1, 2, 'VALUE4_HIGH_UNIT5', '1', NULL, 1.1, 2, NULL, NULL, '1', NULL, NULL, NULL, 'NI', NULL);

INSERT INTO IVL_PQV(ID, VALUE1_LOW_VALUE, VALUE1_HIGH_VALUE, VALUE2_LOW_VALUE, VALUE2_LOW_PRECISION, VALUE2_HIGH_CLOSED, VALUE3_LOW_VALUE, VALUE3_LOW_PRECISION, VALUE3_HIGH_VALUE, VALUE3_HIGH_PRECISION, VALUE3_HIGH_NULL_FLAVOR, VALUE4_HIGH_VALUE, VALUE4_HIGH_PRECISION, VALUE4_HIGH_NULL_FLAVOR, VALUE4_LOW_VALUE, VALUE4_LOW_PRECISION, VALUE4_LOW_NULL_FLAVOR, VALUE4_WIDTH_VALUE, VALUE4_WIDTH_PRECISION, VALUE4_WIDTH_NULL_FLAVOR)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 1.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 2.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 3.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 4.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 5.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, 1.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, 2.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, 3.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, 4.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, 5.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, 1.1, 2, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, 2.1, 2, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, 3.1, 2, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, 4.1, 2, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, 5.1, 2, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, 1.1, 2, 610.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, 2.1, 2, 620.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, 3.1, 2, 630.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (22, NULL, NULL, NULL, NULL, NULL, 4.1, 2, 640.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, NULL, NULL, NULL, NULL, 5.1, 2, 650.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, NULL, NULL, NULL, 1.1, 2, 610.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, NULL, NULL, NULL, 2.1, 2, 620.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (26, NULL, NULL, NULL, NULL, NULL, 3.1, 2, 630.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (27, NULL, NULL, NULL, NULL, NULL, 4.1, 2, 640.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (28, NULL, NULL, NULL, NULL, NULL, 5.1, 2, 650.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 710.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 720.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 730.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 740.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 750.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11.1, 2, 'NI', 100.1, 2, 'NI', NULL, NULL, NULL),
  (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12.1, 2, 'NI', 100.1, 2, 'NI', NULL, NULL, NULL),
  (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13.1, 2, 'NI', 100.1, 2, 'NI', NULL, NULL, NULL),
  (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14.1, 2, 'NI', 100.1, 2, 'NI', NULL, NULL, NULL),
  (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 15.1, 2, 'NI', 100.1, 2, 'NI', NULL, NULL, NULL),
  (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 210.1, 2, 'NI', 100.1, 2, 'NI', 5, 2, 'NI'),
  (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 220.1, 2, 'NI', 100.1, 2, 'NI', 5, 2, 'NI'),
  (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 230.1, 2, 'NI', 100.1, 2, 'NI', 5, 2, 'NI'),
  (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 240.1, 2, 'NI', 100.1, 2, 'NI', 5, 2, 'NI'),
  (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 250.1, 2, 'NI', 100.1, 2, 'NI', 5, 2, 'NI');

INSERT INTO IVL_REAL(ID, VALUE1_LOW_VALUE, VALUE1_HIGH_VALUE, VALUE2_HIGH_VALUE, VALUE2_LOW_CLOSED, VALUE3_ANY_VALUE, VALUE3_HIGH_VALUE, VALUE3_HIGH_CLOSED, VALUE3_LOW_VALUE, VALUE3_LOW_CLOSED, VALUE3_WIDTH_VALUE, VALUE3_WIDTH_NULL_FLAVOR, VALUE3_NULL_FLAVOR)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 10.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 1, 210.2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, NULL, 220.2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 3, 230.2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 230.2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, -310, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, 40, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, 4222222, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, 43, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, 44, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, NULL, 510, '1', 2, '1', 44, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, NULL, 520, '1', 2, '1', 44, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, 530, '1', 2, '1', NULL, 'NI', NULL),
  (16, NULL, NULL, NULL, NULL, NULL, 540, '1', 2, '1', 44, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI'),
  (18, NULL, NULL, NULL, NULL, NULL, 610, '1', 251, '1', 4, NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, 620, '1', 252, '1', 4, NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, 630, '1', 253, '1', 4, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, 640, '1', 254, '1', 4, NULL, NULL),
  (22, NULL, NULL, NULL, NULL, 101, 650, '1', 255, '1', 4, NULL, NULL);

INSERT INTO IVL_TS(ID, VALUE1_LOW_VALUE, VALUE1_HIGH_VALUE, VALUE2_HIGH_VALUE, VALUE2_LOW_CLOSED, VALUE3_HIGH_VALUE, VALUE3_LOW_VALUE, VALUE3_WIDTH_NULL_FLAVOR, VALUE3_WIDTH_VALUE, VALUE3_NULL_FLAVOR)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, '2010-03-12 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, NULL, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, NULL, '2000-03-12 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, '2010-03-03 00:00:00', '2010-03-13 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, '2001-03-11 00:00:00', '1', NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, '2010-03-13 00:00:00', '0', NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, '2010-03-14 00:00:00', '1', NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, '2010-03-15 00:00:00', '1', NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, '2010-03-11 00:00:00', '2010-03-01 00:00:00', NULL, 4, NULL),
  (14, NULL, NULL, NULL, NULL, '2010-03-12 00:00:00', '2010-03-02 00:00:00', NULL, 4, NULL),
  (15, NULL, NULL, NULL, NULL, '2010-03-13 00:00:00', '2010-03-03 00:00:00', NULL, 4, NULL),
  (16, NULL, NULL, NULL, NULL, '2010-03-14 00:00:00', '2010-03-04 00:00:00', 'NI', NULL, NULL),
  (17, NULL, NULL, NULL, NULL, '2010-03-15 00:00:00', '2010-03-05 00:00:00', 'NI', NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, '2010-03-01 00:00:00', NULL, 4, NULL),
  (19, NULL, NULL, NULL, NULL, '2010-03-22 00:00:00', NULL, 'NI', NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, 'NA', NULL, 'NA'),
  (21, NULL, NULL, NULL, NULL, '2010-03-24 00:00:00', '2010-03-04 00:00:00', NULL, 4, NULL);

INSERT INTO PQV_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE, VALUE2_PRECISION, VALUE3_VALUE, VALUE3_PRECISION, VALUE4_NULL_FLAVOR, VALUE4_PRECISION, VALUE4_VALUE)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 1.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, 2.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, 3.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, 4.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, 5.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, 1.22, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, 2.22, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, 3.22, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, NULL, NULL, 4.22, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, NULL, NULL, 5.22, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, NULL, NULL, 1.23, 2, NULL, NULL, NULL, NULL, NULL),
  (22, NULL, NULL, 2.23, 2, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, NULL, 3.23, 2, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, 4.23, 2, NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, 5.23, 2, NULL, NULL, NULL, NULL, NULL),
  (26, NULL, 'NI', 1.23, 2, NULL, NULL, NULL, NULL, NULL),
  (27, NULL, 'NI', 2.23, 2, NULL, NULL, NULL, NULL, NULL),
  (28, NULL, 'NI', 3.23, 2, NULL, NULL, NULL, NULL, NULL),
  (29, NULL, 'NI', 4.23, 2, NULL, NULL, NULL, NULL, NULL),
  (30, NULL, 'NI', 5.23, 2, NULL, NULL, NULL, NULL, NULL);

INSERT INTO PQ_DATATYPE(ID, VALUE1_VALUE, VALUE1_UNIT, VALUE2_VALUE, VALUE3_NULL_FLAVOR, VALUE3_UNIT, VALUE3_VALUE, VALUE3_PRECISION)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 1.12, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 2.12, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 3.12, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 4.12, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 5.12, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, 1.12, 'GALLON', NULL, NULL, NULL, NULL, NULL),
  (8, 2.12, 'GALLON', NULL, NULL, NULL, NULL, NULL),
  (9, 3.12, 'GALLON', NULL, NULL, NULL, NULL, NULL),
  (10, 4.12, 'GALLON', NULL, NULL, NULL, NULL, NULL),
  (11, 5.12, 'GALLON', NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, 1.23, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, 2.23, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, 3.23, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, 4.23, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, 5.23, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, 1.34, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, 2.34, NULL),
  (19, NULL, NULL, NULL, NULL, 'LITER', 1.37, NULL),
  (20, NULL, NULL, NULL, NULL, 'LITER', 2.37, NULL),
  (21, NULL, NULL, NULL, NULL, 'LITER', 3.37, NULL),
  (22, NULL, NULL, NULL, NULL, 'LITER', 1.38, 2),
  (23, NULL, NULL, NULL, NULL, 'LITER', 2.38, 2),
  (24, NULL, NULL, NULL, NULL, 'LITER', 3.38, 2),
  (25, NULL, NULL, NULL, 'NA', NULL, NULL, NULL),
  (26, NULL, NULL, NULL, 'NA', NULL, NULL, NULL),
  (27, NULL, NULL, NULL, 'NA', NULL, NULL, NULL);

INSERT INTO REAL_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, 1001.15, NULL, NULL),
  (3, 1002.15, NULL, NULL),
  (4, -1003, NULL, NULL),
  (5, 1004, NULL, NULL),
  (6, 1005.15, NULL, NULL),
  (7, NULL, NULL, 1101.15),
  (8, NULL, NULL, 1102),
  (9, NULL, NULL, -1201.15),
  (10, NULL, 'NA', NULL),
  (11, NULL, 'NA', NULL),
  (12, NULL, 'NA', NULL);

INSERT INTO SC_DATATYPE(ID, VALUE1_VALUE, VALUE1_CODE_CODE, VALUE2_NULL_FLAVOR, VALUE2_VALUE, VALUE2_CODE_NULL_FLAVOR, VALUE2_CODE_CODE, VALUE2_CODE_CODE_SYSTEM, VALUE2_CODE_CODE_SYSTEM_NAME, VALUE2_CODE_CODE_SYSTEM_VER, VALUE3_NULL_FLAVOR, VALUE3_VALUE, VALUE3_CODE_NULL_FLAVOR, VALUE3_CODE_CODE, VALUE3_CODE_CODE_SYSTEM, VALUE3_CODE_CODE_SYSTEM_NAME, VALUE3_CODE_CODE_SYSTEM_VER, VALUE3_CODE_DISPLAY_NFLAVOR, VALUE3_CODE_DISPLAY_VALUE, VALUE3_CODE_ORIG_TXT_NFLAVOR, VALUE3_CODE_ORIG_TXT_DESC, VALUE3_CODE_ORIG_TXT_VALUE)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'VALUE1_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 'VALUE1_VALUE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 'VALUE1_VALUE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 'VALUE1_VALUE4', 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 'VALUE1_VALUE5', 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, 'UNK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, NULL, 'VALUE2_VALUE1', NULL, 'VALUE2_CODE_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, NULL, 'VALUE2_VALUE2', NULL, 'VALUE2_CODE_CODE2', 'VALUE2_CODE_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, NULL, 'VALUE2_VALUE3', NULL, 'VALUE2_CODE_CODE3', 'VALUE2_CODE_CODE_SYSTEM2', 'VALUE2_CODE_CODE_SYSTEM_NAME1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, NULL, 'VALUE2_VALUE4', NULL, 'VALUE2_CODE_CODE4', 'VALUE2_CODE_CODE_SYSTEM3', 'VALUE2_CODE_CODE_SYSTEM_NAME2', '1.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, 'VALUE2_VALUE5', NULL, 'VALUE2_CODE_CODE5', 'VALUE2_CODE_CODE_SYSTEM4', 'VALUE2_CODE_CODE_SYSTEM_NAME3', '1.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE1', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE2', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER1', NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE1', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER1', 'NI', NULL, 'NI', NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE3', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER', NULL, 'VALUE3_CODE_DISPLAY_VALUE', 'NI', NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE4', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE5', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE6', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE7', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC1', NULL),
  (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE8', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC31', 'VALUE3_CODE_ORIG_TXT_VALUE31'),
  (23, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE9', 'NI', 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC31', 'VALUE3_CODE_ORIG_TXT_VALUE31');

INSERT INTO ST_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, 'VALUE1_VALUE1', NULL, NULL),
  (3, 'VALUE1_VALUE2', NULL, NULL),
  (4, 'VALUE1_VALUE3', NULL, NULL),
  (5, 'VALUE1_VALUE4', NULL, NULL),
  (6, 'VALUE1_VALUE5', NULL, NULL),
  (7, NULL, NULL, 'VALUE2_VALUE1'),
  (8, NULL, NULL, 'VALUE2_VALUE2'),
  (9, NULL, NULL, 'VALUE2_VALUE3'),
  (10, NULL, NULL, 'VALUE2_VALUE4'),
  (11, NULL, NULL, 'VALUE2_VALUE5'),
  (12, NULL, 'UNK', NULL),
  (13, NULL, 'UNK', NULL),
  (14, NULL, 'UNK', NULL),
  (15, NULL, 'UNK', NULL),
  (16, NULL, 'UNK', NULL);

INSERT INTO ST_NT_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, 'VALUE1_VALUE1', NULL, NULL),
  (3, 'VALUE1_VALUE2', NULL, NULL),
  (4, 'VALUE1_VALUE3', NULL, NULL),
  (5, 'VALUE1_VALUE4', NULL, NULL),
  (6, 'VALUE1_VALUE5', NULL, NULL),
  (7, NULL, NULL, 'VALUE2_VALUE1'),
  (8, NULL, NULL, 'VALUE2_VALUE2'),
  (9, NULL, NULL, 'VALUE2_VALUE3'),
  (10, NULL, NULL, 'VALUE2_VALUE4'),
  (11, NULL, NULL, 'VALUE2_VALUE5'),
  (12, NULL, 'UNK', NULL),
  (13, NULL, 'UNK', NULL),
  (14, NULL, 'UNK', NULL),
  (15, NULL, 'UNK', NULL),
  (16, NULL, 'UNK', NULL);

INSERT INTO TEL_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, 'tel://123-456-7891', NULL, NULL),
  (3, 'tel://123-456-7892', NULL, NULL),
  (4, 'tel://123-456-7893', NULL, NULL),
  (5, 'tel://123-456-7894', NULL, NULL),
  (6, 'tel://123-456-7895', NULL, NULL),
  (7, NULL, NULL, 'tel://123-456-7896'),
  (8, NULL, NULL, 'tel://123-456-7897'),
  (9, NULL, NULL, 'tel://123-456-7898'),
  (10, NULL, NULL, 'tel://123-456-7893'),
  (11, NULL, NULL, 'tel://123-456-7894'),
  (12, NULL, NULL, 'tel://123-456-7895');

INSERT INTO TEL_EMAIL_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, 'mailto:jdoe1@nci.gov', NULL, NULL),
  (3, 'mailto:jdoe2@nci.gov', NULL, NULL),
  (4, 'mailto:jdoe3@nci.gov', NULL, NULL),
  (5, 'mailto:jdoe4@nci.gov', NULL, NULL),
  (6, 'mailto:jdoe5@nci.gov', NULL, NULL),
  (7, NULL, NULL, 'MailTo:jdoe1@nci.gov'),
  (8, NULL, NULL, 'MailTo:jdoe2@nci.gov'),
  (9, NULL, NULL, 'mailto:jdoe3@nci.gov'),
  (10, NULL, NULL, 'mailto:jdoe4@nci.gov'),
  (11, NULL, NULL, 'mailto:jdoe5@nci.gov');

INSERT INTO TEL_PERSON_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, 'tel:8004226231', NULL, NULL),
  (3, 'x-text-fax:8004226232', NULL, NULL),
  (4, 'x-text-tel:8004226233', NULL, NULL),
  (5, 'mailto:8004226235', NULL, NULL),
  (6, 'x-text-tel:8004226235', NULL, NULL),
  (7, NULL, NULL, 'x-text-tel:8004226234'),
  (8, NULL, NULL, 'mailto:8004226235'),
  (9, NULL, NULL, 'x-text-tel:8004226233'),
  (10, NULL, NULL, 'mailto:8004226235'),
  (11, NULL, NULL, 'x-text-tel:8004226235');

INSERT INTO TEL_PHONE_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, 'tel:8004226231', NULL, NULL),
  (3, 'x-text-tel:8004226232', NULL, NULL),
  (4, 'x-text-fax:8004226233', NULL, NULL),
  (5, 'tel:8004226234', NULL, NULL),
  (6, 'tel:8004226235', NULL, NULL),
  (7, NULL, NULL, 'tel:8004226231'),
  (8, NULL, NULL, 'x-text-tel:8004226232'),
  (9, NULL, NULL, 'x-text-fax:8004226233'),
  (10, NULL, NULL, 'tel:8004226234'),
  (11, NULL, NULL, 'tel:8004226235');

INSERT INTO TEL_URL_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, 'https://www.cancer.gov', NULL, NULL),
  (3, 'file://test.xml', NULL, NULL),
  (4, 'ftp://cancer.gov', NULL, NULL),
  (5, 'file://www.cancer3.gov', NULL, NULL),
  (6, 'http://www.cancer4.gov', NULL, NULL),
  (7, NULL, NULL, 'https://www.cancer.gov'),
  (8, NULL, NULL, 'nfs://d/test.xml'),
  (9, NULL, NULL, 'file://www.cancer3.gov'),
  (10, NULL, NULL, 'ftp://cancer.gov'),
  (11, NULL, NULL, 'http://www.cancer4.gov');

INSERT INTO TS_DATATYPE(ID, VALUE1_VALUE, VALUE2_NULL_FLAVOR, VALUE2_VALUE)
VALUES (1, NULL, NULL, NULL),
  (2, '2010-03-11 00:00:00', NULL, NULL),
  (3, '2010-03-12 00:00:00', NULL, NULL),
  (4, '2010-03-13 00:00:00', NULL, NULL),
  (5, '2010-03-14 00:00:00', NULL, NULL),
  (6, '2010-03-15 00:00:00', NULL, NULL),
  (7, NULL, NULL, '2010-03-21 00:00:00'),
  (8, NULL, NULL, '2010-03-22 00:00:00'),
  (9, NULL, NULL, '2010-03-23 00:00:00'),
  (10, NULL, 'NA', NULL),
  (11, NULL, 'NA', NULL),
  (12, NULL, 'NA', NULL);

COMMIT;

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

-- End of script
