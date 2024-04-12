package com.example.busticketing;

import com.example.busticketing.service.BusDB;
import com.example.busticketing.ui.BusScreen;

public class Main {

    public static void main(String[] args) {
        BusDB busDB = new BusDB();
        new BusScreen(busDB);
    }
}
