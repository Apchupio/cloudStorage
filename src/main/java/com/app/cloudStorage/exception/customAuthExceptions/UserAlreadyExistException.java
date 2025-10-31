package com.app.cloudStorage.exception.customAuthExceptions;

import java.util.Map;

public class UserAlreadyExistException extends BaseAuthException {

    public UserAlreadyExistException(Map<String, String> fields) {
        super("Пользователь уже существует", fields);
    }
}
