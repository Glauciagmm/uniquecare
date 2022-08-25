package com.uniquecare.controllers;

import com.uniquecare.models.Role;
import com.uniquecare.models.User;
import com.uniquecare.payload.request.SignupRequest;
import com.uniquecare.repositories.UserRepository;
import com.uniquecare.services.IUserService;
import org.junit.jupiter.api.Test;

import static com.uniquecare.models.ERole.*;

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

    /*@Autowired
    private MockMvc mockMvc;*/

    @MockBean
    private IUserService userService;

    @MockBean
    private UserRepository userRepository;

  /*  @Test
    @WithMockUser(roles={"ADMIN","USER"})
    void it_show_list_of_coders_if_auth() throws Exception {
    this.mockMvc
        .perform(get("/api/user"))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    void getUsers() throws Exception {
        List<User> userList = listOfUsersSample();

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
                1L,
                "ROLE_USER",
                new HashSet<>(),
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));
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

    }
    private <T> String asJsonString(T obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }*/

}



//@Autowired
//private MockMvc mockMvc;

//@MockBean
// private IUserService iUserService;

    /*@Test
    void getUsers() throws Exception{


        List<User> userList = sampleUser();


        when(iUserService.getUsers()).thenReturn(userList);

        this.mockMvc
                .perform(get("api/user/list").whit(user("admin").password("password").roles("ROLE_Admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                        .andExpect(jsonPath("$[0].name", equalTo("Glaucia")));


        verify(iUserService).getUsers();


    }

    @Test
    void returnsUnauthotrizedIsNotAuthenticated() throws Exception{
        this.mockMvc
                .perform(get("api/user/list").whit(anonymous()))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

    @Test
    void allowsToCreateNewUser() throws Exception {
        SignupRequest request = siginupRequestSample();
        User expectResponse = sampleUser().get(0);
        when(iUserService.saveUser(any(SignupRequest.class))).thenReturn(expectResponse);

        this.mockMvc.perform(
                post("api/auth/singnup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("City"))
                .andExpect(header().string("City", containsString(""+ expectResponse.getId())))
                .andExpect(jsonPath("$").value("User whit id" + "Usuario Creado!!!"));

        verify(iUserService).saveUser(any(SignupRequest.class));

    }

    private SignupRequest siginupRequestSample(){
        User glaucia = new User(
                1L,
                "Glaucia",
                "Mesquita",
                "glau",
                "glau@test.com",
                "Barcelona",
                "123456789",
                "xxxxx",
                "1234566");
        return ;
    }
    private List<User> sampleUser() {
        //Test Status Code 200

        //Test userService.getAll()

        //Test User List Response

        Role role = new Role(
                1,
                ROLE_ADMIN
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
                "1234566";
        //new Timestamp(System.currentTimeMillis());
        );

        User iris = new User(
                1L,
                "Iris",
                "Mesquita",
                "glau",
                "glau@test.com",
                "Barcelona",
                "123456789",
                "xxxxx",
                "1234566";
        //new Timestamp(System.currentTimeMillis());


        );

    }*/