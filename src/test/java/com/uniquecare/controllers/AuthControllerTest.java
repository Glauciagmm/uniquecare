package com.uniquecare.controllers;

import com.uniquecare.models.Role;
import com.uniquecare.models.User;
import com.uniquecare.payload.request.SignupRequest;
import com.uniquecare.repositories.RoleRepository;
import com.uniquecare.repositories.UserRepository;
import com.uniquecare.services.IUserService;
import org.junit.jupiter.api.Test;

import static com.uniquecare.models.ERole.ROLE_USER;
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
import org.mockito.ArgumentCaptor;

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

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(roles={"ADMIN","USER", "FACILITY"})
    void it_show_list_of_user_if_auth() throws Exception {
        this.mockMvc
                .perform(get("/api/user"))
                .andDo(print())
                .andExpect(status().isOk());
   }

   /* @Test
    void authenticateUser() throws Exception{
        this.mockMvc
                .perform(get("/api/signing").with(anonymous()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void registerUser() throws Exception {
        SignupRequest request = userRequestSample();
        User expectedResponse = listOfUsersSample().get(1);
        when(userService.saveUser(ArgumentMatchers.any(User.class)))
                .thenReturn(expectedResponse);
        mockMvc.perform(
                        post("/api/signup")
                                .with(user("admin").password("pass").roles("USER","ADMIN", "FACILITY"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", containsString(""+expectedResponse.getId())))
                .andExpect(jsonPath("$").value("Coder with id " + expectedResponse.getId() + " Created successfully"));

        verify(userService).saveUser(ArgumentMatchers.any(User.class));
    }

    private SignupRequest userRequestSample (){
        return new SignupRequest (
                "Glaucia",
                "Mesquita",
                "glau",
                "glau@test.com",
                "Barcelona",
                "123456789",
                "xxxxxx",
                "12345666"
        );
    }

    private List<User> listOfUsersSample() {
        Role role = new Role(
                1,
                ROLE_USER);
                new HashSet<>(),
                new Date(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis());
        );

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
                role,
                new Timestamp(System.currentTimeMillis()));

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
                role,
                new Timestamp(System.currentTimeMillis()));

        List<User> userList = new ArrayList<>();
        role.addUser(glaucia);
        role.addUser(patricia);
        return userList;
    }*/

    private <T> String asJsonString(T obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}