DROP DATABASE IF EXISTS tbs;

create database tbs;
USE tbs;

-- TASK 2

CREATE TABLE addresses (
    address_id INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    zipcode VARCHAR(16) NOT NULL
);

-- Create Venu Table
CREATE TABLE venues (
    venue_id INT PRIMARY KEY AUTO_INCREMENT,
    venue_name VARCHAR(255),
    address_id INT NOT NULL,
    CONSTRAINT fk_address_id FOREIGN KEY(address_id) REFERENCES addresses(address_id)
);

CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(20)
);

CREATE TABLE events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    event_name VARCHAR(255),
    event_date DATE,
    event_time TIME,
    venue_id INT,
    total_seats INT,
    available_seats INT,
    ticket_price DECIMAL(10, 2),
    event_type ENUM('Movie', 'Sports', 'Concert'),
    FOREIGN KEY (venue_id) REFERENCES venues(venue_id)
);


CREATE TABLE bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    event_id INT,
    num_tickets INT,
    total_cost DECIMAL(10, 2),
    booking_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (event_id) REFERENCES events(event_id)
);

ALTER TABLE customers
ADD booking_id INT ;

ALTER TABLE customers
ADD FOREIGN KEY (booking_id) REFERENCES bookings(booking_id);

ALTER TABLE events
ADD booking_id INT;

ALTER TABLE events
ADD FOREIGN KEY (booking_id) REFERENCES bookings(booking_id);

-- TASK 3 
-- 1. INSERTION

INSERT INTO addresses (street, city, state, zipcode)
VALUES
    ('123 Main Street', 'Mumbai', 'Maharashtra', '400001'),
    ('456 Oak Avenue', 'Delhi', 'Delhi', '110001'),
    ('789 Pine Road', 'Bangalore', 'Karnataka', '560001'),
    ('101 Maple Lane', 'Chennai', 'Tamil Nadu', '600001'),
    ('202 Cedar Street', 'Kolkata', 'West Bengal', '700001'),
    ('303 Elm Avenue', 'Hyderabad', 'Telangana', '500001'),
    ('404 Birch Road', 'Pune', 'Maharashtra', '411001'),
    ('505 Spruce Lane', 'Ahmedabad', 'Gujarat', '380001'),
    ('606 Pine Road', 'Jaipur', 'Rajasthan', '302001'),
    ('707 Oak Avenue', 'Lucknow', 'Uttar Pradesh', '226001');

INSERT INTO venues (venue_name, address_id)
VALUES
    ('City Hall', 1),
    ('Sports Arena', 2),
    ('Concert Hall', 3),
    ('Community Center', 4),
    ('Stadium', 5),
    ('Convention Center', 6),
    ('Exhibition Hall', 7),
    ('Cultural Center', 8),
    ('Amphitheater', 9),
    ('Town Hall', 10);

INSERT INTO customers (customer_name, email, phone_number)
VALUES
    ('John Doe', 'john.doe@example.com', '9876543210'),
    ('Jane Smith', 'jane.smith@example.com', '8765432109'),
    ('Alice Johnson', 'alice.johnson@example.com', '7654321098'),
    ('Bob Williams', 'bob.williams@example.com', '6543210987'),
    ('Charlie Brown', 'charlie.brown@example.com', '5432109000'),
    ('David Davis', 'david.davis@example.com', '4321098765'),
    ('Eva White', 'eva.white@example.com', '3210987654'),
    ('Frank Miller', 'frank.miller@example.com', '2109876543'),
    ('Grace Taylor', 'grace.taylor@example.com', '1098765432'),
    ('Henry Wilson', 'henry.wilson@example.com', '0987654321');

INSERT INTO events (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type)
VALUES
    ('Movie Night', '2023-12-01', '18:00:00', 1, 100, 50, 10.00, 'Movie'),
    ('Cricket Cup', '2023-12-02', '14:30:00', 2, 200, 150, 20.00, 'Sports'),
    ('Concert in the Park', '2023-12-03', '20:00:00', 3, 500, 400, 30.00, 'Concert'),
    ('Tech Conference', '2023-12-04', '09:00:00', 4, 300, 250, 25.00, 'Movie'),
    ('Football Game', '2023-12-05', '16:00:00', 5, 150, 100, 15.00, 'Sports'),
    ('Art Exhibition', '2023-12-06', '12:00:00', 6, 50, 30, 5.00, 'Concert'),
    ('Comedy Show', '2023-12-07', '19:30:00', 7, 200, 180, 1800, 'Sports'),
    ('Music Festival', '2022-12-08', '17:00:00', 8, 400, 350, 40.00, 'Concert'),
    ('Dance Performance', '2023-12-09', '21:00:00', 9, 100, 80, 12.00, 'Movie'),
    ('Town Hall Meeting', '2023-12-10', '19:00:00', 10, 80, 60, 8.00, 'Sports');

INSERT INTO bookings (customer_id, event_id, num_tickets, total_cost, booking_date)
VALUES
    (1, 1, 26, 20.00, '2023-11-28'),
    (2, 2, 5, 100.00, '2023-11-29'),
    (3, 3, 1, 30.00, '2023-11-30'),
    (4, 4, 3, 75.00, '2023-12-01'),
    (5, 5, 2, 30.00, '2023-12-02'),
    (6, 6, 4, 20.00, '2023-12-03'),
    (7, 7, 3, 54.00, '2023-12-04'),
    (8, 8, 5, 200.00, '2023-12-05'),
    (9, 9, 2, 24.00, '2023-12-06'),
    (10, 10, 1, 8.00, '2023-12-07');

-- 2
SELECT * FROM events;
-- 3
SELECT * FROM events WHERE available_seats > 0;
-- 4
SELECT * FROM events WHERE event_name LIKE '%cup%';

-- 5
SELECT * FROM events WHERE ticket_price BETWEEN 1000 AND 2500;
-- 6
SELECT * FROM events WHERE event_date BETWEEN '2023-01-01' AND '2023-12-31';
-- 7
SELECT * FROM events WHERE available_seats > 0 && event_name LIKE '%Concert%';

-- 8
SELECT * FROM events LIMIT 5 OFFSET 5;

-- 9
SELECT * FROM bookings where num_tickets > 4;
-- 10
SELECT * FROM customers where phone_number LIKE '%000';
-- 11
SELECT * FROM events where total_seats > 15000;
-- 12
SELECT * FROM events WHERE event_name NOT LIKE 'x%' AND event_name NOT LIKE 'y%' AND event_name NOT LIKE 'z%';

-- TASK 4
-- 1
SELECT e.event_type, AVG(e.ticket_price) FROM events e
GROUP BY e.event_type;
-- 2
SELECT SUM((total_seats-available_seats) * ticket_price) FROM events;

-- 3
SELECT * FROM events ORDER BY (total_seats - available_seats) DESC LIMIT 1;

-- 4
SELECT SUM(total_seats - available_seats) FROM events GROUP BY event_type;

-- 5
SELECT * FROM events WHERE total_seats = available_seats;

-- 6
SELECT * FROM customers 
WHERE customer_id = 
(SELECT customer_id  FROM bookings GROUP BY customer_id ORDER BY SUM(num_tickets) DESC LIMIT 1);

-- 7
SELECT e.event_name, MONTH(b.booking_date) as month,
SUM(b.num_tickets) AS total_tickets_sold
FROM bookings b
JOIN events e ON b.event_id = e.event_id
GROUP BY month, e.event_name;

-- 8
SELECT v.venue_name, AVG(e.ticket_price) FROM venues v
JOIN events e ON e.venue_id = v.venue_id
GROUP BY v.venue_name;

-- 9
SELECT e.event_type,SUM(num_tickets) from events e
join bookings b where e.event_id = b.event_id
group by e.event_type;

-- 10
SELECT SUM((total_seats-available_seats) * ticket_price) FROM events
GROUP BY YEAR(event_date);

-- 11
SELECT c.customer_id, c.customer_name, COUNT(DISTINCT b.event_id) AS num_events_booked
FROM customers c
JOIN bookings b ON c.customer_id = b.customer_id
GROUP BY c.customer_id, c.customer_name
HAVING COUNT(DISTINCT b.event_id) > 1;

-- 12
SELECT c.customer_name, SUM(b.total_cost) as total_revenue FROM customers c
JOIN bookings b ON c.customer_id = b.customer_id
JOIN events e ON e.event_id = b.event_id
GROUP BY e.event_id, c.customer_id, c.customer_name;

-- 13
SELECT e.event_type, AVG(e.ticket_price) FROM events e
JOIN venues v ON e.venue_id = v.venue_id
GROUP BY e.venue_id, e.event_type; 

-- 14
SELECT c.customer_id, c.customer_name, SUM(b.num_tickets) AS total_tickets_purchased
FROM customers c
JOIN bookings b ON c.customer_id = b.customer_id
WHERE b.booking_date >= CURRENT_DATE - INTERVAL 30 DAY
GROUP BY c.customer_id, c.customer_name;

-- Task 5
-- 1
SELECT v.venue_name, AVG(ticket_price) FROM events e
JOIN venues v ON e.venue_id = v.venue_id
GROUP BY v.venue_id;

-- 2
SELECT * FROM events WHERE total_seats - available_seats > 0.5 * total_seats;

-- 3
SELECT events.event_name, SUM(num_tickets) FROM bookings
JOIN events on events.event_id = bookings.event_id
GROUP BY bookings.event_id;

-- 4
SELECT * FROM customers c
WHERE NOT EXISTS (SELECT * FROM bookings b WHERE b.customer_id = c.customer_id);

-- 5
SELECT event_id, event_name FROM events
WHERE event_id
NOT IN (SELECT event_id FROM bookings);

-- 6
SELECT event_type, SUM(num_tickets) AS total_tickets_sold
FROM (
    SELECT e.event_type, b.num_tickets
    FROM events e
    JOIN bookings b ON e.event_id = b.event_id
) AS subquery
GROUP BY event_type;

-- 7
SELECT event_name FROM events WHERE 
ticket_price > (SELECT AVG(e.ticket_price) FROM events e);

-- 8
SELECT c.customer_name, (SELECT SUM(e.ticket_price * b.num_tickets) FROM events e
JOIN bookings b ON e.event_id = b.event_id 
WHERE b.customer_id = c.customer_id) as total_revenue
FROM customers c;

-- 9
SELECT c.customer_name FROM customers c
JOIN bookings b ON c.customer_id = b.customer_id 
JOIN events e ON b.event_id  = e.event_id
WHERE b.event_id IN (SELECT event_id FROM events WHERE e.venue_id = '1');

-- 10
SELECT events.event_type, SUM(bookings.num_tickets) FROM events
JOIN bookings ON events.event_id = bookings.event_id 
GROUP BY event_type;

-- 11
SELECT c.customer_name from customers c
WHERE c.customer_id IN (SELECT customer_id FROM bookings WHERE MONTH(booking_date) = '12');

-- 12
SELECT v.venue_name, AVG(e.ticket_price) FROM venues v
LEFT JOIN events e ON e.venue_id = v.venue_id
GROUP BY v.venue_id;