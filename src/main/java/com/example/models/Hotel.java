    package com.example.models;

    public class Hotel {

        private int userID;
        private int hotelID;       // Add this field
        private String hotelName;
        private String registrationNo;
        private String location;
        private String contactNum;
        private String facilities;
        private byte[] hotelImages;

        public Hotel(int hotelID, int userID, String hotelName,String registrationNo, String location, String contactNum, String facilities, byte[] hotelImages) {
            this.hotelID = hotelID;
            this.userID = userID;
            this.hotelName = hotelName;
            this.registrationNo = registrationNo;
            this.location = location;
            this.contactNum = contactNum;
            this.facilities = facilities;
            this.hotelImages = hotelImages;
        }

        public int getHotelID() {
            return hotelID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public void setHotelID(int hotelID) {
            this.hotelID = hotelID;
        }

        public String getHotelName() {
            return hotelName;
        }

        public void setHotelName(String hotelName) {
            this.hotelName = hotelName;
        }

        public String getRegistrationNo() {
            return registrationNo;
        }

        public void setRegistrationNo(String registrationNumber) {
            this.registrationNo = registrationNumber;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getContactNum() {
            return contactNum;
        }

        public void setContactNum(String contactNum) {
            this.contactNum = contactNum;
        }

        public String getFacilities() {
            return facilities;
        }

        public void setFacilities(String facilities) {
            this.facilities = facilities;
        }

        public byte[] getHotelImages() {
            return hotelImages;
        }

        public void setHotelImages(byte[] hotelImages) {
            this.hotelImages = hotelImages;
        }
    }
