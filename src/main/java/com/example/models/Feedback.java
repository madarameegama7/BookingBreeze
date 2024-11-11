package com.example.models;

public class Feedback {
        int feedbackID;
        private int userID;
        private String username;
        private String feedback;
        private String email;

        // Constructor
        public Feedback(int userID, String username, String feedback, String email) {
            this.userID = userID;
            this.username = username;
            this.feedback = feedback;
            this.email = email;
        }

        // Getters and Setters
        public int getFeedbackID() {
            return feedbackID;
        }

        public void setFeedbackID(int feedbackID) {
            this.feedbackID = feedbackID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public String getUserEmail() {
            return email;
        }

        public void setUserEmail(String userEmail) {
            this.email = email;
        }

}

