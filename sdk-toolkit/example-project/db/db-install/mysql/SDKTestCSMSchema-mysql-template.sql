--
-- Definition of table `CSM_APPLICATION`
--

DROP TABLE IF EXISTS CSM_CONFIGURATION_PROPS;
DROP TABLE IF EXISTS CSM_PASSWORD_HISTORY;
DROP TABLE IF EXISTS CSM_MAPPING;
DROP TABLE IF EXISTS CSM_ROLE_PRIVILEGE;
DROP TABLE IF EXISTS CSM_PRIVILEGE;
DROP TABLE IF EXISTS CSM_USER_GROUP_ROLE_PG;
DROP TABLE IF EXISTS CSM_ROLE;
DROP TABLE IF EXISTS CSM_USER_GROUP;
DROP TABLE IF EXISTS CSM_USER_PE;
DROP TABLE IF EXISTS CSM_FILTER_CLAUSE;
DROP TABLE IF EXISTS CSM_GROUP;
DROP TABLE IF EXISTS CSM_PG_PE;
DROP TABLE IF EXISTS CSM_PROTECTION_ELEMENT;
DROP TABLE IF EXISTS CSM_PROTECTION_GROUP;
DROP TABLE IF EXISTS CSM_USER;

DROP TABLE IF EXISTS `CSM_APPLICATION`;
CREATE TABLE CSM_APPLICATION ( 
	APPLICATION_ID BIGINT AUTO_INCREMENT  NOT NULL,
	APPLICATION_NAME VARCHAR(255) NOT NULL,
	APPLICATION_DESCRIPTION VARCHAR(200) NOT NULL,
	DECLARATIVE_FLAG BOOL NOT NULL DEFAULT 0,
	ACTIVE_FLAG BOOL NOT NULL DEFAULT 0,
	UPDATE_DATE DATE DEFAULT '0000-00-00',
	DATABASE_URL VARCHAR(100),
	DATABASE_USER_NAME VARCHAR(100),
	DATABASE_PASSWORD VARCHAR(100),
	DATABASE_DIALECT VARCHAR(100),
	DATABASE_DRIVER VARCHAR(100),
	CSM_VERSION VARCHAR(20),
	PRIMARY KEY(APPLICATION_ID)
)Type=InnoDB
;


--
-- Dumping data for table `CSM_APPLICATION`
--

/*!40000 ALTER TABLE `CSM_APPLICATION` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_APPLICATION` (`APPLICATION_ID`,`APPLICATION_NAME`,`APPLICATION_DESCRIPTION`,`DECLARATIVE_FLAG`,`ACTIVE_FLAG`,`UPDATE_DATE`,`DATABASE_URL`,`DATABASE_USER_NAME`,`DATABASE_PASSWORD`,`DATABASE_DIALECT`,`DATABASE_DRIVER`) VALUES 
 ('1','csmupt','UPT Super Admin Application',0,0,'2007-02-28 13:03:02',NULL,NULL,NULL,NULL,NULL),
 ('2','sdk','sdk',1,1,'2008-04-02 00:00:00','@CSM_DB_CONNECTION_URL@','@CSM_DB_USERNAME@','rCqQ2e5ZFpsV5pL0kOI0eg==','org.hibernate.dialect.MySQLDialect','org.gjt.mm.mysql.Driver');
COMMIT;
/*!40000 ALTER TABLE `CSM_APPLICATION` ENABLE KEYS */;


--
-- Definition of table `CSM_FILTER_CLAUSE`
--

DROP TABLE IF EXISTS CSM_FILTER_CLAUSE;
CREATE TABLE CSM_FILTER_CLAUSE ( 
	FILTER_CLAUSE_ID BIGINT AUTO_INCREMENT  NOT NULL,
	CLASS_NAME VARCHAR(100) NOT NULL,
	FILTER_CHAIN VARCHAR(2000) NOT NULL,
	TARGET_CLASS_NAME VARCHAR (100) NOT NULL,
	TARGET_CLASS_ATTRIBUTE_NAME VARCHAR (100) NOT NULL,
	TARGET_CLASS_ATTRIBUTE_TYPE VARCHAR (100) NOT NULL,
	TARGET_CLASS_ALIAS VARCHAR (100),
	TARGET_CLASS_ATTRIBUTE_ALIAS VARCHAR (100),
	GENERATED_SQL_USER VARCHAR (4000) NOT NULL,
	GENERATED_SQL_GROUP VARCHAR (4000) NOT NULL,
	APPLICATION_ID BIGINT NOT NULL,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PRIMARY KEY(FILTER_CLAUSE_ID)	
)Type=InnoDB 
;

--
-- Dumping data for table `CSM_FILTER_CLAUSE`
--

/*!40000 ALTER TABLE `CSM_FILTER_CLAUSE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_FILTER_CLAUSE` (`FILTER_CLAUSE_ID`,`CLASS_NAME`,`FILTER_CHAIN`,`TARGET_CLASS_NAME`,`TARGET_CLASS_ATTRIBUTE_NAME`,`TARGET_CLASS_ATTRIBUTE_TYPE`,`TARGET_CLASS_ALIAS`,`TARGET_CLASS_ATTRIBUTE_ALIAS`,`GENERATED_SQL_USER`,`APPLICATION_ID`,`UPDATE_DATE`,`GENERATED_SQL_GROUP`) VALUES 
 ('17','gov.nih.nci.cacoresdk.domain.other.levelassociation.Card','suit, deck','gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck - deck','id','java.lang.Integer',NULL,NULL,'ID in (select table_name_csm_.ID   from CARD table_name_csm_, SUIT suit1_, DECK deck2_ where table_name_csm_.SUIT_ID=suit1_.ID and suit1_.DECK_ID=deck2_.ID and deck2_.ID in ( select pe.attribute_value from csm_protection_group pg, csm_protection_element pe, csm_pg_pe pgpe, csm_user_group_role_pg ugrpg, csm_user u, csm_role_privilege rp, csm_role r, csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.user_id = u.user_id and ugrpg.protection_group_id = ANY (select pg1.protection_group_id from csm_protection_group pg1 where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id = (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id)) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= \'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck\' and pe.attribute=\'id\' and p.privilege_name=\'READ\' and u.login_name=:USER_NAME and pe.application_id=:APPLICATION_ID))','2','2008-06-30 00:00:00','ID in (select table_name_csm_.ID   from CARD table_name_csm_, SUIT suit1_, DECK deck2_ where table_name_csm_.SUIT_ID=suit1_.ID and suit1_.DECK_ID=deck2_.ID and deck2_.ID in ( select distinct pe.attribute_value from csm_protection_group pg, 	csm_protection_element pe, 	csm_pg_pe pgpe,	csm_user_group_role_pg ugrpg, 	csm_group g, 	csm_role_privilege rp, 	csm_role r, 	csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.group_id = g.group_id and ugrpg.protection_group_id = any ( select pg1.protection_group_id from csm_protection_group pg1  where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id =  (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id) ) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= \'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck\' and pe.attribute=\'id\' and p.privilege_name=\'READ\' and g.group_name IN (:GROUP_NAMES ) and pe.application_id=:APPLICATION_ID))');
COMMIT;
/*!40000 ALTER TABLE `CSM_FILTER_CLAUSE` ENABLE KEYS */;


--
-- Definition of table `CSM_GROUP`
--

DROP TABLE IF EXISTS CSM_GROUP;
CREATE TABLE CSM_GROUP ( 
	GROUP_ID BIGINT AUTO_INCREMENT  NOT NULL,
	GROUP_NAME VARCHAR(255) NOT NULL,
	GROUP_DESC VARCHAR(200),
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	APPLICATION_ID BIGINT NOT NULL,
	PRIMARY KEY(GROUP_ID)
)Type=InnoDB
;

--
-- Dumping data for table `CSM_GROUP`
--

/*!40000 ALTER TABLE `CSM_GROUP` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_GROUP` (`GROUP_ID`,`GROUP_NAME`,`GROUP_DESC`,`UPDATE_DATE`,`APPLICATION_ID`) VALUES 
 ('2','Group1','Group 1 - Access to All PE\'s.  Same as user1','2008-06-30 00:00:00','2'),
 ('3','Group2','Group 2 - Same limited access as user2','2008-06-30 00:00:00','2'),
 ('4','Group3','Group3 - No access','2008-06-30 00:00:00','2');
COMMIT;
/*!40000 ALTER TABLE `CSM_GROUP` ENABLE KEYS */;


--
-- Definition of table `CSM_PG_PE`
--

DROP TABLE IF EXISTS CSM_PG_PE;
CREATE TABLE CSM_PG_PE ( 
	PG_PE_ID BIGINT AUTO_INCREMENT  NOT NULL,
	PROTECTION_GROUP_ID BIGINT NOT NULL,
	PROTECTION_ELEMENT_ID BIGINT NOT NULL,
	UPDATE_DATE DATE DEFAULT '0000-00-00',
	PRIMARY KEY(PG_PE_ID)
)Type=InnoDB
;


--
-- Dumping data for table `CSM_PG_PE`
--

/*!40000 ALTER TABLE `CSM_PG_PE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_PG_PE` (`PG_PE_ID`,`PROTECTION_GROUP_ID`,`PROTECTION_ELEMENT_ID`,`UPDATE_DATE`) VALUES 
 ('107','2','11','2007-07-17 15:30:19'),
 ('108','2','24','2007-07-17 15:30:19'),
 ('110','2','8','2007-07-17 15:30:19'),
 ('111','3','37','2007-07-17 15:30:19'),
 ('670','1','100','2007-12-19 16:40:11'),
 ('671','1','33','2007-12-19 16:40:11'),
 ('672','1','81','2007-12-19 16:40:11'),
 ('673','1','108','2007-12-19 16:40:11'),
 ('674','1','88','2007-12-19 16:40:11'),
 ('675','1','83','2007-12-19 16:40:11'),
 ('676','1','77','2007-12-19 16:40:11'),
 ('677','1','4','2007-12-19 16:40:11'),
 ('678','1','113','2007-12-19 16:40:11'),
 ('679','1','17','2007-12-19 16:40:11'),
 ('680','1','15','2007-12-19 16:40:11'),
 ('681','1','65','2007-12-19 16:40:11'),
 ('682','1','95','2007-12-19 16:40:11'),
 ('683','1','93','2007-12-19 16:40:11'),
 ('684','1','90','2007-12-19 16:40:11'),
 ('685','1','103','2007-12-19 16:40:11'),
 ('686','1','20','2007-12-19 16:40:11'),
 ('687','1','101','2007-12-19 16:40:11'),
 ('688','1','18','2007-12-19 16:40:11'),
 ('689','1','67','2007-12-19 16:40:11'),
 ('690','1','76','2007-12-19 16:40:11'),
 ('691','1','92','2007-12-19 16:40:11'),
 ('692','1','80','2007-12-19 16:40:11'),
 ('693','1','82','2007-12-19 16:40:11'),
 ('694','1','116','2007-12-19 16:40:11'),
 ('695','1','24','2007-12-19 16:40:11'),
 ('696','1','91','2007-12-19 16:40:11'),
 ('697','1','98','2007-12-19 16:40:11'),
 ('698','1','32','2007-12-19 16:40:11'),
 ('699','1','14','2007-12-19 16:40:11'),
 ('700','1','23','2007-12-19 16:40:11'),
 ('701','1','75','2007-12-19 16:40:11'),
 ('702','1','99','2007-12-19 16:40:11'),
 ('703','1','16','2007-12-19 16:40:11'),
 ('704','1','38','2007-12-19 16:40:11'),
 ('705','1','66','2007-12-19 16:40:11'),
 ('706','1','37','2007-12-19 16:40:11'),
 ('708','1','30','2007-12-19 16:40:11'),
 ('709','1','45','2007-12-19 16:40:11'),
 ('710','1','35','2007-12-19 16:40:11'),
 ('711','1','94','2007-12-19 16:40:11'),
 ('712','1','41','2007-12-19 16:40:11'),
 ('713','1','86','2007-12-19 16:40:11'),
 ('714','1','79','2007-12-19 16:40:11'),
 ('715','1','27','2007-12-19 16:40:11'),
 ('716','1','13','2007-12-19 16:40:11'),
 ('717','1','68','2007-12-19 16:40:11'),
 ('718','1','71','2007-12-19 16:40:11'),
 ('719','1','26','2007-12-19 16:40:11'),
 ('720','1','7','2007-12-19 16:40:11'),
 ('721','1','25','2007-12-19 16:40:11'),
 ('722','1','115','2007-12-19 16:40:11'),
 ('723','1','85','2007-12-19 16:40:11'),
 ('724','1','110','2007-12-19 16:40:11'),
 ('725','1','36','2007-12-19 16:40:11'),
 ('726','1','31','2007-12-19 16:40:11'),
 ('727','1','11','2007-12-19 16:40:11'),
 ('728','1','74','2007-12-19 16:40:11'),
 ('729','1','84','2007-12-19 16:40:11'),
 ('730','1','102','2007-12-19 16:40:11'),
 ('731','1','97','2007-12-19 16:40:11'),
 ('732','1','8','2007-12-19 16:40:11'),
 ('733','1','43','2007-12-19 16:40:11'),
 ('734','1','42','2007-12-19 16:40:11'),
 ('735','1','28','2007-12-19 16:40:11'),
 ('736','1','70','2007-12-19 16:40:11'),
 ('737','1','12','2007-12-19 16:40:11'),
 ('738','1','29','2007-12-19 16:40:11'),
 ('739','1','72','2007-12-19 16:40:11'),
 ('740','1','44','2007-12-19 16:40:11'),
 ('741','1','114','2007-12-19 16:40:11'),
 ('742','1','22','2007-12-19 16:40:11'),
 ('743','1','40','2007-12-19 16:40:11'),
 ('744','1','6','2007-12-19 16:40:11'),
 ('745','1','87','2007-12-19 16:40:11'),
 ('746','1','21','2007-12-19 16:40:11'),
 ('747','1','9','2007-12-19 16:40:11'),
 ('748','1','34','2007-12-19 16:40:11'),
 ('749','1','104','2007-12-19 16:40:11'),
 ('750','1','89','2007-12-19 16:40:11'),
 ('751','1','107','2007-12-19 16:40:11'),
 ('752','1','19','2007-12-19 16:40:11'),
 ('753','1','5','2007-12-19 16:40:11'),
 ('754','1','96','2007-12-19 16:40:11'),
 ('755','1','73','2007-12-19 16:40:11'),
 ('765','1','39','2007-12-27 12:41:17'),
 ('766','4','24','2008-05-26 16:33:58'),
 ('773','7','40','2008-06-30 15:14:32'),
 ('774','7','5','2008-06-30 15:14:32'),
 ('775','7','39','2008-06-30 15:14:32'),
 ('776','7','41','2008-06-30 15:14:32'),
 ('777','7','114','2008-06-30 15:14:32'),
 ('778','7','38','2008-06-30 15:14:32'),
 ('779','1','117','2008-09-05 12:27:56');
COMMIT;
/*!40000 ALTER TABLE `CSM_PG_PE` ENABLE KEYS */;


--
-- Definition of table `CSM_PRIVILEGE`
--

DROP TABLE IF EXISTS CSM_PRIVILEGE;
CREATE TABLE CSM_PRIVILEGE ( 
	PRIVILEGE_ID BIGINT AUTO_INCREMENT  NOT NULL,
	PRIVILEGE_NAME VARCHAR(100) NOT NULL,
	PRIVILEGE_DESCRIPTION VARCHAR(200),
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PRIMARY KEY(PRIVILEGE_ID)
)Type=InnoDB
;

--
-- Dumping data for table `CSM_PRIVILEGE`
--

/*!40000 ALTER TABLE `CSM_PRIVILEGE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_PRIVILEGE` (`PRIVILEGE_ID`,`PRIVILEGE_NAME`,`PRIVILEGE_DESCRIPTION`,`UPDATE_DATE`) VALUES 
 ('1','CREATE','This privilege grants permission to a user to create an entity. This entity can be an object, a database entry, or a resource such as a network connection','2007-02-28 13:03:03'),
 ('2','ACCESS','This privilege allows a user to access a particular resource.  ','2007-02-28 13:03:04'),
 ('3','READ','This privilege permits the user to read data from a file, URL, socket, database, or an object. ','2007-02-28 13:03:04'),
 ('4','WRITE','This privilege allows a user to write data to a file, URL, socket, database, or object. ','2007-02-28 13:03:04'),
 ('5','UPDATE','This privilege grants permission at an entity level and signifies that the user is allowed to update and modify data for a particular entity.','2007-02-28 13:03:04'),
 ('6','DELETE','This privilege permits a user to delete a logical entity.','2007-02-28 13:03:04'),
 ('7','EXECUTE','This privilege allows a user to execute a particular resource.','2007-02-28 13:03:04');
COMMIT;
/*!40000 ALTER TABLE `CSM_PRIVILEGE` ENABLE KEYS */;


--
-- Definition of table `CSM_PROTECTION_ELEMENT`
--

DROP TABLE IF EXISTS CSM_PROTECTION_ELEMENT;
CREATE TABLE CSM_PROTECTION_ELEMENT ( 
	PROTECTION_ELEMENT_ID BIGINT AUTO_INCREMENT  NOT NULL,
	PROTECTION_ELEMENT_NAME VARCHAR(100) NOT NULL,
	PROTECTION_ELEMENT_DESCRIPTION VARCHAR(200),
	OBJECT_ID VARCHAR(100) NOT NULL,
	ATTRIBUTE VARCHAR(100) ,
	ATTRIBUTE_VALUE VARCHAR(100) ,
	PROTECTION_ELEMENT_TYPE VARCHAR(100),
	APPLICATION_ID BIGINT NOT NULL,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PRIMARY KEY(PROTECTION_ELEMENT_ID)
)Type=InnoDB
;

--
-- Dumping data for table `CSM_PROTECTION_ELEMENT`
--

/*!40000 ALTER TABLE `CSM_PROTECTION_ELEMENT` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_PROTECTION_ELEMENT` (`PROTECTION_ELEMENT_ID`,`protection_element_name`,`protection_element_description`,`object_id`,`attribute`,`protection_element_type`,`APPLICATION_ID`,`UPDATE_DATE`,`attribute_value`) VALUES 
 ('1','csmupt','UPT Super Admin Application','csmupt',NULL,NULL,'1','2007-02-28 13:03:03',NULL),
 ('2','sdk','sdk Application','sdk',NULL,NULL,'1','2008-04-02 00:00:00',NULL),
 ('4','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash',NULL,NULL,'2','2007-03-01 15:03:24',NULL),
 ('5','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit','issuingBank',NULL,'2','2007-12-17 00:00:00',NULL),
 ('6','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment',NULL,NULL,'2','2007-03-01 15:03:24',NULL),
 ('7','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent',NULL,NULL,'2','2007-03-01 15:03:24',NULL),
 ('8','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student',NULL,NULL,'2','2007-03-01 15:03:24',NULL),
 ('9','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent',NULL,NULL,'2','2007-03-01 15:03:24',NULL),
 ('11','gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal','gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal','gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal',NULL,NULL,'2','2007-03-01 15:03:24',NULL),
 ('12','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant',NULL,NULL,'2','2007-03-01 15:03:25',NULL),
 ('13','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor',NULL,NULL,'2','2007-03-01 15:03:25',NULL),
 ('14','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor',NULL,NULL,'2','2007-03-01 15:03:25',NULL),
 ('15','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor',NULL,NULL,'2','2007-03-01 15:03:25',NULL),
 ('16','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor',NULL,NULL,'2','2007-03-01 15:03:25',NULL),
 ('17','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor',NULL,NULL,'2','2007-03-01 15:03:25',NULL),
 ('18','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display',NULL,NULL,'2','2007-03-01 15:03:25',NULL),
 ('19','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor',NULL,NULL,'2','2007-03-01 15:03:25',NULL),
 ('20','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor',NULL,NULL,'2','2007-03-01 15:03:25',NULL),
 ('21','gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee','gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee','gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('22','gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project','gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project','gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('23','gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author','gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author','gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('24','gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book','gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book','gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('25','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('26','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('27','gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer','gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer','gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('28','gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive','gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive','gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('29','gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.Key','gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.Key','gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.Key',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('30','gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain','gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain','gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('31','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine',NULL,NULL,'2','2007-03-01 15:03:26',NULL),
 ('32','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product',NULL,NULL,'2','2007-03-01 15:03:27',NULL),
 ('33','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child',NULL,NULL,'2','2007-03-01 15:03:27',NULL),
 ('34','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent',NULL,NULL,'2','2007-03-01 15:03:27',NULL),
 ('35','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address',NULL,NULL,'2','2007-03-01 15:03:27',NULL),
 ('36','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person',NULL,NULL,'2','2007-03-01 15:03:27',NULL),
 ('37','gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType','gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType','gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType',NULL,NULL,'2','2007-03-01 15:03:27',NULL),
 ('38','gov.nih.nci.cacoresdk.domain.other.levelassociation.Card','gov.nih.nci.cacoresdk.domain.other.levelassociation.Card','gov.nih.nci.cacoresdk.domain.other.levelassociation.Card','Name',NULL,'2','2007-12-21 00:00:00','Ace'),
 ('39','gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck','gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck','gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck','id',NULL,'2','2008-06-30 00:00:00','1'),
 ('40','gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand','gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand','gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand',NULL,NULL,'2','2007-03-01 15:03:27',NULL),
 ('41','gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit','gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit','gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit','cardCollection',NULL,'2','2007-12-27 00:00:00',NULL),
 ('42','gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey','gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey','gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey',NULL,NULL,'2','2007-03-01 15:03:28',NULL),
 ('43','gov.nih.nci.cacoresdk.domain.other.primarykey.FloatKey','gov.nih.nci.cacoresdk.domain.other.primarykey.FloatKey','gov.nih.nci.cacoresdk.domain.other.primarykey.FloatKey',NULL,NULL,'2','2007-03-01 15:03:28',NULL),
 ('44','gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerKey','gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerKey','gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerKey',NULL,NULL,'2','2007-03-01 15:03:28',NULL),
 ('45','gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey','gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey','gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey',NULL,NULL,'2','2007-03-01 15:03:28',NULL),
 ('65','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('66','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('67','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('68','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('70','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('71','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('72','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization','gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('73','gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency','gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency','gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('74','gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note','gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note','gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('75','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('76','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('77','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('79','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel','gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('80','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('81','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('82','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('83','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('84','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('85','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('86','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('87','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('88','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator','gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('89','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('90','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song','gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('91','gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight','gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight','gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('92','gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Shirt','gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Shirt','gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Shirt',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('93','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('94','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant','gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('95','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('96','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw','gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('97','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('98','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle','gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('99','gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey','gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey','gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('100','gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('101','gov.nih.nci.cacoresdk.domain.other.primarykey.DoublePrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.DoublePrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.DoublePrimitiveKey',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('102','gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('103','gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('104','gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey','gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey','gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('107','gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('108','gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey','gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey','gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('110','gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey','gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey',NULL,NULL,'2','2007-12-13 00:00:00',NULL),
 ('113','gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human','gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human','gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human',NULL,NULL,'2','2007-12-17 00:00:00',NULL),
 ('114','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank','PE for \'name\' attribute of Bank object','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank','name',NULL,'2','2007-12-17 00:00:00',NULL),
 ('115','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit.amount','PE for Credit.amount attribute','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit','amount',NULL,'2','2007-12-19 00:00:00',NULL),
 ('116','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit.cardNumber','PE for Credit.cardNumber attribute','gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit','cardNumber',NULL,'2','2007-12-19 00:00:00',NULL),
 ('117','gov.nih.nci.cacoresdk.domain.other.validationtype.AllValidationType','gov.nih.nci.cacoresdk.domain.other.validationtype.AllValidationType','gov.nih.nci.cacoresdk.domain.other.validationtype.AllValidationType',NULL,NULL,'2','2008-09-05 12:07:28',NULL);
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:ACCESS_UPT_GROUP_OPERATION', 'System required protection element :ACCESS_UPT_GROUP_OPERATION\'\n Do not change its unique object ID.', 'ACCESS_UPT_GROUP_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:CREATE_UPT_GROUP_OPERATION', 'System required protection element :CREATE_UPT_GROUP_OPERATION\'\n Do not change its unique object ID.', 'CREATE_UPT_GROUP_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:DELETE_UPT_GROUP_OPERATION', 'System required protection element :DELETE_UPT_GROUP_OPERATION\'\n Do not change its unique object ID.', 'DELETE_UPT_GROUP_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:UPDATE_UPT_GROUP_OPERATION', 'System required protection element :UPDATE_UPT_GROUP_OPERATION\'\n Do not change its unique object ID.', 'UPDATE_UPT_GROUP_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:ACCESS_UPT_INSTANCE_LEVEL_OPERATION', 'System required protection element :ACCESS_UPT_INSTANCE_LEVEL_OPERATION\'\n Do not change its unique object ID.', 'ACCESS_UPT_INSTANCE_LEVEL_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:CREATE_UPT_INSTANCE_LEVEL_OPERATION', 'System required protection element :CREATE_UPT_INSTANCE_LEVEL_OPERATION\'\n Do not change its unique object ID.', 'CREATE_UPT_INSTANCE_LEVEL_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:DELETE_UPT_INSTANCE_LEVEL_OPERATION', 'System required protection element :DELETE_UPT_INSTANCE_LEVEL_OPERATION\'\n Do not change its unique object ID.', 'DELETE_UPT_INSTANCE_LEVEL_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:UPDATE_UPT_INSTANCE_LEVEL_OPERATION', 'System required protection element :UPDATE_UPT_INSTANCE_LEVEL_OPERATION\'\n Do not change its unique object ID.', 'UPDATE_UPT_INSTANCE_LEVEL_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:ACCESS_UPT_PRIVILEGE_OPERATION', 'System required protection element :ACCESS_UPT_PRIVILEGE_OPERATION\'\n Do not change its unique object ID.', 'ACCESS_UPT_PRIVILEGE_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:CREATE_UPT_PRIVILEGE_OPERATION', 'System required protection element :CREATE_UPT_PRIVILEGE_OPERATION\'\n Do not change its unique object ID.', 'CREATE_UPT_PRIVILEGE_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:DELETE_UPT_PRIVILEGE_OPERATION', 'System required protection element :DELETE_UPT_PRIVILEGE_OPERATION\'\n Do not change its unique object ID.', 'DELETE_UPT_PRIVILEGE_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:UPDATE_UPT_PRIVILEGE_OPERATION', 'System required protection element :UPDATE_UPT_PRIVILEGE_OPERATION\'\n Do not change its unique object ID.', 'UPDATE_UPT_PRIVILEGE_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:ACCESS_UPT_PROTECTION_ELEMENT_OPERATION', 'System required protection element :ACCESS_UPT_PROTECTION_ELEMENT_OPERATION\'\n Do not change its unique object ID.', 'ACCESS_UPT_PROTECTION_ELEMENT_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:CREATE_UPT_PROTECTION_ELEMENT_OPERATION', 'System required protection element :CREATE_UPT_PROTECTION_ELEMENT_OPERATION\'\n Do not change its unique object ID.', 'CREATE_UPT_PROTECTION_ELEMENT_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:DELETE_UPT_PROTECTION_ELEMENT_OPERATION', 'System required protection element :DELETE_UPT_PROTECTION_ELEMENT_OPERATION\'\n Do not change its unique object ID.', 'DELETE_UPT_PROTECTION_ELEMENT_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:UPDATE_UPT_PROTECTION_ELEMENT_OPERATION', 'System required protection element :UPDATE_UPT_PROTECTION_ELEMENT_OPERATION\'\n Do not change its unique object ID.', 'UPDATE_UPT_PROTECTION_ELEMENT_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:ACCESS_UPT_PROTECTION_GROUP_OPERATION', 'System required protection element :ACCESS_UPT_PROTECTION_GROUP_OPERATION\'\n Do not change its unique object ID.', 'ACCESS_UPT_PROTECTION_GROUP_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:CREATE_UPT_PROTECTION_GROUP_OPERATION', 'System required protection element :CREATE_UPT_PROTECTION_GROUP_OPERATION\'\n Do not change its unique object ID.', 'CREATE_UPT_PROTECTION_GROUP_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:DELETE_UPT_PROTECTION_GROUP_OPERATION', 'System required protection element :DELETE_UPT_PROTECTION_GROUP_OPERATION\'\n Do not change its unique object ID.', 'DELETE_UPT_PROTECTION_GROUP_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:UPDATE_UPT_PROTECTION_GROUP_OPERATION', 'System required protection element :UPDATE_UPT_PROTECTION_GROUP_OPERATION\'\n Do not change its unique object ID.', 'UPDATE_UPT_PROTECTION_GROUP_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:ACCESS_UPT_ROLE_OPERATION', 'System required protection element :ACCESS_UPT_ROLE_OPERATION\'\n Do not change its unique object ID.', 'ACCESS_UPT_ROLE_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:CREATE_UPT_ROLE_OPERATION', 'System required protection element :CREATE_UPT_ROLE_OPERATION\'\n Do not change its unique object ID.', 'CREATE_UPT_ROLE_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:DELETE_UPT_ROLE_OPERATION', 'System required protection element :DELETE_UPT_ROLE_OPERATION\'\n Do not change its unique object ID.', 'DELETE_UPT_ROLE_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:UPDATE_UPT_ROLE_OPERATION', 'System required protection element :UPDATE_UPT_ROLE_OPERATION\'\n Do not change its unique object ID.', 'UPDATE_UPT_ROLE_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:ACCESS_UPT_USER_OPERATION', 'System required protection element :ACCESS_UPT_USER_OPERATION\'\n Do not change its unique object ID.', 'ACCESS_UPT_USER_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:CREATE_UPT_USER_OPERATION', 'System required protection element :CREATE_UPT_USER_OPERATION\'\n Do not change its unique object ID.', 'CREATE_UPT_USER_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:DELETE_UPT_USER_OPERATION', 'System required protection element :DELETE_UPT_USER_OPERATION\'\n Do not change its unique object ID.', 'DELETE_UPT_USER_OPERATION', '', '', '', '2', '2012-12-20');
 INSERT INTO csm_protection_element (PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE) VALUES('Disable Flag for UPT Operation:UPDATE_UPT_USER_OPERATION', 'System required protection element :UPDATE_UPT_USER_OPERATION\'\n Do not change its unique object ID.', 'UPDATE_UPT_USER_OPERATION', '', '', '', '2', '2012-12-20');

COMMIT;
/*!40000 ALTER TABLE `CSM_PROTECTION_ELEMENT` ENABLE KEYS */;


--
-- Definition of table `CSM_PROTECTION_GROUP`
--

DROP TABLE IF EXISTS CSM_PROTECTION_GROUP;
CREATE TABLE CSM_PROTECTION_GROUP ( 
	PROTECTION_GROUP_ID BIGINT AUTO_INCREMENT  NOT NULL,
	PROTECTION_GROUP_NAME VARCHAR(100) NOT NULL,
	PROTECTION_GROUP_DESCRIPTION VARCHAR(200),
	APPLICATION_ID BIGINT NOT NULL,
	LARGE_ELEMENT_COUNT_FLAG BOOL NOT NULL,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PARENT_PROTECTION_GROUP_ID BIGINT,
	PRIMARY KEY(PROTECTION_GROUP_ID)
)Type=InnoDB
;

--
-- Dumping data for table `CSM_PROTECTION_GROUP`
--

/*!40000 ALTER TABLE `CSM_PROTECTION_GROUP` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_PROTECTION_GROUP` (`PROTECTION_GROUP_ID`,`PROTECTION_GROUP_NAME`,`PROTECTION_GROUP_DESCRIPTION`,`APPLICATION_ID`,`LARGE_ELEMENT_COUNT_FLAG`,`UPDATE_DATE`,`PARENT_PROTECTION_GROUP_ID`) VALUES 
 ('1','All PEs','Contains all of the PEs in the entire test sdk system','2',0,'2007-12-19 00:00:00',NULL),
 ('2','Bank',NULL,'2',0,'2007-03-01 00:00:00',NULL),
 ('3','AllDataType',NULL,'2',0,'2007-03-23 00:00:00',NULL),
 ('4','Book',NULL,'2',0,'2007-03-23 00:00:00',NULL),
 ('7','Limited Access','JUnit Security Test Group with limited access to a handful of Classes and Attributes','2',0,'2007-12-19 00:00:00',NULL);
COMMIT;
/*!40000 ALTER TABLE `CSM_PROTECTION_GROUP` ENABLE KEYS */;


--
-- Definition of table `CSM_ROLE`
--

DROP TABLE IF EXISTS CSM_ROLE;
CREATE TABLE CSM_ROLE ( 
	ROLE_ID BIGINT AUTO_INCREMENT  NOT NULL,
	ROLE_NAME VARCHAR(100) NOT NULL,
	ROLE_DESCRIPTION VARCHAR(200),
	APPLICATION_ID BIGINT NOT NULL,
	ACTIVE_FLAG BOOL NOT NULL,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PRIMARY KEY(ROLE_ID)
)Type=InnoDB
;

--
-- Dumping data for table `CSM_ROLE`
--

/*!40000 ALTER TABLE `CSM_ROLE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_ROLE` (`ROLE_ID`,`ROLE_NAME`,`ROLE_DESCRIPTION`,`APPLICATION_ID`,`ACTIVE_FLAG`,`UPDATE_DATE`) VALUES 
 ('1','SuperAdmin','SuperAdmin','2',1,'2007-03-01 00:00:00'),
 ('2','Read',NULL,'2',1,'2007-03-01 00:00:00'),
 ('3','Create',NULL,'2',1,'2007-03-23 00:00:00'),
 ('5','Update',NULL,'2',1,'2008-06-30 00:00:00'),
 ('6','Delete',NULL,'2',1,'2008-06-30 00:00:00');
COMMIT;
/*!40000 ALTER TABLE `CSM_ROLE` ENABLE KEYS */;


--
-- Definition of table `CSM_ROLE_PRIVILEGE`
--

DROP TABLE IF EXISTS CSM_ROLE_PRIVILEGE;
CREATE TABLE CSM_ROLE_PRIVILEGE ( 
	ROLE_PRIVILEGE_ID BIGINT AUTO_INCREMENT  NOT NULL,
	ROLE_ID BIGINT NOT NULL,
	PRIVILEGE_ID BIGINT NOT NULL,
	PRIMARY KEY(ROLE_PRIVILEGE_ID)
)Type=InnoDB
;

--
-- Dumping data for table `CSM_ROLE_PRIVILEGE`
--

/*!40000 ALTER TABLE `CSM_ROLE_PRIVILEGE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_ROLE_PRIVILEGE` (`ROLE_PRIVILEGE_ID`,`ROLE_ID`,`PRIVILEGE_ID`) VALUES 
 ('1','2','3'),
 ('2','1','5'),
 ('3','1','1'),
 ('4','1','2'),
 ('5','1','7'),
 ('6','1','4'),
 ('7','1','3'),
 ('8','1','6'),
 ('9','3','1'),
 ('14','5','5'),
 ('15','6','6');
COMMIT;
/*!40000 ALTER TABLE `CSM_ROLE_PRIVILEGE` ENABLE KEYS */;


--
-- Definition of table `CSM_USER`
--

CREATE TABLE `CSM_USER` (
  `USER_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `LOGIN_NAME` VARCHAR(500) NOT NULL,
  `MIGRATED_FLAG` TINYINT(1) NOT NULL DEFAULT '0',
  `FIRST_NAME` VARCHAR(100) NOT NULL,
  `LAST_NAME` VARCHAR(100) NOT NULL,
  `ORGANIZATION` VARCHAR(100) DEFAULT NULL,
  `DEPARTMENT` VARCHAR(100) DEFAULT NULL,
  `TITLE` VARCHAR(100) DEFAULT NULL,
  `PHONE_NUMBER` VARCHAR(100) DEFAULT NULL,
  `PASSWORD` VARCHAR(100) DEFAULT NULL,
  `EMAIL_ID` VARCHAR(100) DEFAULT NULL,
  `START_DATE` DATE DEFAULT NULL,
  `END_DATE` DATE DEFAULT NULL,
  `UPDATE_DATE` DATE NOT NULL DEFAULT '0000-00-00',
  `PREMGRT_LOGIN_NAME` VARCHAR(100) DEFAULT NULL,
  `PASSWORD_EXPIRED` TINYINT (1),
  `PASSWORD_EXPIRY_DATE` DATE ,
  `FIRST_TIME_LOGIN` TINYINT (1),
  `ACTIVE_FLAG` TINYINT (1),
  PRIMARY KEY (`USER_ID`)
) ENGINE=INNODB;

--
-- Dumping data for table `CSM_USER`
--

/*!40000 ALTER TABLE `CSM_USER` DISABLE KEYS */;
/* SuperAdmin password is set to 'changeme'.  The user1 and user2 passwords are set to 'Psat123!@#'. */
SET AUTOCOMMIT=0;
INSERT INTO `CSM_USER` (`USER_ID`,`LOGIN_NAME`,`FIRST_NAME`,`LAST_NAME`,`ORGANIZATION`,`department`,`TITLE`,`PHONE_NUMBER`,`PASSWORD`,`EMAIL_ID`,`START_DATE`,`END_DATE`,`UPDATE_DATE`,`PASSWORD_EXPIRY_DATE`,`FIRST_TIME_LOGIN`,`ACTIVE_FLAG`) VALUES 
 ('1','SuperAdmin','WK6wimYzUFEcI4vrtVIx1w==','+D38r/6A86uT+7lB5IAnJw==',NULL,NULL,NULL,NULL,'5kJqWYBdWCphljGP2pGUGg==',NULL,NULL,NULL,'2008-05-23 00:00:00','2013-06-30', 0,1),
 ('2','SDKUser1','bEDgOE7ULCxMjJUsj1ISEA==','OmJbo9Yje3ndG4vE8b6fNw==',NULL,NULL,NULL,NULL,'rTHomvIHBO4OngJL8j5Ayw==',NULL,NULL,NULL,'2008-06-30 00:00:00','2013-06-30', 0,1),
 ('3','SDKUser2','s3nhzJSP2buLJN8TKCBqHw==','OmJbo9Yje3ndG4vE8b6fNw==',NULL,NULL,NULL,NULL,'rTHomvIHBO4OngJL8j5Ayw==',NULL,NULL,NULL,'2008-06-30 00:00:00','2013-06-30', 0,1),
 ('13','/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=SDKUser1','bEDgOE7ULCxMjJUsj1ISEA==','rTHomvIHBO4OngJL8j5Ayw==',NULL,NULL,NULL,NULL,'rTHomvIHBO4OngJL8j5Ayw==',NULL,NULL,NULL,'2008-06-30 00:00:00', '2013-06-30', 0,1),
 ('14','/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=SDKUser2','s3nhzJSP2buLJN8TKCBqHw==','rTHomvIHBO4OngJL8j5Ayw==',NULL,NULL,NULL,NULL,'rTHomvIHBO4OngJL8j5Ayw==',NULL,NULL,NULL,'2008-06-30 00:00:00', '2013-06-30', 0,1);
COMMIT;
/*!40000 ALTER TABLE `CSM_USER` ENABLE KEYS */;


--
-- Definition of table `CSM_USER_GROUP`
--

DROP TABLE IF EXISTS CSM_USER_GROUP;
CREATE TABLE CSM_USER_GROUP ( 
	USER_GROUP_ID BIGINT AUTO_INCREMENT  NOT NULL,
	USER_ID BIGINT NOT NULL,
	GROUP_ID BIGINT NOT NULL,
	PRIMARY KEY(USER_GROUP_ID)
)Type=InnoDB
;

--
-- Dumping data for table `CSM_USER_GROUP`
--

/*!40000 ALTER TABLE `CSM_USER_GROUP` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_USER_GROUP` (`USER_GROUP_ID`,`USER_ID`,`GROUP_ID`) VALUES 
 ('2','13','2'),
 ('3','14','3'),
 ('4','2','2'),
 ('5','3','3');
COMMIT;
/*!40000 ALTER TABLE `CSM_USER_GROUP` ENABLE KEYS */;


--
-- Definition of table `CSM_USER_GROUP_ROLE_PG`
--

DROP TABLE IF EXISTS CSM_USER_GROUP_ROLE_PG;
CREATE TABLE CSM_USER_GROUP_ROLE_PG ( 
	USER_GROUP_ROLE_PG_ID BIGINT AUTO_INCREMENT  NOT NULL,
	USER_ID BIGINT,
	GROUP_ID BIGINT,
	ROLE_ID BIGINT NOT NULL,
	PROTECTION_GROUP_ID BIGINT NOT NULL,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PRIMARY KEY(USER_GROUP_ROLE_PG_ID)
)Type=InnoDB
;

/*!40000 ALTER TABLE `CSM_USER_GROUP_ROLE_PG` ENABLE KEYS */;


--
-- Definition of table `CSM_USER_PE`
--

DROP TABLE IF EXISTS CSM_USER_PE;
CREATE TABLE CSM_USER_PE ( 
	USER_PROTECTION_ELEMENT_ID BIGINT AUTO_INCREMENT  NOT NULL,
	PROTECTION_ELEMENT_ID BIGINT NOT NULL,
	USER_ID BIGINT NOT NULL,
	PRIMARY KEY(USER_PROTECTION_ELEMENT_ID)
)Type=InnoDB
;

--
-- Dumping data for table `CSM_USER_PE`
--

/*!40000 ALTER TABLE `CSM_USER_PE` ENABLE KEYS */;


/* Begin - CSM 4.1 to CSM 4.2 Upgrade Script */

CREATE TABLE IF NOT EXISTS CSM_MAPPING (
  MAPPING_ID bigint(20) NOT NULL auto_increment,
  APPLICATION_ID bigint(20) NOT NULL,
  OBJECT_NAME varchar(100) NOT NULL,
  ATTRIBUTE_NAME varchar(100) NOT NULL,
  OBJECT_PACKAGE_NAME varchar(100),
  TABLE_NAME varchar(100),
  TABLE_NAME_GROUP varchar(100),
  TABLE_NAME_USER varchar(100),
  VIEW_NAME_GROUP varchar(100),
  VIEW_NAME_USER varchar(100),
  ACTIVE_FLAG tinyint(1) NOT NULL default '0',
  MAINTAINED_FLAG tinyint(1) NOT NULL default '0',	
  UPDATE_DATE date default '0000-00-00',
  PRIMARY KEY(MAPPING_ID)
)Type=InnoDB
;


CREATE TABLE CSM_PASSWORD_HISTORY (
	CSM_PASSWORD_HISTORY_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	LOGIN_NAME VARCHAR (500),
	PASSWORD VARCHAR (100),
	PRIMARY KEY(CSM_PASSWORD_HISTORY_ID)
)Type=InnoDB
;


CREATE TABLE CSM_CONFIGURATION_PROPS (
  PROPERTY_KEY VARCHAR(300) NOT NULL,
  PROPERTY_VALUE VARCHAR(3000) DEFAULT NULL,
  PRIMARY KEY (PROPERTY_KEY)
) ENGINE=InnoDB 
;


ALTER TABLE CSM_MAPPING ADD CONSTRAINT FK_PE_APPLICATION 
FOREIGN KEY FK_PE_APPLICATION (APPLICATION_ID) REFERENCES csm_application (APPLICATION_ID) ON DELETE CASCADE
;

ALTER TABLE CSM_MAPPING
ADD CONSTRAINT UQ_MP_OBJ_NAME_ATTRI_NAME_APP_ID UNIQUE (OBJECT_NAME,ATTRIBUTE_NAME,APPLICATION_ID)
;


ALTER TABLE CSM_APPLICATION
ADD CONSTRAINT UQ_APPLICATION_NAME UNIQUE (APPLICATION_NAME)
;

CREATE INDEX idx_CSM_PASSWORD_HISTORY_ID ON CSM_PASSWORD_HISTORY(CSM_PASSWORD_HISTORY_ID)
;

CREATE INDEX idx_APPLICATION_ID ON CSM_GROUP(APPLICATION_ID)
;

ALTER TABLE CSM_GROUP
ADD CONSTRAINT UQ_GROUP_GROUP_NAME UNIQUE (APPLICATION_ID, GROUP_NAME)
;
ALTER TABLE CSM_PRIVILEGE
ADD CONSTRAINT UQ_PRIVILEGE_NAME UNIQUE (PRIVILEGE_NAME)
;
CREATE INDEX idx_APPLICATION_ID ON CSM_PROTECTION_ELEMENT(APPLICATION_ID)
;
CREATE INDEX idx_OBJ_ATTR_APP ON CSM_PROTECTION_ELEMENT(OBJECT_ID, ATTRIBUTE, APPLICATION_ID) 
;
ALTER TABLE CSM_PROTECTION_ELEMENT
ADD CONSTRAINT UQ_PE_PE_NAME_ATTRIBUTE_VALUE_APP_ID UNIQUE (OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, APPLICATION_ID)
;
CREATE INDEX idx_APPLICATION_ID ON CSM_PROTECTION_GROUP(APPLICATION_ID)
;
ALTER TABLE CSM_PROTECTION_GROUP
ADD CONSTRAINT UQ_PROTECTION_GROUP_PROTECTION_GROUP_NAME UNIQUE (APPLICATION_ID, PROTECTION_GROUP_NAME)
;
CREATE INDEX idx_PARENT_PROTECTION_GROUP_ID ON CSM_PROTECTION_GROUP(PARENT_PROTECTION_GROUP_ID)
;
CREATE INDEX idx_PROTECTION_ELEMENT_ID ON CSM_PG_PE(PROTECTION_ELEMENT_ID)
;
CREATE INDEX idx_PROTECTION_GROUP_ID ON CSM_PG_PE(PROTECTION_GROUP_ID)
;
ALTER TABLE CSM_PG_PE
ADD CONSTRAINT UQ_PROTECTION_GROUP_PROTECTION_ELEMENT_PROTECTION_GROUP_ID UNIQUE (PROTECTION_ELEMENT_ID, PROTECTION_GROUP_ID)
;
CREATE INDEX idx_APPLICATION_ID ON CSM_ROLE(APPLICATION_ID)
;
ALTER TABLE CSM_ROLE
ADD CONSTRAINT UQ_ROLE_ROLE_NAME UNIQUE (APPLICATION_ID, ROLE_NAME)
;
CREATE INDEX idx_PRIVILEGE_ID ON CSM_ROLE_PRIVILEGE(PRIVILEGE_ID)
;
ALTER TABLE CSM_ROLE_PRIVILEGE
ADD CONSTRAINT UQ_ROLE_PRIVILEGE_ROLE_ID UNIQUE (PRIVILEGE_ID, ROLE_ID)
;
CREATE INDEX idx_ROLE_ID ON CSM_ROLE_PRIVILEGE(ROLE_ID)
;

ALTER TABLE CSM_USER
ADD CONSTRAINT UQ_LOGIN_NAME UNIQUE (LOGIN_NAME)
;
CREATE INDEX idx_USER_ID ON CSM_USER_GROUP(USER_ID)
;
CREATE INDEX idx_GROUP_ID ON CSM_USER_GROUP(GROUP_ID)
;
CREATE INDEX idx_GROUP_ID ON CSM_USER_GROUP_ROLE_PG(GROUP_ID)
;
CREATE INDEX idx_ROLE_ID ON CSM_USER_GROUP_ROLE_PG(ROLE_ID)
;
CREATE INDEX idx_PROTECTION_GROUP_ID ON CSM_USER_GROUP_ROLE_PG(PROTECTION_GROUP_ID)
;
CREATE INDEX idx_USER_ID ON CSM_USER_GROUP_ROLE_PG(USER_ID)
;
CREATE INDEX idx_USER_ID ON CSM_USER_PE(USER_ID)
;
CREATE INDEX idx_PROTECTION_ELEMENT_ID ON CSM_USER_PE(PROTECTION_ELEMENT_ID)
;
ALTER TABLE CSM_USER_PE
ADD CONSTRAINT UQ_USER_PROTECTION_ELEMENT_PROTECTION_ELEMENT_ID UNIQUE (USER_ID, PROTECTION_ELEMENT_ID)
;


ALTER TABLE CSM_PASSWORD_HISTORY ADD CONSTRAINT FK_PWD_LOGIN_NAME 
FOREIGN KEY (LOGIN_NAME) REFERENCES CSM_USER (LOGIN_NAME)
ON DELETE CASCADE
;

ALTER TABLE CSM_GROUP ADD CONSTRAINT FK_APPLICATION_GROUP 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_FILTER_CLAUSE ADD CONSTRAINT FK_APPLICATION_FILTER_CLAUSE 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

#ALTER TABLE CSM_PROTECTION_ELEMENT ADD CONSTRAINT FK_PE_APPLICATION 
#FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
#ON DELETE CASCADE
#;

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT FK_PG_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT FK_PROTECTION_GROUP 
FOREIGN KEY (PARENT_PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
;

ALTER TABLE CSM_PG_PE ADD CONSTRAINT FK_PROTECTION_ELEMENT_PROTECTION_GROUP 
FOREIGN KEY (PROTECTION_ELEMENT_ID) REFERENCES CSM_PROTECTION_ELEMENT (PROTECTION_ELEMENT_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PG_PE ADD CONSTRAINT FK_PROTECTION_GROUP_PROTECTION_ELEMENT 
FOREIGN KEY (PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_ROLE ADD CONSTRAINT FK_APPLICATION_ROLE 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT FK_PRIVILEGE_ROLE 
FOREIGN KEY (PRIVILEGE_ID) REFERENCES CSM_PRIVILEGE (PRIVILEGE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT FK_ROLE
FOREIGN KEY (ROLE_ID) REFERENCES CSM_ROLE (ROLE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT FK_USER_GROUP 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT FK_UG_GROUP 
FOREIGN KEY (GROUP_ID) REFERENCES CSM_GROUP (GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PROTECTION_GROUP_GROUPS 
FOREIGN KEY (GROUP_ID) REFERENCES CSM_GROUP (GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PROTECTION_GROUP_ROLE 
FOREIGN KEY (ROLE_ID) REFERENCES CSM_ROLE (ROLE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PROTECTION_GROUP_PROTECTION_GROUP 
FOREIGN KEY (PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_PE ADD CONSTRAINT FK_PE_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_PE ADD CONSTRAINT FK_PROTECTION_ELEMENT_USER 
FOREIGN KEY (PROTECTION_ELEMENT_ID) REFERENCES CSM_PROTECTION_ELEMENT (PROTECTION_ELEMENT_ID)
ON DELETE CASCADE
;

/*!40000 ALTER TABLE `CSM_USER_PE` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_USER_PE` (`USER_PROTECTION_ELEMENT_ID`,`PROTECTION_ELEMENT_ID`,`USER_ID`) VALUES 
 ('3','1','1'),
 ('7','2','2'),
 ('8','2','1');
COMMIT;

--
-- Dumping data for table `CSM_USER_GROUP_ROLE_PG`
--

/*!40000 ALTER TABLE `CSM_USER_GROUP_ROLE_PG` DISABLE KEYS */;
SET AUTOCOMMIT=0;
INSERT INTO `CSM_USER_GROUP_ROLE_PG` (`USER_GROUP_ROLE_PG_ID`,`USER_ID`,`GROUP_ID`,`ROLE_ID`,`PROTECTION_GROUP_ID`,`UPDATE_DATE`) VALUES 
 ('1','1',NULL,'1','1','2007-03-01 00:00:00'),
 ('10','2',NULL,'1','1','2007-03-23 00:00:00'),
 ('16','12',NULL,'1','1','2007-12-12 00:00:00'),
 ('17','13',NULL,'3','1','2007-12-19 00:00:00'),
 ('18','13',NULL,'2','1','2007-12-19 00:00:00'),
 ('19','2',NULL,'3','1','2007-12-19 00:00:00'),
 ('20','2',NULL,'2','1','2007-12-19 00:00:00'),
 ('23',NULL,'2','3','1','2008-06-30 00:00:00'),
 ('24',NULL,'2','2','1','2008-06-30 00:00:00'),
 ('30','2',NULL,'6','1','2008-06-30 00:00:00'),
 ('31','2',NULL,'5','1','2008-06-30 00:00:00'),
 ('32','3',NULL,'3','7','2008-06-30 00:00:00'),
 ('33','3',NULL,'2','7','2008-06-30 00:00:00'),
 ('34','13',NULL,'6','1','2008-06-30 00:00:00'),
 ('35','13',NULL,'5','1','2008-06-30 00:00:00'),
 ('38','14',NULL,'3','7','2008-06-30 00:00:00'),
 ('39','14',NULL,'2','7','2008-06-30 00:00:00'),
 ('40',NULL,'3','3','7','2008-06-30 00:00:00'),
 ('41',NULL,'3','2','7','2008-06-30 00:00:00');
COMMIT;


INSERT CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES
(8, 'UPT_UI_USERS_LINK', 'Protection Group representing User link in UPT. Do not change the name.', 1, 0, sysdate());

INSERT CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES
(9, 'UPT_UI_PROTECTION_ELEMENTS_LINK', 'Protection Group representing Protection Element link in UPT. Do not change the name.', 1, 0, sysdate());

INSERT CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES
(10, 'UPT_UI_PRIVILEGES_LINK', 'Protection Group representing Privilege link in UPT. Do not change the name.', 1, 0, sysdate());

INSERT CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES
(11, 'UPT_UI_GROUPS_LINK', 'Protection Group representing Group link in UPT. Do not change the name.', 1, 0, sysdate());

INSERT CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES
(12, 'UPT_UI_PROTECTION_GROUPS_LINK', 'Protection Group representing Protection Group link in UPT. Do not change the name.', 1, 0, sysdate());

INSERT CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES
(13, 'UPT_UI_ROLE_LINK', 'Protection Group representing Role link in UPT. Do not change the name.', 1, 0, sysdate());

INSERT CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES
(14, 'UPT_UI_INSTANCE_LEVEL_LINK', 'Protection Group representing Instance Level link in UPT. Do not change the name.', 1, 0, sysdate());

INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('AES_ENCRYPTION_KEY','super secret');
INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('ALLOWED_ATTEMPTS','3');
INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('ALLOWED_LOGIN_TIME','600000');
INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('MD5_HASH_KEY','true');
INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('PASSWORD_EXPIRY_DAYS','60');
INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('PASSWORD_LOCKOUT_TIME','1800000');
INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('PASSWORD_MATCH_NUM','24');
INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('PASSWORD_PATTERN_MATCH','^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$');
INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('PASSWORD_PATTERN_DESCRIPTION','At least one Upper case alphabet, at least one lower case alphabet, at least one number and minimum 8 characters length');

COMMIT;
/* End - CSM 4.1 to CSM 4.2 Upgrade script */

