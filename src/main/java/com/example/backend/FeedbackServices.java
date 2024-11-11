package com.example.backend;

import com.example.models.Feedback;
import com.example.models.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackServices {

    public static List<Feedback> viewFeedback() {
        String sql = "SELECT userID, username, email, feedback FROM feedback";
        List<Feedback> feedbacks = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String feedback = resultSet.getString("feedback");


                Feedback feedback1 = new Feedback(userID, username, email , feedback);
                feedbacks.add(feedback1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }
    // In FeedbackServices.java
    public static String getUsernameByUserId(int userId) {
        String sql = "SELECT username FROM user WHERE userID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getEmailByUserId(int userId) {
        String sql = "SELECT email FROM user WHERE userID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // In FeedbackServices.java

    public static boolean insertFeedback(int userId, String username, String email, String feedback) {
        String sql = "INSERT INTO feedback (userID, username, email, feedback) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the values for the placeholders in the SQL query
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, feedback);

            // Execute the update (this will insert the feedback into the database)
            int rowsAffected = preparedStatement.executeUpdate();

            // If one or more rows are affected, the insertion was successful
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if there is an issue with the insertion
    }



}
