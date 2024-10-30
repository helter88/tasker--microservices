package com.tasker.calandar_manager_entrance.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidSaveException.class)
    public ResponseEntity<?> handleInvalidSaveException(InvalidSaveException ex) {
        return new ResponseEntity<>(preparErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({RuntimeException.class, Exception.class })
    public ResponseEntity<ErrorResponse> handleGeneralExceptions(RuntimeException ex){
        log.error("Unexpected error occurred", ex);
        return new ResponseEntity<>(preparErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    };

    private ErrorResponse preparErrorResponse(int status, String message) {
        return new ErrorResponse(status, message);
    }
}
