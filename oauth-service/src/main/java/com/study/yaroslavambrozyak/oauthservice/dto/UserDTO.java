package com.study.yaroslavambrozyak.oauthservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDTO {

    @NotNull
    @NotEmpty
    private String name;

}
