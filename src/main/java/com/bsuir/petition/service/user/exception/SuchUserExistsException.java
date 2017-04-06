package com.bsuir.petition.service.user.exception;

public class SuchUserExistsException extends Exception {
    public SuchUserExistsException() {
    }

    public SuchUserExistsException(String message) {
        super(message);
    }

    public SuchUserExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchUserExistsException(Throwable cause) {
        super(cause);
    }

    public SuchUserExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
