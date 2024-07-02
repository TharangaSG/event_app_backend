package com.tharanga.event_app.services;

import com.tharanga.event_app.domain.entities.Attendee;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees();
    Attendee registerAttendee(Attendee attendee);
    // Other method signatures as needed
}
