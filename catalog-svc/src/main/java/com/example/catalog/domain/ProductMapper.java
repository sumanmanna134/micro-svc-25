package com.example.catalog.domain;

import java.util.Currency;
import java.util.Locale;

class ProductMapper {

    static Product toProduct(ProductEntity productEntity){
        return new Product.ProductBuilder()
                .name(productEntity.getName())
                .code(productEntity.getCode())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .currency(Currency.getInstance(new Locale("en","in")).getSymbol())
                .imageUrl(productEntity.getImageUrl())
                .build();
    }
}
