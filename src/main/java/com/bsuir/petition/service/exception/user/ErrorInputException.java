package com.bsuir.petition.service.exception.user;

public class ErrorInputException extends Exception {
    public ErrorInputException() {
    }

    public ErrorInputException(String message) {
        super(message);
    }

    public ErrorInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorInputException(Throwable cause) {
        super(cause);
    }

    public ErrorInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
