package com.example.controllers;

import com.example.backend.FeedbackServices;
import com.example.backend.HotelServices;
import com.example.models.Feedback;
import com.example.models.Hotel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ViewFeedbackController {
    @FXML
    private TableView<Feedback> feedbackTableView;
    @FXML
    private TableColumn<Feedback, Integer> userIdColumn;
    @FXML
    private TableColumn<Feedback, Integer> feedbackIdColumn;
    @FXML
    private TableColumn<Feedback, String> userNameColumn;
    @FXML
    private TableColumn<Feedback, String> feedbackColumn;
    @FXML
    private TableColumn<Feedback, String> userEmailColumn;

    private final ObservableList<Feedback> feedbackList = FXCollections.observableArrayList();

    // This method will be called automatically after the FXML file is loaded
    @FXML
    public void initialize() {
        // Set up the columns to use the properties from the Hotel model
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        feedbackIdColumn.setCellValueFactory(new PropertyValueFactory<>("feedbackID"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        feedbackColumn.setCellValueFactory(new PropertyValueFactory<>("feedback"));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmailColumn"));


        // Load the data
        loadHotelData();
    }

    private void loadHotelData() {
        List<Feedback> feedbacks = FeedbackServices.viewFeedback();
        feedbackList.addAll(feedbacks);
        feedbackTableView.setItems(feedbackList);
    }
}

