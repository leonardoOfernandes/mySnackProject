package com.prss.chatbot.communication.application.exception;

import com.google.common.base.CaseFormat;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String INVALID_VALUE_FIELD_MESSAGE = "fields with invalid values";

    @ResponseBody
    @ExceptionHandler(value = {NotFoundException.class})
    protected final ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex) {
        log.info("[ControllerExceptionHandler]: NotFoundException", ex);

        ApiError apiError = new ApiError(NOT_FOUND, ex.getLocalizedMessage(), ex.getMessage());

        log.info("[ControllerExceptionHandler] :: handleNotFoundException: {}", apiError);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    protected final ResponseEntity<ApiError> handleException(Exception ex) {
        log.error("[ControllerExceptionHandler]: internal server error", ex);

        String rootMessage = getRootMessage(ex);
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, rootMessage, ex.getMessage());

        log.info("[ControllerExceptionHandler] :: handleException: {}", apiError);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("[ControllerExceptionHandler]: NoHandlerFoundException", ex);

        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        ApiError apiError = new ApiError(NOT_FOUND, ex.getLocalizedMessage(), error);

        log.info("[ControllerExceptionHandler] :: handleNoHandlerFoundException: {}", apiError);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            log.info("[ControllerExceptionHandler] :: handleMethodArgumentNotValid : field: {} rejected value: {}", error.getField(), error.getRejectedValue());
            errors.add(toSnakeCase(error.getField()) + " " + error.getDefaultMessage());
        }

        ApiError apiError = new ApiError(
                UNPROCESSABLE_ENTITY,
                INVALID_VALUE_FIELD_MESSAGE,
                errors
        );

        log.info("[ControllerExceptionHandler] :: handleMethodArgumentNotValid: {}", apiError);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    private final String toSnakeCase(String camelCaseValue) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, camelCaseValue);
    }

    private String getRootMessage(Throwable ex) {
        Throwable currentThrowable = ex;
        while (currentThrowable.getCause() != null) {
            currentThrowable = currentThrowable.getCause();
        }

        return currentThrowable.getMessage();
    }
}
