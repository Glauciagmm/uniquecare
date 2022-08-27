package com.uniquecare.services;

import com.uniquecare.Exceptions.ContractException;
import com.uniquecare.models.Contract;
import com.uniquecare.models.ERole;
import com.uniquecare.models.Facility;
import com.uniquecare.models.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getUsers();
    Collection<User> setFacilityRole(ERole role);
    void deleteUserById(Long id);
    User saveUser(User user);
    User updateUser(User user);
    User getUserById(Long Id);
    //List<Contract> getContractByClientId(Long clientId);
    User getByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    //List<Contract> getContractByUser (Contract state, User assistant) throws ContractException;
}
