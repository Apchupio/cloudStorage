package com.app.cloudStorage.service.auth;

import com.app.cloudStorage.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextHolderStrategy securityContextHolder;
    private final SecurityContextRepository securityContextRepository;

    public Authentication authenticate(User user, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication  = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        log.info("Запрос дошел4");
        SecurityContext context = securityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolder.setContext(context);
        log.info("Запрос дошел5");
        securityContextRepository.saveContext(context, request, response);
        log.info("Пользователь с именем - " + authentication.getName() + " успешно аутентифицирован");
        return authentication;
    }
}
