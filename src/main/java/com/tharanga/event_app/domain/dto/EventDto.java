package com.tharanga.event_app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private String organizer;
    private String category;
    private String eventImage;
    private String imageUrl;
}
