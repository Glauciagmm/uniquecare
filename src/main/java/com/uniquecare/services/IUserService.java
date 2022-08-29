package com.uniquecare.services;

import com.uniquecare.models.ERole;
import com.uniquecare.models.User;
import java.util.Collection;
import java.util.List;


public interface IUserService {
    List<User> getUsers();
    Collection<User> setFacilityRole(ERole role);
    void deleteUserById(Long id);
    User saveUser(User user);
    User updateUser(User user);
    User getUserById(Long Id);
    User getByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
