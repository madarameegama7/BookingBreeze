package com.example.controllers;

import com.example.backend.PaymentService;
import com.example.models.Payment;
import com.example.backend.DatabaseConnection;
import com.example.utility.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PaymentViewController {

    @FXML
    private TextField userIDField;
    @FXML
    private TextField CardOwnerName;
    @FXML
    private TextField CardNumber;
    @FXML
    private TextField expMonth;
    @FXML
    private TextField expYear;
    @FXML
    private TextField cvv;
    @FXML
    private TextField amount;
    @FXML
    private TextField paymentDate;
    @FXML
    private CheckBox paypalCheckBox;
    @FXML
    private CheckBox cashCheckBox;
    @FXML
    private CheckBox creditCardCheckBox;
    @FXML
    private CheckBox debitCardCheckBox;
    @FXML
    private CheckBox fullPaymentCheckbox;
    @FXML
    private CheckBox initialPaymentCheckbox;

    @FXML
    private Button payNowButton;
    @FXML
    private Button cancelButton;

    private Payment currentPayment;
    private PaymentService paymentService = new PaymentService();




    @FXML
    private void handlePayNow(ActionEvent event) {
        try {
            // Validate input fields
            if (!validateFields()) {
                showAlert("Validation Error", "Please fill in all required fields.");
                return;
            }

            // Retrieve user ID
            int userID = UserSession.getInstance().getUserId();

            if(userID == -1){
                showAlert("Session error","User is not logged in");
            }

            // Payment details
            double paymentAmount = Double.parseDouble(amount.getText());
            String paymentType = getSelectedPaymentType();
            String paymentStatus = getSelectedPaymentStatus();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            try {
                LocalDate parsedDate = LocalDate.parse(paymentDate.getText(), formatter);
                Date paymentDateValue = Date.valueOf(parsedDate);

                // Call PaymentService to process the payment
                boolean paymentSuccess = paymentService.processPayment(userID, paymentAmount, paymentType, paymentDateValue, paymentStatus);

                // Navigation based on the result
                if (paymentSuccess) {
                    navigateToConfirmation(event);
                } else {
                    showAlert("Payment Error", "Payment failed. Please try again.");
                }
            } catch (DateTimeParseException e) {
                showAlert("Date Format Error", "Please enter the date in MM/dd/yyyy format.");
            }



        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred. Please check your input and try again.");
        }
    }


    private boolean validateFields() {
        if (   CardOwnerName.getText().isEmpty() ||
                CardNumber.getText().isEmpty() ||
                expMonth.getText().isEmpty() ||
                expYear.getText().isEmpty() ||
                cvv.getText().isEmpty() ||
                amount.getText().isEmpty() ||
                paymentDate.getText().isEmpty() ||
                getSelectedPaymentType() == null) {
            // If any of the fields are empty or payment type is not selected
            return false;
        }
        return true;
    }

    private void navigateToConfirmation(ActionEvent event) {
        if (currentPayment != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/PaymentConfirmation.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                PaymentConfirmationController controller = loader.getController();
                controller.setPaymentData(currentPayment);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void navigateToUnsuccessful(ActionEvent event) throws IOException {
        // Load PaymentUnsuccessful.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/PaymentUnsuccessful.fxml"));
        Parent unsuccessfulRoot = loader.load();

        // Get the current stage (window) and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(unsuccessfulRoot));
        stage.show();
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        // Load PaymentUnsuccessful.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/userdashboard.fxml"));
        Parent unsuccessfulRoot = loader.load();

        // Get the current stage (window) and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(unsuccessfulRoot));
        stage.show();
    }


    @FXML
    private void showPaymentUnsuccessfulStage(ActionEvent event) {
        try {
            navigateToUnsuccessful(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSelectedPaymentType() {
        if (creditCardCheckBox.isSelected()) {
            return "Credit Card";
        } else if (debitCardCheckBox.isSelected()) {
            return "Debit Card";
        } else if (paypalCheckBox.isSelected()) {
            return "Paypal";
        } else if (cashCheckBox.isSelected()) {
            return "Cash";
        }
        return null;  // Or handle default payment type
    }

    public String getSelectedPaymentStatus(){
        if (fullPaymentCheckbox.isSelected()) {
            return "Full Payment";
        } else if (initialPaymentCheckbox.isSelected()) {
            return "Initial Payment";
        }
        return null;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


}



