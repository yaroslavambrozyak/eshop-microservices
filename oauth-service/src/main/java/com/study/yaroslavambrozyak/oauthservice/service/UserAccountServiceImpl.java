package com.study.yaroslavambrozyak.oauthservice.service;

import com.study.yaroslavambrozyak.oauthservice.entity.UserAccount;
import com.study.yaroslavambrozyak.oauthservice.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAccountServiceImpl implements UserAccountService, UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        return new User(userAccount.getEmail(),userAccount.getPassword(), Collections.emptyList());
    }
}
