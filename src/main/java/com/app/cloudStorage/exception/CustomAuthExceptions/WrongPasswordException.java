package com.app.cloudStorage.exception.CustomAuthExceptions;

import com.app.cloudStorage.exception.CustomAuthExceptions.BaseAuthException;

import java.util.Map;

public class WrongPasswordException extends BaseAuthException {
    public WrongPasswordException(Map<String, String> fields){
        super("Неверный пароль", fields);
    }
}
