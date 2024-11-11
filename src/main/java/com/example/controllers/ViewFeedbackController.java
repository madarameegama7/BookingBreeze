package com.example.controllers;

import com.example.utility.UserSession;
import com.example.backend.FeedbackServices;
import com.example.models.Feedback;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ViewFeedbackController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    @FXML
    private TextField addressField;

    private final ObservableList<Feedback> feedbackList = FXCollections.observableArrayList();

    // This method will be called automatically after the FXML file is loaded
    @FXML
    public void initialize() {
        // Set up the columns to use the properties from the Feedback model
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        feedbackColumn.setCellValueFactory(new PropertyValueFactory<>("feedback"));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));

        // Load the data
        loadFeedbackData();
    }

    // Method to go back to the admin dashboard
    @FXML
    public void backToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/admindashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Method to load feedback data into the TableView
    @FXML
    private void loadFeedbackData() {
        try {
            feedbackList.clear(); // Clear the list to avoid duplicates
            List<Feedback> feedbacks = FeedbackServices.viewFeedback();
            feedbackList.addAll(feedbacks);
            feedbackTableView.setItems(feedbackList);
            feedbackTableView.refresh(); // Refresh the TableView to show the new data
        } catch (Exception e) {
            // Handle any errors during data loading
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Loading Data");
            alert.setHeaderText("Could not load feedback data");
            alert.setContentText("An error occurred while fetching feedback data. Please try again later.");
            alert.showAndWait();
        }
    }
}
