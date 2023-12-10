//package main.java.com.hexaware;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Scanner;
//
//public class TicketBookingSystem extends BookingSystem{
//    public static Event createEvent(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venueName, int totalSeats, double ticketPrice, EventType eventType) {
//        return new Event(eventName, eventDate, eventTime, venueName, totalSeats, ticketPrice, eventType);
//    }
//
//    public static void displayEventDetails(Event event) {
//        event.displayEventDetails();
//    }
//
//    public static void bookTickets(Event event, int numTickets) {
//        double cost = event.getTicketPrice() * numTickets;
//        if (event.bookTickets(numTickets)) {
//            System.out.println(numTickets + " tickets booked successfully. Total cost: $" + cost);
//        }else{
//            System.out.println("Tickets Not Available");
//        }
//    }
//
//    public static boolean cancelTickets(Event event, int numTickets) {
//        return event.cancelBooking(numTickets);
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        Event movieEvent = createEvent("Movie Night", LocalDate.of(2023, 12, 15), LocalTime.of(18, 30), "Cinema Hall", 100, 12.5, EventType.MOVIE);
//        Event sportsEvent = createEvent("Football Match", LocalDate.of(2023, 12, 20), LocalTime.of(15, 0), "Stadium", 200, 20.0, EventType.SPORTS);
//        Event concertEvent = createEvent("Rock Concert", LocalDate.of(2023, 12, 25), LocalTime.of(20, 0), "Arena", 150, 30.0, EventType.CONCERT);
//
//
//        while (true) {
//            System.out.println("\n--- Ticket Booking System ---");
//            System.out.println("1. Display Event Details");
//            System.out.println("2. Book Tickets");
//            System.out.println("3. Cancel Tickets");
//            System.out.println("4. Exit");
//            System.out.print("Enter your choice: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    System.out.println("\n1. Movie Event");
//                    System.out.println("2. Sports Event");
//                    System.out.println("3. Concert Event");
//                    System.out.print("Enter event type choice: ");
//                    int eventTypeChoice = scanner.nextInt();
//                    scanner.nextLine();
//
//                    if (eventTypeChoice == 1) {
//                        displayEventDetails(movieEvent);
//                    } else if (eventTypeChoice == 2) {
//                        displayEventDetails(sportsEvent);
//                    } else if (eventTypeChoice == 3) {
//                        displayEventDetails(concertEvent);
//                    } else {
//                        System.out.println("Invalid choice.");
//                    }
//                    break;
//
//                case 2:
//                    System.out.println("\n1. Movie Event");
//                    System.out.println("2. Sports Event");
//                    System.out.println("3. Concert Event");
//                    System.out.print("Enter event type choice: ");
//                    int bookEventTypeChoice = scanner.nextInt();
//                    scanner.nextLine();
//
//                    System.out.print("Enter the number of tickets to book: ");
//                    int numTicketsToBook = scanner.nextInt();
//                    scanner.nextLine();
//
//                    if (bookEventTypeChoice == 1) {
//                        bookTickets(movieEvent, numTicketsToBook);
//                    } else if (bookEventTypeChoice == 2) {
//                        bookTickets(sportsEvent, numTicketsToBook);
//                    } else if (bookEventTypeChoice == 3) {
//                        bookTickets(concertEvent, numTicketsToBook);
//                    } else {
//                        System.out.println("Invalid choice.");
//                    }
//                    break;
//
//                case 3:
//                    System.out.println("\n1. Movie Event");
//                    System.out.println("2. Sports Event");
//                    System.out.println("3. Concert Event");
//                    System.out.print("Enter event type choice: ");
//                    int cancelEventTypeChoice = scanner.nextInt();
//                    scanner.nextLine();
//
//                    System.out.print("Enter the number of tickets to cancel: ");
//                    int numTicketsToCancel = scanner.nextInt();
//                    scanner.nextLine();
//
//                    if (cancelEventTypeChoice == 1) {
//                        cancelTickets(movieEvent, numTicketsToCancel);
//                    } else if (cancelEventTypeChoice == 2) {
//                        cancelTickets(sportsEvent, numTicketsToCancel);
//                    } else if (cancelEventTypeChoice == 3) {
//                        cancelTickets(concertEvent, numTicketsToCancel);
//                    } else {
//                        System.out.println("Invalid choice.");
//                    }
//                    break;
//
//                case 4:
//                    System.out.println("Exiting the Ticket Booking System.");
//                    System.exit(0);
//
//                default:
//                    System.out.println("Invalid choice. Please enter a valid option.");
//                    break;
//            }
//        }
//    }
//}