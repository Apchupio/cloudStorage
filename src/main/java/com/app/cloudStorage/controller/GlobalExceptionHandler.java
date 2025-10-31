package com.app.cloudStorage.controller;

import com.app.cloudStorage.exception.customAuthExceptions.IncorrectUserDataException;
import com.app.cloudStorage.exception.customAuthExceptions.UserAlreadyExistException;
import com.app.cloudStorage.exception.customAuthExceptions.UserNotFoundException;
import com.app.cloudStorage.exception.customAuthExceptions.WrongPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<WrongPasswordException> WrongPasswordExceptionHandler(WrongPasswordException exception) {
        log.debug("Ошибка авторизации - " + exception.getMessage() + " " + exception.getFields());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundException> UserNotFoundExceptionHandler(UserNotFoundException exception) {
        log.debug("Ошибка авторизации - " + exception.getMessage() + " " + exception.getFields());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<UserAlreadyExistException> UserAlreadyExistExceptionHandler(UserAlreadyExistException exception) {
        log.debug("Ошибка регистрации - " + exception.getMessage() + " " + exception.getFields());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
    }

    @ExceptionHandler(IncorrectUserDataException.class)
    public ResponseEntity<IncorrectUserDataException> incorrectUserDataExceptionHandler(IncorrectUserDataException exception) {
        log.debug("Ошибка регистрации - " + exception.getMessage() + " " + exception.getFields());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }


}
