package com.msvc.invex.entrypoints.handler;

import com.msvc.invex.entrypoints.dto.ErrorResponse;
import com.msvc.invex.infrastructure.exceptions.EmployeeNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException ex) {

        ErrorResponse error = ErrorResponse.builder()
                .code("EMPLOYEE_NOT_FOUND")
                .message(ex.getMessage())
                .details(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse error = ErrorResponse.builder()
                .code("VALIDATION_ERROR")
                .message("Error de validación")
                .details(details)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {

        ErrorResponse error = ErrorResponse.builder()
                .code("INTERNAL_ERROR")
                .message("Error interno del servidor")
                .details(List.of(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}