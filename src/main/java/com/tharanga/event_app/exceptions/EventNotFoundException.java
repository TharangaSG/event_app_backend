package com.tharanga.event_app.exceptions;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(String message) {
        super(message);
    }
}
