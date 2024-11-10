package com.example.controllers;

import com.example.backend.HotelServices;
import com.example.models.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EditHotelController {
    @FXML
    private TextField userIDField;
    @FXML
    private TextField hotelNameField;
    @FXML
    private TextField registrationNoField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField facilitiesField;
    @FXML
    private TextField contactNumField;
    @FXML
    private TextField hotelIDField;
    @FXML
    private Button uploadImageButton;
    @FXML
    private ImageView hotelImageView;

    private Hotel currentHotel;

    private byte[] hotelImages;

    public void setHotelData(Hotel hotel) {
        this.currentHotel = hotel;
        userIDField.setText(String.valueOf(hotel.getUserID()));
        hotelNameField.setText(hotel.getHotelName());
        registrationNoField.setText(hotel.getRegistrationNo());
        locationField.setText(hotel.getLocation());
        facilitiesField.setText(hotel.getFacilities());
        contactNumField.setText(hotel.getContactNum());
        hotelIDField.setText(String.valueOf(hotel.getHotelID()));
    }

    @FXML
    private void handleUploadImageClick() {
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
        private void handleSave() {
        currentHotel.setHotelID(Integer.parseInt(hotelIDField.getText()));
            currentHotel.setHotelName(hotelNameField.getText());
            currentHotel.setRegistrationNo(registrationNoField.getText());
            currentHotel.setLocation(locationField.getText());
            currentHotel.setFacilities(facilitiesField.getText());
            currentHotel.setContactNum(contactNumField.getText());
            // Optional: Update the hotel images if they were changed
            if (hotelImages != null) {
                currentHotel.setHotelImages(hotelImages);
            }
            // Call the service layer to save the updated hotel details
            boolean success = HotelServices.updateHotelDetails(currentHotel);
            if (success) {
                System.out.println("Hotel details updated successfully!");
            } else {
                System.out.println("Failed to update hotel details.");
            }
        }
    }
