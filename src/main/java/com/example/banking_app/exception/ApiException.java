package com.example.banking_app.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);

    }
}
