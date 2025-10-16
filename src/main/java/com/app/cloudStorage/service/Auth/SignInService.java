package com.app.cloudStorage.service.Auth;

import com.app.cloudStorage.exception.CustomAuthExceptions.WrongPasswordException;
import com.app.cloudStorage.model.DTO.AuthDTO;
import com.app.cloudStorage.model.entity.User;
import com.app.cloudStorage.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SignInService {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;


    //Проверка подлиности пользователя
    public boolean authentication(AuthDTO authDTO) {
        User user = userService.getUserByLogin(authDTO);
        if (passwordEncoder.matches(authDTO.password(), user.getPassword())) {
            return true;
        } else {
            Map<String, String> exceptionsFields = userService.getFields(authDTO);
            throw new WrongPasswordException(exceptionsFields);
        }

    }
}
