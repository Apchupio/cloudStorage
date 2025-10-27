package com.app.cloudStorage.service;

import com.app.cloudStorage.model.DTO.AuthDTO;
import com.app.cloudStorage.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean isExist(AuthDTO authDTO);

    void save(AuthDTO authDTO);

    boolean userAlreadyExist(AuthDTO authDTO);

    User convertToUser(AuthDTO authDTO);

    User getUserByLogin(AuthDTO authDTO);
}
