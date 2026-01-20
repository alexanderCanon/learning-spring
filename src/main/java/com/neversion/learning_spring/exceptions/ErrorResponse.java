package com.neversion.learning_spring.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private String statusCode;
    private LocalDateTime date;

    public ErrorResponse(String message, String statusCode, LocalDateTime date) {
        this.message = message;
        this.statusCode = statusCode;
        this.date = date;
    }

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = String.valueOf(statusCode);
        this.date = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
