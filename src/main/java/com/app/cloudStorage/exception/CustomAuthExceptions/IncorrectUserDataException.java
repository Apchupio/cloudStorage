package com.app.cloudStorage.exception.CustomAuthExceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class IncorrectUserDataException extends RuntimeException{

    private Map<String, String> errors;
    private Map<String, String> fields;

    public IncorrectUserDataException(Map<String, String> errors, Map<String, String> fields){
        this.errors = errors;
        this.fields = fields;
    }
}
