package com.study.yaroslavambrozyak.catalogservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Product {

    private UUID id;
    private String name;
    private String description;

}
