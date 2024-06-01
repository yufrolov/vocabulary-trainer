package com.yufrolov.vocabularytrainer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleMethod(Exception ex) {
        return ResponseEntity.internalServerError().body(
                List.of(ex.getClass().getName())
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethod(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
                ex.getFieldErrors().stream()
                        .map(it -> it.getField() + " " + it.getDefaultMessage()).collect(Collectors.toList())
        );
    }

    @ExceptionHandler(value = ProfileNotFoundException.class)
    public ResponseEntity<Object> handleMethod(ProfileNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = ProfileAlreadyExistException.class)
    public ResponseEntity<Object> handleMethod(ProfileAlreadyExistException ex) {
        return ResponseEntity.badRequest().body(
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(value = LanguageNotFoundException.class)
    public ResponseEntity<Object> handleMethod(LanguageNotFoundException ex) {
        return ResponseEntity.badRequest().body(
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(value = VocabularyNotFoundException.class)
    public ResponseEntity<Object> handleMethod(VocabularyNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = MyBadCredentialsException.class)
    public ResponseEntity<Object> handleMethod(MyBadCredentialsException ex) {
        return ResponseEntity.badRequest().body(
                List.of(ex.getMessage())
        );
    }
}