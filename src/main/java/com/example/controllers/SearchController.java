package com.example.controllers;

import com.example.backend.HotelServices;
import com.example.models.Hotel;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class SearchController {
    @FXML
    private TextField hotelNameField;
    @FXML
    private VBox detailsBox;
    @FXML
    private TextField hotelIdField;
    @FXML
    private TextField registrationNoField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField facilitiesField;
    @FXML
    private TextField contactNoField;

    private Hotel currentHotel;

    @FXML
    private void handleSearch(ActionEvent event) {
        String hotelName = hotelNameField.getText();
        if (hotelName != null && !hotelName.isEmpty()) {
            List<Hotel> hotels = HotelServices.retrieveHotelDetails(hotelName);
            if (!hotels.isEmpty()) {
                currentHotel = hotels.get(0);
                populateFields(currentHotel);
                detailsBox.setVisible(true);
                detailsBox.setManaged(true);
            } else {
                detailsBox.setVisible(false);
                detailsBox.setManaged(false);
            }
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        String hotelName = hotelNameField.getText();
        if (hotelName!= null && !hotelName.isEmpty()) {
            boolean success = HotelServices.deleteHotelByName(hotelName);
            if (success) {
                clearFields();
                detailsBox.setVisible(false);
                detailsBox.setManaged(false);
            }else {
                System.out.println("Failed to delete hotel.");
            }
        }
    }

    @FXML
    private void handleEditDetails(ActionEvent event) {
        if (currentHotel != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/editHotel.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                EditHotelController controller = loader.getController();
                controller.setHotelData(currentHotel);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void populateFields(Hotel hotel) {
        hotelIdField.setText(String.valueOf(hotel.getHotelID()));
        registrationNoField.setText(hotel.getRegistrationNo());
        addressField.setText(hotel.getLocation());
        facilitiesField.setText(hotel.getFacilities());
        contactNoField.setText(hotel.getContactNum());
    }
    private void clearFields() {
        hotelNameField.clear();
        hotelIdField.clear();
        registrationNoField.clear();
        addressField.clear();
        facilitiesField.clear();
        contactNoField.clear();
    }


}
