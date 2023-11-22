DROP TABLE IF EXISTS user_cinema;

CREATE TABLE IF NOT EXISTS user_cinema (
    ID_User INTEGER AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Age INT NOT NULL,
    Username VARCHAR(100) NOT NULL,
    Password VARCHAR(250) NOT NULL,
    Account VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS worker;

CREATE TABLE IF NOT EXISTS worker (
    ID_Worker INTEGER AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Register_Date DATE NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Username VARCHAR(100) NOT NULL,
    Password VARCHAR(250) NOT NULL,
    Worker_Type VARCHAR(20) NOT NULL
);


