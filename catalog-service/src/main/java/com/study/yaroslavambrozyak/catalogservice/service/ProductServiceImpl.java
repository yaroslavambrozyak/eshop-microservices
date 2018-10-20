package com.study.yaroslavambrozyak.catalogservice.service;

import com.study.yaroslavambrozyak.catalogservice.dto.inventoryservicedto.InventoryMessage;
import com.study.yaroslavambrozyak.catalogservice.entity.Product;
import com.study.yaroslavambrozyak.catalogservice.exception.ProductNotFoundException;
import com.study.yaroslavambrozyak.catalogservice.repository.ProductRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product findByUUID(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    @RabbitListener(queues = "inventory-queue")
    public void inventoryUpdate(InventoryMessage inventoryMessage){
        Product product = findByUUID(UUID.fromString(inventoryMessage.getItemCode()));
        product.setAvailable(inventoryMessage.isAvailable());
    }
}
