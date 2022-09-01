package com.uniquecare.services;

import com.uniquecare.models.User;
import com.uniquecare.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.BDDMockito.given;
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
        underTest.saveUser(user);
        given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.of(user));
        underTest.getByUsername(user.getUsername());
        verify(userRepository).findByUsername("Mesquita");
    }

    @Test
    void deleteUserById() {
        underTest.deleteUserById(1L);
        verify(userRepository).deleteById(1L);
    }
}