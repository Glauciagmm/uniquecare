package com.uniquecare.controllers;

import com.uniquecare.models.ERole;
import com.uniquecare.models.Role;
import com.uniquecare.models.User;
import com.uniquecare.payload.request.LoginRequest;
import com.uniquecare.payload.request.SignupRequest;
import com.uniquecare.repositories.RoleRepository;
import com.uniquecare.repositories.UserRepository;
import com.uniquecare.services.IUserService;
import com.uniquecare.services.RoleService;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;
    @MockBean
    private IUserService userService;

   @Test
    void authenticateUser() throws Exception{
       LoginRequest loginRequest = new LoginRequest("glau", "12345678");
        this.mockMvc
                .perform(post("/api/auth/signing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginRequest)))
                        .andDo(print())
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists());
    }

    @Test
    void itshouldntLoginIncorrectUsernamePassword() throws Exception{

        LoginRequest loginRequest = new LoginRequest("lalala", "123456789");
        this.mockMvc
                .perform(post("/api/auth/signing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginRequest)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void registerUser() throws Exception {
        SignupRequest request = new SignupRequest(
                "John",
                "John",
                "johndoe",
                "john@doe.com",
                "Barcelona",
                "123456789",
                "gdsaksgakj",
                "12345678",
                null);

        Role role = new Role(1, ERole.ROLE_USER);

       User glaucia = new User(
                1L,
                "John",
                "John",
                "johndoe",
                "john@doe.com",
                "Barcelona",
                "123456789",
                "gdsaksgakj",
                "12345678",
                new HashSet<>());

        glaucia.getRoles().add(role);

        when(roleService.findByName(any(ERole.class))).thenReturn(Optional.of(role));
        when(userService.saveUser(any(User.class))).thenReturn(glaucia);

        this.mockMvc.perform(
                        post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void itsShouldntCreateAExistingUsername() throws Exception {
        SignupRequest request = new SignupRequest("Ana", "Lopes", "glau", "ana2@ana.test", "Barcelona","123456789","gdsaksgakj","12345678", null);

        this.mockMvc.perform(
                        post("/api/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void itsShouldntCreateAExistingEmail() throws Exception {
        SignupRequest request = new SignupRequest("Ana", "Lopes", "ana", "ana@ana.test", "Barcelona","123456789","gdsaksgakj","12345678", null);

        this.mockMvc.perform(
                        post("/api/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private SignupRequest userSignupSample (){
        return new SignupRequest (
                "Glaucia",
                "Mesquita",
                "glau",
                "glau@test.com",
                "Barcelona",
                "123456789",
                "xxxxxx",
                "12345666",
                new HashSet<>());
    }

    private List<User> listOfUsersSample() {
        Role role = new Role(1, ERole.ROLE_USER);
        User glaucia = new User(
                1L,
                "Glaucia",
                "Mesquita",
                "glau",
                "glau@test.com",
                "Barcelona",
                "123456789",
                "xxxxx",
                "1234566",
                new HashSet<>());
        glaucia.getRoles().add(role);

        User patricia = new User(
                1L,
                "Patricia",
                "Mesquita",
                "glau",
                "glau@test.com",
                "Barcelona",
                "123456789",
                "xxxxx",
                "1234566",
                new HashSet<>());
        patricia.getRoles().add(role);
        return null;
    }

}