package com.hexaware.app;

import com.hexaware.dao.bean.*;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.entity.EventType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TicketBookingSystem{
    public static void main(String[] args){
        try {
            Scanner scanner = new Scanner(System.in);

            BookingSystemServiceProviderImpl bookingObject = new BookingSystemServiceProviderImpl();
            Set<Event> events = new TreeSet<>(Comparator.comparing(Event::getEventName).thenComparing((Event event) -> event.getVenue().getVenueName()));

            while (true) {
                System.out.println("\n--- Ticket Booking System ---");
                System.out.println("0. Create an Event");
                System.out.println("1. Display Event Details");
                System.out.println("2. Book Tickets");
                System.out.println("3. Cancel Tickets");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        String eventName;
                        LocalDate eventDate;
                        LocalTime eventTime;
                        String venueName;
                        String venueAddress;
                        int totalSeats;
                        double ticketPrice;
                        int eventTypeChoice;


                        System.out.println("\n---Enter Event Details---");
                        System.out.print("Event Name : ");
                        eventName = scanner.nextLine();

                        System.out.print("Event Date (YYYY-MM-DD): ");
                        eventDate = LocalDate.parse(scanner.nextLine());

                        System.out.print("Event Time (HH:mm): ");
                        eventTime = LocalTime.parse(scanner.nextLine());

                        System.out.print("Venue Name: ");
                        venueName = scanner.nextLine();

                        System.out.print("Venue Address : ");
                        venueAddress = scanner.nextLine();

                        System.out.println("Total Seats: ");
                        totalSeats = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Ticket Price: ");
                        ticketPrice = scanner.nextDouble();

                        System.out.println("Event Type: ");
                        System.out.println("1. Movie Event");
                        System.out.println("2. Sports Event");
                        System.out.println("3. Concert Event");
                        eventTypeChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (eventTypeChoice == 1) {
                            String genre;
                            String actorName;
                            String actressName;

                            System.out.print("Genre: ");
                            genre = scanner.nextLine();

                            System.out.print("Actor's Name: ");
                            actorName = scanner.nextLine();

                            System.out.print("Actress's Name: ");
                            actressName = scanner.nextLine();

                            Event event = bookingObject.createEvent(eventName,
                                    eventDate,
                                    eventTime,
                                    venueName,
                                    venueAddress,
                                    totalSeats,
                                    ticketPrice,
                                    EventType.MOVIE,
                                    genre,
                                    actorName,
                                    actressName);

                            events.add(event);
                        } else if (eventTypeChoice == 2) {
                            String sportName;
                            int numTeams;
                            ArrayList<String> teamsName = new ArrayList<String>();

                            System.out.print("Sport Name: ");
                            sportName = scanner.nextLine();

                            System.out.print("Number of Teams: ");
                            numTeams = scanner.nextInt();
                            scanner.nextLine();

                            for (int i = 1; i <= numTeams; i++) {
                                System.out.print("Enter Team " + i + " Name: ");
                                teamsName.add(scanner.nextLine());
                            }

                            Event event = bookingObject.createEvent(
                                    eventName,
                                    eventDate,
                                    eventTime,
                                    venueName,
                                    venueAddress,
                                    totalSeats,
                                    ticketPrice,
                                    EventType.SPORTS,
                                    sportName,
                                    teamsName
                            );
                            events.add(event);
                        } else if (eventTypeChoice == 3) {
                            System.out.print("Artist: ");
                            String artist = scanner.nextLine();

                            System.out.print("Type: ");
                            String type = scanner.nextLine();

                            Event event = bookingObject.createEvent(
                                    eventName,
                                    eventDate,
                                    eventTime,
                                    venueName,
                                    venueAddress,
                                    totalSeats,
                                    ticketPrice,
                                    EventType.CONCERT,
                                    artist,
                                    type
                            );
                            events.add(event);
                        } else {
                            System.out.println("Invalid choice.");
                        }
                        break;

                    case 1:
                        for (Event event : events) {
                            event.displayEventDetails();
                        }
                        break;

                    case 2:
                        for (Event event : events) {
                            event.displayEventDetails();

                            System.out.print("Do you want to book this? (1 - Yes, 2 - No) ");
                            int ch = scanner.nextInt();
                            scanner.nextLine();

                            if (ch == 1) {
                                System.out.print("Enter the number of tickets to book: ");
                                int numTicketsToBook = scanner.nextInt();
                                scanner.nextLine();
                                ArrayList<Customer> customers = new ArrayList<Customer>();
                                for (int i = 0; i < numTicketsToBook; i++) {
                                    String name, email, phoneNumber;
                                    System.out.println("Customer " + (i + 1) + " details : ");
                                    System.out.print("Customer Name : ");
                                    name = scanner.nextLine();
                                    System.out.print("Customer Email : ");
                                    email = scanner.nextLine();
                                    System.out.print("Customer Phone Number : ");
                                    phoneNumber = scanner.nextLine();
                                    Customer customer = new Customer(name, email, phoneNumber);
                                    customers.add(customer);
                                }
                                bookingObject.bookTickets(event, numTicketsToBook, customers);
                                break;
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter booking id to cancel: ");
                        int bookingId = scanner.nextInt();
                        scanner.nextLine();

                        if(bookingObject.cancelBooking(bookingId)) {
                            System.out.println("Booking Cancelled Successfully.");
                        }
                        break;

                    case 4:
                        System.out.println("Exiting the Ticket Booking System.");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        } catch(Exception e) {
            System.err.println(e);
        }
    }
}