package com.study.yaroslavambrozyak.oauthservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class UserAccount {

    @Id
    @GeneratedValue
    private UUID id;
    private String email;
    private String userName;
    private String password;

}
