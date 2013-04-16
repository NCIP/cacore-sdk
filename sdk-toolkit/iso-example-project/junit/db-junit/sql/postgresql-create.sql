/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
L*/

-- drop the existing database
drop database cacoresdk;

-- create the test user
create user test password 'test';

-- create the database
create database cacoresdk owner test;
