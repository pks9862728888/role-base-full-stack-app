package com.demo.rbac.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GenericExceptionHandler  {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Exception caught in generic exception handler: {}", e.toString());
        return new ResponseEntity("Internal Server Error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
