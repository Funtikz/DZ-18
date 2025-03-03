package org.example.dz18;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.dz18.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestUserControllerTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/user";
    }


    @Test
    public void testFindAllUsers() {
        given()
                .when()
                .get("/find-all")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("$", not(empty()));
    }

    @Test
    public void testFindUserById() {
        long userId = 1L;

        given()
                .pathParam("id", userId)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("id", equalTo((int) userId));
    }

    @Test
    public void testCreateUser() {
        User newUser = new User();
        newUser.setFirstname("Иван");
        newUser.setLastname("Иванов");
        newUser.setAge(30);

        given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post("/create")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Иван"))
                .body("lastname", equalTo("Иванов"))
                .body("age", equalTo(30));
    }


    @Test
    public void testDeleteUser() {
        long userId = 2L;

        given()
                .pathParam("id", userId)
                .when()
                .delete("/delete/{id}")
                .then()
                .statusCode(HttpStatus.OK.value());

        given()
                .pathParam("id", userId)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
