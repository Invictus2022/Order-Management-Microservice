package com.invictus.microservices.order_service.service;

import com.invictus.microservices.order_service.dto.OrderRequest;
import org.springframework.http.ResponseEntity;

public interface OrderServiceImpl {
    ResponseEntity<OrderRequest> placeOrder(OrderRequest orderRequest);
}
