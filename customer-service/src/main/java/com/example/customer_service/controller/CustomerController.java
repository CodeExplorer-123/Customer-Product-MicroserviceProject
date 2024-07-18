package com.example.customer_service.controller;

import com.example.customer_service.entity.Customer;
import com.example.customer_service.entity.Product;
import com.example.customer_service.service.CustomerService;
import com.example.customer_service.service.ProductServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductServiceProxy productServiceProxy;

    @PostMapping("/add")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer c = customerService.createCustomer(customer);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> c = customerService.getAllCustomer();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }


    //operation on product-service

    @GetMapping(value="/products/all" , produces = "application/json")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> listOfProduct = productServiceProxy.getAllProduct();
        return new ResponseEntity<>(listOfProduct,HttpStatus.OK);
    }

    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productServiceProxy.getProduct(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/products/update")
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        String msg = productServiceProxy.updateProduct(product);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }

    @PostMapping("products/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product product1 = productServiceProxy.addProduct(product);
        return  new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping("/products/delete/id/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        String msg = productServiceProxy.deleteProduct(id);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
