package com.study.yaroslavambrozyak.catalogservice.client;

import com.study.yaroslavambrozyak.catalogservice.dto.inventoryservicedto.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

    @GetMapping("/api/inventory/{itemCode}")
    ResponseEntity<InventoryDTO> findInventoryByItemCode(@PathVariable("itemCode")String itemCode);

}
