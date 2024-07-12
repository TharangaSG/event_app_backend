package com.tharanga.event_app.services.impl;

import com.tharanga.event_app.domain.dto.EventDto;
import com.tharanga.event_app.domain.entities.Event;
import com.tharanga.event_app.exceptions.EventNotFoundException;
import com.tharanga.event_app.exceptions.FileExistsException;
import com.tharanga.event_app.mappers.Mapper;
import com.tharanga.event_app.repositories.EventRepository;
import com.tharanga.event_app.services.EventService;
import com.tharanga.event_app.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        for (Event event : events) {
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
    public EventDto getEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id = " + eventId));
        String eventUrl = baseUrl + "/file/" + event.getEventImage();

        EventDto eventDto = eventMapper.mapTo(event);
        eventDto.setImageUrl(eventUrl);
        return eventDto;
    }

    @Override
    public EventDto createEvent(EventDto eventDto, MultipartFile file) throws IOException {

            // Upload the file
            if (Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))) {
                throw new FileExistsException("File already exists, Please enter another file");
            }
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

    }

    @Override
    public EventDto updateEvent(Long eventId, EventDto eventDto, MultipartFile file) throws IOException {

            // check event object exist with given id
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EventNotFoundException("Event not found with id = " + eventId));

            // if file is not null delete the existing file and upload new
            String fileName = event.getEventImage();
            if (file != null) {
                Files.deleteIfExists(Paths.get(path + File.separator + fileName));
                fileName = fileService.uploadFile(path, file);
            }

            eventDto.setEventImage(fileName);

            Event eventUpdated = eventMapper.mapFrom(eventDto);

            Event eventSaved = eventRepository.save(eventUpdated);

            String imageUrl = baseUrl + "/file/" + fileName;

            EventDto eventDtoUpdated = eventMapper.mapTo(eventSaved);
            eventDtoUpdated.setImageUrl(imageUrl);

            return eventDtoUpdated;

    }

    @Override
    public String deleteEvent(Long eventId) throws IOException {

            // check event object exist with given id
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EventNotFoundException("Event not found with id = " + eventId));
            Long id = event.getId();
            Files.deleteIfExists(Paths.get(path + File.separator + event.getEventImage()));

            eventRepository.delete(event);

            return ("Event deleted with id = " + id);
    }
}

