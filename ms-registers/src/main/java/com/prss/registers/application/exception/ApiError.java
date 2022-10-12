package com.prss.registers.application.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private String preConsumerId;

    private HttpStatus status;

    private String errorCode;

    private String message;

    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = new ArrayList<>(errors);
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        addError(error);
    }

    public ApiError(HttpStatus status, String message, String error, String preConsumerId) {
        super();
        this.status = status;
        this.message = message;
        this.preConsumerId = preConsumerId;
        addError(error);
    }

    private void addError(String error) {
        if (isNotBlank(error)) {
            this.errors = List.of(error);
        }
    }
}
