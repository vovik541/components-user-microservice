package com.microservice.user.exception;

public class ReaderWasNotFoundException extends RuntimeException{

    public ReaderWasNotFoundException(String message){
        super(message);
    }
}
