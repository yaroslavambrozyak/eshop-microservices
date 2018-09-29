package com.study.yaroslavambrozyak.catalogservice.controller;

import com.study.yaroslavambrozyak.catalogservice.entity.Product;
import com.study.yaroslavambrozyak.catalogservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product productByCode(@PathVariable UUID id){
        return productService.findByUUID(id);
    }
}
