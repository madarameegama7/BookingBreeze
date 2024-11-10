package com.example.controllers;

import com.example.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UserProfileUpdateController {

    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private Button updateButton;

    private User currenUser;

    @FXML
    public void setUserData(User user){
            this.currenUser = user;
            emailField.setText(user.getEmail());
            usernameField.setText(user.getUsername());
            contactNumberField.setText(user.getContactNumber());
            addressField.setText(user.getAddress());
        }
    }


    // Method to handle profile update (Edit Profile button)



