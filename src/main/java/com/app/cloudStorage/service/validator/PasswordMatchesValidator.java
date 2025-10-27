package com.app.cloudStorage.service.validator;

import com.app.cloudStorage.Annotation.PasswordMatches;
import com.app.cloudStorage.model.DTO.AuthDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, AuthDTO> {

    @Override
    public boolean isValid(AuthDTO authDTO, ConstraintValidatorContext context) {
        if (authDTO == null) {
            return false;
        }
        return authDTO.password().equals(authDTO.matchPassword());
    }


}
