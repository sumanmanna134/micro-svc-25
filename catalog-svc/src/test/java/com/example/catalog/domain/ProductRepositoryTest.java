package com.example.catalog.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        })
@Sql("/test-data.sql")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }

    @Test
    public void shouldGetProductByCode() {
        ProductEntity productEntity = productRepository.findByCode("P100").orElseThrow();
        assertThat(productEntity.getCode()).isEqualTo("P100");
        assertThat(productEntity.getName()).isEqualTo("The Hunger Games");
        assertThat(productEntity.getDescription())
                .isEqualTo("Winning will make you famous. Losing means certain death...");
        assertThat(productEntity.getPrice()).isEqualTo("34.0");
    }

    @Test
    public void shouldReturnEmptyWhenProductNotExists() {
        assertThat(productRepository.findByCode("invalid_code")).isEmpty();
    }
}
