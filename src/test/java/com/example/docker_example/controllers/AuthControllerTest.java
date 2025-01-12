package com.example.docker_example.controllers;

import com.example.docker_example.models.ERole;
import com.example.docker_example.models.Role;
import com.example.docker_example.payload.request.SignupRequest;
import com.example.docker_example.repository.RoleRepository;
import com.example.docker_example.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @Test
    public void testRegisterUser_Success() throws Exception {
        // Mock behavior for userRepository
        Mockito.when(userRepository.existsByUsername("testuser")).thenReturn(false);
        Mockito.when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        Mockito.when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(new Role(ERole.ROLE_USER)));
        Mockito.when(encoder.encode("password123")).thenReturn("encodedPassword");

        // Create the SignupRequest object
        SignupRequest signupRequestObject = new SignupRequest();
        signupRequestObject.setUsername("testuser");
        signupRequestObject.setEmail("test@example.com");
        signupRequestObject.setPassword("password123");
        signupRequestObject.setRole(new HashSet<>(List.of("user")));

        // Use ObjectMapper to convert the SignupRequest object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String signupRequestJson = objectMapper.writeValueAsString(signupRequestObject);

        String response = mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signupRequestJson))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Response: " + response);
    }

    @Test
    public void testRegisterUser_UsernameExists() throws Exception {
        // Mock behavior for userRepository
        Mockito.when(userRepository.existsByUsername("testuser")).thenReturn(true);

        SignupRequest signupRequestObject = new SignupRequest();
        signupRequestObject.setUsername("testuser");
        signupRequestObject.setEmail("test@example.com");
        signupRequestObject.setPassword("password123");
        signupRequestObject.setRole(new HashSet<>(List.of("user")));

        // Use ObjectMapper to convert the SignupRequest object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String signupRequestJson = objectMapper.writeValueAsString(signupRequestObject);

        String response = mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signupRequestJson))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Response: " + response);
    }
}