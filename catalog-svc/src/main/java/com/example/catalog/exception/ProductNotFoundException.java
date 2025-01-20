package com.example.catalog.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException forCode(String code) {
        return new ProductNotFoundException("Product Not Found with code: " + code);
    }

    public static ProductNotFoundException forProductId(String productId) {
        return new ProductNotFoundException("Product Not Found with product id: " + productId);
    }
}
