package com.bsuir.petition.service.vote.exception;

public class VoteNotFoundException extends Exception {

    public VoteNotFoundException() {
    }

    public VoteNotFoundException(String message) {
        super(message);
    }

    public VoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteNotFoundException(Throwable cause) {
        super(cause);
    }

    public VoteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
