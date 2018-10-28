package com.study.yaroslavambrozyak.oauthservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class UserActivationToken {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime expiryDate;
    @OneToOne(mappedBy = "userActivationToken")
    public UserAccount userAccount;


}
