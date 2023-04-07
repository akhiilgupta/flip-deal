package com.flipdeal.service.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AppConfig {

    private final @Value("${api.default.connect.timeout}") Integer apiDefaultConnectTimeout;

    private final @Value("${api.default.read.timeout}") Integer apiDefaultReadTimeout;

    private ClientHttpRequestFactory customRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setConnectTimeout(apiDefaultConnectTimeout);
        httpComponentsClientHttpRequestFactory.setReadTimeout(apiDefaultReadTimeout);
        return httpComponentsClientHttpRequestFactory;
    }

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(customRequestFactory());
        return restTemplate;
    }
}
