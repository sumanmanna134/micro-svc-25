package com.example.catalog.domain;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record Product(
        String code, String name, String description, String imageUrl, BigDecimal price, String currency) {}
