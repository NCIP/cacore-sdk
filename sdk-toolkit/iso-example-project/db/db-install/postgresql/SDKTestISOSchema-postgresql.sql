/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
L*/

-- This script assumes that 'public' schema is used for the particular database in postgres.
-- Structure =
-- > database->schema->tables
-- > database->schema->trigger
-- > database->schema->functions
-- > database->schema->indexes
-- Refer the postgres documentation for further details.
--
-- SET statement_timeout = 0;
-- SET client_encoding = 'UTF8';
-- SET standard_conforming_strings = off;
-- SET check_function_bodies = false;
-- SET client_min_messages = warning;
-- SET escape_string_warning = off;

-- CREATE SCHEMA public;

SET search_path TO public;

DROP TABLE IF EXISTS address CASCADE;
CREATE TABLE address (
    ID integer NOT NULL,
    ZIP character varying(50)
);

DROP TABLE IF EXISTS album CASCADE;
CREATE TABLE album (
    ID numeric NOT NULL,
    TITLE character varying(50)
);

DROP TABLE IF EXISTS album_song CASCADE;
CREATE TABLE album_song (
    ALBUM_ID integer NOT NULL,
    SONG_ID integer NOT NULL
);

DROP TABLE IF EXISTS all_data_type CASCADE;
CREATE TABLE all_data_type (
    ID integer NOT NULL,
    INT_VALUE integer,
    STRING_VALUE character varying(50),
    DOUBLE_VALUE numeric,
    FLOAT_VALUE numeric,
    DATE_VALUE timestamp without time zone,
    BOOLEAN_VALUE character(1),
    CLOB_VALUE text,
    BLOB_VALUE bytea,
    CHARACTER_VALUE character(1),
    LONG_VALUE numeric,
    DOUBLE_PRIMITIVE_VALUE numeric,
    INT_PRIMITIVE_VALUE integer,
    DATE_PRIMITIVE_VALUE timestamp without time zone,
    STRING_PRIMITIVE_VALUE character varying(50),
    FLOAT_PRIMITIVE_VALUE numeric,
    BOOLEAN_PRIMITIVE_VALUE character(1),
    CHARACTER_PRIMITIVE_VALUE character(1),
    LONG_PRIMITIVE_VALUE numeric
);

DROP TABLE IF EXISTS all_data_type_string_coll CASCADE;
CREATE TABLE all_data_type_string_coll (
    ALL_DATA_TYPE_ID integer NOT NULL,
    STRING_VALUE character varying(50)
);

DROP TABLE IF EXISTS all_validation_type CASCADE;
CREATE TABLE all_validation_type (
    ID integer NOT NULL,
    EMAIL character varying(50),
    FUTURE timestamp without time zone,
    LENGTH character varying(50),
    MAX_NUMERIC numeric,
    PAST timestamp without time zone,
    MAX_STRING character varying(50),
    MIN_NUMERIC numeric,
    MIN_STRING character varying(50),
    NOT_EMPTY character varying(50),
    NOT_NULL character varying(50),
    RANGE_STRING character varying(50),
    RANGE_NUMERIC numeric,
    PATTERN character varying(50)
);

DROP TABLE IF EXISTS assistant CASCADE;
CREATE TABLE assistant (
    ID integer NOT NULL,
    NAME character varying(50),
    PROFESSOR_ID integer
);

DROP TABLE IF EXISTS assistant_professor CASCADE;
CREATE TABLE assistant_professor (
    PROFESSOR_ID integer NOT NULL,
    JOINING_YEAR integer
);

DROP TABLE IF EXISTS associate_professor CASCADE;
CREATE TABLE associate_professor (
    PROFESSOR_ID integer NOT NULL,
    YEARS_SERVED integer
);

DROP TABLE IF EXISTS author CASCADE;
CREATE TABLE author (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS author_book CASCADE;
CREATE TABLE author_book (
    AUTHOR_ID integer NOT NULL,
    BOOK_ID integer NOT NULL
);

DROP TABLE IF EXISTS bag CASCADE;
CREATE TABLE bag (
    ID integer NOT NULL,
    STYLE character varying(50)
);

DROP TABLE IF EXISTS bag_handle CASCADE;
CREATE TABLE bag_handle (
    BAG_ID integer NOT NULL,
    HANDLE_ID integer NOT NULL
);

DROP TABLE IF EXISTS bank CASCADE;
CREATE TABLE bank (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS book CASCADE;
CREATE TABLE book (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS bride CASCADE;
CREATE TABLE bride (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS bride_father_in_law CASCADE;
CREATE TABLE bride_father_in_law (
    BRIDE_ID integer NOT NULL,
    IN_LAW_ID integer NOT NULL
);

DROP TABLE IF EXISTS bride_mother_in_law CASCADE;
CREATE TABLE bride_mother_in_law (
    BRIDE_D integer NOT NULL,
    IN_LAW_ID integer NOT NULL
);

DROP TABLE IF EXISTS button CASCADE;
CREATE TABLE button (
    ID integer NOT NULL,
    HOLES integer
);

DROP TABLE IF EXISTS calculator CASCADE;
CREATE TABLE calculator (
    ID integer NOT NULL,
    DISCRIMINATOR character varying(150),
    BRAND character varying(50)
);

DROP TABLE IF EXISTS card CASCADE;
CREATE TABLE card (
    ID integer NOT NULL,
    NAME character varying(50),
    SUIT_ID integer,
    IMAGE text
);

DROP TABLE IF EXISTS cash CASCADE;
CREATE TABLE cash (
    PAYMENT_ID integer NOT NULL
);

DROP TABLE IF EXISTS chain CASCADE;
CREATE TABLE chain (
    ID integer NOT NULL,
    METAL character varying(50)
);

DROP TABLE IF EXISTS chain_pendant CASCADE;
CREATE TABLE chain_pendant (
    CHAIN_ID integer NOT NULL,
    PENDANT_ID integer NOT NULL
);

DROP TABLE IF EXISTS character_key CASCADE;
CREATE TABLE character_key (
    ID character(1) NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS character_primitive_key CASCADE;
CREATE TABLE character_primitive_key (
    ID character(1) NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS chef CASCADE;
CREATE TABLE chef (
    ID integer NOT NULL,
    NAME character varying(50),
    RESTAURANT_ID integer
);

DROP TABLE IF EXISTS child CASCADE;
CREATE TABLE child (
    ID integer NOT NULL,
    NAME character varying(50),
    FATHER_ID integer,
    MOTHER_ID integer
);

DROP TABLE IF EXISTS computer CASCADE;
CREATE TABLE computer (
    ID integer NOT NULL,
    TYPE character varying(50)
);

DROP TABLE IF EXISTS credit CASCADE;
CREATE TABLE credit (
    PAYMENT_ID integer NOT NULL,
    CARD_NUMBER character varying(50),
    BANK_ID integer
);

DROP TABLE IF EXISTS crt_monitor CASCADE;
CREATE TABLE crt_monitor (
    MONITOR_ID integer NOT NULL,
    REFRESH_RATE integer
);

DROP TABLE IF EXISTS currency CASCADE;
CREATE TABLE currency (
    ID integer NOT NULL,
    DISCRIMINATOR character varying(150),
    COUNTRY character varying(50),
    VALUE integer
);

DROP TABLE IF EXISTS deck CASCADE;
CREATE TABLE deck (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS designer CASCADE;
CREATE TABLE designer (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS dessert CASCADE;
CREATE TABLE dessert (
    ID integer NOT NULL,
    TOPPING character varying(50),
    FILLING character varying(50),
    DISCRIMINATOR character varying(150)
);

DROP TABLE IF EXISTS dessert_utensil CASCADE;
CREATE TABLE dessert_utensil (
    DESSERT_ID integer NOT NULL,
    UTENSIL_ID integer NOT NULL
);

DROP TABLE IF EXISTS display CASCADE;
CREATE TABLE display (
    ID integer NOT NULL,
    WIDTH integer,
    HEIGHT integer
);

DROP TABLE IF EXISTS dog CASCADE;
CREATE TABLE dog (
    ID integer NOT NULL,
    BREED character varying(50),
    GENDER character varying(50)
);

DROP TABLE IF EXISTS double_key CASCADE;
CREATE TABLE double_key (
    ID numeric NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS double_primitive_key CASCADE;
CREATE TABLE double_primitive_key (
    ID numeric NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS element CASCADE;
CREATE TABLE element (
    ID integer NOT NULL,
    NAME character varying(50),
    PARENT_ELEMENT_ID integer
);

DROP TABLE IF EXISTS employee CASCADE;
CREATE TABLE employee (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS employee_project CASCADE;
CREATE TABLE employee_project (
    EMPLOYEE_ID integer NOT NULL,
    PROJECT_ID integer NOT NULL
);

DROP TABLE IF EXISTS fish CASCADE;
CREATE TABLE fish (
    ID integer NOT NULL,
    GENERA character varying(50),
    PRIMARY_COLOR character varying(50),
    FIN_SIZE integer,
    DISCRIMINATOR character varying(150),
    TANK_ID integer,
    TANK_DISCRIMINATOR character varying(50)
);

DROP TABLE IF EXISTS flight CASCADE;
CREATE TABLE flight (
    ID integer NOT NULL,
    DESTINATION character varying(50)
);

DROP TABLE IF EXISTS flight_passanger CASCADE;
CREATE TABLE flight_passanger (
    FLIGHT_ID integer NOT NULL,
    PASSANGER_ID integer NOT NULL
);

DROP TABLE IF EXISTS float_key CASCADE;
CREATE TABLE float_key (
    ID numeric NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS float_primitive_key CASCADE;
CREATE TABLE float_primitive_key (
    ID numeric NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS freshwater_fish_tank CASCADE;
CREATE TABLE freshwater_fish_tank (
    ID integer NOT NULL,
    SHAPE character varying(50),
    NUM_GALLONS integer,
    FILTER_MODEL character varying(50)
);

DROP TABLE IF EXISTS goverment CASCADE;
CREATE TABLE goverment (
    ID integer NOT NULL,
    DISCRIMINATOR character varying(150),
    COUNTRY character varying(50),
    PRIME_MINISTER character varying(50),
    PRESIDENT character varying(50),
    DEMOCRATIC_DISCRIMINATOR character varying(50)
);

DROP TABLE IF EXISTS graduate_student CASCADE;
CREATE TABLE graduate_student (
    STUDENT_ID integer NOT NULL,
    PROJECT_NAME character varying(50)
);

DROP TABLE IF EXISTS graphic_calculator CASCADE;
CREATE TABLE graphic_calculator (
    CALCULATOR_ID integer NOT NULL
);

DROP TABLE IF EXISTS hand CASCADE;
CREATE TABLE hand (
    ID integer NOT NULL
);

DROP TABLE IF EXISTS hand_card CASCADE;
CREATE TABLE hand_card (
    HAND_ID integer NOT NULL,
    CARD_ID integer NOT NULL
);

DROP TABLE IF EXISTS handle CASCADE;
CREATE TABLE handle (
    ID integer NOT NULL,
    COLOR character varying(50)
);

DROP TABLE IF EXISTS hard_drive CASCADE;
CREATE TABLE hard_drive (
    ID integer NOT NULL,
    DRIVE_SIZE integer,
    COMPUTER_ID integer
);

DROP TABLE IF EXISTS hi_value CASCADE;
CREATE TABLE hi_value (
    NEXT_VALUE numeric
);

DROP TABLE IF EXISTS hl7_data_type CASCADE;
CREATE TABLE hl7_data_type (
    ID integer NOT NULL,
    ROOT character varying(50),
    EXTENSION character varying(50),
    XML character varying(512)
);

DROP TABLE IF EXISTS human CASCADE;
CREATE TABLE human (
    MAMMAL_ID integer NOT NULL,
    DIET character varying(50)
);

DROP TABLE IF EXISTS in_law CASCADE;
CREATE TABLE in_law (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS integer_key CASCADE;
CREATE TABLE integer_key (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS integer_primitive_key CASCADE;
CREATE TABLE integer_primitive_key (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS keychain CASCADE;
CREATE TABLE keychain (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS latch_key CASCADE;
CREATE TABLE latch_key (
    ID integer NOT NULL,
    TYPE character varying(50),
    KEYCHAIN_ID integer
);

DROP TABLE IF EXISTS lcd_monitor CASCADE;
CREATE TABLE lcd_monitor (
    MONITOR_ID integer NOT NULL,
    DPI_SUPPORTED integer
);

DROP TABLE IF EXISTS long_key CASCADE;
CREATE TABLE long_key (
    ID numeric NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS long_primitive_key CASCADE;
CREATE TABLE long_primitive_key (
    ID numeric NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS luggage CASCADE;
CREATE TABLE luggage (
    ID integer NOT NULL,
    DISCRIMINATOR character varying(150),
    CAPACITY integer,
    KEY_CODE integer,
    EXPANDABLE character varying(1),
    WHEEL_ID integer
);

DROP TABLE IF EXISTS mammal CASCADE;
CREATE TABLE mammal (
    ID integer NOT NULL,
    HAIR_COLOR character varying(50)
);

DROP TABLE IF EXISTS monitor CASCADE;
CREATE TABLE monitor (
    DISPLAY_ID integer NOT NULL,
    BRAND character varying(50)
);

DROP TABLE IF EXISTS no_id_key CASCADE;
CREATE TABLE no_id_key (
    MY_KEY integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS orderline CASCADE;
CREATE TABLE orderline (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS organization CASCADE;
CREATE TABLE organization (
    ID integer NOT NULL,
    DISCRIMINATOR character varying(150),
    NAME character varying(50),
    AGENCY_BUDGET integer,
    CEO character varying(50)
);

DROP TABLE IF EXISTS parent CASCADE;
CREATE TABLE parent (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS passanger CASCADE;
CREATE TABLE passanger (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS payment CASCADE;
CREATE TABLE payment (
    ID integer NOT NULL,
    AMOUNT integer
);

DROP TABLE IF EXISTS pendant CASCADE;
CREATE TABLE pendant (
    ID integer NOT NULL,
    SHAPE character varying(50)
);

DROP TABLE IF EXISTS person CASCADE;
CREATE TABLE person (
    ID integer NOT NULL,
    NAME character varying(50),
    ADDRESS_ID integer
);

DROP TABLE IF EXISTS private_teacher CASCADE;
CREATE TABLE private_teacher (
    TEACHER_ID integer NOT NULL,
    YEARS_EXPERIENCE integer
);

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (
    ID integer NOT NULL,
    NAME character varying(50),
    ORDERLINE_ID integer
);

DROP TABLE IF EXISTS professor CASCADE;
CREATE TABLE professor (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS project CASCADE;
CREATE TABLE project (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS pupil CASCADE;
CREATE TABLE pupil (
    ID integer NOT NULL,
    NAME character varying(50),
    TEACHER_ID integer
);

DROP TABLE IF EXISTS restaurant CASCADE;
CREATE TABLE restaurant (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS saltwater_fish_tank CASCADE;
CREATE TABLE saltwater_fish_tank (
    ID integer NOT NULL,
    SHAPE character varying(50),
    NUM_GALLONS integer,
    PROTEIN_SKIMMER_MODEL character varying(50)
);

DROP TABLE IF EXISTS saltwater_fish_tank_substrate CASCADE;
CREATE TABLE saltwater_fish_tank_substrate (
    SALTWATER_FISH_TANK_ID integer NOT NULL,
    SUBSTRATE_ID integer NOT NULL
);

DROP TABLE IF EXISTS shirt CASCADE;
CREATE TABLE shirt (
    ID integer NOT NULL,
    STYLE character varying(50)
);

DROP TABLE IF EXISTS shirt_button CASCADE;
CREATE TABLE shirt_button (
    SHIRT_ID integer NOT NULL,
    BUTTON_ID integer NOT NULL
);

DROP TABLE IF EXISTS shoes CASCADE;
CREATE TABLE shoes (
    ID integer NOT NULL,
    DISCRIMINATOR character varying(150),
    COLOR character varying(50),
    SPORTS_TYPE character varying(50),
    DESIGNER_ID integer
);

DROP TABLE IF EXISTS song CASCADE;
CREATE TABLE song (
    ID integer NOT NULL,
    TITLE character varying(50)
);

DROP TABLE IF EXISTS string_key CASCADE;
CREATE TABLE string_key (
    ID character varying(50) NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS string_primitive_key CASCADE;
CREATE TABLE string_primitive_key (
    ID character varying(50) NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS student CASCADE;
CREATE TABLE student (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS substrate CASCADE;
CREATE TABLE substrate (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS suit CASCADE;
CREATE TABLE suit (
    ID integer NOT NULL,
    NAME character varying(50),
    DECK_ID integer
);

DROP TABLE IF EXISTS tank_accessory CASCADE;
CREATE TABLE tank_accessory (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS tank_tank_accessory CASCADE;
CREATE TABLE tank_tank_accessory (
    TANK_ID integer NOT NULL,
    TANK_ACCESSORY_ID integer NOT NULL,
    TANK_DISCRIMINATOR character varying(150) NOT NULL
);

DROP TABLE IF EXISTS teacher CASCADE;
CREATE TABLE teacher (
    ID integer NOT NULL,
    NAME character varying(50),
    NULL_FLAVOR character varying(50)
);

DROP TABLE IF EXISTS tenured_professor CASCADE;
CREATE TABLE tenured_professor (
    PROFESSOR_ID integer NOT NULL,
    TENURED_YEAR integer
);

DROP TABLE IF EXISTS undergraduate_student CASCADE;
CREATE TABLE undergraduate_student (
    STUDENT_ID integer NOT NULL
);

DROP TABLE IF EXISTS utensil CASCADE;
CREATE TABLE utensil (
    ID integer NOT NULL,
    NAME character varying(50)
);

DROP TABLE IF EXISTS wheel CASCADE;
CREATE TABLE wheel (
    ID integer NOT NULL,
    RADIUS integer
);

INSERT INTO address (ID, ZIP) VALUES (1, 'Zip1');
INSERT INTO address (ID, ZIP) VALUES (2, 'Zip2');
INSERT INTO address (ID, ZIP) VALUES (3, 'Zip3');
INSERT INTO address (ID, ZIP) VALUES (4, 'Zip4');
INSERT INTO address (ID, ZIP) VALUES (5, 'Zip5');

INSERT INTO album (ID, TITLE) VALUES (1, 'Venetian Oboe Concertos');
INSERT INTO album (ID, TITLE) VALUES (2, 'The Cello');

INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (1, 1);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (1, 2);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (1, 3);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (1, 4);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (1, 5);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (1, 6);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (2, 7);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (2, 8);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (2, 9);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (2, 10);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (2, 11);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (3, 14);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (4, 15);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (6, 17);
INSERT INTO album_song (ALBUM_ID, SONG_ID) VALUES (7, 18);

INSERT INTO all_data_type (ID, INT_VALUE, STRING_VALUE, DOUBLE_VALUE, FLOAT_VALUE, DATE_VALUE, BOOLEAN_VALUE, CLOB_VALUE, CHARACTER_VALUE, LONG_VALUE, DOUBLE_PRIMITIVE_VALUE, INT_PRIMITIVE_VALUE, DATE_PRIMITIVE_VALUE, STRING_PRIMITIVE_VALUE, FLOAT_PRIMITIVE_VALUE, BOOLEAN_PRIMITIVE_VALUE, CHARACTER_PRIMITIVE_VALUE, LONG_PRIMITIVE_VALUE) VALUES (1, -1, ',./-+/*&&()||==''%!\\', -1.1, 1.1, '2011-11-11 00:00:00', '1', '0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012', 'a', 1000001.0, 10001.0, 11, '2007-10-01 00:00:00', 'abc', 10.01, '1', 'a', 1000001.0);
INSERT INTO all_data_type (ID, INT_VALUE, STRING_VALUE, DOUBLE_VALUE, FLOAT_VALUE, DATE_VALUE, BOOLEAN_VALUE, CLOB_VALUE, CHARACTER_VALUE, LONG_VALUE, DOUBLE_PRIMITIVE_VALUE, INT_PRIMITIVE_VALUE, DATE_PRIMITIVE_VALUE, STRING_PRIMITIVE_VALUE, FLOAT_PRIMITIVE_VALUE, BOOLEAN_PRIMITIVE_VALUE, CHARACTER_PRIMITIVE_VALUE, LONG_PRIMITIVE_VALUE) VALUES (2, 0, '''Steve''s Test''', 0.0, 222.22, '2012-12-12 00:00:00', '0', '0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012', 'b', 1000002.0, 10002.0, 12, '2007-10-02 00:00:00', 'def', 10.02, '0', 'b', 1000002.0);
INSERT INTO all_data_type (ID, INT_VALUE, STRING_VALUE, DOUBLE_VALUE, FLOAT_VALUE, DATE_VALUE, BOOLEAN_VALUE, CLOB_VALUE, CHARACTER_VALUE, LONG_VALUE, DOUBLE_PRIMITIVE_VALUE, INT_PRIMITIVE_VALUE, DATE_PRIMITIVE_VALUE, STRING_PRIMITIVE_VALUE, FLOAT_PRIMITIVE_VALUE, BOOLEAN_PRIMITIVE_VALUE, CHARACTER_PRIMITIVE_VALUE, LONG_PRIMITIVE_VALUE) VALUES (3, 1, '~!@#$%^&*()_+-=|:<>?[];'',./-+/*&&()||==''%!''', 1.1, 333.33, '2003-03-03 00:00:00', '1', '0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012', 'c', 1000003.0, 10003.0, 13, '2007-10-03 00:00:00', 'ghi', 10.03, '1', 'c', 1000003.0);
INSERT INTO all_data_type (ID, INT_VALUE, STRING_VALUE, DOUBLE_VALUE, FLOAT_VALUE, DATE_VALUE, BOOLEAN_VALUE, CLOB_VALUE, CHARACTER_VALUE, LONG_VALUE, DOUBLE_PRIMITIVE_VALUE, INT_PRIMITIVE_VALUE, DATE_PRIMITIVE_VALUE, STRING_PRIMITIVE_VALUE, FLOAT_PRIMITIVE_VALUE, BOOLEAN_PRIMITIVE_VALUE, CHARACTER_PRIMITIVE_VALUE, LONG_PRIMITIVE_VALUE) VALUES (4, 10000, '01234567890123456789012345678901234567890123456789', 10000.0, 444.44, '2004-04-04 00:00:00', '0', '0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012', 'd', 1000004.0, 10004.0, 14, '2007-10-04 00:00:00', 'jkl', 10.04, '0', 'd', 1000004.0);
INSERT INTO all_data_type (ID, INT_VALUE, STRING_VALUE, DOUBLE_VALUE, FLOAT_VALUE, DATE_VALUE, BOOLEAN_VALUE, CLOB_VALUE, CHARACTER_VALUE, LONG_VALUE, DOUBLE_PRIMITIVE_VALUE, INT_PRIMITIVE_VALUE, DATE_PRIMITIVE_VALUE, STRING_PRIMITIVE_VALUE, FLOAT_PRIMITIVE_VALUE, BOOLEAN_PRIMITIVE_VALUE, CHARACTER_PRIMITIVE_VALUE, LONG_PRIMITIVE_VALUE) VALUES (5, 5, 'String_Value5', 555.55, 555.55, '2005-05-05 00:00:00', '1', '0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012', 'e', 1000005.0, 10005.0, 15, '2007-10-05 00:00:00', 'mno', 10.05, '1', 'e', 1000005.0);

INSERT INTO all_data_type_string_coll (ALL_DATA_TYPE_ID, STRING_VALUE) VALUES (1, 'String_Collection 1');
INSERT INTO all_data_type_string_coll (ALL_DATA_TYPE_ID, STRING_VALUE) VALUES (1, 'String_Collection 2');
INSERT INTO all_data_type_string_coll (ALL_DATA_TYPE_ID, STRING_VALUE) VALUES (1, 'String_Collection 3');

INSERT INTO all_validation_type (ID, EMAIL, FUTURE, LENGTH, MAX_NUMERIC, PAST, MAX_STRING, MIN_NUMERIC, MIN_STRING, NOT_EMPTY, NOT_NULL, RANGE_STRING, RANGE_NUMERIC, PATTERN) VALUES (1, 'name@mail.nih.gov', '2008-05-15 00:00:00', '123', 999.0, NULL, '999', 1.0, '1', 'abc', 'abc', '3', 3.0, 'cat');
INSERT INTO all_validation_type (ID, EMAIL, FUTURE, LENGTH, MAX_NUMERIC, PAST, MAX_STRING, MIN_NUMERIC, MIN_STRING, NOT_EMPTY, NOT_NULL, RANGE_STRING, RANGE_NUMERIC, PATTERN) VALUES (10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'asfdasdf');
INSERT INTO all_validation_type (ID, EMAIL, FUTURE, LENGTH, MAX_NUMERIC, PAST, MAX_STRING, MIN_NUMERIC, MIN_STRING, NOT_EMPTY, NOT_NULL, RANGE_STRING, RANGE_NUMERIC, PATTERN) VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'asfdasdf', NULL, 'asfdasdf');
INSERT INTO all_validation_type (ID, EMAIL, FUTURE, LENGTH, MAX_NUMERIC, PAST, MAX_STRING, MIN_NUMERIC, MIN_STRING, NOT_EMPTY, NOT_NULL, RANGE_STRING, RANGE_NUMERIC, PATTERN) VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Invalid Value', NULL, 'DEPT');

INSERT INTO assistant (ID, NAME, PROFESSOR_ID) VALUES (1, 'Assistant_Name1', 1);
INSERT INTO assistant (ID, NAME, PROFESSOR_ID) VALUES (2, 'Assistant_Name2', 2);
INSERT INTO assistant (ID, NAME, PROFESSOR_ID) VALUES (3, 'Assistant_Name3', 3);
INSERT INTO assistant (ID, NAME, PROFESSOR_ID) VALUES (4, 'Assistant_Name4', 6);
INSERT INTO assistant (ID, NAME, PROFESSOR_ID) VALUES (5, 'Assistant_Name5', 7);
INSERT INTO assistant (ID, NAME, PROFESSOR_ID) VALUES (6, 'Assistant_Name6', 7);
INSERT INTO assistant (ID, NAME, PROFESSOR_ID) VALUES (7, 'Assistant_Name7', 11);
INSERT INTO assistant (ID, NAME, PROFESSOR_ID) VALUES (8, 'Assistant_Name8', 12);
INSERT INTO assistant (ID, NAME, PROFESSOR_ID) VALUES (9, 'Assistant_Name9', 12);

INSERT INTO assistant_professor (PROFESSOR_ID, JOINING_YEAR) VALUES (11, 11);
INSERT INTO assistant_professor (PROFESSOR_ID, JOINING_YEAR) VALUES (12, 12);
INSERT INTO assistant_professor (PROFESSOR_ID, JOINING_YEAR) VALUES (13, 13);
INSERT INTO assistant_professor (PROFESSOR_ID, JOINING_YEAR) VALUES (14, 14);
INSERT INTO assistant_professor (PROFESSOR_ID, JOINING_YEAR) VALUES (15, 15);

INSERT INTO associate_professor (PROFESSOR_ID, YEARS_SERVED) VALUES (6, 6);
INSERT INTO associate_professor (PROFESSOR_ID, YEARS_SERVED) VALUES (7, 7);
INSERT INTO associate_professor (PROFESSOR_ID, YEARS_SERVED) VALUES (8, 8);
INSERT INTO associate_professor (PROFESSOR_ID, YEARS_SERVED) VALUES (9, 9);
INSERT INTO associate_professor (PROFESSOR_ID, YEARS_SERVED) VALUES (10, 10);

INSERT INTO author (ID, NAME) VALUES (1, 'Author1');
INSERT INTO author (ID, NAME) VALUES (2, 'Author2');
INSERT INTO author (ID, NAME) VALUES (3, 'Author3');
INSERT INTO author (ID, NAME) VALUES (4, 'Author4');
INSERT INTO author (ID, NAME) VALUES (5, 'Author5');

INSERT INTO author_book (AUTHOR_ID, BOOK_ID) VALUES (1, 1);
INSERT INTO author_book (AUTHOR_ID, BOOK_ID) VALUES (2, 2);
INSERT INTO author_book (AUTHOR_ID, BOOK_ID) VALUES (3, 3);

INSERT INTO bag (ID, STYLE) VALUES (1, 'Baguettes');
INSERT INTO bag (ID, STYLE) VALUES (2, 'Barrel');
INSERT INTO bag (ID, STYLE) VALUES (3, 'Beach');
INSERT INTO bag (ID, STYLE) VALUES (4, 'Bowler');
INSERT INTO bag (ID, STYLE) VALUES (5, 'Bucket');
INSERT INTO bag (ID, STYLE) VALUES (6, 'Duffel');
INSERT INTO bag (ID, STYLE) VALUES (7, 'Evening');
INSERT INTO bag (ID, STYLE) VALUES (8, 'Flap');
INSERT INTO bag (ID, STYLE) VALUES (9, 'Hobos');
INSERT INTO bag (ID, STYLE) VALUES (10, 'Pochettes');
INSERT INTO bag (ID, STYLE) VALUES (11, 'Satchel');

INSERT INTO bag_handle (BAG_ID, HANDLE_ID) VALUES (1, 1);
INSERT INTO bag_handle (BAG_ID, HANDLE_ID) VALUES (2, 2);
INSERT INTO bag_handle (BAG_ID, HANDLE_ID) VALUES (3, 3);
INSERT INTO bag_handle (BAG_ID, HANDLE_ID) VALUES (4, 4);
INSERT INTO bag_handle (BAG_ID, HANDLE_ID) VALUES (5, 5);
INSERT INTO bag_handle (BAG_ID, HANDLE_ID) VALUES (6, 6);
INSERT INTO bag_handle (BAG_ID, HANDLE_ID) VALUES (7, 7);
INSERT INTO bag_handle (BAG_ID, HANDLE_ID) VALUES (8, 8);
INSERT INTO bag_handle (BAG_ID, HANDLE_ID) VALUES (9, 9);
INSERT INTO bag_handle (BAG_ID, HANDLE_ID) VALUES (10, 10);

INSERT INTO bank (ID, NAME) VALUES (1, 'Bank1');
INSERT INTO bank (ID, NAME) VALUES (2, 'Bank2');
INSERT INTO bank (ID, NAME) VALUES (3, 'Bank3');
INSERT INTO bank (ID, NAME) VALUES (4, 'Bank4');

INSERT INTO book (ID, NAME) VALUES (1, 'Book1');
INSERT INTO book (ID, NAME) VALUES (2, 'Book2');
INSERT INTO book (ID, NAME) VALUES (3, 'Book3');
INSERT INTO book (ID, NAME) VALUES (4, 'Book4');
INSERT INTO book (ID, NAME) VALUES (5, 'Book5');

INSERT INTO bride (ID, NAME) VALUES (1, 'Bride 1');
INSERT INTO bride (ID, NAME) VALUES (2, 'Bride 2');
INSERT INTO bride (ID, NAME) VALUES (3, 'Bride 3');
INSERT INTO bride (ID, NAME) VALUES (4, 'Bride 4');

INSERT INTO bride_father_in_law (BRIDE_ID, IN_LAW_ID) VALUES (1, 1);
INSERT INTO bride_father_in_law (BRIDE_ID, IN_LAW_ID) VALUES (2, 3);

INSERT INTO bride_mother_in_law (BRIDE_D, IN_LAW_ID) VALUES (1, 2);
INSERT INTO bride_mother_in_law (BRIDE_D, IN_LAW_ID) VALUES (3, 5);

INSERT INTO button (ID, HOLES) VALUES (1, 2);
INSERT INTO button (ID, HOLES) VALUES (2, 4);

INSERT INTO calculator (ID, DISCRIMINATOR, BRAND) VALUES (1, 'financial', 'NCR');
INSERT INTO calculator (ID, DISCRIMINATOR, BRAND) VALUES (2, 'scientific', 'Texas Instruments');
INSERT INTO calculator (ID, DISCRIMINATOR, BRAND) VALUES (3, 'graphics', 'HP');

INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (1, 'Ace', 1, 'My Ace');
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (2, 'Two', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (3, 'Three', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (4, 'Four', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (5, 'Five', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (6, 'Six', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (7, 'Seven', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (8, 'Eight', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (9, 'Nine', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (10, 'Ten', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (11, 'Jack', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (12, 'Queen', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (13, 'King', 1, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (14, 'Ace', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (15, 'Two', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (16, 'Three', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (17, 'Four', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (18, 'Five', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (19, 'Six', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (20, 'Seven', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (21, 'Eight', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (22, 'Nine', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (23, 'Ten', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (24, 'Jack', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (25, 'Queen', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (26, 'King', 2, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (27, 'Ace', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (28, 'Two', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (29, 'Three', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (30, 'Four', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (31, 'Five', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (32, 'Six', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (33, 'Seven', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (34, 'Eight', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (35, 'Nine', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (36, 'Ten', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (37, 'Jack', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (38, 'Queen', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (39, 'King', 3, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (40, 'Ace', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (41, 'Two', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (42, 'Three', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (43, 'Four', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (44, 'Five', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (45, 'Six', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (46, 'Seven', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (47, 'Eight', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (48, 'Nine', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (49, 'Ten', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (50, 'Jack', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (51, 'Queen', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (52, 'King', 4, NULL);
INSERT INTO card (ID, NAME, SUIT_ID, IMAGE) VALUES (53, 'Joker', NULL, NULL);

INSERT INTO cash (PAYMENT_ID) VALUES (1);
INSERT INTO cash (PAYMENT_ID) VALUES (2);

INSERT INTO chain (ID, METAL) VALUES (1, 'Gold');
INSERT INTO chain (ID, METAL) VALUES (2, 'Silver');
INSERT INTO chain (ID, METAL) VALUES (3, 'Bronze');

INSERT INTO chain_pendant (CHAIN_ID, PENDANT_ID) VALUES (1, 1);
INSERT INTO chain_pendant (CHAIN_ID, PENDANT_ID) VALUES (2, 2);
INSERT INTO chain_pendant (CHAIN_ID, PENDANT_ID) VALUES (3, 3);

INSERT INTO character_key (ID, NAME) VALUES ('9', 'CharacterKey_Name 9');
INSERT INTO character_key (ID, NAME) VALUES (';', 'CharacterKey _Name;');
INSERT INTO character_key (ID, NAME) VALUES ('a', 'CharacterKey_Name a');
INSERT INTO character_key (ID, NAME) VALUES ('B', 'CharacterKey_Name B');

INSERT INTO character_primitive_key (ID, NAME) VALUES ('6', 'Character_Primitive_Key_Name 6');
INSERT INTO character_primitive_key (ID, NAME) VALUES ('d', 'Character_Primitive_Key_Name d');
INSERT INTO character_primitive_key (ID, NAME) VALUES ('L', 'Character_Primitive_Key_Name L');
INSERT INTO character_primitive_key (ID, NAME) VALUES ('[', 'Character_Primitive_Key_Name [');

INSERT INTO chef (ID, NAME, RESTAURANT_ID) VALUES (1, 'Chef1', 1);
INSERT INTO chef (ID, NAME, RESTAURANT_ID) VALUES (2, 'Chef2', 2);
INSERT INTO chef (ID, NAME, RESTAURANT_ID) VALUES (3, 'Chef3', 2);
INSERT INTO chef (ID, NAME, RESTAURANT_ID) VALUES (4, 'Chef4', NULL);

INSERT INTO child (ID, NAME, FATHER_ID, MOTHER_ID) VALUES (1, 'Child_Name1', 1, 2);
INSERT INTO child (ID, NAME, FATHER_ID, MOTHER_ID) VALUES (2, 'Child_Name2', 3, 4);
INSERT INTO child (ID, NAME, FATHER_ID, MOTHER_ID) VALUES (3, 'Child_Name3', 5, NULL);
INSERT INTO child (ID, NAME, FATHER_ID, MOTHER_ID) VALUES (4, 'Child_Name4', NULL, 6);
INSERT INTO child (ID, NAME, FATHER_ID, MOTHER_ID) VALUES (5, 'Child_Name5', NULL, NULL);

INSERT INTO computer (ID, TYPE) VALUES (1, 'Computer_Type1');
INSERT INTO computer (ID, TYPE) VALUES (2, 'Computer_Type2');
INSERT INTO computer (ID, TYPE) VALUES (3, 'Computer_Type3');
INSERT INTO computer (ID, TYPE) VALUES (4, 'Computer_Type4');
INSERT INTO computer (ID, TYPE) VALUES (5, 'Computer_Type5');

INSERT INTO credit (PAYMENT_ID, CARD_NUMBER, BANK_ID) VALUES (3, '3', 3);
INSERT INTO credit (PAYMENT_ID, CARD_NUMBER, BANK_ID) VALUES (4, '4', 4);

INSERT INTO crt_monitor (MONITOR_ID, REFRESH_RATE) VALUES (1, 45);

INSERT INTO currency (ID, DISCRIMINATOR, COUNTRY, VALUE) VALUES (1, 'Note', 'USA', 1);
INSERT INTO currency (ID, DISCRIMINATOR, COUNTRY, VALUE) VALUES (2, 'Note', 'Germany', 2);
INSERT INTO currency (ID, DISCRIMINATOR, COUNTRY, VALUE) VALUES (3, 'Note', 'Spain', 3);

INSERT INTO deck (ID, NAME) VALUES (1, 'My Deck 1');

INSERT INTO designer (ID, NAME) VALUES (1, 'Gucci');
INSERT INTO designer (ID, NAME) VALUES (2, 'Prada');
INSERT INTO designer (ID, NAME) VALUES (3, 'Sergio Rossi');

INSERT INTO dessert (ID, TOPPING, FILLING, DISCRIMINATOR) VALUES (1, 'Peanuts', NULL, 'IceCream');
INSERT INTO dessert (ID, TOPPING, FILLING, DISCRIMINATOR) VALUES (2, 'Sprinkles', NULL, 'IceCream');
INSERT INTO dessert (ID, TOPPING, FILLING, DISCRIMINATOR) VALUES (3, NULL, 'Apples', 'Pie');
INSERT INTO dessert (ID, TOPPING, FILLING, DISCRIMINATOR) VALUES (4, NULL, 'Cherries', 'Pie');

INSERT INTO dessert_utensil (DESSERT_ID, UTENSIL_ID) VALUES (1, 1);
INSERT INTO dessert_utensil (DESSERT_ID, UTENSIL_ID) VALUES (3, 1);
INSERT INTO dessert_utensil (DESSERT_ID, UTENSIL_ID) VALUES (2, 2);
INSERT INTO dessert_utensil (DESSERT_ID, UTENSIL_ID) VALUES (3, 2);
INSERT INTO dessert_utensil (DESSERT_ID, UTENSIL_ID) VALUES (4, 2);
INSERT INTO dessert_utensil (DESSERT_ID, UTENSIL_ID) VALUES (1, 3);
INSERT INTO dessert_utensil (DESSERT_ID, UTENSIL_ID) VALUES (4, 3);

INSERT INTO display (ID, WIDTH, HEIGHT) VALUES (1, 1, 1);
INSERT INTO display (ID, WIDTH, HEIGHT) VALUES (2, 2, 2);
INSERT INTO display (ID, WIDTH, HEIGHT) VALUES (3, 3, 3);
INSERT INTO display (ID, WIDTH, HEIGHT) VALUES (4, 4, 4);
INSERT INTO display (ID, WIDTH, HEIGHT) VALUES (5, 5, 5);

INSERT INTO dog (ID, BREED, GENDER) VALUES (1, 'Poodle', 'Male');
INSERT INTO dog (ID, BREED, GENDER) VALUES (2, 'Chihuahua', 'Female');
INSERT INTO dog (ID, BREED, GENDER) VALUES (3, 'St. Bernard', 'Male');

INSERT INTO double_key (ID, NAME) VALUES (1.1, 'Double_Key_Name1.1');
INSERT INTO double_key (ID, NAME) VALUES (2.2, 'Double_Key_Name2.2');
INSERT INTO double_key (ID, NAME) VALUES (3.3, 'Double_Key_Name3.3');
INSERT INTO double_key (ID, NAME) VALUES (4.4, 'Double_Key_Name4.4');
INSERT INTO double_key (ID, NAME) VALUES (5.5, 'Double_Key_Name5.5');

INSERT INTO double_primitive_key (ID, NAME) VALUES (1.1, 'Double_Primitive_Key 1.1');
INSERT INTO double_primitive_key (ID, NAME) VALUES (2.2, 'Double_Primitive_Key 2.2');

INSERT INTO element (ID, NAME, PARENT_ELEMENT_ID) VALUES (1, 'Name1', NULL);
INSERT INTO element (ID, NAME, PARENT_ELEMENT_ID) VALUES (2, 'Name2', 1);
INSERT INTO element (ID, NAME, PARENT_ELEMENT_ID) VALUES (3, 'Element', NULL);
INSERT INTO element (ID, NAME, PARENT_ELEMENT_ID) VALUES (4, 'Element', NULL);

INSERT INTO employee (ID, NAME) VALUES (1, 'Employee_Name1');
INSERT INTO employee (ID, NAME) VALUES (2, 'Employee_Name2');
INSERT INTO employee (ID, NAME) VALUES (3, 'Employee_Name3');
INSERT INTO employee (ID, NAME) VALUES (4, 'Employee_Name4');
INSERT INTO employee (ID, NAME) VALUES (5, 'Employee_Name5');
INSERT INTO employee (ID, NAME) VALUES (6, 'Employee_Name6');
INSERT INTO employee (ID, NAME) VALUES (7, 'Employee_Name7');
INSERT INTO employee (ID, NAME) VALUES (8, 'Employee_Name8');
INSERT INTO employee (ID, NAME) VALUES (9, 'Employee_Name9');
INSERT INTO employee (ID, NAME) VALUES (10, 'Employee_Name10');

INSERT INTO employee_project (EMPLOYEE_ID, PROJECT_ID) VALUES (1, 1);
INSERT INTO employee_project (EMPLOYEE_ID, PROJECT_ID) VALUES (2, 2);
INSERT INTO employee_project (EMPLOYEE_ID, PROJECT_ID) VALUES (3, 2);
INSERT INTO employee_project (EMPLOYEE_ID, PROJECT_ID) VALUES (4, 4);
INSERT INTO employee_project (EMPLOYEE_ID, PROJECT_ID) VALUES (4, 5);
INSERT INTO employee_project (EMPLOYEE_ID, PROJECT_ID) VALUES (6, 5);

INSERT INTO fish (ID, GENERA, PRIMARY_COLOR, FIN_SIZE, DISCRIMINATOR, TANK_ID, TANK_DISCRIMINATOR) VALUES (1, 'Hemichromis', 'blue', NULL, 'DiscusFish', 1, 'FreshwaterFishTank');
INSERT INTO fish (ID, GENERA, PRIMARY_COLOR, FIN_SIZE, DISCRIMINATOR, TANK_ID, TANK_DISCRIMINATOR) VALUES (2, 'Hemichromis', 'red', NULL, 'DiscusFish', 2, 'FreshwaterFishTank');
INSERT INTO fish (ID, GENERA, PRIMARY_COLOR, FIN_SIZE, DISCRIMINATOR, TANK_ID, TANK_DISCRIMINATOR) VALUES (3, 'Pterophyllum', NULL, 3, 'AngelFish', 3, 'SaltwaterFishTank');
INSERT INTO fish (ID, GENERA, PRIMARY_COLOR, FIN_SIZE, DISCRIMINATOR, TANK_ID, TANK_DISCRIMINATOR) VALUES (4, 'Pterophyllum', NULL, 5, 'AngelFish', 4, 'SaltwaterFishTank');

INSERT INTO flight (ID, DESTINATION) VALUES (1, 'Baltimore, MD');
INSERT INTO flight (ID, DESTINATION) VALUES (2, 'San Francisco, CA');
INSERT INTO flight (ID, DESTINATION) VALUES (3, 'Maui, HI');

INSERT INTO flight_passanger (FLIGHT_ID, PASSANGER_ID) VALUES (1, 1);
INSERT INTO flight_passanger (FLIGHT_ID, PASSANGER_ID) VALUES (1, 2);

INSERT INTO float_key (ID, NAME) VALUES (1.1, 'Float_Key_Name1.1');
INSERT INTO float_key (ID, NAME) VALUES (2.2, 'Float_Key_Name2.2');
INSERT INTO float_key (ID, NAME) VALUES (3.3, 'Float_Key_Name3.3');
INSERT INTO float_key (ID, NAME) VALUES (4.4, 'Float_Key_Name4.4');
INSERT INTO float_key (ID, NAME) VALUES (5.5, 'Float_Key_Name5.5');

INSERT INTO float_primitive_key (ID, NAME) VALUES (1.1, 'Float_Key_Name 1.1');
INSERT INTO float_primitive_key (ID, NAME) VALUES (2.2, 'Float_Key_Name 2.2');
INSERT INTO float_primitive_key (ID, NAME) VALUES (3.3, 'Float_Key_Name 3.3');

INSERT INTO freshwater_fish_tank (ID, SHAPE, NUM_GALLONS, FILTER_MODEL) VALUES (1, 'Rectangular', 10, '350B Penguin Bio-Wheel Filter');
INSERT INTO freshwater_fish_tank (ID, SHAPE, NUM_GALLONS, FILTER_MODEL) VALUES (2, 'Hexagonal', 7, '200B Penguin Bio-Wheel Filter');

INSERT INTO goverment (ID, DISCRIMINATOR, COUNTRY, PRIME_MINISTER, PRESIDENT, DEMOCRATIC_DISCRIMINATOR) VALUES (1, 'PresidentialGovt', 'USA', NULL, 'George W. Bush', NULL);
INSERT INTO goverment (ID, DISCRIMINATOR, COUNTRY, PRIME_MINISTER, PRESIDENT, DEMOCRATIC_DISCRIMINATOR) VALUES (2, 'ParliamantaryGovt', 'England', 'Tony Blair', NULL, NULL);
INSERT INTO goverment (ID, DISCRIMINATOR, COUNTRY, PRIME_MINISTER, PRESIDENT, DEMOCRATIC_DISCRIMINATOR) VALUES (3, 'CommunistGovt', 'Cuba', NULL, NULL, NULL);

INSERT INTO graduate_student (STUDENT_ID, PROJECT_NAME) VALUES (6, 'Project_Name6');
INSERT INTO graduate_student (STUDENT_ID, PROJECT_NAME) VALUES (7, 'Project_Name7');
INSERT INTO graduate_student (STUDENT_ID, PROJECT_NAME) VALUES (8, 'Project_Name8');
INSERT INTO graduate_student (STUDENT_ID, PROJECT_NAME) VALUES (9, 'Project_Name9');
INSERT INTO graduate_student (STUDENT_ID, PROJECT_NAME) VALUES (10, 'Project_Name10');

INSERT INTO graphic_calculator (CALCULATOR_ID) VALUES (3);

INSERT INTO hand (ID) VALUES (1);
INSERT INTO hand (ID) VALUES (2);
INSERT INTO hand (ID) VALUES (3);
INSERT INTO hand (ID) VALUES (4);
INSERT INTO hand (ID) VALUES (5);

INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (1, 1);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (2, 2);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (2, 3);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (2, 5);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (3, 6);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (3, 14);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (3, 15);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (1, 25);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (4, 26);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (4, 27);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (4, 30);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (5, 39);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (5, 40);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (5, 41);
INSERT INTO hand_card (HAND_ID, CARD_ID) VALUES (1, 52);

INSERT INTO handle (ID, COLOR) VALUES (1, 'Black');
INSERT INTO handle (ID, COLOR) VALUES (2, 'Brown');
INSERT INTO handle (ID, COLOR) VALUES (3, 'Blue');
INSERT INTO handle (ID, COLOR) VALUES (4, 'White');
INSERT INTO handle (ID, COLOR) VALUES (5, 'Red');
INSERT INTO handle (ID, COLOR) VALUES (6, 'Yellow');
INSERT INTO handle (ID, COLOR) VALUES (7, 'Green');
INSERT INTO handle (ID, COLOR) VALUES (8, 'Beige');
INSERT INTO handle (ID, COLOR) VALUES (9, 'Purple');
INSERT INTO handle (ID, COLOR) VALUES (10, 'Orange');
INSERT INTO handle (ID, COLOR) VALUES (11, 'Teal');
INSERT INTO handle (ID, COLOR) VALUES (12, 'Burgundy');

INSERT INTO hard_drive (ID, DRIVE_SIZE, COMPUTER_ID) VALUES (1, 1, 1);
INSERT INTO hard_drive (ID, DRIVE_SIZE, COMPUTER_ID) VALUES (2, 2, 2);
INSERT INTO hard_drive (ID, DRIVE_SIZE, COMPUTER_ID) VALUES (3, 3, 2);

INSERT INTO hi_value (NEXT_VALUE) VALUES (8);

INSERT INTO hl7_data_type (ID, ROOT, EXTENSION, XML) VALUES (1, 'My Root', 'My Ext', NULL);
INSERT INTO hl7_data_type (ID, ROOT, EXTENSION, XML) VALUES (2, 'My Root 2', 'My Ext 2', NULL);

INSERT INTO human (MAMMAL_ID, DIET) VALUES (1, 'DIET1');
INSERT INTO human (MAMMAL_ID, DIET) VALUES (2, 'DIET2');
INSERT INTO human (MAMMAL_ID, DIET) VALUES (3, 'DIET3');
INSERT INTO human (MAMMAL_ID, DIET) VALUES (4, 'DIET4');

INSERT INTO in_law (ID, NAME) VALUES (1, ' Father-in-Law Bride 1');
INSERT INTO in_law (ID, NAME) VALUES (2, 'Mother-in-Law Bride 1');
INSERT INTO in_law (ID, NAME) VALUES (3, 'Father-in-Law Bride 2');
INSERT INTO in_law (ID, NAME) VALUES (5, 'Mother-in-Law Bride 3');

INSERT INTO integer_key (ID, NAME) VALUES (1, 'Integer_Key_Name1');
INSERT INTO integer_key (ID, NAME) VALUES (2, 'Integer_Key_Name2');
INSERT INTO integer_key (ID, NAME) VALUES (3, 'Integer_Key_Name3');
INSERT INTO integer_key (ID, NAME) VALUES (4, 'Integer_Key_Name4');
INSERT INTO integer_key (ID, NAME) VALUES (5, 'Integer_Key_Name5');

INSERT INTO integer_primitive_key (ID, NAME) VALUES (1, 'Integer_Primitive_Key_Name 1');
INSERT INTO integer_primitive_key (ID, NAME) VALUES (2, 'Integer_Primitive_Key_Name 2');

INSERT INTO keychain (ID, NAME) VALUES (1, 'Keychain_Name1');
INSERT INTO keychain (ID, NAME) VALUES (2, 'Keychain_Name2');
INSERT INTO keychain (ID, NAME) VALUES (3, 'Keychain_Name3');
INSERT INTO keychain (ID, NAME) VALUES (4, 'Keychain_Name4');
INSERT INTO keychain (ID, NAME) VALUES (5, 'Keychain_Name5');

INSERT INTO latch_key (ID, TYPE, KEYCHAIN_ID) VALUES (1, 'Key_Type1', 1);
INSERT INTO latch_key (ID, TYPE, KEYCHAIN_ID) VALUES (2, 'Key_Type2', 2);
INSERT INTO latch_key (ID, TYPE, KEYCHAIN_ID) VALUES (3, 'Key_Type3', 2);

INSERT INTO lcd_monitor (MONITOR_ID, DPI_SUPPORTED) VALUES (2, 2323);
INSERT INTO lcd_monitor (MONITOR_ID, DPI_SUPPORTED) VALUES (3, 1212);

INSERT INTO long_key (ID, NAME) VALUES (1234567890987650000, 'Long_Key_NAME 1234567890987654321');

INSERT INTO long_primitive_key (ID, NAME) VALUES (987654321234568000, 'Long_Primitive_Key_NAME 987654321234568000');

INSERT INTO luggage (ID, DISCRIMINATOR, CAPACITY, KEY_CODE, EXPANDABLE, WHEEL_ID) VALUES (1, 'HardTop', 75, 627, NULL, 1);
INSERT INTO luggage (ID, DISCRIMINATOR, CAPACITY, KEY_CODE, EXPANDABLE, WHEEL_ID) VALUES (2, 'HardTop', 75, 985, NULL, 3);
INSERT INTO luggage (ID, DISCRIMINATOR, CAPACITY, KEY_CODE, EXPANDABLE, WHEEL_ID) VALUES (3, 'SoftTop', 55, NULL, '1', 1);
INSERT INTO luggage (ID, DISCRIMINATOR, CAPACITY, KEY_CODE, EXPANDABLE, WHEEL_ID) VALUES (4, 'SoftTop', 35, NULL, '0', 2);
INSERT INTO luggage (ID, DISCRIMINATOR, CAPACITY, KEY_CODE, EXPANDABLE, WHEEL_ID) VALUES (5, 'HardTopType', 100, 890, NULL, 1);

INSERT INTO mammal (ID, HAIR_COLOR) VALUES (1, 'Hair_Color1');
INSERT INTO mammal (ID, HAIR_COLOR) VALUES (2, 'Hair_Color2');
INSERT INTO mammal (ID, HAIR_COLOR) VALUES (3, 'Hair_Color3');
INSERT INTO mammal (ID, HAIR_COLOR) VALUES (4, 'Hair_Color4');
INSERT INTO mammal (ID, HAIR_COLOR) VALUES (5, 'Hair_Color5');

INSERT INTO monitor (DISPLAY_ID, BRAND) VALUES (1, 'A');
INSERT INTO monitor (DISPLAY_ID, BRAND) VALUES (2, 'B');
INSERT INTO monitor (DISPLAY_ID, BRAND) VALUES (3, 'C');
INSERT INTO monitor (DISPLAY_ID, BRAND) VALUES (4, 'D');

INSERT INTO no_id_key (MY_KEY, NAME) VALUES (1, 'NoIdKey 1');
INSERT INTO no_id_key (MY_KEY, NAME) VALUES (2, 'NoIdKey 2');

INSERT INTO orderline (ID, NAME) VALUES (1, 'Orderline_Name1');
INSERT INTO orderline (ID, NAME) VALUES (2, 'Orderline_Name2');
INSERT INTO orderline (ID, NAME) VALUES (3, 'Orderline_Name3');
INSERT INTO orderline (ID, NAME) VALUES (4, 'Orderline_Name4');
INSERT INTO orderline (ID, NAME) VALUES (5, 'Orderline_Name5');

INSERT INTO organization (ID, DISCRIMINATOR, NAME, AGENCY_BUDGET, CEO) VALUES (1, 'govt', 'Public Org Name', 1000, NULL);
INSERT INTO organization (ID, DISCRIMINATOR, NAME, AGENCY_BUDGET, CEO) VALUES (2, 'pvt', 'Private Org Name', NULL, 'Private CEO Name');

INSERT INTO parent (ID, NAME) VALUES (1, 'Parent_Name1');
INSERT INTO parent (ID, NAME) VALUES (2, 'Parent_Name2');
INSERT INTO parent (ID, NAME) VALUES (3, 'Parent_Name3');
INSERT INTO parent (ID, NAME) VALUES (4, 'Parent_Name4');
INSERT INTO parent (ID, NAME) VALUES (5, 'Parent_Name5');
INSERT INTO parent (ID, NAME) VALUES (6, 'Parent_Name6');
INSERT INTO parent (ID, NAME) VALUES (7, 'Parent_Name7');
INSERT INTO parent (ID, NAME) VALUES (8, 'Parent_Name8');
INSERT INTO parent (ID, NAME) VALUES (9, 'Parent_Name9');
INSERT INTO parent (ID, NAME) VALUES (10, 'Parent_Name10');

INSERT INTO passanger (ID, NAME) VALUES (1, 'John Doe');
INSERT INTO passanger (ID, NAME) VALUES (2, 'Jane Doe');

INSERT INTO payment (ID, AMOUNT) VALUES (1, 1);
INSERT INTO payment (ID, AMOUNT) VALUES (2, 2);
INSERT INTO payment (ID, AMOUNT) VALUES (3, 3);
INSERT INTO payment (ID, AMOUNT) VALUES (4, 4);
INSERT INTO payment (ID, AMOUNT) VALUES (5, 5);

INSERT INTO pendant (ID, SHAPE) VALUES (1, 'Circle Pearl');
INSERT INTO pendant (ID, SHAPE) VALUES (2, 'Heart Opal');
INSERT INTO pendant (ID, SHAPE) VALUES (3, 'Oval Carnelian Shell Cameo');

INSERT INTO person (ID, NAME, ADDRESS_ID) VALUES (1, 'Person_Name1', 1);
INSERT INTO person (ID, NAME, ADDRESS_ID) VALUES (2, 'Person_Name2', 2);
INSERT INTO person (ID, NAME, ADDRESS_ID) VALUES (3, 'Person_Name3', 3);
INSERT INTO person (ID, NAME, ADDRESS_ID) VALUES (4, 'Person_Name4', NULL);
INSERT INTO person (ID, NAME, ADDRESS_ID) VALUES (5, 'Person_Name5', NULL);

INSERT INTO private_teacher (TEACHER_ID, YEARS_EXPERIENCE) VALUES (1, 5);
INSERT INTO private_teacher (TEACHER_ID, YEARS_EXPERIENCE) VALUES (2, 10);
INSERT INTO private_teacher (TEACHER_ID, YEARS_EXPERIENCE) VALUES (3, 15);

INSERT INTO product (ID, NAME, ORDERLINE_ID) VALUES (1, 'Product_Name1', 1);
INSERT INTO product (ID, NAME, ORDERLINE_ID) VALUES (2, 'Product_Name2', 2);
INSERT INTO product (ID, NAME, ORDERLINE_ID) VALUES (3, 'Product_Name3', NULL);

INSERT INTO professor (ID, NAME) VALUES (1, 'Professor_Name1');
INSERT INTO professor (ID, NAME) VALUES (2, 'Professor_Name2');
INSERT INTO professor (ID, NAME) VALUES (3, 'Professor_Name3');
INSERT INTO professor (ID, NAME) VALUES (4, 'Professor_Name4');
INSERT INTO professor (ID, NAME) VALUES (5, 'Professor_Name5');
INSERT INTO professor (ID, NAME) VALUES (6, 'Professor_Name6');
INSERT INTO professor (ID, NAME) VALUES (7, 'Professor_Name7');
INSERT INTO professor (ID, NAME) VALUES (8, 'Professor_Name8');
INSERT INTO professor (ID, NAME) VALUES (9, 'Professor_Name9');
INSERT INTO professor (ID, NAME) VALUES (10, 'Professor_Name10');
INSERT INTO professor (ID, NAME) VALUES (11, 'Professor_Name11');
INSERT INTO professor (ID, NAME) VALUES (12, 'Professor_Name12');
INSERT INTO professor (ID, NAME) VALUES (13, 'Professor_Name13');
INSERT INTO professor (ID, NAME) VALUES (14, 'Professor_Name14');
INSERT INTO professor (ID, NAME) VALUES (15, 'Professor_Name15');

INSERT INTO project (ID, NAME) VALUES (1, 'Project_Name1');
INSERT INTO project (ID, NAME) VALUES (2, 'Project_Name2');
INSERT INTO project (ID, NAME) VALUES (3, 'Project_Name3');
INSERT INTO project (ID, NAME) VALUES (4, 'Project_Name4');
INSERT INTO project (ID, NAME) VALUES (5, 'Project_Name5');
INSERT INTO project (ID, NAME) VALUES (6, 'Project_Name6');
INSERT INTO project (ID, NAME) VALUES (7, 'Project_Name7');
INSERT INTO project (ID, NAME) VALUES (8, 'Project_Name8');
INSERT INTO project (ID, NAME) VALUES (9, 'Project_Name9');
INSERT INTO project (ID, NAME) VALUES (10, 'Project_Name10');

INSERT INTO pupil (ID, NAME, TEACHER_ID) VALUES (1, 'Pupil_Name_1', 1);
INSERT INTO pupil (ID, NAME, TEACHER_ID) VALUES (2, 'Pupil_Name_2', 1);
INSERT INTO pupil (ID, NAME, TEACHER_ID) VALUES (3, 'Pupil_Name_3', 3);
INSERT INTO pupil (ID, NAME, TEACHER_ID) VALUES (4, 'Pupil_Name_4', 3);

INSERT INTO restaurant (ID, NAME) VALUES (1, 'Rest1');
INSERT INTO restaurant (ID, NAME) VALUES (2, 'Rest2');
INSERT INTO restaurant (ID, NAME) VALUES (3, 'Rest3');
INSERT INTO restaurant (ID, NAME) VALUES (4, 'Rest4');
INSERT INTO restaurant (ID, NAME) VALUES (5, 'Rest5');

INSERT INTO saltwater_fish_tank (ID, SHAPE, NUM_GALLONS, PROTEIN_SKIMMER_MODEL) VALUES (3, 'Rectangular', 60, 'Berlin X2 Turbo Skimmer');
INSERT INTO saltwater_fish_tank (ID, SHAPE, NUM_GALLONS, PROTEIN_SKIMMER_MODEL) VALUES (4, 'Hexagonal', 20, 'Prizm Pro Deluxe Skimmer');

INSERT INTO saltwater_fish_tank_substrate (SALTWATER_FISH_TANK_ID, SUBSTRATE_ID) VALUES (3, 1);
INSERT INTO saltwater_fish_tank_substrate (SALTWATER_FISH_TANK_ID, SUBSTRATE_ID) VALUES (3, 2);
INSERT INTO saltwater_fish_tank_substrate (SALTWATER_FISH_TANK_ID, SUBSTRATE_ID) VALUES (4, 3);
INSERT INTO saltwater_fish_tank_substrate (SALTWATER_FISH_TANK_ID, SUBSTRATE_ID) VALUES (4, 4);

INSERT INTO shirt (ID, STYLE) VALUES (1, 'Collar');
INSERT INTO shirt (ID, STYLE) VALUES (2, 'Western');
INSERT INTO shirt (ID, STYLE) VALUES (3, 'T-Shirt');

INSERT INTO shirt_button (SHIRT_ID, BUTTON_ID) VALUES (1, 1);
INSERT INTO shirt_button (SHIRT_ID, BUTTON_ID) VALUES (2, 2);

INSERT INTO shoes (ID, DISCRIMINATOR, COLOR, SPORTS_TYPE, DESIGNER_ID) VALUES (1, 'DesignerShoes', 'White', NULL, 2);
INSERT INTO shoes (ID, DISCRIMINATOR, COLOR, SPORTS_TYPE, DESIGNER_ID) VALUES (2, 'SportsShoes', 'Red', 'BasketBall', NULL);
INSERT INTO shoes (ID, DISCRIMINATOR, COLOR, SPORTS_TYPE, DESIGNER_ID) VALUES (3, 'DesignerShoes', 'Black', NULL, 3);

INSERT INTO song (ID, TITLE) VALUES (1, 'Albinoni:  Concerto in B Flat, OP. 7 No. 3');
INSERT INTO song (ID, TITLE) VALUES (2, 'Albinoni:  Concerto in D Major, Op. 7 No. 6');
INSERT INTO song (ID, TITLE) VALUES (3, 'Marcello:  Concerto in D Minor');
INSERT INTO song (ID, TITLE) VALUES (4, 'Vivaldi:  Concerto in F Major F VII 2');
INSERT INTO song (ID, TITLE) VALUES (5, 'Vivaldi:  Concerto in A Minor F VII 5');
INSERT INTO song (ID, TITLE) VALUES (6, 'Cimarosa/Benjamin:  Concerto in C Minor');
INSERT INTO song (ID, TITLE) VALUES (7, 'Rubenstein: Melody in F, Op. 3 No. 1');
INSERT INTO song (ID, TITLE) VALUES (8, 'Schubert: Ave Maria');
INSERT INTO song (ID, TITLE) VALUES (9, 'Rimsky-Korsakov:  The Flight of the Bumble Bee');
INSERT INTO song (ID, TITLE) VALUES (10, 'Schumann:  Traumerei');
INSERT INTO song (ID, TITLE) VALUES (11, 'Dvorak:  Songs My Mother Taught Me');
INSERT INTO song (ID, TITLE) VALUES (12, 'Saint-Seans:  The Swan');

INSERT INTO string_key (ID, NAME) VALUES ('ID1', 'String_Key_Name1');
INSERT INTO string_key (ID, NAME) VALUES ('ID2', 'String_Key_Name2');
INSERT INTO string_key (ID, NAME) VALUES ('ID3', 'String_Key_Name3');
INSERT INTO string_key (ID, NAME) VALUES ('ID4', 'String_Key_Name4');
INSERT INTO string_key (ID, NAME) VALUES ('ID5', 'String_Key_Name5');

INSERT INTO string_primitive_key (ID, NAME) VALUES ('id1', 'String_Primitive_Key id1');
INSERT INTO string_primitive_key (ID, NAME) VALUES ('id2', 'String_Primitive_Key id2');

INSERT INTO student (ID, NAME) VALUES (1, 'Student_Name1');
INSERT INTO student (ID, NAME) VALUES (2, 'Student_Name2');
INSERT INTO student (ID, NAME) VALUES (3, 'Student_Name3');
INSERT INTO student (ID, NAME) VALUES (4, 'Student_Name4');
INSERT INTO student (ID, NAME) VALUES (5, 'Student_Name5');
INSERT INTO student (ID, NAME) VALUES (6, 'Student_Name6');
INSERT INTO student (ID, NAME) VALUES (7, 'Student_Name7');
INSERT INTO student (ID, NAME) VALUES (8, 'Student_Name8');
INSERT INTO student (ID, NAME) VALUES (9, 'Student_Name9');
INSERT INTO student (ID, NAME) VALUES (10, 'Student_Name10');

INSERT INTO substrate (ID, NAME) VALUES (1, 'Live Rock');
INSERT INTO substrate (ID, NAME) VALUES (2, 'Sand');
INSERT INTO substrate (ID, NAME) VALUES (3, 'Crushed Coral');
INSERT INTO substrate (ID, NAME) VALUES (4, 'River Pebbles');

INSERT INTO suit (ID, NAME, DECK_ID) VALUES (1, 'Spade', 1);
INSERT INTO suit (ID, NAME, DECK_ID) VALUES (2, 'Flower', 1);
INSERT INTO suit (ID, NAME, DECK_ID) VALUES (3, 'Diamond', 1);
INSERT INTO suit (ID, NAME, DECK_ID) VALUES (4, 'Heart', 1);

INSERT INTO tank_accessory (ID, NAME) VALUES (1, 'Filter');
INSERT INTO tank_accessory (ID, NAME) VALUES (2, 'Heater');
INSERT INTO tank_accessory (ID, NAME) VALUES (3, 'Lighting');
INSERT INTO tank_accessory (ID, NAME) VALUES (4, 'Protein Skimmer');

INSERT INTO tank_tank_accessory (TANK_ID, TANK_ACCESSORY_ID, TANK_DISCRIMINATOR) VALUES (1, 1, 'FreshwaterFishTank');
INSERT INTO tank_tank_accessory (TANK_ID, TANK_ACCESSORY_ID, TANK_DISCRIMINATOR) VALUES (1, 2, 'FreshwaterFishTank');
INSERT INTO tank_tank_accessory (TANK_ID, TANK_ACCESSORY_ID, TANK_DISCRIMINATOR) VALUES (2, 2, 'FreshwaterFishTank');
INSERT INTO tank_tank_accessory (TANK_ID, TANK_ACCESSORY_ID, TANK_DISCRIMINATOR) VALUES (2, 3, 'FreshwaterFishTank');
INSERT INTO tank_tank_accessory (TANK_ID, TANK_ACCESSORY_ID, TANK_DISCRIMINATOR) VALUES (3, 1, 'SaltwaterFishTank');
INSERT INTO tank_tank_accessory (TANK_ID, TANK_ACCESSORY_ID, TANK_DISCRIMINATOR) VALUES (3, 4, 'SaltwaterFishTank');
INSERT INTO tank_tank_accessory (TANK_ID, TANK_ACCESSORY_ID, TANK_DISCRIMINATOR) VALUES (4, 1, 'SaltwaterFishTank');
INSERT INTO tank_tank_accessory (TANK_ID, TANK_ACCESSORY_ID, TANK_DISCRIMINATOR) VALUES (4, 2, 'SaltwaterFishTank');
INSERT INTO tank_tank_accessory (TANK_ID, TANK_ACCESSORY_ID, TANK_DISCRIMINATOR) VALUES (4, 4, 'SaltwaterFishTank');

INSERT INTO teacher (ID, NAME) VALUES (1, 'Teacher_Name1');
INSERT INTO teacher (ID, NAME) VALUES (2, 'Teacher_Name2');
INSERT INTO teacher (ID, NAME) VALUES (3, 'Teacher_Name3');

INSERT INTO tenured_professor (PROFESSOR_ID, TENURED_YEAR) VALUES (1, 1);
INSERT INTO tenured_professor (PROFESSOR_ID, TENURED_YEAR) VALUES (2, 2);
INSERT INTO tenured_professor (PROFESSOR_ID, TENURED_YEAR) VALUES (3, 3);
INSERT INTO tenured_professor (PROFESSOR_ID, TENURED_YEAR) VALUES (4, 4);
INSERT INTO tenured_professor (PROFESSOR_ID, TENURED_YEAR) VALUES (5, 5);

INSERT INTO undergraduate_student (STUDENT_ID) VALUES (1);
INSERT INTO undergraduate_student (STUDENT_ID) VALUES (2);
INSERT INTO undergraduate_student (STUDENT_ID) VALUES (3);
INSERT INTO undergraduate_student (STUDENT_ID) VALUES (4);
INSERT INTO undergraduate_student (STUDENT_ID) VALUES (5);

INSERT INTO utensil (ID, NAME) VALUES (1, 'Spoon');
INSERT INTO utensil (ID, NAME) VALUES (2, 'Knife');
INSERT INTO utensil (ID, NAME) VALUES (3, 'Fork');

INSERT INTO wheel (ID, RADIUS) VALUES (1, 1);
INSERT INTO wheel (ID, RADIUS) VALUES (2, 5);
INSERT INTO wheel (ID, RADIUS) VALUES (3, 10);

ALTER TABLE ONLY bag_handle
    ADD CONSTRAINT UQ_BAG_HANDLE_BAG_ID UNIQUE (BAG_ID);

ALTER TABLE ONLY bag_handle
    ADD CONSTRAINT UQ_BAG_HANDLE_HANDLE_ID UNIQUE (HANDLE_ID);

ALTER TABLE ONLY bride_father_in_law
    ADD CONSTRAINT UQ_BRIDE_FATHER_IN_L_BRIDE_ID UNIQUE (BRIDE_ID);

ALTER TABLE ONLY bride_father_in_law
    ADD CONSTRAINT UQ_BRIDE_FATHER_IN__IN_LAW_ID UNIQUE (IN_LAW_ID);

ALTER TABLE ONLY bride_mother_in_law
    ADD CONSTRAINT UQ_BRIDE_MOTHER_IN_LA_BRIDE_D UNIQUE (BRIDE_D);

ALTER TABLE ONLY bride_mother_in_law
    ADD CONSTRAINT UQ_BRIDE_MOTHER_IN__IN_LAW_ID UNIQUE (IN_LAW_ID);

ALTER TABLE ONLY chain_pendant
    ADD CONSTRAINT UQ_CHAIN_PENDANT_CHAIN_ID UNIQUE (CHAIN_ID);

ALTER TABLE ONLY chain_pendant
    ADD CONSTRAINT UQ_CHAIN_PENDANT_PENDANT_ID UNIQUE (PENDANT_ID);

ALTER TABLE ONLY child
    ADD CONSTRAINT UQ_CHILD_FATHER_ID UNIQUE (FATHER_ID);

ALTER TABLE ONLY child
    ADD CONSTRAINT UQ_CHILD_MOTHER_ID UNIQUE (MOTHER_ID);

ALTER TABLE ONLY element
    ADD CONSTRAINT UQ_ELEMENT_PARENT_ELEMENT_ID UNIQUE (PARENT_ELEMENT_ID);

ALTER TABLE ONLY person
    ADD CONSTRAINT UQ_PERSON_ADDRESS_ID UNIQUE (ADDRESS_ID);

ALTER TABLE ONLY product
    ADD CONSTRAINT UQ_PRODUCT_ORDERLINE_ID UNIQUE (ORDERLINE_ID);

ALTER TABLE ONLY shirt_button
    ADD CONSTRAINT UQ_SHIRT_BUTTON_SHIRT_ID UNIQUE (SHIRT_ID);

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY album
    ADD CONSTRAINT album_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY album_song
    ADD CONSTRAINT album_song_pkey PRIMARY KEY (ALBUM_ID, SONG_ID);

ALTER TABLE ONLY all_data_type
    ADD CONSTRAINT all_data_type_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY all_validation_type
    ADD CONSTRAINT all_validation_type_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY assistant
    ADD CONSTRAINT assistant_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY assistant_professor
    ADD CONSTRAINT assistant_professor_pkey PRIMARY KEY (PROFESSOR_ID);

ALTER TABLE ONLY associate_professor
    ADD CONSTRAINT associate_professor_pkey PRIMARY KEY (PROFESSOR_ID);

ALTER TABLE ONLY author_book
    ADD CONSTRAINT author_book_pkey PRIMARY KEY (AUTHOR_ID, BOOK_ID);

ALTER TABLE ONLY author
    ADD CONSTRAINT author_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY bag_handle
    ADD CONSTRAINT bag_handle_pkey PRIMARY KEY (BAG_ID, HANDLE_ID);

ALTER TABLE ONLY bag
    ADD CONSTRAINT bag_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY bank
    ADD CONSTRAINT bank_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY book
    ADD CONSTRAINT book_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY bride_father_in_law
    ADD CONSTRAINT bride_father_in_law_pkey PRIMARY KEY (BRIDE_ID, IN_LAW_ID);

ALTER TABLE ONLY bride_mother_in_law
    ADD CONSTRAINT bride_mother_in_law_pkey PRIMARY KEY (BRIDE_D, IN_LAW_ID);

ALTER TABLE ONLY bride
    ADD CONSTRAINT bride_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY button
    ADD CONSTRAINT button_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY calculator
    ADD CONSTRAINT calculator_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY card
    ADD CONSTRAINT card_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY cash
    ADD CONSTRAINT cash_pkey PRIMARY KEY (PAYMENT_ID);

ALTER TABLE ONLY chain_pendant
    ADD CONSTRAINT chain_pendant_pkey PRIMARY KEY (CHAIN_ID, PENDANT_ID);

ALTER TABLE ONLY chain
    ADD CONSTRAINT chain_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY character_key
    ADD CONSTRAINT character_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY character_primitive_key
    ADD CONSTRAINT character_primitive_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY chef
    ADD CONSTRAINT chef_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY child
    ADD CONSTRAINT child_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY computer
    ADD CONSTRAINT computer_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY credit
    ADD CONSTRAINT credit_pkey PRIMARY KEY (PAYMENT_ID);

ALTER TABLE ONLY crt_monitor
    ADD CONSTRAINT crt_monitor_pkey PRIMARY KEY (MONITOR_ID);

ALTER TABLE ONLY currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY deck
    ADD CONSTRAINT deck_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY designer
    ADD CONSTRAINT designer_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY dessert
    ADD CONSTRAINT dessert_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY dessert_utensil
    ADD CONSTRAINT dessert_utensil_pkey PRIMARY KEY (DESSERT_ID, UTENSIL_ID);

ALTER TABLE ONLY display
    ADD CONSTRAINT display_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY dog
    ADD CONSTRAINT dog_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY double_key
    ADD CONSTRAINT double_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY double_primitive_key
    ADD CONSTRAINT double_primitive_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY element
    ADD CONSTRAINT element_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY employee_project
    ADD CONSTRAINT employee_project_pkey PRIMARY KEY (EMPLOYEE_ID, PROJECT_ID);

ALTER TABLE ONLY fish
    ADD CONSTRAINT fish_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY flight_passanger
    ADD CONSTRAINT flight_passanger_pkey PRIMARY KEY (FLIGHT_ID, PASSANGER_ID);

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY float_key
    ADD CONSTRAINT float_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY float_primitive_key
    ADD CONSTRAINT float_primitive_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY freshwater_fish_tank
    ADD CONSTRAINT freshwater_fish_tank_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY goverment
    ADD CONSTRAINT goverment_pkey PRIMARY KEY (ID);


ALTER TABLE ONLY graduate_student
    ADD CONSTRAINT graduate_student_pkey PRIMARY KEY (STUDENT_ID);

ALTER TABLE ONLY graphic_calculator
    ADD CONSTRAINT graphic_calculator_pkey PRIMARY KEY (CALCULATOR_ID);

ALTER TABLE ONLY hand_card
    ADD CONSTRAINT hand_card_pkey PRIMARY KEY (CARD_ID, HAND_ID);

ALTER TABLE ONLY hand
    ADD CONSTRAINT hand_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY handle
    ADD CONSTRAINT handle_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY hard_drive
    ADD CONSTRAINT hard_drive_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY hl7_data_type
    ADD CONSTRAINT hl7_data_type_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY human
    ADD CONSTRAINT human_pkey PRIMARY KEY (MAMMAL_ID);

ALTER TABLE ONLY in_law
    ADD CONSTRAINT in_law_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY integer_key
    ADD CONSTRAINT integer_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY integer_primitive_key
    ADD CONSTRAINT integer_primitive_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY keychain
    ADD CONSTRAINT keychain_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY latch_key
    ADD CONSTRAINT latch_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY lcd_monitor
    ADD CONSTRAINT lcd_monitor_pkey PRIMARY KEY (MONITOR_ID);


ALTER TABLE ONLY long_key
    ADD CONSTRAINT long_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY long_primitive_key
    ADD CONSTRAINT long_primitive_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY luggage
    ADD CONSTRAINT luggage_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY mammal
    ADD CONSTRAINT mammal_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY monitor
    ADD CONSTRAINT monitor_pkey PRIMARY KEY (DISPLAY_ID);

ALTER TABLE ONLY no_id_key
    ADD CONSTRAINT no_id_key_pkey PRIMARY KEY (MY_KEY);

ALTER TABLE ONLY orderline
    ADD CONSTRAINT orderline_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY parent
    ADD CONSTRAINT parent_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY passanger
    ADD CONSTRAINT passanger_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY pendant
    ADD CONSTRAINT pendant_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY private_teacher
    ADD CONSTRAINT private_teacher_pkey PRIMARY KEY (TEACHER_ID);

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY professor
    ADD CONSTRAINT professor_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY project
    ADD CONSTRAINT project_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY pupil
    ADD CONSTRAINT pupil_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY restaurant
    ADD CONSTRAINT restaurant_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY saltwater_fish_tank
    ADD CONSTRAINT saltwater_fish_tank_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY saltwater_fish_tank_substrate
    ADD CONSTRAINT saltwater_fish_tank_substrate_pkey PRIMARY KEY (SALTWATER_FISH_TANK_ID, SUBSTRATE_ID);

ALTER TABLE ONLY shirt_button
    ADD CONSTRAINT shirt_button_pkey PRIMARY KEY (BUTTON_ID, SHIRT_ID);

ALTER TABLE ONLY shirt
    ADD CONSTRAINT shirt_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY shoes
    ADD CONSTRAINT shoes_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY song
    ADD CONSTRAINT song_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY string_key
    ADD CONSTRAINT string_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY string_primitive_key
    ADD CONSTRAINT string_primitive_key_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY student
    ADD CONSTRAINT student_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY substrate
    ADD CONSTRAINT substrate_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY suit
    ADD CONSTRAINT suit_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY all_validation_type
    ADD CONSTRAINT sys_c0068335 UNIQUE (ID);

ALTER TABLE ONLY tank_accessory
    ADD CONSTRAINT tank_accessory_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY tank_tank_accessory
    ADD CONSTRAINT tank_tank_accessory_pkey PRIMARY KEY (TANK_ACCESSORY_ID, TANK_ID);

ALTER TABLE ONLY teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY tenured_professor
    ADD CONSTRAINT tenured_professor_pkey PRIMARY KEY (PROFESSOR_ID);

ALTER TABLE ONLY undergraduate_student
    ADD CONSTRAINT undergraduate_student_pkey PRIMARY KEY (STUDENT_ID);

ALTER TABLE ONLY utensil
    ADD CONSTRAINT utensil_pkey PRIMARY KEY (ID);

ALTER TABLE ONLY wheel
    ADD CONSTRAINT wheel_pkey PRIMARY KEY (ID);

DROP INDEX IF EXISTS FK_ALL_DATA_TYPE_ALL_DATA_TYPE;
CREATE INDEX FK_ALL_DATA_TYPE_ALL_DATA_TYPE ON all_data_type_string_coll USING btree (ALL_DATA_TYPE_ID);

DROP INDEX IF EXISTS FK_ASSISTANT_PROFESSOR;
CREATE INDEX FK_ASSISTANT_PROFESSOR ON assistant USING btree (PROFESSOR_ID);

DROP INDEX IF EXISTS FK_AUTHOR_BOOK_BOOK;
CREATE INDEX FK_AUTHOR_BOOK_BOOK ON author_book USING btree (BOOK_ID);

DROP INDEX IF EXISTS FK_CARD_SUIT;
CREATE INDEX FK_CARD_SUIT ON card USING btree (SUIT_ID);

DROP INDEX IF EXISTS FK_CHEF_RESTAURANT;
CREATE INDEX FK_CHEF_RESTAURANT ON chef USING btree (RESTAURANT_ID);

DROP INDEX IF EXISTS FK_CREDIT_BANK;
CREATE INDEX FK_CREDIT_BANK ON credit USING btree (BANK_ID);

DROP INDEX IF EXISTS FK_DESSERT_UTENSIL_UTENSIL;
CREATE INDEX FK_DESSERT_UTENSIL_UTENSIL ON dessert_utensil USING btree (UTENSIL_ID);

DROP INDEX IF EXISTS FK_EMPLOYEE_PROJECT_PROJECT;
CREATE INDEX FK_EMPLOYEE_PROJECT_PROJECT ON employee_project USING btree (PROJECT_ID);

DROP INDEX IF EXISTS FK_FLIGHT_PASSANGER_PASSANGER;
CREATE INDEX FK_FLIGHT_PASSANGER_PASSANGER ON flight_passanger USING btree (PASSANGER_ID);

DROP INDEX IF EXISTS FK_HAND_CARD_CARD;
CREATE INDEX FK_HAND_CARD_CARD ON hand_card USING btree (CARD_ID);

DROP INDEX IF EXISTS FK_HARD_DRIVE_COMPUTER;
CREATE INDEX FK_HARD_DRIVE_COMPUTER ON hard_drive USING btree (COMPUTER_ID);

DROP INDEX IF EXISTS FK_LATCH_KEY_KEYCHAIN;
CREATE INDEX FK_LATCH_KEY_KEYCHAIN ON latch_key USING btree (KEYCHAIN_ID);

DROP INDEX IF EXISTS FK_LUGGAGE_WHEEL;
CREATE INDEX FK_LUGGAGE_WHEEL ON luggage USING btree (WHEEL_ID);

DROP INDEX IF EXISTS FK_PUPIL_TEACHER;
CREATE INDEX FK_PUPIL_TEACHER ON pupil USING btree (TEACHER_ID);

DROP INDEX IF EXISTS FK_SHIRT_BUTTON_BUTTON;
CREATE INDEX FK_SHIRT_BUTTON_BUTTON ON shirt_button USING btree (BUTTON_ID);

DROP INDEX IF EXISTS FK_SHOES_DESIGNER;
CREATE INDEX FK_SHOES_DESIGNER ON shoes USING btree (DESIGNER_ID);

DROP INDEX IF EXISTS FK_SUIT_DECK;
CREATE INDEX FK_SUIT_DECK ON suit USING btree (DECK_ID);

DROP INDEX IF EXISTS FK_SWFT_SUBSTRATE_SUBSTRATE;
CREATE INDEX FK_SWFT_SUBSTRATE_SUBSTRATE ON saltwater_fish_tank_substrate USING btree (SUBSTRATE_ID);

DROP INDEX IF EXISTS FK_TANK_TANK_ACCESSORY;
CREATE INDEX FK_TANK_TANK_ACCESSORY ON tank_tank_accessory USING btree (TANK_ACCESSORY_ID);

COMMIT;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: iso21090schema; Type: SCHEMA; Schema: -; Owner: -
--

-- CREATE SCHEMA iso21090schema;

SET search_path TO public;

-- SET search_path = iso21090schema, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ad_ad_datatype_value9; Type: TABLE 
--

DROP TABLE IF EXISTS ad_ad_datatype_value9 CASCADE;
CREATE TABLE ad_ad_datatype_value9 (
    ad_datatype_id integer NOT NULL,
    ad_datatype_value9_id integer NOT NULL
);


--
-- Name: ad_datatype; Type: TABLE 
--

DROP TABLE IF EXISTS ad_datatype CASCADE;
CREATE TABLE ad_datatype (
    id integer NOT NULL,
    value1_al_value character varying(50),
    value2_dal_value character varying(50),
    value2_dal_code character varying(50),
    value3_al_value character varying(50),
    value3_al_code character varying(50),
    value3_al_codesystem character varying(50),
    value4_al1_value character varying(50),
    value4_al2_value character varying(50),
    value4_al1_code character varying(50),
    value4_al2_code character varying(50),
    value4_al1_codesystem character varying(50),
    value4_al2_codesystem character varying(50),
    value5_al_value character varying(50),
    value5_al_code character varying(50),
    value5_al_codesystem character varying(50),
    value5_dal_code character varying(50),
    value5_dal_value character varying(50),
    value5_dal_codesystem character varying(50),
    value5_cty_value character varying(50),
    value5_cty_code character varying(50),
    value5_cty_codesystem character varying(50),
    value6_adl_value character varying(50),
    value6_al_value character varying(50),
    value6_bnn_value character varying(50),
    value6_bnr_value character varying(50),
    value6_bns_value character varying(50),
    value6_car_value character varying(50),
    value6_cen_value character varying(50),
    value6_cnt_value character varying(50),
    value6_cpa_value character varying(50),
    value6_cty_value character varying(50),
    value6_dal_value character varying(50),
    value6_del_value character varying(50),
    value6_dinsta_value character varying(50),
    value6_dinstq_value character varying(50),
    value6_dir_value character varying(50),
    value6_dmod_value character varying(50),
    value6_dmodid_value character varying(50),
    value6_int_value character varying(50),
    value6_pob_value character varying(50),
    value6_pre_value character varying(50),
    value6_sal_value character varying(50),
    value6_sta_value character varying(50),
    value6_stb_value character varying(50),
    value6_str_value character varying(50),
    value6_sttyp_value character varying(50),
    value6_unid_value character varying(50),
    value6_unit_value character varying(50),
    value6_zip_value character varying(50),
    value6_adl_code character varying(50),
    value6_bns_code character varying(50),
    value6_bns_codesystem character varying(50),
    value6_dal_code character varying(50),
    value6_dal_codesystem character varying(50),
    value6_int_code character varying(50),
    value6_int_codesystem character varying(50),
    value6_stb_code character varying(50),
    value6_stb_codesystem character varying(50),
    value6_zip_codesystem character varying(50),
    value6_zip_code character varying(50),
    ad_datatype_value8_id integer
);


--
-- Name: ad_datatype_value7; Type: TABLE 
--
DROP TABLE IF EXISTS ad_datatype_value7 CASCADE;
CREATE TABLE ad_datatype_value7 (
    ad_datatype_id integer,
    al_value character varying(50),
    al_code character varying(50),
    al_codesystem character varying(50),
    dal_code character varying(50),
    dal_value character varying(50),
    dal_codesystem character varying(50),
    cty_value character varying(50),
    cty_code character varying(50),
    cty_codesystem character varying(50)
);


--
-- Name: ad_datatype_value8; Type: TABLE 
--
DROP TABLE IF EXISTS ad_datatype_value8 CASCADE;
CREATE TABLE ad_datatype_value8 (
    id integer NOT NULL,
    al_value character varying(50),
    al_code character varying(50),
    al_codesystem character varying(50),
    dal_code character varying(50),
    dal_value character varying(50),
    dal_codesystem character varying(50),
    cty_value character varying(50),
    cty_code character varying(50),
    cty_codesystem character varying(50)
);


--
-- Name: ad_datatype_value9; Type: TABLE 
--
DROP TABLE IF EXISTS ad_datatype_value9 CASCADE;
CREATE TABLE ad_datatype_value9 (
    id integer NOT NULL,
    al_value character varying(50),
    al_code character varying(50),
    al_codesystem character varying(50),
    dal_code character varying(50),
    dal_value character varying(50),
    dal_codesystem character varying(50),
    cty_value character varying(50),
    cty_code character varying(50),
    cty_codesystem character varying(50)
);


--
-- Name: bl_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS bl_datatype CASCADE;
CREATE TABLE bl_datatype (
    id integer NOT NULL,
    value1_value boolean,
    value2_null_flavor character varying(50),
    value2_value boolean
);


--
-- Name: bl_nonnull_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS bl_nonnull_datatype CASCADE;
CREATE TABLE bl_nonnull_datatype (
    id integer NOT NULL,
    value1_value boolean
);


--
-- Name: cd_cd_datatype_value8; Type: TABLE 
--
DROP TABLE IF EXISTS cd_cd_datatype_value8 CASCADE;
CREATE TABLE cd_cd_datatype_value8 (
    cd_data_type_id integer NOT NULL,
    cd_datatype_value8_id integer NOT NULL
);


--
-- Name: cd_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS cd_datatype CASCADE;
CREATE TABLE cd_datatype (
    id integer NOT NULL,
    value1_code character varying(50),
    value2_null_flavor character varying(50),
    value2_code character varying(50),
    value3_code character varying(50),
    value4_null_flavor character varying(50),
    value4_code character varying(50),
    value4_code_system character varying(50),
    value4_code_system_version character varying(50),
    value4_code_system_name character varying(50),
    value4_display_null_flavor character varying(50),
    value4_display_value character varying(50),
    value4_orig_txt_null_flavor character varying(50),
    value4_orig_txt_value  character varying(50),
    value4_orig_txt_description character varying(50),
    value5_null_flavor character varying(50),
    value5_code character varying(50),
    value5_code_system character varying(50),
    value5_code_system_name character varying(50),
    value5_code_system_version character varying(50),
    value5_display_null_flavor character varying(50),
    value5_display_value character varying(50),
    value5_orig_txt_null_flavor character varying(50),
    value5_orig_txt_value  character varying(50),
    value5_orig_txt_description character varying(50),
    cd_datatype_value7_id integer
);


--
-- Name: cd_datatype_value6; Type: TABLE 
--
DROP TABLE IF EXISTS cd_datatype_value6 CASCADE;
CREATE TABLE cd_datatype_value6 (
    cd_datatype_id integer NOT NULL,
    null_flavor character varying(50),
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50),
    code_system_version character varying(50),
    display_null_flavor character varying(50),
    display_value character varying(50),
    orig_txt_null_flavor character varying(50),
    orig_txt_value  character varying(50),
    orig_txt_description character varying(50)
);


--
-- Name: cd_datatype_value7; Type: TABLE 
--
DROP TABLE IF EXISTS cd_datatype_value7 CASCADE;
CREATE TABLE cd_datatype_value7 (
    id integer NOT NULL,
    null_flavor character varying(50),
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50),
    code_system_version character varying(50),
    display_null_flavor character varying(50),
    display_value character varying(50),
    orig_txt_null_flavor character varying(50),
    orig_txt_value  character varying(50),
    orig_txt_description character varying(50)
);

--
-- Name: cd_datatype_value8; Type: TABLE 
--
DROP TABLE IF EXISTS cd_datatype_value8 CASCADE;
CREATE TABLE cd_datatype_value8 (
    id integer NOT NULL,
    null_flavor character varying(50),
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50),
    code_system_version character varying(50),
    display_null_flavor character varying(50),
    display_value character varying(50),
    orig_txt_null_flavor character varying(50),
    orig_txt_value  character varying(50),
    orig_txt_description character varying(50)
);


--
-- Name: dset_ad_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ad_datatype CASCADE;
CREATE TABLE dset_ad_datatype (
    id integer NOT NULL
);


--
-- Name: dset_ad_dset_ad_value8; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ad_dset_ad_value8 CASCADE;
CREATE TABLE dset_ad_dset_ad_value8 (
    dset_ad_datatype_id integer NOT NULL,
    dset_ad_value8_id integer NOT NULL
);


--
-- Name: dset_ad_value1; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ad_value1 CASCADE;
CREATE TABLE dset_ad_value1 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_al_value character varying(50)
);


--
-- Name: dset_ad_value2; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ad_value2 CASCADE;
CREATE TABLE dset_ad_value2 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_dal_value character varying(50),
    adxp_dal_code character varying(50)
);


--
-- Name: dset_ad_value3; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ad_value3 CASCADE;
CREATE TABLE dset_ad_value3 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_al_value character varying(50),
    adxp_al_code character varying(50),
    adxp_al_codesystem character varying(50)
);


--
-- Name: dset_ad_value4; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ad_value4 CASCADE;
CREATE TABLE dset_ad_value4 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_al1_value character varying(50),
    adxp_al2_value character varying(50),
    adxp_al1_code character varying(50),
    adxp_al2_code character varying(50),
    adxp_al2_codesystem character varying(50),
    adxp_al1_codesystem character varying(50)
);


--
-- Name: dset_ad_value5; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ad_value5 CASCADE;
CREATE TABLE dset_ad_value5 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_al_value character varying(50),
    adxp_al_code character varying(50),
    adxp_al_codesystem character varying(50),
    adxp_dal_code character varying(50),
    adxp_dal_codesystem character varying(50),
    adxp_dal_value character varying(50),
    adxp_cty_value character varying(50),
    adxp_cty_code character varying(50),
    adxp_cty_codesystem character varying(50)
);


--
-- Name: dset_ad_value6; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ad_value6 CASCADE;
CREATE TABLE dset_ad_value6 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_adl_value character varying(50),
    adxp_al_value character varying(50),
    adxp_bnn_value character varying(50),
    adxp_bnr_value character varying(50),
    adxp_bns_value character varying(50),
    adxp_car_value character varying(50),
    adxp_cen_value character varying(50),
    adxp_cnt_value character varying(50),
    adxp_cpa_value character varying(50),
    adxp_cty_value character varying(50),
    adxp_dal_value character varying(50),
    adxp_del_value character varying(50),
    adxp_dinsta_value character varying(50),
    adxp_dinstq_value character varying(50),
    adxp_dir_value character varying(50),
    adxp_dmod_value character varying(50),
    adxp_dmodid_value character varying(50),
    adxp_int_value character varying(50),
    adxp_pob_value character varying(50),
    adxp_pre_value character varying(50),
    adxp_sal_value character varying(50),
    adxp_sta_value character varying(50),
    adxp_stb_value character varying(50),
    adxp_str_value character varying(50),
    adxp_sttyp_value character varying(50),
    adxp_unid_value character varying(50),
    adxp_unit_value character varying(50),
    adxp_zip_value character varying(50),
    adxp_adl_code character varying(50),
    adxp_bns_code character varying(50),
    adxp_bns_codesystem character varying(50),
    adxp_dal_code character varying(50),
    adxp_dal_codesystem character varying(50),
    adxp_int_code character varying(50),
    adxp_int_codesystem character varying(50),
    adxp_stb_code character varying(50),
    adxp_stb_codesystem character varying(50),
    adxp_zip_codesystem character varying(50),
    adxp_zip_code character varying(50)
);


--
-- Name: dset_ad_value7; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ad_value7 CASCADE;
CREATE TABLE dset_ad_value7 (
    id integer NOT NULL,
    dset_ad_datatype_id integer,
    adxp_al_value character varying(50),
    adxp_al_code character varying(50),
    adxp_al_codesystem character varying(50),
    adxp_dal_code character varying(50),
    adxp_dal_codesystem character varying(50),
    adxp_dal_value character varying(50),
    adxp_cty_value character varying(50),
    adxp_cty_code character varying(50),
    adxp_cty_codesystem character varying(50)
);


--
-- Name: dset_ad_value8; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ad_value8 CASCADE;
CREATE TABLE dset_ad_value8 (
    id integer NOT NULL,
    adxp_al_value character varying(50),
    adxp_al_code character varying(50),
    adxp_al_codesystem character varying(50),
    adxp_dal_code character varying(50),
    adxp_dal_codesystem character varying(50),
    adxp_dal_value character varying(50),
    adxp_cty_value character varying(50),
    adxp_cty_code character varying(50),
    adxp_cty_codesystem character varying(50)
);


--
-- Name: dset_cd; Type: TABLE 
--
DROP TABLE IF EXISTS dset_cd CASCADE;
CREATE TABLE dset_cd (
    id integer NOT NULL
);


--
-- Name: dset_cd_cd_value7; Type: TABLE 
--
DROP TABLE IF EXISTS dset_cd_cd_value7 CASCADE;
CREATE TABLE dset_cd_cd_value7 (
    dset_cd_id integer NOT NULL,
    dset_cd_value7_id integer NOT NULL
);


--
-- Name: dset_cd_value1; Type: TABLE 
--
DROP TABLE IF EXISTS dset_cd_value1 CASCADE;
CREATE TABLE dset_cd_value1 (
    dset_cd_id integer NOT NULL,
    code character varying(50)
);


--
-- Name: dset_cd_value2; Type: TABLE 
--
DROP TABLE IF EXISTS dset_cd_value2 CASCADE;
CREATE TABLE dset_cd_value2 (
    dset_cd_id integer NOT NULL,
    code character varying(50),
    null_flavor character varying(50)
);


--
-- Name: dset_cd_value3; Type: TABLE 
--
DROP TABLE IF EXISTS dset_cd_value3 CASCADE;
CREATE TABLE dset_cd_value3 (
    dset_cd_id integer NOT NULL,
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50)
);


--
-- Name: dset_cd_value4; Type: TABLE 
--
DROP TABLE IF EXISTS dset_cd_value4 CASCADE;
CREATE TABLE dset_cd_value4 (
    dset_cd_id integer NOT NULL,
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50),
    code_system_version character varying(50),
    displayable_value character varying(50),
    originaltext_value character varying(50),
    originaltext_desc character varying(50)
);


--
-- Name: dset_cd_value5; Type: TABLE 
--
DROP TABLE IF EXISTS dset_cd_value5 CASCADE;
CREATE TABLE dset_cd_value5 (
    dset_cd_id integer NOT NULL,
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50),
    code_system_version character varying(50),
    displayable_value character varying(50),
    originaltext_value character varying(50),
    originaltext_desc character varying(50)
);


--
-- Name: dset_cd_value6; Type: TABLE 
--
DROP TABLE IF EXISTS dset_cd_value6 CASCADE;
CREATE TABLE dset_cd_value6 (
    id integer NOT NULL,
    dset_cd_id integer,
    code character varying(50)
);


--
-- Name: dset_cd_value7; Type: TABLE 
--
DROP TABLE IF EXISTS dset_cd_value7 CASCADE;
CREATE TABLE dset_cd_value7 (
    id integer NOT NULL,
    code character varying(50)
);


--
-- Name: dset_en; Type: TABLE 
--
DROP TABLE IF EXISTS dset_en CASCADE;
CREATE TABLE dset_en (
    id integer NOT NULL
);


--
-- Name: dset_en_value1; Type: TABLE 
--
DROP TABLE IF EXISTS dset_en_value1 CASCADE;
CREATE TABLE dset_en_value1 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_value character varying(50)
);


--
-- Name: dset_en_value2; Type: TABLE 
--
DROP TABLE IF EXISTS dset_en_value2 CASCADE;
CREATE TABLE dset_en_value2 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_value character varying(50),
    enxp_pn_code character varying(50)
);


--
-- Name: dset_en_value3; Type: TABLE 
--
DROP TABLE IF EXISTS dset_en_value3 CASCADE;
CREATE TABLE dset_en_value3 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_code character varying(50),
    enxp_pn_value character varying(50),
    enxp_pn_codesystem character varying(50),
    enxp_pn_codesystemversion character varying(50)
);


--
-- Name: dset_en_value4; Type: TABLE 
--
DROP TABLE IF EXISTS dset_en_value4 CASCADE;
CREATE TABLE dset_en_value4 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_value character varying(50),
    enxp_pn_enpq character varying(50)
);


--
-- Name: dset_en_value5; Type: TABLE 
--
DROP TABLE IF EXISTS dset_en_value5 CASCADE;
CREATE TABLE dset_en_value5 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_value character varying(50),
    enxp_pn2_value character varying(50),
    enxp_pn_code character varying(50),
    enxp_pn2_code character varying(50),
    enxp_pn_codesystem character varying(50),
    enxp_pn2_codesystem character varying(50),
    enxp_pn2_codesystemversion character varying(50),
    enxp_pn_codesystemversion character varying(50)
);


--
-- Name: dset_en_value6; Type: TABLE 
--
DROP TABLE IF EXISTS dset_en_value6 CASCADE;
CREATE TABLE dset_en_value6 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_value character varying(50),
    enxp_pn_code character varying(50),
    enxp_pn_codesystem character varying(50),
    enxp_pn_codesystemversion character varying(50),
    enxp_on_value character varying(50),
    enxp_on_code character varying(50),
    enxp_on_codesystem character varying(50),
    enxp_on_codesystemversion character varying(50)
);


--
-- Name: dset_en_value7; Type: TABLE 
--
DROP TABLE IF EXISTS dset_en_value7 CASCADE;
CREATE TABLE dset_en_value7 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_code character varying(50),
    enxp_on_code character varying(50),
    enxp_pn_value character varying(50),
    enxp_on_value character varying(50),
    enxp_on_codesystem character varying(50),
    enxp_pn_codesystem character varying(50),
    enxp_on_codesystemversion character varying(50),
    enxp_pn_codesystemversion character varying(50)
);


--
-- Name: dset_ii; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ii CASCADE;
CREATE TABLE dset_ii (
    id integer NOT NULL
);


--
-- Name: dset_ii_ii_value6; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ii_ii_value6 CASCADE;
CREATE TABLE dset_ii_ii_value6 (
    dset_ii_id integer NOT NULL,
    dset_ii_value6_id integer NOT NULL
);


--
-- Name: dset_ii_value1; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ii_value1 CASCADE;
CREATE TABLE dset_ii_value1 (
    dset_ii_id integer NOT NULL,
    extension character varying(50)
);


--
-- Name: dset_ii_value2; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ii_value2 CASCADE;
CREATE TABLE dset_ii_value2 (
    dset_ii_id integer NOT NULL,
    extension character varying(50),
    root character varying(50),
    null_flavor character varying(50)
);


--
-- Name: dset_ii_value3; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ii_value3 CASCADE;
CREATE TABLE dset_ii_value3 (
    dset_ii_id integer NOT NULL,
    extension character varying(50),
    identifier_name character varying(50),
    displayable_value character varying(1),
    reliability character varying(50),
    scope character varying(50)
);


--
-- Name: dset_ii_value4; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ii_value4 CASCADE;
CREATE TABLE dset_ii_value4 (
    dset_ii_id integer NOT NULL,
    root character varying(50),
    extension character varying(50),
    identifier_name character varying(50),
    reliability character varying(50),
    scope character varying(50),
    displayable_value character varying(1),
    null_flavor character varying(50)
);


--
-- Name: dset_ii_value5; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ii_value5 CASCADE;
CREATE TABLE dset_ii_value5 (
    id integer NOT NULL,
    dset_ii_id integer,
    extension character varying(50)
);


--
-- Name: dset_ii_value6; Type: TABLE 
--
DROP TABLE IF EXISTS dset_ii_value6 CASCADE;
CREATE TABLE dset_ii_value6 (
    id integer NOT NULL,
    extension character varying(50)
);


--
-- Name: dset_tel; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel CASCADE;
CREATE TABLE dset_tel (
    id integer NOT NULL,
    null_flavor character varying(50)
);


--
-- Name: dset_tel_email; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_email CASCADE;
CREATE TABLE dset_tel_email (
    id integer NOT NULL,
    null_flavor character varying(50)
);


--
-- Name: dset_tel_email_value1; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_email_value1 CASCADE;
CREATE TABLE dset_tel_email_value1 (
    dset_tel_email_id integer NOT NULL,
    tel_email_value character varying(50)
);


--
-- Name: dset_tel_email_value2; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_email_value2 CASCADE;
CREATE TABLE dset_tel_email_value2 (
    dset_tel_email_id integer,
    tel_email_value character varying(50),
    tel_email_null_flavor character varying(50)
);


--
-- Name: dset_tel_person; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_person CASCADE;
CREATE TABLE dset_tel_person (
    id integer NOT NULL,
    null_flavor character varying(50)
);


--
-- Name: dset_tel_person_value1; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_person_value1 CASCADE;
CREATE TABLE dset_tel_person_value1 (
    dset_tel_person_id integer NOT NULL,
    tel_person_value character varying(50)
);


--
-- Name: dset_tel_phone; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_phone CASCADE;
CREATE TABLE dset_tel_phone (
    id integer NOT NULL,
    null_flavor character varying(50)
);


--
-- Name: dset_tel_phone_value1; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_phone_value1 CASCADE;
CREATE TABLE dset_tel_phone_value1 (
    dset_tel_phone_id integer NOT NULL,
    tel_phone_value character varying(50)
);


--
-- Name: dset_tel_tel_value_3; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_tel_value_3 CASCADE;
CREATE TABLE dset_tel_tel_value_3 (
    dset_tel_id integer NOT NULL,
    dset_tel_value3_id integer NOT NULL
);


--
-- Name: dset_tel_url; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_url CASCADE;
CREATE TABLE dset_tel_url (
    id integer NOT NULL,
    null_flavor character varying(50)
);


--
-- Name: dset_tel_url_value1; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_url_value1 CASCADE;
CREATE TABLE dset_tel_url_value1 (
    dset_tel_url_id integer NOT NULL,
    tel_url_value character varying(50)
);


--
-- Name: dset_tel_value1; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_value1 CASCADE;
CREATE TABLE dset_tel_value1 (
    dset_tel_id integer NOT NULL,
    tel_value character varying(50)
);


--
-- Name: dset_tel_value2; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_value2 CASCADE;
CREATE TABLE dset_tel_value2 (
    id integer NOT NULL,
    dset_tel_id integer,
    tel_value character varying(50)
);


--
-- Name: dset_tel_value3; Type: TABLE 
--
DROP TABLE IF EXISTS dset_tel_value3 CASCADE;
CREATE TABLE dset_tel_value3 (
    id integer NOT NULL,
    tel_value character varying(50)
);


--
-- Name: ed_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS ed_datatype CASCADE;
CREATE TABLE ed_datatype (
    id integer NOT NULL,
    value1_data bytea,
    value2_null_flavor character varying(50),
    value2_data bytea,
    value2_compression character varying(50),
    value3_null_flavor character varying(50),
    value3_data bytea,
    value3_compression character varying(50),
    value3_description character varying(50),
    value3_value character varying(50)
);


--
-- Name: ed_text_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS ed_text_datatype CASCADE;
CREATE TABLE ed_text_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50),
    value3_null_flavor character varying(50),
    value3_data bytea,
    value3_compression character varying(50),
    value3_description character varying(50),
    value3_value character varying(50)
);


--
-- Name: en_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS en_datatype CASCADE;
CREATE TABLE en_datatype (
    id integer NOT NULL,
    value1_pn_value character varying(50),
    value2_pn_value character varying(50),
    value2_pn_code character varying(50),
    value3_pn_value character varying(50),
    value3_pn_code character varying(50),
    value3_pn_code_system character varying(50),
    value3_pn_code_system_version character varying(50),
    value4_pn_value character varying(50),
    value4_pn_enpq character varying(50),
    value5_pn_value character varying(50),
    value5_pn2_value character varying(50),
    value5_pn_code character varying(50),
    value5_pn2_code character varying(50),
    value5_pn_code_system character varying(50),
    value5_pn2_code_system character varying(50),
    value5_pn_code_system_version character varying(50),
    value5_pn2_code_system_version character varying(50),
    value6_pn_value character varying(50),
    value6_on_value character varying(50),
    value6_pn_code character varying(50),
    value6_on_code character varying(50),
    value6_pn_code_system character varying(50),
    value6_on_code_system character varying(50),
    value6_pn_code_system_version character varying(50),
    value6_on_code_system_version character varying(50)
);


--
-- Name: en_on_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS en_on_datatype CASCADE;
CREATE TABLE en_on_datatype (
    id integer NOT NULL,
    value1_on_value character varying(50)
);


--
-- Name: en_pn_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS en_pn_datatype CASCADE;
CREATE TABLE en_pn_datatype (
    id integer NOT NULL,
    value1_pn_value character varying(50)
);


--
-- Name: ii_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS ii_datatype CASCADE;
CREATE TABLE ii_datatype (
    id integer NOT NULL,
    value1_extension character varying(50),
    value2_null_flavor character varying(50),
    value2_root character varying(50),
    value2_extension character varying(50),
    value3_null_flavor character varying(50),
    value3_extension character varying(50),
    value3_identifier_name character varying(50),
    value3_reliability character varying(50),
    value3_scope character varying(50),
    value3_displayable character varying(1),
    value4_null_flavor character varying(50),
    value4_root character varying(50),
    value4_extension character varying(50),
    value4_identifier_name character varying(50),
    value4_reliability character varying(50),
    value4_scope character varying(50),
    value4_displayable character varying(1)
);


--
-- Name: int_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS int_datatype CASCADE;
CREATE TABLE int_datatype (
    id integer NOT NULL,
    value1_value numeric,
    value2_null_flavor character varying(50),
    value2_value numeric
);


--

-- Name: ivl_int; Type: TABLE 
--
DROP TABLE IF EXISTS ivl_int CASCADE;
CREATE TABLE ivl_int (
    id integer NOT NULL,
    value1_low_value numeric,
    value1_high_value numeric,
    value2_high_value numeric,
    value2_lowclosed character varying(1),
    value3_any_value numeric,
    value3_low_value numeric,
    value3_high_closed character varying(1),
    value4_low_value numeric,
    value4_high_value numeric,
    value4_width_value integer,
    value4_null_flavor character varying(50)
);


--

-- Name: ivl_pq; Type: TABLE 
--
DROP TABLE IF EXISTS ivl_pq CASCADE;
CREATE TABLE ivl_pq (
    id integer NOT NULL,
    value1_low_value numeric,
    value2_low_value numeric,
    value2_low_precision numeric,
    value2_low_unit character varying(50),
    value3_low_value numeric,
    value3_low_precision numeric,
    value3_low_unit character varying(50),
    value3_null_flavor character varying(50),
    value4_high_value numeric,
    value4_high_precision numeric,
    value4_high_unit character varying(50),
    value4_high_closed character varying(1),
    value4_high_null_flavor character varying(50),
    value4_low_value numeric,
    value4_low_precision numeric,
    value4_low_unit character varying(50),
    value4_low_null_flavor character varying(50),
    value4_low_closed character varying(1),
    value4_width_value numeric,
    value4_width_precision numeric,
    value4_width_unit character varying(50),
    value4_width_null_flavor character varying(50),
    value4_null_flavor character varying(50)
);


--
-- Name: ivl_pqv; Type: TABLE 
--
DROP TABLE IF EXISTS ivl_pqv CASCADE;
CREATE TABLE ivl_pqv (
    id integer NOT NULL,
    value1_low_value numeric,
    value1_high_value numeric,
    value2_low_value numeric,
    value2_low_precision numeric,
    value2_high_closed character varying(1),
    value3_low_value numeric,
    value3_low_precision numeric,
    value3_high_value numeric,
    value3_high_precision numeric,
    value3_high_null_flavor character varying(50),
    value4_high_value numeric,
    value4_high_precision numeric,
    value4_high_null_flavor character varying(50),
    value4_low_value numeric,
    value4_low_precision numeric,
    value4_low_null_flavor character varying(50),
    value4_width_value numeric,
    value4_width_precision numeric,
    value4_width_null_flavor character varying(50)
);


--
-- Name: ivl_real; Type: TABLE 
--
DROP TABLE IF EXISTS ivl_real CASCADE;
CREATE TABLE ivl_real (
    id integer,
    value1_low_value double precision,
    value1_high_value double precision,
    value2_high_value double precision,
    value2_low_closed character varying(1),
    value3_any_value double precision,
    value3_high_value double precision,
    value3_high_closed character varying(1),
    value3_low_value double precision,
    value3_low_closed character varying(1),
    value3_width_value double precision,
    value3_width_null_flavor character varying(50),
    value3_null_flavor character varying(50)
);


--
-- Name: ivl_ts; Type: TABLE 
--
DROP TABLE IF EXISTS ivl_ts CASCADE;
CREATE TABLE ivl_ts (
    id integer,
    value1_low_value timestamp without time zone,
    value1_high_value timestamp without time zone,
    value2_high_value timestamp without time zone,
    value2_low_closed character varying(1),
    value3_high_value timestamp without time zone,
    value3_low_value timestamp without time zone,
    value3_width_null_flavor character varying(50),
    value3_width_value integer,
    value3_null_flavor character varying(50)
);


--
-- Name: pq_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS pq_datatype CASCADE;
CREATE TABLE pq_datatype (
    id integer NOT NULL,
    value1_value numeric,
    value1_unit character varying(50),
    value2_value numeric,
    value3_null_flavor character varying(50),
    value3_unit character varying(50),
    value3_value numeric,
    value3_precision numeric
);


--
-- Name: pqv_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS pqv_datatype CASCADE;
CREATE TABLE pqv_datatype (
    id integer NOT NULL,
    value1_value numeric,
    value2_null_flavor character varying(50),
    value2_value numeric,
    value2_precision numeric,
    value3_value numeric,
    value3_precision numeric,
    value4_null_flavor character varying(50),
    value4_precision numeric,
    value4_value numeric
);


--
-- Name: real_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS real_datatype CASCADE;
CREATE TABLE real_datatype (
    id integer NOT NULL,
    value1_value double precision,
    value2_null_flavor character varying(50),
    value2_value double precision
);


--
-- Name: sc_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS sc_datatype CASCADE;
CREATE TABLE sc_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value1_code_code character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50),
    value2_code_null_flavor character varying(50),
    value2_code_code character varying(50),
    value2_code_code_system character varying(50),
    value2_code_code_system_name character varying(50),
    value2_code_code_system_ver character varying(50),
    value3_null_flavor character varying(50),
    value3_value character varying(50),
    value3_code_null_flavor character varying(50),
    value3_code_code character varying(50),
    value3_code_code_system character varying(50),
    value3_code_code_system_name character varying(50),
    value3_code_code_system_ver character varying(50),
    value3_code_display_nflavor character varying(50),
    value3_code_display_value character varying(50),
    value3_code_orig_txt_nflavor character varying(50),
    value3_code_orig_txt_desc character varying(50),
    value3_code_orig_txt_value character varying(50)
);


--
-- Name: st_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS st_datatype CASCADE;
CREATE TABLE st_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: st_nt_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS st_nt_datatype CASCADE;
CREATE TABLE st_nt_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: tel_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS tel_datatype CASCADE;
CREATE TABLE tel_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: tel_email_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS tel_email_datatype CASCADE;
CREATE TABLE tel_email_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: tel_person_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS tel_person_datatype CASCADE;
CREATE TABLE tel_person_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: tel_phone_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS tel_phone_datatype CASCADE;
CREATE TABLE tel_phone_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: tel_url_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS tel_url_datatype CASCADE;
CREATE TABLE tel_url_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: ts_datatype; Type: TABLE 
--
DROP TABLE IF EXISTS ts_datatype CASCADE;
CREATE TABLE ts_datatype (
    id integer NOT NULL,
    value1_value timestamp without time zone,
    value2_null_flavor character varying(50),
    value2_value timestamp without time zone
);


--
-- Data for Name: ad_ad_datatype_value9; Type: TABLE DATA; 
--

INSERT INTO ad_ad_datatype_value9 (ad_datatype_id, ad_datatype_value9_id) VALUES (44, 2);
INSERT INTO ad_ad_datatype_value9 (ad_datatype_id, ad_datatype_value9_id) VALUES (45, 2);
INSERT INTO ad_ad_datatype_value9 (ad_datatype_id, ad_datatype_value9_id) VALUES (46, 2);
INSERT INTO ad_ad_datatype_value9 (ad_datatype_id, ad_datatype_value9_id) VALUES (47, 2);
INSERT INTO ad_ad_datatype_value9 (ad_datatype_id, ad_datatype_value9_id) VALUES (48, 2);


--
-- Data for Name: ad_datatype; Type: TABLE DATA; 
--

INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (2, '1 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (3, '2 E Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (4, '3 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (5, '4 Sun Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (6, '5 Sun Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (7, NULL, '5th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (8, NULL, '6th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (9, NULL, '7th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (10, NULL, '8th Floor', 'NCI5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (11, NULL, '9th Floor', 'NCI6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (12, NULL, NULL, NULL, 'E Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (13, NULL, NULL, NULL, 'E Jefferson Street', 'NCI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (14, NULL, NULL, NULL, 'Jefferson Street', 'NCI1', 'codeSystem1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (15, NULL, NULL, NULL, 'F Jefferson Street', 'NCI2', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (16, NULL, NULL, NULL, 'G Jefferson Street', 'NCI3', 'codeSystem3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, 'E Jefferson Street', NULL, 'NCI1', NULL, 'codeSystem1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, 'F Jefferson Street', NULL, 'NCI2', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'E Jefferson Street', NULL, 'NCI-DC1', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, 'F Jefferson Street', 'F Jefferson Street', NULL, 'NCI-DC2', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, 'G Jefferson Street', 'G Jefferson Street', 'NCI3', 'NCI-DC3', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, 'H Jefferson Street', 'H Jefferson Street', 'NCI4', 'NCI-DC4', 'codeSystem4', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (23, NULL, NULL, NULL, NULL, NULL, NULL, 'I Jefferson Street', 'I Jefferson Street', 'NCI5', 'NCI-DC5', 'codeSystem5', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', NULL, NULL, NULL, 'Delivery line1', NULL, 'Rockville', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE1', NULL, 'DAL_CODE1', 'Delivery line1', NULL, 'Rockville', 'CITY_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', NULL, 'AL_CODE_SYSTEM1', NULL, 'Delivery line1', 'DAL_CODE_SYSTEM1', 'Rockville', NULL, 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE2', 'AL_CODE_SYSTEM1', 'DAL_CODE2', 'Delivery line1', 'DAL_CODE_SYSTEM2', 'Rockville', 'CITY_CODE2', 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE3', 'AL_CODE_SYSTEM1', 'DAL_CODE3', 'Delivery line1', 'DAL_CODE_SYSTEM3', 'Rockville', 'CITY_CODE3', 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE1', 'VALUE6_AL_VALUE1', 'VALUE6_BNN_VALUE1', 'VALUE6_BNR_VALUE1', 'VALUE6_BNS_VALUE1', 'VALUE6_CAR_VALUE1', 'VALUE6_CEN_VALUE1', 'VALUE6_CNT_VALUE1', 'VALUE6_CPA_VALUE1', 'VALUE6_CTY_VALUE1', 'VALUE6_DAL_VALUE1', 'VALUE6_DEL_VALUE1', 'VALUE6_DINSTA_VALUE1', 'VALUE6_DINSTQ_VALUE1', 'VALUE6_DIR_VALUE1', 'VALUE6_DMOD_VALUE1', 'VALUE6_DMODID_VALUE1', 'VALUE6_INT_VALUE1', 'VALUE6_POB_VALUE1', 'VALUE6_PRE_VALUE1', 'VALUE6_SAL_VALUE1', 'VALUE6_STA_VALUE1', 'VALUE6_STB_VALUE1', 'VALUE6_STR_VALUE1', 'VALUE6_STTYP_VALUE1', 'VALUE6_UNID_VALUE1', 'VALUE6_UNIT_VALUE1', 'VALUE6_ZIP_VALUE1', 'VALUE6_ADL_CODE1', 'VALUE6_BNS_CODE1', 'VALUE6_BNS_CODESYSTEM1', 'VALUE6_DAL_CODE1', 'VALUE6_DAL_CODESYSTEM1', 'VALUE6_INT_CODE1', 'VALUE6_INT_CODESYSTEM1', 'VALUE6_STB_CODE1', 'VALUE6_STB_CODESYSTEM1', 'VALUE6_ZIP_CODESYSTEM1', 'VALUE6_ZIP_CODE1', NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE2', 'VALUE6_AL_VALUE2', 'VALUE6_BNN_VALUE2', 'VALUE6_BNR_VALUE2', 'VALUE6_BNS_VALUE2', 'VALUE6_CAR_VALUE2', 'VALUE6_CEN_VALUE2', 'VALUE6_CNT_VALUE2', 'VALUE6_CPA_VALUE2', 'VALUE6_CTY_VALUE2', 'VALUE6_DAL_VALUE2', 'VALUE6_DEL_VALUE2', 'VALUE6_DINSTA_VALUE2', 'VALUE6_DINSTQ_VALUE2', 'VALUE6_DIR_VALUE2', 'VALUE6_DMOD_VALUE2', 'VALUE6_DMODID_VALUE2', 'VALUE6_INT_VALUE2', 'VALUE6_POB_VALUE2', 'VALUE6_PRE_VALUE2', 'VALUE6_SAL_VALUE2', 'VALUE6_STA_VALUE2', 'VALUE6_STB_VALUE2', 'VALUE6_STR_VALUE2', 'VALUE6_STTYP_VALUE2', 'VALUE6_UNID_VALUE2', 'VALUE6_UNIT_VALUE2', 'VALUE6_ZIP_VALUE2', 'VALUE6_ADL_CODE2', 'VALUE6_BNS_CODE2', 'VALUE6_BNS_CODESYSTEM2', 'VALUE6_DAL_CODE2', 'VALUE6_DAL_CODESYSTEM2', 'VALUE6_INT_CODE2', 'VALUE6_INT_CODESYSTEM2', 'VALUE6_STB_CODE2', 'VALUE6_STB_CODESYSTEM2', 'VALUE6_ZIP_CODESYSTEM2', 'VALUE6_ZIP_CODE2', NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE3', 'VALUE6_AL_VALUE3', 'VALUE6_BNN_VALUE3', 'VALUE6_BNR_VALUE3', 'VALUE6_BNS_VALUE3', 'VALUE6_CAR_VALUE3', 'VALUE6_CEN_VALUE3', 'VALUE6_CNT_VALUE3', 'VALUE6_CPA_VALUE3', 'VALUE6_CTY_VALUE3', 'VALUE6_DAL_VALUE3', 'VALUE6_DEL_VALUE3', 'VALUE6_DINSTA_VALUE3', 'VALUE6_DINSTQ_VALUE3', 'VALUE6_DIR_VALUE3', 'VALUE6_DMOD_VALUE3', 'VALUE6_DMODID_VALUE3', 'VALUE6_INT_VALUE3', 'VALUE6_POB_VALUE3', 'VALUE6_PRE_VALUE3', 'VALUE6_SAL_VALUE3', 'VALUE6_STA_VALUE3', 'VALUE6_STB_VALUE3', 'VALUE6_STR_VALUE3', 'VALUE6_STTYP_VALUE3', 'VALUE6_UNID_VALUE3', 'VALUE6_UNIT_VALUE3', 'VALUE6_ZIP_VALUE3', 'VALUE6_ADL_CODE3', 'VALUE6_BNS_CODE3', 'VALUE6_BNS_CODESYSTEM3', 'VALUE6_DAL_CODE3', 'VALUE6_DAL_CODESYSTEM3', 'VALUE6_INT_CODE3', 'VALUE6_INT_CODESYSTEM3', 'VALUE6_STB_CODE3', 'VALUE6_STB_CODESYSTEM3', 'VALUE6_ZIP_CODESYSTEM3', 'VALUE6_ZIP_CODE3', NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE4', 'VALUE6_AL_VALUE4', 'VALUE6_BNN_VALUE4', 'VALUE6_BNR_VALUE4', 'VALUE6_BNS_VALUE4', 'VALUE6_CAR_VALUE4', 'VALUE6_CEN_VALUE4', 'VALUE6_CNT_VALUE4', 'VALUE6_CPA_VALUE4', 'VALUE6_CTY_VALUE4', 'VALUE6_DAL_VALUE4', 'VALUE6_DEL_VALUE4', 'VALUE6_DINSTA_VALUE4', 'VALUE6_DINSTQ_VALUE4', 'VALUE6_DIR_VALUE4', 'VALUE6_DMOD_VALUE4', 'VALUE6_DMODID_VALUE4', 'VALUE6_INT_VALUE4', 'VALUE6_POB_VALUE4', 'VALUE6_PRE_VALUE4', 'VALUE6_SAL_VALUE4', 'VALUE6_STA_VALUE4', 'VALUE6_STB_VALUE4', 'VALUE6_STR_VALUE4', 'VALUE6_STTYP_VALUE4', 'VALUE6_UNID_VALUE4', 'VALUE6_UNIT_VALUE4', 'VALUE6_ZIP_VALUE4', 'VALUE6_ADL_CODE4', 'VALUE6_BNS_CODE4', 'VALUE6_BNS_CODESYSTEM4', 'VALUE6_DAL_CODE4', 'VALUE6_DAL_CODESYSTEM4', 'VALUE6_INT_CODE4', 'VALUE6_INT_CODESYSTEM4', 'VALUE6_STB_CODE4', 'VALUE6_STB_CODESYSTEM4', 'VALUE6_ZIP_CODESYSTEM4', 'VALUE6_ZIP_CODE4', NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE5', 'VALUE6_AL_VALUE5', 'VALUE6_BNN_VALUE5', 'VALUE6_BNR_VALUE5', 'VALUE6_BNS_VALUE5', 'VALUE6_CAR_VALUE5', 'VALUE6_CEN_VALUE5', 'VALUE6_CNT_VALUE5', 'VALUE6_CPA_VALUE5', 'VALUE6_CTY_VALUE5', 'VALUE6_DAL_VALUE5', 'VALUE6_DEL_VALUE5', 'VALUE6_DINSTA_VALUE5', 'VALUE6_DINSTQ_VALUE5', 'VALUE6_DIR_VALUE5', 'VALUE6_DMOD_VALUE5', 'VALUE6_DMODID_VALUE5', 'VALUE6_INT_VALUE5', 'VALUE6_POB_VALUE5', 'VALUE6_PRE_VALUE5', 'VALUE6_SAL_VALUE5', 'VALUE6_STA_VALUE5', 'VALUE6_STB_VALUE5', 'VALUE6_STR_VALUE5', 'VALUE6_STTYP_VALUE5', 'VALUE6_UNID_VALUE5', 'VALUE6_UNIT_VALUE5', 'VALUE6_ZIP_VALUE5', 'VALUE6_ADL_CODE5', 'VALUE6_BNS_CODE5', 'VALUE6_BNS_CODESYSTEM5', 'VALUE6_DAL_CODE5', 'VALUE6_DAL_CODESYSTEM5', 'VALUE6_INT_CODE5', 'VALUE6_INT_CODESYSTEM5', 'VALUE6_STB_CODE5', 'VALUE6_STB_CODESYSTEM5', 'VALUE6_ZIP_CODESYSTEM5', 'VALUE6_ZIP_CODE5', NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (46, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (47, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (48, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


--
-- Data for Name: ad_datatype_value7; Type: TABLE DATA; 
--

INSERT INTO ad_datatype_value7 (ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (34, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype_value7 (ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (35, NULL, NULL, NULL, 'DAL_CODE1', 'DAL_VALUE1', 'DAL_CODESYSTEM1', 'CTY_VALUE1', 'CTY_CODE1', 'CTY_CODESYSTEM1');
INSERT INTO ad_datatype_value7 (ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (36, 'AL_VALUE2', 'AL_CODE2', 'AL_CODESYSTEM2', 'DAL_CODE2', 'DAL_VALUE2', 'DAL_CODESYSTEM2', NULL, NULL, NULL);
INSERT INTO ad_datatype_value7 (ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (37, 'AL_VALUE3', 'AL_CODE3', 'AL_CODESYSTEM3', 'DAL_CODE3', 'DAL_VALUE3', 'DAL_CODESYSTEM3', 'CTY_VALUE3', 'CTY_CODE3', 'CTY_CODESYSTEM3');
INSERT INTO ad_datatype_value7 (ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (38, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');


--
-- Data for Name: ad_datatype_value8; Type: TABLE DATA; 
--

INSERT INTO ad_datatype_value8 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (1, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype_value8 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (2, NULL, NULL, NULL, 'DAL_CODE1', 'DAL_VALUE1', 'DAL_CODESYSTEM1', 'CTY_VALUE1', 'CTY_CODE1', 'CTY_CODESYSTEM1');
INSERT INTO ad_datatype_value8 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (3, 'AL_VALUE2', 'AL_CODE2', 'AL_CODESYSTEM2', NULL, NULL, NULL, 'CTY_VALUE2', 'CTY_CODE2', 'CTY_CODESYSTEM2');
INSERT INTO ad_datatype_value8 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (4, 'AL_VALUE3', 'AL_CODE3', 'AL_CODESYSTEM3', 'DAL_CODE3', 'DAL_VALUE3', 'DAL_CODESYSTEM3', NULL, NULL, NULL);
INSERT INTO ad_datatype_value8 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (5, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');


--
-- Data for Name: ad_datatype_value9; Type: TABLE DATA; 
--

INSERT INTO ad_datatype_value9 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (1, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype_value9 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (2, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');


--
-- Data for Name: bl_datatype; Type: TABLE DATA; 
--

INSERT INTO bl_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO bl_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, '1', NULL, NULL);
INSERT INTO bl_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, NULL, NULL, '1');
INSERT INTO bl_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, NULL, 'INV', NULL);


--
-- Data for Name: bl_nonnull_datatype; Type: TABLE DATA; 
--

INSERT INTO bl_nonnull_datatype (id, value1_value) VALUES (1, '0');
INSERT INTO bl_nonnull_datatype (id, value1_value) VALUES (2, '1');


--
-- Data for Name: cd_cd_datatype_value8; Type: TABLE DATA; 
--

INSERT INTO cd_cd_datatype_value8 (cd_data_type_id, cd_datatype_value8_id) VALUES (40, 1);
INSERT INTO cd_cd_datatype_value8 (cd_data_type_id, cd_datatype_value8_id) VALUES (41, 1);
INSERT INTO cd_cd_datatype_value8 (cd_data_type_id, cd_datatype_value8_id) VALUES (42, 1);
INSERT INTO cd_cd_datatype_value8 (cd_data_type_id, cd_datatype_value8_id) VALUES (43, 1);
INSERT INTO cd_cd_datatype_value8 (cd_data_type_id, cd_datatype_value8_id) VALUES (44, 1);


--
-- Data for Name: cd_datatype; Type: TABLE DATA; 
--

INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (1, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (2, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (3, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (4, 'CODE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (5, 'CODE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (6, NULL, NULL, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (7, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (8, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (9, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (10, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (11, NULL, NULL, NULL, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (12, NULL, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (13, NULL, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (14, NULL, NULL, NULL, 'CODE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (15, NULL, NULL, NULL, 'CODE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (16, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (17, NULL, NULL, NULL, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE1', 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (18, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE2', NULL, NULL, 'VALUE4_ORIG_TXT_DESCRIPTION1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (19, NULL, NULL, NULL, NULL, NULL, 'CODE4', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE2', NULL, 'VALUE4_ORIG_TXT_VALUE1', 'VALUE4_ORIG_TXT_DESCRIPTION2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (20, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE4_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE3', NULL, 'VALUE4_ORIG_TXT_VALUE2', 'VALUE4_ORIG_TXT_DESCRIPTION3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (21, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE4_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE3', 'VALUE4_ORIG_TXT_DESCRIPTION4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (22, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE4_CODE_SYSTEM', 'VALUE4_CODE_SYSTEM_VERSION1', NULL, NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE4', 'VALUE4_ORIG_TXT_DESCRIPTION5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (23, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE4_CODE_SYSTEM', 'VALUE4_CODE_SYSTEM_VERSION1', 'VALUE4_CODE_SYSTEM_NAME', NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE5', 'VALUE4_ORIG_TXT_DESCRIPTION6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE1', 'VALUE5_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE2', 'VALUE5_CODE_SYSTEM2', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE1', NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, 'VALUE5_CODE_SYSTEM_NAME1', NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, NULL, 'VALUE5_ORIG_TXT_DESCRIPTION1', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE4', 'VALUE5_CODE_SYSTEM3', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, 'VALUE5_ORIG_TXT_VALUE1', 'VALUE5_ORIG_TXT_DESCRIPTION2', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME2', NULL, NULL, 'VALUE5_DISPLAY_VALUE3', NULL, 'VALUE5_ORIG_TXT_VALUE2', 'VALUE5_ORIG_TXT_DESCRIPTION3', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE5_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE3', 'VALUE5_ORIG_TXT_DESCRIPTION4', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE5_CODE_SYSTEM', NULL, 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION5', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME', 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION6', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE1', 'VALUE5_CODE_SYSTEM1', NULL, NULL, 'NI', NULL, 'NI', NULL, NULL, 1);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE2', 'VALUE5_CODE_SYSTEM2', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE1', 'NI', NULL, NULL, 2);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, 'VALUE5_CODE_SYSTEM_NAME1', NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, NULL, 'VALUE5_ORIG_TXT_DESCRIPTION1', 3);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE4', 'VALUE5_CODE_SYSTEM3', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, 'VALUE5_ORIG_TXT_VALUE1', 'VALUE5_ORIG_TXT_DESCRIPTION2', 4);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME2', NULL, NULL, 'VALUE5_DISPLAY_VALUE3', NULL, 'VALUE5_ORIG_TXT_VALUE2', 'VALUE5_ORIG_TXT_DESCRIPTION3', 5);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE5_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE3', 'VALUE5_ORIG_TXT_DESCRIPTION4', 6);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE5_CODE_SYSTEM', NULL, 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION5', 7);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME', 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION6', 8);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (45, NULL, NULL, 'CODE61', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (46, NULL, NULL, 'CODE62', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (47, NULL, NULL, 'CODE63', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (48, NULL, NULL, 'CODE64', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (49, NULL, NULL, 'CODE65', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (50, NULL, NULL, 'CODE66', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


--
-- Data for Name: cd_datatype_value6; Type: TABLE DATA; 
--

INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (45, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE1', 'NI', NULL, NULL);
INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (46, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE2', NULL, 'ORIG_TXT_VALUE1', NULL);
INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (47, NULL, 'CODE3', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE3', NULL, 'ORIG_TXT_VALUE2', 'ORIG_TXT_DESCRIPTION1');
INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (48, NULL, 'CODE4', 'CODE_SYSTEM_61', NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE4', NULL, 'ORIG_TXT_VALUE3', 'ORIG_TXT_DESCRIPTION2');
INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (49, NULL, 'CODE5', 'CODE_SYSTEM_62', 'CODE_SYSTEM_NAME_61', NULL, NULL, 'VALUE6_DISPLAY_VALUE5', NULL, 'ORIG_TXT_VALUE4', 'ORIG_TXT_DESCRIPTION3');
INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (50, NULL, 'CODE1', 'CODE_SYSTEM_63', 'CODE_SYSTEM_NAME_62', 'CODE_SYSTEM_VERSION_61', NULL, 'VALUE6_DISPLAY_VALUE6', NULL, NULL, NULL);


--
-- TOC entry 2233 (class 0 OID 18521)
-- Dependencies: 1659
-- Data for Name: cd_datatype_value7; Type: TABLE DATA; 
--

INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (1, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE1', NULL, 'ORIG_TXT_VALUE1', NULL);
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (2, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE2', NULL, 'ORIG_TXT_VALUE2', 'ORIG_TXT_DESCRIPTION1');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (3, NULL, 'CODE3', 'CODE_SYSTEM_1', NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE3', NULL, 'ORIG_TXT_VALUE3', 'ORIG_TXT_DESCRIPTION2');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (4, NULL, 'CODE4', 'CODE_SYSTEM_2', 'CODE_SYSTEM_NAME_1', NULL, NULL, 'VALUE7_DISPLAY_VALUE4', NULL, 'ORIG_TXT_VALUE4', 'ORIG_TXT_DESCRIPTION3');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (5, NULL, 'CODE5', 'CODE_SYSTEM_3', 'CODE_SYSTEM_NAME_2', 'CODE_SYSTEM_VERSION_1', NULL, 'VALUE7_DISPLAY_VALUE5', NULL, 'ORIG_TXT_VALUE5', 'ORIG_TXT_DESCRIPTION4');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (6, NULL, 'CODE6', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE6', NULL, NULL, 'ORIG_TXT_DESCRIPTION5');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (7, NULL, 'CODE7', NULL, NULL, NULL, 'NI', NULL, NULL, 'ORIG_TXT_VALUE7', 'ORIG_TXT_DESCRIPTION6');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (8, NULL, 'CODE8', 'CODE_SYSTEM_4', NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE8', NULL, 'ORIG_TXT_VALUE8', 'ORIG_TXT_DESCRIPTION7');


--
-- TOC entry 2234 (class 0 OID 18527)
-- Dependencies: 1660
-- Data for Name: cd_datatype_value8; Type: TABLE DATA; 
--

INSERT INTO cd_datatype_value8 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (1, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE8_DISPLAY_VALUE1', 'NI', NULL, NULL);


--
-- TOC entry 2235 (class 0 OID 18533)
-- Dependencies: 1661
-- Data for Name: dset_ad_datatype; Type: TABLE DATA; 
--

INSERT INTO dset_ad_datatype (id) VALUES (1);
INSERT INTO dset_ad_datatype (id) VALUES (2);
INSERT INTO dset_ad_datatype (id) VALUES (3);
INSERT INTO dset_ad_datatype (id) VALUES (4);
INSERT INTO dset_ad_datatype (id) VALUES (5);
INSERT INTO dset_ad_datatype (id) VALUES (6);
INSERT INTO dset_ad_datatype (id) VALUES (7);
INSERT INTO dset_ad_datatype (id) VALUES (8);
INSERT INTO dset_ad_datatype (id) VALUES (9);
INSERT INTO dset_ad_datatype (id) VALUES (10);
INSERT INTO dset_ad_datatype (id) VALUES (11);
INSERT INTO dset_ad_datatype (id) VALUES (12);
INSERT INTO dset_ad_datatype (id) VALUES (13);
INSERT INTO dset_ad_datatype (id) VALUES (14);
INSERT INTO dset_ad_datatype (id) VALUES (15);
INSERT INTO dset_ad_datatype (id) VALUES (16);
INSERT INTO dset_ad_datatype (id) VALUES (17);
INSERT INTO dset_ad_datatype (id) VALUES (18);
INSERT INTO dset_ad_datatype (id) VALUES (19);
INSERT INTO dset_ad_datatype (id) VALUES (20);
INSERT INTO dset_ad_datatype (id) VALUES (21);
INSERT INTO dset_ad_datatype (id) VALUES (22);
INSERT INTO dset_ad_datatype (id) VALUES (23);
INSERT INTO dset_ad_datatype (id) VALUES (24);
INSERT INTO dset_ad_datatype (id) VALUES (25);
INSERT INTO dset_ad_datatype (id) VALUES (26);
INSERT INTO dset_ad_datatype (id) VALUES (27);
INSERT INTO dset_ad_datatype (id) VALUES (28);
INSERT INTO dset_ad_datatype (id) VALUES (29);
INSERT INTO dset_ad_datatype (id) VALUES (30);
INSERT INTO dset_ad_datatype (id) VALUES (31);
INSERT INTO dset_ad_datatype (id) VALUES (32);
INSERT INTO dset_ad_datatype (id) VALUES (33);
INSERT INTO dset_ad_datatype (id) VALUES (34);
INSERT INTO dset_ad_datatype (id) VALUES (35);
INSERT INTO dset_ad_datatype (id) VALUES (36);
INSERT INTO dset_ad_datatype (id) VALUES (37);
INSERT INTO dset_ad_datatype (id) VALUES (38);
INSERT INTO dset_ad_datatype (id) VALUES (39);
INSERT INTO dset_ad_datatype (id) VALUES (40);
INSERT INTO dset_ad_datatype (id) VALUES (41);
INSERT INTO dset_ad_datatype (id) VALUES (42);
INSERT INTO dset_ad_datatype (id) VALUES (43);
INSERT INTO dset_ad_datatype (id) VALUES (44);
INSERT INTO dset_ad_datatype (id) VALUES (45);
INSERT INTO dset_ad_datatype (id) VALUES (46);
INSERT INTO dset_ad_datatype (id) VALUES (47);
INSERT INTO dset_ad_datatype (id) VALUES (48);
INSERT INTO dset_ad_datatype (id) VALUES (49);
INSERT INTO dset_ad_datatype (id) VALUES (50);
INSERT INTO dset_ad_datatype (id) VALUES (51);
INSERT INTO dset_ad_datatype (id) VALUES (52);
INSERT INTO dset_ad_datatype (id) VALUES (53);
INSERT INTO dset_ad_datatype (id) VALUES (54);
INSERT INTO dset_ad_datatype (id) VALUES (55);
INSERT INTO dset_ad_datatype (id) VALUES (56);
INSERT INTO dset_ad_datatype (id) VALUES (57);
INSERT INTO dset_ad_datatype (id) VALUES (58);
INSERT INTO dset_ad_datatype (id) VALUES (59);
INSERT INTO dset_ad_datatype (id) VALUES (60);
INSERT INTO dset_ad_datatype (id) VALUES (61);
INSERT INTO dset_ad_datatype (id) VALUES (62);


--
-- Data for Name: dset_ad_dset_ad_value8; Type: TABLE DATA; 
--

INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (59, 1);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (60, 1);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (61, 1);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (62, 7);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (62, 8);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (62, 9);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (62, 10);


--
-- Data for Name: dset_ad_value1; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (1, NULL);
INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (2, '1 Jefferson Street');
INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (3, '2 Jefferson Street');
INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (4, '3 Jefferson Street');
INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (5, '4 Sun Street');
INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (6, '5 Sun Street');


--
-- Data for Name: dset_ad_value2; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value2 (dset_ad_datatype_id, adxp_dal_value, adxp_dal_code) VALUES (7, 'Suite 100', NULL);
INSERT INTO dset_ad_value2 (dset_ad_datatype_id, adxp_dal_value, adxp_dal_code) VALUES (8, 'Suite 101', NULL);
INSERT INTO dset_ad_value2 (dset_ad_datatype_id, adxp_dal_value, adxp_dal_code) VALUES (9, NULL, 'CODE1');
INSERT INTO dset_ad_value2 (dset_ad_datatype_id, adxp_dal_value, adxp_dal_code) VALUES (10, 'Suite 103', 'CODE2');
INSERT INTO dset_ad_value2 (dset_ad_datatype_id, adxp_dal_value, adxp_dal_code) VALUES (11, 'Suite 104', 'CODE3');


--
-- Data for Name: dset_ad_value3; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value3 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem) VALUES (12, '1 Jefferson Street', NULL, NULL);
INSERT INTO dset_ad_value3 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem) VALUES (13, '2 Jefferson Street', 'CODE1', NULL);
INSERT INTO dset_ad_value3 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem) VALUES (14, NULL, 'CODE2', NULL);
INSERT INTO dset_ad_value3 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem) VALUES (15, '3 Jefferson Street', 'CODE3', NULL);
INSERT INTO dset_ad_value3 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem) VALUES (16, '4 Jefferson Street', 'CODE4', 'CODE_SYSTEM');


--
-- Data for Name: dset_ad_value4; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (17, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (18, '101 Jefferson Street', NULL, 'NCI101', NULL, NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (19, '102 Jefferson Street', NULL, 'NCI102', NULL, NULL, 'ADXP_AL1_CODESYSTEM1');
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (20, NULL, '200 Executive Blvd', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (21, NULL, '201 Executive Blvd', NULL, 'NCI201', NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (22, NULL, '202 Executive Blvd', NULL, 'NCI202', 'ADXP_AL2_CODESYSTEM1', NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (23, '100 Jefferson Street', '200 Executive Blvd', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (24, '101 Jefferson Street', '201 Executive Blvd', 'NCI101', 'NCI201', NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (25, '102 Jefferson Street', '202 Executive Blvd', 'NCI102', 'NCI202', 'ADXP_AL2_CODESYSTEM1', 'ADXP_AL1_CODESYSTEM1');


--
-- Data for Name: dset_ad_value5; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (26, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (27, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (28, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (29, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL100', NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (30, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL101', 'ADXP_DAL_CODESYSTEM1', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (31, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM2', 'Suite 500', NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (32, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM3', 'Suite 501', 'Rockville', NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (33, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM4', 'Suite 502', 'Rockville', 'RCK', NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (34, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (35, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');


--
-- Data for Name: dset_ad_value6; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (36, 'ADXP_ADL_VALUE1', 'ADXP_AL_VALUE1', 'ADXP_BNN_VALUE1', 'ADXP_BNR_VALUE1', 'ADXP_BNS_VALUE1', 'ADXP_CAR_VALUE1', 'ADXP_CEN_VALUE1', 'ADXP_CNT_VALUE1', 'ADXP_CPA_VALUE1', 'ADXP_CTY_VALUE1', 'ADXP_DAL_VALUE1', 'ADXP_DEL_VALUE1', 'ADXP_DINSTA_VALUE1', 'ADXP_DINSTQ_VALUE1', 'ADXP_DIR_VALUE1', 'ADXP_DMOD_VALUE1', 'ADXP_DMODID_VALUE1', 'ADXP_INT_VALUE1', 'ADXP_POB_VALUE1', 'ADXP_PRE_VALUE1', 'ADXP_SAL_VALUE1', 'ADXP_STA_VALUE1', 'ADXP_STB_VALUE1', 'ADXP_STR_VALUE1', 'ADXP_STTYP_VALUE1', 'ADXP_UNID_VALUE1', 'ADXP_UNIT_VALUE1', 'ADXP_ZIP_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (37, 'ADXP_ADL_VALUE2', 'ADXP_AL_VALUE2', 'ADXP_BNN_VALUE2', 'ADXP_BNR_VALUE2', 'ADXP_BNS_VALUE2', 'ADXP_CAR_VALUE2', 'ADXP_CEN_VALUE2', 'ADXP_CNT_VALUE2', 'ADXP_CPA_VALUE2', 'ADXP_CTY_VALUE2', 'ADXP_DAL_VALUE2', 'ADXP_DEL_VALUE2', 'ADXP_DINSTA_VALUE2', 'ADXP_DINSTQ_VALUE2', 'ADXP_DIR_VALUE2', 'ADXP_DMOD_VALUE2', 'ADXP_DMODID_VALUE2', 'ADXP_INT_VALUE2', 'ADXP_POB_VALUE2', 'ADXP_PRE_VALUE2', 'ADXP_SAL_VALUE2', 'ADXP_STA_VALUE2', 'ADXP_STB_VALUE2', 'ADXP_STR_VALUE2', 'ADXP_STTYP_VALUE2', 'ADXP_UNID_VALUE2', 'ADXP_UNIT_VALUE2', 'ADXP_ZIP_VALUE2', 'ADXP_ADL_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (38, 'ADXP_ADL_VALUE3', 'ADXP_AL_VALUE3', 'ADXP_BNN_VALUE3', 'ADXP_BNR_VALUE3', 'ADXP_BNS_VALUE3', 'ADXP_CAR_VALUE3', 'ADXP_CEN_VALUE3', 'ADXP_CNT_VALUE3', 'ADXP_CPA_VALUE3', 'ADXP_CTY_VALUE3', 'ADXP_DAL_VALUE3', 'ADXP_DEL_VALUE3', 'ADXP_DINSTA_VALUE3', 'ADXP_DINSTQ_VALUE3', 'ADXP_DIR_VALUE3', 'ADXP_DMOD_VALUE3', 'ADXP_DMODID_VALUE3', 'ADXP_INT_VALUE3', 'ADXP_POB_VALUE3', 'ADXP_PRE_VALUE3', 'ADXP_SAL_VALUE3', 'ADXP_STA_VALUE3', 'ADXP_STB_VALUE3', 'ADXP_STR_VALUE3', 'ADXP_STTYP_VALUE3', 'ADXP_UNID_VALUE3', 'ADXP_UNIT_VALUE3', 'ADXP_ZIP_VALUE3', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (39, 'ADXP_ADL_VALUE4', 'ADXP_AL_VALUE4', 'ADXP_BNN_VALUE4', 'ADXP_BNR_VALUE4', 'ADXP_BNS_VALUE4', 'ADXP_CAR_VALUE4', 'ADXP_CEN_VALUE4', 'ADXP_CNT_VALUE4', 'ADXP_CPA_VALUE4', 'ADXP_CTY_VALUE4', 'ADXP_DAL_VALUE4', 'ADXP_DEL_VALUE4', 'ADXP_DINSTA_VALUE4', 'ADXP_DINSTQ_VALUE4', 'ADXP_DIR_VALUE4', 'ADXP_DMOD_VALUE4', 'ADXP_DMODID_VALUE4', 'ADXP_INT_VALUE4', 'ADXP_POB_VALUE4', 'ADXP_PRE_VALUE4', 'ADXP_SAL_VALUE4', 'ADXP_STA_VALUE4', 'ADXP_STB_VALUE4', 'ADXP_STR_VALUE4', 'ADXP_STTYP_VALUE4', 'ADXP_UNID_VALUE4', 'ADXP_UNIT_VALUE4', 'ADXP_ZIP_VALUE4', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (40, 'ADXP_ADL_VALUE5', 'ADXP_AL_VALUE5', 'ADXP_BNN_VALUE5', 'ADXP_BNR_VALUE5', 'ADXP_BNS_VALUE5', 'ADXP_CAR_VALUE5', 'ADXP_CEN_VALUE5', 'ADXP_CNT_VALUE5', 'ADXP_CPA_VALUE5', 'ADXP_CTY_VALUE5', 'ADXP_DAL_VALUE5', 'ADXP_DEL_VALUE5', 'ADXP_DINSTA_VALUE5', 'ADXP_DINSTQ_VALUE5', 'ADXP_DIR_VALUE5', 'ADXP_DMOD_VALUE5', 'ADXP_DMODID_VALUE5', 'ADXP_INT_VALUE5', 'ADXP_POB_VALUE5', 'ADXP_PRE_VALUE5', 'ADXP_SAL_VALUE5', 'ADXP_STA_VALUE5', 'ADXP_STB_VALUE5', 'ADXP_STR_VALUE5', 'ADXP_STTYP_VALUE5', 'ADXP_UNID_VALUE5', 'ADXP_UNIT_VALUE5', 'ADXP_ZIP_VALUE5', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (41, 'ADXP_ADL_VALUE6', 'ADXP_AL_VALUE6', 'ADXP_BNN_VALUE6', 'ADXP_BNR_VALUE6', 'ADXP_BNS_VALUE6', 'ADXP_CAR_VALUE6', 'ADXP_CEN_VALUE6', 'ADXP_CNT_VALUE6', 'ADXP_CPA_VALUE6', 'ADXP_CTY_VALUE6', 'ADXP_DAL_VALUE6', 'ADXP_DEL_VALUE6', 'ADXP_DINSTA_VALUE6', 'ADXP_DINSTQ_VALUE6', 'ADXP_DIR_VALUE6', 'ADXP_DMOD_VALUE6', 'ADXP_DMODID_VALUE6', 'ADXP_INT_VALUE6', 'ADXP_POB_VALUE6', 'ADXP_PRE_VALUE6', 'ADXP_SAL_VALUE6', 'ADXP_STA_VALUE6', 'ADXP_STB_VALUE6', 'ADXP_STR_VALUE6', 'ADXP_STTYP_VALUE6', 'ADXP_UNID_VALUE6', 'ADXP_UNIT_VALUE6', 'ADXP_ZIP_VALUE6', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (42, 'ADXP_ADL_VALUE7', 'ADXP_AL_VALUE7', 'ADXP_BNN_VALUE7', 'ADXP_BNR_VALUE7', 'ADXP_BNS_VALUE7', 'ADXP_CAR_VALUE7', 'ADXP_CEN_VALUE7', 'ADXP_CNT_VALUE7', 'ADXP_CPA_VALUE7', 'ADXP_CTY_VALUE7', 'ADXP_DAL_VALUE7', 'ADXP_DEL_VALUE7', 'ADXP_DINSTA_VALUE7', 'ADXP_DINSTQ_VALUE7', 'ADXP_DIR_VALUE7', 'ADXP_DMOD_VALUE7', 'ADXP_DMODID_VALUE7', 'ADXP_INT_VALUE7', 'ADXP_POB_VALUE7', 'ADXP_PRE_VALUE7', 'ADXP_SAL_VALUE7', 'ADXP_STA_VALUE7', 'ADXP_STB_VALUE7', 'ADXP_STR_VALUE7', 'ADXP_STTYP_VALUE7', 'ADXP_UNID_VALUE7', 'ADXP_UNIT_VALUE7', 'ADXP_ZIP_VALUE7', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (43, 'ADXP_ADL_VALUE8', 'ADXP_AL_VALUE8', 'ADXP_BNN_VALUE8', 'ADXP_BNR_VALUE8', 'ADXP_BNS_VALUE8', 'ADXP_CAR_VALUE8', 'ADXP_CEN_VALUE8', 'ADXP_CNT_VALUE8', 'ADXP_CPA_VALUE8', 'ADXP_CTY_VALUE8', 'ADXP_DAL_VALUE8', 'ADXP_DEL_VALUE8', 'ADXP_DINSTA_VALUE8', 'ADXP_DINSTQ_VALUE8', 'ADXP_DIR_VALUE8', 'ADXP_DMOD_VALUE8', 'ADXP_DMODID_VALUE8', 'ADXP_INT_VALUE8', 'ADXP_POB_VALUE8', 'ADXP_PRE_VALUE8', 'ADXP_SAL_VALUE8', 'ADXP_STA_VALUE8', 'ADXP_STB_VALUE8', 'ADXP_STR_VALUE8', 'ADXP_STTYP_VALUE8', 'ADXP_UNID_VALUE8', 'ADXP_UNIT_VALUE8', 'ADXP_ZIP_VALUE8', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (44, 'ADXP_ADL_VALUE9', 'ADXP_AL_VALUE9', 'ADXP_BNN_VALUE9', 'ADXP_BNR_VALUE9', 'ADXP_BNS_VALUE9', 'ADXP_CAR_VALUE9', 'ADXP_CEN_VALUE9', 'ADXP_CNT_VALUE9', 'ADXP_CPA_VALUE9', 'ADXP_CTY_VALUE9', 'ADXP_DAL_VALUE9', 'ADXP_DEL_VALUE9', 'ADXP_DINSTA_VALUE9', 'ADXP_DINSTQ_VALUE9', 'ADXP_DIR_VALUE9', 'ADXP_DMOD_VALUE9', 'ADXP_DMODID_VALUE9', 'ADXP_INT_VALUE9', 'ADXP_POB_VALUE9', 'ADXP_PRE_VALUE9', 'ADXP_SAL_VALUE9', 'ADXP_STA_VALUE9', 'ADXP_STB_VALUE9', 'ADXP_STR_VALUE9', 'ADXP_STTYP_VALUE9', 'ADXP_UNID_VALUE9', 'ADXP_UNIT_VALUE9', 'ADXP_ZIP_VALUE9', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (45, 'ADXP_ADL_VALUE10', 'ADXP_AL_VALUE10', 'ADXP_BNN_VALUE10', 'ADXP_BNR_VALUE10', 'ADXP_BNS_VALUE10', 'ADXP_CAR_VALUE10', 'ADXP_CEN_VALUE10', 'ADXP_CNT_VALUE10', 'ADXP_CPA_VALUE10', 'ADXP_CTY_VALUE10', 'ADXP_DAL_VALUE10', 'ADXP_DEL_VALUE10', 'ADXP_DINSTA_VALUE10', 'ADXP_DINSTQ_VALUE10', 'ADXP_DIR_VALUE10', 'ADXP_DMOD_VALUE10', 'ADXP_DMODID_VALUE10', 'ADXP_INT_VALUE10', 'ADXP_POB_VALUE10', 'ADXP_PRE_VALUE10', 'ADXP_SAL_VALUE10', 'ADXP_STA_VALUE10', 'ADXP_STB_VALUE10', 'ADXP_STR_VALUE10', 'ADXP_STTYP_VALUE10', 'ADXP_UNID_VALUE10', 'ADXP_UNIT_VALUE10', 'ADXP_ZIP_VALUE10', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (46, 'ADXP_ADL_VALUE11', 'ADXP_AL_VALUE11', 'ADXP_BNN_VALUE11', 'ADXP_BNR_VALUE11', 'ADXP_BNS_VALUE11', 'ADXP_CAR_VALUE11', 'ADXP_CEN_VALUE11', 'ADXP_CNT_VALUE11', 'ADXP_CPA_VALUE11', 'ADXP_CTY_VALUE11', 'ADXP_DAL_VALUE11', 'ADXP_DEL_VALUE11', 'ADXP_DINSTA_VALUE11', 'ADXP_DINSTQ_VALUE11', 'ADXP_DIR_VALUE11', 'ADXP_DMOD_VALUE11', 'ADXP_DMODID_VALUE11', 'ADXP_INT_VALUE11', 'ADXP_POB_VALUE11', 'ADXP_PRE_VALUE11', 'ADXP_SAL_VALUE11', 'ADXP_STA_VALUE11', 'ADXP_STB_VALUE11', 'ADXP_STR_VALUE11', 'ADXP_STTYP_VALUE11', 'ADXP_UNID_VALUE11', 'ADXP_UNIT_VALUE11', 'ADXP_ZIP_VALUE11', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', 'ADXP_ZIP_CODESYSTEM', NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (47, 'ADXP_ADL_VALUE12', 'ADXP_AL_VALUE12', 'ADXP_BNN_VALUE12', 'ADXP_BNR_VALUE12', 'ADXP_BNS_VALUE12', 'ADXP_CAR_VALUE12', 'ADXP_CEN_VALUE12', 'ADXP_CNT_VALUE12', 'ADXP_CPA_VALUE12', 'ADXP_CTY_VALUE12', 'ADXP_DAL_VALUE12', 'ADXP_DEL_VALUE12', 'ADXP_DINSTA_VALUE12', 'ADXP_DINSTQ_VALUE12', 'ADXP_DIR_VALUE12', 'ADXP_DMOD_VALUE12', 'ADXP_DMODID_VALUE12', 'ADXP_INT_VALUE12', 'ADXP_POB_VALUE12', 'ADXP_PRE_VALUE12', 'ADXP_SAL_VALUE12', 'ADXP_STA_VALUE12', 'ADXP_STB_VALUE12', 'ADXP_STR_VALUE12', 'ADXP_STTYP_VALUE12', 'ADXP_UNID_VALUE12', 'ADXP_UNIT_VALUE12', 'ADXP_ZIP_VALUE12', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', 'ADXP_ZIP_CODESYSTEM', 'ADXP_ZIP_CODE');


--
-- TOC entry 2243 (class 0 OID 18560)
-- Dependencies: 1669
-- Data for Name: dset_ad_value7; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (1, 49, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (2, 50, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (3, 51, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (4, 52, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL101', NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (5, 53, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM5', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (6, 54, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM5', 'Suite 501', NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (7, 55, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM5', 'Suite 502', 'Rockville', NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (8, 56, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (9, 57, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL106', 'ADXP_DAL_CODESYSTEM5', 'Suite 504', 'Rockville', 'RCK', 'RCK_CODE_SYS');
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (10, 58, '109 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM8', 'NCIDAL107', 'ADXP_DAL_CODESYSTEM5', 'Suite 505', 'Rockville', 'RCK', 'RCK_CODE_SYS');


--
-- TOC entry 2244 (class 0 OID 18563)
-- Dependencies: 1670
-- Data for Name: dset_ad_value8; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (1, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (2, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (3, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (4, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL100', NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (5, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL101', 'ADXP_DAL_CODESYSTEM1', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (6, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM2', 'Suite 500', NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (7, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM3', 'Suite 501', 'Rockville', NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (8, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM4', 'Suite 502', 'Rockville', 'RCK', NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (9, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (10, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');


--
-- TOC entry 2245 (class 0 OID 18566)
-- Dependencies: 1671
-- Data for Name: dset_cd; Type: TABLE DATA; 
--

INSERT INTO dset_cd (id) VALUES (1);
INSERT INTO dset_cd (id) VALUES (2);
INSERT INTO dset_cd (id) VALUES (3);
INSERT INTO dset_cd (id) VALUES (4);
INSERT INTO dset_cd (id) VALUES (5);
INSERT INTO dset_cd (id) VALUES (6);
INSERT INTO dset_cd (id) VALUES (7);
INSERT INTO dset_cd (id) VALUES (8);
INSERT INTO dset_cd (id) VALUES (9);
INSERT INTO dset_cd (id) VALUES (10);
INSERT INTO dset_cd (id) VALUES (11);
INSERT INTO dset_cd (id) VALUES (12);
INSERT INTO dset_cd (id) VALUES (13);
INSERT INTO dset_cd (id) VALUES (14);
INSERT INTO dset_cd (id) VALUES (15);
INSERT INTO dset_cd (id) VALUES (16);
INSERT INTO dset_cd (id) VALUES (17);
INSERT INTO dset_cd (id) VALUES (18);
INSERT INTO dset_cd (id) VALUES (19);
INSERT INTO dset_cd (id) VALUES (20);
INSERT INTO dset_cd (id) VALUES (21);
INSERT INTO dset_cd (id) VALUES (22);
INSERT INTO dset_cd (id) VALUES (23);
INSERT INTO dset_cd (id) VALUES (24);
INSERT INTO dset_cd (id) VALUES (25);
INSERT INTO dset_cd (id) VALUES (26);
INSERT INTO dset_cd (id) VALUES (27);
INSERT INTO dset_cd (id) VALUES (28);
INSERT INTO dset_cd (id) VALUES (29);
INSERT INTO dset_cd (id) VALUES (30);
INSERT INTO dset_cd (id) VALUES (31);
INSERT INTO dset_cd (id) VALUES (32);
INSERT INTO dset_cd (id) VALUES (33);
INSERT INTO dset_cd (id) VALUES (34);
INSERT INTO dset_cd (id) VALUES (35);


--
-- TOC entry 2246 (class 0 OID 18569)
-- Dependencies: 1672
-- Data for Name: dset_cd_cd_value7; Type: TABLE DATA; 
--

INSERT INTO dset_cd_cd_value7 (dset_cd_id, dset_cd_value7_id) VALUES (31, 1);
INSERT INTO dset_cd_cd_value7 (dset_cd_id, dset_cd_value7_id) VALUES (32, 2);
INSERT INTO dset_cd_cd_value7 (dset_cd_id, dset_cd_value7_id) VALUES (33, 3);
INSERT INTO dset_cd_cd_value7 (dset_cd_id, dset_cd_value7_id) VALUES (34, 4);
INSERT INTO dset_cd_cd_value7 (dset_cd_id, dset_cd_value7_id) VALUES (35, 5);


--
-- TOC entry 2247 (class 0 OID 18572)
-- Dependencies: 1673
-- Data for Name: dset_cd_value1; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value1 (dset_cd_id, code) VALUES (1, 'CODE1');
INSERT INTO dset_cd_value1 (dset_cd_id, code) VALUES (2, 'CODE2');
INSERT INTO dset_cd_value1 (dset_cd_id, code) VALUES (3, 'CODE3');
INSERT INTO dset_cd_value1 (dset_cd_id, code) VALUES (4, 'CODE4');
INSERT INTO dset_cd_value1 (dset_cd_id, code) VALUES (5, 'CODE5');


--
-- TOC entry 2248 (class 0 OID 18575)
-- Dependencies: 1674
-- Data for Name: dset_cd_value2; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value2 (dset_cd_id, code, null_flavor) VALUES (6, 'CODE1', NULL);
INSERT INTO dset_cd_value2 (dset_cd_id, code, null_flavor) VALUES (7, 'CODE2', NULL);
INSERT INTO dset_cd_value2 (dset_cd_id, code, null_flavor) VALUES (8, 'CODE3', NULL);
INSERT INTO dset_cd_value2 (dset_cd_id, code, null_flavor) VALUES (9, NULL, 'NI');
INSERT INTO dset_cd_value2 (dset_cd_id, code, null_flavor) VALUES (10, NULL, 'NI');


--
-- TOC entry 2249 (class 0 OID 18578)
-- Dependencies: 1675
-- Data for Name: dset_cd_value3; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value3 (dset_cd_id, code, code_system, code_system_name) VALUES (11, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1');
INSERT INTO dset_cd_value3 (dset_cd_id, code, code_system, code_system_name) VALUES (12, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2');
INSERT INTO dset_cd_value3 (dset_cd_id, code, code_system, code_system_name) VALUES (13, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3');
INSERT INTO dset_cd_value3 (dset_cd_id, code, code_system, code_system_name) VALUES (14, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4');
INSERT INTO dset_cd_value3 (dset_cd_id, code, code_system, code_system_name) VALUES (15, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5');


--
-- TOC entry 2250 (class 0 OID 18581)
-- Dependencies: 1676
-- Data for Name: dset_cd_value4; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value4 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (16, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1', 'CODE_SYSTEM_VERSION1', 'CODE1', 'OrgTextVal1', 'OrgTextDesc1');
INSERT INTO dset_cd_value4 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (17, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2', 'CODE_SYSTEM_VERSION2', 'CODE2', 'OrgTextVal2', 'OrgTextDesc2');
INSERT INTO dset_cd_value4 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (18, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3', 'CODE_SYSTEM_VERSION3', 'CODE3', 'OrgTextVal3', 'OrgTextDesc3');
INSERT INTO dset_cd_value4 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (19, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4', 'CODE_SYSTEM_VERSION4', 'CODE4', 'OrgTextVal4', 'OrgTextDesc4');
INSERT INTO dset_cd_value4 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (20, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5', 'CODE_SYSTEM_VERSION5', 'CODE5', 'OrgTextVal5', 'OrgTextDesc5');


--
-- TOC entry 2251 (class 0 OID 18584)
-- Dependencies: 1677
-- Data for Name: dset_cd_value5; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value5 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (21, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1', 'CODE_SYSTEM_VERSION1', 'CODE1', 'OrgTextVal1', 'OrgTextDesc1');
INSERT INTO dset_cd_value5 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (22, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2', 'CODE_SYSTEM_VERSION2', 'CODE2', 'OrgTextVal2', 'OrgTextDesc2');
INSERT INTO dset_cd_value5 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (23, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3', 'CODE_SYSTEM_VERSION3', 'CODE3', 'OrgTextVal3', 'OrgTextDesc3');
INSERT INTO dset_cd_value5 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (24, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4', 'CODE_SYSTEM_VERSION4', 'CODE4', 'OrgTextVal4', 'OrgTextDesc4');
INSERT INTO dset_cd_value5 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (25, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5', 'CODE_SYSTEM_VERSION5', 'CODE5', 'OrgTextVal5', 'OrgTextDesc5');


--
-- TOC entry 2252 (class 0 OID 18587)
-- Dependencies: 1678
-- Data for Name: dset_cd_value6; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value6 (id, dset_cd_id, code) VALUES (1, 26, 'CODE1');
INSERT INTO dset_cd_value6 (id, dset_cd_id, code) VALUES (2, 27, 'CODE2');
INSERT INTO dset_cd_value6 (id, dset_cd_id, code) VALUES (3, 28, 'CODE3');
INSERT INTO dset_cd_value6 (id, dset_cd_id, code) VALUES (4, 29, 'CODE4');
INSERT INTO dset_cd_value6 (id, dset_cd_id, code) VALUES (5, 30, 'CODE5');


--
-- TOC entry 2253 (class 0 OID 18590)
-- Dependencies: 1679
-- Data for Name: dset_cd_value7; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value7 (id, code) VALUES (1, 'CODE1');
INSERT INTO dset_cd_value7 (id, code) VALUES (2, 'CODE2');
INSERT INTO dset_cd_value7 (id, code) VALUES (3, 'CODE3');
INSERT INTO dset_cd_value7 (id, code) VALUES (4, 'CODE4');
INSERT INTO dset_cd_value7 (id, code) VALUES (5, 'CODE5');


--
-- TOC entry 2254 (class 0 OID 18593)
-- Dependencies: 1680
-- Data for Name: dset_en; Type: TABLE DATA; 
--



--
-- TOC entry 2255 (class 0 OID 18596)
-- Dependencies: 1681
-- Data for Name: dset_en_value1; Type: TABLE DATA; 
--



--
-- TOC entry 2256 (class 0 OID 18599)
-- Dependencies: 1682
-- Data for Name: dset_en_value2; Type: TABLE DATA; 
--



--
-- TOC entry 2257 (class 0 OID 18602)
-- Dependencies: 1683
-- Data for Name: dset_en_value3; Type: TABLE DATA; 
--



--
-- TOC entry 2258 (class 0 OID 18605)
-- Dependencies: 1684
-- Data for Name: dset_en_value4; Type: TABLE DATA; 
--



--
-- TOC entry 2259 (class 0 OID 18608)
-- Dependencies: 1685
-- Data for Name: dset_en_value5; Type: TABLE DATA; 
--



--
-- TOC entry 2260 (class 0 OID 18611)
-- Dependencies: 1686
-- Data for Name: dset_en_value6; Type: TABLE DATA; 
--



--
-- TOC entry 2261 (class 0 OID 18614)
-- Dependencies: 1687
-- Data for Name: dset_en_value7; Type: TABLE DATA; 
--



--
-- TOC entry 2262 (class 0 OID 18617)
-- Dependencies: 1688
-- Data for Name: dset_ii; Type: TABLE DATA; 
--

INSERT INTO dset_ii (id) VALUES (1);
INSERT INTO dset_ii (id) VALUES (2);
INSERT INTO dset_ii (id) VALUES (3);
INSERT INTO dset_ii (id) VALUES (4);
INSERT INTO dset_ii (id) VALUES (5);
INSERT INTO dset_ii (id) VALUES (6);
INSERT INTO dset_ii (id) VALUES (7);
INSERT INTO dset_ii (id) VALUES (8);
INSERT INTO dset_ii (id) VALUES (9);
INSERT INTO dset_ii (id) VALUES (10);
INSERT INTO dset_ii (id) VALUES (11);
INSERT INTO dset_ii (id) VALUES (12);
INSERT INTO dset_ii (id) VALUES (13);
INSERT INTO dset_ii (id) VALUES (14);
INSERT INTO dset_ii (id) VALUES (15);
INSERT INTO dset_ii (id) VALUES (16);
INSERT INTO dset_ii (id) VALUES (17);
INSERT INTO dset_ii (id) VALUES (18);
INSERT INTO dset_ii (id) VALUES (19);
INSERT INTO dset_ii (id) VALUES (20);
INSERT INTO dset_ii (id) VALUES (21);
INSERT INTO dset_ii (id) VALUES (22);
INSERT INTO dset_ii (id) VALUES (23);
INSERT INTO dset_ii (id) VALUES (24);
INSERT INTO dset_ii (id) VALUES (25);
INSERT INTO dset_ii (id) VALUES (26);
INSERT INTO dset_ii (id) VALUES (27);
INSERT INTO dset_ii (id) VALUES (28);
INSERT INTO dset_ii (id) VALUES (29);


--
-- TOC entry 2263 (class 0 OID 18620)
-- Dependencies: 1689
-- Data for Name: dset_ii_ii_value6; Type: TABLE DATA; 
--

INSERT INTO dset_ii_ii_value6 (dset_ii_id, dset_ii_value6_id) VALUES (26, 1);
INSERT INTO dset_ii_ii_value6 (dset_ii_id, dset_ii_value6_id) VALUES (27, 1);
INSERT INTO dset_ii_ii_value6 (dset_ii_id, dset_ii_value6_id) VALUES (28, 1);
INSERT INTO dset_ii_ii_value6 (dset_ii_id, dset_ii_value6_id) VALUES (29, 2);
INSERT INTO dset_ii_ii_value6 (dset_ii_id, dset_ii_value6_id) VALUES (29, 3);


--
-- TOC entry 2264 (class 0 OID 18623)
-- Dependencies: 1690
-- Data for Name: dset_ii_value1; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value1 (dset_ii_id, extension) VALUES (1, 'Extension1');
INSERT INTO dset_ii_value1 (dset_ii_id, extension) VALUES (2, 'Extension2');
INSERT INTO dset_ii_value1 (dset_ii_id, extension) VALUES (3, 'Extension3');
INSERT INTO dset_ii_value1 (dset_ii_id, extension) VALUES (4, 'Extension4');
INSERT INTO dset_ii_value1 (dset_ii_id, extension) VALUES (5, 'Extension5');


--
-- TOC entry 2265 (class 0 OID 18626)
-- Dependencies: 1691
-- Data for Name: dset_ii_value2; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value2 (dset_ii_id, extension, root, null_flavor) VALUES (6, NULL, NULL, 'NI');
INSERT INTO dset_ii_value2 (dset_ii_id, extension, root, null_flavor) VALUES (7, 'Extension2', 'ROOT2', NULL);
INSERT INTO dset_ii_value2 (dset_ii_id, extension, root, null_flavor) VALUES (8, 'Extension3', 'ROOT3', NULL);
INSERT INTO dset_ii_value2 (dset_ii_id, extension, root, null_flavor) VALUES (9, 'Extension4', 'ROOT4', NULL);
INSERT INTO dset_ii_value2 (dset_ii_id, extension, root, null_flavor) VALUES (10, 'Extension5', 'ROOT5', NULL);


--
-- TOC entry 2266 (class 0 OID 18629)
-- Dependencies: 1692
-- Data for Name: dset_ii_value3; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value3 (dset_ii_id, extension, identifier_name, displayable_value, reliability, scope) VALUES (11, 'Extension1', NULL, NULL, NULL, NULL);
INSERT INTO dset_ii_value3 (dset_ii_id, extension, identifier_name, displayable_value, reliability, scope) VALUES (12, NULL, 'IDENTIFIER_NAME2', NULL, NULL, NULL);
INSERT INTO dset_ii_value3 (dset_ii_id, extension, identifier_name, displayable_value, reliability, scope) VALUES (13, 'Extension3', 'IDENTIFIER_NAME3', NULL, NULL, NULL);
INSERT INTO dset_ii_value3 (dset_ii_id, extension, identifier_name, displayable_value, reliability, scope) VALUES (14, 'Extension4', 'IDENTIFIER_NAME4', NULL, NULL, NULL);
INSERT INTO dset_ii_value3 (dset_ii_id, extension, identifier_name, displayable_value, reliability, scope) VALUES (15, 'Extension5', 'IDENTIFIER_NAME5', NULL, NULL, NULL);


--
-- TOC entry 2267 (class 0 OID 18632)
-- Dependencies: 1693
-- Data for Name: dset_ii_value4; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value4 (dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor) VALUES (16, 'Root1', 'Extension1', NULL, NULL, NULL, '1', NULL);
INSERT INTO dset_ii_value4 (dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor) VALUES (17, 'Root2', 'Extension2', 'IDENTIFIER_NAME2', NULL, NULL, '1', NULL);
INSERT INTO dset_ii_value4 (dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor) VALUES (18, 'Root3', NULL, 'IDENTIFIER_NAME3', 'ISS', NULL, '1', NULL);
INSERT INTO dset_ii_value4 (dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor) VALUES (19, 'Root4', 'Extension4', 'IDENTIFIER_NAME4', 'ISS', 'BUSN', '0', NULL);
INSERT INTO dset_ii_value4 (dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor) VALUES (20, 'Root5', 'Extension5', 'IDENTIFIER_NAME5', 'ISS', 'BUSN', '1', NULL);


--
-- TOC entry 2268 (class 0 OID 18635)
-- Dependencies: 1694
-- Data for Name: dset_ii_value5; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value5 (id, dset_ii_id, extension) VALUES (1, 21, 'Extension1');
INSERT INTO dset_ii_value5 (id, dset_ii_id, extension) VALUES (2, 22, 'Extension2');
INSERT INTO dset_ii_value5 (id, dset_ii_id, extension) VALUES (3, 23, 'Extension3');
INSERT INTO dset_ii_value5 (id, dset_ii_id, extension) VALUES (4, 24, 'Extension4');
INSERT INTO dset_ii_value5 (id, dset_ii_id, extension) VALUES (5, 25, 'Extension5');


--
-- TOC entry 2269 (class 0 OID 18638)
-- Dependencies: 1695
-- Data for Name: dset_ii_value6; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value6 (id, extension) VALUES (1, 'Extension1');
INSERT INTO dset_ii_value6 (id, extension) VALUES (2, 'Extension4');
INSERT INTO dset_ii_value6 (id, extension) VALUES (3, 'Extension5');


--
-- TOC entry 2270 (class 0 OID 18641)
-- Dependencies: 1696
-- Data for Name: dset_tel; Type: TABLE DATA; 
--

INSERT INTO dset_tel (id, null_flavor) VALUES (0, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (1, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (2, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (3, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (4, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (5, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (6, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (7, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (8, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (9, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (10, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (11, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (12, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (13, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (14, NULL);


--
-- TOC entry 2271 (class 0 OID 18644)
-- Dependencies: 1697
-- Data for Name: dset_tel_email; Type: TABLE DATA; 
--

INSERT INTO dset_tel_email (id, null_flavor) VALUES (0, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (1, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (2, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (3, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (4, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (5, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (6, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (7, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (8, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (9, NULL);


--
-- TOC entry 2272 (class 0 OID 18647)
-- Dependencies: 1698
-- Data for Name: dset_tel_email_value1; Type: TABLE DATA; 
--

INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (0, 'TEL_EMAIL_VALUE1');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (1, 'TEL_EMAIL_VALUE2');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (2, 'TEL_EMAIL_VALUE3');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (3, 'TEL_EMAIL_VALUE4');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (4, 'TEL_EMAIL_VALUE5');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (5, 'TEL_EMAIL_VALUE1');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (6, 'TEL_EMAIL_VALUE2');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (7, 'TEL_EMAIL_VALUE3');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (8, 'TEL_EMAIL_VALUE4');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (9, 'TEL_EMAIL_VALUE5');


--
-- TOC entry 2273 (class 0 OID 18650)
-- Dependencies: 1699
-- Data for Name: dset_tel_email_value2; Type: TABLE DATA; 
--



--
-- TOC entry 2274 (class 0 OID 18653)
-- Dependencies: 1700
-- Data for Name: dset_tel_person; Type: TABLE DATA; 
--

INSERT INTO dset_tel_person (id, null_flavor) VALUES (0, NULL);
INSERT INTO dset_tel_person (id, null_flavor) VALUES (1, NULL);
INSERT INTO dset_tel_person (id, null_flavor) VALUES (2, NULL);
INSERT INTO dset_tel_person (id, null_flavor) VALUES (3, NULL);
INSERT INTO dset_tel_person (id, null_flavor) VALUES (4, NULL);


--
-- TOC entry 2275 (class 0 OID 18656)
-- Dependencies: 1701
-- Data for Name: dset_tel_person_value1; Type: TABLE DATA; 
--

INSERT INTO dset_tel_person_value1 (dset_tel_person_id, tel_person_value) VALUES (0, 'TEL_PERSON_VALUE1');
INSERT INTO dset_tel_person_value1 (dset_tel_person_id, tel_person_value) VALUES (1, 'TEL_PERSON_VALUE2');
INSERT INTO dset_tel_person_value1 (dset_tel_person_id, tel_person_value) VALUES (2, 'TEL_PERSON_VALUE3');
INSERT INTO dset_tel_person_value1 (dset_tel_person_id, tel_person_value) VALUES (3, 'TEL_PERSON_VALUE4');
INSERT INTO dset_tel_person_value1 (dset_tel_person_id, tel_person_value) VALUES (4, 'TEL_PERSON_VALUE5');


--
-- TOC entry 2276 (class 0 OID 18659)
-- Dependencies: 1702
-- Data for Name: dset_tel_phone; Type: TABLE DATA; 
--

INSERT INTO dset_tel_phone (id, null_flavor) VALUES (0, NULL);
INSERT INTO dset_tel_phone (id, null_flavor) VALUES (1, NULL);
INSERT INTO dset_tel_phone (id, null_flavor) VALUES (2, NULL);
INSERT INTO dset_tel_phone (id, null_flavor) VALUES (3, NULL);
INSERT INTO dset_tel_phone (id, null_flavor) VALUES (4, NULL);


--
-- TOC entry 2277 (class 0 OID 18662)
-- Dependencies: 1703
-- Data for Name: dset_tel_phone_value1; Type: TABLE DATA; 
--

INSERT INTO dset_tel_phone_value1 (dset_tel_phone_id, tel_phone_value) VALUES (0, 'TEL_PHONE_VALUE1');
INSERT INTO dset_tel_phone_value1 (dset_tel_phone_id, tel_phone_value) VALUES (1, 'TEL_PHONE_VALUE2');
INSERT INTO dset_tel_phone_value1 (dset_tel_phone_id, tel_phone_value) VALUES (2, 'TEL_PHONE_VALUE3');
INSERT INTO dset_tel_phone_value1 (dset_tel_phone_id, tel_phone_value) VALUES (3, 'TEL_PHONE_VALUE4');
INSERT INTO dset_tel_phone_value1 (dset_tel_phone_id, tel_phone_value) VALUES (4, 'TEL_PHONE_VALUE5');


--
-- TOC entry 2278 (class 0 OID 18665)
-- Dependencies: 1704
-- Data for Name: dset_tel_tel_value_3; Type: TABLE DATA; 
--

INSERT INTO dset_tel_tel_value_3 (dset_tel_id, dset_tel_value3_id) VALUES (10, 1);
INSERT INTO dset_tel_tel_value_3 (dset_tel_id, dset_tel_value3_id) VALUES (11, 1);
INSERT INTO dset_tel_tel_value_3 (dset_tel_id, dset_tel_value3_id) VALUES (12, 1);
INSERT INTO dset_tel_tel_value_3 (dset_tel_id, dset_tel_value3_id) VALUES (13, 1);
INSERT INTO dset_tel_tel_value_3 (dset_tel_id, dset_tel_value3_id) VALUES (14, 1);


--
-- TOC entry 2279 (class 0 OID 18668)
-- Dependencies: 1705
-- Data for Name: dset_tel_url; Type: TABLE DATA; 
--

INSERT INTO dset_tel_url (id, null_flavor) VALUES (0, NULL);
INSERT INTO dset_tel_url (id, null_flavor) VALUES (1, NULL);
INSERT INTO dset_tel_url (id, null_flavor) VALUES (2, NULL);
INSERT INTO dset_tel_url (id, null_flavor) VALUES (3, NULL);
INSERT INTO dset_tel_url (id, null_flavor) VALUES (4, NULL);


--
-- TOC entry 2280 (class 0 OID 18671)
-- Dependencies: 1706
-- Data for Name: dset_tel_url_value1; Type: TABLE DATA; 
--

INSERT INTO dset_tel_url_value1 (dset_tel_url_id, tel_url_value) VALUES (0, 'TEL_URL_VALUE1');
INSERT INTO dset_tel_url_value1 (dset_tel_url_id, tel_url_value) VALUES (1, 'TEL_URL_VALUE2');
INSERT INTO dset_tel_url_value1 (dset_tel_url_id, tel_url_value) VALUES (2, 'TEL_URL_VALUE3');
INSERT INTO dset_tel_url_value1 (dset_tel_url_id, tel_url_value) VALUES (3, 'TEL_URL_VALUE4');
INSERT INTO dset_tel_url_value1 (dset_tel_url_id, tel_url_value) VALUES (4, 'TEL_URL_VALUE5');


--
-- TOC entry 2281 (class 0 OID 18674)
-- Dependencies: 1707
-- Data for Name: dset_tel_value1; Type: TABLE DATA; 
--

INSERT INTO dset_tel_value1 (dset_tel_id, tel_value) VALUES (0, 'tel://123-456-7891');
INSERT INTO dset_tel_value1 (dset_tel_id, tel_value) VALUES (1, 'tel://123-456-7892');
INSERT INTO dset_tel_value1 (dset_tel_id, tel_value) VALUES (2, 'tel://123-456-7893');
INSERT INTO dset_tel_value1 (dset_tel_id, tel_value) VALUES (3, 'tel://123-456-7894');
INSERT INTO dset_tel_value1 (dset_tel_id, tel_value) VALUES (4, 'tel://123-456-7895');


--
-- TOC entry 2282 (class 0 OID 18677)
-- Dependencies: 1708
-- Data for Name: dset_tel_value2; Type: TABLE DATA; 
--

INSERT INTO dset_tel_value2 (id, dset_tel_id, tel_value) VALUES (1, 5, 'tel://123-456-7891');
INSERT INTO dset_tel_value2 (id, dset_tel_id, tel_value) VALUES (2, 6, 'tel://123-456-7892');
INSERT INTO dset_tel_value2 (id, dset_tel_id, tel_value) VALUES (3, 7, 'tel://123-456-7893');
INSERT INTO dset_tel_value2 (id, dset_tel_id, tel_value) VALUES (4, 8, 'tel://123-456-7894');
INSERT INTO dset_tel_value2 (id, dset_tel_id, tel_value) VALUES (5, 9, 'tel://123-456-7895');


--
-- TOC entry 2283 (class 0 OID 18680)
-- Dependencies: 1709
-- Data for Name: dset_tel_value3; Type: TABLE DATA; 
--

INSERT INTO dset_tel_value3 (id, tel_value) VALUES (1, 'tel://123-456-7892');


--
-- TOC entry 2284 (class 0 OID 18683)
-- Dependencies: 1710
-- Data for Name: ed_datatype; Type: TABLE DATA; 
--

INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (2, '110101010010', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (3, '100101010011', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (4, '100001010010', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (5, '110001010011', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (6, '110001011110', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (7, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (8, NULL, NULL, '110101010011', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (9, NULL, NULL, '010101010011', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (10, NULL, NULL, '000101010011', 'GZ', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (11, NULL, NULL, '110001010010', 'GZ', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, 'GZ', NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (13, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (14, NULL, NULL, NULL, NULL, NULL, '110001010111', 'GZ', NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (15, NULL, NULL, NULL, NULL, NULL, '110001010011', 'GZ', 'DESCRIPTION', NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (16, NULL, NULL, NULL, NULL, NULL, '110001010010', 'GZ', 'DESCRIPTION', 'VALUE3_VALUE_A');

--
-- TOC entry 2285 (class 0 OID 18689)
-- Dependencies: 1711
-- Data for Name: ed_text_datatype; Type: TABLE DATA; 
--

INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (18, 'ED_TEXT_VALUE1_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (19, 'ED_TEXT_VALUE1_VALUE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (20, 'ED_TEXT_VALUE1_VALUE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (21, 'ED_TEXT_VALUE1_VALUE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (22, 'ED_TEXT_VALUE1_VALUE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (23, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (24, NULL, NULL, 'ED_TEXT_VALUE2_VALUE1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (25, NULL, NULL, 'ED_TEXT_VALUE2_VALUE2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (26, NULL, NULL, 'ED_TEXT_VALUE2_VALUE3', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (27, NULL, NULL, 'ED_TEXT_VALUE2_VALUE4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (28, NULL, NULL, NULL, NULL, NULL, 'GZ', NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (29, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (30, NULL, NULL, NULL, NULL, '110001011010', 'GZ', NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (31, NULL, NULL, NULL, NULL, '110111010011', 'GZ', 'DESCRIPTION', NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (32, NULL, NULL, NULL, NULL, '110001010010', 'GZ', 'DESCRIPTION', 'VALUE3_VALUE_A');


--
-- TOC entry 2286 (class 0 OID 18695)
-- Dependencies: 1712
-- Data for Name: en_datatype; Type: TABLE DATA; 
--

INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (2, 'Mr. John Doe Jr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (3, 'Mr. John Doe Double Jr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (4, 'Mr. John Doe Sr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (5, 'Mr. John Doe Super Sr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (6, 'Mr. John Doe II', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (7, NULL, 'Mr. John Doe Jr1.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (8, NULL, 'Mr. John Doe Jr2.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (9, NULL, 'Mr. John Doe Jr3.', 'JDJ3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (10, NULL, 'Mr. John Doe Jr4.', 'JDJ4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (11, NULL, 'Mr. John Doe Jr5.', 'JDJ5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (12, NULL, NULL, NULL, 'Mrs. Sarah Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (13, NULL, NULL, NULL, 'Mrs. Sarah Doe II', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (14, NULL, NULL, NULL, 'Mrs. Sarah Doe III', 'MSD1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (15, NULL, NULL, NULL, 'Mrs. Sarah Doe IV', 'MSD2', 'VALUE3_PN_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (16, NULL, NULL, NULL, 'Mrs. Sarah Doe V', 'MSD3', 'VALUE3_PN_CODE_SYSTEM2', '1.3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE3', 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE4', 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE1', 'SP,BR,NB', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (23, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', NULL, 'JDJ1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', NULL, 'JDJ1', NULL, 'VALUE5_PN_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', NULL, 'JDJ1', NULL, 'VALUE5_PN_CODE_SYSTEM1', NULL, '2.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', NULL, 'VALUE5_PN2_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VII', NULL, 'VALUE5_PN2_CODE2', NULL, 'VALUE5_PN2_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VIII', NULL, 'VALUE5_PN2_CODE3', NULL, 'VALUE5_PN2_CODE_SYSTEM2', NULL, '2.2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', 'Mr. John Doe VI', 'VALUE5_PN_CODE1', 'VALUE5_PN2_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', 'Mr. John Doe VII', 'VALUE5_PN_CODE2', 'VALUE5_PN2_CODE2', 'VALUE5_PN_CODE_SYSTEM1', 'VALUE5_PN2_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', 'Mr. John Doe VIII', 'VALUE5_PN_CODE3', 'VALUE5_PN2_CODE3', 'VALUE5_PN_CODE_SYSTEM2', 'VALUE5_PN2_CODE_SYSTEM2', '2.1', '2.2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', NULL, 'VALUE6_PN_CODE1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', NULL, 'VALUE6_PN_CODE2', NULL, 'VALUE6_PN_CODE_SYSTEM1', NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', NULL, 'VALUE6_PN_CODE3', NULL, 'VALUE6_PN_CODE_SYSTEM2', NULL, '3.1', NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', NULL, 'VALUE6_ON_CODE1', NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VII', NULL, 'VALUE6_ON_CODE1', NULL, 'VALUE6_ON_CODE_SYSTEM1', NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VIII', NULL, 'VALUE6_ON_CODE2', NULL, 'VALUE6_ON_CODE_SYSTEM2', NULL, '1.1');
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', 'Mr. John Doe VI', 'VALUE6_PN_CODE1', 'VALUE6_ON_CODE1', NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', 'Mr. John Doe VII', 'VALUE6_PN_CODE2', 'VALUE6_ON_CODE2', 'VALUE6_PN_CODE_SYSTEM2', 'VALUE6_ON_CODE_SYSTEM2', NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', 'Mr. John Doe VIII', 'VALUE6_PN_CODE3', 'VALUE6_ON_CODE3', 'VALUE6_PN_CODE_SYSTEM3', 'VALUE6_ON_CODE_SYSTEM3', '2.1', '1.1');


--
-- TOC entry 2287 (class 0 OID 18701)
-- Dependencies: 1713
-- Data for Name: en_on_datatype; Type: TABLE DATA; 
--

INSERT INTO en_on_datatype (id, value1_on_value) VALUES (1, NULL);
INSERT INTO en_on_datatype (id, value1_on_value) VALUES (2, 'NCI1');
INSERT INTO en_on_datatype (id, value1_on_value) VALUES (3, 'NCI2');
INSERT INTO en_on_datatype (id, value1_on_value) VALUES (4, 'NCI3');
INSERT INTO en_on_datatype (id, value1_on_value) VALUES (5, 'NCI4');
INSERT INTO en_on_datatype (id, value1_on_value) VALUES (6, 'NCI5');


--
-- TOC entry 2288 (class 0 OID 18704)
-- Dependencies: 1714
-- Data for Name: en_pn_datatype; Type: TABLE DATA; 
--

INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (1, NULL);
INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (2, 'Mr. John Doe');
INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (3, 'Mr. John Doe II');
INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (4, 'Mr. John Doe III');
INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (5, 'Mr. John Doe IV');
INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (6, 'Mr. John Doe V');


--
-- TOC entry 2289 (class 0 OID 18707)
-- Dependencies: 1715
-- Data for Name: ii_datatype; Type: TABLE DATA; 
--

INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (2, 'II_Extension', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (4, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (5, NULL, NULL, 'II_VALUE2_ROOT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (6, NULL, NULL, 'II_VALUE2_ROOT', 'II_VALUE2_EXTENSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (8, NULL, NULL, NULL, NULL, 'UNK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (9, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (10, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (11, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', 'ISS', 'BUSN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (12, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', 'VRF', 'BUSN', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'INV', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'UNV', NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'ISS', 'BUSN', NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'VRF', 'BUSN', '0');


--
-- TOC entry 2290 (class 0 OID 18713)
-- Dependencies: 1716
-- Data for Name: int_datatype; Type: TABLE DATA; 
--

INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 1.0, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 2.0, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 3.0, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 4.0, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 5.0, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 6.0);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 7.0);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 8.0);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, 'NI', NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, 'NI', NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, 'NI', NULL);


--
-- TOC entry 2291 (class 0 OID 18719)
-- Dependencies: 1717
-- Data for Name: ivl_int; Type: TABLE DATA; 
--

INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (2, 1.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (3, NULL, 10.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (4, 1.0, 10.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (5, 1.0, 1.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (6, 2.0, 1.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (7, NULL, NULL, 10.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (8, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (9, NULL, NULL, 10.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (10, NULL, NULL, 1.0, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (11, NULL, NULL, 1.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (12, NULL, NULL, NULL, NULL, null, 11.0, '1', NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (13, NULL, NULL, NULL, NULL, null, 12.0, '1', NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (14, NULL, NULL, NULL, NULL, null, 13.0, '1', NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (15, NULL, NULL, NULL, NULL, 8.0, null, '1', NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (16, NULL, NULL, NULL, NULL, 9.0, null, '1', NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5.0, 11.0, 5, 'NI');
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.0, 12.0, 4, 'NI');
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7.0, 13.0, 3, 'NI');
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8.0, 14.0, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9.0, NULL, NULL, 'NI');


--
-- TOC entry 2292 (class 0 OID 18725)
-- Dependencies: 1718
-- Data for Name: ivl_pq; Type: TABLE DATA; 
--

INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (2, 1.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (3, 2.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (4, 3.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (5, 4.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (6, 5.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (7, NULL, 221.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (8, NULL, 222.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (9, NULL, 221.1, 2.0, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (10, NULL, 222.1, NULL, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (11, NULL, 223.1, 2.0, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (12, NULL, 224.1, 2.0, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (13, NULL, NULL, NULL, NULL, 224.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (14, NULL, NULL, NULL, NULL, 224.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (15, NULL, NULL, NULL, NULL, NULL, 2.0, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (16, NULL, NULL, NULL, NULL, 224.1, 2.0, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5.1, 2.0, 'VALUE4_HIGH_UNIT1', '1', NULL, 1.1, 2.0, 'VALUE4_LOW_UNIT', NULL, '1', 5.1, 2.0, 'VALUE4_WIDTH_UNIT1', NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.1, 2.0, 'VALUE4_HIGH_UNIT2', '1', NULL, 1.1, 2.0, 'VALUE4_LOW_UNIT', NULL, '1', 5.1, 2.0, 'VALUE4_WIDTH_UNIT2', NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, 'NI', 'NA');
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8.1, 2.0, 'VALUE4_HIGH_UNIT4', '1', NULL, NULL, NULL, NULL, 'NI', '1', 5.0, 2.0, 'VALUE4_WIDTH_UNIT4', NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9.1, 2.0, 'VALUE4_HIGH_UNIT5', '1', NULL, 1.1, 2.0, NULL, NULL, '1', NULL, NULL, NULL, 'NI', NULL);


--
-- TOC entry 2293 (class 0 OID 18731)
-- Dependencies: 1719
-- Data for Name: ivl_pqv; Type: TABLE DATA; 
--

INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (2, 1.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (3, 2.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (4, 3.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (5, 4.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (6, 5.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (8, NULL, NULL, 1.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (9, NULL, NULL, 2.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (10, NULL, NULL, 3.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (11, NULL, NULL, 4.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (12, NULL, NULL, 5.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (13, NULL, NULL, 1.1, 2.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (14, NULL, NULL, 2.1, 2.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (15, NULL, NULL, 3.1, 2.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (16, NULL, NULL, 4.1, 2.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (17, NULL, NULL, 5.1, 2.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (19, NULL, NULL, NULL, NULL, NULL, 1.1, 2.0, 610.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (20, NULL, NULL, NULL, NULL, NULL, 2.1, 2.0, 620.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (21, NULL, NULL, NULL, NULL, NULL, 3.1, 2.0, 630.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (22, NULL, NULL, NULL, NULL, NULL, 4.1, 2.0, 640.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (23, NULL, NULL, NULL, NULL, NULL, 5.1, 2.0, 650.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (24, NULL, NULL, NULL, NULL, NULL, 1.1, 2.0, 610.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (25, NULL, NULL, NULL, NULL, NULL, 2.1, 2.0, 620.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (26, NULL, NULL, NULL, NULL, NULL, 3.1, 2.0, 630.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (27, NULL, NULL, NULL, NULL, NULL, 4.1, 2.0, 640.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (28, NULL, NULL, NULL, NULL, NULL, 5.1, 2.0, 650.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 710.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 720.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 730.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 740.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 750.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11.1, 2.0, 'NI', 100.1, 2.0, 'NI', NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12.1, 2.0, 'NI', 100.1, 2.0, 'NI', NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13.1, 2.0, 'NI', 100.1, 2.0, 'NI', NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14.1, 2.0, 'NI', 100.1, 2.0, 'NI', NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 15.1, 2.0, 'NI', 100.1, 2.0, 'NI', NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 210.1, 2.0, 'NI', 100.1, 2.0, 'NI', 5.0, 2.0, 'NI');
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 220.1, 2.0, 'NI', 100.1, 2.0, 'NI', 5.0, 2.0, 'NI');
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 230.1, 2.0, 'NI', 100.1, 2.0, 'NI', 5.0, 2.0, 'NI');
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 240.1, 2.0, 'NI', 100.1, 2.0, 'NI', 5.0, 2.0, 'NI');
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 250.1, 2.0, 'NI', 100.1, 2.0, 'NI', 5.0, 2.0, 'NI');


--
-- TOC entry 2294 (class 0 OID 18737)
-- Dependencies: 1720
-- Data for Name: ivl_real; Type: TABLE DATA; 
--

INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (2, 10.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (3, 1, 210.19999999999999, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (4, NULL, 220.19999999999999, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (5, 3, 230.19999999999999, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (6, 230.19999999999999, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (7, NULL, NULL, -310, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (8, NULL, NULL, 40, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (9, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (10, NULL, NULL, 4222222, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (11, NULL, NULL, 43, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (12, NULL, NULL, 44, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (13, NULL, NULL, NULL, NULL, NULL, 510, '1', 2, '1', 44, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (14, NULL, NULL, NULL, NULL, NULL, 520, '1', 2, '1', 44, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (15, NULL, NULL, NULL, NULL, NULL, 530, '1', 2, '1', NULL, 'NI', NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (16, NULL, NULL, NULL, NULL, NULL, 540, '1', 2, '1', 44, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI');
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (18, NULL, NULL, NULL, NULL, NULL, 610, '1', 251, '1', 4, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (19, NULL, NULL, NULL, NULL, NULL, 620, '1', 252, '1', 4, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (20, NULL, NULL, NULL, NULL, NULL, 630, '1', 253, '1', 4, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (21, NULL, NULL, NULL, NULL, NULL, 640, '1', 254, '1', 4, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (22, NULL, NULL, NULL, NULL, 101, 650, '1', 255, '1', 4, NULL, NULL);


--
-- TOC entry 2295 (class 0 OID 18740)
-- Dependencies: 1721
-- Data for Name: ivl_ts; Type: TABLE DATA; 
--

INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (2, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (3, '2010-03-12 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (4, NULL, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (5, NULL, '2000-03-12 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (6, '2010-03-03 00:00:00', '2010-03-13 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (7, NULL, NULL, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (8, NULL, NULL, '2001-03-11 00:00:00', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (9, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (10, NULL, NULL, '2010-03-13 00:00:00', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (11, NULL, NULL, '2010-03-14 00:00:00', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (12, NULL, NULL, '2010-03-15 00:00:00', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (13, NULL, NULL, NULL, NULL, '2010-03-11 00:00:00', '2010-03-01 00:00:00', NULL, 4, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (14, NULL, NULL, NULL, NULL, '2010-03-12 00:00:00', '2010-03-02 00:00:00', NULL, 4, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (15, NULL, NULL, NULL, NULL, '2010-03-13 00:00:00', '2010-03-03 00:00:00', NULL, 4, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (16, NULL, NULL, NULL, NULL, '2010-03-14 00:00:00', '2010-03-04 00:00:00', 'NI', NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (17, NULL, NULL, NULL, NULL, '2010-03-15 00:00:00', '2010-03-05 00:00:00', 'NI', NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (18, NULL, NULL, NULL, NULL, NULL, '2010-03-01 00:00:00', NULL, 4, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (19, NULL, NULL, NULL, NULL, '2010-03-22 00:00:00', NULL, 'NI', NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, 'NA', NULL, 'NA');
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (21, NULL, NULL, NULL, NULL, '2010-03-24 00:00:00', '2010-03-04 00:00:00', NULL, 4, NULL);


--
-- TOC entry 2296 (class 0 OID 18743)
-- Dependencies: 1722
-- Data for Name: pq_datatype; Type: TABLE DATA; 
--

INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (2, 1.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (3, 2.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (4, 3.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (5, 4.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (6, 5.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (7, 1.12, 'GALLON', NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (8, 2.12, 'GALLON', NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (9, 3.12, 'GALLON', NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (10, 4.12, 'GALLON', NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (11, 5.12, 'GALLON', NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (12, NULL, NULL, 1.23, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (13, NULL, NULL, 2.23, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (14, NULL, NULL, 3.23, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (15, NULL, NULL, 4.23, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (16, NULL, NULL, 5.23, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (17, NULL, NULL, NULL, NULL, NULL, 1.34, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (18, NULL, NULL, NULL, NULL, NULL, 2.34, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (19, NULL, NULL, NULL, NULL, 'LITER', 1.37, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (20, NULL, NULL, NULL, NULL, 'LITER', 2.37, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (21, NULL, NULL, NULL, NULL, 'LITER', 3.37, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (22, NULL, NULL, NULL, NULL, 'LITER', 1.38, 2.0);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (23, NULL, NULL, NULL, NULL, 'LITER', 2.38, 2.0);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (24, NULL, NULL, NULL, NULL, 'LITER', 3.38, 2.0);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (25, NULL, NULL, NULL, 'NA', NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (26, NULL, NULL, NULL, 'NA', NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (27, NULL, NULL, NULL, 'NA', NULL, NULL, NULL);


--
-- TOC entry 2297 (class 0 OID 18749)
-- Dependencies: 1723
-- Data for Name: pqv_datatype; Type: TABLE DATA; 
--

INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (6, 1.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (7, 2.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (8, 3.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (9, 4.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (10, 5.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (16, NULL, NULL, 1.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (17, NULL, NULL, 2.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (18, NULL, NULL, 3.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (19, NULL, NULL, 4.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (20, NULL, NULL, 5.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (21, NULL, NULL, 1.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (22, NULL, NULL, 2.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (23, NULL, NULL, 3.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (24, NULL, NULL, 4.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (25, NULL, NULL, 5.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (26, NULL, 'NI', 1.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (27, NULL, 'NI', 2.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (28, NULL, 'NI', 3.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (29, NULL, 'NI', 4.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (30, NULL, 'NI', 5.23, 2.0, NULL, NULL, NULL, NULL, NULL);


--
-- TOC entry 2298 (class 0 OID 18755)
-- Dependencies: 1724
-- Data for Name: real_datatype; Type: TABLE DATA; 
--

INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 1001.15, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 1002.15, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, -1003, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 1004, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 1005.15, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 1101.1500000000001);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 1102);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, -1201.1500000000001);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, 'NA', NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, 'NA', NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, 'NA', NULL);


--
-- TOC entry 2299 (class 0 OID 18758)
-- Dependencies: 1725
-- Data for Name: sc_datatype; Type: TABLE DATA; 
--

INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (2, 'VALUE1_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (3, 'VALUE1_VALUE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (4, 'VALUE1_VALUE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (5, 'VALUE1_VALUE4', 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (6, 'VALUE1_VALUE5', 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (7, NULL, NULL, 'UNK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (8, NULL, NULL, NULL, 'VALUE2_VALUE1', NULL, 'VALUE2_CODE_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (9, NULL, NULL, NULL, 'VALUE2_VALUE2', NULL, 'VALUE2_CODE_CODE2', 'VALUE2_CODE_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (10, NULL, NULL, NULL, 'VALUE2_VALUE3', NULL, 'VALUE2_CODE_CODE3', 'VALUE2_CODE_CODE_SYSTEM2', 'VALUE2_CODE_CODE_SYSTEM_NAME1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (11, NULL, NULL, NULL, 'VALUE2_VALUE4', NULL, 'VALUE2_CODE_CODE4', 'VALUE2_CODE_CODE_SYSTEM3', 'VALUE2_CODE_CODE_SYSTEM_NAME2', '1.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (12, NULL, NULL, NULL, 'VALUE2_VALUE5', NULL, 'VALUE2_CODE_CODE5', 'VALUE2_CODE_CODE_SYSTEM4', 'VALUE2_CODE_CODE_SYSTEM_NAME3', '1.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE1', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE2', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE1', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER1', 'NI', NULL, 'NI', NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE3', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER', NULL, 'VALUE3_CODE_DISPLAY_VALUE', 'NI', NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE4', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE5', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE6', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE7', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC1', NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE8', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC31', 'VALUE3_CODE_ORIG_TXT_VALUE31');
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (23, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE9', 'NI', 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC31', 'VALUE3_CODE_ORIG_TXT_VALUE31');


--
-- TOC entry 2300 (class 0 OID 18764)
-- Dependencies: 1726
-- Data for Name: st_datatype; Type: TABLE DATA; 
--

INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'VALUE1_VALUE1', NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'VALUE1_VALUE2', NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'VALUE1_VALUE3', NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'VALUE1_VALUE4', NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'VALUE1_VALUE5', NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'VALUE2_VALUE1');
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'VALUE2_VALUE2');
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'VALUE2_VALUE3');
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'VALUE2_VALUE4');
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'VALUE2_VALUE5');
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, 'UNK', NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (13, NULL, 'UNK', NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (14, NULL, 'UNK', NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (15, NULL, 'UNK', NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (16, NULL, 'UNK', NULL);


--
-- TOC entry 2301 (class 0 OID 18767)
-- Dependencies: 1727
-- Data for Name: st_nt_datatype; Type: TABLE DATA; 
--

INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'VALUE1_VALUE1', NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'VALUE1_VALUE2', NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'VALUE1_VALUE3', NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'VALUE1_VALUE4', NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'VALUE1_VALUE5', NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'VALUE2_VALUE1');
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'VALUE2_VALUE2');
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'VALUE2_VALUE3');
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'VALUE2_VALUE4');
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'VALUE2_VALUE5');
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, 'UNK', NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (13, NULL, 'UNK', NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (14, NULL, 'UNK', NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (15, NULL, 'UNK', NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (16, NULL, 'UNK', NULL);


--
-- TOC entry 2302 (class 0 OID 18770)
-- Dependencies: 1728
-- Data for Name: tel_datatype; Type: TABLE DATA; 
--

INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'tel://123-456-7891', NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'tel://123-456-7892', NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'tel://123-456-7893', NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'tel://123-456-7894', NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'tel://123-456-7895', NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'tel://123-456-7896');
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'tel://123-456-7897');
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'tel://123-456-7898');
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'tel://123-456-7893');
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'tel://123-456-7894');
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, NULL, 'tel://123-456-7895');


--
-- TOC entry 2303 (class 0 OID 18773)
-- Dependencies: 1729
-- Data for Name: tel_email_datatype; Type: TABLE DATA; 
--

INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'mailto:jdoe1@nci.gov', NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'mailto:jdoe2@nci.gov', NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'mailto:jdoe3@nci.gov', NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'mailto:jdoe4@nci.gov', NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'mailto:jdoe5@nci.gov', NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'MailTo:jdoe1@nci.gov');
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'MailTo:jdoe2@nci.gov');
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'mailto:jdoe3@nci.gov');
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'mailto:jdoe4@nci.gov');
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'mailto:jdoe5@nci.gov');


--
-- TOC entry 2304 (class 0 OID 18776)
-- Dependencies: 1730
-- Data for Name: tel_person_datatype; Type: TABLE DATA; 
--

INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'tel:8004226231', NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'x-text-fax:8004226232', NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'x-text-tel:8004226233', NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'mailto:8004226235', NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'x-text-tel:8004226235', NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'x-text-tel:8004226234');
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'mailto:8004226235');
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'x-text-tel:8004226233');
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'mailto:8004226235');
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'x-text-tel:8004226235');


--
-- TOC entry 2305 (class 0 OID 18779)
-- Dependencies: 1731
-- Data for Name: tel_phone_datatype; Type: TABLE DATA; 
--

INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'tel:8004226231', NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'x-text-tel:8004226232', NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'x-text-fax:8004226233', NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'tel:8004226234', NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'tel:8004226235', NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'tel:8004226231');
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'x-text-tel:8004226232');
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'x-text-fax:8004226233');
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'tel:8004226234');
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'tel:8004226235');


--
-- TOC entry 2306 (class 0 OID 18782)
-- Dependencies: 1732
-- Data for Name: tel_url_datatype; Type: TABLE DATA; 
--

INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'https://www.cancer.gov', NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'file://test.xml', NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'ftp://cancer.gov', NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'file://www.cancer3.gov', NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'http://www.cancer4.gov', NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'https://www.cancer.gov');
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'nfs://d/test.xml');
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'file://www.cancer3.gov');
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'ftp://cancer.gov');
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'http://www.cancer4.gov');


--
-- TOC entry 2307 (class 0 OID 18785)
-- Dependencies: 1733
-- Data for Name: ts_datatype; Type: TABLE DATA; 
--

INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, '2010-03-11 00:00:00', NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, '2010-03-12 00:00:00', NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, '2010-03-13 00:00:00', NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, '2010-03-14 00:00:00', NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, '2010-03-15 00:00:00', NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, '2010-03-21 00:00:00');
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, '2010-03-22 00:00:00');
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, '2010-03-23 00:00:00');
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, 'NA', NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, 'NA', NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, 'NA', NULL);


--
-- TOC entry 2001 (class 2606 OID 18795)
-- Dependencies: 1649 1649 1649
-- Name: ad_ad_datatype_value9_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ad_ad_datatype_value9
    ADD CONSTRAINT ad_ad_datatype_value9_pkey PRIMARY KEY (ad_datatype_id, ad_datatype_value9_id);


--
-- TOC entry 2004 (class 2606 OID 18797)
-- Dependencies: 1650 1650
-- Name: ad_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ad_datatype
    ADD CONSTRAINT ad_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2008 (class 2606 OID 18799)
-- Dependencies: 1652 1652
-- Name: ad_datatype_value8_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ad_datatype_value8
    ADD CONSTRAINT ad_datatype_value8_pkey PRIMARY KEY (id);


--
-- TOC entry 2010 (class 2606 OID 18801)
-- Dependencies: 1653 1653
-- Name: ad_datatype_value9_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ad_datatype_value9
    ADD CONSTRAINT ad_datatype_value9_pkey PRIMARY KEY (id);


--
-- TOC entry 2012 (class 2606 OID 18803)
-- Dependencies: 1654 1654
-- Name: bl_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY bl_datatype
    ADD CONSTRAINT bl_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2014 (class 2606 OID 18805)
-- Dependencies: 1655 1655
-- Name: bl_nonnull_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY bl_nonnull_datatype
    ADD CONSTRAINT bl_nonnull_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2016 (class 2606 OID 18807)
-- Dependencies: 1656 1656 1656
-- Name: cd_cd_datatype_value8_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY cd_cd_datatype_value8
    ADD CONSTRAINT cd_cd_datatype_value8_pkey PRIMARY KEY (cd_data_type_id, cd_datatype_value8_id);


--
-- TOC entry 2019 (class 2606 OID 18809)
-- Dependencies: 1657 1657
-- Name: cd_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY cd_datatype
    ADD CONSTRAINT cd_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2022 (class 2606 OID 18811)
-- Dependencies: 1658 1658
-- Name: cd_datatype_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY cd_datatype_value6
    ADD CONSTRAINT cd_datatype_value6_pkey PRIMARY KEY (cd_datatype_id);


--
-- TOC entry 2024 (class 2606 OID 18813)
-- Dependencies: 1659 1659
-- Name: cd_datatype_value7_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY cd_datatype_value7
    ADD CONSTRAINT cd_datatype_value7_pkey PRIMARY KEY (id);


--
-- TOC entry 2026 (class 2606 OID 18815)
-- Dependencies: 1660 1660
-- Name: cd_datatype_value8_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY cd_datatype_value8
    ADD CONSTRAINT cd_datatype_value8_pkey PRIMARY KEY (id);


--
-- TOC entry 2028 (class 2606 OID 18817)
-- Dependencies: 1661 1661
-- Name: dset_ad_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_datatype
    ADD CONSTRAINT dset_ad_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2030 (class 2606 OID 18819)
-- Dependencies: 1662 1662 1662
-- Name: dset_ad_dset_ad_value8_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_dset_ad_value8
    ADD CONSTRAINT dset_ad_dset_ad_value8_pkey PRIMARY KEY (dset_ad_datatype_id, dset_ad_value8_id);


--
-- TOC entry 2033 (class 2606 OID 18821)
-- Dependencies: 1663 1663
-- Name: dset_ad_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value1
    ADD CONSTRAINT dset_ad_value1_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2035 (class 2606 OID 18823)
-- Dependencies: 1664 1664
-- Name: dset_ad_value2_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value2
    ADD CONSTRAINT dset_ad_value2_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2037 (class 2606 OID 18825)
-- Dependencies: 1665 1665
-- Name: dset_ad_value3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value3
    ADD CONSTRAINT dset_ad_value3_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2039 (class 2606 OID 18827)
-- Dependencies: 1666 1666
-- Name: dset_ad_value4_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value4
    ADD CONSTRAINT dset_ad_value4_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2041 (class 2606 OID 18829)
-- Dependencies: 1667 1667
-- Name: dset_ad_value5_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value5
    ADD CONSTRAINT dset_ad_value5_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2043 (class 2606 OID 18831)
-- Dependencies: 1668 1668
-- Name: dset_ad_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value6
    ADD CONSTRAINT dset_ad_value6_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2045 (class 2606 OID 18833)
-- Dependencies: 1669 1669
-- Name: dset_ad_value7_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value7
    ADD CONSTRAINT dset_ad_value7_pkey PRIMARY KEY (id);


--
-- TOC entry 2048 (class 2606 OID 18835)
-- Dependencies: 1670 1670
-- Name: dset_ad_value8_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value8
    ADD CONSTRAINT dset_ad_value8_pkey PRIMARY KEY (id);


--
-- TOC entry 2052 (class 2606 OID 18839)
-- Dependencies: 1672 1672 1672
-- Name: dset_cd_cd_value7_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_cd_value7
    ADD CONSTRAINT dset_cd_cd_value7_pkey PRIMARY KEY (dset_cd_id, dset_cd_value7_id);


--
-- TOC entry 2050 (class 2606 OID 18837)
-- Dependencies: 1671 1671
-- Name: dset_cd_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd
    ADD CONSTRAINT dset_cd_pkey PRIMARY KEY (id);


--
-- TOC entry 2055 (class 2606 OID 18841)
-- Dependencies: 1673 1673
-- Name: dset_cd_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value1
    ADD CONSTRAINT dset_cd_value1_pkey PRIMARY KEY (dset_cd_id);


--
-- TOC entry 2057 (class 2606 OID 18843)
-- Dependencies: 1674 1674
-- Name: dset_cd_value2_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value2
    ADD CONSTRAINT dset_cd_value2_pkey PRIMARY KEY (dset_cd_id);


--
-- TOC entry 2059 (class 2606 OID 18845)
-- Dependencies: 1675 1675
-- Name: dset_cd_value3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value3
    ADD CONSTRAINT dset_cd_value3_pkey PRIMARY KEY (dset_cd_id);


--
-- TOC entry 2061 (class 2606 OID 18847)
-- Dependencies: 1676 1676
-- Name: dset_cd_value4_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value4
    ADD CONSTRAINT dset_cd_value4_pkey PRIMARY KEY (dset_cd_id);


--
-- TOC entry 2063 (class 2606 OID 18849)
-- Dependencies: 1677 1677
-- Name: dset_cd_value5_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value5
    ADD CONSTRAINT dset_cd_value5_pkey PRIMARY KEY (dset_cd_id);


--
-- TOC entry 2065 (class 2606 OID 18851)
-- Dependencies: 1678 1678
-- Name: dset_cd_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value6
    ADD CONSTRAINT dset_cd_value6_pkey PRIMARY KEY (id);


--
-- TOC entry 2068 (class 2606 OID 18853)
-- Dependencies: 1679 1679
-- Name: dset_cd_value7_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value7
    ADD CONSTRAINT dset_cd_value7_pkey PRIMARY KEY (id);


--
-- TOC entry 2070 (class 2606 OID 18855)
-- Dependencies: 1680 1680
-- Name: dset_en_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en
    ADD CONSTRAINT dset_en_pkey PRIMARY KEY (id);


--
-- TOC entry 2072 (class 2606 OID 18857)
-- Dependencies: 1681 1681
-- Name: dset_en_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value1
    ADD CONSTRAINT dset_en_value1_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2074 (class 2606 OID 18859)
-- Dependencies: 1682 1682
-- Name: dset_en_value2_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value2
    ADD CONSTRAINT dset_en_value2_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2076 (class 2606 OID 18861)
-- Dependencies: 1683 1683
-- Name: dset_en_value3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value3
    ADD CONSTRAINT dset_en_value3_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2078 (class 2606 OID 18863)
-- Dependencies: 1684 1684
-- Name: dset_en_value4_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value4
    ADD CONSTRAINT dset_en_value4_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2080 (class 2606 OID 18865)
-- Dependencies: 1685 1685
-- Name: dset_en_value5_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value5
    ADD CONSTRAINT dset_en_value5_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2082 (class 2606 OID 18867)
-- Dependencies: 1686 1686
-- Name: dset_en_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value6
    ADD CONSTRAINT dset_en_value6_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2084 (class 2606 OID 18869)
-- Dependencies: 1687 1687
-- Name: dset_en_value7_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value7
    ADD CONSTRAINT dset_en_value7_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2088 (class 2606 OID 18873)
-- Dependencies: 1689 1689 1689
-- Name: dset_ii_ii_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_ii_value6
    ADD CONSTRAINT dset_ii_ii_value6_pkey PRIMARY KEY (dset_ii_id, dset_ii_value6_id);


--
-- TOC entry 2086 (class 2606 OID 18871)
-- Dependencies: 1688 1688
-- Name: dset_ii_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii
    ADD CONSTRAINT dset_ii_pkey PRIMARY KEY (id);


--
-- TOC entry 2091 (class 2606 OID 18875)
-- Dependencies: 1690 1690
-- Name: dset_ii_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value1
    ADD CONSTRAINT dset_ii_value1_pkey PRIMARY KEY (dset_ii_id);


--
-- TOC entry 2093 (class 2606 OID 18877)
-- Dependencies: 1691 1691
-- Name: dset_ii_value2_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value2
    ADD CONSTRAINT dset_ii_value2_pkey PRIMARY KEY (dset_ii_id);


--
-- TOC entry 2095 (class 2606 OID 18879)
-- Dependencies: 1692 1692
-- Name: dset_ii_value3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value3
    ADD CONSTRAINT dset_ii_value3_pkey PRIMARY KEY (dset_ii_id);


--
-- TOC entry 2097 (class 2606 OID 18881)
-- Dependencies: 1693 1693
-- Name: dset_ii_value4_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value4
    ADD CONSTRAINT dset_ii_value4_pkey PRIMARY KEY (dset_ii_id);


--
-- TOC entry 2099 (class 2606 OID 18883)
-- Dependencies: 1694 1694
-- Name: dset_ii_value5_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value5
    ADD CONSTRAINT dset_ii_value5_pkey PRIMARY KEY (id);


--
-- TOC entry 2102 (class 2606 OID 18885)
-- Dependencies: 1695 1695
-- Name: dset_ii_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value6
    ADD CONSTRAINT dset_ii_value6_pkey PRIMARY KEY (id);


--
-- TOC entry 2106 (class 2606 OID 18889)
-- Dependencies: 1697 1697
-- Name: dset_tel_email_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_email
    ADD CONSTRAINT dset_tel_email_pkey PRIMARY KEY (id);


--
-- TOC entry 2108 (class 2606 OID 18891)
-- Dependencies: 1698 1698
-- Name: dset_tel_email_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_email_value1
    ADD CONSTRAINT dset_tel_email_value1_pkey PRIMARY KEY (dset_tel_email_id);


--
-- TOC entry 2111 (class 2606 OID 18893)
-- Dependencies: 1700 1700
-- Name: dset_tel_person_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_person
    ADD CONSTRAINT dset_tel_person_pkey PRIMARY KEY (id);


--
-- TOC entry 2113 (class 2606 OID 18895)
-- Dependencies: 1701 1701
-- Name: dset_tel_person_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_person_value1
    ADD CONSTRAINT dset_tel_person_value1_pkey PRIMARY KEY (dset_tel_person_id);


--
-- TOC entry 2115 (class 2606 OID 18897)
-- Dependencies: 1702 1702
-- Name: dset_tel_phone_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_phone
    ADD CONSTRAINT dset_tel_phone_pkey PRIMARY KEY (id);


--
-- TOC entry 2117 (class 2606 OID 18899)
-- Dependencies: 1703 1703
-- Name: dset_tel_phone_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_phone_value1
    ADD CONSTRAINT dset_tel_phone_value1_pkey PRIMARY KEY (dset_tel_phone_id);


--
-- TOC entry 2104 (class 2606 OID 18887)
-- Dependencies: 1696 1696
-- Name: dset_tel_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel
    ADD CONSTRAINT dset_tel_pkey PRIMARY KEY (id);


--
-- TOC entry 2119 (class 2606 OID 18901)
-- Dependencies: 1704 1704 1704
-- Name: dset_tel_tel_value_3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_tel_value_3
    ADD CONSTRAINT dset_tel_tel_value_3_pkey PRIMARY KEY (dset_tel_id, dset_tel_value3_id);


--
-- TOC entry 2122 (class 2606 OID 18903)
-- Dependencies: 1705 1705
-- Name: dset_tel_url_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_url
    ADD CONSTRAINT dset_tel_url_pkey PRIMARY KEY (id);


--
-- TOC entry 2124 (class 2606 OID 18905)
-- Dependencies: 1706 1706
-- Name: dset_tel_url_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_url_value1
    ADD CONSTRAINT dset_tel_url_value1_pkey PRIMARY KEY (dset_tel_url_id);


--
-- TOC entry 2126 (class 2606 OID 18907)
-- Dependencies: 1707 1707
-- Name: dset_tel_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_value1
    ADD CONSTRAINT dset_tel_value1_pkey PRIMARY KEY (dset_tel_id);


--
-- TOC entry 2128 (class 2606 OID 18909)
-- Dependencies: 1708 1708
-- Name: dset_tel_value2_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_value2
    ADD CONSTRAINT dset_tel_value2_pkey PRIMARY KEY (id);


--
-- TOC entry 2131 (class 2606 OID 18911)
-- Dependencies: 1709 1709
-- Name: dset_tel_value3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_value3
    ADD CONSTRAINT dset_tel_value3_pkey PRIMARY KEY (id);


--
-- TOC entry 2133 (class 2606 OID 18913)
-- Dependencies: 1710 1710
-- Name: ed_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ed_datatype
    ADD CONSTRAINT ed_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2135 (class 2606 OID 18915)
-- Dependencies: 1711 1711
-- Name: ed_text_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ed_text_datatype
    ADD CONSTRAINT ed_text_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2137 (class 2606 OID 18917)
-- Dependencies: 1712 1712
-- Name: en_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY en_datatype
    ADD CONSTRAINT en_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2139 (class 2606 OID 18919)
-- Dependencies: 1713 1713
-- Name: en_on_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY en_on_datatype
    ADD CONSTRAINT en_on_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2141 (class 2606 OID 18921)
-- Dependencies: 1714 1714
-- Name: en_pn_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY en_pn_datatype
    ADD CONSTRAINT en_pn_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2143 (class 2606 OID 18923)
-- Dependencies: 1715 1715
-- Name: ii_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ii_datatype
    ADD CONSTRAINT ii_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2145 (class 2606 OID 18925)
-- Dependencies: 1716 1716
-- Name: int_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY int_datatype
    ADD CONSTRAINT int_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2147 (class 2606 OID 18927)
-- Dependencies: 1717 1717
-- Name: ivl_int_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ivl_int
    ADD CONSTRAINT ivl_int_pkey PRIMARY KEY (id);


--
-- TOC entry 2149 (class 2606 OID 18929)
-- Dependencies: 1718 1718
-- Name: ivl_pq_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ivl_pq
    ADD CONSTRAINT ivl_pq_pkey PRIMARY KEY (id);


--
-- TOC entry 2151 (class 2606 OID 18931)
-- Dependencies: 1719 1719
-- Name: ivl_pqv_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ivl_pqv
    ADD CONSTRAINT ivl_pqv_pkey PRIMARY KEY (id);


--
-- TOC entry 2153 (class 2606 OID 18933)
-- Dependencies: 1722 1722
-- Name: pq_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY pq_datatype
    ADD CONSTRAINT pq_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2155 (class 2606 OID 18935)
-- Dependencies: 1723 1723
-- Name: pqv_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY pqv_datatype
    ADD CONSTRAINT pqv_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2157 (class 2606 OID 18937)
-- Dependencies: 1724 1724
-- Name: real_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY real_datatype
    ADD CONSTRAINT real_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2159 (class 2606 OID 18939)
-- Dependencies: 1725 1725
-- Name: sc_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY sc_datatype
    ADD CONSTRAINT sc_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2161 (class 2606 OID 18941)
-- Dependencies: 1726 1726
-- Name: st_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY st_datatype
    ADD CONSTRAINT st_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2163 (class 2606 OID 18943)
-- Dependencies: 1727 1727
-- Name: st_nt_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY st_nt_datatype
    ADD CONSTRAINT st_nt_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2165 (class 2606 OID 18945)
-- Dependencies: 1728 1728
-- Name: tel_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY tel_datatype
    ADD CONSTRAINT tel_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2167 (class 2606 OID 18947)
-- Dependencies: 1729 1729
-- Name: tel_email_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY tel_email_datatype
    ADD CONSTRAINT tel_email_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2169 (class 2606 OID 18949)
-- Dependencies: 1730 1730
-- Name: tel_person_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY tel_person_datatype
    ADD CONSTRAINT tel_person_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2171 (class 2606 OID 18951)
-- Dependencies: 1731 1731
-- Name: tel_phone_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY tel_phone_datatype
    ADD CONSTRAINT tel_phone_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2173 (class 2606 OID 18953)
-- Dependencies: 1732 1732
-- Name: tel_url_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY tel_url_datatype
    ADD CONSTRAINT tel_url_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2175 (class 2606 OID 18955)
-- Dependencies: 1733 1733
-- Name: ts_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ts_datatype
    ADD CONSTRAINT ts_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2002 (class 1259 OID 19191)
-- Dependencies: 1649
-- Name: fk_ad_ad_datatype__ad_datatype1; Type: INDEX 
--

CREATE INDEX fk_ad_ad_datatype__ad_datatype1 ON ad_ad_datatype_value9 USING btree (ad_datatype_id);


--
-- TOC entry 2006 (class 1259 OID 19193)
-- Dependencies: 1651
-- Name: fk_ad_datatype_val_ad_datatype1; Type: INDEX 
--

CREATE INDEX fk_ad_datatype_val_ad_datatype1 ON ad_datatype_value7 USING btree (ad_datatype_id);


--
-- TOC entry 2005 (class 1259 OID 19192)
-- Dependencies: 1650
-- Name: fk_ad_datatype_value81; Type: INDEX 
--

CREATE INDEX fk_ad_datatype_value81 ON ad_datatype USING btree (ad_datatype_value8_id);


--
-- TOC entry 2017 (class 1259 OID 19194)
-- Dependencies: 1656
-- Name: fk_cd_cd_datatype__cd_datatype1; Type: INDEX 
--

CREATE INDEX fk_cd_cd_datatype__cd_datatype1 ON cd_cd_datatype_value8 USING btree (cd_data_type_id);


--
-- TOC entry 2020 (class 1259 OID 19195)
-- Dependencies: 1657
-- Name: fk_cd_datatype_value71; Type: INDEX 
--

CREATE INDEX fk_cd_datatype_value71 ON cd_datatype USING btree (cd_datatype_value7_id);


--
-- TOC entry 2031 (class 1259 OID 19196)
-- Dependencies: 1662
-- Name: fk_dser_ad_dset__dset_ad_data1; Type: INDEX 
--

CREATE INDEX fk_dser_ad_dset__dset_ad_data1 ON dset_ad_dset_ad_value8 USING btree (dset_ad_datatype_id);


--
-- TOC entry 2046 (class 1259 OID 19197)
-- Dependencies: 1669
-- Name: fk_dset_ad_value_dset_ad71; Type: INDEX 
--

CREATE INDEX fk_dset_ad_value_dset_ad71 ON dset_ad_value7 USING btree (dset_ad_datatype_id);


--
-- TOC entry 2053 (class 1259 OID 19198)
-- Dependencies: 1672
-- Name: fk_dset_cd_cd_va_dset_cd51; Type: INDEX 
--

CREATE INDEX fk_dset_cd_cd_va_dset_cd51 ON dset_cd_cd_value7 USING btree (dset_cd_value7_id);


--
-- TOC entry 2066 (class 1259 OID 19199)
-- Dependencies: 1678
-- Name: fk_dset_cd_value6_dset_cd1; Type: INDEX 
--

CREATE INDEX fk_dset_cd_value6_dset_cd1 ON dset_cd_value6 USING btree (dset_cd_id);


--
-- TOC entry 2089 (class 1259 OID 19200)
-- Dependencies: 1689
-- Name: fk_dset_ii_ii_value6_dset_ii1; Type: INDEX 
--

CREATE INDEX fk_dset_ii_ii_value6_dset_ii1 ON dset_ii_ii_value6 USING btree (dset_ii_id);


--
-- TOC entry 2100 (class 1259 OID 19201)
-- Dependencies: 1694
-- Name: fk_dset_ii_value5_dset_ii1; Type: INDEX 
--

CREATE INDEX fk_dset_ii_value5_dset_ii1 ON dset_ii_value5 USING btree (dset_ii_id);


--
-- TOC entry 2120 (class 1259 OID 19203)
-- Dependencies: 1704
-- Name: fk_dset_tel_tel__dset_tel_val1; Type: INDEX 
--

CREATE INDEX fk_dset_tel_tel__dset_tel_val1 ON dset_tel_tel_value_3 USING btree (dset_tel_value3_id);


--
-- TOC entry 2129 (class 1259 OID 19204)
-- Dependencies: 1708
-- Name: fk_dset_tel_value2_dset_tel1; Type: INDEX 
--

CREATE INDEX fk_dset_tel_value2_dset_tel1 ON dset_tel_value2 USING btree (dset_tel_id);


--
-- TOC entry 2109 (class 1259 OID 19202)
-- Dependencies: 1699
-- Name: fk_tel_email_val_dset_tel_ema1; Type: INDEX 
--

CREATE INDEX fk_tel_email_val_dset_tel_ema1 ON dset_tel_email_value2 USING btree (dset_tel_email_id);


--
-- TOC entry 2177 (class 2606 OID 18961)
-- Dependencies: 2009 1653 1649
-- Name: fk_ad_ad_datatyp_ad_datatype_v; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY ad_ad_datatype_value9
    ADD CONSTRAINT fk_ad_ad_datatyp_ad_datatype_v FOREIGN KEY (ad_datatype_value9_id) REFERENCES ad_datatype_value9(id) MATCH FULL;


--
-- TOC entry 2176 (class 2606 OID 18956)
-- Dependencies: 2003 1649 1650
-- Name: fk_ad_ad_datatype__ad_datatype; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY ad_ad_datatype_value9
    ADD CONSTRAINT fk_ad_ad_datatype__ad_datatype FOREIGN KEY (ad_datatype_id) REFERENCES ad_datatype(id) MATCH FULL;


--
-- TOC entry 2179 (class 2606 OID 18971)
-- Dependencies: 1651 2003 1650
-- Name: fk_ad_datatype_val_ad_datatype; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY ad_datatype_value7
    ADD CONSTRAINT fk_ad_datatype_val_ad_datatype FOREIGN KEY (ad_datatype_id) REFERENCES ad_datatype(id) MATCH FULL;


--
-- TOC entry 2178 (class 2606 OID 18966)
-- Dependencies: 2007 1652 1650
-- Name: fk_ad_datatype_value8; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY ad_datatype
    ADD CONSTRAINT fk_ad_datatype_value8 FOREIGN KEY (ad_datatype_value8_id) REFERENCES ad_datatype_value8(id) MATCH FULL;


--
-- TOC entry 2181 (class 2606 OID 18981)
-- Dependencies: 1660 2025 1656
-- Name: fk_cd_cd_datatyp_cd_datatype_v; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY cd_cd_datatype_value8
    ADD CONSTRAINT fk_cd_cd_datatyp_cd_datatype_v FOREIGN KEY (cd_datatype_value8_id) REFERENCES cd_datatype_value8(id) MATCH FULL;


--
-- TOC entry 2180 (class 2606 OID 18976)
-- Dependencies: 1657 2018 1656
-- Name: fk_cd_cd_datatype__cd_datatype; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY cd_cd_datatype_value8
    ADD CONSTRAINT fk_cd_cd_datatype__cd_datatype FOREIGN KEY (cd_data_type_id) REFERENCES cd_datatype(id) MATCH FULL;


--
-- TOC entry 2183 (class 2606 OID 18991)
-- Dependencies: 1658 2018 1657
-- Name: fk_cd_datatype_val_cd_datatype; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY cd_datatype_value6
    ADD CONSTRAINT fk_cd_datatype_val_cd_datatype FOREIGN KEY (cd_datatype_id) REFERENCES cd_datatype(id) MATCH FULL;


--
-- TOC entry 2182 (class 2606 OID 18986)
-- Dependencies: 2023 1657 1659
-- Name: fk_cd_datatype_value7; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY cd_datatype
    ADD CONSTRAINT fk_cd_datatype_value7 FOREIGN KEY (cd_datatype_value7_id) REFERENCES cd_datatype_value7(id) MATCH FULL;


--
-- TOC entry 2188 (class 2606 OID 19016)
-- Dependencies: 2027 1661 1665
-- Name: fk_dser_ad_datat_val_ad3; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value3
    ADD CONSTRAINT fk_dser_ad_datat_val_ad3 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2190 (class 2606 OID 19026)
-- Dependencies: 1667 2027 1661
-- Name: fk_dser_ad_datat_val_ad5; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value5
    ADD CONSTRAINT fk_dser_ad_datat_val_ad5 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2187 (class 2606 OID 19011)
-- Dependencies: 1664 1661 2027
-- Name: fk_dser_ad_datat_val_ad_datat; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value2
    ADD CONSTRAINT fk_dser_ad_datat_val_ad_datat FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2186 (class 2606 OID 19006)
-- Dependencies: 1663 2027 1661
-- Name: fk_dser_ad_dataty_val_ad_da; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value1
    ADD CONSTRAINT fk_dser_ad_dataty_val_ad_da FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2184 (class 2606 OID 18996)
-- Dependencies: 2027 1661 1662
-- Name: fk_dser_ad_dset__dset_ad_data; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_dset_ad_value8
    ADD CONSTRAINT fk_dser_ad_dset__dset_ad_data FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2185 (class 2606 OID 19001)
-- Dependencies: 1670 2047 1662
-- Name: fk_dser_ad_dset__dset_ad_valu; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_dset_ad_value8
    ADD CONSTRAINT fk_dser_ad_dset__dset_ad_valu FOREIGN KEY (dset_ad_value8_id) REFERENCES dset_ad_value8(id) MATCH FULL;


--
-- TOC entry 2189 (class 2606 OID 19021)
-- Dependencies: 1666 2027 1661
-- Name: fk_dser_ad_type_val_ad4; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value4
    ADD CONSTRAINT fk_dser_ad_type_val_ad4 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2191 (class 2606 OID 19031)
-- Dependencies: 2027 1668 1661
-- Name: fk_dser_ad_type_val_ad6; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value6
    ADD CONSTRAINT fk_dser_ad_type_val_ad6 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2192 (class 2606 OID 19036)
-- Dependencies: 2027 1661 1669
-- Name: fk_dset_ad_value_dset_ad7; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value7
    ADD CONSTRAINT fk_dset_ad_value_dset_ad7 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2194 (class 2606 OID 19046)
-- Dependencies: 1679 1672 2067
-- Name: fk_dset_cd_cd_va_dset_cd5; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_cd_value7
    ADD CONSTRAINT fk_dset_cd_cd_va_dset_cd5 FOREIGN KEY (dset_cd_value7_id) REFERENCES dset_cd_value7(id) MATCH FULL;


--
-- TOC entry 2193 (class 2606 OID 19041)
-- Dependencies: 1671 1672 2049
-- Name: fk_dset_cd_cd_value7_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_cd_value7
    ADD CONSTRAINT fk_dset_cd_cd_value7_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2195 (class 2606 OID 19051)
-- Dependencies: 1673 2049 1671
-- Name: fk_dset_cd_value1_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value1
    ADD CONSTRAINT fk_dset_cd_value1_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2196 (class 2606 OID 19056)
-- Dependencies: 2049 1674 1671
-- Name: fk_dset_cd_value2_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value2
    ADD CONSTRAINT fk_dset_cd_value2_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2197 (class 2606 OID 19061)
-- Dependencies: 1675 2049 1671
-- Name: fk_dset_cd_value3_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value3
    ADD CONSTRAINT fk_dset_cd_value3_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2198 (class 2606 OID 19066)
-- Dependencies: 1671 1676 2049
-- Name: fk_dset_cd_value4_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value4
    ADD CONSTRAINT fk_dset_cd_value4_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2199 (class 2606 OID 19071)
-- Dependencies: 1677 2049 1671
-- Name: fk_dset_cd_value5_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value5
    ADD CONSTRAINT fk_dset_cd_value5_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2200 (class 2606 OID 19076)
-- Dependencies: 2049 1671 1678
-- Name: fk_dset_cd_value6_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value6
    ADD CONSTRAINT fk_dset_cd_value6_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2208 (class 2606 OID 19116)
-- Dependencies: 2101 1689 1695
-- Name: fk_dset_ii_ii_va_dset_ii_val; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_ii_value6
    ADD CONSTRAINT fk_dset_ii_ii_va_dset_ii_val FOREIGN KEY (dset_ii_value6_id) REFERENCES dset_ii_value6(id) MATCH FULL;


--
-- TOC entry 2207 (class 2606 OID 19111)
-- Dependencies: 1689 1688 2085
-- Name: fk_dset_ii_ii_value6_dset_ii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_ii_value6
    ADD CONSTRAINT fk_dset_ii_ii_value6_dset_ii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2209 (class 2606 OID 19121)
-- Dependencies: 1690 1688 2085
-- Name: fk_dset_ii_value1_dsetii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_value1
    ADD CONSTRAINT fk_dset_ii_value1_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2210 (class 2606 OID 19126)
-- Dependencies: 2085 1691 1688
-- Name: fk_dset_ii_value2_dsetii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_value2
    ADD CONSTRAINT fk_dset_ii_value2_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2211 (class 2606 OID 19131)
-- Dependencies: 2085 1692 1688
-- Name: fk_dset_ii_value3_dsetii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_value3
    ADD CONSTRAINT fk_dset_ii_value3_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2212 (class 2606 OID 19136)
-- Dependencies: 2085 1688 1693
-- Name: fk_dset_ii_value4_dsetii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_value4
    ADD CONSTRAINT fk_dset_ii_value4_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2213 (class 2606 OID 19141)
-- Dependencies: 1688 1694 2085
-- Name: fk_dset_ii_value5_dset_ii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_value5
    ADD CONSTRAINT fk_dset_ii_value5_dset_ii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2214 (class 2606 OID 19146)
-- Dependencies: 2105 1697 1698
-- Name: fk_dset_tel_emai_dset_tel_ema; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_email_value1
    ADD CONSTRAINT fk_dset_tel_emai_dset_tel_ema FOREIGN KEY (dset_tel_email_id) REFERENCES dset_tel_email(id) MATCH FULL;


--
-- TOC entry 2216 (class 2606 OID 19156)
-- Dependencies: 1700 1701 2110
-- Name: fk_dset_tel_pers_dset_tel_per; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_person_value1
    ADD CONSTRAINT fk_dset_tel_pers_dset_tel_per FOREIGN KEY (dset_tel_person_id) REFERENCES dset_tel_person(id) MATCH FULL;


--
-- TOC entry 2217 (class 2606 OID 19161)
-- Dependencies: 2114 1703 1702
-- Name: fk_dset_tel_phon_dset_tel_pho; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_phone_value1
    ADD CONSTRAINT fk_dset_tel_phon_dset_tel_pho FOREIGN KEY (dset_tel_phone_id) REFERENCES dset_tel_phone(id) MATCH FULL;


--
-- TOC entry 2219 (class 2606 OID 19171)
-- Dependencies: 2130 1704 1709
-- Name: fk_dset_tel_tel__dset_tel_val; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_tel_value_3
    ADD CONSTRAINT fk_dset_tel_tel__dset_tel_val FOREIGN KEY (dset_tel_value3_id) REFERENCES dset_tel_value3(id) MATCH FULL;


--
-- Name: fk_dset_tel_tel_valu_dset_tel; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_tel_value_3
    ADD CONSTRAINT fk_dset_tel_tel_valu_dset_tel FOREIGN KEY (dset_tel_id) REFERENCES dset_tel(id) MATCH FULL;


--
-- Name: fk_dset_tel_url_v_dset_tel_url; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_url_value1
    ADD CONSTRAINT fk_dset_tel_url_v_dset_tel_url FOREIGN KEY (dset_tel_url_id) REFERENCES dset_tel_url(id) MATCH FULL;


--
-- Name: fk_dset_tel_value1_dset_tel; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_value1
    ADD CONSTRAINT fk_dset_tel_value1_dset_tel FOREIGN KEY (dset_tel_id) REFERENCES dset_tel(id) MATCH FULL;


--
-- Name: fk_dset_tel_value2_dset_tel; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_value2
    ADD CONSTRAINT fk_dset_tel_value2_dset_tel FOREIGN KEY (dset_tel_id) REFERENCES dset_tel(id) MATCH FULL;


--
-- Name: fk_en_datatype_val4_en_datat; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value4
    ADD CONSTRAINT fk_en_datatype_val4_en_datat FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_en_datatype_val6_en_datat; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value6
    ADD CONSTRAINT fk_en_datatype_val6_en_datat FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_en_datatype_val7_en_datat; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value7
    ADD CONSTRAINT fk_en_datatype_val7_en_datat FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_en_type_val2_en_type; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value2
    ADD CONSTRAINT fk_en_type_val2_en_type FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_en_type_val3_en_type; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value3
    ADD CONSTRAINT fk_en_type_val3_en_type FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_en_type_val_en_type; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value1
    ADD CONSTRAINT fk_en_type_val_en_type FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_tel_email_val_dset_tel_ema; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_email_value2
    ADD CONSTRAINT fk_tel_email_val_dset_tel_ema FOREIGN KEY (dset_tel_email_id) REFERENCES dset_tel_email(id) MATCH FULL;

COMMIT;
