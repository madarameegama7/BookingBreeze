package com.example.controllers;

import com.example.backend.HotelServices;
import com.example.backend.ReservationService;
import com.example.models.Hotel;
import com.example.models.Reservation;
import com.example.utility.HotelSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

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
    private ComboBox<String> hotelComboBox;

    @FXML
    public void initialize() {
        loadHotelNames();
    }

    @FXML


    public LocalTime getTimeInput() {
        try {
            return LocalTime.parse(arrivalTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            showAlert("Invalid time format. Please enter time as HH:mm.");
            return null;
        }
    }

    private void loadHotelNames() {
        List<String> hotelNames = HotelServices.getAllHotelNames();
        hotelComboBox.getItems().addAll(hotelNames);
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleBookNowClick() {
        int userID = HotelSession.getInstance().getUserId();
        String username = HotelSession.getInstance().getUsername();
        System.out.println("Username: " + username);  // Debugging line

        String hotel = hotelComboBox.getSelectionModel().getSelectedItem();
        System.out.println("Selected Hotel: " + hotel);
        String roomType = roomTypeField.getText();
        int roomNumber = Integer.parseInt(roomNumberField.getText());
        int count = Integer.parseInt(countField.getText());
        LocalTime arrivalTime = LocalTime.parse(arrivalTimeField.getText());
        LocalTime departureTime = LocalTime.parse(departureTimeField.getText());
        LocalDate date = dateField.getValue();

        Reservation reservation = new Reservation(userID, username, hotel, roomType, roomNumber, count, date, arrivalTime, departureTime);

        ReservationService reservationService = new ReservationService();
        int reservationId = reservationService.addReservation(hotel, roomType, roomNumber, count, date, arrivalTime, departureTime);
        if (reservationId > 0) {
            showAlert("Reservation booked successfully!");

            loadBookingConfirmationPage(reservationId);
        } else {
            showAlert("Failed to book the reservation.");
        }
    }


    private void loadBookingConfirmationPage(int reservationID) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/BookingConfirmation.fxml"));
            Parent root = loader.load();
            BookingConfirmationController controller = loader.getController();
            controller.setReservationID(reservationID);

            Stage stage = (Stage) hotelComboBox.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Failed to load confirmation page.");
            e.printStackTrace();
        }
    }

}
