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

    @ExceptionHandler(value = HttpClientErrorException.class)
    protected ResponseEntity<Object> handleHttpClientErrorException() {
        Map<String, String> map = new HashMap<>();
        map.put("errorMessage", "The service is temporarily unavailable, try again later");
        return new ResponseEntity<>(map, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
