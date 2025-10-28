package com.app.cloudStorage.controller.auth;

import com.app.cloudStorage.model.dto.AuthDTO;
import com.app.cloudStorage.service.auth.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/cloud-storage/v1/sign-up")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/auth")
    public ResponseEntity<AuthDTO> registrationUser(@RequestBody @Valid AuthDTO authDTO, BindingResult bindingResult) {
        signUpService.registerUser(authDTO, bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(authDTO);
    }
}
