package com.tharanga.event_app.domain.dto;

import java.util.List;

public record EventPageResponse(List<EventDto> eventDtos,
                                Integer pageNumber,
                                Integer pageSize,
                                Long totalElements,
                                int totalPages,
                                boolean isLast) {

}
