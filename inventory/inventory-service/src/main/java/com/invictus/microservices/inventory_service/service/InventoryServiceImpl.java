package com.invictus.microservices.inventory_service.service;

import com.invictus.microservices.inventory_service.dto.InventoryRequest;
import org.springframework.http.ResponseEntity;

public interface InventoryServiceImpl {
    boolean isInStock(String skuCode, Integer quantity);
    ResponseEntity<InventoryRequest> postInventory(InventoryRequest inventoryRequest);
}
