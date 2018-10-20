package com.study.yaroslavambrozyak.inventoryservice.controller;

import com.study.yaroslavambrozyak.inventoryservice.dto.ExceptionMessageDTO;
import com.study.yaroslavambrozyak.inventoryservice.exception.InventoryItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(InventoryItemNotFoundException.class)
    public ResponseEntity<ExceptionMessageDTO> inventoryItemNotFound(InventoryItemNotFoundException ex) {
        log.error("Item not found exc " + ex.getMessage());
        ExceptionMessageDTO exceptionMessageDTO = convertToExceptionMessageDTO(ex.getMessage());
        return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
    }

    private ExceptionMessageDTO convertToExceptionMessageDTO(String exceptionMessage) {
        ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO();
        exceptionMessageDTO.setMessage(exceptionMessage);
        return exceptionMessageDTO;
    }
}
