package com.hexaware.entity;

public class Venue {
    private String venueName;
    private Address address;

    Venue(String venueName, String street, String city, String state, String zipCode) {
        this.venueName = venueName;
        this.address = new Address(street, city, state, zipCode);
    }
    public Venue(String venueName, String address) {
        this.venueName = venueName;
        this.address = new Address(address);
    }

    // Getter methods
    public String getVenueName(){
        return venueName;
    }

    public void setVenueName(String venueName){
        this.venueName = venueName;
    }

    public Address getAddress(){
        return address;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    //Display Venue Details
    public void displayVenueDetails() {
        System.out.println("Venue Name : " + venueName);
        System.out.println("Venue Address : " + address.getFormattedAddress());
    }

}
