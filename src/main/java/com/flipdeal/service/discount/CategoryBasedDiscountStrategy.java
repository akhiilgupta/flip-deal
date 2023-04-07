package com.flipdeal.service.discount;

import com.flipdeal.service.model.Discount;
import com.flipdeal.service.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Component
public class CategoryBasedDiscountStrategy implements DiscountStrategy {

    private static final BigDecimal FLAT_DISCOUNT = new BigDecimal("100");
    private static final BigDecimal MINIMUM_PRICE = new BigDecimal("500");

    private final HashSet<Product.Category> categories =
            new HashSet<>(Arrays.asList(Product.Category.ELECTRONICS, Product.Category.FURNISHING));

    @Override
    public String getPromotionSet() {
        return "promotionSetA";
    }

    @Override
    public Optional<Discount> applyDiscount(Product product) {
        if (categories.contains(product.getCategory()) && product.getPrice().compareTo(MINIMUM_PRICE) >= 0) {
            return Optional.ofNullable(Discount.builder().amount(FLAT_DISCOUNT)
                    .description("category based discount applied").build());
        }
        return Optional.empty();
    }
}
