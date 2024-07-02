package com.tharanga.event_app.services.impl;

import com.tharanga.event_app.domain.entities.Event;
import com.tharanga.event_app.repositories.EventRepository;
import com.tharanga.event_app.services.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
}
