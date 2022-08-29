package com.uniquecare.controllers;


import com.uniquecare.models.User;
import com.uniquecare.repositories.UserRepository;
import com.uniquecare.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="**")
public class UserController {

    private final IUserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserController(IUserService userService, UserRepository userRepository, PasswordEncoder encoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    /**Lista todos los usuaruios de la base de datos - works! */

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    /**Encuentra un usuario por su ID - works! */
    /*@PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")
    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }*/

    @PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")
    @GetMapping("/currentuser")
    public Optional<User> getUser(Authentication authentication) {
        return Optional.ofNullable(userRepository.getByUsername(authentication.getName()));
    }

    /**Edita un usuario sin editar la contraseña - works!*/
    @PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")
    @PutMapping("/user/edit/{id}")
    public User updateUser (@RequestBody User profileRequest) {
        User user = userRepository.findById(profileRequest.getId()).orElseThrow(RuntimeException::new);
        user.setName(profileRequest.getName());
        user.setSurname(profileRequest.getSurname());
        user.setEmail(profileRequest.getEmail());
        user.setUsername(profileRequest.getUsername());
        user.setCity(profileRequest.getCity());
        user.setPhone(profileRequest.getPhone());
        user.setPhoto(profileRequest.getPhoto());
        return userService.updateUser(user);
    }

    /**Borra un user de la base de datos - works! */
    @PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }


}
