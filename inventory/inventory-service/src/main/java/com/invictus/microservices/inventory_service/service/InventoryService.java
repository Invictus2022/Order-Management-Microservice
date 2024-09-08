package com.invictus.microservices.inventory_service.service;


import com.invictus.microservices.inventory_service.dto.InventoryRequest;
import com.invictus.microservices.inventory_service.model.Inventory;
import com.invictus.microservices.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements  InventoryServiceImpl {


    @Autowired
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isInStock(String skuCode, Integer quantity){
        return repository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }


    @Override
    public ResponseEntity<InventoryRequest> postInventory(InventoryRequest inventoryRequest)
    {
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.skuCode());
        inventory.setQuantity(inventoryRequest.quantity());
        repository.save(inventory);
        return new ResponseEntity<>(inventoryRequest, HttpStatus.CREATED);
    }

}
