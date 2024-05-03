package com.yufrolov.vocabularytrainer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethod(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
                ex.getFieldErrors().stream()
                        .map(it -> it.getField() + " " + it.getDefaultMessage()).collect(Collectors.toList())
        );
    }

    @ExceptionHandler(value = ProfileNotFoundException.class)
    public ResponseEntity<Object> handleMethod(ProfileNotFoundException ex) {
        return ResponseEntity.badRequest().body(
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(value = ProfileAlreadyExistException.class)
    public ResponseEntity<Object> handleMethod(ProfileAlreadyExistException ex) {
        return ResponseEntity.badRequest().body(
                List.of(ex.getMessage())
        );
    }
}