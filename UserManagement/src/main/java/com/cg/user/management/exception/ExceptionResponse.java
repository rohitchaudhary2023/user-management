package com.cg.user.management.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String errorCode;
    private String errorMessage;
    private String errorDescription;

    public ExceptionResponse(String errorCode, String errorMessage, String errorDescription) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
    }

    public ExceptionResponse(Date timestamp, String errorCode, String errorMessage, String errorDescription) {
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
