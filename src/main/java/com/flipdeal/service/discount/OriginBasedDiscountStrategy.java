package com.flipdeal.service.discount;

import com.flipdeal.service.model.Discount;
import com.flipdeal.service.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class OriginBasedDiscountStrategy implements DiscountStrategy {

    private static final BigDecimal DISCOUNT = new BigDecimal("0.07");

    @Override
    public String getPromotionSet() {
        return "promotionSetA";
    }

    @Override
    public Optional<Discount> applyDiscount(Product product) {
        if ("Africa".equalsIgnoreCase(product.getOrigin())) {
            return Optional.ofNullable(Discount.builder().amount(product.getPrice().multiply(DISCOUNT))
                    .description("origin based discount applied").build());
        }
        return Optional.empty();
    }
}
