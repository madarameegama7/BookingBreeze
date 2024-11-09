package com.example.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // Method to register a new user with hashed password and additional fields
    public boolean signup(String email, String username, String password, String contactNumber, String address) {
        if (!isEmailValid(email) || !isPasswordValid(password)) {
            return false;  // Fail if validation fails
        }

        //String hashedPassword = hashPassword(password);
        String sql = "INSERT INTO user (email, userName, password, contactNumber, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, contactNumber);
            preparedStatement.setString(5, address);

            int result = preparedStatement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to validate email format
    public boolean isEmailValid(String email) {
        return email.contains("@") && email.endsWith(".com");
    }

    // Method to validate password length
    public boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

    // Method to check if user credentials are valid (with hashed password)
    public boolean login(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            //String hashedPassword = hashPassword(password); // Hash input password
            System.out.println("Logging in with email: " + email + " and hashed password: " + password);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Login successful for user: " + email);
                return true;
            } else {
                System.out.println("Login failed: No matching user found for email " + email);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
