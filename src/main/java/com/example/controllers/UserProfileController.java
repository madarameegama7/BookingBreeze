package com.example.controllers;

import com.example.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserProfileController {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label contactnumberLabel;

    @FXML
    private Label addressLabel;

    public void setUser(User user) {
        usernameLabel.setText(user.getUsername());
        emailLabel.setText(user.getEmail());
        contactnumberLabel.setText(user.getContactNumber());
        addressLabel.setText(user.getAddress());

    }
}
