package com.hexaware.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event {
    private String genre;
    private String actorName;
    private String actressName;

    public Movie(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venueName, int totalSeats, double ticketPrice, EventType eventType, String genre, String actorName, String actressName){
        super(eventName, eventDate, eventTime, venueName, totalSeats, ticketPrice, eventType);
        this.genre = genre;
        this.actorName = actorName;
        this.actressName = actressName;
    }

    public String getGenre() {
        return genre;
    }
    public String getActorName() {
        return actorName;
    }

    public String getActressName() {
        return actressName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setActressName(String actressName) {
        this.actressName = actressName;
    }

    @Override
    public void displayEventDetails() {
        System.out.println("Event : " + super.getEventName());
        System.out.println("Event Type: " + EventType.MOVIE);
        System.out.println("Genre: " + this.genre);
        System.out.println("Actor Name : " + this.actorName);
        System.out.println("Actress Name : " + this.actressName);
        System.out.println("Date : " + super.getEventDate());
        System.out.println("Time : " + super.getEventTime());
        System.out.println("Venue Name : " + super.getVenue().getVenueName());
        System.out.println("Seats Available : " + super.getAvailableSeats());
        System.out.println("Ticket Price : " + super.getTicketPrice());
    }

}
