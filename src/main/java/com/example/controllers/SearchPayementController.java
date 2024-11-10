package com.example.controllers;

import com.example.backend.PaymentService;
import com.example.models.Payment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SearchPayementController {
    @FXML
    private TextField paymentIdField;
    @FXML
    private VBox detailsBox;
    @FXML
    private TextField paymentTypeField;
    @FXML
    private TextField paymentDateField;
    @FXML
    private TextField paymentAmountField;
    @FXML
    private TextField paymentStatusField;

    private Payment currentPayement;

    @FXML
    private void handleSearch(ActionEvent event) {
        int paymentID = Integer.parseInt(paymentIdField.getText().trim());
        List<Payment> payments = PaymentService.retrievePayementDetails(paymentID);
        if (!payments.isEmpty()) {
            currentPayement = payments.get(0);
            populateFields(currentPayement);
            detailsBox.setVisible(true);
            detailsBox.setManaged(true);
        } else {
            detailsBox.setVisible(false);
            detailsBox.setManaged(false);
        }
    }

    private void populateFields(Payment payment) {
        paymentIdField.setText(String.valueOf(payment.getPaymentID()));
        paymentTypeField.setText(payment.getPaymentType());
        paymentDateField.setText(String.valueOf(payment.getPaymentDate()));
        paymentAmountField.setText(String.valueOf(payment.getPayment()));
        paymentStatusField.setText(payment.getPaymentStatus());
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        try {
            int paymentID = Integer.parseInt(paymentIdField.getText().trim());

            // Attempt to delete the payment
            boolean success = PaymentService.deletePayementByID(paymentID);

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
        paymentIdField.clear();
        paymentTypeField.clear();
        paymentDateField.clear();
        paymentAmountField.clear();
        paymentStatusField.clear();
    }

    @FXML
    private void handleEditDetails(ActionEvent event) {
        if (currentPayement != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/UpdatePaymentStatus.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                UpdatePaymentStatusController controller = loader.getController();
                controller.setPaymentData(currentPayement);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
