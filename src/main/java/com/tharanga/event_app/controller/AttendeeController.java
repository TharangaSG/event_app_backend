package com.tharanga.event_app.controller;

import com.tharanga.event_app.domain.entities.Attendee;
import com.tharanga.event_app.services.AttendeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {

    final AttendeeService attendeeService;
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping(path="/all_attendee")
    public List<Attendee> getAllAttendees() {
        return attendeeService.getAllAttendees();
    }

    @PostMapping(path="/all_attendee")
    public Attendee registerAttendee(@RequestBody Attendee attendee) {
        return attendeeService.registerAttendee(attendee);
    }

    // Other endpoints as needed
}