package com.flipdeal.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String product;
    private Double rating;
    private Currency currency;
    private BigDecimal price;
    private Category category;
    private Integer inventory;
    private String origin;
    private String arrivalStatus;
    private Discount discount;

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public enum Category {
        @JsonProperty("electronics") ELECTRONICS("electronics"),
        @JsonProperty("clothing") CLOTHING("clothing"),
        @JsonProperty("grooming") GROOMING("grooming"),
        @JsonProperty("accessories") ACCESSORIES("accessories"),
        @JsonProperty("toys") TOYS("toys"),
        @JsonProperty("books") BOOKS("books"),
        @JsonProperty("apparels") APPARELS("apparels"),
        @JsonProperty("home") HOME("home"),
        @JsonProperty("furnishing") FURNISHING("furnishing");

        private String value;
    }
}

