package com.example.catalog.domain;

import com.example.catalog.ApplicationProperties;
import com.example.catalog.exception.ProductNotFoundException;
import com.example.catalog.web.response.AppResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;

    ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public AppResponse<List<Product>> getProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(pageNo <= 1 ? 0 : pageNo - 1, applicationProperties.pageSize(), sort);
        Page<Product> all = productRepository.findAll(pageable).map(ProductMapper::toProduct);
        return AppResponse.fromPage(all, "Items Fetched Successfully");
    }

    public AppResponse<?> getProductByCode(String code) {
        return AppResponse.builder()
                .data(productRepository
                        .findByCode(code)
                        .map(ProductMapper::toProduct)
                        .orElseThrow(() -> ProductNotFoundException.forCode(code)))
                .message("Product fetched Successfully")
                .build();
    }
}
