package com.vizaco.onlinecontrol.exceptions;

/**
 * Created by super on 2/19/15.
 */
public class CustomGenericException extends RuntimeException { //TODO: May be extends Throwable or add new class for extends Exception

    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String errorMsg;

    public CustomGenericException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}