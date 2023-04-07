package com.flipdeal.service.promotion;

import com.flipdeal.service.model.Discount;
import com.flipdeal.service.model.Product;

public interface PromotionSet {

    String getPromotionSet();

    Discount applyDiscount(Product product);
}
