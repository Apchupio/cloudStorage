package com.app.cloudStorage.unitTest;

import com.app.cloudStorage.exception.customAuthExceptions.UserNotFoundException;
import com.app.cloudStorage.model.dto.auth.AuthDTO;
import com.app.cloudStorage.model.entity.User;
import com.app.cloudStorage.repository.UserRepository;
import com.app.cloudStorage.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUSer_WhenUserExist() {

    }

    @Test
    public void testConvertToUser_whenAuthDtoNotNull() {
        AuthDTO authDTO = new AuthDTO("login", "password", "password");

        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(authDTO.password())).thenReturn(encodedPassword);
        User user = userService.convertToUser(authDTO);

        assertEquals(authDTO.login(), user.getLogin());
        assertEquals(encodedPassword, user.getPassword());
    }

    @Test
    public void testUserIsExist_shouldReturnTrue() {
        AuthDTO authDTO = new AuthDTO("login", "password", "password");

        when(userRepository.existsByLogin(authDTO.login())).thenReturn(true);
        boolean result = userService.isExist(authDTO);
        assertTrue(result);
        verify(userRepository).existsByLogin(authDTO.login());
    }

    @Test
    public void testUserIsExist_shouldReturnException() {
        AuthDTO authDTO = new AuthDTO("login", "password", "password");
        when(userRepository.existsByLogin(authDTO.login())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> {
            userService.isExist(authDTO);
        });
    }


}
