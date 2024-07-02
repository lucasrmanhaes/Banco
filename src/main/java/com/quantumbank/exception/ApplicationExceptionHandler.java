package com.quantumbank.exception;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> invalidArguments(MethodArgumentNotValidException error){
        Map<String, String> mapErrors = new HashMap<>();
        List<FieldError> fieldErrors = error.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            mapErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return mapErrors;
    }

}