package com.hexaware.dao.repo;

import com.hexaware.entity.*;
import com.hexaware.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository {

    private List<Event> events = new ArrayList<>();
    private int availableTickets = 0;
    private List<Booking> bookings = new ArrayList<>();
    private Connection conn = DBUtil.getDBConn();

    public int createAddress(Venue venue) throws SQLException{
        String query;

        query = "INSERT INTO addresses (street, city, state, zipcode) VALUES ('?', '','','')";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, venue.getAddress().getStreet());

        int rowAffected = ps.executeUpdate();

        if(rowAffected > 0) {
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if(generatedKeys.next()) {
                return generatedKeys.getInt("address_id");
            } else {
                System.err.println("Failed to retrieve the generated address_id.");
            }
        }
        return -1;
    }

    public int createVenue(Venue venue) {
        try {
            int addressID;
            String query;
            PreparedStatement ps;
            ResultSet rs;

            query = "SELECT address_id FROM address WHERE street = ?";
            ps = conn.prepareStatement(query);

            ps.setString(2, venue.getAddress().getStreet());
            rs = ps.executeQuery();

            if (rs.next()) {
                addressID = rs.getInt("address_id");
            } else {
                addressID = createAddress(venue);

                if (addressID == -1)
                    return -1;
            }

            query = "INSERT INTO venues (venue_name, address_id) VALUES (?,?)";
            ps = conn.prepareStatement(query);

            ps.setString(1, venue.getVenueName());
            ps.setInt(2, addressID);

            int rowAffected = ps.executeUpdate();

            if (rowAffected > 0) {

                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.next()) {
                    return generatedKeys.getInt("venue_id");
                } else {
                    System.err.println("Failed to retrieve the generated address_id.");
                }
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public Event createEvent(String eventName, String date, String time, int totalSeats,
                             float ticketPrice, String eventType, Venue venue) {
        // Implementation to create a new event and store it in the database

        try  {
            int venueID;
            String query;
            PreparedStatement ps;
            ResultSet rs;

            query = "SELECT venue_id FROM address WHERE venue_name=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, venue.getVenueName());
            rs = ps.executeQuery();

            if(rs.next()){
                venueID = rs.getInt("venue_id");
            }else{
                int newVenueID = createVenue(venue);
                if(newVenueID == -1)
                    throw new SQLException("error");
                venueID = newVenueID;
            }
            query = "INSERT INTO events(event_name, event_date, event_time, venue_id, total_seats, available_seats," +
                    " ticket_price, event_type) " +
                    "VALUES(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, eventName);
            ps.setString(2, date);
            ps.setString(3, time);
            ps.setInt(4,venueID);
            ps.setInt(5, totalSeats);
            ps.setInt(6, availableTickets);
            ps.setDouble(7, ticketPrice);
            ps.setString(8, eventType);
            int rowAffected = ps.executeUpdate();

            if(rowAffected > 0) {
                System.out.println("Event saved in Database");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Event newEvent = new Event(eventName, date, time, totalSeats, ticketPrice, eventType, venue);
        events.add(newEvent);
        return newEvent;
    }

    @Override
    public List<Event> getEventDetails() {
        // Implementation to retrieve event details from the database
        List<Event> events = new ArrayList<>();
        try {
            String query = "SELECT * FROM events";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        String eventName = resultSet.getString("event_name");
                        LocalDate eventDate = resultSet.getDate("event_date").toLocalDate();
                        LocalTime eventTime = resultSet.getTime("event_time").toLocalTime();
                        int totalSeats = resultSet.getInt("total_seats");
                        int availableSeats = resultSet.getInt("available_seats");
                        double ticketPrice = resultSet.getDouble("ticket_price");
                        String eventType = resultSet.getString("event_type");

                        events.add(new Event(eventName, eventDate, eventTime,totalSeats, availableSeats, ticketPrice, EventType.valueOf(eventType)));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public int getAvailableNoOfTickets() {
        // Implementation to retrieve available tickets from the database
        int availableTickets = -1;
        String query = "SELECT SUM(available_tickets) FROM events";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.executeUpdate();
        }
        return availableTickets;
    }

    @Override
    public void calculateBookingCost(Event event, int numTickets) {
        String query = "SELECT ticket_price, available_seats FROM events WHERE event_name = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, event.getEventName());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Calculate booking cost
                int eventID = resultSet.getInt("event_id");
                double ticketPrice = resultSet.getDouble("ticket_price");
                int availableSeats = resultSet.getInt("available_seats");

                if (numTickets <= availableSeats) {
                    double totalCost = ticketPrice * numTickets;

                    // Step 4: Update the booking details in the database
                    String updateQuery = "UPDATE events SET available_seats = available_seats - ?, total_seats = total_seats - ? WHERE event_id = ?";
                    try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
                        updateStatement.setInt(1, numTickets);
                        updateStatement.setInt(2, numTickets);
                        updateStatement.setInt(3, eventID);
                        updateStatement.executeUpdate();

                        System.out.println("Booking cost calculated: " + totalCost);
                    }
                } else {
                    System.out.println("Not enough available seats for booking.");
                }
            } else {
                System.out.println("Event not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void bookTickets(Event event, int numTickets, List<Customer> listOfCustomers) {
        try {

            // Query the database
            String query = "SELECT event_id, available_seats, ticket_price FROM events WHERE event_name = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, event.getEventName());
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int eventID = resultSet.getInt("event_id");
                    int availableSeats = resultSet.getInt("available_seats");
                    double ticketPrice = resultSet.getDouble("ticket_price");

                    if (numTickets <= availableSeats) {
                        for (Customer customer : listOfCustomers) {
                            // Insert customer details into the customers table
                            String insertCustomerQuery = "INSERT INTO customers (customer_name, email, phone_number) VALUES (?, ?, ?)";
                            try (PreparedStatement insertCustomerStatement = conn.prepareStatement(insertCustomerQuery){
                                insertCustomerStatement.setString(1, customer.getCustomerName());
                                insertCustomerStatement.setString(2, customer.getEmail());
                                insertCustomerStatement.setString(3, customer.getPhoneNumber());
                                insertCustomerStatement.executeUpdate();

                                // Retrieve the auto-generated customer_id
                                ResultSet generatedKeys = insertCustomerStatement.getGeneratedKeys();
                                if (generatedKeys.next()) {
                                    int customerId = generatedKeys.getInt(1);

                                    // Insert booking details into the bookings table
                                    String insertBookingQuery = "INSERT INTO bookings (customer_id, event_id, num_tickets, total_cost, booking_date) VALUES (?, ?, ?, ?, CURDATE())";
                                    try (PreparedStatement insertBookingStatement = conn.prepareStatement(insertBookingQuery)) {
                                        insertBookingStatement.setInt(1, customerId);
                                        insertBookingStatement.setInt(2, eventID);
                                        insertBookingStatement.setInt(3, numTickets);
                                        double totalCost = ticketPrice * numTickets;
                                        insertBookingStatement.setDouble(4, totalCost);
                                        insertBookingStatement.executeUpdate();
                                    }
                                }
                            }
                        }

                        // Update available seats in the events table
                        String updateSeatsQuery = "UPDATE events SET available_seats = available_seats - ? WHERE event_id = ?";
                        try (PreparedStatement updateSeatsStatement = conn.prepareStatement(updateSeatsQuery)) {
                            updateSeatsStatement.setInt(1, numTickets);
                            updateSeatsStatement.setInt(2, eventID;
                            updateSeatsStatement.executeUpdate();
                        }

                        System.out.println("Tickets booked successfully.");
                    } else {
                        System.out.println("Not enough available seats for booking.");
                    }
                } else {
                    System.out.println("Event not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelBooking(int bookingId) {
            String query = "SELECT event_id, num_tickets FROM bookings WHERE booking_id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(1, bookingId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int eventId = resultSet.getInt("event_id");
                    int numTickets = resultSet.getInt("num_tickets");

                    // Update available seats in the events table
                    String updateSeatsQuery = "UPDATE events SET available_seats = available_seats + ? WHERE event_id = ?";
                    try (PreparedStatement updateSeatsStatement = conn.prepareStatement(updateSeatsQuery)) {
                        updateSeatsStatement.setInt(1, numTickets);
                        updateSeatsStatement.setInt(2, eventId);
                        updateSeatsStatement.executeUpdate();
                    }

                    // Remove booking information from the bookings
                    String deleteBookingQuery = "DELETE FROM bookings WHERE booking_id = ?";
                    try (PreparedStatement deleteBookingStatement = conn.prepareStatement(deleteBookingQuery)) {
                        deleteBookingStatement.setInt(1, bookingId);
                        deleteBookingStatement.executeUpdate();
                    }

                    System.out.println("Booking canceled successfully.");
                } else {
                    System.out.println("Booking not found.");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Booking getBookingDetails(int bookingId) {
        Booking booking = null;
        String query = "SELECT * FROM bookings WHERE booking_id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(1, bookingId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Retrieve booking details
                    int eventId = resultSet.getInt("event_id");
                    int customerId = resultSet.getInt("customer_id");
                    int numTickets = resultSet.getInt("num_tickets");
                    double totalCost = resultSet.getDouble("total_cost");
                    LocalDate bookingDate = resultSet.getDate("booking_date").toLocalDate();

                    //Retrieve Customer details
                    List<Customer> customers = new ArrayList<>();
                    String customerQuery = "SELECT * FROM customers WHERE customer_id = ?";
                    PreparedStatement customerStatement = conn.prepareStatement(customerQuery);
                    customerStatement.setInt(1, customerId);
                    ResultSet customerResultSet = customerStatement.executeQuery(query);
                        while (customerResultSet.next()) {
                            String customerName = customerResultSet.getString("customer_name");
                            String email = customerResultSet.getString("email");
                            String phoneNumber = customerResultSet.getString("phone_number");

                            // Create Customer object and add it to the list
                            Customer customer = new Customer(customerName, email, phoneNumber);
                            customers.add(customer);
                        }

                    //Get Event
                    PreparedStatement eventStatement = conn.prepareStatement("SELECT * FROM events WHERE event_id = ?");
                        eventStatement.setInt(1, eventId);
                        ResultSet rs = eventStatement.executeQuery();

                        String eventName = rs.getString("event_name");
                        LocalDate eventDate = rs.getDate("event_date").toLocalDate();
                        LocalTime eventTime = rs.getTime("event_time").toLocalTime();
                        int totalSeats = rs.getInt("total_seats");
                        double ticketPrice = resultSet.getDouble("ticket_price");

                    // Create Event object
                    Event event = new Event(eventName, eventDate, eventTime, totalSeats, ticketPrice);

                    // Create a Booking object with the retrieved details
                    booking = new Booking(bookingId, event, numTickets, totalCost, bookingDate, customers);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;
    }
}
