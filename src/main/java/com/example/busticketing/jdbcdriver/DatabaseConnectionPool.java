package com.example.busticketing.jdbcdriver;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionPool {

    private DatabaseConnectionPool() {
        // Private constructor to hide the implicit public one
    }
    private static final String URL = "jdbc:postgresql://localhost:5432/bus-ticketing";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static final BasicDataSource DATASOURCE;

    static {
        DATASOURCE = new BasicDataSource();
        DATASOURCE.setUrl(URL);
        DATASOURCE.setUsername(USERNAME);
        DATASOURCE.setPassword(PASSWORD);
        DATASOURCE.setInitialSize(10);
        DATASOURCE.setMaxTotal(20);
    }

    public static Connection getConnection() throws SQLException {
        return DATASOURCE.getConnection();
    }
}
