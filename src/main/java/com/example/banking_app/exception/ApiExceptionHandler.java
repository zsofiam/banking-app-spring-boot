package com.example.banking_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(value = {AccountNotFoundException.class})
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {LowBalanceException.class})
    public ResponseEntity<String> handleLowBalanceException(LowBalanceException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception,
                                                            WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                ZonedDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException exception,
                                                   WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                ZonedDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
