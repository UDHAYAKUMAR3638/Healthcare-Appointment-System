package com.HealthCare.SystemApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailAlreadyExistsException() {
        return new ResponseEntity<>("This EmailId is already present", HttpStatus.BAD_REQUEST);

    }

}
