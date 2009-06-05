

DROP SEQUENCE CSM_APPLICATI_APPLICATION__SEQ;

DROP TABLE CSM_APPLICATION CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_GROUP_GROUP_ID_SEQ;

DROP TABLE CSM_GROUP CASCADE CONSTRAINTS
;

DROP TRIGGER SET_CSM_PG_PE_PG_PE_ID;

DROP TRIGGER SET_CSM_PG_PE_UPDATE_DATE;

DROP SEQUENCE CSM_PG_PE_PG_PE_ID_SEQ;

DROP TABLE CSM_PG_PE CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_PRIVILEGE_PRIVILEGE_ID_SEQ;

DROP TABLE CSM_PRIVILEGE CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_FILTER_CLAUSE_FILTE_ID_SEQ;

DROP TABLE CSM_FILTER_CLAUSE CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_PROTECTIO_PROTECTION_E_SEQ;

DROP TABLE CSM_PROTECTION_ELEMENT CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_PROTECTIO_PROTECTION_G_SEQ;

DROP TABLE CSM_PROTECTION_GROUP CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_ROLE_ROLE_ID_SEQ;

DROP TABLE CSM_ROLE CASCADE CONSTRAINTS
;

DROP TRIGGER SET_CSM_ROLE_PRIV_ROLE_PRIVILE;

DROP SEQUENCE CSM_ROLE_PRIV_ROLE_PRIVILE_SEQ;

DROP TABLE CSM_ROLE_PRIVILEGE CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_USER_USER_ID_SEQ;

DROP TABLE CSM_USER CASCADE CONSTRAINTS
;

DROP TRIGGER SET_CSM_USER_GROU_USER_GROUP_I;

DROP SEQUENCE CSM_USER_GROU_USER_GROUP_I_SEQ;

DROP TABLE CSM_USER_GROUP CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_USER_GROU_USER_GROUP_R_SEQ;

DROP TABLE CSM_USER_GROUP_ROLE_PG CASCADE CONSTRAINTS
;

DROP TRIGGER SET_CSM_USER_PE_USER_PROTECTIO;

DROP SEQUENCE CSM_USER_PE_USER_PROTECTIO_SEQ;

DROP TABLE CSM_USER_PE CASCADE CONSTRAINTS
;


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
;
CREATE SEQUENCE CSM_APPLICATI_APPLICATION__SEQ
increment by 1
start with 3
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_GROUP ( 
	GROUP_ID NUMBER(38) NOT NULL,
	GROUP_NAME VARCHAR2(255) NOT NULL,
	GROUP_DESC VARCHAR2(200),
	UPDATE_DATE DATE NOT NULL,
	APPLICATION_ID NUMBER(38) NOT NULL
) 
;
CREATE SEQUENCE CSM_GROUP_GROUP_ID_SEQ
increment by 1
start with 5
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_PG_PE ( 
	PG_PE_ID NUMBER(38) NOT NULL,
	PROTECTION_GROUP_ID NUMBER(38) NOT NULL,
	PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE 
) 
;
CREATE SEQUENCE CSM_PG_PE_PG_PE_ID_SEQ
increment by 1
start with 780
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

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


CREATE TABLE CSM_PRIVILEGE ( 
	PRIVILEGE_ID NUMBER(38) NOT NULL,
	PRIVILEGE_NAME VARCHAR2(100) NOT NULL,
	PRIVILEGE_DESCRIPTION VARCHAR2(200),
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_PRIVILEGE_PRIVILEGE_ID_SEQ
increment by 1
start with 8
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

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
;

CREATE SEQUENCE CSM_FILTER_CLAUSE_FILTE_ID_SEQ
increment by 1
start with 21
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


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
;
CREATE SEQUENCE CSM_PROTECTIO_PROTECTION_E_SEQ
increment by 1
start with 116
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_PROTECTION_GROUP ( 
	PROTECTION_GROUP_ID NUMBER(38) NOT NULL,
	PROTECTION_GROUP_NAME VARCHAR2(100) NOT NULL,
	PROTECTION_GROUP_DESCRIPTION VARCHAR2(200),
	APPLICATION_ID NUMBER(38) NOT NULL,
	LARGE_ELEMENT_COUNT_FLAG NUMBER(1) NOT NULL,
	UPDATE_DATE DATE NOT NULL,
	PARENT_PROTECTION_GROUP_ID NUMBER(38)
) 
;
CREATE SEQUENCE CSM_PROTECTIO_PROTECTION_G_SEQ
increment by 1
start with 8
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_ROLE ( 
	ROLE_ID NUMBER(38) NOT NULL,
	ROLE_NAME VARCHAR2(100) NOT NULL,
	ROLE_DESCRIPTION VARCHAR2(200),
	APPLICATION_ID NUMBER(38) NOT NULL,
	ACTIVE_FLAG NUMBER(1) NOT NULL,
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_ROLE_ROLE_ID_SEQ
increment by 1
start with 7
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

CREATE TABLE CSM_ROLE_PRIVILEGE ( 
	ROLE_PRIVILEGE_ID NUMBER(38) NOT NULL,
	ROLE_ID NUMBER(38) NOT NULL,
	PRIVILEGE_ID NUMBER(38) NOT NULL
) 
;
CREATE SEQUENCE CSM_ROLE_PRIV_ROLE_PRIVILE_SEQ
increment by 1
start with 16
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

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
;
CREATE SEQUENCE CSM_USER_USER_ID_SEQ
increment by 1
start with 18
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_USER_GROUP ( 
	USER_GROUP_ID NUMBER(38) NOT NULL,
	USER_ID NUMBER(38) NOT NULL,
	GROUP_ID NUMBER(38) NOT NULL
) 
;
CREATE SEQUENCE CSM_USER_GROU_USER_GROUP_I_SEQ
increment by 1
start with 7
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

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


CREATE TABLE CSM_USER_GROUP_ROLE_PG ( 
	USER_GROUP_ROLE_PG_ID NUMBER(38) NOT NULL,
	USER_ID NUMBER(38),
	GROUP_ID NUMBER(38),
	ROLE_ID NUMBER(38) NOT NULL,
	PROTECTION_GROUP_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_USER_GROU_USER_GROUP_R_SEQ
increment by 1
start with 49
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

CREATE TABLE CSM_USER_PE ( 
	USER_PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	USER_ID NUMBER(38) NOT NULL
) 
;
CREATE SEQUENCE CSM_USER_PE_USER_PROTECTIO_SEQ
increment by 1
start with 9
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

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


Insert into CSM_APPLICATION
   (APPLICATION_ID, APPLICATION_NAME, APPLICATION_DESCRIPTION, DECLARATIVE_FLAG, ACTIVE_FLAG, 
    UPDATE_DATE, DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD, DATABASE_DIALECT, 
    DATABASE_DRIVER)
 Values
   (1, 'csmupt', 'UPT Super Admin Application', 0, 0, 
    TO_DATE('02/28/2007 13:03:02', 'MM/DD/YYYY HH24:MI:SS'), NULL, NULL, NULL, NULL, 
    NULL);
Insert into CSM_APPLICATION
   (APPLICATION_ID, APPLICATION_NAME, APPLICATION_DESCRIPTION, DECLARATIVE_FLAG, ACTIVE_FLAG, 
    UPDATE_DATE, DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD, DATABASE_DIALECT, 
    DATABASE_DRIVER)
 Values
   (2, 'sdk', 'sdk', 1, 1, 
    TO_DATE('04/02/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), '@CSM_DB_CONNECTION_URL@', '@CSM_DB_USERNAME@', 'mNIHCvHxv3w=', 'org.hibernate.dialect.Oracle9Dialect', 
    'oracle.jdbc.driver.OracleDriver');
COMMIT;

Insert into CSM_FILTER_CLAUSE
   (FILTER_CLAUSE_ID, CLASS_NAME, FILTER_CHAIN, TARGET_CLASS_NAME, TARGET_CLASS_ATTRIBUTE_NAME, 
    TARGET_CLASS_ATTRIBUTE_TYPE, TARGET_CLASS_ALIAS, TARGET_CLASS_ATTRIBUTE_ALIAS, GENERATED_SQL_USER, APPLICATION_ID, 
    UPDATE_DATE, GENERATED_SQL_GROUP)
 Values
   (20, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck', 'suitCollection, cardCollection', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card - cardCollection', 'Name', 
    'java.lang.String', NULL, NULL, 'ID in (select table_name_csm_.ID   from DECK table_name_csm_, SUIT suit1_, CARD card2_ where table_name_csm_.ID=suit1_.DECK_ID and suit1_.ID=card2_.SUIT_ID and card2_.NAME in ( select pe.attribute_value from csm_protection_group pg, csm_protection_element pe, csm_pg_pe pgpe, csm_user_group_role_pg ugrpg, csm_user u, csm_role_privilege rp, csm_role r, csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.user_id = u.user_id and ugrpg.protection_group_id = ANY (select pg1.protection_group_id from csm_protection_group pg1 where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id = (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id)) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Card'' and pe.attribute=''Name'' and p.privilege_name=''READ'' and u.login_name=:USER_NAME and pe.application_id=:APPLICATION_ID))', 2, 
    TO_DATE('07/09/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'ID in (select table_name_csm_.ID   from DECK table_name_csm_, SUIT suit1_, CARD card2_ where table_name_csm_.ID=suit1_.DECK_ID and suit1_.ID=card2_.SUIT_ID and card2_.NAME in (SELECT Distinct pe.attribute_value FROM CSM_PROTECTION_GROUP pg, 	CSM_PROTECTION_ELEMENT pe, 	CSM_PG_PE pgpe,	CSM_USER_GROUP_ROLE_PG ugrpg, 	CSM_GROUP g, 	CSM_ROLE_PRIVILEGE rp, 	CSM_ROLE r, 	CSM_PRIVILEGE p WHERE ugrpg.role_id = r.role_id AND ugrpg.group_id = g.group_id AND ugrpg.protection_group_id = ANY ( select pg1.protection_group_id from csm_protection_group pg1  where pg1.protection_group_id = pg.protection_group_id OR pg1.protection_group_id =  (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id) ) AND pg.protection_group_id = pgpe.protection_group_id AND pgpe.protection_element_id = pe.protection_element_id AND r.role_id = rp.role_id AND rp.privilege_id = p.privilege_id AND pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Card'' AND p.privilege_name=''READ'' AND g.group_name IN (:GROUP_NAMES ) AND pe.application_id=:APPLICATION_ID))');
Insert into CSM_FILTER_CLAUSE
   (FILTER_CLAUSE_ID, CLASS_NAME, FILTER_CHAIN, TARGET_CLASS_NAME, TARGET_CLASS_ATTRIBUTE_NAME, 
    TARGET_CLASS_ATTRIBUTE_TYPE, TARGET_CLASS_ALIAS, TARGET_CLASS_ATTRIBUTE_ALIAS, GENERATED_SQL_USER, APPLICATION_ID, 
    UPDATE_DATE, GENERATED_SQL_GROUP)
 Values
   (17, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card', 'suit, deck', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck - deck', 'id', 
    'java.lang.Integer', NULL, NULL, 'ID in (select table_name_csm_.ID   from CARD table_name_csm_, SUIT suit1_, DECK deck2_ where table_name_csm_.SUIT_ID=suit1_.ID and suit1_.DECK_ID=deck2_.ID and deck2_.ID in ( select pe.attribute_value from csm_protection_group pg, csm_protection_element pe, csm_pg_pe pgpe, csm_user_group_role_pg ugrpg, csm_user u, csm_role_privilege rp, csm_role r, csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.user_id = u.user_id and ugrpg.protection_group_id = ANY (select pg1.protection_group_id from csm_protection_group pg1 where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id = (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id)) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck'' and pe.attribute=''id'' and p.privilege_name=''READ'' and u.login_name=:USER_NAME and pe.application_id=:APPLICATION_ID))', 2, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'ID in (select table_name_csm_.ID   from CARD table_name_csm_, SUIT suit1_, DECK deck2_ where table_name_csm_.SUIT_ID=suit1_.ID and suit1_.DECK_ID=deck2_.ID and deck2_.ID in ( select distinct pe.attribute_value from csm_protection_group pg, 	csm_protection_element pe, 	csm_pg_pe pgpe,	csm_user_group_role_pg ugrpg, 	csm_group g, 	csm_role_privilege rp, 	csm_role r, 	csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.group_id = g.group_id and ugrpg.protection_group_id = any ( select pg1.protection_group_id from csm_protection_group pg1  where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id =  (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id) ) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck'' and pe.attribute=''id'' and p.privilege_name=''READ'' and g.group_name IN (:GROUP_NAMES ) and pe.application_id=:APPLICATION_ID))');
Insert into CSM_FILTER_CLAUSE
   (FILTER_CLAUSE_ID, CLASS_NAME, FILTER_CHAIN, TARGET_CLASS_NAME, TARGET_CLASS_ATTRIBUTE_NAME, 
    TARGET_CLASS_ATTRIBUTE_TYPE, TARGET_CLASS_ALIAS, TARGET_CLASS_ATTRIBUTE_ALIAS, GENERATED_SQL_USER, APPLICATION_ID, 
    UPDATE_DATE, GENERATED_SQL_GROUP)
 Values
   (18, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck', 'suitCollection, cardCollection', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card - cardCollection', 'id', 
    'java.lang.Integer', NULL, NULL, 'ID in (select table_name_csm_.ID   from DECK table_name_csm_, SUIT suit1_, CARD card2_ where table_name_csm_.ID=suit1_.DECK_ID and suit1_.ID=card2_.SUIT_ID and card2_.ID in ( select pe.attribute_value from csm_protection_group pg, csm_protection_element pe, csm_pg_pe pgpe, csm_user_group_role_pg ugrpg, csm_user u, csm_role_privilege rp, csm_role r, csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.user_id = u.user_id and ugrpg.protection_group_id = ANY (select pg1.protection_group_id from csm_protection_group pg1 where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id = (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id)) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Card'' and pe.attribute=''id'' and p.privilege_name=''READ'' and u.login_name=:USER_NAME and pe.application_id=:APPLICATION_ID))', 2, 
    TO_DATE('07/09/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'ID in (select table_name_csm_.ID   from DECK table_name_csm_, SUIT suit1_, CARD card2_ where table_name_csm_.ID=suit1_.DECK_ID and suit1_.ID=card2_.SUIT_ID and card2_.ID in (SELECT Distinct pe.attribute_value FROM CSM_PROTECTION_GROUP pg, 	CSM_PROTECTION_ELEMENT pe, 	CSM_PG_PE pgpe,	CSM_USER_GROUP_ROLE_PG ugrpg, 	CSM_GROUP g, 	CSM_ROLE_PRIVILEGE rp, 	CSM_ROLE r, 	CSM_PRIVILEGE p WHERE ugrpg.role_id = r.role_id AND ugrpg.group_id = g.group_id AND ugrpg.protection_group_id = ANY ( select pg1.protection_group_id from csm_protection_group pg1  where pg1.protection_group_id = pg.protection_group_id OR pg1.protection_group_id =  (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id) ) AND pg.protection_group_id = pgpe.protection_group_id AND pgpe.protection_element_id = pe.protection_element_id AND r.role_id = rp.role_id AND rp.privilege_id = p.privilege_id AND pe.object_id= ''gov.nih.nci.cacoresdk.domain.other.levelassociation.Card'' AND p.privilege_name=''READ'' AND g.group_name IN (:GROUP_NAMES ) AND pe.application_id=:APPLICATION_ID))');
Insert into CSM_FILTER_CLAUSE
   (FILTER_CLAUSE_ID, CLASS_NAME, FILTER_CHAIN, TARGET_CLASS_NAME, TARGET_CLASS_ATTRIBUTE_NAME, 
    TARGET_CLASS_ATTRIBUTE_TYPE, TARGET_CLASS_ALIAS, TARGET_CLASS_ATTRIBUTE_ALIAS, GENERATED_SQL_USER, APPLICATION_ID, 
    UPDATE_DATE, GENERATED_SQL_GROUP)
 Values
   (19, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle - self', 'color', 
    'java.lang.String', NULL, NULL, 'ID in (select table_name_csm_.ID   from HANDLE table_name_csm_ where table_name_csm_.COLOR in ( select pe.attribute_value from csm_protection_group pg, csm_protection_element pe, csm_pg_pe pgpe, csm_user_group_role_pg ugrpg, csm_user u, csm_role_privilege rp, csm_role r, csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.user_id = u.user_id and ugrpg.protection_group_id = ANY (select pg1.protection_group_id from csm_protection_group pg1 where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id = (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id)) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= ''gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle'' and pe.attribute=''color'' and p.privilege_name=''READ'' and u.login_name=:USER_NAME and pe.application_id=:APPLICATION_ID))', 2, 
    TO_DATE('07/09/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'ID in (select table_name_csm_.ID   from HANDLE table_name_csm_ where table_name_csm_.COLOR in (SELECT Distinct pe.attribute_value FROM CSM_PROTECTION_GROUP pg, 	CSM_PROTECTION_ELEMENT pe, 	CSM_PG_PE pgpe,	CSM_USER_GROUP_ROLE_PG ugrpg, 	CSM_GROUP g, 	CSM_ROLE_PRIVILEGE rp, 	CSM_ROLE r, 	CSM_PRIVILEGE p WHERE ugrpg.role_id = r.role_id AND ugrpg.group_id = g.group_id AND ugrpg.protection_group_id = ANY ( select pg1.protection_group_id from csm_protection_group pg1  where pg1.protection_group_id = pg.protection_group_id OR pg1.protection_group_id =  (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id) ) AND pg.protection_group_id = pgpe.protection_group_id AND pgpe.protection_element_id = pe.protection_element_id AND r.role_id = rp.role_id AND rp.privilege_id = p.privilege_id AND pe.object_id= ''gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle'' AND p.privilege_name=''READ'' AND g.group_name IN (:GROUP_NAMES ) AND pe.application_id=:APPLICATION_ID))');
COMMIT;

Insert into CSM_GROUP
   (GROUP_ID, GROUP_NAME, GROUP_DESC, UPDATE_DATE, APPLICATION_ID)
 Values
   (2, 'Group1', 'Group 1 - Access to All PE''s.  Same as user1', TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 2);
Insert into CSM_GROUP
   (GROUP_ID, GROUP_NAME, GROUP_DESC, UPDATE_DATE, APPLICATION_ID)
 Values
   (3, 'Group2', 'Group 2 - Same limited access as user2', TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 2);
Insert into CSM_GROUP
   (GROUP_ID, GROUP_NAME, GROUP_DESC, UPDATE_DATE, APPLICATION_ID)
 Values
   (4, 'Group3', 'Group3 - No access', TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 2);
COMMIT;

Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (766, 4, 24, TO_DATE('05/26/2008 16:33:58', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (779, 1, 119, TO_DATE('07/23/2008 14:52:09', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (773, 7, 40, TO_DATE('06/30/2008 15:14:32', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (774, 7, 5, TO_DATE('06/30/2008 15:14:32', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (775, 7, 39, TO_DATE('06/30/2008 15:14:32', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (776, 7, 41, TO_DATE('06/30/2008 15:14:32', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (777, 7, 114, TO_DATE('06/30/2008 15:14:32', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (778, 7, 38, TO_DATE('06/30/2008 15:14:32', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (670, 1, 100, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (671, 1, 33, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (672, 1, 81, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (673, 1, 108, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (674, 1, 88, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (675, 1, 83, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (676, 1, 77, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (677, 1, 4, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (678, 1, 113, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (679, 1, 17, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (680, 1, 15, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (681, 1, 65, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (682, 1, 95, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (683, 1, 93, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (684, 1, 90, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (685, 1, 103, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (686, 1, 20, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (687, 1, 101, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (688, 1, 18, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (689, 1, 67, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (690, 1, 76, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (691, 1, 92, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (692, 1, 80, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (693, 1, 82, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (694, 1, 116, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (695, 1, 24, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (696, 1, 91, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (697, 1, 98, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (698, 1, 32, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (699, 1, 14, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (700, 1, 23, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (701, 1, 75, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (702, 1, 99, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (703, 1, 16, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (704, 1, 38, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (705, 1, 66, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (706, 1, 37, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (708, 1, 30, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (709, 1, 45, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (710, 1, 35, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (711, 1, 94, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (712, 1, 41, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (713, 1, 86, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (714, 1, 79, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (715, 1, 27, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (716, 1, 13, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (717, 1, 68, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (718, 1, 71, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (719, 1, 26, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (720, 1, 7, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (721, 1, 25, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (722, 1, 115, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (723, 1, 85, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (724, 1, 110, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (725, 1, 36, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (726, 1, 31, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (727, 1, 11, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (728, 1, 74, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (729, 1, 84, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (730, 1, 102, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (731, 1, 97, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (732, 1, 8, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (733, 1, 43, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (734, 1, 42, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (735, 1, 28, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (736, 1, 70, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (737, 1, 12, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (738, 1, 29, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (739, 1, 72, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (740, 1, 44, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (741, 1, 114, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (742, 1, 22, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (743, 1, 40, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (744, 1, 6, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (745, 1, 87, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (746, 1, 21, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (747, 1, 9, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (748, 1, 34, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (749, 1, 104, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (750, 1, 89, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (751, 1, 107, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (752, 1, 19, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (753, 1, 5, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (754, 1, 96, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (755, 1, 73, TO_DATE('12/19/2007 16:40:11', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (765, 1, 39, TO_DATE('12/27/2007 12:41:17', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (107, 2, 11, TO_DATE('07/17/2007 15:30:19', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (108, 2, 24, TO_DATE('07/17/2007 15:30:19', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (110, 2, 8, TO_DATE('07/17/2007 15:30:19', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PG_PE
   (PG_PE_ID, PROTECTION_GROUP_ID, PROTECTION_ELEMENT_ID, UPDATE_DATE)
 Values
   (111, 3, 37, TO_DATE('07/17/2007 15:30:19', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

Insert into CSM_PRIVILEGE
   (PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE)
 Values
   (1, 'CREATE', 'This privilege grants permission to a user to create an entity. This entity can be an object, a database entry, or a resource such as a network connection', TO_DATE('02/28/2007 13:03:03', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PRIVILEGE
   (PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE)
 Values
   (2, 'ACCESS', 'This privilege allows a user to access a particular resource.  ', TO_DATE('02/28/2007 13:03:04', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PRIVILEGE
   (PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE)
 Values
   (3, 'READ', 'This privilege permits the user to read data from a file, URL, socket, database, or an object. ', TO_DATE('02/28/2007 13:03:04', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PRIVILEGE
   (PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE)
 Values
   (4, 'WRITE', 'This privilege allows a user to write data to a file, URL, socket, database, or object. ', TO_DATE('02/28/2007 13:03:04', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PRIVILEGE
   (PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE)
 Values
   (5, 'UPDATE', 'This privilege grants permission at an entity level and signifies that the user is allowed to update and modify data for a particular entity.', TO_DATE('02/28/2007 13:03:04', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PRIVILEGE
   (PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE)
 Values
   (6, 'DELETE', 'This privilege permits a user to delete a logical entity.', TO_DATE('02/28/2007 13:03:04', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_PRIVILEGE
   (PRIVILEGE_ID, PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION, UPDATE_DATE)
 Values
   (7, 'EXECUTE', 'This privilege allows a user to execute a particular resource.', TO_DATE('02/28/2007 13:03:04', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (5, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit', 'issuingBank', 
    NULL, 2, TO_DATE('12/17/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (6, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:24', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (7, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:24', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (8, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:24', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (9, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:24', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (11, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:24', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (12, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:25', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (13, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:25', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (14, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:25', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (15, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:25', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (16, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:25', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (17, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:25', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (18, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:25', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (19, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:25', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (20, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:25', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (21, 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee', 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee', 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (22, 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project', 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project', 'gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (23, 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author', 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author', 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (24, 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book', 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book', 'gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (25, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (26, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (27, 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer', 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer', 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (28, 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive', 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive', 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (29, 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.Key', 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.Key', 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.Key', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (30, 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain', 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain', 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (31, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:26', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (32, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:27', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (33, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:27', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (34, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:27', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (35, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:27', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (36, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:27', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (37, 'gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType', 'gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType', 'gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:27', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (38, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Card', 'Name', 
    NULL, 2, TO_DATE('12/21/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Ace');
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (39, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck', 'id', 
    NULL, 2, TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), '1');
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (40, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:27', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (41, 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit', 'gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit', 'cardCollection', 
    NULL, 2, TO_DATE('12/27/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (42, 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:28', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (43, 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatKey', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:28', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (44, 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerKey', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:28', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (45, 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:28', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (113, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human', NULL, 
    NULL, 2, TO_DATE('12/17/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (114, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank', 'PE for ''name'' attribute of Bank object', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank', 'name', 
    NULL, 2, TO_DATE('12/17/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (1, 'csmupt', 'UPT Super Admin Application', 'csmupt', NULL, 
    NULL, 1, TO_DATE('02/28/2007 13:03:03', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (2, 'sdk', 'sdk Application', 'sdk', NULL, 
    NULL, 1, TO_DATE('04/02/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (71, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (67, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (73, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (75, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (81, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (82, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (86, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (87, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (91, 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight', 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight', 'gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (92, 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Shirt', 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Shirt', 'gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Shirt', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (96, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (97, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (101, 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoublePrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoublePrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.DoublePrimitiveKey', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (102, 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (107, 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (119, 'utensil', NULL, 'gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil', NULL, 
    NULL, 2, TO_DATE('07/23/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (66, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (70, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (83, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (88, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (93, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (98, 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle', 'gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (103, 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (108, 'gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (65, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (68, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (72, 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization', 'gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (74, 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note', 'gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (76, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (77, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (79, 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel', 'gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (80, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (84, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (85, 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator', 'gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (89, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (90, 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song', 'gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (94, 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant', 'gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (95, 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride', 'gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (99, 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (100, 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (104, 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (110, 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey', 'gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey', NULL, 
    NULL, 2, TO_DATE('12/13/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (115, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit.amount', 'PE for Credit.amount attribute', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit', 'amount', 
    NULL, 2, TO_DATE('12/19/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (116, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit.cardNumber', 'PE for Credit.cardNumber attribute', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit', 'cardNumber', 
    NULL, 2, TO_DATE('12/19/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_ELEMENT
   (PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, ATTRIBUTE, 
    PROTECTION_ELEMENT_TYPE, APPLICATION_ID, UPDATE_DATE, ATTRIBUTE_VALUE)
 Values
   (4, 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash', 'gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash', NULL, 
    NULL, 2, TO_DATE('03/01/2007 15:03:24', 'MM/DD/YYYY HH24:MI:SS'), NULL);
COMMIT;

Insert into CSM_PROTECTION_GROUP
   (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, 
    UPDATE_DATE, PARENT_PROTECTION_GROUP_ID)
 Values
   (3, 'AllDataType', NULL, 2, 0, 
    TO_DATE('03/23/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_GROUP
   (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, 
    UPDATE_DATE, PARENT_PROTECTION_GROUP_ID)
 Values
   (1, 'All PEs', 'Contains all of the PEs in the entire test sdk system', 2, 0, 
    TO_DATE('12/19/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_GROUP
   (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, 
    UPDATE_DATE, PARENT_PROTECTION_GROUP_ID)
 Values
   (2, 'Bank', NULL, 2, 0, 
    TO_DATE('03/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_GROUP
   (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, 
    UPDATE_DATE, PARENT_PROTECTION_GROUP_ID)
 Values
   (4, 'Book', NULL, 2, 0, 
    TO_DATE('03/23/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
Insert into CSM_PROTECTION_GROUP
   (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, 
    UPDATE_DATE, PARENT_PROTECTION_GROUP_ID)
 Values
   (7, 'Limited Access', 'JUnit Security Test Group with limited access to a handful of Classes and Attributes', 2, 0, 
    TO_DATE('12/19/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), NULL);
COMMIT;

Insert into CSM_ROLE
   (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, APPLICATION_ID, ACTIVE_FLAG, 
    UPDATE_DATE)
 Values
   (5, 'Update', NULL, 2, 1, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_ROLE
   (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, APPLICATION_ID, ACTIVE_FLAG, 
    UPDATE_DATE)
 Values
   (6, 'Delete', NULL, 2, 1, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_ROLE
   (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, APPLICATION_ID, ACTIVE_FLAG, 
    UPDATE_DATE)
 Values
   (3, 'Create', NULL, 2, 1, 
    TO_DATE('03/23/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_ROLE
   (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, APPLICATION_ID, ACTIVE_FLAG, 
    UPDATE_DATE)
 Values
   (1, 'SuperAdmin', 'SuperAdmin', 2, 1, 
    TO_DATE('03/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_ROLE
   (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION, APPLICATION_ID, ACTIVE_FLAG, 
    UPDATE_DATE)
 Values
   (2, 'Read', NULL, 2, 1, 
    TO_DATE('03/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (14, 5, 5);
Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (15, 6, 6);
Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (1, 2, 3);
Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (2, 1, 5);
Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (3, 1, 1);
Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (4, 1, 2);
Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (5, 1, 7);
Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (6, 1, 4);
Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (7, 1, 3);
Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (8, 1, 6);
Insert into CSM_ROLE_PRIVILEGE
   (ROLE_PRIVILEGE_ID, ROLE_ID, PRIVILEGE_ID)
 Values
   (9, 3, 1);
COMMIT;

Insert into CSM_USER
   (USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, ORGANIZATION, 
    DEPARTMENT, TITLE, PHONE_NUMBER, PASSWORD, EMAIL_ID, 
    START_DATE, END_DATE, UPDATE_DATE)
 Values
   (15, 'sdkqa', 'SDK', 'QA', NULL, 
    NULL, NULL, NULL, 'MNOl1igujyI=', NULL, 
    NULL, NULL, TO_DATE('07/23/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER
   (USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, ORGANIZATION, 
    DEPARTMENT, TITLE, PHONE_NUMBER, PASSWORD, EMAIL_ID, 
    START_DATE, END_DATE, UPDATE_DATE)
 Values
   (17, '/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=SDKTestUser', 'Grid', 'User', NULL, 
    NULL, NULL, NULL, NULL, NULL, 
    NULL, NULL, TO_DATE('08/18/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER
   (USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, ORGANIZATION, 
    DEPARTMENT, TITLE, PHONE_NUMBER, PASSWORD, EMAIL_ID, 
    START_DATE, END_DATE, UPDATE_DATE)
 Values
   (13, '/O=caBIG/OU=caGrid/OU=NCICB DEV LOA1/OU=Dorian/CN=SDKUser1', 'user1', 'junit', NULL, 
    NULL, NULL, NULL, '/3+jJvECOxQI2TxoU2+Xvw==', NULL, 
    NULL, NULL, TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER
   (USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, ORGANIZATION, 
    DEPARTMENT, TITLE, PHONE_NUMBER, PASSWORD, EMAIL_ID, 
    START_DATE, END_DATE, UPDATE_DATE)
 Values
   (14, '/O=caBIG/OU=caGrid/OU=NCICB DEV LOA1/OU=Dorian/CN=SDKUser2', 'user2', 'junit', NULL, 
    NULL, NULL, NULL, '/3+jJvECOxQI2TxoU2+Xvw==', NULL, 
    NULL, NULL, TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER
   (USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, ORGANIZATION, 
    DEPARTMENT, TITLE, PHONE_NUMBER, PASSWORD, EMAIL_ID, 
    START_DATE, END_DATE, UPDATE_DATE)
 Values
   (9, 'patelsat', 'Satish', 'Patel', NULL, 
    NULL, NULL, NULL, 'zJPWCwDeSgG8j2uyHEABIQ==', NULL, 
    NULL, NULL, TO_DATE('03/23/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER
   (USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, ORGANIZATION, 
    DEPARTMENT, TITLE, PHONE_NUMBER, PASSWORD, EMAIL_ID, 
    START_DATE, END_DATE, UPDATE_DATE)
 Values
   (1, 'SuperAdmin', 'Super', 'Admin', NULL, 
    NULL, NULL, NULL, 'zJPWCwDeSgG8j2uyHEABIQ==', NULL, 
    NULL, NULL, TO_DATE('05/23/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER
   (USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, ORGANIZATION, 
    DEPARTMENT, TITLE, PHONE_NUMBER, PASSWORD, EMAIL_ID, 
    START_DATE, END_DATE, UPDATE_DATE)
 Values
   (12, 'dumitrud', 'Dan', 'Dumitru', NULL, 
    NULL, NULL, NULL, 'qN+MnXquuqO8j2uyHEABIQ==', 'dumitrud@mail.nih.gov', 
    NULL, NULL, TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER
   (USER_ID, LOGIN_NAME, FIRST_NAME, LAST_NAME, ORGANIZATION, 
    DEPARTMENT, TITLE, PHONE_NUMBER, PASSWORD, EMAIL_ID, 
    START_DATE, END_DATE, UPDATE_DATE)
 Values
   (16, 'testsdkqa', 'testsdkqa', 'testsdkqa', NULL, 
    NULL, NULL, NULL, NULL, NULL, 
    NULL, NULL, TO_DATE('07/23/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

Insert into CSM_USER_GROUP
   (USER_GROUP_ID, USER_ID, GROUP_ID)
 Values
   (2, 13, 2);
Insert into CSM_USER_GROUP
   (USER_GROUP_ID, USER_ID, GROUP_ID)
 Values
   (3, 14, 3);
Insert into CSM_USER_GROUP
   (USER_GROUP_ID, USER_ID, GROUP_ID)
 Values
   (4, 15, 4);
Insert into CSM_USER_GROUP
   (USER_GROUP_ID, USER_ID, GROUP_ID)
 Values
   (5, 15, 3);
Insert into CSM_USER_GROUP
   (USER_GROUP_ID, USER_ID, GROUP_ID)
 Values
   (6, 15, 2);
COMMIT;

Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (23, NULL, 2, 3, 1, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (24, NULL, 2, 2, 1, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (47, 15, NULL, 1, 3, 
    TO_DATE('07/23/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (40, NULL, 3, 3, 7, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (41, NULL, 3, 2, 7, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (42, 15, NULL, 3, 1, 
    TO_DATE('07/23/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (43, 15, NULL, 6, 1, 
    TO_DATE('07/23/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (44, 15, NULL, 2, 1, 
    TO_DATE('07/23/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (45, 15, NULL, 1, 1, 
    TO_DATE('07/23/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (46, 15, NULL, 5, 1, 
    TO_DATE('07/23/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (34, 13, NULL, 6, 1, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (35, 13, NULL, 5, 1, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (16, 12, NULL, 1, 1, 
    TO_DATE('12/12/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (48, 17, NULL, 2, 1, 
    TO_DATE('08/18/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (1, 1, NULL, 1, 1, 
    TO_DATE('03/01/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (10, 9, NULL, 1, 1, 
    TO_DATE('03/23/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (17, 13, NULL, 3, 1, 
    TO_DATE('12/19/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (18, 13, NULL, 2, 1, 
    TO_DATE('12/19/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (38, 14, NULL, 3, 7, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CSM_USER_GROUP_ROLE_PG
   (USER_GROUP_ROLE_PG_ID, USER_ID, GROUP_ID, ROLE_ID, PROTECTION_GROUP_ID, 
    UPDATE_DATE)
 Values
   (39, 14, NULL, 2, 7, 
    TO_DATE('06/30/2008 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

Insert into CSM_USER_PE
   (USER_PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_ID, USER_ID)
 Values
   (3, 1, 1);
Insert into CSM_USER_PE
   (USER_PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_ID, USER_ID)
 Values
   (7, 2, 9);
Insert into CSM_USER_PE
   (USER_PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_ID, USER_ID)
 Values
   (8, 2, 1);
COMMIT;


ALTER TABLE CSM_APPLICATION ADD CONSTRAINT PK_APPLICATION 
PRIMARY KEY (APPLICATION_ID) 
;

ALTER TABLE CSM_GROUP ADD CONSTRAINT PK_GROUP 
PRIMARY KEY (GROUP_ID) 
;

ALTER TABLE CSM_PG_PE ADD CONSTRAINT PK_PG_PE 
PRIMARY KEY (PG_PE_ID) 
;

ALTER TABLE CSM_PRIVILEGE ADD CONSTRAINT PK_PRIVILEGE 
PRIMARY KEY (PRIVILEGE_ID) 
;

ALTER TABLE CSM_PROTECTION_ELEMENT ADD CONSTRAINT PK_PROTECTION_ELEMENT 
PRIMARY KEY (PROTECTION_ELEMENT_ID) 
;

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT PK_PROTECTION_GROUP 
PRIMARY KEY (PROTECTION_GROUP_ID) 
;

ALTER TABLE CSM_ROLE ADD CONSTRAINT PK_ROLE 
PRIMARY KEY (ROLE_ID) 
;

ALTER TABLE CSM_FILTER_CLAUSE ADD CONSTRAINT PK_FILTER_CLAUSE 
PRIMARY KEY (FILTER_CLAUSE_ID) 
;

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT PK_ROLE_PRIVILEGE 
PRIMARY KEY (ROLE_PRIVILEGE_ID) 
;

ALTER TABLE CSM_USER ADD CONSTRAINT PK_USER 
PRIMARY KEY (USER_ID) 
;

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT PK_USER_GROUP 
PRIMARY KEY (USER_GROUP_ID) 
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT PK_USER_GROUP_ROLE_PG 
PRIMARY KEY (USER_GROUP_ROLE_PG_ID) 
;

ALTER TABLE CSM_USER_PE ADD CONSTRAINT PK_USER_PROTECTION_ELEMENT 
PRIMARY KEY (USER_PROTECTION_ELEMENT_ID) 
;


ALTER TABLE CSM_APPLICATION
ADD CONSTRAINT UQ_APPLICATION_NAME UNIQUE (APPLICATION_NAME)
;
ALTER TABLE CSM_GROUP
ADD CONSTRAINT UQ_GROUP_GROUP_NAME UNIQUE (APPLICATION_ID, GROUP_NAME)
;
ALTER TABLE CSM_PG_PE
ADD CONSTRAINT UQ_PG_PE_PG_PE_ID UNIQUE (PROTECTION_ELEMENT_ID, PROTECTION_GROUP_ID)
;
ALTER TABLE CSM_PRIVILEGE
ADD CONSTRAINT UQ_PRIVILEGE_NAME UNIQUE (PRIVILEGE_NAME)
;
ALTER TABLE CSM_PROTECTION_ELEMENT
ADD CONSTRAINT UQ_PE_OBJ_ATT_APP_ID UNIQUE (OBJECT_ID, ATTRIBUTE, APPLICATION_ID)
;
ALTER TABLE CSM_PROTECTION_GROUP
ADD CONSTRAINT UQ_PG_PG_NAME UNIQUE (APPLICATION_ID, PROTECTION_GROUP_NAME)
;
ALTER TABLE CSM_ROLE
ADD CONSTRAINT UQ_ROLE_ROLE_NAME UNIQUE (APPLICATION_ID, ROLE_NAME)
;
ALTER TABLE CSM_ROLE_PRIVILEGE
ADD CONSTRAINT UQ_ROLE_ID_PRIVILEGE_ID UNIQUE (PRIVILEGE_ID, ROLE_ID)
;
ALTER TABLE CSM_USER
ADD CONSTRAINT UQ_LOGIN_NAME UNIQUE (LOGIN_NAME)
;
ALTER TABLE CSM_USER_PE
ADD CONSTRAINT UQ_USER_PE_PE_ID UNIQUE (USER_ID, PROTECTION_ELEMENT_ID)
;

ALTER TABLE CSM_GROUP ADD CONSTRAINT FK_CSM_GROUP_CSM_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_FILTER_CLAUSE ADD CONSTRAINT FK_CSM_FC_CSM_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PG_PE ADD CONSTRAINT FK_PG_PE_PE 
FOREIGN KEY (PROTECTION_ELEMENT_ID) REFERENCES CSM_PROTECTION_ELEMENT (PROTECTION_ELEMENT_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PG_PE ADD CONSTRAINT FK_PG_PE_PG 
FOREIGN KEY (PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PROTECTION_ELEMENT ADD CONSTRAINT FK_PE_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT FK_PG_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT FK_PG_PG 
FOREIGN KEY (PARENT_PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
;

ALTER TABLE CSM_ROLE ADD CONSTRAINT FK_ROLE_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT FK_ROLE_PRIVILEGE_PRIVILEGE 
FOREIGN KEY (PRIVILEGE_ID) REFERENCES CSM_PRIVILEGE (PRIVILEGE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT FK_ROLE_PRIVILEGE_ROLE 
FOREIGN KEY (ROLE_ID) REFERENCES CSM_ROLE (ROLE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT FK_USER_GROUP_GROUP 
FOREIGN KEY (GROUP_ID) REFERENCES CSM_GROUP (GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT FK_USER_GROUP_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_GROUP 
FOREIGN KEY (GROUP_ID) REFERENCES CSM_GROUP (GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_PG 
FOREIGN KEY (PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_ROLE 
FOREIGN KEY (ROLE_ID) REFERENCES CSM_ROLE (ROLE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_PE ADD CONSTRAINT FK_USER_PE_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_PE ADD CONSTRAINT FK_USER_PE_PE 
FOREIGN KEY (PROTECTION_ELEMENT_ID) REFERENCES CSM_PROTECTION_ELEMENT (PROTECTION_ELEMENT_ID)
ON DELETE CASCADE
;

COMMIT;
