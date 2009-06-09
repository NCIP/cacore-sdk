DROP SEQUENCE CSM_APPLICATI_APPLICATION__SEQ
/

DROP TABLE CSM_APPLICATION CASCADE CONSTRAINTS
/

DROP SEQUENCE CSM_GROUP_GROUP_ID_SEQ
/

DROP TABLE CSM_GROUP CASCADE CONSTRAINTS

/

DROP TRIGGER SET_CSM_PG_PE_PG_PE_ID
/

DROP TRIGGER SET_CSM_PG_PE_UPDATE_DATE
/

DROP SEQUENCE CSM_PG_PE_PG_PE_ID_SEQ
/

DROP TABLE CSM_PG_PE CASCADE CONSTRAINTS
/

DROP SEQUENCE CSM_PRIVILEGE_PRIVILEGE_ID_SEQ
/

DROP TABLE CSM_PRIVILEGE CASCADE CONSTRAINTS
/

DROP SEQUENCE CSM_FILTER_CLAUSE_FILTE_ID_SEQ
/

DROP TABLE CSM_FILTER_CLAUSE CASCADE CONSTRAINTS
/

DROP SEQUENCE CSM_PROTECTIO_PROTECTION_E_SEQ
/

DROP TABLE CSM_PROTECTION_ELEMENT CASCADE CONSTRAINTS
/

DROP SEQUENCE CSM_PROTECTIO_PROTECTION_G_SEQ
/

DROP TABLE CSM_PROTECTION_GROUP CASCADE CONSTRAINTS
/

DROP SEQUENCE CSM_ROLE_ROLE_ID_SEQ
/

DROP TABLE CSM_ROLE CASCADE CONSTRAINTS
/

DROP TRIGGER SET_CSM_ROLE_PRIV_ROLE_PRIVILE
/

DROP SEQUENCE CSM_ROLE_PRIV_ROLE_PRIVILE_SEQ
/

DROP TABLE CSM_ROLE_PRIVILEGE CASCADE CONSTRAINTS
/

DROP SEQUENCE CSM_USER_USER_ID_SEQ
/

DROP TABLE CSM_USER CASCADE CONSTRAINTS
/

DROP TRIGGER SET_CSM_USER_GROU_USER_GROUP_I
/

DROP SEQUENCE CSM_USER_GROU_USER_GROUP_I_SEQ
/

DROP TABLE CSM_USER_GROUP CASCADE CONSTRAINTS
/

DROP SEQUENCE CSM_USER_GROU_USER_GROUP_R_SEQ
/

DROP TABLE CSM_USER_GROUP_ROLE_PG CASCADE CONSTRAINTS
/

DROP TRIGGER SET_CSM_USER_PE_USER_PROTECTIO
/

DROP SEQUENCE CSM_USER_PE_USER_PROTECTIO_SEQ
/

DROP TABLE CSM_USER_PE CASCADE CONSTRAINTS
/

CREATE TABLE CSM_APPLICATION ( 
	APPLICATION_ID NUMBER(38) NOT NULL,
	APPLICATION_NAME VARCHAR2(255) NOT NULL,
	APPLICATION_DESCRIPTION VARCHAR2(200) NOT NULL,
	DECLARATIVE_FLAG NUMBER(1) NOT NULL,
	ACTIVE_FLAG NUMBER(1) DEFAULT '0' NOT NULL ,
	UPDATE_DATE DATE NOT NULL,
	DATABASE_URL VARCHAR2(100),
	DATABASE_USER_NAME VARCHAR2(100),
	DATABASE_PASSWORD VARCHAR2(100),
	DATABASE_DIALECT VARCHAR2(100),
	DATABASE_DRIVER VARCHAR2(100)
) 
/

CREATE SEQUENCE CSM_APPLICATI_APPLICATION__SEQ
increment by 1
start with 3
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE TABLE CSM_GROUP ( 
	GROUP_ID NUMBER(38) NOT NULL,
	GROUP_NAME VARCHAR2(255) NOT NULL,
	GROUP_DESC VARCHAR2(200),
	UPDATE_DATE DATE NOT NULL,
	APPLICATION_ID NUMBER(38) NOT NULL
) 
/

CREATE SEQUENCE CSM_GROUP_GROUP_ID_SEQ
increment by 1
start with 5
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/


CREATE TABLE CSM_PG_PE ( 
	PG_PE_ID NUMBER(38) NOT NULL,
	PROTECTION_GROUP_ID NUMBER(38) NOT NULL,
	PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE 
) 
/

CREATE SEQUENCE CSM_PG_PE_PG_PE_ID_SEQ
increment by 1
start with 780
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE OR REPLACE TRIGGER SET_CSM_PG_PE_PG_PE_ID
BEFORE INSERT
ON CSM_PG_PE
FOR EACH ROW
BEGIN
  SELECT CSM_PG_PE_PG_PE_ID_SEQ.NEXTVAL
  INTO :NEW.PG_PE_ID
  FROM DUAL;
END;
/
COMMIT
/

CREATE OR REPLACE TRIGGER SET_CSM_PG_PE_UPDATE_DATE
BEFORE UPDATE
ON CSM_PG_PE
FOR EACH ROW
BEGIN
  SELECT SYSDATE
  INTO :NEW.UPDATE_DATE
  FROM DUAL;
END;
/
COMMIT
/

CREATE TABLE CSM_PRIVILEGE ( 
	PRIVILEGE_ID NUMBER(38) NOT NULL,
	PRIVILEGE_NAME VARCHAR2(100) NOT NULL,
	PRIVILEGE_DESCRIPTION VARCHAR2(200),
	UPDATE_DATE DATE NOT NULL
) 

/
CREATE SEQUENCE CSM_PRIVILEGE_PRIVILEGE_ID_SEQ
increment by 1
start with 8
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE TABLE CSM_FILTER_CLAUSE ( 
	FILTER_CLAUSE_ID NUMBER(38) NOT NULL,
	CLASS_NAME VARCHAR2(100) NOT NULL,
	FILTER_CHAIN VARCHAR2(2000) NOT NULL,
	TARGET_CLASS_NAME VARCHAR2 (100) NOT NULL,
	TARGET_CLASS_ATTRIBUTE_NAME VARCHAR2 (100) NOT NULL,
	TARGET_CLASS_ATTRIBUTE_TYPE VARCHAR2 (100) NOT NULL,
	TARGET_CLASS_ALIAS VARCHAR2 (100),
	TARGET_CLASS_ATTRIBUTE_ALIAS VARCHAR2 (100),
	GENERATED_SQL_USER VARCHAR (4000) NOT NULL,
	GENERATED_SQL_GROUP VARCHAR (4000) NOT NULL,
	APPLICATION_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE NOT NULL	
) 
/

CREATE SEQUENCE CSM_FILTER_CLAUSE_FILTE_ID_SEQ
increment by 1
start with 21
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/


CREATE TABLE CSM_PROTECTION_ELEMENT ( 
	PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	PROTECTION_ELEMENT_NAME VARCHAR2(100) NOT NULL,
	PROTECTION_ELEMENT_DESCRIPTION VARCHAR2(200),
	OBJECT_ID VARCHAR2(100) NOT NULL,
	ATTRIBUTE VARCHAR2(100),
	ATTRIBUTE_VALUE VARCHAR2(100),
	PROTECTION_ELEMENT_TYPE VARCHAR2(100),
	APPLICATION_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE NOT NULL
) 
/

CREATE SEQUENCE CSM_PROTECTIO_PROTECTION_E_SEQ
increment by 1
start with 116
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE TABLE CSM_PROTECTION_GROUP ( 
	PROTECTION_GROUP_ID NUMBER(38) NOT NULL,
	PROTECTION_GROUP_NAME VARCHAR2(100) NOT NULL,
	PROTECTION_GROUP_DESCRIPTION VARCHAR2(200),
	APPLICATION_ID NUMBER(38) NOT NULL,
	LARGE_ELEMENT_COUNT_FLAG NUMBER(1) NOT NULL,
	UPDATE_DATE DATE NOT NULL,
	PARENT_PROTECTION_GROUP_ID NUMBER(38)
) 
/

CREATE SEQUENCE CSM_PROTECTIO_PROTECTION_G_SEQ
increment by 1
start with 8
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE TABLE CSM_ROLE ( 
	ROLE_ID NUMBER(38) NOT NULL,
	ROLE_NAME VARCHAR2(100) NOT NULL,
	ROLE_DESCRIPTION VARCHAR2(200),
	APPLICATION_ID NUMBER(38) NOT NULL,
	ACTIVE_FLAG NUMBER(1) NOT NULL,
	UPDATE_DATE DATE NOT NULL
)
/

CREATE SEQUENCE CSM_ROLE_ROLE_ID_SEQ
increment by 1
start with 7
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE TABLE CSM_ROLE_PRIVILEGE ( 
	ROLE_PRIVILEGE_ID NUMBER(38) NOT NULL,
	ROLE_ID NUMBER(38) NOT NULL,
	PRIVILEGE_ID NUMBER(38) NOT NULL
)
/

CREATE SEQUENCE CSM_ROLE_PRIV_ROLE_PRIVILE_SEQ
increment by 1
start with 16
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE OR REPLACE TRIGGER SET_CSM_ROLE_PRIV_ROLE_PRIVILE
BEFORE INSERT
ON CSM_ROLE_PRIVILEGE
FOR EACH ROW
BEGIN
  SELECT CSM_ROLE_PRIV_ROLE_PRIVILE_SEQ.NEXTVAL
  INTO :NEW.ROLE_PRIVILEGE_ID
  FROM DUAL;
END;
/
COMMIT
/

CREATE TABLE CSM_USER ( 
	USER_ID NUMBER(38) NOT NULL,
	LOGIN_NAME VARCHAR2(500) NOT NULL,
	FIRST_NAME VARCHAR2(100) NOT NULL,
	MIGRATED_FLAG NUMBER(1) DEFAULT '0' NOT NULL ,
	LAST_NAME VARCHAR2(100) NOT NULL,
	ORGANIZATION VARCHAR2(100),
	DEPARTMENT VARCHAR2(100),
	TITLE VARCHAR2(100),
	PHONE_NUMBER VARCHAR2(15),
	PASSWORD VARCHAR2(100),
	EMAIL_ID VARCHAR2(100),
	START_DATE DATE,
	END_DATE DATE,
	UPDATE_DATE DATE NOT NULL,
	PREMGRT_LOGIN_NAME VARCHAR2(100)
)
/

CREATE SEQUENCE CSM_USER_USER_ID_SEQ
increment by 1
start with 18
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE TABLE CSM_USER_GROUP ( 
	USER_GROUP_ID NUMBER(38) NOT NULL,
	USER_ID NUMBER(38) NOT NULL,
	GROUP_ID NUMBER(38) NOT NULL
) 

/
CREATE SEQUENCE CSM_USER_GROU_USER_GROUP_I_SEQ
increment by 1
start with 7
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE OR REPLACE TRIGGER SET_CSM_USER_GROU_USER_GROUP_I
BEFORE INSERT
ON CSM_USER_GROUP
FOR EACH ROW
BEGIN
  SELECT CSM_USER_GROU_USER_GROUP_I_SEQ.NEXTVAL
  INTO :NEW.USER_GROUP_ID
  FROM DUAL;
END;
/
COMMIT
/

CREATE TABLE CSM_USER_GROUP_ROLE_PG ( 
	USER_GROUP_ROLE_PG_ID NUMBER(38) NOT NULL,
	USER_ID NUMBER(38),
	GROUP_ID NUMBER(38),
	ROLE_ID NUMBER(38) NOT NULL,
	PROTECTION_GROUP_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE NOT NULL
) 

/
CREATE SEQUENCE CSM_USER_GROU_USER_GROUP_R_SEQ
increment by 1
start with 49
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE TABLE CSM_USER_PE ( 
	USER_PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	USER_ID NUMBER(38) NOT NULL
) 

/
CREATE SEQUENCE CSM_USER_PE_USER_PROTECTIO_SEQ
increment by 1
start with 9
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder
/

CREATE OR REPLACE TRIGGER SET_CSM_USER_PE_USER_PROTECTIO
BEFORE INSERT
ON CSM_USER_PE
FOR EACH ROW
BEGIN
  SELECT CSM_USER_PE_USER_PROTECTIO_SEQ.NEXTVAL
  INTO :NEW.USER_PROTECTION_ELEMENT_ID
  FROM DUAL;
END;
/
commit
/


INSERT INTO CSM_APPLICATION ( APPLICATION_ID, APPLICATION_NAME, APPLICATION_DESCRIPTION
, DECLARATIVE_FLAG, ACTIVE_FLAG, UPDATE_DATE ) VALUES ( 
1, 'csmupt', 'UPT Super Admin Application', 0, 0,  TO_Date( '02/28/2007 01:03:02 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_APPLICATION ( APPLICATION_ID, APPLICATION_NAME, APPLICATION_DESCRIPTION
, DECLARATIVE_FLAG, ACTIVE_FLAG, UPDATE_DATE, DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD
, DATABASE_DIALECT, DATABASE_DRIVER ) VALUES ( 
2, 'sdk', 'sdk', 1, 1,  TO_Date( '04/02/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 'jdbc:oracle:thin:@cbiodb30.nci.nih.gov:1521:CBTEST', 'CACORESDK_ANT', 'mNIHCvHxv3w='
, 'org.hibernate.dialect.Oracle9Dialect', 'oracle.jdbc.driver.OracleDriver')
/
commit
/
 
INSERT INTO CSM_FILTER_CLAUSE ( FILTER_CLAUSE_ID, CLASS_NAME, FILTER_CHAIN, TARGET_CLASS_NAME
, TARGET_CLASS_ATTRIBUTE_NAME, TARGET_CLASS_ATTRIBUTE_TYPE, GENERATED_SQL_USER, APPLICATION_ID
, UPDATE_DATE, GENERATED_SQL_GROUP ) VALUES ( 
20, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck', 'suitCollection, cardCollection'
, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card - cardCollection', 'Name'
, 'java.lang.String', 'ID in (select table_name_csm_.ID   from DECK table_name_csm_, SUIT suit1_, CARD card2_ where table_name_csm_.ID=suit1_.DECK_ID and suit1_.ID=card2_.SUIT_ID and card2_.NAME in ( select pe.attribute_value from csm_protection_group pg, csm_protection_element pe, csm_pg_pe pgpe, csm_user_group_role_pg ugrpg, csm_user u, csm_role_privilege rp, csm_role r, csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.user_id = u.user_id and ugrpg.protection_group_id = ANY (select pg1.protection_group_id from csm_protection_group pg1 where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id = (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id)) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Card'' and pe.attribute=''Name'' and p.privilege_name=''READ'' and u.login_name=:USER_NAME and pe.application_id=:APPLICATION_ID))'
, 2,  TO_Date( '07/09/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'), 'ID in (select table_name_csm_.ID   from DECK table_name_csm_, SUIT suit1_, CARD card2_ where table_name_csm_.ID=suit1_.DECK_ID and suit1_.ID=card2_.SUIT_ID and card2_.NAME in (SELECT Distinct pe.attribute_value FROM CSM_PROTECTION_GROUP pg, 	CSM_PROTECTION_ELEMENT pe, 	CSM_PG_PE pgpe,	CSM_USER_GROUP_ROLE_PG ugrpg, 	CSM_GROUP g, 	CSM_ROLE_PRIVILEGE rp, 	CSM_ROLE r, 	CSM_PRIVILEGE p WHERE ugrpg.role_id = r.role_id AND ugrpg.group_id = g.group_id AND ugrpg.protection_group_id = ANY ( select pg1.protection_group_id from csm_protection_group pg1  where pg1.protection_group_id = pg.protection_group_id OR pg1.protection_group_id =  (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id) ) AND pg.protection_group_id = pgpe.protection_group_id AND pgpe.protection_element_id = pe.protection_element_id AND r.role_id = rp.role_id AND rp.privilege_id = p.privilege_id AND pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Card'' AND p.privilege_name=''READ'' AND g.group_name IN (:GROUP_NAMES ) AND pe.application_id=:APPLICATION_ID))')
/
INSERT INTO CSM_FILTER_CLAUSE ( FILTER_CLAUSE_ID, CLASS_NAME, FILTER_CHAIN, TARGET_CLASS_NAME
, TARGET_CLASS_ATTRIBUTE_NAME, TARGET_CLASS_ATTRIBUTE_TYPE, GENERATED_SQL_USER, APPLICATION_ID
, UPDATE_DATE, GENERATED_SQL_GROUP ) VALUES ( 
17, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card', 'suit, deck', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck - deck'
, 'id', 'java.lang.Integer', 'ID in (select table_name_csm_.ID   from CARD table_name_csm_, SUIT suit1_, DECK deck2_ where table_name_csm_.SUIT_ID=suit1_.ID and suit1_.DECK_ID=deck2_.ID and deck2_.ID in ( select pe.attribute_value from csm_protection_group pg, csm_protection_element pe, csm_pg_pe pgpe, csm_user_group_role_pg ugrpg, csm_user u, csm_role_privilege rp, csm_role r, csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.user_id = u.user_id and ugrpg.protection_group_id = ANY (select pg1.protection_group_id from csm_protection_group pg1 where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id = (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id)) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck'' and pe.attribute=''id'' and p.privilege_name=''READ'' and u.login_name=:USER_NAME and pe.application_id=:APPLICATION_ID))'
, 2,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'), 'ID in (select table_name_csm_.ID   from CARD table_name_csm_, SUIT suit1_, DECK deck2_ where table_name_csm_.SUIT_ID=suit1_.ID and suit1_.DECK_ID=deck2_.ID and deck2_.ID in ( select distinct pe.attribute_value from csm_protection_group pg, 	csm_protection_element pe, 	csm_pg_pe pgpe,	csm_user_group_role_pg ugrpg, 	csm_group g, 	csm_role_privilege rp, 	csm_role r, 	csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.group_id = g.group_id and ugrpg.protection_group_id = any ( select pg1.protection_group_id from csm_protection_group pg1  where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id =  (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id) ) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck'' and pe.attribute=''id'' and p.privilege_name=''READ'' and g.group_name IN (:GROUP_NAMES ) and pe.application_id=:APPLICATION_ID))')
/
INSERT INTO CSM_FILTER_CLAUSE ( FILTER_CLAUSE_ID, CLASS_NAME, FILTER_CHAIN, TARGET_CLASS_NAME
, TARGET_CLASS_ATTRIBUTE_NAME, TARGET_CLASS_ATTRIBUTE_TYPE, GENERATED_SQL_USER, APPLICATION_ID
, UPDATE_DATE, GENERATED_SQL_GROUP ) VALUES ( 
18, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck', 'suitCollection, cardCollection'
, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card - cardCollection', 'id'
, 'java.lang.Integer', 'ID in (select table_name_csm_.ID   from DECK table_name_csm_, SUIT suit1_, CARD card2_ where table_name_csm_.ID=suit1_.DECK_ID and suit1_.ID=card2_.SUIT_ID and card2_.ID in ( select pe.attribute_value from csm_protection_group pg, csm_protection_element pe, csm_pg_pe pgpe, csm_user_group_role_pg ugrpg, csm_user u, csm_role_privilege rp, csm_role r, csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.user_id = u.user_id and ugrpg.protection_group_id = ANY (select pg1.protection_group_id from csm_protection_group pg1 where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id = (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id)) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Card'' and pe.attribute=''id'' and p.privilege_name=''READ'' and u.login_name=:USER_NAME and pe.application_id=:APPLICATION_ID))'
, 2,  TO_Date( '07/09/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'), 'ID in (select table_name_csm_.ID   from DECK table_name_csm_, SUIT suit1_, CARD card2_ where table_name_csm_.ID=suit1_.DECK_ID and suit1_.ID=card2_.SUIT_ID and card2_.ID in (SELECT Distinct pe.attribute_value FROM CSM_PROTECTION_GROUP pg, 	CSM_PROTECTION_ELEMENT pe, 	CSM_PG_PE pgpe,	CSM_USER_GROUP_ROLE_PG ugrpg, 	CSM_GROUP g, 	CSM_ROLE_PRIVILEGE rp, 	CSM_ROLE r, 	CSM_PRIVILEGE p WHERE ugrpg.role_id = r.role_id AND ugrpg.group_id = g.group_id AND ugrpg.protection_group_id = ANY ( select pg1.protection_group_id from csm_protection_group pg1  where pg1.protection_group_id = pg.protection_group_id OR pg1.protection_group_id =  (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id) ) AND pg.protection_group_id = pgpe.protection_group_id AND pgpe.protection_element_id = pe.protection_element_id AND r.role_id = rp.role_id AND rp.privilege_id = p.privilege_id AND pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Card'' AND p.privilege_name=''READ'' AND g.group_name IN (:GROUP_NAMES ) AND pe.application_id=:APPLICATION_ID))')
/
INSERT INTO CSM_FILTER_CLAUSE ( FILTER_CLAUSE_ID, CLASS_NAME, FILTER_CHAIN, TARGET_CLASS_NAME
, TARGET_CLASS_ATTRIBUTE_NAME, TARGET_CLASS_ATTRIBUTE_TYPE, GENERATED_SQL_USER, APPLICATION_ID
, UPDATE_DATE, GENERATED_SQL_GROUP ) VALUES ( 
19, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle'
, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle - self', 'color'
, 'java.lang.String', 'ID in (select table_name_csm_.ID   from HANDLE table_name_csm_ where table_name_csm_.COLOR in ( select pe.attribute_value from csm_protection_group pg, csm_protection_element pe, csm_pg_pe pgpe, csm_user_group_role_pg ugrpg, csm_user u, csm_role_privilege rp, csm_role r, csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.user_id = u.user_id and ugrpg.protection_group_id = ANY (select pg1.protection_group_id from csm_protection_group pg1 where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id = (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id)) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= ''gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle'' and pe.attribute=''color'' and p.privilege_name=''READ'' and u.login_name=:USER_NAME and pe.application_id=:APPLICATION_ID))'
, 2,  TO_Date( '07/09/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'), 'ID in (select table_name_csm_.ID   from HANDLE table_name_csm_ where table_name_csm_.COLOR in (SELECT Distinct pe.attribute_value FROM CSM_PROTECTION_GROUP pg, 	CSM_PROTECTION_ELEMENT pe, 	CSM_PG_PE pgpe,	CSM_USER_GROUP_ROLE_PG ugrpg, 	CSM_GROUP g, 	CSM_ROLE_PRIVILEGE rp, 	CSM_ROLE r, 	CSM_PRIVILEGE p WHERE ugrpg.role_id = r.role_id AND ugrpg.group_id = g.group_id AND ugrpg.protection_group_id = ANY ( select pg1.protection_group_id from csm_protection_group pg1  where pg1.protection_group_id = pg.protection_group_id OR pg1.protection_group_id =  (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id) ) AND pg.protection_group_id = pgpe.protection_group_id AND pgpe.protection_element_id = pe.protection_element_id AND r.role_id = rp.role_id AND rp.privilege_id = p.privilege_id AND pe.object_id= ''gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle'' AND p.privilege_name=''READ'' AND g.group_name IN (:GROUP_NAMES ) AND pe.application_id=:APPLICATION_ID))')
/
commit
/
 
INSERT INTO CSM_GROUP ( GROUP_ID, GROUP_NAME, GROUP_DESC, UPDATE_DATE, APPLICATION_ID
 ) VALUES ( 
2, 'Group1', 'Group 1 - Access to All PE''s.  Same as user1',  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 2)
/
INSERT INTO CSM_GROUP ( GROUP_ID, GROUP_NAME, GROUP_DESC, UPDATE_DATE, APPLICATION_ID
 ) VALUES ( 
3, 'Group2', 'Group 2 - Same limited access as user2',  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 2)
/
INSERT INTO CSM_GROUP ( GROUP_ID, GROUP_NAME, GROUP_DESC, UPDATE_DATE, APPLICATION_ID
 ) VALUES ( 
4, 'Group3', 'Group3 - No access',  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 2)
/
commit
/
 
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
766, 4, 24,  TO_Date( '05/26/2008 04:33:58 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
779, 1, 119,  TO_Date( '07/23/2008 02:52:09 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
773, 7, 40,  TO_Date( '06/30/2008 03:14:32 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
774, 7, 5,  TO_Date( '06/30/2008 03:14:32 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
775, 7, 39,  TO_Date( '06/30/2008 03:14:32 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
776, 7, 41,  TO_Date( '06/30/2008 03:14:32 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
777, 7, 114,  TO_Date( '06/30/2008 03:14:32 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
778, 7, 38,  TO_Date( '06/30/2008 03:14:32 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
670, 1, 100,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
671, 1, 33,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
672, 1, 81,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
673, 1, 108,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
674, 1, 88,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
675, 1, 83,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
676, 1, 77,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
677, 1, 4,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
678, 1, 113,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
679, 1, 17,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
680, 1, 15,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
681, 1, 65,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
682, 1, 95,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
683, 1, 93,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
684, 1, 90,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
685, 1, 103,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
686, 1, 20,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
687, 1, 101,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
688, 1, 18,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
689, 1, 67,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
690, 1, 76,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
691, 1, 92,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
692, 1, 80,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
693, 1, 82,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
694, 1, 116,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
695, 1, 24,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
696, 1, 91,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
697, 1, 98,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
698, 1, 32,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
699, 1, 14,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
700, 1, 23,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
701, 1, 75,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
702, 1, 99,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
703, 1, 16,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
704, 1, 38,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
705, 1, 66,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
706, 1, 37,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
708, 1, 30,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
709, 1, 45,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
710, 1, 35,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
711, 1, 94,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
712, 1, 41,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
713, 1, 86,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
714, 1, 79,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
715, 1, 27,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
716, 1, 13,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
717, 1, 68,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
718, 1, 71,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
719, 1, 26,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
720, 1, 7,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
721, 1, 25,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
722, 1, 115,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
723, 1, 85,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
724, 1, 110,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
725, 1, 36,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
726, 1, 31,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
727, 1, 11,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
728, 1, 74,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
729, 1, 84,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
730, 1, 102,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
731, 1, 97,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
732, 1, 8,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
733, 1, 43,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
734, 1, 42,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
735, 1, 28,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
736, 1, 70,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
737, 1, 12,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
738, 1, 29,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
739, 1, 72,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
740, 1, 44,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
741, 1, 114,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
742, 1, 22,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
743, 1, 40,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
744, 1, 6,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
745, 1, 87,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
746, 1, 21,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
747, 1, 9,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
748, 1, 34,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
749, 1, 104,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
750, 1, 89,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
751, 1, 107,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
752, 1, 19,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
753, 1, 5,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
754, 1, 96,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
755, 1, 73,  TO_Date( '12/19/2007 04:40:11 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
765, 1, 39,  TO_Date( '12/27/2007 12:41:17 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
107, 2, 11,  TO_Date( '07/17/2007 03:30:19 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
108, 2, 24,  TO_Date( '07/17/2007 03:30:19 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
110, 2, 8,  TO_Date( '07/17/2007 03:30:19 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PG_PE ( PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE
 ) VALUES ( 
111, 3, 37,  TO_Date( '07/17/2007 03:30:19 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
commit
/
 
INSERT INTO CSM_PRIVILEGE ( PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE
 ) VALUES ( 
1, 'CREATE', 'This privilege grants permission to a user to create an entity. This entity can be an object, a database entry, or a resource such as a network connection'
,  TO_Date( '02/28/2007 01:03:03 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PRIVILEGE ( PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE
 ) VALUES ( 
2, 'ACCESS', 'This privilege allows a user to access a particular resource.  ',  TO_Date( '02/28/2007 01:03:04 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PRIVILEGE ( PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE
 ) VALUES ( 
3, 'READ', 'This privilege permits the user to read data from a file, URL, socket, database, or an object. '
,  TO_Date( '02/28/2007 01:03:04 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PRIVILEGE ( PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE
 ) VALUES ( 
4, 'WRITE', 'This privilege allows a user to write data to a file, URL, socket, database, or object. '
,  TO_Date( '02/28/2007 01:03:04 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PRIVILEGE ( PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE
 ) VALUES ( 
5, 'UPDATE', 'This privilege grants permission at an entity level and signifies that the user is allowed to update and modify data for a particular entity.'
,  TO_Date( '02/28/2007 01:03:04 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PRIVILEGE ( PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE
 ) VALUES ( 
6, 'DELETE', 'This privilege permits a user to delete a logical entity.',  TO_Date( '02/28/2007 01:03:04 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PRIVILEGE ( PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE
 ) VALUES ( 
7, 'EXECUTE', 'This privilege allows a user to execute a particular resource.',  TO_Date( '02/28/2007 01:03:04 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
commit
/
 
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, APPLICATION_ID, UPDATE_DATE
 ) VALUES ( 
5, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit', 'issuingBank'
, 2,  TO_Date( '12/17/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
6, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment', 2,  TO_Date( '03/01/2007 03:03:24 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
7, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent'
, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent', 2,  TO_Date( '03/01/2007 03:03:24 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
8, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student'
, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student', 2,  TO_Date( '03/01/2007 03:03:24 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
9, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent'
, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent', 2
,  TO_Date( '03/01/2007 03:03:24 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
11, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal'
, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal', 2,  TO_Date( '03/01/2007 03:03:24 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
12, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant', 2,  TO_Date( '03/01/2007 03:03:25 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
13, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor'
, 2,  TO_Date( '03/01/2007 03:03:25 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
14, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor'
, 2,  TO_Date( '03/01/2007 03:03:25 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
15, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor', 2,  TO_Date( '03/01/2007 03:03:25 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
16, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor'
, 2,  TO_Date( '03/01/2007 03:03:25 PM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
17, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor', 2,  TO_Date( '03/01/2007 03:03:25 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
18, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display', 2,  TO_Date( '03/01/2007 03:03:25 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
19, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor', 2,  TO_Date( '03/01/2007 03:03:25 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
20, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor', 2,  TO_Date( '03/01/2007 03:03:25 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
21, 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee', 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee'
, 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
22, 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project', 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project'
, 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
23, 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author', 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author'
, 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
24, 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book', 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book'
, 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
25, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef'
, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
26, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant'
, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
27, 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer', 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer'
, 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
28, 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive', 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive'
, 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
29, 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.Key', 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.Key'
, 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.Key', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
30, 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain', 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain'
, 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
31, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine'
, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine', 2,  TO_Date( '03/01/2007 03:03:26 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
32, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product'
, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product', 2,  TO_Date( '03/01/2007 03:03:27 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
33, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child'
, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child', 2,  TO_Date( '03/01/2007 03:03:27 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
34, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent'
, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent', 2,  TO_Date( '03/01/2007 03:03:27 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
35, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address'
, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address', 2,  TO_Date( '03/01/2007 03:03:27 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
36, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person'
, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person', 2,  TO_Date( '03/01/2007 03:03:27 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
37, 'gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType', 'gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType'
, 'gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType', 2,  TO_Date( '03/01/2007 03:03:27 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, APPLICATION_ID, UPDATE_DATE
, ATTRIBUTE_VALUE ) VALUES ( 
38, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card'
, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card', 'Name', 2,  TO_Date( '12/21/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 'Ace')
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, APPLICATION_ID, UPDATE_DATE
, ATTRIBUTE_VALUE ) VALUES ( 
39, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck'
, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck', 'id', 2,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, '1')
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
40, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand'
, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand', 2,  TO_Date( '03/01/2007 03:03:27 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, APPLICATION_ID, UPDATE_DATE
 ) VALUES ( 
41, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit'
, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit', 'cardCollection', 2
,  TO_Date( '12/27/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
42, 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey', 2,  TO_Date( '03/01/2007 03:03:28 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
43, 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatKey', 2,  TO_Date( '03/01/2007 03:03:28 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
44, 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerKey', 2,  TO_Date( '03/01/2007 03:03:28 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
45, 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey', 2,  TO_Date( '03/01/2007 03:03:28 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
113, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human'
, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human', 2,  TO_Date( '12/17/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, APPLICATION_ID, UPDATE_DATE
 ) VALUES ( 
114, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank', 'PE for ''name'' attribute of Bank object'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank', 'name', 2
,  TO_Date( '12/17/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
1, 'csmupt', 'UPT Super Admin Application', 'csmupt', 1,  TO_Date( '02/28/2007 01:03:03 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
2, 'sdk', 'sdk Application', 'sdk', 1,  TO_Date( '04/02/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
71, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization'
, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization'
, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
67, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
73, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency'
, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
75, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
81, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
82, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
86, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
87, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
91, 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight', 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight'
, 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
92, 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Shirt', 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Shirt'
, 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Shirt', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
96, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw'
, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
97, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag'
, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
101, 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoublePrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoublePrimitiveKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoublePrimitiveKey', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
102, 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
107, 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
119, 'utensil', 'gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil'
, 2,  TO_Date( '07/23/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
66, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
70, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization'
, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization'
, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
83, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
88, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
93, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain'
, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
98, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle'
, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
103, 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
108, 'gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
65, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
68, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
72, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization'
, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization'
, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
74, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note'
, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
76, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
77, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
79, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel'
, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
80, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
84, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
85, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator'
, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator'
, 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
89, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album'
, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
90, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song'
, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
94, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant'
, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
95, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride'
, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
99, 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
100, 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
104, 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
110, 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey'
, 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey', 2,  TO_Date( '12/13/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, APPLICATION_ID, UPDATE_DATE
 ) VALUES ( 
115, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit.amount'
, 'PE for Credit.amount attribute', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit'
, 'amount', 2,  TO_Date( '12/19/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, APPLICATION_ID, UPDATE_DATE
 ) VALUES ( 
116, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit.cardNumber'
, 'PE for Credit.cardNumber attribute', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit'
, 'cardNumber', 2,  TO_Date( '12/19/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_ELEMENT ( PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME
, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID, UPDATE_DATE ) VALUES ( 
4, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash'
, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash', 2,  TO_Date( '03/01/2007 03:03:24 PM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
commit
/
 
INSERT INTO CSM_PROTECTION_GROUP ( PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, APPLICATION_ID
, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE ) VALUES ( 
3, 'AllDataType', 2, 0,  TO_Date( '03/23/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_GROUP ( PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION
, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE ) VALUES ( 
1, 'All PEs', 'Contains all of the PEs in the entire test sdk system', 2, 0,  TO_Date( '12/19/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
)
/
INSERT INTO CSM_PROTECTION_GROUP ( PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, APPLICATION_ID
, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE ) VALUES ( 
2, 'Bank', 2, 0,  TO_Date( '03/01/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_GROUP ( PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, APPLICATION_ID
, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE ) VALUES ( 
4, 'Book', 2, 0,  TO_Date( '03/23/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_PROTECTION_GROUP ( PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION
, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE ) VALUES ( 
7, 'Limited Access', 'JUnit Security Test Group with limited access to a handful of Classes and Attributes'
, 2, 0,  TO_Date( '12/19/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
commit
/
 
INSERT INTO CSM_ROLE ( ROLE_ID, ROLE_NAME, APPLICATION_ID, ACTIVE_FLAG, UPDATE_DATE
 ) VALUES ( 
5, 'Update', 2, 1,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_ROLE ( ROLE_ID, ROLE_NAME, APPLICATION_ID, ACTIVE_FLAG, UPDATE_DATE
 ) VALUES ( 
6, 'Delete', 2, 1,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_ROLE ( ROLE_ID, ROLE_NAME, APPLICATION_ID, ACTIVE_FLAG, UPDATE_DATE
 ) VALUES ( 
3, 'Create', 2, 1,  TO_Date( '03/23/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_ROLE ( ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, APPLICATION_ID, ACTIVE_FLAG
, UPDATE_DATE ) VALUES ( 
1, 'SuperAdmin', 'SuperAdmin', 2, 1,  TO_Date( '03/01/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_ROLE ( ROLE_ID, ROLE_NAME, APPLICATION_ID, ACTIVE_FLAG, UPDATE_DATE
 ) VALUES ( 
2, 'Read', 2, 1,  TO_Date( '03/01/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
commit
/
 
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
3, 1, 1)
/
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
9, 3, 1)
/
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
4, 1, 2)
/
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
7, 1, 3)
/
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
1, 2, 3)
/
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
6, 1, 4)
/
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
2, 1, 5)
/
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
14, 5, 5)
/
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
8, 1, 6)
/
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
15, 6, 6)
/
INSERT INTO CSM_ROLE_PRIVILEGE ( ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID ) VALUES ( 
5, 1, 7)
/
commit
/
 
INSERT INTO CSM_USER ( USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, UPDATE_DATE, MIGRATED_FLAG
 ) VALUES ( 
17, '/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=SDKTestUser', 'Grid', 'User',  TO_Date( '08/18/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 0)
/
INSERT INTO CSM_USER ( USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, PASSWORD, UPDATE_DATE
, MIGRATED_FLAG ) VALUES ( 
13, '/O=caBIG/OU=caGrid/OU=NCICB DEV LOA1/OU=Dorian/CN=SDKUser1', 'user1', 'junit'
, '/3+jJvECOxQI2TxoU2+Xvw==',  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 0)
/
INSERT INTO CSM_USER ( USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, PASSWORD, UPDATE_DATE
, MIGRATED_FLAG ) VALUES ( 
14, '/O=caBIG/OU=caGrid/OU=NCICB DEV LOA1/OU=Dorian/CN=SDKUser2', 'user2', 'junit'
, '/3+jJvECOxQI2TxoU2+Xvw==',  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 0)
/
INSERT INTO CSM_USER ( USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, PASSWORD, UPDATE_DATE
, MIGRATED_FLAG ) VALUES ( 
9, 'patelsat', 'Satish', 'Patel', 'zJPWCwDeSgG8j2uyHEABIQ==',  TO_Date( '03/23/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 0)
/
INSERT INTO CSM_USER ( USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, PASSWORD, UPDATE_DATE
, MIGRATED_FLAG ) VALUES ( 
1, 'SuperAdmin', 'Super', 'Admin', 'zJPWCwDeSgG8j2uyHEABIQ==',  TO_Date( '05/23/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 0)
/
INSERT INTO CSM_USER ( USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, PASSWORD, EMAIL_ID
, UPDATE_DATE, MIGRATED_FLAG ) VALUES ( 
12, 'dumitrud', 'Dan', 'Dumitru', 'qN+MnXquuqO8j2uyHEABIQ==', 'dumitrud@mail.nih.gov'
,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'), 0)
/
INSERT INTO CSM_USER ( USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, UPDATE_DATE, MIGRATED_FLAG
 ) VALUES ( 
16, 'testsdkqa', 'testsdkqa', 'testsdkqa',  TO_Date( '07/23/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM')
, 0)
/
commit
/
 
INSERT INTO CSM_USER_GROUP ( USER_GROUP_ID, USER_ID, GROUP_ID ) VALUES ( 
2, 13, 2)
/
INSERT INTO CSM_USER_GROUP ( USER_GROUP_ID, USER_ID, GROUP_ID ) VALUES ( 
3, 14, 3)
/
commit
/
 
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
23, 2, 3, 1,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
24, 2, 2, 1,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
40, 3, 3, 7,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
41, 3, 2, 7,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, USER_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
34, 13, 6, 1,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, USER_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
35, 13, 5, 1,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, USER_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
16, 12, 1, 1,  TO_Date( '12/12/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, USER_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
48, 17, 2, 1,  TO_Date( '08/18/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, USER_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
1, 1, 1, 1,  TO_Date( '03/01/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, USER_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
10, 9, 1, 1,  TO_Date( '03/23/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, USER_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
17, 13, 3, 1,  TO_Date( '12/19/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, USER_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
18, 13, 2, 1,  TO_Date( '12/19/2007 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, USER_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
38, 14, 3, 7,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
INSERT INTO CSM_USER_GROUP_ROLE_PG ( USER_GROUP_ROLE_PG_ID, USER_ID, ROLE_ID, PROTECTION_GROUP_ID
, UPDATE_DATE ) VALUES ( 
39, 14, 2, 7,  TO_Date( '06/30/2008 12:00:00 AM', 'MM/DD/YYYY HH:MI:SS AM'))
/
commit
/
 
INSERT INTO CSM_USER_PE ( USER_PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_ID, USER_ID
 ) VALUES ( 
3, 1, 1)
/
INSERT INTO CSM_USER_PE ( USER_PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_ID, USER_ID
 ) VALUES ( 
8, 2, 1)
/
INSERT INTO CSM_USER_PE ( USER_PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_ID, USER_ID
 ) VALUES ( 
7, 2, 9)
/
commit
/
 
ALTER TABLE CSM_APPLICATION ADD CONSTRAINT PK_APPLICATION 
PRIMARY KEY (APPLICATION_ID) 

/

ALTER TABLE CSM_GROUP ADD CONSTRAINT PK_GROUP 
PRIMARY KEY (GROUP_ID) 

/

ALTER TABLE CSM_PG_PE ADD CONSTRAINT PK_PG_PE 
PRIMARY KEY (PG_PE_ID) 

/

ALTER TABLE CSM_PRIVILEGE ADD CONSTRAINT PK_PRIVILEGE 
PRIMARY KEY (PRIVILEGE_ID) 

/

ALTER TABLE CSM_PROTECTION_ELEMENT ADD CONSTRAINT PK_PROTECTION_ELEMENT 
PRIMARY KEY (PROTECTION_ELEMENT_ID) 

/

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT PK_PROTECTION_GROUP 
PRIMARY KEY (PROTECTION_GROUP_ID) 

/

ALTER TABLE CSM_ROLE ADD CONSTRAINT PK_ROLE 
PRIMARY KEY (ROLE_ID) 

/

ALTER TABLE CSM_FILTER_CLAUSE ADD CONSTRAINT PK_FILTER_CLAUSE 
PRIMARY KEY (FILTER_CLAUSE_ID) 

/

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT PK_ROLE_PRIVILEGE 
PRIMARY KEY (ROLE_PRIVILEGE_ID) 

/

ALTER TABLE CSM_USER ADD CONSTRAINT PK_USER 
PRIMARY KEY (USER_ID) 

/

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT PK_USER_GROUP 
PRIMARY KEY (USER_GROUP_ID) 

/

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT PK_USER_GROUP_ROLE_PG 
PRIMARY KEY (USER_GROUP_ROLE_PG_ID) 

/

ALTER TABLE CSM_USER_PE ADD CONSTRAINT PK_USER_PROTECTION_ELEMENT 
PRIMARY KEY (USER_PROTECTION_ELEMENT_ID) 

/


ALTER TABLE CSM_APPLICATION
ADD CONSTRAINT UQ_APPLICATION_NAME UNIQUE (APPLICATION_NAME)

/
ALTER TABLE CSM_GROUP
ADD CONSTRAINT UQ_GROUP_GROUP_NAME UNIQUE (APPLICATION_ID, GROUP_NAME)

/
ALTER TABLE CSM_PG_PE
ADD CONSTRAINT UQ_PG_PE_PG_PE_ID UNIQUE (PROTECTION_ELEMENT_ID, PROTECTION_GROUP_ID)

/
ALTER TABLE CSM_PRIVILEGE
ADD CONSTRAINT UQ_PRIVILEGE_NAME UNIQUE (PRIVILEGE_NAME)

/
ALTER TABLE CSM_PROTECTION_ELEMENT
ADD CONSTRAINT UQ_PE_OBJ_ATT_APP_ID UNIQUE (OBJECT_ID, ATTRIBUTE, APPLICATION_ID)

/
ALTER TABLE CSM_PROTECTION_GROUP
ADD CONSTRAINT UQ_PG_PG_NAME UNIQUE (APPLICATION_ID, PROTECTION_GROUP_NAME)

/
ALTER TABLE CSM_ROLE
ADD CONSTRAINT UQ_ROLE_ROLE_NAME UNIQUE (APPLICATION_ID, ROLE_NAME)

/
ALTER TABLE CSM_ROLE_PRIVILEGE
ADD CONSTRAINT UQ_ROLE_ID_PRIVILEGE_ID UNIQUE (PRIVILEGE_ID, ROLE_ID)

/
ALTER TABLE CSM_USER
ADD CONSTRAINT UQ_LOGIN_NAME UNIQUE (LOGIN_NAME)

/
ALTER TABLE CSM_USER_PE
ADD CONSTRAINT UQ_USER_PE_PE_ID UNIQUE (USER_ID, PROTECTION_ELEMENT_ID)

/

ALTER TABLE CSM_GROUP ADD CONSTRAINT FK_CSM_GROUP_CSM_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_FILTER_CLAUSE ADD CONSTRAINT FK_CSM_FC_CSM_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_PG_PE ADD CONSTRAINT FK_PG_PE_PE 
FOREIGN KEY (PROTECTION_ELEMENT_ID) REFERENCES CSM_PROTECTION_ELEMENT (PROTECTION_ELEMENT_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_PG_PE ADD CONSTRAINT FK_PG_PE_PG 
FOREIGN KEY (PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_PROTECTION_ELEMENT ADD CONSTRAINT FK_PE_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT FK_PG_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT FK_PG_PG 
FOREIGN KEY (PARENT_PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)

/

ALTER TABLE CSM_ROLE ADD CONSTRAINT FK_ROLE_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT FK_ROLE_PRIVILEGE_PRIVILEGE 
FOREIGN KEY (PRIVILEGE_ID) REFERENCES CSM_PRIVILEGE (PRIVILEGE_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT FK_ROLE_PRIVILEGE_ROLE 
FOREIGN KEY (ROLE_ID) REFERENCES CSM_ROLE (ROLE_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT FK_USER_GROUP_GROUP 
FOREIGN KEY (GROUP_ID) REFERENCES CSM_GROUP (GROUP_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT FK_USER_GROUP_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_GROUP 
FOREIGN KEY (GROUP_ID) REFERENCES CSM_GROUP (GROUP_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_PG 
FOREIGN KEY (PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_ROLE 
FOREIGN KEY (ROLE_ID) REFERENCES CSM_ROLE (ROLE_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_USER_PE ADD CONSTRAINT FK_USER_PE_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE

/

ALTER TABLE CSM_USER_PE ADD CONSTRAINT FK_USER_PE_PE 
FOREIGN KEY (PROTECTION_ELEMENT_ID) REFERENCES CSM_PROTECTION_ELEMENT (PROTECTION_ELEMENT_ID)
ON DELETE CASCADE

/

COMMIT
/
