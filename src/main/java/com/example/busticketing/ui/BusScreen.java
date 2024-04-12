package com.example.busticketing.ui;

import com.example.busticketing.model.Bus;
import com.example.busticketing.service.BusDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class BusScreen extends JFrame {
    String[] columnNames = {"Name", "Type", "Origin", "Destination", "Departure Time", "Capacity"};
    JTable table;

    public BusScreen(BusDB busDB) {
        JFrame frame = new JFrame("Browsing Buses");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Main section
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        Object[][] data = {}; // Populate with actual data
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton addBusButton = getjButton(busDB);
        mainPanel.add(addBusButton, BorderLayout.SOUTH);

        // Search bar and search button
        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search Buses");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Search button action listener
        searchButton.addActionListener(e -> {
            String searchTerm = searchField.getText().trim(); // Get search term and trim whitespace

            try {
                // Display all buses if search term is empty
                if (searchTerm.isEmpty()) {
                    displayAllBuses(busDB);
                } else {
                    List<Bus> searchedBuses;
                    searchedBuses = busDB.getBusById(Long.valueOf(searchTerm));
                    updateTableData(searchedBuses); // Update table with search results
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        frame.setVisible(true);
    }

    private static JButton getjButton(BusDB busDB) {
        JButton addBusButton = new JButton("Add Bus");
        addBusButton.addActionListener(e -> {
            // Create an instance of AddBusScreen and pass the BusDB instance to it
            AddBusScreen addBusScreen = new AddBusScreen(busDB);
            // Set the size of the window
            addBusScreen.setSize(500, 400);
            // Center the window on the screen
            addBusScreen.setLocationRelativeTo(null);
            // Make the window visible
            addBusScreen.setVisible(true);
            // Code to open AddBusScreen will go here
        });
        return addBusButton;
    }

    private void displayAllBuses(BusDB busDB) throws SQLException {
        List<Bus> buses = busDB.getAllBus(); // Get all buses from BusDB
        updateTableData(buses); // Update table with all buses
    }

    private void updateTableData(List<Bus> buses) {
        Object[][] data = convertBusesToTableData(buses); // Convert buses to table data format

        // Update table data
        table.setModel(new DefaultTableModel(data, columnNames));
    }

    private Object[][] convertBusesToTableData(List<Bus> buses) {
        int numBuses = buses.size();
        Object[][] data = new Object[numBuses][columnNames.length];
        for (int i = 0; i < numBuses; i++) {
            Bus bus = buses.get(i);
            data[i][0] = bus.getBusName();
            data[i][1] = bus.getBusType();
            data[i][2] = bus.getSource();
            data[i][3] = bus.getDestination();
            data[i][4] = bus.getDepartureTime().toString();
            data[i][5] = bus.getCapacity();
        }

        return data;
    }

}

