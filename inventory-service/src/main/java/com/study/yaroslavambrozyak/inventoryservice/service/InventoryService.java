package com.study.yaroslavambrozyak.inventoryservice.service;

import com.study.yaroslavambrozyak.inventoryservice.entity.InventoryItem;

public interface InventoryService {

    InventoryItem findInventoryItemByProductCode(String productCode);
    void addInventoryItem(InventoryItem inventoryItem);
    void updateInventoryItem(InventoryItem inventoryItem);

}
