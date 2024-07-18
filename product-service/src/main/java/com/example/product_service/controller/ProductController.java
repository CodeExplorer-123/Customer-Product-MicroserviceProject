package com.example.product_service.controller;

import com.example.product_service.entity.Product;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product p = productService.createproduct(product);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> p = productService.getAllProduct();
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
            Product product = productService.getProduct(id);
            return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/product")
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        String msg = productService.updateProduct(product);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        String msg = productService.deleteProduct(id);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
