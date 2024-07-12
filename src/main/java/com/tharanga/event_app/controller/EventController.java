package com.tharanga.event_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tharanga.event_app.domain.dto.EventDto;
import com.tharanga.event_app.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;



@RestController
@RequestMapping(path="/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    private EventDto convertToEventDto(String eventDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // Register the JavaTimeModule because using date and time
        return objectMapper.readValue(eventDtoObj, EventDto.class);
    }
    @GetMapping(path="/all_events")
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(path="/{eventId}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Long eventId){
        return ResponseEntity.ok(eventService.getEvent(eventId));
    }

    @PostMapping(path="/all_events")
    public ResponseEntity<EventDto> createEvent(@RequestPart MultipartFile file,
                                                @RequestPart String eventDto) throws IOException {
        EventDto dto = convertToEventDto(eventDto);
        return new ResponseEntity<>(eventService.createEvent(dto, file), HttpStatus.CREATED);
    }

    @PutMapping(path="/update/{eventId}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long eventId,
                                                @RequestPart MultipartFile file,
                                                @RequestPart  String eventDtoObj) throws IOException {
        if (file.isEmpty()){
            file = null;
        }
        EventDto eventDto = convertToEventDto(eventDtoObj);
        return ResponseEntity.ok(eventService.updateEvent(eventId, eventDto, file));
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) throws IOException {
        return ResponseEntity.ok(eventService.deleteEvent(eventId));
    }
}
