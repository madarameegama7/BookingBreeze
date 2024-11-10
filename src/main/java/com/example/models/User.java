package com.example.models;

public class User {

    private int userID;
    private String email;
    private String username;
    private String contactNumber;
    private String address;

    public User(String email, String username, String contactNumber, String address) {
        this.email = email;
        this.username = username;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public int getUserID(){return userID;}

    // Getters
    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setUserID(int userID){this.userID=userID;}
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
