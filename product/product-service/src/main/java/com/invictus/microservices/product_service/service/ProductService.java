package com.invictus.microservices.product_service.service;


import com.invictus.microservices.product_service.dto.ProductRequest;
import com.invictus.microservices.product_service.dto.ProductResponse;
import com.invictus.microservices.product_service.model.Product;
import com.invictus.microservices.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor(remove boilerplate constructor)
public class ProductService implements ProductServiceImpl {

    @Autowired
    public final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest){
        Product products = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        repository.save(products);
        ProductResponse productResponse = new ProductResponse(products.getId(),
                products.getName(),
                products.getDescription(),
                products.getPrice());
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> productResponses = repository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(productResponses,HttpStatus.OK);
    }
}
