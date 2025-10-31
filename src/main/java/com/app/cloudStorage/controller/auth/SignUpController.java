package com.app.cloudStorage.controller.auth;

import com.app.cloudStorage.model.dto.auth.AuthDTO;
import com.app.cloudStorage.service.auth.SignUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/cloud-storage/v1/sign-up")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @Tag(name = "auth", description = "Rest контроллер для аутентификации пользователей")
    @Operation(summary = "Регистрация пользователя", description = "В ответе возвращается статус CREATED " +
            "и тело в виде AuthDto.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Успешная регистрация нового пользователя"),
            @ApiResponse(responseCode = "400", description = "Неккоректные данные"),
            @ApiResponse(responseCode = "409", description = "Пользователь с таким логином уже существует")
    })
    @PostMapping("/auth")
    public ResponseEntity<AuthDTO> registrationUser(@RequestBody @Valid AuthDTO authDTO, BindingResult bindingResult) {
        signUpService.registerUser(authDTO, bindingResult);
        log.info("Успешная авторизация пользователя с login - " + authDTO.login());
        return ResponseEntity.status(HttpStatus.CREATED).body(authDTO);
    }
}
