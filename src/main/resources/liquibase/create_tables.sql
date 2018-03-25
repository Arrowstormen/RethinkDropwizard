--liquibase formatted sql
--changeset cn:release_1.create_tables.sql

/* This script creates the postgressql schmeas, loaded in as a liquibase changeSet in the db.changelog.xml */

CREATE TABLE Donor
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100),
    address      VARCHAR(100),
    contactName  VARCHAR(100),
    contactPhone INT
);

CREATE TABLE Chef
(
    id serial primary key,
    name varchar(100)
);

CREATE TABLE Driver
(
    id serial primary key,
    name varchar(100)
);

CREATE TABLE Pickup_Information
(
    id serial Primary key,
    driver integer references Driver (id),
    pickup_time date,
    donor_estimated_food_value money,
    quarts int
);

CREATE TABLE Chef_Information
(
    id serial primary key,
    chef integer references Chef (id)
);

CREATE TABLE Food_item
(
    id serial primary key,
    chef_info integer references Chef_Information (id),
    name varchar(100),
    quarts int,
    chef_determined_value money,
    processing_reduction int,
    nutritional_macro int,
    labor_time int

);

CREATE TABLE Donation
(
    id serial primary key,
    donor integer references Donor (id),
    pickup_info integer references Pickup_Information (id),
    chef_info integer references Chef_Information (id)
);