package com.example.utility;

public class ReservationSession {

    private static ReservationSession instance;
    private static int userId;


    private ReservationSession() {}

    public static ReservationSession getInstance() {
        if (instance == null) {
            instance = new ReservationSession();
        }
        return instance;
    }

    public static void setUserId(int userId) {
        userId = userId;
    }

    public static int getUserId() {
        return userId;
    }

}
