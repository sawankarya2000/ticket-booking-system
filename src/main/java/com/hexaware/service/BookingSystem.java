package main.java.com.hexaware.service;

import main.java.com.hexaware.bean.Event;
import main.java.com.hexaware.bean.EventType;
import main.java.com.hexaware.bean.Venue;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class BookingSystem {
    public abstract Event createEvent(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venueName, int totalSeats, double ticketPrice, EventType eventType);

    public abstract void displayEventDetails(Event event);

    public abstract void bookTickets(Event event, int numTickets);

    public abstract boolean cancelTickets(Event event, int numTickets);
}
