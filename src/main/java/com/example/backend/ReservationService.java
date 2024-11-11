package com.example.backend;

import com.example.models.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private final DatabaseConnection dbConnection = new DatabaseConnection();

    public boolean addReservation(String username, String hotelName, String roomType, String roomID, int userID, int count,
                                  LocalDate date, String arrivalTime, String departureTime) {
        String query = "INSERT INTO reservation (username, hotelName, roomType, roomID, userID, count, date, arrivalTime, departureTime) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hotelName);
            preparedStatement.setString(3, roomType);
            preparedStatement.setString(4, roomID);
            preparedStatement.setInt(5, userID);
            preparedStatement.setInt(6, count);
            preparedStatement.setString(7, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            preparedStatement.setString(8, arrivalTime);
            preparedStatement.setString(9, departureTime);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Reservation> getReservationsByUserId(int userId) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation WHERE userID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setHotelName(resultSet.getString("hotelName"));
                reservation.setRoomType(resultSet.getString("roomType"));
                reservation.setRoomID(resultSet.getString("roomID"));
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
    public boolean updateReservation(Reservation reservation) {
        String query = "UPDATE reservation SET hotelName = ?, roomType = ?, roomID = ?, count = ?, date = ?, " +
                "arrivalTime = ?, departureTime = ? WHERE userID = ? AND roomID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, reservation.getHotelName());
            preparedStatement.setString(2, reservation.getRoomType());
            preparedStatement.setString(3, reservation.getRoomID());
            preparedStatement.setInt(4, reservation.getGuestCount());
            preparedStatement.setString(5, reservation.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            preparedStatement.setString(6, reservation.getArrivalTime().toString());
            preparedStatement.setString(7, reservation.getDepartureTime().toString());
            preparedStatement.setInt(8, reservation.getUserId());
            preparedStatement.setString(9, reservation.getRoomID());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a reservation
    public boolean deleteReservation(int reservationId) {
        String query = "DELETE FROM reservation WHERE id = ?";

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
