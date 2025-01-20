/*
 * Copyright (c) 2025  IsumDev. All rights reserved.
 *
 * This software, including its code and documentation, is the proprietary property of IsumDev
 * and is protected by applicable copyright laws. Unauthorized use, reproduction, modification, or distribution is strictly prohibited.
 * By using this software, you agree to abide by these terms.
 *  For licensing or inquiries, contact IsumDev. All rights are reserved under international and domestic intellectual property laws.
 */

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
