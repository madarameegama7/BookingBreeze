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

public class DashboardController {

    @FXML
    private VBox profileView;

    @FXML
    private VBox hotelsView;

    @FXML
    private VBox reservationsView;

    @FXML
    private VBox settingsView;


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
    public void viewProfile(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/userProfile.fxml"));
        Parent root = loader.load();

        // Get the UserProfileController and set the user data
        UserProfileController profileController = loader.getController();
        profileController.setUser(currentUser);

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
