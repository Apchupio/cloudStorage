package com.app.cloudStorage.service.auth;

import com.app.cloudStorage.exception.customAuthExceptions.WrongPasswordException;
import com.app.cloudStorage.model.dto.auth.AuthDTO;
import com.app.cloudStorage.model.entity.User;
import com.app.cloudStorage.service.Impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SignInService {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    public boolean signIn(AuthDTO authDTO, HttpServletRequest request, HttpServletResponse response) {
        User user = userService.getUserByLogin(authDTO);
        if (passwordEncoder.matches(authDTO.password(), user.getPassword())) {
            authenticationService.authenticate(user, request, response);
            return true;
        } else {
            Map<String, String> exceptionsFields = userService.getFields(authDTO);
            throw new WrongPasswordException(exceptionsFields);
        }
    }
}
