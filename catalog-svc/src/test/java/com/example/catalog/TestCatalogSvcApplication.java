package com.example.catalog;

import org.springframework.boot.SpringApplication;

public class TestCatalogSvcApplication {

	public static void main(String[] args) {
		SpringApplication.from(CatalogSvcApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
