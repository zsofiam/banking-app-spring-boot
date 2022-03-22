package com.example.banking_app.exception;

import java.time.ZonedDateTime;

public class ErrorDetails {

    private ZonedDateTime timestamp;
    private String message;
    private String details;

    public ErrorDetails(ZonedDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }


}
