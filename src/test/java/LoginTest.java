import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class LoginTest {


    @Test
    void succesfulLoginTest(){
        String authData = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";

        given()
                    .body(authData)
                    .contentType(JSON)
                    .log().uri()
                .when()
                    .post("https://reqres.in/api/login")
                .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .body("token",is("QpwL5tke4Pnpja7X4"));

    }

    @Test
    void unsuccesfulLogin415Test(){
        given()
                .log().uri()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);


    }
    @Test
    void unsuccessfulLogin400Test() {
        String authData = "";

        given()
                .body(authData)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
    @Test
    void userNotFoundTest() {
        String authData = "{\"email\": \"eveasdas.holt@reqres.in\", \"password\": \"cda\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));
    }
    @Test
    void missingPasswordTest() {
        String authData = "{\"email\": \"eveasdas.holt@reqres.in\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
    @Test
    void missingLoginTest() {
        String authData = "{\"password\": \"cda\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

}
