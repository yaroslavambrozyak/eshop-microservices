package com.study.yaroslavambrozyak.oauthservice.service;

import com.study.yaroslavambrozyak.oauthservice.dto.UserDTO;
import com.study.yaroslavambrozyak.oauthservice.dto.UserRegistrationDTO;
import com.study.yaroslavambrozyak.oauthservice.entity.UserAccount;
import com.study.yaroslavambrozyak.oauthservice.repository.UserAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountServiceImpl implements UserAccountService, UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = findUserByEmail(email);
        return new User(userAccount.getEmail(),userAccount.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
    }

    @Override
    @Transactional
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        boolean userExists = userAccountRepository.existsByEmail(userRegistrationDTO.getEmail());
        if(userExists)
            throw new RuntimeException();
        UserAccount user = modelMapper.map(userRegistrationDTO, UserAccount.class);
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        UserAccount currentUser = getCurrentUser();
        BeanUtils.copyProperties(userDTO,currentUser);
    }

    private UserAccount getCurrentUser(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findUserByEmail(email);
    }

    private UserAccount findUserByEmail(String email){
        return userAccountRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }
}
