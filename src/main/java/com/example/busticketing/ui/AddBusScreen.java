package com.example.busticketing.ui;

import com.example.busticketing.model.Bus;
import com.example.busticketing.service.BusDB;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddBusScreen extends JFrame {

    public AddBusScreen(BusDB busDB) {
        super("Add Bus");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        // Text fields for user input
        JTextField busNameField = new JTextField(20);
        JTextField busTypeField = new JTextField(20);
        JTextField sourceField = new JTextField(20);
        JTextField destinationField = new JTextField(20);
        JTextField departureTimeField = new JTextField(20); // Consider using a date picker component for better user experience
        JTextField capacityField = new JTextField(10); // Limit input to numbers

        // Labels
        JLabel busNameLabel = new JLabel("Bus Name:");
        JLabel busTypeLabel = new JLabel("Bus Type:");
        JLabel sourceLabel = new JLabel("Source:");
        JLabel destinationLabel = new JLabel("Destination:");
        JLabel departureTimeLabel = new JLabel("Departure Time (YYYY-MM-DD HH:mm):");
        JLabel capacityLabel = new JLabel("Capacity:");

        // Add labels and text fields to the panels
        mainPanel.add(busNameLabel);
        mainPanel.add(busNameField);
        mainPanel.add(busTypeLabel);
        mainPanel.add(busTypeField);
        mainPanel.add(sourceLabel);
        mainPanel.add(sourceField);
        mainPanel.add(destinationLabel);
        mainPanel.add(destinationField);
        mainPanel.add(departureTimeLabel);
        mainPanel.add(departureTimeField);
        mainPanel.add(capacityLabel);
        mainPanel.add(capacityField);

        // Grid layout for 7 rows, 2 columns with spacing

        JButton addBusButton = new JButton("Add Bus");
        addBusButton.addActionListener(e -> {
            Bus bus = new Bus();
            bus.setBusName(busNameField.getText());
            bus.setBusType(busTypeField.getText());
            bus.setSource(sourceField.getText());
            bus.setDestination(destinationField.getText());
            String departureTimeStr = departureTimeField.getText();
            bus.setCapacity(Integer.parseInt(capacityField.getText()));

            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date departureTime = dateFormat.parse(departureTimeStr);
                bus.setDepartureTime(departureTime);
                busDB.addBus(bus);
                JOptionPane.showMessageDialog(AddBusScreen.this, "Bus added successfully");
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Error: Invalid departure time format. Please use YYYY-MM-DD HH:mm.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        mainPanel.add(addBusButton, BorderLayout.SOUTH);
        // Add panels and button to the frame
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(addBusButton, BorderLayout.SOUTH);
    }
}
