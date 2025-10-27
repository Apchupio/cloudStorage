package com.app.cloudStorage.controller;

import com.app.cloudStorage.exception.CustomAuthExceptions.IncorrectUserDataException;
import com.app.cloudStorage.exception.CustomAuthExceptions.UserAlreadyExistException;
import com.app.cloudStorage.exception.CustomAuthExceptions.UserNotFoundException;
import com.app.cloudStorage.exception.CustomAuthExceptions.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<WrongPasswordException> WrongPasswordExceptionHandler(WrongPasswordException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundException> UserNotFoundExceptionHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<UserAlreadyExistException> UserAlreadyExistExceptionHandler(UserAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
    }

    @ExceptionHandler(IncorrectUserDataException.class)
    public ResponseEntity<IncorrectUserDataException> incorrectUserDataExceptionHandler(IncorrectUserDataException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }


}
