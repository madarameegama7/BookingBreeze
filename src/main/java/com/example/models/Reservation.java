package com.example.models;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

    private int reservationId;
    private int userId;
    private String name;
    private String hotelName;
    private String roomType;
    private int roomID;
    private int guestCount;
    private LocalDate date;
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    // Modify constructor to accept java.sql.Date
    public Reservation(int userId, String name, String hotelName, String roomType,
                       int roomID, int guestCount, LocalDate date, LocalTime arrivalTime, LocalTime departureTime) {
        this.userId = userId;
        this.name = name;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.roomID = roomID;
        this.guestCount = guestCount;
        this.date = date;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    // Default constructor
    public Reservation() {
    }

    // Getters and Setters (same as before)
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomID='" + roomID + '\'' +
                ", guestCount=" + guestCount +
                ", date=" + date +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
