package com.study.yaroslavambrozyak.inventoryservice.service;

import com.study.yaroslavambrozyak.inventoryservice.entity.InventoryItem;
import com.study.yaroslavambrozyak.inventoryservice.exception.InventoryItemNotFoundException;
import com.study.yaroslavambrozyak.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryNotificationService inventoryNotificationService;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryNotificationService inventoryNotificationService) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryNotificationService = inventoryNotificationService;
    }

    @Override
    public InventoryItem findInventoryItemByProductCode(String productCode) {
        return inventoryRepository.findByItemCode(productCode).orElseThrow(() -> new InventoryItemNotFoundException(""));
    }

    @Override
    public void addInventoryItem(InventoryItem inventoryItem) {
        inventoryRepository.save(inventoryItem);
    }

    @Override
    public void updateInventoryItem(InventoryItem inventoryItem) {
        InventoryItem item = findInventoryItemByProductCode(inventoryItem.getItemCode());
        item.setQuantity(inventoryItem.getQuantity());
        inventoryRepository.save(item);
        if (inventoryItem.getQuantity().equals(0)) {
            inventoryNotificationService.sendInventoryMessage(inventoryItem.getItemCode(), false);
        } else if (item.getQuantity().equals(0) && inventoryItem.getQuantity() > 0) {
            inventoryNotificationService.sendInventoryMessage(inventoryItem.getItemCode(), true);
        }
    }
}
