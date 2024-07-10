package com.tharanga.event_app.mappers.impl;

import com.tharanga.event_app.domain.dto.EventDto;
import com.tharanga.event_app.domain.entities.Event;
import com.tharanga.event_app.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapperImpl implements Mapper<Event, EventDto> {

    private final ModelMapper modelMapper;

    public EventMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EventDto mapTo(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

    @Override
    public Event mapFrom(EventDto eventDto) {
        return modelMapper.map(eventDto, Event.class);
    }
}
