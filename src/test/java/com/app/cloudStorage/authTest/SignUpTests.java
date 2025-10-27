package com.app.cloudStorage.authTest;

import com.app.cloudStorage.model.DTO.AuthDTO;
import com.app.cloudStorage.model.entity.User;
import com.app.cloudStorage.repository.UserRepository;
import com.app.cloudStorage.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SignUpTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void signUpUser_WhenValidData_StatusShouldBeCreated() throws Exception {
        AuthDTO authDto = new AuthDTO("testUser2", "password", "password");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/api/cloud-storage/v1/sign-up/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void signUpUser_WhenIncorrectData_StatusShouldBeBadRequest() throws Exception {
        AuthDTO authDto = new AuthDTO("user", "password", "password");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/api/cloud-storage/v1/sign-up/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void signUpUser_WhenUserAlreadyExist_StatusShouldBeConflict() throws Exception {
        AuthDTO authDto = new AuthDTO("testUser", "password", "password");
        User user = userService.convertToUser(authDto);
        userRepository.save(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/api/cloud-storage/v1/sign-up/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    public void signUpUser_WhenUserMatchPasswordNotMatch_ShouldBeBadRequest() throws Exception {
        AuthDTO authDto = new AuthDTO("testUser", "password", "passworddd");
        User user = userService.convertToUser(authDto);
        userRepository.save(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDto);
        mockMvc.perform(post("/api/cloud-storage/v1/sign-up/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
