package com.invictus.microservices.order_service.service;

import com.invictus.microservices.order_service.client.InventoryClient;
import com.invictus.microservices.order_service.dto.OrderRequest;
import com.invictus.microservices.order_service.model.Order;
import com.invictus.microservices.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class OrderService implements OrderServiceImpl {

    @Autowired
    private final OrderRepository repository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository repository, InventoryClient inventoryClient) {
        this.repository = repository;
        this.inventoryClient = inventoryClient;
    }

    @Override
    public ResponseEntity<OrderRequest> placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            repository.save(order);
            return new ResponseEntity<>(orderRequest, HttpStatus.CREATED);
        }else {
            throw new RuntimeException("Product with SkuCode " +orderRequest.skuCode()+" is not in the stock");
        }
    }
}
