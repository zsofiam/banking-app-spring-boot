package com.example.banking_app.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);


    }
}
