package main.java.com.hexaware.bean;

import main.java.com.hexaware.service.IBookingSystemServiceProvider;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookingSystemServiceProviderImpl extends EventServiceProviderImpl implements IBookingSystemServiceProvider {
    private ArrayList<Booking> bookings;

    private static int count = 0;

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

    public boolean cancelBooking(int bookingId) {
        for(int i = 0 ; i<bookings.size() ; i++) {
            Booking currentBooking = bookings.get(i);
            if(currentBooking.getBookingId() == bookingId) {
                int numTickets = currentBooking.getNumTickets();
                Event event = currentBooking.getEvent();
                event.cancelBooking(numTickets);
                return true;
            }
        }
        return false;
    }

    public Booking getBookingDetails(int bookingId) {
        for(int i = 0 ; i<bookings.size() ; i++) {
            Booking currentBooking = bookings.get(i);
            if(currentBooking.getBookingId() == bookingId) {
                return currentBooking;
            }
        }
        return null;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}
