package com.tasker.project.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CreationCalendarEventException.class)
    public ResponseEntity<ErrorResponse>  handleCreationCalendarEventException(CreationCalendarEventException ex){
        return new ResponseEntity<>(preparErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidResponseFromCalendarException.class)
    public ResponseEntity<ErrorResponse>  handleInvalidResponseFromCalendarException(InvalidResponseFromCalendarException ex){
        return new ResponseEntity<>(preparErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse preparErrorResponse(int status, String message) {
        return new ErrorResponse(status, message);
    }
}
