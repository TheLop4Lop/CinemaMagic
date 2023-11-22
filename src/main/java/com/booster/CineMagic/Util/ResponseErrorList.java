package com.booster.CineMagic.Util;

import java.util.List;

public class ResponseErrorList {
    private String errorCode;
    private String errorMessage;
    private List<String> errorList;

    public ResponseErrorList(String errorCode, String errorMessage, List<String> errorList) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorList = errorList;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

}
