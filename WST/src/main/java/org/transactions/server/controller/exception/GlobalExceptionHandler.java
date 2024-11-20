package org.transactions.server.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.transactions.server.exception.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundHandler(NotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(404, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> argumentNotValidHandler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(new ErrorResponse(400, message), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    public static final class ErrorResponse {
        private int statusCode;
        private String message;
    }

}
