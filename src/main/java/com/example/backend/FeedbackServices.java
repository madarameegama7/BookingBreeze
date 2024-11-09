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
        String sql = "SELECT userID, feedbackID, username, feedback, userEmail FROM feedback";
        List<Feedback> feedbacks = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                int feedbackID = resultSet.getInt("feedbackID");
                String username = resultSet.getString("username");
                String feedback = resultSet.getString("feedback");
                String userEmail = resultSet.getString("userEmail");

                Feedback feedback1 = new Feedback(userID, feedbackID, username, feedback , userEmail);
                feedbacks.add(feedback1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

}
