package com.flipdeal.service.service;

import com.flipdeal.service.discount.DiscountStrategy;
import com.flipdeal.service.model.Discount;
import com.flipdeal.service.model.Product;
import com.flipdeal.service.promotion.PromotionSet;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    private final HashMap<String, PromotionSet> promotionSetHashMap = new HashMap<>();
    private final DiscountStrategy.DefaultDiscountStrategy defaultDiscountStrategy;

    public PromotionService(List<PromotionSet> promotionSets, DiscountStrategy.DefaultDiscountStrategy discountStrategy) {
        promotionSets.forEach(ps -> this.promotionSetHashMap.put(ps.getPromotionSet(), ps));
        this.defaultDiscountStrategy = discountStrategy;
    }

    public void applyDiscount(Product product, String promotionSet) {
        if (promotionSetHashMap.containsKey(promotionSet)) {
            Discount discount = promotionSetHashMap.get(promotionSet).applyDiscount(product);
            product.setDiscount(discount);
        }
        Optional<Discount> defaultDiscount = defaultDiscountStrategy.applyDiscount(product);
        if (defaultDiscount.isPresent() && product.getDiscount() != null &&
                product.getDiscount().getAmount().compareTo(defaultDiscount.get().getAmount()) < 0) {
            product.setDiscount(defaultDiscount.get());
        }
    }
}
