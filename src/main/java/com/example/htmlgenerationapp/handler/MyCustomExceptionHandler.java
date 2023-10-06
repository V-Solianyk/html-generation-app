package com.example.htmlgenerationapp.handler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyCustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String SERVICE_UNAVAILABLE_MESSAGE = "The service is temporarily "
            + "unavailable, try again later";

    @ExceptionHandler(value = HttpClientErrorException.class)
    protected ResponseEntity<Object> handleHttpClientErrorException() {
        Map<String, String> map = new HashMap<>();
        map.put("errorMessage", SERVICE_UNAVAILABLE_MESSAGE);
        return new ResponseEntity<>(map, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
