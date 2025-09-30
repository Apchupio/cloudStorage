package com.app.cloudStorage.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Setter;

@Setter
public record AuthDTO(
        @NotBlank(message = "Логин не должен быть пустым")
        @Size(min = 5, max = 25, message = "Логин должен быть размером от 5 до 25 символов")
        String login,

        @NotBlank(message = "Пароль не должен быть пустым")
        @Size(min = 5, max = 25, message = "Пароль должен быть размером от 5 до 25 символов")
        String password,
        String matchPassword

) {
}
