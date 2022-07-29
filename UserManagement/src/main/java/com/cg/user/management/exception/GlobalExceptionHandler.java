package com.cg.user.management.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class,
            UserNotFoundException.class,
            RoleNotFoundException.class,
            InputDataValidationException.class,
            DBException.class

    })

    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if (ex instanceof UserNotFoundException || ex instanceof RoleNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            ExceptionResponse response = new ExceptionResponse(new Date(),
                    ErrorCodes.DATA_NOT_FOUND.toString(),
                    ex.getMessage(),
                    request.getDescription(false));
            return new ResponseEntity(response, status);
        }else if (ex instanceof InputDataValidationException) {
            status = HttpStatus.BAD_REQUEST;
            ExceptionResponse response = new ExceptionResponse(new Date(),
                    ErrorCodes.INPUT_DATA_VALIDATION_FAIL.toString(),
                    ex.getMessage(),
                    request.getDescription(false));
            return new ResponseEntity(response, status);
        }else if (ex instanceof DBException) {
            status = HttpStatus.BAD_REQUEST;
            ExceptionResponse response = new ExceptionResponse(new Date(),
                    ErrorCodes.DB_ERROR.toString(),
                    ex.getMessage(),
                    request.getDescription(false));
            return new ResponseEntity(response, status);
        }else if (ex instanceof Exception) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            ExceptionResponse response = new ExceptionResponse(new Date(),
                    ErrorCodes.SERVER_ERROR.toString(),
                    ex.getMessage(),
                    request.getDescription(false));
            return new ResponseEntity(response, status);
        } else {
            throw ex;
        }
    }
}
