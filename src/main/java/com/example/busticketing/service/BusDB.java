package com.example.busticketing.service;

import com.example.busticketing.jdbcdriver.DatabaseConnectionPool;
import com.example.busticketing.model.Bus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDB {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    private final Bus bus = new Bus();

    public BusDB() {
        // TODO document why this constructor is empty
    }

    public void addBus(Bus bus) throws SQLException {
        String sql = "INSERT INTO bus (bus_name, source, destination, bus_type, departure_time, capacity) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, bus.getBusName());
            statement.setString(2, bus.getSource());
            statement.setString(3, bus.getDestination());
            statement.setString(4, bus.getBusType());

            // Convert departure time to a SQL compatible format (Timestamp)
            statement.setTimestamp(5, new Timestamp(bus.getDepartureTime().getTime()));

            statement.setInt(6, bus.getCapacity());
            statement.executeUpdate();
        }
    }

    public List<Bus> getAllBus() throws SQLException {
        String sql = "SELECT * FROM bus";
        List<Bus> busList = new ArrayList<>();
        try (Connection con = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bus bus1 = new Bus();
                bus1.setBusId(resultSet.getLong("bus_id"));
                bus1.setBusName(resultSet.getString("bus_name"));
                bus1.setSource(resultSet.getString("source"));
                bus1.setDestination(resultSet.getString("destination"));
                bus1.setBusType(resultSet.getString("bus_type"));
                bus1.setDepartureTime(resultSet.getDate("departure_time")); // Convert to LocalDate
                bus1.setCapacity(resultSet.getInt("capacity"));
                busList.add(bus1);
            }
            return busList;
        }
    }

    public List<Bus> getBusById(Long busId) throws SQLException {
        String sql = "SELECT * FROM bus WHERE bus_id = ?";
        List<Bus> busList = new ArrayList<>();
        try (Connection con = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setLong(1, busId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                bus.setBusId(resultSet.getLong("bus_id"));
                bus.setBusName(resultSet.getString("bus_name"));
                bus.setSource(resultSet.getString("source"));
                bus.setDestination(resultSet.getString("destination"));
                bus.setBusType(resultSet.getString("bus_type"));
                bus.setDepartureTime(resultSet.getDate("departure_time")); // Convert to LocalDate
                bus.setCapacity(resultSet.getInt("capacity"));
                busList.add(bus);
            } else {
                return null; // Bus not found with the specified ID
            }
        }
        return busList;
    }

    public void updateBus(Bus bus) throws SQLException {
        String sql = "UPDATE bus SET bus_name = ?, source = ?, destination = ?, bus_type = ?, departure_time = ?, capacity = ? WHERE bus_id = ?";
        try (Connection con = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, bus.getBusName());
            statement.setString(2, bus.getSource());
            statement.setString(3, bus.getDestination());
            statement.setString(4, bus.getBusType());
            statement.setTimestamp(5, new Timestamp(bus.getDepartureTime().getTime()));
            statement.setInt(6, bus.getCapacity());
            statement.setLong(7, bus.getBusId());
            statement.executeUpdate();
        }
    }

    public void deleteBus(Long busId) throws SQLException {
        String sql = "DELETE FROM bus WHERE bus_id = ?";
        try (Connection con = DatabaseConnectionPool.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setLong(1, busId);
            statement.executeUpdate();
        }
    }
}
