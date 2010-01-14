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
    BOOLEAN_VALUE character varying(1),
    CLOB_VALUE text,
    CHARACTER_VALUE character(1),
    LONG_VALUE numeric,
    DOUBLE_PRIMITIVE_VALUE numeric,
    INT_PRIMITIVE_VALUE integer,
    DATE_PRIMITIVE_VALUE timestamp without time zone,
    STRING_PRIMITIVE_VALUE character varying(50),
    FLOAT_PRIMITIVE_VALUE numeric,
    BOOLEAN_PRIMITIVE_VALUE character varying(1),
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
    NAME character varying(50)
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

INSERT INTO album (ID, TITLE) VALUES (1.0, 'Venetian Oboe Concertos');
INSERT INTO album (ID, TITLE) VALUES (2.0, 'The Cello');

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

INSERT INTO hi_value (NEXT_VALUE) VALUES (8.0);

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

INSERT INTO long_primitive_key (ID, NAME) VALUES (987654321234567940, 'Long_Primitive_Key_NAME 987654321234567890');

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
