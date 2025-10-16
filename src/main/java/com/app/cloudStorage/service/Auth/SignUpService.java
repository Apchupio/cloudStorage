package com.app.cloudStorage.service.Auth;

import com.app.cloudStorage.model.DTO.AuthDTO;
import com.app.cloudStorage.model.entity.User;
import com.app.cloudStorage.repository.UserRepository;
import com.app.cloudStorage.service.Impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserServiceImpl userService;

    public boolean registerUser(AuthDTO authDTO, BindingResult bindingResult) {
        userService.userDataCorrect(authDTO, bindingResult);
        userService.isExist(authDTO);
        User user = userService.convertToUser(authDTO);
        return userService.save(user);
    }
}
