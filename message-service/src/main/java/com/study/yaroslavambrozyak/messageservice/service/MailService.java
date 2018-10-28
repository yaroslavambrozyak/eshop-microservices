package com.study.yaroslavambrozyak.messageservice.service;

import com.study.yaroslavambrozyak.messageservice.dto.UserNotificationDTO;

public interface MailService {

    void sendActivationEmail(UserNotificationDTO userNotificationDTO);

}
