package com.uniquecare.services;

import com.uniquecare.models.Contract;
import com.uniquecare.models.ERole;
import com.uniquecare.models.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getUsers();
    Collection<User> setFacilityRole(ERole role);
    Optional<User> findByUsername(String username);
    /** works*/
    void deleteUserById(Long id);
    User saveUser(User user);
    /** works*/
    User updateUser(User user);
    User getUserById(Long userId);
    Optional<User> getUserByUsername(String username);
    List<Contract> getContractByUserId(Long userId);
    Optional<User> getUser(String username);
    /** works*/
    List<Contract> getContractByAssistantId(Long assistantId);
    User getByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
