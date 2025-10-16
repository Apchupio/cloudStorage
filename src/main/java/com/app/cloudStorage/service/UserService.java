package com.app.cloudStorage.service;

import com.app.cloudStorage.model.DTO.AuthDTO;
import com.app.cloudStorage.model.entity.User;

public interface UserService {

    boolean isExist(AuthDTO authDTO);

    boolean save(User user);

    boolean userAlreadyExist(AuthDTO authDTO);

    User convertToUser(AuthDTO authDTO);

    User getUserByLogin(AuthDTO authDTO);
}
