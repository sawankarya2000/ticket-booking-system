package main.java.com.hexaware.service;

import main.java.com.hexaware.bean.Booking;
import main.java.com.hexaware.bean.Customer;
import main.java.com.hexaware.bean.Event;

import java.util.ArrayList;

public interface IBookingSystemServiceProvider {
    double calculateBookingCost(Event event, int numTickets);
    Booking bookTickets(Event event, int numTickets, ArrayList<Customer> customers);
    boolean cancelBooking(int bookingId);
    Booking getBookingDetails(int bookingId);

}
