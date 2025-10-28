package com.app.cloudStorage.exception.customAuthExceptions;

import java.util.Map;

public class BaseAuthException extends RuntimeException{

    private Map<String, String> fields;

    public BaseAuthException(String message, Map<String, String> fields){
        super(message);
        this.fields = fields;
    }

    public String getField(String nameField) {
        return fields.get(nameField);
    }
}
