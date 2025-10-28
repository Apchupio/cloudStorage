package com.app.cloudStorage.controller.auth;

import com.app.cloudStorage.model.dto.AuthDTO;
import com.app.cloudStorage.service.auth.SignInService;
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

    @PostMapping("/auth")
    public ResponseEntity<AuthDTO> authorization(@RequestBody AuthDTO authDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        signInService.authentication(authDTO);
        System.out.println(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(authDTO);
    }
}
