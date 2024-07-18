package com.example.customer_service.service;

import com.example.customer_service.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductServiceProxy {

    @Autowired
    private WebClient webClient;

//    @Autowired
//    private RestTemplate restTemplate;

//    public List<Product> getAllProduct(){
//        ResponseEntity<Product[]> responseOfGetAllProduct = restTemplate.getForEntity("http://localhost:8081/products/all", Product[].class);
//        return Arrays.asList(Objects.requireNonNull(responseOfGetAllProduct.getBody()));
//    }

//    public List<Product> getAllProduct() {
//        ResponseEntity<List<Product>> response = restTemplate.exchange(
//                "http://localhost:8081/products/all",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Product>>() {}
//        );
//        return response.getBody();
//    }


//    public Product getProduct(Long id){
//        ResponseEntity<Product> responseOfGetProduct = restTemplate.getForEntity("http://localhost:8081/products/id/{id}", Product.class, id);
//        return responseOfGetProduct.getBody();
//    }

    public Product getProduct(Long id){
        Product product = webClient.get()
                .uri("http://localhost:8081/products/id/{id}",id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
        return product;
    }

    public List<Product> getAllProduct(){
        List<Product> products = webClient.get()
                .uri("http://localhost:8081/products/all")
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();
        return products;
    }

    public String updateProduct(Product product){

          String msg = webClient.put()
                .uri("http://localhost:8081/products/product")
                .body(Mono.just(product), Product.class)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Blocking call to get the result from the Mono
        return msg;
    }

    public Product addProduct(Product product){
        Product product1 = webClient.post()
                .uri("http://localhost:8081/products/add")
                .body(Mono.just(product),Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
        return product1;
    }

    public String deleteProduct(Long id){

        String msg = webClient.delete()
                .uri("http://localhost:8081/products/product/delete/{id}",id)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Blocking call to get the result from the Mono
        return msg;
    }
}
