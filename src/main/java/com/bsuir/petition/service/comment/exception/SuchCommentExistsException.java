package com.bsuir.petition.service.comment.exception;

public class SuchCommentExistsException extends Exception {
    public SuchCommentExistsException() {
    }

    public SuchCommentExistsException(String message) {
        super(message);
    }

    public SuchCommentExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchCommentExistsException(Throwable cause) {
        super(cause);
    }

    public SuchCommentExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
