package com.app.cloudStorage.service.Impl;

import com.app.cloudStorage.exception.customAuthExceptions.IncorrectUserDataException;
import com.app.cloudStorage.exception.customAuthExceptions.UserAlreadyExistException;
import com.app.cloudStorage.exception.customAuthExceptions.UserNotFoundException;
import com.app.cloudStorage.model.dto.AuthDTO;
import com.app.cloudStorage.model.entity.User;
import com.app.cloudStorage.repository.UserRepository;
import com.app.cloudStorage.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean userAlreadyExist(AuthDTO authDTO) {
        if (userRepository.existsByLogin(authDTO.login())) {
            Map<String, String> exceptionsFields = getFields(authDTO);
            throw new UserAlreadyExistException(exceptionsFields);
        } else {
            return true;
        }
    }

    @Override
    public boolean isExist(AuthDTO authDTO) {
        if (userRepository.existsByLogin(authDTO.login())) {
            return true;
        } else {
            Map<String, String> exceptionsFields = getFields(authDTO);
            throw new UserNotFoundException(exceptionsFields);
        }
    }

    @Override
    @Transactional
    public void save(AuthDTO authDTO) {
        User user = convertToUser(authDTO);
        userRepository.save(user);
    }

    @Override
    public User convertToUser(AuthDTO authDTO){
        return User.builder()
                .login(authDTO.login())
                .password(passwordEncoder.encode(authDTO.password()))
                .build();
    }

    @Override
    public User getUserByLogin(AuthDTO authDTO) {
        return userRepository.findByLogin(authDTO.login()).orElseThrow(() -> {
            Map<String, String> exceptionsFields = getFields(authDTO);
            return new UserNotFoundException(exceptionsFields);
        });
    }

    public Map<String, String> getFields(AuthDTO authDTO){
        return Map.of(
                "login", authDTO.login(),
                "password", authDTO.password()
        );
    }


    public boolean userDataCorrect(AuthDTO authDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage
                    ));
            Map<String, String> fields = getFields(authDTO);
            throw  new IncorrectUserDataException(errors, fields);
        } else {
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow();
    }
}
