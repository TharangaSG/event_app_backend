package com.tharanga.event_app.domain.dto;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
