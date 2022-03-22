package com.example.banking_app.exception;

public class NotEnoughCoverException extends RuntimeException{
    private final String message;

    public NotEnoughCoverException(String message) {
        super();

        this.message = message;
    }
}
