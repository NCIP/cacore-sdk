-- drop the existing database
drop database cacoresdk;

-- create the test user
create user test password 'test';

-- create the database
create database cacoresdk owner test;
