package com.app.cloudStorage.controller.auth;

import com.app.cloudStorage.model.dto.auth.AuthDTO;
import com.app.cloudStorage.service.auth.SignInService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cloud-storage/v1/sign-in")
public class SignInController {

    private final SignInService signInService;

    @Tag(name = "auth", description = "Rest контроллер для аутентификации пользователей")
    @Operation(summary = "Авторизация пользователя", description = "В ответе возвращается статус Ok " +
            "и тело в виде AuthDto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешная авторизация пользователя"),
            @ApiResponse(responseCode = "400", description = "Неверный пароль"),
            @ApiResponse(responseCode = "401", description = "Пользователя с таким логином не существует")
    })
    @PostMapping("/auth")
    public ResponseEntity<AuthDTO> authorization(@RequestBody AuthDTO authDTO, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        signInService.signIn(authDTO, request, response);
        System.out.println(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(authDTO);
    }
}
