package com.example.busticketing.service;

import com.example.busticketing.jdbcdriver.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketDB {

    public void saveTicket() throws SQLException {
        String sql = "INSERT INTO ticket (passenger_name, seat_no, bus_id, ticket_payment, booking_date, price) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, "John Doe");
            statement.setString(2, "A1");
            statement.setInt(3, 1);
            statement.setString(4, "paid");
            statement.setDate(5, java.sql.Date.valueOf("2022-01-01"));
            statement.setDouble(6, 100.0);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
