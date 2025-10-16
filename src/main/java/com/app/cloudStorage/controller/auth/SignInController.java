package com.app.cloudStorage.controller.auth;

import com.app.cloudStorage.model.DTO.AuthDTO;
import com.app.cloudStorage.service.Auth.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/cloud-storage/sign-in")
public class SignInController {

    private final SignInService signInService;

    @GetMapping("/page")
    @ResponseBody
    public String signInPage() {
        return "signIn";
    }

    @PostMapping("/auth")
    public String authorization(AuthDTO authDTO) {
        signInService.authentication(authDTO);
        return "redirect:/api/cloud-storage";
    }
}
