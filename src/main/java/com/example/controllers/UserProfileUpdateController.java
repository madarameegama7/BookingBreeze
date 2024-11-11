package com.example.controllers;

import com.example.backend.UserService;
import com.example.models.User;

import com.example.utility.HotelSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private Button editProfileButton;
    @FXML
    private Button deleteAccountButton;
    @FXML
    private int userId;

    public void loadUserData(int userId) {
        System.out.println("Loading user data for user ID: " + userId);
        User user = UserService.getUserById(userId); // Database call

        // Populate the fields with user data
        if (user != null) {
            usernameField.setText(user.getUsername());
            emailField.setText(user.getEmail());
            contactNumberField.setText(user.getContactNumber());
            addressField.setText(user.getAddress());
        } else {
            System.out.println("User not found"); // Debug statement } }
            // Populate other fields similarly
        }
    }

        @FXML
        public void handleDeleteAccount() {
            int userId = HotelSession.getInstance().getUserId();
            boolean success = UserService.deleteUserById(userId);
            if (success) {
                showAlert("User deleted successfully.");
                Stage stage = (Stage) deleteAccountButton.getScene().getWindow();
                stage.close(); // Close the profile update window
            } else {
                showAlert("Failed to delete user.");
            }
        }


    @FXML
    public void handleEditProfile(ActionEvent event) {
        int userId = HotelSession.getInstance().getUserId(); // Ensure this is set correctly
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/editUser.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Pass user ID to EditProfileController
            SaveProfileController controller = loader.getController();
            controller.loadUserData(userId, emailField.getText(), usernameField.getText(), contactNumberField.getText(), addressField.getText());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }





    // Method to handle profile update (Edit Profile button)



