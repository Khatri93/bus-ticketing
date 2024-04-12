package com.example.busticketing.ui;

import javax.swing.*;
import java.awt.*;


public class TicketScreen {
    public void viewTicketScreen() {
        JFrame frame = new JFrame("Booking a Ticket");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Top section for selected bus info
        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        frame.add(topPanel, BorderLayout.NORTH);

        JLabel busInfoLabel = new JLabel("Selected Bus Info: Origin - Destination, Departure - Arrival, Price");
        topPanel.add(busInfoLabel);

        // Passenger Information section
        JPanel passengerInfoPanel = new JPanel(new GridLayout(3, 2));
        frame.add(passengerInfoPanel, BorderLayout.CENTER);

        passengerInfoPanel.add(new JLabel("Passenger Name:"));
        passengerInfoPanel.add(new JTextField(20));

        passengerInfoPanel.add(new JLabel("Email Address:"));
        passengerInfoPanel.add(new JTextField(20));

        passengerInfoPanel.add(new JLabel("Phone Number:"));
        passengerInfoPanel.add(new JTextField(20));

        // Confirmation section
        JPanel confirmationPanel = new JPanel(new FlowLayout());
        JButton bookTicketButton = new JButton("Book Ticket");
        JButton backButton = new JButton("Back");
        confirmationPanel.add(bookTicketButton);
        confirmationPanel.add(backButton);
        frame.add(confirmationPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

