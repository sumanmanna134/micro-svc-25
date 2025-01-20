package com.example.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CatalogSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogSvcApplication.class, args);
    }
}
