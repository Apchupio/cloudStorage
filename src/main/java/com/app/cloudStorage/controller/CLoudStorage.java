package com.app.cloudStorage.controller;

import com.app.cloudStorage.model.dto.auth.AuthDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/api/cloud-storage")
@Slf4j
@RequiredArgsConstructor
public class CLoudStorage {

    @PostMapping("/check-session")
    public ResponseEntity<AuthDTO> checkSession(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        log.info(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
