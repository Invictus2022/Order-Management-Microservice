package com.invictus.microservices.product_service.service;

import com.invictus.microservices.product_service.dto.ProductRequest;
import com.invictus.microservices.product_service.dto.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductServiceImpl {
    ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest);

    ResponseEntity<List<ProductResponse>> getAllProducts();
}
