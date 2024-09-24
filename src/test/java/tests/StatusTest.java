package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class StatusTest {

    @Test
    void chekTotal(){
        given()
                .log().all()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body()
                .statusCode(200);
    }
}
