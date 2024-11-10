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

}
