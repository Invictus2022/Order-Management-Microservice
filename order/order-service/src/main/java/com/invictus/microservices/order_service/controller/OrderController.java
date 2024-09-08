package com.invictus.microservices.order_service.controller;


import com.invictus.microservices.order_service.dto.OrderRequest;
import com.invictus.microservices.order_service.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {


    @Autowired
    private final OrderServiceImpl service;

    public OrderController(OrderServiceImpl service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderRequest> placeOrder(@RequestBody OrderRequest orderRequest){
        return service.placeOrder(orderRequest);
    }


}
