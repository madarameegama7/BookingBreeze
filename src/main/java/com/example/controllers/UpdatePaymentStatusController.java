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
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.models.Payment;
import javafx.stage.Stage;

public class UpdatePaymentStatusController {
    private Payment currentPayement;

    @FXML
    private TextField paymentIdField;
    @FXML
    private TextField paymentTypeField;
    @FXML
    private TextField paymentDateField;
    @FXML
    private TextField paymentAmountField;
    @FXML
    private TextField paymentStatusField;


    public void setPaymentData(Payment payment) {
        this.currentPayement = payment;
        paymentIdField.setText(String.valueOf(payment.getPaymentID()));
        paymentTypeField.setText(payment.getPaymentType());
        LocalDate paymentDate = payment.getPaymentDate().toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        paymentDateField.setText(paymentDate.format(formatter));
        paymentAmountField.setText(String.format("%.2f", payment.getPayment()));
        paymentStatusField.setText(payment.getPaymentStatus());
    }

    @FXML
    public void handleSave() {
        currentPayement.setPaymentID(Integer.parseInt(paymentIdField.getText()));
        currentPayement.setPaymentType(paymentTypeField.getText());
        String dateText = paymentDateField.getText().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateText, formatter);
        Date sqlDate = Date.valueOf(localDate);
        currentPayement.setPaymentDate(sqlDate);
        currentPayement.setPayment(Double.parseDouble(paymentAmountField.getText()));
        currentPayement.setPaymentStatus(paymentStatusField.getText());

        // Call the service layer to save the updated hotel details
        boolean success = PaymentService.updatePaymentDetails(currentPayement);
        if (success) {
            System.out.println("payment details updated successfully!");
        } else {
            System.out.println("Failed to update payment details.");
        }
    }
//    @FXML
//    public void updateStatus(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/searchPayment.fxml"));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
}
