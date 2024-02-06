package com.smallworld.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String responseCode;
    private Date timestamp;
    private String message;
    private String description;
    private HttpStatus status;

    public static ErrorResponse of(String responseCode, Date timestamp, String message, String description, HttpStatus status) {
        return new ErrorResponse(responseCode, timestamp, message, description, status);
    }

}