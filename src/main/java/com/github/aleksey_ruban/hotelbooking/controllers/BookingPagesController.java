package com.github.aleksey_ruban.hotelbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingPagesController {
    @GetMapping({"/", "home", "/home/"})
    public String index() {
        return "booking/index";
    }

    @GetMapping({"/rooms", "/rooms/"})
    public String rooms() {
        return "booking/rooms";
    }

    @GetMapping({"/room-details", "/room-details/"})
    public String roomDetails() {
        return "booking/room-details";
    }

    @GetMapping({"/booking", "/booking/"})
    public String booking() {
        return "booking/booking";
    }
}
