package com.example.controllers;

import com.example.backend.FeedbackServices;
import com.example.backend.HotelServices;
import com.example.models.Feedback;
import com.example.models.Hotel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        feedbackColumn.setCellValueFactory(new PropertyValueFactory<>("feedback"));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmailColumn"));


        // Load the data
        loadHotelData();
    }
    @FXML
    public void backToDashboard(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/userdashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void loadHotelData() {
        List<Feedback> feedbacks = FeedbackServices.viewFeedback();
        feedbackList.addAll(feedbacks);
        feedbackTableView.setItems(feedbackList);
    }
}

