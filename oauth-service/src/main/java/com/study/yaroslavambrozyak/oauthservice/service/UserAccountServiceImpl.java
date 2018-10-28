package com.study.yaroslavambrozyak.oauthservice.service;

import com.study.yaroslavambrozyak.oauthservice.dto.UserDTO;
import com.study.yaroslavambrozyak.oauthservice.dto.UserRegistrationDTO;
import com.study.yaroslavambrozyak.oauthservice.entity.UserAccount;
import com.study.yaroslavambrozyak.oauthservice.entity.UserActivationToken;
import com.study.yaroslavambrozyak.oauthservice.repository.UserAccountRepository;
import com.study.yaroslavambrozyak.oauthservice.security.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserAccountServiceImpl implements UserAccountService, UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserNotificationService userNotificationService;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder
            , ModelMapper modelMapper, UserNotificationService userNotificationService) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userNotificationService = userNotificationService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = findUserByEmail(email);
        return new User(userAccount.getEmail(),userAccount.getPassword(),userAccount.getActivated(),true,
                true,true,
                AuthorityUtils.createAuthorityList(userAccount.getRole().toString()));
    }

    @Override
    @Transactional
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        boolean userExists = userAccountRepository.existsByEmail(userRegistrationDTO.getEmail());
        if(userExists)
            throw new RuntimeException();
        UserAccount user = modelMapper.map(userRegistrationDTO, UserAccount.class);
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setRole(Role.CUSTOMER);
        UserAccount savedUser = userAccountRepository.save(user);
        userNotificationService.newUserNotification(savedUser);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('CUSTOMER')")
    public void updateUser(UserDTO userDTO) {
        UserAccount currentUser = getCurrentUser();
        BeanUtils.copyProperties(userDTO,currentUser);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('CUSTOMER')")
    public void deleteUser() {
        userAccountRepository.delete(getCurrentUser());
    }

    @Override
    @Transactional
    public void activateUserAccount(String token) {
        userAccountRepository.findByUserActivationToken_Token(token).ifPresent(userAccount->{
            UserActivationToken activationToken = userAccount.getUserActivationToken();
            if(activationToken.getExpiryDate().isBefore(LocalDateTime.now()))
                throw new RuntimeException();
            if(!activationToken.getToken().equals(token))
                throw new RuntimeException();
            userAccount.setActivated(true);
        });
    }

    private UserAccount getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findUserByEmail(user.getUsername());
    }

    private UserAccount findUserByEmail(String email){
        return userAccountRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }
}
