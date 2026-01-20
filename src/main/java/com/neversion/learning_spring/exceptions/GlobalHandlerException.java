package com.neversion.learning_spring.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // annotation is used to handle exceptions globally in a Spring Boot application
public class GlobalHandlerException {

    // This method will handle ResourceNotFoundException and return a ResponseEntity
    // with ErrorResponse
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

        // Create an ErrorResponse object with the exception message, status code, and
        // timestamp
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString(),
                LocalDateTime.now());
        // Return a ResponseEntity with the ErrorResponse object and status code
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

        // More methods can be added here to handle other exceptions
    }
}
