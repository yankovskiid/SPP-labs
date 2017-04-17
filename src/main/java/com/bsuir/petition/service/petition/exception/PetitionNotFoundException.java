package com.bsuir.petition.service.petition.exception;

public class PetitionNotFoundException extends Exception {
    public PetitionNotFoundException() {
    }

    public PetitionNotFoundException(String message) {
        super(message);
    }

    public PetitionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PetitionNotFoundException(Throwable cause) {
        super(cause);
    }

    public PetitionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
