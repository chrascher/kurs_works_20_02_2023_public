package org.acme.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    @Disabled
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello Chris RESTEasy"));
    }

    @Test
    @Disabled
    public void testHelloEndpointSubPath() {
        given()
                .when().get("/hello/helloSubPath")
                .then()
                .statusCode(200)
                .body(is("Hello from RESTEasy"));
    }

}