package com.bsuir.petition.service.vote.exception;

public class SuchVoteExistsException extends Exception {
    public SuchVoteExistsException() {
    }

    public SuchVoteExistsException(String message) {
        super(message);
    }

    public SuchVoteExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchVoteExistsException(Throwable cause) {
        super(cause);
    }

    public SuchVoteExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
