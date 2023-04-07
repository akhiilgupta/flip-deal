package com.flipdeal.service.discount;

import com.flipdeal.service.model.Discount;
import com.flipdeal.service.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ArrivalStatusDiscountStrategy implements DiscountStrategy {

    private static final BigDecimal DISCOUNT = new BigDecimal("0.07");
    private static final String NEW_ARRIVAL_STATUS = "new";

    @Override
    public String getPromotionSet() {
        return "promotionSetB";
    }

    @Override
    public Optional<Discount> applyDiscount(Product product) {
        if (NEW_ARRIVAL_STATUS.equalsIgnoreCase(product.getArrivalStatus())) {
            return Optional.ofNullable(Discount.builder().amount(product.getPrice()
                    .multiply(DISCOUNT)).description("arrival status based discount applied").build());
        }
        return Optional.empty();
    }
}
