package com.app.cloudStorage.service.Auth;

import com.app.cloudStorage.model.DTO.AuthDTO;
import com.app.cloudStorage.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class SignUpService  {

    private final UserServiceImpl userService;

    public void registerUser(AuthDTO authDTO, BindingResult bindingResult) {
        userService.userDataCorrect(authDTO, bindingResult);
        userService.userAlreadyExist(authDTO);
        userService.save(authDTO);
    }
}
