package com.study.yaroslavambrozyak.inventoryservice.exception;

public class InventoryItemNotFoundException extends RuntimeException {

    public InventoryItemNotFoundException() {
        super();
    }

    public InventoryItemNotFoundException(String message) {
        super(message);
    }

    public InventoryItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryItemNotFoundException(Throwable cause) {
        super(cause);
    }
}
