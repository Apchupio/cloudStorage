package com.app.cloudStorage.controller.auth;

import com.app.cloudStorage.model.DTO.AuthDTO;
import com.app.cloudStorage.service.Auth.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/cloud-storage/sign-up")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/page")
    public String signUpPage() {
        return "signUp";
    }

    @PostMapping("/auth")
    public String registration(@Valid AuthDTO authDTO, BindingResult bindingResult) {
        if (signUpService.registerUser(authDTO, bindingResult)) {
            return "redirect:/api/cloud-storage/sign-in";
        } else {
            return "errorPage";
        }
    }
}
