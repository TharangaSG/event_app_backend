package com.tharanga.event_app.exceptions;

public class FileExistsException extends RuntimeException{
    public FileExistsException(String message) {
        super(message);
    }
}
