package com.app.cloudStorage.exception.customAuthExceptions;

import java.util.Map;

public class WrongPasswordException extends BaseAuthException {
    public WrongPasswordException(Map<String, String> fields){
        super("Неверный пароль", fields);
    }
}
