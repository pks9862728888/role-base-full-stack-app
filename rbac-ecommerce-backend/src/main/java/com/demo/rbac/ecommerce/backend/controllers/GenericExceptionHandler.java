package com.demo.rbac.ecommerce.backend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GenericExceptionHandler  {

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<String> handleException(AuthorizationDeniedException ex) {
        log.warn(ex.getMessage());
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(httpStatus.getReasonPhrase(), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Exception caught in generic exception handler: {}", e.toString());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(httpStatus.getReasonPhrase(), httpStatus);
    }
}
