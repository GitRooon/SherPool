DROP SCHEMA SherPool;
CREATE SCHEMA SherPool;

USE SherPool;

DROP TABLE ScheduleDetails;
DROP TABLE Schedules;

CREATE TABLE Users (
    StudentID INTEGER NOT NULL,
    Password VARCHAR(15) NOT NULL,
    FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(25) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Phone BIGINT(10),
    DateRegistered TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Driver BOOLEAN DEFAULT FALSE,
    CONSTRAINT Users_PK PRIMARY KEY (StudentID)
);

CREATE TABLE Cars (
    PlateNumber VARCHAR(10) NOT NULL,
    Make VARCHAR(20),
    Model VARCHAR(15),
    Seats TINYINT NOT NULL,
    DateRegistered TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Owner INTEGER NOT NULL,
    CONSTRAINT Cars_PK PRIMARY KEY (PlateNumber),
    CONSTRAINT Cars_FK1 FOREIGN KEY (Owner)
        REFERENCES Users (StudentID)
);

CREATE TABLE Schedules (
    ScheduleNO INT NOT NULL AUTO_INCREMENT,
    CampusSource VARCHAR(10) NOT NULL,
    CampusDestination VARCHAR(10) NOT NULL,
    DateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ExpiryDate DATETIME NOT NULL,
    PlateNumber VARCHAR(10) NOT NULL,
    CONSTRAINT Schedules_PK PRIMARY KEY (ScheduleNO),
    CONSTRAINT Schedules_FK1 FOREIGN KEY (PlateNumber)
        REFERENCES Cars (PlateNumber)
);

CREATE TABLE ScheduleDetails (
    ScheduleNO INTEGER NOT NULL,
    Rider INTEGER NOT NULL,
    CONSTRAINT ScheduleDetails_FK1 FOREIGN KEY (ScheduleNO)
        REFERENCES Schedules (ScheduleNO),
    CONSTRAINT ScheduleDetails_FK2 FOREIGN KEY (Rider)
        REFERENCES Users (StudentID)
);

INSERT 
INTO 
	Users (StudentID,Password,FirstName,LastName,Email,Phone, Driver) 
VALUE 
	(1234, '1234', 'Admin', 'Try', 'admin@gmail.com', 2893001228, 1);
    
INSERT 
INTO 
	Cars (PlateNumber,Make,Model,Seats,Owner) 
VALUE 
	('ADMIN 123', 'Honda', 'Civic', 4, 1234);
    
INSERT 
INTO 
	Schedules (CampusSource,CampusDestination, ExpiryDate,PlateNumber) 
VALUE 
	('Trafalgar', 'Davis', '2018-12-31 20:00:00', 'ADMIN 123');
 --    
-- INSERT INTO
-- ScheduleDetails 
-- VALUES ();

-- USE SheridanCarPool;
-- SELECT 
--     *
-- FROM
--     Schedule;

SELECT 
    *
FROM
    ScheduleDetails;
--     
-- SELECT 
--     *
-- FROM
--     Cars;

SELECT 
    *
FROM
    Schedules;
    
SELECT 
    *
FROM
    Schedules s
        LEFT JOIN
    Cars c ON c.PlateNumber = s.PlateNumber
        LEFT JOIN
    Users u ON u.StudentID = c.Owner;
    
Select * from Users;


SELECT 
    *
FROM
    ScheduleDetails
        INNER JOIN
    Schedules ON Schedules.ScheduleNO = ScheduleDetails.ScheduleNO
        INNER JOIN
    Users ON Users.StudentID = ScheduleDetails.Rider
        LEFT JOIN
    Cars ON Cars.PlateNumber = Schedules.PlateNumber;
    
SELECT 
    *
FROM
    Schedules
        INNER JOIN
    Cars ON Cars.PlateNumber = Schedules.PlateNumber
        INNER JOIN
    Users ON Users.StudentID = Cars.Owner
    WHERE ExpiryDate >= CURRENT_TIMESTAMP;
    