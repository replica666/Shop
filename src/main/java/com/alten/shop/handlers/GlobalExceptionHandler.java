package com.alten.shop.handlers;

import com.alten.shop.dto.response.ErrorDto;
import com.alten.shop.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> entityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(ErrorDto.builder()
                .timestamp(new Date())
                .httpCode(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build());
    }

    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(EntityAlreadyExistException.class)
    public ErrorDto entityAlreadyExistException(EntityAlreadyExistException e) {
        return ErrorDto.builder()
                .timestamp(new Date())
                .httpCode(HttpStatus.FOUND)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleException(ObjectNotValidException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getErrorMessages());
    }


    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(FieldNotFoundException e) {
        ErrorDto err = ErrorDto.builder()
                .timestamp(new Date())
                .httpCode(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, List<String>>> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, List<String>>> handleInvalidCredentials(InvalidCredentialsException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.UNAUTHORIZED);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
