package com.danko.crm.rest_server.advice;

import com.danko.crm.service.exception.EntityCanNotUpdatedException;
import com.danko.crm.service.exception.EntityNotFoundException;
import com.danko.crm.service.exception.NestedEntityInactiveException;
import com.danko.crm.service.response_entity.ExceptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
@AllArgsConstructor
public class ControllerAdvice {
    private static final String ERROR_CODE_0001_ENTITY_NOT_FOUND = "Error: 0001";
    private static final String ERROR_CODE_0002_ENTITY_CAN_NOT_UPDATE = "Error: 0002";
    private static final String ERROR_CODE_0003_ENTITY_PARAM_IS_NOT_VALID = "Error: 0003";
    private static final String ERROR_CODE_0004_NESTED_ENTITY_IS_INACTIVE = "Error: 0004";

    private static final String INPUT_DATA_IS_NOT_CORRECT = "input.parameter.is.not.correct";

    private final ResourceBundleMessageSource messageSource;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerException(EntityNotFoundException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        stringLocaleMessage(exception.getErrorMessage()),
                        Collections.singletonList(exception.getId().toString()),
                        LocalDateTime.now().toString(),
                        ERROR_CODE_0001_ENTITY_NOT_FOUND)
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityCanNotUpdatedException.class)
    public ResponseEntity<ExceptionResponse> handlerException(EntityCanNotUpdatedException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        stringLocaleMessage(exception.getErrorMessage()),
                        Collections.singletonList(exception.getId().toString()),
                        LocalDateTime.now().toString(),
                        ERROR_CODE_0002_ENTITY_CAN_NOT_UPDATE)
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NestedEntityInactiveException.class)
    public ResponseEntity<ExceptionResponse> handlerException(NestedEntityInactiveException exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        stringLocaleMessage(exception.getErrorMessage()),
                        Collections.singletonList(null),
                        LocalDateTime.now().toString(),
                        ERROR_CODE_0004_NESTED_ENTITY_IS_INACTIVE)
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handlerException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + stringLocaleMessage(error.getDefaultMessage()));
        }

        ExceptionResponse response = new ExceptionResponse();

        response.setIncorrectParameters(errors);
        response.setErrorCode(ERROR_CODE_0003_ENTITY_PARAM_IS_NOT_VALID);
        response.setErrorTime(LocalDateTime.now().toString());
        response.setErrorMessage(stringLocaleMessage(INPUT_DATA_IS_NOT_CORRECT));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String stringLocaleMessage(String error) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(error, null, locale);
    }
}
