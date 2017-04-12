package com.bsuir.petition.service.country.exception;

/**
 * Created by Александр on 12.04.2017.
 */
public class SuchCountryExistsException extends Exception {
    public SuchCountryExistsException() {
    }

    public SuchCountryExistsException(String message) {
        super(message);
    }

    public SuchCountryExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchCountryExistsException(Throwable cause) {
        super(cause);
    }

    public SuchCountryExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
