package com.study.yaroslavambrozyak.catalogservice.repository;

import com.study.yaroslavambrozyak.catalogservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product,UUID> {

}
