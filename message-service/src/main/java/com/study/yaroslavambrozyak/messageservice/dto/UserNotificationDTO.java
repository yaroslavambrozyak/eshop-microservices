package com.study.yaroslavambrozyak.messageservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotificationDTO {

    private String id;
    private String email;
    private String token;

}
