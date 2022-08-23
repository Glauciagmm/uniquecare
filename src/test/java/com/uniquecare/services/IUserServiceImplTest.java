package com.uniquecare.services;

import com.uniquecare.models.User;
import com.uniquecare.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IUserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private IUserServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new IUserServiceImpl(userRepository);
    }

    @Test
    void getUsers() {
        underTest.getUsers();
        verify(userRepository).findAll();
    }

    @Test
    void setFacilityRole() {
    }

    @Test
    void getUserById() {
        User user = new User(
                "Glau",
                "Glaucia",
                "Mesquita",
                "glaucia",
                "Glaucia@gmail.com",
                "123456789",
                "Barcelona",
                "123789456"
        );
        underTest.updateUser(user);
        underTest.getUserById(1L);
        verify(userRepository).findById(1L);
    }

    @Test
    void getUserByUsername() {
        underTest.getUser("username");
        verify(userRepository).findByUsername("username");
    }

    @Test
    void getContractByUserId() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserById() {
        underTest.deleteUserById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void getUser() {
    }

    @Test
    void getContractByAssistantId() {
    }

    @Test
    void getByUsername() {
    }

    @Test
    void existsByUsername() {
    }

    @Test
    void existsByEmail() {
    }
}