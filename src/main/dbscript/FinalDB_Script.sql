-- Create the BookingBreeze database
CREATE DATABASE IF NOT EXISTS BookingBreeze;

USE BookingBreeze;
CREATE TABLE user (
    userID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    contactNumber VARCHAR(15),
    address VARCHAR(255),
    userRole ENUM('user', 'admin', 'manager') DEFAULT 'user'
);

CREATE TABLE feedback (
    feedbackID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT,
    username VARCHAR(100),
    email VARCHAR(100),
    feedback TEXT NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE hotel (
    hotelID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT,
    registrationNo VARCHAR(100),
    hotelName VARCHAR(255) NOT NULL,
    location VARCHAR(100),
    contactNum VARCHAR(15),
    facilities TEXT,
    hotelImages mediumblob,
    FOREIGN KEY (userID) REFERENCES user(userID)

);

CREATE TABLE reservation (
    reservationID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    hotelName VARCHAR(100) NOT NULL,
    roomType VARCHAR(50) NOT NULL,
    roomID INT,
    userID INT,
    count INT,
    date DATE NOT NULL,
    arrivalTime TIME,
    departureTime TIME,
    FOREIGN KEY (userID) REFERENCES user(userID)
);
CREATE TABLE payment (
    paymentID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT,
    reservationID INT,
    payment DECIMAL(10, 2) NOT NULL,
    paymentType ENUM('PayPal', 'Credit Card', 'Debit Card', 'Cash'),
    paymentDate DATE,
    paymentStatus VARCHAR(255),
    FOREIGN KEY (userID) REFERENCES user(userID),
    FOREIGN KEY (reservationID) REFERENCES reservation(reservationID)
);




