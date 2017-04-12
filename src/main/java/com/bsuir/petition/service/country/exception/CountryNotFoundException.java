package com.bsuir.petition.service.country.exception;

/**
 * Created by Александр on 12.04.2017.
 */
public class CountryNotFoundException extends Exception {
    public CountryNotFoundException() {
    }

    public CountryNotFoundException(String message) {
        super(message);
    }

    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryNotFoundException(Throwable cause) {
        super(cause);
    }

    public CountryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
