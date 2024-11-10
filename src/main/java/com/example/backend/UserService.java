package com.example.backend;

import com.example.models.User;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

    public boolean signup(String email, String username, String password, String contactNumber, String address) {
        String hashedPassword = hashPassword(password);
        String sql = "INSERT INTO user (email, username, password, contactNumber, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.setString(4, contactNumber);
            preparedStatement.setString(5, address);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int login(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        String hashedPassword = hashPassword(password);

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("userID");  // return the user ID if login is successful
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return -1; // return -1 if login fails
    }

    public boolean isEmailValid(String email) {
        return email.contains("@") && email.endsWith(".com");
    }

    public boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
