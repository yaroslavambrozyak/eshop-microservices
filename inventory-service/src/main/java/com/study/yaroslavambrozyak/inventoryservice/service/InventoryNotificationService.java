package com.study.yaroslavambrozyak.inventoryservice.service;

public interface InventoryNotificationService {

    void sendInventoryMessage(String itemCode, boolean isAvailable);

}
