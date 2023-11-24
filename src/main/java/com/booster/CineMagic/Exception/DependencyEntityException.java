package com.booster.CineMagic.Exception;

import org.springframework.http.HttpStatus;

public class DependencyEntityException extends RuntimeException{
    private String errorCode;
    private HttpStatus errorStatus;

    public DependencyEntityException(String message, String errorCode, HttpStatus errorStatus) {
        super(message);

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
