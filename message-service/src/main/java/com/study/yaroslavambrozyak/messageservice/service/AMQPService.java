package com.study.yaroslavambrozyak.messageservice.service;

import com.study.yaroslavambrozyak.messageservice.dto.UserNotificationDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AMQPService {

    @RabbitListener(queues = "user.new-queue")
    public void onNewUserRegister(UserNotificationDTO userNotificationDTO){
        System.out.println("user received");
    }
}
