CREATE DATABASE Greenhouse;
USE Greenhouse;
CREATE TABLE Device (
    DeviceID INT PRIMARY KEY AUTO_INCREMENT,
    Location VARCHAR(255)
);

-- Create Sensor Data Table
CREATE TABLE SensorData (
    ReadingID INT PRIMARY KEY AUTO_INCREMENT,
    DeviceID INT,
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    AirHumidity FLOAT,
    Temperature FLOAT,
    SoilHumidity FLOAT,
    
    FOREIGN KEY (DeviceID) REFERENCES Device(DeviceID)
);

-- Create User Table
CREATE TABLE User (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(255),
    Password VARCHAR(255), 
    Email VARCHAR(255),
    Role ENUM('admin', 'user') 
   
);

-- Create Greenhouse Configuration Table
CREATE TABLE GreenhouseConfiguration (
    ConfigurationID INT PRIMARY KEY AUTO_INCREMENT,
    ConfigurationName VARCHAR(255),
    Value VARCHAR(255), 
    Description TEXT,
    
    UserID INT,
    DeviceID INT, 
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (DeviceID) REFERENCES Device(DeviceID)
);

-- Create Log Table
CREATE TABLE Log (
    LogID INT PRIMARY KEY AUTO_INCREMENT,
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LogType ENUM('info', 'warning', 'error'),
    LogMessage TEXT,
    UserID INT, 
   
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);



