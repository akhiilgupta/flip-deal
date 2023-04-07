package com.flipdeal.service.discount;

import com.flipdeal.service.model.Discount;
import com.flipdeal.service.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

public interface DiscountStrategy {

    String getPromotionSet();

    Optional<Discount> applyDiscount(Product product);

    @Service
    @Qualifier("defaultDiscountStrategy")
    class DefaultDiscountStrategy implements DiscountStrategy {

        private static final BigDecimal MIN_DISCOUNT_PRICE = BigDecimal.valueOf(1000);

        @Override
        public String getPromotionSet() {
            return "default";
        }

        @Override
        public Optional<Discount> applyDiscount(Product product) {
            if (product.getPrice().compareTo(MIN_DISCOUNT_PRICE) >= 0) {
                BigDecimal discountedAmount = product.getPrice().multiply(BigDecimal.valueOf(0.02));
                return Optional.ofNullable(Discount.builder().amount(discountedAmount).description("default discount applied").build());
            }
            return Optional.empty();
        }
    }
}
