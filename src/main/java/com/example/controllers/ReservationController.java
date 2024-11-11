package com.example.controllers;

import com.example.backend.ReservationService;
import com.example.models.Reservation;
import com.example.utility.ReservationSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservationController {

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
    private Label hotelNameLabel;
    @FXML
    private Label roomTypeLabel;
    @FXML
    private Label roomNumberLabel;
    @FXML
    private Label countLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label arrivalTimeLabel;
    @FXML
    private Label departureTimeLabel;

    @FXML
    private TableView<Reservation> reservationTableView;
    @FXML
    private TableColumn<Reservation, Integer> userIdColumn;
    @FXML
    private TableColumn<Reservation, String> hotelNameColumn;
    @FXML
    private TableColumn<Reservation, String> roomTypeColumn;
    @FXML
    private TableColumn<Reservation, String> roomNumberColumn;
    @FXML
    private TableColumn<Reservation, Integer> guestCountColumn;
    @FXML
    private TableColumn<Reservation, String> dateColumn;

    @FXML
    private ListView<String> reservationsListView;

    private final ReservationService reservationService = new ReservationService();

    @FXML
    private void bookNow(ActionEvent event) {
        String name = nameField.getText().trim();
        String hotel = hotelField.getText().trim();
        String roomType = roomTypeField.getText().trim();
        String roomNumber = roomNumberField.getText().trim();
        String count = countField.getText().trim();
        String arrivalTime = arrivalTimeField.getText().trim();
        String departureTime = departureTimeField.getText().trim();
        LocalDate date = dateField.getValue();

        if (name.isEmpty() || hotel.isEmpty() || roomType.isEmpty() || roomNumber.isEmpty() || count.isEmpty() ||
                arrivalTime.isEmpty() || departureTime.isEmpty() || date == null) {
            showAlert("All fields are required.");
            return;
        }

        if (!isNumeric(count) || Integer.parseInt(count) <= 0) {
            showAlert("Count must be a positive integer.");
            return;
        }

        boolean isReserved = reservationService.addReservation(name, hotel, roomType, roomNumber, /*userID*/ 1,
                Integer.parseInt(count), date, arrivalTime, departureTime);

        if (isReserved) {
            try {
                switchToSuccessScreen(event);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Failed to load success screen.");
            }
        } else {
            showAlert("Reservation failed. Please try again.");
        }
    }

    private void switchToSuccessScreen(ActionEvent event) throws IOException {
        Parent successRoot = FXMLLoader.load(getClass().getResource("/com/example/fxml/success.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(successRoot));
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reservation Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setReservation(Reservation reservation) {
        hotelNameLabel.setText(reservation.getHotelName());
        roomTypeLabel.setText(reservation.getRoomType());
        roomNumberLabel.setText(reservation.getRoomID());
        countLabel.setText(String.valueOf(reservation.getGuestCount()));
        dateLabel.setText(reservation.getDate().toString());

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        arrivalTimeLabel.setText(reservation.getArrivalTime().format(timeFormatter));
        departureTimeLabel.setText(reservation.getDepartureTime().format(timeFormatter));
    }

    @FXML
    public void viewReservations(ActionEvent event) throws IOException {
        int userId = ReservationSession.getUserId();

        // Fetch all reservations for the user
        List<Reservation> reservations = reservationService.getReservationsByUserId(userId);

        // Create an ObservableList to hold the reservation details as strings
        ObservableList<String> reservationDetails = FXCollections.observableArrayList();

        // Iterate over each reservation and format its details into a string
        for (Reservation reservation : reservations) {
            String details = "Hotel: " + reservation.getHotelName() +
                    ", Room: " + reservation.getRoomType() +
                    ", Room Number: " + reservation.getRoomID() +
                    ", Date: " + reservation.getDate().toString();
            reservationDetails.add(details);
        }

        // Load and display reservation view scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/reservationsList.fxml"));
        Parent root = loader.load();

        // Ensure that the controller is fully initialized before accessing the ListView
        ReservationController controller = loader.getController();

        // Set the items for the ListView in the loaded FXML
        controller.reservationsListView.setItems(reservationDetails);

        // Show the new stage with the updated ListView
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void updateReservation(ActionEvent event) {
        // Get the selected reservation
        Reservation selectedReservation = reservationTableView.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            showAlert("Please select a reservation to update.");
            return;
        }

        // Fill the fields with the selected reservation's data
        nameField.setText(selectedReservation.getName());
        hotelField.setText(selectedReservation.getHotelName());
        roomTypeField.setText(selectedReservation.getRoomType());
        roomNumberField.setText(selectedReservation.getRoomID());
        countField.setText(String.valueOf(selectedReservation.getGuestCount()));
        dateField.setValue(selectedReservation.getDate());
        arrivalTimeField.setText(selectedReservation.getArrivalTime().toString());
        departureTimeField.setText(selectedReservation.getDepartureTime().toString());

        // Set up a button for updating
        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            selectedReservation.setName(nameField.getText());
            selectedReservation.setHotelName(hotelField.getText());
            selectedReservation.setRoomType(roomTypeField.getText());
            selectedReservation.setRoomID(roomNumberField.getText());
            selectedReservation.setGuestCount(Integer.parseInt(countField.getText()));
            selectedReservation.setDate(dateField.getValue());
            selectedReservation.setArrivalTime(LocalTime.parse(arrivalTimeField.getText()));
            selectedReservation.setDepartureTime(LocalTime.parse(departureTimeField.getText()));

            reservationService.updateReservation(selectedReservation);
            showAlert("Reservation updated successfully.");
        });

        // Call this method when you want to switch to an edit page.
    }

    @FXML
    public void deleteReservation(ActionEvent event) {
        Reservation selectedReservation = reservationTableView.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            showAlert("Please select a reservation to delete.");
            return;
        }

        Alert confirmDeleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDeleteAlert.setTitle("Delete Reservation");
        confirmDeleteAlert.setHeaderText("Are you sure you want to delete this reservation?");
        confirmDeleteAlert.setContentText("This action cannot be undone.");

        confirmDeleteAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                reservationService.deleteReservation(selectedReservation.getUserId());
                showAlert("Reservation deleted successfully.");
            }
        });
    }
}
