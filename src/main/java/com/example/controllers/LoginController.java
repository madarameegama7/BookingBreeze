package com.example.controllers;
//
//import com.example.backend.UserService;
//import com.example.utility.UserSession;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class LoginController {
//
//    private Stage stage;
//
//    @FXML
//    private TextField emailField;
//    @FXML
//    private PasswordField passwordField;
//
//    private final UserService userService = new UserService();
//
//    @FXML
//    public void login(ActionEvent event) {
//        String email = emailField.getText().trim();
//        String password = passwordField.getText().trim();
//
//        if (email.isEmpty() || password.isEmpty()) {
//            showAlert("Please fill in all fields.");
//            return;
//        }
//
//        int userId = userService.login(email, password);
//
//        if (userId != -1) {
//            UserSession.getInstance().setUserId(userId);// If userId is valid, login successful
//            // Store the userId for later use (e.g., in a session or controller)
//            System.out.println("User ID: " + userId);
//
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/userdashboard.fxml"));
//                Parent root = loader.load();
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(new Scene(root));
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            showAlert("Invalid email or password.");
//        }
//    }
//
//
//    private void loadAdminDashboard() throws Exception {
//        Parent adminRoot = FXMLLoader.load(getClass().getResource("/com/example/fxml/admindashboard.fxml"));
//        stage.setScene(new Scene(adminRoot));
//        stage.show();
//    }
//
//    private void loadUserDashboard() throws Exception {
//        Parent userRoot = FXMLLoader.load(getClass().getResource("/com/example/fxml/userdashboard.fxml"));
//        stage.setScene(new Scene(userRoot));
//        stage.show();
//    }
//    @FXML
//    private void switchToSignup(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/signup.fxml"));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
//
//    private void showAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Login Information");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}
import com.example.backend.UserService;
import com.example.models.UserRole;
import com.example.utility.HotelSession;
import com.example.utility.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Stage stage;

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    private final UserService userService = new UserService();

    @FXML
    public void login(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        UserRole role = userService.login(email, password);
        int userId = userService.login(email, password);

        if (userId != -1) {
            HotelSession.getInstance().setUserId(userId);// If userId is valid, login successful
            // Store the userId for later use (e.g., in a session or controller)
            System.out.println("User ID: " + userId);

        if (role != null) {
            // Based on the role, load the appropriate dashboard
            try {
                if (role == UserRole.ADMIN) {
                    loadAdminDashboard(event);
                } else if (role == UserRole.USER) {
                    loadUserDashboard(event);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Invalid email or password.");
        }
    }

    private void loadAdminDashboard(ActionEvent event) throws IOException {
        Parent adminRoot = FXMLLoader.load(getClass().getResource("/com/example/fxml/admindashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(adminRoot));
        stage.show();
    }

    private void loadUserDashboard(ActionEvent event) throws IOException {
        Parent userRoot = FXMLLoader.load(getClass().getResource("/com/example/fxml/userdashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(userRoot));
        stage.show();
    }

    @FXML
    private void switchToSignup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/signup.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
