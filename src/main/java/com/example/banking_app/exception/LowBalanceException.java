package com.example.banking_app.exception;

public class LowBalanceException extends RuntimeException{

    public LowBalanceException() {
        super("Not enough money on your account!");

    }
}
