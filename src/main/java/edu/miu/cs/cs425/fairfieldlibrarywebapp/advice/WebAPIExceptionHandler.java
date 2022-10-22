package edu.miu.cs.cs425.fairfieldlibrarywebapp.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.exception.CustomNotFoundException;

@RestControllerAdvice
public class WebAPIExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomNotFoundException.class)
    public Map<String, String> handleWebAPIException(CustomNotFoundException customNotFoundException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", customNotFoundException.getMessage());
        return errorMap;
    }
}
