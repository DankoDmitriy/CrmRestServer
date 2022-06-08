package com.danko.crm.service.exception;

public class NestedEntityInactiveException extends RuntimeException {
    private final String errorMessage;

    public NestedEntityInactiveException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
