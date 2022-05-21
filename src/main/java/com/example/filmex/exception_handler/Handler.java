package com.example.filmex.exception_handler;


import com.example.filmex.exception.AuthenticationException;
import com.example.filmex.exception.FileException;
import com.example.filmex.exception.InvalidTokenException;
import com.example.filmex.exception.NotFoundException;
import com.example.filmex.exception.NullParameterException;
import com.example.filmex.exception.UpdateException;
import com.example.filmex.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.time.LocalDateTime.now;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(NullParameterException.class)
    protected ResponseEntity<Object> handleNullParameterException(final NullParameterException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .error("NullParameterException")
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UpdateException.class)
    protected ResponseEntity<Object> handleUpdateException(final UpdateException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .error("UpdateException")
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(final NotFoundException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .error("NotFoundException")
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenException.class)
    protected ResponseEntity<Object> handleInvalidTokenException(final InvalidTokenException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .error("InvalidTokenException")
                .message(e.getMessage())
                .status(HttpStatus.FORBIDDEN)
                .timestamp(now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(final AuthenticationException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .error("AuthenticationException")
                .message(e.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .timestamp(now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FileException.class)
    protected ResponseEntity<Object> handleFileException(final FileException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .error("FileException")
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
