package com.example.controllers;
import com.example.backend.PaymentService;
import com.example.models.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ViewPaymentHistoryController {

    @FXML
    private TableView<Payment> paymentHistoryTableView;
    @FXML
    private TableColumn<Payment, Integer> userIdColumn;
    @FXML
    private TableColumn<Payment, Integer> reservationIdColumn;
    @FXML
    private TableColumn<Payment, Integer> paymentIdColumn;
    @FXML
    private TableColumn<Payment, String> paymentMethodColumn;
    @FXML
    private TableColumn<Payment, Double> paymentAmountColumn;
    @FXML
    private TableColumn<Payment, String> paymentStatusColumn;

    private final ObservableList<Payment> paymentList = FXCollections.observableArrayList();

    // This method will be called automatically after the FXML file is loaded
    @FXML
    public void initialize() {
        // Set up the columns to use the properties from the Hotel model
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        paymentAmountColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        paymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));


        // Load the data
        loadPaymentData();
    }

    private void loadPaymentData() {
        List<Payment> payment1 = PaymentService.viewPayments();
        paymentList.addAll(payment1);
        paymentHistoryTableView.setItems(paymentList);
    }
}

