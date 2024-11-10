package com.example.controllers;

import com.example.models.Payment;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.Date;

public class PaymentConfirmationController {

    @FXML
    private TextField userID;
    @FXML
    private TextField reservationID;
    @FXML
    private TextField paymentIDField;
    @FXML
    private TextField paymentType;
    @FXML
    private TextField paymentDate;
    @FXML
    private TextField amount;

    private Payment currentPayment;

    // Method to populate the payment details
    public void setPaymentData(Payment payment) {
        this.currentPayment = payment;
        this.paymentIDField.setText(String.valueOf(payment.getPaymentID()));
        this.userID.setText(String.valueOf(payment.getUserID()));
        this.reservationID.setText(String.valueOf(payment.getReservationID()));
        this.paymentType.setText(payment.getPaymentType());
        this.paymentDate.setText(String.valueOf(payment.getPaymentDate())); // Handle null date gracefullyary
        this.amount.setText(String.valueOf(payment.getPayment()));

        // You would set paymentID dynamically if it's auto-generated from the database after insertion
        // Assuming we do this in the PaymentService or after inserting payment record.
        // this.paymentID.setText(String.valueOf(paymentID));
    }
}

