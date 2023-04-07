package com.flipdeal.service.dao;

import com.flipdeal.service.model.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExchangeRateDao {

    private final RestTemplate restTemplate;

    private final @Value("${exchange-rate.api.url}") String exchangeRateApiUrl;

    public ExchangeRateResponse getExchangeRates() {
        ExchangeRateResponse response = restTemplate.getForObject(exchangeRateApiUrl, ExchangeRateResponse.class);
        if (response != null && response.getRates() != null) {
            return response;
        }
        throw new RuntimeException("Failed to fetch exchange rates from the API");
    }
}

