package com.flipdeal.service.dao;

import com.flipdeal.service.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDao {

    private final @Value("${product.api.url}") String productApiUrl;

    private final RestTemplate restTemplate;

    public List<Product> getProducts() {
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
                productApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }
        );
        return responseEntity.getBody();
    }
}


