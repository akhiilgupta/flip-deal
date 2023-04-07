package com.flipdeal.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipdeal.service.dao.ExchangeRateDao;
import com.flipdeal.service.dao.ProductDao;
import com.flipdeal.service.model.ExchangeRateResponse;
import com.flipdeal.service.model.Product;
import com.flipdeal.service.model.ProductDiscountNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final PromotionService promotionService;

    private final ExchangeRateDao exchangeRateDao;

    private final ProductDao productDao;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void createPromotionSet(String promotionSet) throws IOException {
        List<Product> productList = productDao.getProducts();
        ExchangeRateResponse exchangeRates = exchangeRateDao.getExchangeRates();

        for (Product product : productList) {
            if (!"INR".equals(product.getCurrency().getCurrencyCode())) {
                BigDecimal convertedPrice = exchangeRates.convertCurrency(product.getPrice(), product.getCurrency());
                product.setPrice(convertedPrice);
                product.setCurrency(Currency.getInstance("INR"));
            }
        }
        productList.forEach(p -> promotionService.applyDiscount(p, promotionSet));
        saveProducts(productList);
    }

    private void saveProducts(List<Product> products) throws IOException {
        File outputFile = new File("target/output.json");
        ProductDiscountNode productDiscountNode = ProductDiscountNode.builder().products(products).build();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, productDiscountNode);
        log.info("Output saved to file: {}", outputFile.getAbsolutePath());
    }
}
