package com.hexaware.dao.bean;


import com.hexaware.entity.*;
import com.hexaware.dao.service.IEventServiceProvider;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class EventServiceProviderImpl implements IEventServiceProvider {

    @Override
    public Movie createEvent(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName, String venueAddress, int totalSeats, double ticketPrice, EventType eventType, String genre, String actorName, String actressName) {
        Venue venue = new Venue(venueName, venueAddress);
        return new Movie(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, eventType, genre, actorName, actressName);
    }

    @Override
    public Concert createEvent(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName, String venueAddress, int totalSeats, double ticketPrice, EventType eventType, String artist, String type) {
        Venue venue = new Venue(venueName, venueAddress);
        return new Concert(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, eventType, artist, type);
    }

    @Override
    public Sports createEvent(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName, String venueAddress, int totalSeats, double ticketPrice, EventType eventType, String sportName, ArrayList<String> teamsName) {
        Venue venue = new Venue(venueName, venueAddress);
        return new Sports(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, eventType, sportName, teamsName);
    }

    @Override
    public List<String> getEventDetails(Event event) {
        List<String> eventDetails = new ArrayList<>();
        eventDetails.add("Event Name: " + event.getEventName());
        eventDetails.add("Event Date: " + event.getEventDate());
        eventDetails.add("Event Time: " + event.getEventTime());
        eventDetails.add("Venue: " + event.getVenue().getVenueName());
        eventDetails.add("Total Seats: " + event.getTotalSeats());
        eventDetails.add("Ticket Price: " + event.getTicketPrice());
        eventDetails.add("Event Type: " + event.getEventType());
        return eventDetails;
    }

    @Override
    public int getAvailableNoOfTickets(Event event) {
        return event.getAvailableSeats();
    }
}
