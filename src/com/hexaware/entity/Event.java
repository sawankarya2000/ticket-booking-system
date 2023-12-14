package com.hexaware.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Event {
    private String eventName;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private Venue venue;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;
    private EventType eventType;

    public Event(String eventName, LocalDate eventDate, LocalTime eventTime, int totalSeats, double ticketPrice, EventType eventType){
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }
    public Event(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue, int totalSeats, double ticketPrice, EventType eventType){
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setvenue(Venue venue) {
        this.venue = venue;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        int diff = totalSeats - this.totalSeats ;
        this.totalSeats = totalSeats;
        this.availableSeats += diff;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int bookedNumberOfTickets() {
        return (this.totalSeats - this.availableSeats);
    }
    public double calculateTotalRevenue() {
        return this.ticketPrice * (bookedNumberOfTickets());
    }

    public boolean bookTickets(int numberOfTickets) {
        if(this.availableSeats < numberOfTickets){
            return false;
        }
        this.availableSeats -=  numberOfTickets;
        return true;
    }

    public boolean cancelBooking(int numberOfTickets){
        if(numberOfTickets > (this.totalSeats - this.availableSeats)) {
            return false;
        }
        this.availableSeats += numberOfTickets;
        return true;
    }

    public abstract void displayEventDetails();

//        System.out.println("Event : " + this.eventName);
//        System.out.println("Date : " + this.eventDate);
//        System.out.println("Time : " + this.eventTime);
//        System.out.println("Venue Name : " + this.venue);
//        System.out.println("Seats Available : " + this.availableSeats);
//        System.out.println("Ticket Price : " + this.ticketPrice);


}
