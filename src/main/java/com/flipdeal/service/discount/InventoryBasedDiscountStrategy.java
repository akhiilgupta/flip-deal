package com.flipdeal.service.discount;

import com.flipdeal.service.model.Discount;
import com.flipdeal.service.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class InventoryBasedDiscountStrategy implements DiscountStrategy {

    private static final BigDecimal DISCOUNT = new BigDecimal("0.12");
    private static final int MINIMUM_INVENTORY = 20;

    @Override
    public String getPromotionSet() {
        return "promotionSetB";
    }

    @Override
    public Optional<Discount> applyDiscount(Product product) {
        if (product.getInventory() > MINIMUM_INVENTORY) {
            return Optional.ofNullable(Discount.builder().amount(product.getPrice().multiply(DISCOUNT))
                    .description("inventory based discount applied").build());
        }
        return Optional.empty();
    }
}
