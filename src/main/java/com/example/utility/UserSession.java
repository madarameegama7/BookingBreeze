package com.example.utility;

import com.example.models.User;
import com.example.models.UserRole;

//public class UserSession {
//    private static UserSession instance;
//    private User currentUser;
//    private int userID;
//
//    // Private constructor to prevent instantiation
//    private UserSession() {}
//
//    // Singleton method to get the instance of SessionManager
//    public static UserSession getInstance() {
//        if (instance == null) {
//            instance = new UserSession();
//        }
//        return instance;
//    }
//
//    // Getter for current user
//    public User getCurrentUser() {
//        return currentUser;
//    }
//
//    // Setter for current user
//    public void setCurrentUser(User user) {
//        this.currentUser = user;
//    }
//
//    // Getter for current user's userID (if you only need the ID)
//    public int getUserId() {
//        if (currentUser != null) {
//            return currentUser.getUserID();
//        }
//        return -1; // Return -1 if no user is logged in
//    }
//
//    // Method to clear the session (e.g., when logging out)
//    public void clearSession() {
//        this.currentUser = null;
//    }
//
//    public void setUserId(int userId) {
//        this.userID = userId;
//
//    }
//
//}
public class UserSession {
    private static UserSession instance;
    private int userId;
    private UserRole userRole;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}

