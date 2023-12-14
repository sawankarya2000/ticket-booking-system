package com.hexaware.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Sports extends Event {
    String sportName;
    ArrayList<String> teamsName;

    public Sports(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venueName, int totalSeats, double ticketPrice, EventType eventType, String sportName, ArrayList<String> teamsName){
        super(eventName, eventDate, eventTime, venueName, totalSeats, ticketPrice, eventType);
        this.sportName = sportName;
        this.teamsName = teamsName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public ArrayList<String> getTeamsName() {
        return teamsName;
    }

    public void setTeamsName(ArrayList<String> teamsName) {
        this.teamsName = teamsName;
    }
    @Override
    public void displayEventDetails() {
        System.out.println("Event : " + super.getEventName());
        System.out.println("Event Type : " + EventType.SPORTS);
        System.out.println("Sports Name : " + this.sportName);
        System.out.println("Teams : " + this.teamsName.toString());
        System.out.println("Date : " + super.getEventDate());
        System.out.println("Time : " + super.getEventTime());
        System.out.println("Venue Name : " + super.getVenue().getVenueName());
        System.out.println("Seats Available : " + super.getAvailableSeats());
        System.out.println("Ticket Price : " + super.getTicketPrice());
    }
}
