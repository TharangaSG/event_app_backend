package com.tharanga.event_app.services.impl;

import com.tharanga.event_app.domain.dto.EventDto;
import com.tharanga.event_app.domain.entities.Event;
import com.tharanga.event_app.mappers.Mapper;
import com.tharanga.event_app.repositories.EventRepository;
import com.tharanga.event_app.services.EventService;
import com.tharanga.event_app.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final Mapper<Event, EventDto> eventMapper;
    private final FileService fileService;

    @Value("${project.event}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    public EventServiceImpl(EventRepository eventRepository, Mapper<Event, EventDto> eventMapper, FileService fileService) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.fileService = fileService;
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();

        List<EventDto> eventDtos = new ArrayList<>();

        for(Event event:events){
            String imageUrl = baseUrl + "/file/" + event.getEventImage();
            EventDto eventDto = eventMapper.mapTo(event);
            eventDto.setImageUrl(imageUrl);
            eventDtos.add(eventDto);
        }
        return eventDtos;

//        return events.stream()   alternative way
//                .map(eventMapper::mapTo)
//                .collect(Collectors.toList());
    }

    @Override
    public EventDto createEvent(EventDto eventDto, MultipartFile file) {
        try {
            // Upload the file
            String uploadedFileName = fileService.uploadFile(path, file);

            // Set the value of the field 'eventImage' as the filename
            eventDto.setEventImage(uploadedFileName);

            // Generate the imageUrl
            String imageUrl = baseUrl + "/file/" + uploadedFileName;

            // Map DTO to entity, save the event, and map back to DTO
            Event event = eventMapper.mapFrom(eventDto);
            Event savedEvent = eventRepository.save(event);

            EventDto savedEventDto = eventMapper.mapTo(savedEvent);
            savedEventDto.setImageUrl(imageUrl); // setting imageUrl in the returned DTO

            return savedEventDto;
        } catch (IOException e) {
            // Handle the exception or rethrow it as a runtime exception
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}
