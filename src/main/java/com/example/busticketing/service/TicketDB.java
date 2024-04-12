package com.example.busticketing.service;

import com.example.busticketing.jdbcdriver.DatabaseConnectionPool;
import com.example.busticketing.model.Ticket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketDB {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void saveTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO ticket (passenger_name, seat_no, bus_id, ticket_payment_status, booking_date, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, ticket.getPassengerName());
            statement.setString(2, ticket.getSeatNo());
            statement.setLong(3, ticket.getBus().getBusId());
            statement.setString(4, ticket.getTicketPaymentStatus());
            statement.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            statement.setDouble(6, ticket.getPrice());
            statement.executeUpdate();
        }
    }

    public List<Ticket> getTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";
        BusDB busDB = new BusDB();
        try (Connection con = DatabaseConnectionPool.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(resultSet.getLong("ticket_id"));
                ticket.setPassengerName(resultSet.getString("passenger_name"));
                ticket.setSeatNo(resultSet.getString("seat_no"));

                ticket.setBus(busDB.getBusById(resultSet.getLong("bus_id")).get(0));
                ticket.setTicketPaymentStatus(resultSet.getString("ticket_payment_status"));
                ticket.setBookingDate(resultSet.getDate("booking_date"));
                ticket.setPrice(resultSet.getDouble("price"));
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    public void updateTicket(Ticket ticket) throws SQLException {
        String sql = "UPDATE ticket SET passenger_name = ?, seat_no = ?, bus_id = ?, ticket_payment_status = ?, booking_date = ?, price = ? WHERE ticket_id = ?";
        try (Connection con = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, ticket.getPassengerName());
            statement.setString(2, ticket.getSeatNo());
            statement.setLong(3, ticket.getBus().getBusId());
            statement.setString(4, ticket.getTicketPaymentStatus());
            statement.setDate(5, java.sql.Date.valueOf(String.valueOf(ticket.getBookingDate())));
            statement.setDouble(6, ticket.getPrice());
            statement.setLong(7, ticket.getTicketId());
            statement.executeUpdate();
        }
    }

    public void deleteTicket(Long ticketId) throws SQLException {
        String sql = "DELETE FROM ticket WHERE ticket_id = ?";
        try (Connection con = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setLong(1, ticketId);
            statement.executeUpdate();
        }
    }
}
