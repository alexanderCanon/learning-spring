package com.neversion.learning_spring.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice // annotation is used to handle exceptions globally in a Spring Boot application
public class GlobalHandlerException {

    // If somebody try to access a resource that does not exist
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // This method will handle ResourceNotFoundException and return a ResponseEntity
    // with ErrorResponse
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Each data that failed and its message
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        // Return a ResponseEntity with the ErrorResponse object and status code
        return ResponseEntity.badRequest().body(errors);

        // More methods can be added here to handle other exceptions
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleRouteNotFoundException(NoResourceFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "La ruta que intentas consultar no existe. Por favor verifica la URL. ",
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
