package com.app.cloudStorage.service.Auth;

import com.app.cloudStorage.exception.CustomAuthExceptions.WrongPasswordException;
import com.app.cloudStorage.model.DTO.AuthDTO;
import com.app.cloudStorage.model.entity.User;
import com.app.cloudStorage.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SignInService {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public boolean authentication(AuthDTO authDTO) {
        User user = userService.getUserByLogin(authDTO);
        UserDetails userDetails = userService.loadUserByUsername(authDTO.login());
        if (passwordEncoder.matches(authDTO.password(), user.getPassword())) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } else {
            Map<String, String> exceptionsFields = userService.getFields(authDTO);
            throw new WrongPasswordException(exceptionsFields);
        }
    }
}
