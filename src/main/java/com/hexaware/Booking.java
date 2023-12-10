package main.java.com.hexaware;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Booking {
    private Event event;
    private int numTickets;
    private double totalCost;

    public Booking(Event event) {
        this.event = event;
    }

    public void calculateBookingCost(int numTickets) {
        this.numTickets = numTickets;
        this.totalCost = numTickets * event.getTicketPrice();
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
        Booking booking = new Booking(event);

        Scanner scan = new Scanner(System.in);

        event.displayEventDetails();

        System.out.println("How many ticket do you want to book ? ");
        int numTickets = scan.nextInt();
        scan.nextLine();

        System.out.println("Available seats: " + event.getAvailableSeats());

        //Calculate booking cost
        booking.calculateBookingCost(numTickets);

        System.out.println(booking.getTotalCost());

        if (booking.bookTickets(numTickets)) {
            System.out.println("Booked " + numTickets + " tickets.");
        } else {
            System.out.println("Not enough seats available.");
        }

        System.out.println("Event details: " + booking.getEventDetails());
        System.out.println("Total cost: " + booking.getTotalCost());
        System.out.println("Number of tickets: " + booking.getNumTickets());


        booking.cancelBooking(1);
        System.out.println("Available seats: " + event.getAvailableSeats());

    }
}
