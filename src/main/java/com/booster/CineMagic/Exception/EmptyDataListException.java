package com.booster.CineMagic.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class EmptyDataListException extends RuntimeException{
    String errorCode;
    HttpStatus errorStatus;
    BindingResult errorResults;

    public EmptyDataListException(String errorMessage, String errorCode, HttpStatus errorStatus, BindingResult errorResults) {
        super(errorMessage);

        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
        this.errorResults = errorResults;
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

    public BindingResult getErrorResults() {
        return errorResults;
    }

    public void setErrorResults(BindingResult errorResults) {
        this.errorResults = errorResults;
    }
}
