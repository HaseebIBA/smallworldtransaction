package com.smallworld.Exception;


import com.smallworld.Miscellaneous.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = TransactionLoaderException.class)
    public ResponseEntity<ErrorResponse> transactionExceptionHandler(
            HttpServletRequest req, HttpServletResponse response, TransactionLoaderException e) {

        logException(req.getRequestURL().toString(), e);

        ErrorResponse obj = ExceptionCreator.createErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR,
                new Date(),
                e.getMessage(),
                ErrorCode.getDescription(ErrorCode.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR
        );


        return new ResponseEntity<>(obj, obj.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(HttpServletRequest req, HttpServletResponse response, Exception e) {


        logException(req.getRequestURL().toString(), e);

        ErrorResponse obj = ExceptionCreator.createErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR,
                new Date(),
                e.getMessage(),
                ErrorCode.getDescription(ErrorCode.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        return new ResponseEntity<>(obj, obj.getStatus());
    }

    private void logException(String requestUrl, Exception ex) {
        log.error("Exception occurred for request URL: {}", requestUrl, ex);
    }

}
