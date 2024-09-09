package com.example.queue_manager.exception;

public class InvalidJobException extends RuntimeException{
    public InvalidJobException(String message) {
        super(message);
    }
}
