package com.example.banking_app.exception;

public class AccountNotFoundException extends RuntimeException{

    private final String message;

    public AccountNotFoundException(String message) {
        super();

        this.message = message;
    }

}
