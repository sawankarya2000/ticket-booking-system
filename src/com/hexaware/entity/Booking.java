package com.hexaware.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
    private int bookingId;
    private ArrayList<Customer> customers;
    private Event event;
    private int numTickets;
    private double totalCost;
    private LocalDate bookingDate;

    public Booking(int bookingId, Event event, int numTickets, double totalCost, LocalDate bookingDate, ArrayList<Customer> customers) {
        this.bookingId = bookingId;
        this.event = event;
        this.numTickets = numTickets;
        this.totalCost = totalCost;
        this.bookingDate = bookingDate;
        this.customers = customers;

        // Check array size equals tickets or not
        if (customers.size() != numTickets) {
            throw new IllegalArgumentException("Number of customers must be the same as numTickets");
        }
    }

    // Getter and Setter methods
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    //Display booking details
    public void displayBookingDetails() {
        event.displayEventDetails();
        for(int i = 0 ; i < numTickets ; i++) {
            customers.get(i).displayCustomerDetails();
        }
        System.out.println("Tickets : " + numTickets);
        System.out.println("Total Cost : " + totalCost);
        System.out.println("Booking Date : " + bookingDate);
    }
}
