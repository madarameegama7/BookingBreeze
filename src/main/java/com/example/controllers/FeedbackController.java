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

public class FeedbackController {
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
//    @FXML
//    public void initialize() {
//        // Set up the columns to use the properties from the Hotel model
//        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
//        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
//        feedbackColumn.setCellValueFactory(new PropertyValueFactory<>("feedback"));
//        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
//
//
//
//        // Load the data
//        loadHotelData();
//    }



    @FXML
    public void giveFeedback(ActionEvent event) throws IOException {
        int userId = UserSession.getInstance().getUserId();

        if (userId != -1) { // Ensure userId is valid
            // Retrieve username and email associated with userId from the database
            String username = FeedbackServices.getUsernameByUserId(userId);
            String email = FeedbackServices.getEmailByUserId(userId);

            String feedbackText = addressField.getText();

            if (!feedbackText.isEmpty() && username != null && email != null) {
                boolean isInserted = FeedbackServices.insertFeedback(userId, username, email, feedbackText);

                if (isInserted) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Feedback Submitted");
                    alert.setHeaderText(null);
                    alert.setContentText("Your feedback has been successfully submitted!");
                    alert.showAndWait();

                    // Clear the field after submission
                    addressField.clear();

                    // Redirect to the dashboard
                    backToDashboard(event);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Submission Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("An error occurred while submitting your feedback. Please try again.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Feedback");
                alert.setHeaderText(null);
                alert.setContentText("Please enter some feedback before submitting.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User Not Logged In");
            alert.setHeaderText(null);
            alert.setContentText("Please log in to submit feedback.");
            alert.showAndWait();
        }
    }

    @FXML
    public void backToDashboard(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/userdashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void loadHotelData() {
        List<Feedback> feedbacks = FeedbackServices.viewFeedback();
        feedbackList.addAll(feedbacks);
        feedbackTableView.setItems(feedbackList);
    }
}

