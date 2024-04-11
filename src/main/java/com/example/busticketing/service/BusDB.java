package com.example.busticketing.service;

import com.example.busticketing.jdbcdriver.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BusDB {
    public void saveBus() throws SQLException {
        String sql = "INSERT INTO ticket ( busName, source, destination, busType, departureTime, capacity) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, "Safari Travels");
            statement.setString(2, "Pokhara");
            statement.setString(3, "Kathmandu");
            statement.setString(4, "AC Deluxe");
            statement.setString(5, "08:00");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
