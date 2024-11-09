package com.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class DashboardController {

    @FXML
    private VBox profileView;

    @FXML
    private VBox hotelsView;

    @FXML
    private VBox reservationsView;

    @FXML
    private VBox settingsView;

    @FXML
    public void initialize(String email) {
//        welcomeLabel.setText("Welcome, " + email);
        // Optionally set the default view, for example, the profile view.
        showProfileView();
    }

    @FXML
    private void showProfileView() {
        profileView.setVisible(true);
        hotelsView.setVisible(false);
        reservationsView.setVisible(false);
        settingsView.setVisible(false);
    }

    @FXML
    private void showHotelsView() {
        profileView.setVisible(false);
        hotelsView.setVisible(true);
        reservationsView.setVisible(false);
        settingsView.setVisible(false);
    }

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

