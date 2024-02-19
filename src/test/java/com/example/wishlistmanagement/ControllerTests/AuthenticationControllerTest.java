package com.example.wishlistmanagement.ControllerTests;

import com.example.wishlistmanagement.controller.AuthenticationController;
import com.example.wishlistmanagement.dao.LoginUser;
import com.example.wishlistmanagement.dao.RegisterUser;
import com.example.wishlistmanagement.model.AuthenticationResponse;
import com.example.wishlistmanagement.model.User;
import com.example.wishlistmanagement.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    public void testRegister() throws Exception {
        RegisterUser registerUser = new RegisterUser();
        registerUser.setName("Test User");
        registerUser.setUsername("testuser");
        registerUser.setEmail("testuser@example.com");
        registerUser.setPassword("password");

        AuthenticationResponse authResponse = new AuthenticationResponse("","User registered successfully");

        when(authenticationService.register(any(User.class))).thenReturn(authResponse);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test User\",\"username\":\"testuser\",\"email\":\"testuser@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User registered successfully"));

        verify(authenticationService, times(1)).register(any(User.class));
    }

    @Test
    public void testLogin() throws Exception {
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername("testuser");
        loginUser.setPassword("password");

        AuthenticationResponse authResponse = new AuthenticationResponse("","User authenticated successfully");

        when(authenticationService.authenticate(loginUser.getUsername(), loginUser.getPassword())).thenReturn(authResponse);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User authenticated successfully"));

        verify(authenticationService, times(1)).authenticate(loginUser.getUsername(), loginUser.getPassword());
    }
}

