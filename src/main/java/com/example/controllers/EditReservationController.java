package com.example.controllers;

import com.example.backend.HotelServices;
import com.example.backend.ReservationService;
import com.example.models.Hotel;
import com.example.models.Reservation;
//import com.google.protobuf.StringValue;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EditReservationController {
    @FXML
    private TextField reservationIDField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField roomTypeField;
    @FXML
    private TextField roomNumberField;
    @FXML
    private TextField countField;
    @FXML
    private TextField arrivalTimeField;
    @FXML
    private TextField departureTimeField;
    @FXML
    private DatePicker dateField;
    @FXML
    private Reservation currentreservation;

    public void setReservationData(Reservation reservation) {
        this.currentreservation = reservation;
        reservationIDField.setText(String.valueOf(reservation.getReservationId()));
        reservationIDField.setText(String.valueOf(reservation.getReservationId()));
        reservationIDField.setEditable(false);
        nameField.setText(reservation.getHotelName());
        roomTypeField.setText(String.valueOf(reservation.getRoomType()));
        roomTypeField.setText(reservation.getRoomType());
        roomNumberField.setText(String.valueOf(reservation.getRoomID()));
        countField.setText(String.valueOf(reservation.getGuestCount()));
        arrivalTimeField.setText(String.valueOf(reservation.getArrivalTime()));
        departureTimeField.setText(String.valueOf(reservation.getDepartureTime()));
        dateField.setValue(reservation.getDate());
        System.out.println("Loaded reservation ID: " + reservation.getReservationId());


    }

    @FXML
    private void handleSave() {
        currentreservation.setReservationId(Integer.parseInt(reservationIDField.getText()));// Setting values for the reservation
        currentreservation.setHotelName(nameField.getText());
        currentreservation.setRoomType(roomTypeField.getText());
        currentreservation.setRoomID(Integer.parseInt(roomNumberField.getText()));  // Convert room number to int
        currentreservation.setGuestCount(Integer.parseInt(countField.getText()));   // Convert guest count to int
        currentreservation.setDate(dateField.getValue());  // Use the DatePicker value for the date

        // Parsing arrival and departure times
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // Ensure arrivalTimeField and departureTimeField are not empty before parsing
        String arrivalTimeText = arrivalTimeField.getText();
        String departureTimeText = departureTimeField.getText();
        LocalTime arrivalTime = LocalTime.parse(arrivalTimeText, timeFormatter);
        LocalTime departureTime = LocalTime.parse(departureTimeText, timeFormatter);
        currentreservation.setArrivalTime(arrivalTime);
        currentreservation.setDepartureTime(departureTime);
        System.out.println("Saving reservation with ID: " + currentreservation.getReservationId());
        System.out.println("Arrival Time: " + currentreservation.getArrivalTime());
        System.out.println("Departure Time: " + currentreservation.getDepartureTime());
        boolean success = ReservationService.updateReservation(currentreservation);

        if (success) {
            System.out.println("Reservation details updated successfully!");
        } else {
            System.out.println("Failed to update reservation details.");
        }
    }
}
