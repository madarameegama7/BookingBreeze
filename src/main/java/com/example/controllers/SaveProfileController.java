package com.example.controllers;

import com.example.models.User;
import com.example.backend.UserService;
import com.example.utility.HotelSession;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaveProfileController {

    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private Button saveProfileButton;
    private int userId;

    public void loadUserData(int userId, String email, String username, String contactNumber, String address) {
        this.userId = userId;
        emailField.setText(email);
        usernameField.setText(username);
        contactNumberField.setText(contactNumber);
        addressField.setText(address);
    }

    @FXML
    public void handleSaveProfile() {
        User updatedUser = new User();
        updatedUser.setUserID(userId);
        updatedUser.setUsername(usernameField.getText());
        updatedUser.setEmail(emailField.getText());
        updatedUser.setContactNumber(contactNumberField.getText());
        updatedUser.setAddress(addressField.getText());

        System.out.println("Updating user: " + updatedUser);


        boolean success = UserService.updateUser(updatedUser);
        if (success) {
            if (HotelSession.getInstance().getUserId() == userId) {
                // Update the username in the session
                HotelSession.getInstance().setUsername(updatedUser.getUsername());
            }
            showAlert("Profile updated successfully.");
            Stage stage = (Stage) saveProfileButton.getScene().getWindow();
            stage.close(); // Close the edit profile window
        } else {
            showAlert("Failed to update profile.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


