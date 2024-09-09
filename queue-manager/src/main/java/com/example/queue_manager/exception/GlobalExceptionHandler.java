package com.example.queue_manager.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //TODO pomiar czasu wszystkich wyjątku i obsługa ile trwa
    @ExceptionHandler(InvalidJobException.class)
    @TimerAnnotation
    public ResponseEntity<ErrorResponse> handleInvalidJobException(InvalidJobException ex){
        return new ResponseEntity<>(preparErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), ex.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    };
    @ExceptionHandler({RuntimeException.class, Exception.class })
    @TimerAnnotation
    public ResponseEntity<ErrorResponse> handleGeneralExceptions(RuntimeException ex){
        log.error("Unexpected error occurred", ex);
        return new ResponseEntity<>(preparErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    };

    private ErrorResponse preparErrorResponse(int status, String message) {
        return new ErrorResponse(status, message);
    }
}
