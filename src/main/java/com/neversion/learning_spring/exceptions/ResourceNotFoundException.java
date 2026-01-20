package com.neversion.learning_spring.exceptions;

//This exception will be thrown when a resource is not found
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
