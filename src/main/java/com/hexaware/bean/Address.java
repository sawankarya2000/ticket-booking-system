package main.java.com.hexaware.bean;

public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;

    Address(String street, String city, String state, String zipCode){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    Address(Address address){
        this.street = address.street;
        this.city = address.city;
        this.state = address.state;
        this.zipCode = address.zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFormattedAddress() {
        return (street + ", " + city + ", " + state + ", " + zipCode);
    }

}
