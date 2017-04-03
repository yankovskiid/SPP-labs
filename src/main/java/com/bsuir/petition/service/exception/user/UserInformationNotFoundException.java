package com.bsuir.petition.service.exception.user;

public class UserInformationNotFoundException extends Exception {
    public UserInformationNotFoundException() {
    }

    public UserInformationNotFoundException(String message) {
        super(message);
    }

    public UserInformationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInformationNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserInformationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
