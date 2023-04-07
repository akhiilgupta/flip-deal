package com.flipdeal.service.discount;

import com.flipdeal.service.model.Discount;
import com.flipdeal.service.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class RatingBasedDiscountStrategy implements DiscountStrategy {
    private static final BigDecimal DISCOUNT_2 = new BigDecimal("0.04");
    private static final BigDecimal DISCOUNT_BELOW_2 = new BigDecimal("0.08");

    @Override
    public String getPromotionSet() {
        return "promotionSetA";
    }

    @Override
    public Optional<Discount> applyDiscount(Product product) {
        if (product.getRating() == 2) {
            return Optional.ofNullable(Discount.builder().amount(product.getPrice().multiply(DISCOUNT_2))
                    .description("rating based discount applied").build());
        } else if (product.getRating() < 2) {
            return Optional.ofNullable(Discount.builder().amount(product.getPrice().multiply(DISCOUNT_BELOW_2))
                    .description("rating based discount applied").build());
        }
        return Optional.empty();
    }
}
