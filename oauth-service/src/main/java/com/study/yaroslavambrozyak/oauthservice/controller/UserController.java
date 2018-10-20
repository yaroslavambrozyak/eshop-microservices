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

    @Autowired
    private UserAccountService userAccountService;

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
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDTO userDTO){

        return null;
    }

}
