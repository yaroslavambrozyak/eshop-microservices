package com.study.yaroslavambrozyak.oauthservice.dto;

import com.study.yaroslavambrozyak.oauthservice.validation.annotation.PasswordMatches;
import com.study.yaroslavambrozyak.oauthservice.validation.annotation.ValidEmail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@PasswordMatches
public class UserRegistrationDTO {

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String confirmPassword;

}
