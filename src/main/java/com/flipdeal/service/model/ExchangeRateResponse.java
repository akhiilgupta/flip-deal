package com.flipdeal.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse {

    private String base;
    private Map<String, BigDecimal> rates;

    public BigDecimal convertCurrency(BigDecimal value, Currency sourceCurrency) {
        BigDecimal rate = rates.get(sourceCurrency.getCurrencyCode());
        return value.divide(rate, 2 , RoundingMode.HALF_UP);
    }
}
