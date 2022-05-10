package com.example.filmex.exception;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(final String message) {
        super(message);
    }
}
