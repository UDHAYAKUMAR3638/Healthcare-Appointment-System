package com.HealthCare.SystemApplication.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }

}
