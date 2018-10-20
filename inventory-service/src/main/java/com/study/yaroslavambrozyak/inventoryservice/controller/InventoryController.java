package com.study.yaroslavambrozyak.inventoryservice.controller;

import com.study.yaroslavambrozyak.inventoryservice.dto.InventoryDTO;
import com.study.yaroslavambrozyak.inventoryservice.entity.InventoryItem;
import com.study.yaroslavambrozyak.inventoryservice.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public InventoryController(InventoryService inventoryService, ModelMapper modelMapper) {
        this.inventoryService = inventoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{itemCode}")
    public ResponseEntity<InventoryDTO> findInventoryByItemCode(@PathVariable String itemCode) {
        InventoryItem inventoryItem = inventoryService.findInventoryItemByProductCode(itemCode);
        InventoryDTO inventoryDTO = modelMapper.map(inventoryItem, InventoryDTO.class);
        return new ResponseEntity<>(inventoryDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Void> addNewInventoryForItem(@RequestBody InventoryDTO inventoryDTO) {
        InventoryItem inventoryItem = modelMapper.map(inventoryDTO, InventoryItem.class);
        inventoryService.addInventoryItem(inventoryItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateInventoryForItem(@RequestBody InventoryDTO inventoryDTO) {
        InventoryItem inventoryItem = modelMapper.map(inventoryDTO, InventoryItem.class);
        inventoryService.updateInventoryItem(inventoryItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
