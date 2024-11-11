package com.example.backend;

import com.example.models.Hotel;
import com.example.utility.HotelSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelServices {


    // Method to register a new user in the database
    public boolean addHotel(String hotelName, String registrationNo, String location, String contactNum, String facilities, byte[] hotelImages) {
        int userID = HotelSession.getInstance().getUserId();
      
        String sql = "INSERT INTO hotel (userID, hotelName, registrationNo, location, contactNum, facilities, hotelImages  ) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, hotelName);
            preparedStatement.setString(3, registrationNo);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, contactNum);
            preparedStatement.setString(6, facilities);
            preparedStatement.setBytes(7, hotelImages);

            int result = preparedStatement.executeUpdate();
            return result > 0;  // Returns true if the user was successfully inserted

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Hotel> viewHotels() {
        String sql = "SELECT userID, hotelID, hotelName, registrationNo, location, contactNum, facilities, hotelImages FROM hotel";
        List<Hotel> hotels = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                int hotelID = resultSet.getInt("hotelID");
                String hotelName = resultSet.getString("hotelName");
                String registrationNo = resultSet.getString("registrationNo");
                String location = resultSet.getString("location");
                String contactNum = resultSet.getString("contactNum");
                String facilities = resultSet.getString("facilities");
                byte[] hotelImages = resultSet.getBytes("hotelImages");

                Hotel hotel = new Hotel(userID, hotelID, hotelName, registrationNo, location, contactNum, facilities, hotelImages);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    public static List<Hotel> retrieveHotelDetails(String hotelName) {
        String sql = "SELECT * FROM hotel WHERE hotelName LIKE ?";

        List<Hotel> hotels = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + hotelName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userID = resultSet.getInt("userId");
                int hotelID = resultSet.getInt("hotelId");
                String hotelNameResult = resultSet.getString("hotelName");
                String registrationNo = resultSet.getString("registrationNo");
                String location = resultSet.getString("location");
                String contactNum = resultSet.getString("contactNum");
                String facilities = resultSet.getString("facilities");
                byte[] hotelImages = resultSet.getBytes("hotelImages");

                Hotel hotel = new Hotel(hotelID, userID, hotelNameResult, registrationNo, location, contactNum, facilities, hotelImages);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }


        public static boolean deleteHotelByName(String hotelName) {
            String sql = "DELETE FROM hotel WHERE hotelName LIKE ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, "%" + hotelName + "%");
                int rowsDeleted = preparedStatement.executeUpdate();
                return rowsDeleted > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public static boolean updateHotelDetails(Hotel hotel) {
            String sql = "UPDATE hotel SET hotelName = ?, registrationNo = ?, location = ?, contactNum = ?, facilities = ?, hotelImages = ? WHERE hotelId = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, hotel.getHotelName());
                preparedStatement.setString(2, hotel.getRegistrationNo());
                preparedStatement.setString(3, hotel.getLocation());
                preparedStatement.setString(4, hotel.getContactNum());
                preparedStatement.setString(5, hotel.getFacilities());
                preparedStatement.setBytes(6, hotel.getHotelImages());
                preparedStatement.setInt(7, hotel.getHotelID());
                int rowsUpdated = preparedStatement.executeUpdate();
                return rowsUpdated > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    public static List<String> getAllHotelNames() {
        List<String> hotelNames = new ArrayList<>();
        String query = "SELECT hotelName FROM hotel";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                hotelNames.add(resultSet.getString("hotelName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Log or handle the exception as appropriate
        }

        return hotelNames;
    }
}






