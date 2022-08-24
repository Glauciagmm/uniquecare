package com.uniquecare.controllers;

import com.uniquecare.models.User;
import com.uniquecare.repositories.UserRepository;
import com.uniquecare.services.IUserService;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(roles={"ADMIN","USER"})
    void it_show_list_of_coders_if_auth() throws Exception {
    this.mockMvc
        .perform(get("/api/user"))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    void getUsers() {
        List<User> coderList = listOfUsersSample();

        when(userService.getUsers()).thenReturn(userList);

        this.mockMvc
                .perform(get("/api/user").with(user("admin").password("pass").roles("USER","ADMIN")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalTo("John Doe")));

        verify(userService).getUsers();
    }

    @Test
    void findUserById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    private UserRequest userRequestSample (){
        return new UserRequest (
                "Glaucia",
                "Mesquita",
                "glau",
                "glau@test.com",
                "123456789",
                "12345678",

        )
    }
}