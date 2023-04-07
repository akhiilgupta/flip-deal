package com.flipdeal.service.promotion;

import com.flipdeal.service.discount.DiscountStrategy;
import com.flipdeal.service.model.Discount;
import com.flipdeal.service.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionSetA implements PromotionSet {

    private final List<DiscountStrategy> discountStrategies;

    public PromotionSetA(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies.stream().filter(strategy ->
                this.getPromotionSet().equals(strategy.getPromotionSet())).collect(Collectors.toList());
    }

    @Override
    public Discount applyDiscount(Product product) {
        Discount discount = Discount.builder().amount(BigDecimal.ZERO).build();
        for (DiscountStrategy strategy : discountStrategies) {
            Optional<Discount> strategyDiscount = strategy.applyDiscount(product);
            if (strategyDiscount.isPresent() && discount.getAmount().compareTo(strategyDiscount.get().getAmount()) < 0) {
                discount = strategyDiscount.get();
            }
        }
        return discount;
    }

    @Override
    public String getPromotionSet() {
        return "promotionSetA";
    }
}
