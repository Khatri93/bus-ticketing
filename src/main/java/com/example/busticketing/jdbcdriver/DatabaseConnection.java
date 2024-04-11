package com.example.busticketing.jdbcdriver;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/bus-ticketing";
    private static final String username = "postgres";
    private static final String password = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

}
