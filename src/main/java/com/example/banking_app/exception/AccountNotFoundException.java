package com.example.banking_app.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(Long id) {
        super("Could not find account " + id);

    }

}
