package com.example.busticketing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Bus {

    private Long busId;

    private String busName;

    private String busType;

    private String source;

    private String destination;

    private Date departureTime;

    private int capacity;
}
