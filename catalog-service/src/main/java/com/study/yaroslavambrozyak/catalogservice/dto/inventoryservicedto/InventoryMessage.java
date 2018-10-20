package com.study.yaroslavambrozyak.catalogservice.dto.inventoryservicedto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryMessage {

    private String itemCode;
    private boolean available;

}
