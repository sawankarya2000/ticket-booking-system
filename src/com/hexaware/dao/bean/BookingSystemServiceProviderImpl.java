package com.hexaware.dao.bean;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.exception.InvalidBookingIDException;
import com.hexaware.dao.service.IBookingSystemServiceProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class BookingSystemServiceProviderImpl extends EventServiceProviderImpl implements IBookingSystemServiceProvider {
    private LinkedHashSet<Booking> bookings;

    private static int count = 0;

    public BookingSystemServiceProviderImpl() {
        bookings = new LinkedHashSet<>();
    }

    public double calculateBookingCost(Event event, int numTickets) {
        BookingApp booking = new BookingApp(event);
        return booking.calculateBookingCost(numTickets);
    }
    @Override
    public Booking bookTickets(Event event, int numTickets, ArrayList<Customer> customers) {
        BookingApp bookingApp = new BookingApp(event);
        bookingApp.bookTickets(numTickets);
        Booking booking = new Booking(count++, event, numTickets, calculateBookingCost(event, numTickets), LocalDate.now(), customers);
        bookings.add(booking);
        return booking;
    }

    public boolean cancelBooking(int bookingId) throws InvalidBookingIDException {
        for(Booking currentBooking : bookings) {
            if(currentBooking.getBookingId() == bookingId) {
                int numTickets = currentBooking.getNumTickets();
                Event event = currentBooking.getEvent();
                event.cancelBooking(numTickets);
                return true;
            }
        }
        throw new InvalidBookingIDException(bookingId);
    }

    public Booking getBookingDetails(int bookingId) {
        for(Booking currentBooking : bookings) {
            if(currentBooking.getBookingId() == bookingId) {
                return currentBooking;
            }
        }
        return null;
    }

    public LinkedHashSet<Booking> getBookings() {
        return bookings;
    }
}
