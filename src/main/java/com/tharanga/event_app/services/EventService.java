package com.tharanga.event_app.services;

import com.tharanga.event_app.domain.entities.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event createEvent(Event event);
}
