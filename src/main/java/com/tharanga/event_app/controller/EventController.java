package com.tharanga.event_app.controller;

import com.tharanga.event_app.domain.entities.Event;
import com.tharanga.event_app.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/events")
public class EventController {

    final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path="/all_events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping(path="/all_events")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }


}