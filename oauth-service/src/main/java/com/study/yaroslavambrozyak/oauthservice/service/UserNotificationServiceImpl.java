package com.study.yaroslavambrozyak.oauthservice.service;

import com.study.yaroslavambrozyak.oauthservice.dto.UserNotificationDTO;
import com.study.yaroslavambrozyak.oauthservice.entity.UserAccount;
import com.study.yaroslavambrozyak.oauthservice.entity.UserActivationToken;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserNotificationServiceImpl implements UserNotificationService {

    private final AmqpTemplate amqpTemplate;
    private final ModelMapper modelMapper;
    private String queueRouteKey = "user-key";
    private String queueExchange = "user";

    @Autowired
    public UserNotificationServiceImpl(AmqpTemplate amqpTemplate, ModelMapper modelMapper) {
        this.amqpTemplate = amqpTemplate;
        this.modelMapper = modelMapper;
    }

    @Override
    public void newUserNotification(UserAccount userAccount) {
        UserActivationToken activationToken = createNewActivationToken(userAccount);
        amqpTemplate.convertAndSend(queueExchange, queueRouteKey, createUserNotificationDTO(activationToken));
    }

    private UserActivationToken createNewActivationToken(UserAccount userAccount){
        UserActivationToken userActivationToken = new UserActivationToken();
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(24);
        userActivationToken.setToken(token);
        userActivationToken.setExpiryDate(expiryDate);
        userAccount.addToken(userActivationToken);
        return userActivationToken;
    }

    private UserNotificationDTO createUserNotificationDTO(UserActivationToken userActivationToken){
        UserNotificationDTO userNotificationDTO = new UserNotificationDTO();
        userNotificationDTO.setId(userActivationToken.getUserAccount().getId().toString());
        userNotificationDTO.setId(userActivationToken.getUserAccount().getEmail());
        userNotificationDTO.setToken(userActivationToken.getToken());
        return userNotificationDTO;
    }
}
