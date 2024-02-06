package com.smallworld.Exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionCreator {

    private ExceptionCreator() {
    }

    public static ErrorResponse createErrorResponse(
            String responseCode,
            Date timestamp,
            String message,
            String description,
            HttpStatus status) {
        return new ErrorResponse(responseCode, timestamp, message, description, status);
    }

}
