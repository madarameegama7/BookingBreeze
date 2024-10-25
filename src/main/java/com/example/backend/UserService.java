package com.example.backend;

import com.example.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // Method to register a new user in the database
    public boolean signup(String email, String username, String password) {
        String sql = "INSERT INTO user (email, username, password) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);

            int result = preparedStatement.executeUpdate();
            return result > 0;  // Returns true if the user was successfully inserted

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to check if user credentials are valid
    public boolean login(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();  // Returns true if user credentials match

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
