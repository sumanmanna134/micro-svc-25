package com.example.catalog.web.controllers;

import com.example.catalog.domain.ProductService;
import com.example.catalog.web.response.AppResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public AppResponse<?> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    public AppResponse<?> getProductByCode(@PathVariable String code) {
        return productService.getProductByCode(code);
    }
}
