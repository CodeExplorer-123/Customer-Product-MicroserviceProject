package com.example.customer_service.client;

import com.example.customer_service.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange
public interface ProductClient {


    @PostExchange("products/add")
    public ResponseEntity<Product> createProduct(@RequestBody Product product);

    @GetExchange("products/all")
    public ResponseEntity<List<Product>> getAllProduct();

    @GetExchange("products/id/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id);

    @PutExchange("products/product")
    public ResponseEntity<String> updateProduct(@RequestBody Product product);

    @DeleteExchange("/products/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id);
}
