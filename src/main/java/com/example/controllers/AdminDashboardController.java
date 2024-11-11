package com.example.controllers;

import com.example.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private VBox profileView;

    @FXML
    private VBox hotelsView;

    @FXML
    private VBox reservationsView;

    @FXML
    private VBox settingsView;

    private int userId;

    // Getter and Setter for userId
    public void setUserId(int userId) {
        this.userId = userId;
    }


    private User currentUser;  // Store the logged-in user

    // Method to set the current user
    public void setUser(User user) {

        this.currentUser = user;
    }
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


//    @FXML
//    public void addHotels(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/addHotel.fxml"));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
//    }

    @FXML
    public void viewHotels(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/viewHotel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void viewPaymentHistory(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/viewPaymentHistory.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void viewFeedback(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/viewFeedback.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        // Redirect to the login screen or close the current window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }





//    @FXML
//    private void showProfileView() {
//        profileView.setVisible(true);
//        hotelsView.setVisible(false);
//        reservationsView.setVisible(false);
//        settingsView.setVisible(false);
//    }
//
//    @FXML
//    private void showHotelsView() {
//        profileView.setVisible(false);
//        hotelsView.setVisible(true);
//        reservationsView.setVisible(false);
//        settingsView.setVisible(false);
//    }

//    @FXML
//    private void showReservationsView() {
//        profileView.setVisible(false);
//        hotelsView.setVisible(false);
//        reservationsView.setVisible(true);
//        settingsView.setVisible(false);
//    }
//
//    @FXML
//    private void showSettingsView() {
//        profileView.setVisible(false);
//        hotelsView.setVisible(false);
//        reservationsView.setVisible(false);
//        settingsView.setVisible(true);
//    }


}