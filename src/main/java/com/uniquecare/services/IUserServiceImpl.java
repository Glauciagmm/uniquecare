package com.uniquecare.services;

import com.uniquecare.Exceptions.ContractException;
import com.uniquecare.models.Contract;
import com.uniquecare.models.ERole;
import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import com.uniquecare.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class IUserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public IUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Collection<User> setFacilityRole(ERole eRole) {
        return setFacilityRole(ERole.valueOf("ROLE_FACILITY"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

   /* @Override
    public Optional<User> getUserByUsername(String username) {
        log.info("Fetching user {}",  username);
        return userRepository.findByUsername(username);
    }*/


    /*@Override
     public List<Contract> getContractByUserId(Long userId) {
         return contractRepository.findAll();
     }*/

  /*  @Override
    public Optional<User> findByUsername(String username) {
        log.info("Fetching user {}",  username);
        return userRepository.findByUsername(username);
    }*/

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /** works*/
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /** works*/
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

//    @Override
//    public Optional<User> getUser(String username) {
//        log.info("Fetching user {}",  username);
//        return userRepository.findByUsername(username);
//    }

   /* @Override
    public List<Contract> getContractByClientId(Long clientId) {
        return userRepository.getContractByClientId(clientId);
    }*/

    /** works*/
    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not Found"));
    }
    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

   /* @Override
    public List<Contract> getContractByUser(Contract state, User assistant) throws ContractException {
        return userRepository.getContractByUser(state, assistant);
    }*/
}
