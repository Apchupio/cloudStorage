package com.app.cloudStorage.exception.CustomAuthExceptions;

import com.app.cloudStorage.exception.CustomAuthExceptions.BaseAuthException;

import java.util.Map;

public class UserNotFoundException extends BaseAuthException {
    public UserNotFoundException(Map<String ,String> fields){
        super("Пользователь с таким логином не сущетсвует", fields);
    }
}
