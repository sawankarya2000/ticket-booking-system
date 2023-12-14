package com.hexaware.dao.service;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.exception.InvalidBookingIDException;
import com.hexaware.entity.Booking;

import java.util.ArrayList;

public interface IBookingSystemServiceProvider {
    double calculateBookingCost(Event event, int numTickets);
    Booking bookTickets(Event event, int numTickets, ArrayList<Customer> customers);
    boolean cancelBooking(int bookingId)  throws InvalidBookingIDException;
    Booking getBookingDetails(int bookingId);

}
