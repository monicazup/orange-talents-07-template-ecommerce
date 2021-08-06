package com.zupedu.monica.mercadolivre.config;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class CustomHandlerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> manipulador(ConstraintViolationException e) {

        return ResponseEntity.badRequest().body("Campo inválido.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manipulador(MethodArgumentNotValidException exception) {
        Collection<String> messages = new ArrayList<>();
        BindingResult bind = exception.getBindingResult();
        List<FieldError> errors = bind.getFieldErrors();
        errors.forEach(fieldError -> {
            String message = "Falha: valor de " + fieldError.getField() + " " + fieldError.getDefaultMessage();
            messages.add(message);

        });
        ErroPadronizado erroPadronizado = new ErroPadronizado(messages);
        return ResponseEntity.badRequest().body(erroPadronizado);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> manipulador(ApiException e) {

        Collection<String> messages = new ArrayList<>();
        messages.add(e.getReason());
        ErroPadronizado erroPadronizado = new ErroPadronizado(messages);

        return ResponseEntity.status(e.getHttpStatus()).body(erroPadronizado);
    }

}

