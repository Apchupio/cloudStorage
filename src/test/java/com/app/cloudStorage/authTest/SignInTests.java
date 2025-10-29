package com.app.cloudStorage.authTest;

import com.app.cloudStorage.model.dto.auth.AuthDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SignInTests {

    @Autowired
    private MockMvc mockMvc;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        session = new MockHttpSession();
    }

    @Test
    public void testLogin_Success() throws Exception {
        AuthDTO authDTO = new AuthDTO("testUser", "password", "password");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDTO);

        // Выполняем вход в систему и сохраняем сессию
        MockHttpSession session = (MockHttpSession) mockMvc.perform(post("/api/cloud-storage/v1/sign-in/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getRequest()
                .getSession();

        // Проверяем наличие активной сессии
        mockMvc.perform(get("/api/cloud-storage/v1/sign-in/check-session")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Session exists")));
    }

    @Test
    public void testLogin_Failure() throws Exception {
        AuthDTO authDTO = new AuthDTO("wrongUser", "password", "password");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDTO);

        mockMvc.perform(post("/api/cloud-storage/v1/sign-in/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        // Проверим, что сессия все еще не активна
        mockMvc.perform(get("/auth/check-session")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().string("No active session"));
    }


    @Test
    public void signInUser_WhenUserExist_ShouldBeAuthority() throws Exception {
        AuthDTO authDTO = new AuthDTO("testUser", "password", "password");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDTO);

        mockMvc.perform(post("/api/cloud-storage/v1/sign-in/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

    }

    @Test
    public void signInUser_WhenUserNotExist_ShouldBeAuthority() throws Exception {
        AuthDTO authDTO = new AuthDTO("testUser1", "password", "password");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDTO);

        mockMvc.perform(post("/api/cloud-storage/v1/sign-in/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void signInUser_WhenUserPasswordNotMatch_ShouldBeBadRequest() throws Exception {
        AuthDTO authDTO = new AuthDTO("testUser", "passwordddd", "passwordddd");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDTO);

        mockMvc.perform(post("/api/cloud-storage/v1/sign-in/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void signInUser_WhenUserMatchPasswordNotMatch_ShouldBeBadRequest() throws Exception {
        AuthDTO authDTO = new AuthDTO("testUser", "passwordddd", "password");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authDTO);

        mockMvc.perform(post("/api/cloud-storage/v1/sign-in/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
