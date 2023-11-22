package com.booster.CineMagic.Exception;

import org.springframework.http.HttpStatus;

public class ExistingDataException extends RuntimeException{
    private String errorCode;
    private HttpStatus errorStatus;

    public ExistingDataException(String errorMessage, String errorCode, HttpStatus errorStatus) {
        super(errorMessage);

        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(HttpStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

}
