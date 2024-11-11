package com.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BookingConfirmationController {

    @FXML
    private TextField reservationIDField;

    public void setReservationID(int reservationID) {
        reservationIDField.setText(String.valueOf(reservationID));
    }
}
