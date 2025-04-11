package com.juanbuild.juan.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus())
                             .body(new ErrorResponse(errorCode.getMessage()));
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getStatus())
                             .body(new ErrorResponse(message));
    }
}
