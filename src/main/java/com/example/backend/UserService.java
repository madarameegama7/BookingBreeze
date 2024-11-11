package com.example.backend;

import com.example.models.Hotel;
import com.example.models.UserRole;
import com.example.utility.HotelSession;
import com.example.utility.UserSession;
import com.example.models.User;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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


    public UserRole login(String email, String password) {
        String sql = "SELECT userID, username, userRole FROM user WHERE email = ? AND password = ?";
        String hashedPassword = hashPassword(password);

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("userID");
                String username = resultSet.getString("username");
                String roleString = resultSet.getString("userRole").toUpperCase();
                UserRole role = UserRole.valueOf(roleString);

                HotelSession.getInstance().setUserId(userId);
                HotelSession.getInstance().setUsername(username);// Store userId in session
                UserSession.getInstance().setUserRole(role);  // Store userRole in session

                System.out.println("User ID set in session: " + userId);
                System.out.println("Username set in session: " + username);
                System.out.println("UserRole set in session: " + role);

                return role; // Return the user's role
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null; // Return null if login fails
    }

    public static User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE userID = ?";
        User user = null;


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String contactNumber = resultSet.getString("contactNumber");
                String address = resultSet.getString("address");

                user = new User(email, username, contactNumber, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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

    public static boolean deleteUserById(int userId) {
        String sql = "DELETE FROM user WHERE userID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateUser(User user) {
        String sql = "UPDATE user SET username = ?, email = ?, contactNumber = ?, address = ? WHERE userID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getContactNumber());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setInt(5, user.getUserID());

            System.out.println("Executing update: " + preparedStatement.toString()); // Debug statement

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            if (rowsAffected > 0) {
                // Check if the updated userID matches the current session's userID
                if (HotelSession.getInstance().getUserId() == user.getUserID()) {
                    // Update the username in the session
                    HotelSession.getInstance().setUsername(user.getUsername());
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }return false;
    }
}



