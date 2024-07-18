package com.example.product_service.service;

import com.example.product_service.entity.Product;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createproduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("product is not found with given id :"  + id));
    }

    public String updateProduct(Product product){
        Product product1 = productRepository.findById(product.getId())
                .orElseThrow(()->new ProductNotFoundException("product is not found with given id :"  + product.getId()));
        product1.setId(product.getId());
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        productRepository.save(product1);
        return "updated successfully";
    }

    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "deleted successfully";
    }

}
