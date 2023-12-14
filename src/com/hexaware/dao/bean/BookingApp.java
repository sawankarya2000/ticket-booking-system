package com.hexaware.dao.bean;

import com.hexaware.entity.Event;
import com.hexaware.entity.EventType;
import com.hexaware.entity.Movie;
import com.hexaware.entity.Venue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class BookingApp {
    private Event event;
    private int numTickets;
    private double totalCost;

    public BookingApp(Event event) {
        this.event = event;
    }

    public double calculateBookingCost(int numTickets) {
        this.numTickets = numTickets;
        this.totalCost = numTickets * event.getTicketPrice();
        return totalCost;
    }

    public boolean bookTickets(int numTickets) {
        if (event.bookTickets(numTickets)) {
            calculateBookingCost(numTickets);
            return true;
        }
        return false;
    }

    public boolean cancelBooking(int numTickets) {
        return event.cancelBooking(numTickets);
    }

    public int getAvailableNoOfTickets() {
        return event.getAvailableSeats();
    }

    public String getEventDetails() {
        return event.getEventName() + " - " + event.getEventDate() + " " + event.getEventTime() + " (" + event.getVenue() + ")";
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public static void main(String[] args) {
        Venue venue = new Venue("Walkway Mall", "Haldwani");
        Event event = new Movie("Animal", LocalDate.of(2023, 12, 07), LocalTime.of(19, 00), venue, 100, 10.00, EventType.MOVIE, "Action", "Ranbir Kapoor", "Rashmika Mandana");
        BookingApp bookingApp = new BookingApp(event);

        Scanner scan = new Scanner(System.in);

        event.displayEventDetails();

        System.out.println("How many ticket do you want to book ? ");
        int numTickets = scan.nextInt();
        scan.nextLine();

        System.out.println("Available seats: " + event.getAvailableSeats());

        //Calculate booking cost
        bookingApp.calculateBookingCost(numTickets);

        System.out.println(bookingApp.getTotalCost());

        if (bookingApp.bookTickets(numTickets)) {
            System.out.println("Booked " + numTickets + " tickets.");
        } else {
            System.out.println("Not enough seats available.");
        }

        System.out.println("Event details: " + bookingApp.getEventDetails());
        System.out.println("Total cost: " + bookingApp.getTotalCost());
        System.out.println("Number of tickets: " + bookingApp.getNumTickets());


        bookingApp.cancelBooking(1);
        System.out.println("Available seats: " + event.getAvailableSeats());

    }
}
