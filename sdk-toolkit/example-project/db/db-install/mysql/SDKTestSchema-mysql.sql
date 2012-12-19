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
  `ID` decimal(8,2) NOT NULL,
  `TITLE` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ALBUM`
--

/*!40000 ALTER TABLE `ALBUM` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ALBUM` (`ID`,`TITLE`) VALUES 
 ('1.00','Venetian Oboe Concertos'),
 ('2.00','The Cello');
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
 (1,'name@mail.nih.gov','2008-05-15 00:00:00','123','999','2008-05-15 00:00:00','999','1','1','abc','abc','3','3','cat');
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
 (2,'Two',1,'TEST'),
 (3,'Three',1,'TEST'),
 (4,'Four',1,'TEST'),
 (5,'Five',1,'TEST'),
 (6,'Six',1,'TEST'),
 (7,'Seven',1,'TEST'),
 (8,'Eight',1,'TEST'),
 (9,'Nine',1,'TEST'),
 (10,'Ten',1,'TEST'),
 (11,'Jack',1,'TEST'),
 (12,'Queen',1,'TEST'),
 (13,'King',1,'TEST'),
 (14,'Ace',2,'TEST'),
 (15,'Two',2,'TEST'),
 (16,'Three',2,'TEST'),
 (17,'Four',2,'TEST'),
 (18,'Five',2,'TEST'),
 (19,'Six',2,'TEST'),
 (20,'Seven',2,'TEST'),
 (21,'Eight',2,'TEST'),
 (22,'Nine',2,'TEST'),
 (23,'Ten',2,'TEST'),
 (24,'Jack',2,'TEST'),
 (25,'Queen',2,'TEST'),
 (26,'King',2,'TEST'),
 (27,'Ace',3,'TEST'),
 (28,'Two',3,'TEST'),
 (29,'Three',3,'TEST'),
 (30,'Four',3,'TEST'),
 (31,'Five',3,'TEST'),
 (32,'Six',3,'TEST'),
 (33,'Seven',3,'TEST'),
 (34,'Eight',3,'TEST'),
 (35,'Nine',3,'TEST'),
 (36,'Ten',3,'TEST'),
 (37,'Jack',3,'TEST'),
 (38,'Queen',3,'TEST'),
 (39,'King',3,'TEST'),
 (40,'Ace',4,'TEST'),
 (41,'Two',4,'TEST'),
 (42,'Three',4,'TEST'),
 (43,'Four',4,'TEST'),
 (44,'Five',4,'TEST'),
 (45,'Six',4,'TEST'),
 (46,'Seven',4,'TEST'),
 (47,'Eight',4,'TEST'),
 (48,'Nine',4,'TEST'),
 (49,'Ten',4,'TEST'),
 (50,'Jack',4,'TEST'),
 (51,'Queen',4,'TEST'),
 (52,'King',4,'TEST'),
 (53,'Joker',NULL,'TEST');
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
DROP TABLE IF EXISTS `ABSTRACT_SPECIMEN`;
CREATE TABLE `ABSTRACT_SPECIMEN` (
  `ID` int(8) NOT NULL,
  `INITIAL_QTY` int(8),
  `LABEL` VARCHAR(50),
  `LINEAGE` VARCHAR(50),
  `PATHOLOGICAL_STATUS` VARCHAR(50),
  `SPECIMEN_CLASS` VARCHAR(50),
  `SPECIMEN_TYPE` VARCHAR(50),
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ABSTRACT_SPECIMEN`
--

/*!40000 ALTER TABLE `ADDRESS` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `ABSTRACT_SPECIMEN` (`ID`,`INITIAL_QTY`, `LABEL`, `LINEAGE`,`PATHOLOGICAL_STATUS`,`SPECIMEN_CLASS`, `SPECIMEN_TYPE`) VALUES 
 (1,10,'Specimen1',null, null, 'Class1', 'Type1'),
 (2,10,'Specimen2',null, null, 'Class2', 'Type2'),
 (3,10,'Specimen3',null, null, 'Class3', 'Type3'),
 (4,10,'Specimen4',null, null, 'Class4', 'Type4'),
 (5,10,'Specimen5',null, null, 'Class5', 'Type5');
COMMIT;

DROP TABLE IF EXISTS `SUPER_TEST`;
CREATE TABLE `SUPER_TEST` (
  `ID` int(8) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `SUPER_TEST` (`ID`) VALUES 
 (1),
 (2),
 (3),
 (4),
 (5);
COMMIT;

DROP TABLE IF EXISTS `CHILD_TEST`;
CREATE TABLE `CHILD_TEST` (
  `ID` int(8) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `CHILD_TEST` (`ID`) VALUES 
 (1),
 (2),
 (3),
 (4),
 (5);
COMMIT;

DROP TABLE IF EXISTS `SITE`;
CREATE TABLE `SITE` (
  `ID` int(8) NOT NULL,
  `ACTIVITY_STATUS` VARCHAR(50),
  `EMAIL_ADDRESS` VARCHAR(50),
  `NAME` VARCHAR(50),
  `TYPE` VARCHAR(10),
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `SITE` (`ID`, `ACTIVITY_STATUS`, `EMAIL_ADDRESS`, `NAME`, `TYPE`) VALUES 
 (1, 'ACTIVE', 'TEST@TEST.COM', 'SITE1', 'TYPE1'),
 (2, 'ACTIVE', 'TEST@TEST.COM', 'SITE2', 'TYPE1'),
 (3, 'ACTIVE', 'TEST@TEST.COM', 'SITE3', 'TYPE2'),
 (4, 'ACTIVE', 'TEST@TEST.COM', 'SITE4', 'TYPE2'),
 (5, 'ACTIVE', 'TEST@TEST.COM', 'SITE5', 'TYPE2');
COMMIT;


DROP TABLE IF EXISTS `SPECIMEN_COLLECTION_GROUP`;
CREATE TABLE `SPECIMEN_COLLECTION_GROUP` (
  `ID` int(8) NOT NULL,
  `NAME` VARCHAR(50),
  `COMMENT_TEXT` VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SPECIMEN`
--

SET AUTOCOMMIT=0;
INSERT INTO `SPECIMEN_COLLECTION_GROUP` (`ID`,`NAME`, `COMMENT_TEXT`) VALUES 
 (1,'GROUP1','COMMENT'),
 (2,'GROUP2','COMMENT'),
 (3,'GROUP3','COMMENT'),
 (4,'GROUP4','COMMENT'),
 (5,'GRPUP5','COMMENT');
COMMIT;


DROP TABLE IF EXISTS `SPECIMEN`;
CREATE TABLE `SPECIMEN` (
  `ID` int(8) NOT NULL,
  `ACTIVITY_STATUS` VARCHAR(50),
  `AVAILABLE_QTY` int(8),
  `BARCODE` VARCHAR(50),
  `COLLECTION_STATUS` VARCHAR(50),
  `COMMENT_TEXT` VARCHAR(50),
  `LABEL` VARCHAR(50),
  `CREATED_ON` DATE,
  `IS_AVAILABLE` int(8),
  `SPECIMEN_COLLECTION_GROUP_ID` int(8)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SPECIMEN`
--

SET AUTOCOMMIT=0;
INSERT INTO `SPECIMEN` (`ID`,`ACTIVITY_STATUS`, `AVAILABLE_QTY`, `BARCODE`,`COLLECTION_STATUS`,`COMMENT_TEXT`, `LABEL`, `CREATED_ON`, `IS_AVAILABLE`, `SPECIMEN_COLLECTION_GROUP_ID`) VALUES 
 (1,'ACTIVE',1, '123', 'STATUS', 'COMMENT', 'Specimen1', '2011-11-11 00:00:00', 1, 1),
 (2,'ACTIVE',2, '123', 'STATUS', 'COMMENT', 'Specimen1', '2011-11-11 00:00:00', 1, 1),
 (3,'ACTIVE',3, '123', 'STATUS', 'COMMENT', 'Specimen1', '2011-11-11 00:00:00', 1, 2),
 (4,'ACTIVE',3, '123', 'STATUS', 'COMMENT', 'Specimen1', '2011-11-11 00:00:00', 1, 2),
 (5,'ACTIVE',10, '123', 'STATUS', 'COMMENT', 'Specimen1', '2011-11-11 00:00:00', 1, 2);
COMMIT;

ALTER TABLE SPECIMEN ADD CONSTRAINT PK_SPECIMEN_ID 
	PRIMARY KEY (ID)
;

ALTER TABLE SPECIMEN_COLLECTION_GROUP ADD CONSTRAINT PK_SPECIMEN_COLLECTION_GROUP_ID 
	PRIMARY KEY (ID)
;

ALTER TABLE SPECIMEN ADD CONSTRAINT FK_SPECIMEN_COLLECTION_GROUP_ID 
	FOREIGN KEY (SPECIMEN_COLLECTION_GROUP_ID) REFERENCES SPECIMEN_COLLECTION_GROUP (ID)
;


DROP TABLE IF EXISTS `MEMBER_O2OBS`;
CREATE TABLE `MEMBER_O2OBS` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `MENTOR_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_MENTOR` (`MENTOR_ID`),
  UNIQUE KEY `UQ_FK_MENTOR` (`MENTOR_ID`)
);

DROP TABLE IF EXISTS `MEMBER_O2MUS`;
CREATE TABLE `MEMBER_O2MUS` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `MENTOR_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_MENTOR` (`MENTOR_ID`),
  UNIQUE KEY `UQ_FK_MENTOR` (`MENTOR_ID`)
);



DROP TABLE IF EXISTS `MEMBER_O2MBS`;
CREATE TABLE `MEMBER_O2MBS` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `FRIEND_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_FRIEND` (`FRIEND_ID`)
);


DROP TABLE IF EXISTS `MEMBER_O2OUS`;
CREATE TABLE `MEMBER_O2OUS` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  `MENTOR_ID` int(8) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_MENTOR` (`MENTOR_ID`)
);


DROP TABLE IF EXISTS `MEMBER_M2MBS`;

CREATE TABLE `MEMBER_M2MBS` (
  `ID` int(8) NOT NULL,
  `NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
);

DROP TABLE IF EXISTS `MEMBER_M2MBS_JOIN`;
CREATE TABLE `MEMBER_M2MBS_JOIN` (
  `MEMBER_ID` int(8) NOT NULL,
  `MENTOR_ID` int(8) NOT NULL,
  PRIMARY KEY  (`MEMBER_ID`, `MENTOR_ID`  ),
  UNIQUE KEY `PK_MEMBER_M2MBS_JOIN` (`MEMBER_ID`, `MENTOR_ID`)
) ;


SET AUTOCOMMIT=0;

INSERT INTO MEMBER_O2OBS ( ID, NAME, MENTOR_ID ) VALUES ( 1, 'Name1', 2);
commit;

INSERT INTO MEMBER_O2OBS ( ID, NAME, MENTOR_ID ) VALUES ( 2, 'Name2', 1);
commit;
INSERT INTO MEMBER_O2OBS ( ID, NAME, MENTOR_ID ) VALUES ( 3, 'Name3', NULL);
INSERT INTO MEMBER_O2OBS ( ID, NAME, MENTOR_ID ) VALUES ( 4, 'Name4', NULL);
commit;



INSERT INTO MEMBER_O2MBS ( ID, NAME, FRIEND_ID ) VALUES ( 1, 'Name1', NULL);
commit;
INSERT INTO MEMBER_O2MBS ( ID, NAME, FRIEND_ID ) VALUES ( 2, 'Name2', 1);
INSERT INTO MEMBER_O2MBS ( ID, NAME, FRIEND_ID ) VALUES ( 3, 'Name3', 1);
INSERT INTO MEMBER_O2MBS ( ID, NAME, FRIEND_ID ) VALUES ( 4, 'MEMBER_O2MBS', NULL);
commit;

INSERT INTO MEMBER_O2OUS ( ID, NAME, MENTOR_ID ) VALUES ( 1, 'Name1', NULL);
commit;
INSERT INTO MEMBER_O2OUS ( ID, NAME, MENTOR_ID ) VALUES ( 2, 'Name2', 1);
INSERT INTO MEMBER_O2OUS ( ID, NAME, MENTOR_ID ) VALUES ( 3, 'Name3', 2);
INSERT INTO MEMBER_O2OUS ( ID, NAME, MENTOR_ID ) VALUES ( 4, 'MEMBER_O2OUS', NULL);
INSERT INTO MEMBER_O2OUS ( ID, NAME, MENTOR_ID ) VALUES ( 5, 'MEMBER_O2OUS', 4);
commit;

INSERT INTO MEMBER_O2MUS ( ID, NAME, MENTOR_ID ) VALUES ( 1, 'Name1', 2);
INSERT INTO MEMBER_O2MUS ( ID, NAME, MENTOR_ID ) VALUES ( 2, 'Name2', 1);
INSERT INTO MEMBER_O2MUS ( ID, NAME, MENTOR_ID ) VALUES ( 3, 'Name3', NULL);
INSERT INTO MEMBER_O2MUS ( ID, NAME, MENTOR_ID ) VALUES ( 4, 'Name4', NULL);
commit;


INSERT INTO MEMBER_M2MBS ( ID, NAME ) VALUES ( 1, 'Employee_Name1');
INSERT INTO MEMBER_M2MBS ( ID, NAME ) VALUES ( 2, 'Employee_Name2');
INSERT INTO MEMBER_M2MBS ( ID, NAME ) VALUES ( 3, 'Employee_Name3');
INSERT INTO MEMBER_M2MBS ( ID, NAME ) VALUES ( 4, 'Employee_Name4');
INSERT INTO MEMBER_M2MBS ( ID, NAME ) VALUES ( 5, 'Employee_Name5');
INSERT INTO MEMBER_M2MBS ( ID, NAME ) VALUES ( 6, 'Employee_Name6');
INSERT INTO MEMBER_M2MBS ( ID, NAME ) VALUES ( 7, 'Employee_Name7');
INSERT INTO MEMBER_M2MBS ( ID, NAME ) VALUES ( 8, 'Employee_Name8');
INSERT INTO MEMBER_M2MBS ( ID, NAME ) VALUES ( 9, 'Employee_Name9');
INSERT INTO MEMBER_M2MBS ( ID, NAME ) VALUES ( 10, 'Employee_Name10');
commit;

INSERT INTO MEMBER_M2MBS_JOIN ( MEMBER_ID, MENTOR_ID ) VALUES ( 1, 1);
INSERT INTO MEMBER_M2MBS_JOIN ( MEMBER_ID, MENTOR_ID ) VALUES ( 2, 2);
INSERT INTO MEMBER_M2MBS_JOIN ( MEMBER_ID, MENTOR_ID ) VALUES ( 3, 2);
INSERT INTO MEMBER_M2MBS_JOIN ( MEMBER_ID, MENTOR_ID ) VALUES ( 4, 4);
INSERT INTO MEMBER_M2MBS_JOIN ( MEMBER_ID, MENTOR_ID ) VALUES ( 4, 5);
INSERT INTO MEMBER_M2MBS_JOIN ( MEMBER_ID, MENTOR_ID ) VALUES ( 6, 5);
commit;


