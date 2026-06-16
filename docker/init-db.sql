CREATE DATABASE IF NOT EXISTS webapp_login;
USE webapp_login;

CREATE TABLE IF NOT EXISTS credentials (
    username VARCHAR(255) NOT NULL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
