#creating banking_db
CREATE DATABASE banking_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

#verify if banking_db exists
SHOW DATABASES LIKE 'banking_db';

#use banking_db
USE banking_db;

#confirm banking_db selected
SELECT DATABASE();



