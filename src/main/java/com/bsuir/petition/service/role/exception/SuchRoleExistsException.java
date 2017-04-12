package com.bsuir.petition.service.role.exception;

public class SuchRoleExistsException extends Exception {
    public SuchRoleExistsException() {
    }

    public SuchRoleExistsException(String message) {
        super(message);
    }

    public SuchRoleExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchRoleExistsException(Throwable cause) {
        super(cause);
    }

    public SuchRoleExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
