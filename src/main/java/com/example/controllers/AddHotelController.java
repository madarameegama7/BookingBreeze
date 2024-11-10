package com.example.controllers;

import com.example.backend.HotelServices;
import com.example.models.Hotel;
import com.example.utility.HotelSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AddHotelController {

    @FXML
    private TextField userIDField;
    @FXML
    private TextField hotelNameField;
    @FXML
    private TextField registrationNoField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField contactNumField;
    @FXML
    private TextField facilitiesField;
    @FXML
    private Button uploadImageButton;
    @FXML
    private ImageView hotelImageView;
    @FXML
    private Button submitButton;
    @FXML
    private Label statusMessageLabel;
    @FXML


    private byte[] hotelImages;

    @FXML
    public void backToDashboard(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/fxml/viewHotel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void handleUploadImageClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(uploadImageButton.getScene().getWindow());

        if (selectedFile != null) {
            try {
                // Read the image file into a byte array
                hotelImages = Files.readAllBytes(selectedFile.toPath());
                // Load the image into the ImageView (optional)
                hotelImageView.setImage(new javafx.scene.image.Image(selectedFile.toURI().toString()));
            } catch (IOException e) {
                e.printStackTrace(); // Handle exceptions
            }
        }
    }

    @FXML
    public void handleRegisterButtonClick() {
        int userID = HotelSession.getInstance().getUserId();
        String hotelName = hotelNameField.getText();
        String registrationNo = registrationNoField.getText();
        String location = locationField.getText();
        String contactNum = contactNumField.getText();
        String facilities = facilitiesField.getText();

        // Create a new Hotel object
        Hotel hotel = new Hotel(userID, userID, hotelName, registrationNo, location, contactNum, facilities, hotelImages);

        // Call the HotelServices to add the hotel
        HotelServices hotelServices = new HotelServices();
        boolean isAdded = hotelServices.addHotel(hotelName, registrationNo, location, contactNum, facilities, hotelImages);

        if (isAdded) {
            statusMessageLabel.setText("Hotel registered successfully!"); // Success message
            statusMessageLabel.setTextFill(Color.BLACK); // Optional: Change text color to green
        } else {
            statusMessageLabel.setText("Failed to register hotel."); // Failure message
            statusMessageLabel.setTextFill(javafx.scene.paint.Color.RED); // Optional: Change text color to red
        }
    }
}

