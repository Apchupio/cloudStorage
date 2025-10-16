package com.app.cloudStorage.exception.CustomAuthExceptions;

import com.app.cloudStorage.exception.CustomAuthExceptions.BaseAuthException;

import java.util.Map;

public class UserAlreadyExistException extends BaseAuthException {

    public UserAlreadyExistException(Map<String, String> fields) {
        super("Пользователь не существует", fields);
    }
}
