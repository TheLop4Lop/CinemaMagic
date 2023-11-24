INSERT INTO user_cinema (Name, Email, Age, Username, Password, Account) VALUES ('Pedro Martinez','pedro@gmail.com',26,'PeterMtz','Password123','PREMIUM')
INSERT INTO user_cinema (Name, Email, Age, Username, Password, Account) VALUES ('Carlos Rodriguez','carlos@gmail.com',56,'CarlosRod','Password123','REGULAR')
INSERT INTO user_cinema (Name, Email, Age, Username, Password, Account) VALUES ('Julia Cañedo','julia@gmail.com',32,'JulyCanedo','Password123','PREMIUM')

INSERT INTO worker (Name, Register_Date, Email, Username, Password, Worker_Type) VALUES ('Rodrigo Ibarra',2020,'rodrigo@magic.com','RodrigoW12','Password123','ADMIN')
INSERT INTO worker (Name, Register_Date, Email, Username, Password, Worker_Type) VALUES ('Alejandro Carrillo',2022,'carillo@magic.com','AlejandroW12','Password123','REGULAR')
INSERT INTO worker (Name, Register_Date, Email, Username, Password, Worker_Type) VALUES ('Cristina Alvarez',2019,'cristina@magic.com','CristinaW12','Password1253','ADMIN')

INSERT INTO movies (Title, Duration, Country, Category, Classification, Rating, Synopsis, Movie_Language, Release_Year, Director, Format, Type, Watched, Available) VALUES ('The Lord Of The Rings: The Fellowship of the Ring','178 minutes','United Kingdom','Adventure','PG-13',4.8,'An epic story based on the book written by J.R.R. Tolkien.','English',2001,'Peter Jackson','SUBTITLE','PREMIUM',786, true)
INSERT INTO movies (Title, Duration, Country, Category, Classification, Rating, Synopsis, Movie_Language, Release_Year, Director, Format, Type, Watched, Available) VALUES ('John Wick','169 minutes','United States','Action','R-15',4.3,'An international assassin comes back from retirement','English',2014,'Chad Stahelski','DUBBED','REGULAR',500, false)
INSERT INTO movies (Title, Duration, Country, Category, Classification, Rating, Synopsis, Movie_Language, Release_Year, Director, Format, Type, Watched, Available) VALUES ('Pastorela','90 minutes','Mexico','Comedy','PG-13',4.0,'A man wants to be El Diablo in a little town','Spanish',2011,'Emilio Portes','SUBTITLE','REGULAR',135, false)

INSERT INTO projection (ID_Movie, Room, Projection_Hour) VALUES (1, '2A', '16:00')