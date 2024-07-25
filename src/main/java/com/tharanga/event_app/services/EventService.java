package com.tharanga.event_app.services;

import com.tharanga.event_app.domain.dto.EventDto;
import com.tharanga.event_app.domain.dto.EventPageResponse;
import com.tharanga.event_app.domain.entities.Event;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventService {
    List<EventDto> getAllEvents();

    EventDto getEvent(Long eventId);

    EventDto createEvent(EventDto eventDto, MultipartFile file) throws IOException;

    EventDto updateEvent(Long eventId, EventDto eventDto, MultipartFile file) throws IOException;

    String deleteEvent(Long eventId) throws IOException;

    EventPageResponse getAllEventsWithPagination(Integer pageNumber, Integer PageSize);

    EventPageResponse getAllEventsWithPaginationAndSorting(Integer pageNumber, Integer pageSize,
                                                           String sortBy, String dir);
}
