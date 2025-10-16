package com.app.cloudStorage.controller;

import com.app.cloudStorage.exception.CustomAuthExceptions.UserAlreadyExistException;
import com.app.cloudStorage.exception.CustomAuthExceptions.UserNotFoundException;
import com.app.cloudStorage.exception.CustomAuthExceptions.WrongPasswordException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongPasswordException.class)
    public String WrongPasswordExceptionHandler(WrongPasswordException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        model.addAttribute("login", exception.getField("login"));
        model.addAttribute("password", exception.getField("password"));
        return "";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String UserNotFoundExceptionHandler(UserNotFoundException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        model.addAttribute("login", exception.getField("login"));
        model.addAttribute("password", exception.getField("password"));
        return "";
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public String UserAlreadyExistExceptionHandler(UserAlreadyExistException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        model.addAttribute("login", exception.getField("login"));
        model.addAttribute("password", exception.getField("password"));
        return "";
    }

    @ExceptionHandler(RuntimeException.class)
    public String RuntimeExceptionHandler() {
        return "errorPage";
    }
}
