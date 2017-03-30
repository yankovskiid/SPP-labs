package com.bsuir.petition.bean.dto.response.message;

public class MessageDTO {
    private boolean isError;
    private String message;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
