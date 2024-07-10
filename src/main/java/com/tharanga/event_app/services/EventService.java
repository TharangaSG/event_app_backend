package com.tharanga.event_app.services;

import com.tharanga.event_app.domain.dto.EventDto;
import com.tharanga.event_app.domain.entities.Event;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    List<EventDto> getAllEvents();
    EventDto createEvent(EventDto eventDto, MultipartFile file);
}
