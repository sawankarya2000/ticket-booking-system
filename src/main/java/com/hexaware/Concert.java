package main.java.com.hexaware;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event{
    private String artist;
    private String type;

    Concert(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venueName, int totalSeats, double ticketPrice, EventType eventType, String artist, String type){
        super(eventName, eventDate, eventTime, venueName, totalSeats, ticketPrice, eventType);
        this.artist = artist;
        this.type = type;
    }

    public String getArtist() {
        return artist;
    }
    public String getType() {
        return type;
    }


    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void displayEventDetails() {
        System.out.println("Event : " + super.getEventName());
        System.out.println("Event Type: " + EventType.CONCERT);
        System.out.println("Artist: " + this.artist);
        System.out.println("Concert Type: " + this.type);
        System.out.println("Date : " + super.getEventDate());
        System.out.println("Time : " + super.getEventTime());
        System.out.println("Venue Name : " + super.getVenueName());
        System.out.println("Seats Available : " + super.getAvailableSeats());
        System.out.println("Ticket Price : " + super.getTicketPrice());
    }
}
