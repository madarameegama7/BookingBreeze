package com.example.backend;

import com.example.backend.DatabaseConnection;
import com.example.models.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    public boolean processPayment(int discountID, int userID, int reservationID, double payment, String paymentType, Date paymentDate, String paymentStatus) {

        String sql = "INSERT INTO payment (discountID, userID, reservationID, payment, paymentType, paymentDate, paymentStatus) VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, discountID);
            preparedStatement.setInt(2, userID);
            preparedStatement.setInt(3, reservationID);
            preparedStatement.setDouble(4, payment);
            preparedStatement.setString(5, paymentType);
            preparedStatement.setDate(6, paymentDate);
            preparedStatement.setString(7, paymentStatus);

            int result = preparedStatement.executeUpdate();
            return result > 0;  // Returns true if the user was successfully inserted

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Integer getDiscountIDByUserID(int userID) throws SQLException {
        Integer discountID = null;
        String query = "SELECT discountID FROM discount WHERE userID = ?"; // Adjust table and column names as needed

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                discountID = rs.getInt("discountID");
            }
        }
        return discountID;
    }

    public static Integer getRiservationIDByUserID(int userID) throws SQLException {
        Integer reservationID = null;
        String query = "SELECT reservationID FROM reservation WHERE userID = ?"; // Adjust table and column names as needed

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reservationID  = rs.getInt("reservationID");
            }
        }
        return reservationID;
    }
    public static Integer getPaymentIDByreservationID(int reservationID) throws SQLException {
        Integer paymentID = null;
        String query = "SELECT paymentID FROM payment WHERE reservationID = ?"; // Adjust table and column names as needed

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservationID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paymentID = rs.getInt("paymentID");
            }
        }
        return paymentID;
    }

    public static List<Payment> viewPayments() {
        String sql = "SELECT paymentID, discountID, reservationID, userID, paymentType, payment, paymentStatus, paymentDate FROM payment";
        List<Payment> hotels = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int paymentID = resultSet.getInt("paymentID");
                int discountID = resultSet.getInt("discountID");
                int reservationID = resultSet.getInt("reservationID");
                int userID = resultSet.getInt("userID");
                String paymentType = resultSet.getString("paymentType");
                double payment = resultSet.getDouble("payment");
                Date paymentDate = resultSet.getDate("paymentDate");
                String paymentStatus = resultSet.getString("paymentStatus");


                Payment payment1 = new Payment(paymentID, discountID, userID,reservationID,payment, paymentType, paymentDate, paymentStatus);
                hotels.add(payment1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    public static List<Payment> retrievePayementDetails(int paymentID) {
        String sql = "SELECT * FROM payment WHERE paymentID = ?";

        List<Payment> payment1 = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, paymentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int PaymentID = resultSet.getInt("paymentID");
                int discountID = resultSet.getInt("discountID");
                int reservationID = resultSet.getInt("reservationID");
                int userID = resultSet.getInt("userID");
                String paymentType = resultSet.getString("paymentType");
                double payment = resultSet.getDouble("payment");
                Date paymentDate = resultSet.getDate("paymentDate");
                String paymentStatus = resultSet.getString("paymentStatus");

                Payment payment2 = new Payment(paymentID, discountID, userID,reservationID,payment, paymentType, paymentDate, paymentStatus);
                payment1.add(payment2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment1;
    }

    public static boolean deletePayementByID(int paymentID) {
        String sql = "DELETE FROM payment WHERE paymentID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, paymentID);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updatePaymentDetails(Payment payment) {
        String sql = "UPDATE payment SET paymentType = ?, paymentDate = ?, paymentStatus = ?, payment = ? WHERE paymentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, payment.getPaymentType());
            preparedStatement.setDate(2, payment.getPaymentDate());
            preparedStatement.setString(3, payment.getPaymentStatus());
            preparedStatement.setDouble(4, payment.getPayment());
            preparedStatement.setInt(5, payment.getPaymentID());


            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
