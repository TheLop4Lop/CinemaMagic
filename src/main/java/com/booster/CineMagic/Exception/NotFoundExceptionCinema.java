package com.booster.CineMagic.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionCinema extends RuntimeException{
    private String errorCode;
    HttpStatus errorStatus;

    public NotFoundExceptionCinema(String errorMessage, String errorCode, HttpStatus errorStatus) {
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
