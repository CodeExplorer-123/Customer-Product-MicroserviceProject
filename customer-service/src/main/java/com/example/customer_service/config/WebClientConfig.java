package com.example.customer_service.config;

import com.example.customer_service.client.ProductClient;
import com.example.customer_service.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


    @Bean
    public WebClient productWebClient() {
        return WebClient.builder()
                .baseUrl("http://product-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public ProductClient employeeClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(productWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(ProductClient.class);
    }
}
