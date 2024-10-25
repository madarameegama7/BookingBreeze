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

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private UserService userService = new UserService();

    // Method to handle the login button action in the UI
    @FXML
    public void login(ActionEvent actionEvent) {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate user credentials with UserService
        if (userService.login(email, password)) {
            messageLabel.setText("Login Successful!");
            // Code to redirect to dashboard or next page goes here
        } else {
            messageLabel.setText("Login Failed!");
        }
    }

    @FXML
    private void switchToSignup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/signup.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
