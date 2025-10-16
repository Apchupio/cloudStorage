package com.app.cloudStorage.authTest;

import com.app.cloudStorage.model.DTO.AuthDTO;
import com.app.cloudStorage.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void createUser_ShouldSaveUser_WhenValidData() throws Exception {
        AuthDTO authDto = new AuthDTO("testUser", "password", "password");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDto); // Преобразование объекта в JSON
        mockMvc.perform(post("/api/cloud-storage/sign-up/auth")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(String.valueOf(authDto)))
                .andExpect(status().is3xxRedirection());
        assertTrue(userRepository.existsByLogin("testUser"));
    }
}
