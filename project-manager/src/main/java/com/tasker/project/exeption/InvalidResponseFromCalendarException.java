package com.tasker.project.exeption;

public class InvalidResponseFromCalendarException extends RuntimeException{
    public InvalidResponseFromCalendarException(String message) {
        super(message);
    }
}
