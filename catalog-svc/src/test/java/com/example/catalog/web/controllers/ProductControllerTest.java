package com.example.catalog.web.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import com.example.catalog.AbstractIT;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void shouldReturnProducts() {
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

    //    {
    //        "message": "Product fetched Successfully",
    //            "data": {
    //        "code": "P100",
    //                "name": "The Hunger Games",
    //                "description": "Winning will make you famous. Losing means certain death...",
    //                "imageUrl": "https://images.gr-assets.com/books/1447303603l/2767052.jpg",
    //                "price": 34,
    //                "currency": "₹"
    //    },
    //        "timestamp": "2025-01-20T16:18:55.526938"
    //    }
    @Test
    void shouldReturnProductByCode() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/P100")
                .then()
                .statusCode(200)
                .body("data.code", equalTo("P100"))
                .body("data.name", equalTo("The Hunger Games"))
                .body("data.description", equalTo("Winning will make you famous. Losing means certain death..."))
                .body("data.imageUrl", equalTo("https://images.gr-assets.com/books/1447303603l/2767052.jpg"))
                .body("data.currency", equalTo("₹"))
                .body("message", equalTo("Product fetched Successfully")) // Verify the response message
                .body("timestamp", notNullValue());
    }
}
