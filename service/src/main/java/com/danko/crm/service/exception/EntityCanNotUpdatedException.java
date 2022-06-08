package com.danko.crm.service.exception;

public class EntityCanNotUpdatedException extends RuntimeException {
    private final String errorMessage;
    private final Long id;

    public EntityCanNotUpdatedException(String errorMessage, Long id) {
        this.errorMessage = errorMessage;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}