package com.example.utility;

public class HotelSession {

        private static HotelSession instance;
        private int userId;


        private HotelSession() {}

        public static HotelSession getInstance() {
            if (instance == null) {
                instance = new HotelSession();
            }
            return instance;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return userId;
        }

    }
