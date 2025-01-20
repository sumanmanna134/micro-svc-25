package com.example.catalog.web.controllers;

import com.example.catalog.AbstractIT;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void shouldReturnProducts(){
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("meta.count", equalTo(15)) // Verify the total count of products
                .body("meta.perPage", equalTo(10)) // Verify the perPage value
                .body("meta.currentPage", equalTo(1)) // Verify the current page
                .body("meta.pages", equalTo(2)) // Verify total pages
                .body("meta.hasNext", equalTo(true)) // Verify there's a next page
                .body("message", equalTo("Items Fetched Successfully")) // Verify the response message
                .body("timestamp", notNullValue());

    }
}