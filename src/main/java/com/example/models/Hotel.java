package com.example.models;

public class Hotel {
    private String hotelName;
    private String hotelAddress;
    private String hotelCity;
    private String contactnumber;
    private String facilities;

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
