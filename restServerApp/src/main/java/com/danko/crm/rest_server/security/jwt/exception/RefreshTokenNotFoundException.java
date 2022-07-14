package com.danko.crm.rest_server.security.jwt.exception;

public class RefreshTokenNotFoundException extends RuntimeException{
    private final String errorMessage;

    public RefreshTokenNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
