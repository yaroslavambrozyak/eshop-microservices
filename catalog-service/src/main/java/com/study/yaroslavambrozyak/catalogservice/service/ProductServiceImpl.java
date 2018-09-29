package com.study.yaroslavambrozyak.catalogservice.service;

import com.study.yaroslavambrozyak.catalogservice.entity.Product;
import com.study.yaroslavambrozyak.catalogservice.exception.ProductNotFoundException;
import com.study.yaroslavambrozyak.catalogservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Product findByUUID(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }
}
