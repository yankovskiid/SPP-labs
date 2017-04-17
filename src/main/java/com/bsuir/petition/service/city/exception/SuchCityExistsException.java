package com.bsuir.petition.service.city.exception;

public class SuchCityExistsException extends Exception {
    public SuchCityExistsException() {
    }

    public SuchCityExistsException(String message) {
        super(message);
    }

    public SuchCityExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchCityExistsException(Throwable cause) {
        super(cause);
    }

    public SuchCityExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
