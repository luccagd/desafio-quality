package com.example.desafioquality.exception;

import com.example.desafioquality.error.AppErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // 422
        HttpStatus unprocessableEntity = HttpStatus.UNPROCESSABLE_ENTITY;

        for (FieldError field : ex.getBindingResult().getFieldErrors()) {
            errors.put(field.getField(), field.getDefaultMessage());
        }

        return new ResponseEntity<>(
                AppErrorResponse.builder()
                        .timestamp(Date.from(Instant.now()))
                        .code(unprocessableEntity.value())
                        .status(unprocessableEntity.name())
                        .message(ex.getMessage())
                        .data(errors)
                        .build(),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<AppErrorResponse> handleBusinessException(BusinessException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(
                AppErrorResponse.builder()
                        .timestamp(Date.from(Instant.now()))
                        .code(badRequest.value())
                        .status(badRequest.name())
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<AppErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(
                AppErrorResponse.builder()
                        .timestamp(Date.from(Instant.now()))
                        .code(notFound.value())
                        .status(notFound.name())
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }
}
