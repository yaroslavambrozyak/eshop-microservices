package com.study.yaroslavambrozyak.inventoryservice.service;

import com.study.yaroslavambrozyak.inventoryservice.dto.InventoryMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryNotificationServiceImpl implements InventoryNotificationService {

    private final AmqpTemplate amqpTemplate;
    private String queueExchange = "inventory";
    private String queueRouteKey = "inventory-key";

    @Autowired
    public InventoryNotificationServiceImpl(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void sendInventoryMessage(String itemCode, boolean isAvailable) {
        InventoryMessage message = new InventoryMessage(itemCode, isAvailable);
        amqpTemplate.convertAndSend(queueExchange,queueRouteKey,message);
    }

}
