package com.cg.user.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputDataValidationException extends RuntimeException{
    public InputDataValidationException(String message) {
        super(message);
    }

    public InputDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
