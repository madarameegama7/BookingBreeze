package com.example.models;

import java.math.BigDecimal;
import java.sql.Date;

public class Payment {
    private int paymentID;
    private int userID;
    private int reservationID;
    private double payment;
    private String paymentType;
    private Date paymentDate;
    private String paymentStatus;

    public Payment(int paymentID, int userID, int reservationID, double payment, String paymentType, Date paymentDate, String paymentStatus) {
        this.paymentID = paymentID;
        this.userID = userID;
        this.reservationID = reservationID;
        this.payment = payment;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}