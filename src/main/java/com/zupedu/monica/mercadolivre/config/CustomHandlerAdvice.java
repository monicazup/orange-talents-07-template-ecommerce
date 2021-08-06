package com.zupedu.monica.mercadolivre.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomHandlerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> manipulador (ConstraintViolationException e) {

        return ResponseEntity.badRequest().body("Campo inválido.");
    }
}
