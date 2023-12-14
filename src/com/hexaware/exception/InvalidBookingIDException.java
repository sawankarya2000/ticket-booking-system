package com.hexaware.exception;

public class InvalidBookingIDException extends Exception {
    public InvalidBookingIDException(int bookingID) {
        super("Invalid Booking ID: " + bookingID);
    }
}
