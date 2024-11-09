package com.example.controllers;

import com.example.backend.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField contactNumberField; // New field for contact number
    @FXML
    private TextField addressField;       // New field for address
    @FXML
    private Label messageLabel;

    private final UserService userService = new UserService();

    @FXML
    public void signup(ActionEvent event) {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String contactNumber = contactNumberField.getText().trim();
        String address = addressField.getText().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || contactNumber.isEmpty() || address.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        if (!userService.isEmailValid(email)) {
            showAlert("Invalid email format. Please include '@' and end with '.com'.");
            return;
        }

        if (!userService.isPasswordValid(password)) {
            showAlert("Password must be at least 8 characters.");
            return;
        }

        // If signup is successful, display the dashboard directly
        if (userService.signup(email, username, password, contactNumber, address)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/userdashboard.fxml"));
                Parent root = loader.load();

                // Pass user data to the DashboardController
                DashboardController dashboardController = loader.getController();
                //dashboardController.setUserEmail(email); // Pass email or other user data as needed

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Signup failed. Username or email may already exist.");
        }
    }

    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Show alert method for displaying error or success messages
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Signup Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
