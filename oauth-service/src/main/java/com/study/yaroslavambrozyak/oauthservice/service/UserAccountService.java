package com.study.yaroslavambrozyak.oauthservice.service;

import com.study.yaroslavambrozyak.oauthservice.dto.UserDTO;
import com.study.yaroslavambrozyak.oauthservice.dto.UserRegistrationDTO;

public interface UserAccountService {

    void registerUser(UserRegistrationDTO userRegistrationDTO);

    void updateUser(UserDTO userDTO);

    void deleteUser();

    void activateUserAccount(String token);
}
