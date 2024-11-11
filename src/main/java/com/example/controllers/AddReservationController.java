package com.example.controllers;

import com.example.backend.HotelServices;
import com.example.backend.ReservationService;
import com.example.models.Hotel;
import com.example.models.Reservation;
import com.example.utility.HotelSession;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddReservationController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField hotelField;
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


    public LocalTime getTimeInput() {
        try {
            return LocalTime.parse(arrivalTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            showAlert("Invalid time format. Please enter time as HH:mm.");
            return null;
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleBookNowClick() {
        int userID = HotelSession.getInstance().getUserId();
        String username = HotelSession.getInstance().getUsername();

        String hotel = hotelField.getText();
        String roomType = roomTypeField.getText();
        int roomNumber = Integer.parseInt(roomNumberField.getText());
        int count = Integer.parseInt(countField.getText()); // Convert count to an integer
        LocalTime arrivalTime = LocalTime.parse(arrivalTimeField.getText()); // Parse time from text
        LocalTime departureTime = LocalTime.parse(departureTimeField.getText()); // Parse time from text
        LocalDate date = dateField.getValue();

        // Create a new Hotel object
        Reservation reservation = new Reservation(userID, username, hotel, roomType, roomNumber, count, date, arrivalTime, departureTime);

        // Call the HotelServices to add the hotel
        ReservationService reservationService = new ReservationService();
        boolean isAdded = reservationService.addReservation(hotel, roomType, roomNumber, count,date, arrivalTime, departureTime);


    }


}
