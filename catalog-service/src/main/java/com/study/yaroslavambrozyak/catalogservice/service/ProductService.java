package com.study.yaroslavambrozyak.catalogservice.service;

import com.study.yaroslavambrozyak.catalogservice.entity.Product;

import java.util.UUID;

public interface ProductService {

    Product findByUUID(UUID id);

}
