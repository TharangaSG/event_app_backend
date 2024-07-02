package com.tharanga.event_app.services.impl;

import com.tharanga.event_app.domain.entities.Attendee;
import com.tharanga.event_app.repositories.AttendeeRepository;
import com.tharanga.event_app.services.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {

    final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    @Override
    public Attendee registerAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    // Other methods as needed
}
