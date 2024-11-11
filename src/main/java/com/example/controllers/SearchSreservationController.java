package com.example.controllers;

import com.example.backend.PaymentService;
import com.example.backend.ReservationService;
import com.example.models.Payment;
import com.example.models.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchSreservationController {

    @FXML
    private TextField ReservationIDField;
    @FXML
    private TextField hotelNameField;
    @FXML
    private TextField roomTypeField;
    @FXML
    private TextField roomNoField;
    @FXML
    private TextField countField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField arrivalTimeField;
    @FXML
    private  TextField departureTimeField;

    @FXML
    private VBox detailsBox;

    private Reservation currentreservation;

    @FXML
    private void handleSearch(ActionEvent event) {
        int reservationID = Integer.parseInt(ReservationIDField.getText().trim());
        List<Reservation> reservations = ReservationService.getReservationDetails(reservationID);
        if (!reservations.isEmpty()) {
            currentreservation = reservations.get(0);
            System.out.println("Found reservation ID: " + currentreservation.getReservationId());
            populateFields(currentreservation);
            detailsBox.setVisible(true);
            detailsBox.setManaged(true);
        } else {
            detailsBox.setVisible(false);
            detailsBox.setManaged(false);
        }
    }

    private void populateFields(Reservation reservation) {
        hotelNameField.setText(reservation.getHotelName());
        roomTypeField.setText(reservation.getRoomType());
        roomNoField.setText(String.valueOf(reservation.getRoomID()));
        countField.setText(String.valueOf(reservation.getGuestCount()));
        dateField.setValue(reservation.getDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // 24-hour format
        arrivalTimeField.setText(reservation.getArrivalTime().format(formatter));
        departureTimeField.setText(reservation.getDepartureTime().format(formatter));
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            int reservationID = Integer.parseInt(ReservationIDField.getText().trim());

            // Attempt to delete the payment
            boolean success = ReservationService.deleteReservation(reservationID);

            if (success) {
                clearFields();
                detailsBox.setVisible(false);
                detailsBox.setManaged(false);
                System.out.println("Payment deleted successfully.");
            } else {
                System.out.println("Failed to delete payment.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Payment ID. Please enter a numeric value.");
        }
    }
    private void clearFields() {
        hotelNameField.clear();
        roomTypeField.clear();
        roomNoField.clear();
        countField.clear();
        dateField.setValue(null);
        arrivalTimeField.clear();
        departureTimeField.clear();
    }

    @FXML
    private void handleEditDetails(ActionEvent event) {
        if (currentreservation != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/Editreservation.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                EditReservationController controller = loader.getController();
                controller.setReservationData(currentreservation);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
