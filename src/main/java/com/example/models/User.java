package com.example.models;

public class User {
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
}
