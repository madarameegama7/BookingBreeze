package com.example.controllers;

import com.example.backend.HotelServices;
import com.example.models.Hotel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


public class ViewHotelController {


        @FXML
        private TableView<Hotel> hotelTableView;
        @FXML
        private TableColumn<Hotel, Integer> userIdColumn;
        @FXML
        private TableColumn<Hotel, Integer> hotelIdColumn;
        @FXML
        private TableColumn<Hotel, String> hotelNameColumn;
        @FXML
        private TableColumn<Hotel, String> registrationNoColumn;
        @FXML
        private TableColumn<Hotel, String> locationColumn;
        @FXML
        private TableColumn<Hotel, String> contactNumColumn;
        @FXML
        private TableColumn<Hotel, String> facilitiesColumn;

        private final ObservableList<Hotel> hotelList = FXCollections.observableArrayList();

        // This method will be called automatically after the FXML file is loaded
        @FXML
        public void initialize() {
            // Set up the columns to use the properties from the Hotel model
            userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
            hotelIdColumn.setCellValueFactory(new PropertyValueFactory<>("hotelID"));
            hotelNameColumn.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
            registrationNoColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNo"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            contactNumColumn.setCellValueFactory(new PropertyValueFactory<>("contactNum"));
            facilitiesColumn.setCellValueFactory(new PropertyValueFactory<>("facilities"));


            // Load the data
            loadHotelData();
        }

        private void loadHotelData() {
            List<Hotel> hotels = HotelServices.viewHotels();
            hotelList.addAll(hotels);
            hotelTableView.setItems(hotelList);
        }
    }

