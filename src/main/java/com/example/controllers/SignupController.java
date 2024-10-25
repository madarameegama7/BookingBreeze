package com.example.controllers;

import com.example.backend.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField usernameField;  // Field for the username
    @FXML
    private TextField emailField;     // Field for the email address
    @FXML
    private PasswordField passwordField;  // Field for the password

    @FXML
    private Label messageLabel;  // Label to display messages like success or failure

    private final UserService userService = new UserService();  // Instantiate UserService

    // Method to handle signup button action in the UI
    @FXML
    public void signup(ActionEvent event) {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("All fields are required.");
            return;
        }

        // Call UserService to create a new user
        if (userService.signup(email, username, password)) {
            messageLabel.setText("Signup successful! You can now log in.");
            try {
                switchToLogin(event);  // Redirect to login screen after successful signup
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Signup failed. Username or email may already exist.");
        }
    }

    // Method to switch to the login screen
    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
