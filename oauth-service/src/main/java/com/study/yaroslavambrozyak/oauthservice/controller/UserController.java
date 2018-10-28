package com.study.yaroslavambrozyak.oauthservice.controller;

import com.study.yaroslavambrozyak.oauthservice.dto.UserDTO;
import com.study.yaroslavambrozyak.oauthservice.dto.UserRegistrationDTO;
import com.study.yaroslavambrozyak.oauthservice.security.Role;
import com.study.yaroslavambrozyak.oauthservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/")
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping("/")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO){
        userAccountService.registerUser(userRegistrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDTO userDTO){
        userAccountService.updateUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteUser(){
        userAccountService.deleteUser();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateUserAccount(@RequestParam("token") String token){
        userAccountService.activateUserAccount(token);
        return null;
    }

}
