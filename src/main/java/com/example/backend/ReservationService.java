package com.example.backend;

import com.example.models.Reservation;
import com.example.utility.HotelSession;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    public int addReservation(String hotelName, String roomType, int roomID, int count, LocalDate date, LocalTime arrivalTime, LocalTime departureTime) {
        String query = "INSERT INTO reservation (username, hotelName, roomType, roomID, userID, count, date, arrivalTime, departureTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int userID = HotelSession.getInstance().getUserId();
        String username = HotelSession.getInstance().getUsername();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hotelName);
            preparedStatement.setString(3, roomType);
            preparedStatement.setInt(4, roomID);
            preparedStatement.setInt(5, userID);
            preparedStatement.setInt(6, count);
            preparedStatement.setString(7, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            preparedStatement.setTime(8, Time.valueOf(arrivalTime));
            preparedStatement.setTime(9, Time.valueOf(departureTime));

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int reservationId = generatedKeys.getInt(1);
                        System.out.println("Generated Reservation ID: " + reservationId);
                        return reservationId; // Return reservation ID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if insertion fails
    }



    public static List<Reservation> getReservationDetails(int reservationID) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation WHERE reservationID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, reservationID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(resultSet.getInt("reservationID"));
                reservation.setHotelName(resultSet.getString("hotelName"));
                reservation.setRoomType(resultSet.getString("roomType"));
                reservation.setRoomID(resultSet.getInt("roomID"));
                reservation.setGuestCount(resultSet.getInt("count"));
                reservation.setDate(resultSet.getDate("date").toLocalDate());
                reservation.setArrivalTime(resultSet.getTime("arrivalTime").toLocalTime());
                reservation.setDepartureTime(resultSet.getTime("departureTime").toLocalTime());
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Method to update an existing reservation
    public static boolean updateReservation(Reservation reservation) {
        String query = "UPDATE reservation SET hotelName = ?, roomType = ?, roomID = ?, count = ?, date = ?, arrivalTime = ?, departureTime = ? WHERE reservationID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, reservation.getHotelName());
            preparedStatement.setString(2, reservation.getRoomType());
            preparedStatement.setInt(3, reservation.getRoomID());
            preparedStatement.setInt(4, reservation.getGuestCount());
            preparedStatement.setString(5, reservation.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            preparedStatement.setString(6, reservation.getArrivalTime().toString());
            preparedStatement.setString(7, reservation.getDepartureTime().toString());
            preparedStatement.setInt(8, reservation.getReservationId());

            System.out.println("Executing query: " + preparedStatement.toString()); // This will print the query with the parameters System.out.println("Reservation ID: "
            System.out.println("Reservation ID: " + reservation.getReservationId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a reservation
    public static boolean deleteReservation(int reservationId) {
        String query = "DELETE FROM reservation WHERE reservationID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reservationId);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
