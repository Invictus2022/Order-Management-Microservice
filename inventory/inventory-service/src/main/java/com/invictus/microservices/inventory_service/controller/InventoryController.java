package com.invictus.microservices.inventory_service.controller;


import com.invictus.microservices.inventory_service.dto.InventoryRequest;
import com.invictus.microservices.inventory_service.service.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/inventory")
public class InventoryController {


    @Autowired
    private final InventoryServiceImpl service;

    public InventoryController(InventoryServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity){
        return service.isInStock(skuCode,quantity);
    }

    @PostMapping
    public ResponseEntity<InventoryRequest> postInventory(@RequestBody InventoryRequest inventoryRequest){
        return service.postInventory(inventoryRequest);
    }
}
