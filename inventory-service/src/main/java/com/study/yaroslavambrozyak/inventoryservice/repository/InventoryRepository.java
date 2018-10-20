package com.study.yaroslavambrozyak.inventoryservice.repository;

import com.study.yaroslavambrozyak.inventoryservice.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<InventoryItem,UUID> {

    Optional<InventoryItem> findByItemCode(String itemCode);

}
