package com.bsuir.petition.service.category.exception;

public class SuchCategoryExistsException extends Exception {
    public SuchCategoryExistsException() {
    }

    public SuchCategoryExistsException(String message) {
        super(message);
    }

    public SuchCategoryExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchCategoryExistsException(Throwable cause) {
        super(cause);
    }

    public SuchCategoryExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
