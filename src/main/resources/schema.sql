CREATE TABLE IF NOT EXISTS user_cinema (
    ID_User INTEGER AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Age INT NOT NULL,
    Username VARCHAR(100) NOT NULL,
    Password VARCHAR(250) NOT NULL,
    Account VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS worker (
    ID_Worker INTEGER AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Register_Date INT NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Username VARCHAR(100) NOT NULL,
    Password VARCHAR(250) NOT NULL,
    Worker_Type VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS movies (
    ID_Movie INTEGER AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(100) NOT NULL,
    Duration VARCHAR NOT NULL,
    Country VARCHAR(50) NOT NULL,
    Category VARCHAR(50) NOT NULL,
    Classification VARCHAR(20) NOT NULL,
    Rating FLOAT NOT NULL,
    Synopsis VARCHAR(1000) NOT NULL,
    Movie_Language VARCHAR(50) NOT NULL,
    Release_Year INT NOT NULL,
    Director VARCHAR(100) NOT NULL,
    Format VARCHAR(20) NOT NULL,
    Type VARCHAR(20) NOT NULL,
    Watched INT NOT NULL
);

CREATE TABLE IF NOT EXISTS projection (
    ID_Projection INTEGER AUTO_INCREMENT PRIMARY KEY,
    ID_Movie INT NOT NULL,
    Room VARCHAR(10) NOT NULL,
    Projection_Hour VARCHAR(20) NOT NULL,

    FOREIGN KEY (ID_Movie) REFERENCES movies(ID_Movie) ON DELETE CASCADE
);
