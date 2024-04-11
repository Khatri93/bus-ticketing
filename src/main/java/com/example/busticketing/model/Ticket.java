package com.example.busticketing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    private Long ticketId;
    private String passengerName;
    private String seatNo;
    private String ticketPaymentStatus;
    private Date bookingDate;  // Added field for booking date
    private double price;

    @ManyToOne
    private Bus bus;

    public static Ticket createSampleTicket(String passengerName, String seatNo, Bus bus, String ticketPaymentStatus) {
        Ticket ticket = new Ticket();
        ticket.setPassengerName(passengerName);
        ticket.setSeatNo(seatNo);
        ticket.setBus(bus);
        ticket.setTicketPaymentStatus(ticketPaymentStatus);
        return ticket;
    }
}
