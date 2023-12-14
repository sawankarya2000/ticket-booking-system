package com.hexaware.dao.repo;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.entity.Venue;

import java.util.List;

public interface IBookingSystemRepository {

    Event createEvent(String eventName, String date, String time, int totalSeats,
                      float ticketPrice, String eventType, Venue venue);

    List<Event> getEventDetails();

    int getAvailableNoOfTickets();

    void calculateBookingCost( Event event, int numTickets);

    void bookTickets(Event event, int numTickets, List<Customer> listOfCustomers);

    void cancelBooking(int bookingId);

    Booking getBookingDetails(int bookingId);
}
