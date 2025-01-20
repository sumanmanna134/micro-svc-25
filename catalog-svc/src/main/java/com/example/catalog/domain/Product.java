package com.example.catalog.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Product(String code, String name, String description, String imageUrl, BigDecimal price, String currency){

}
