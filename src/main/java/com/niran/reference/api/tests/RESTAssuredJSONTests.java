package com.niran.reference.api.tests;

import com.niran.reference.api.domain.Person;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.AssertJUnit.assertEquals;

public class RESTAssuredJSONTests {

    //final static String BASE_URI = "http://localhost:8080/people";

    @Test
    public void getAllPersons() {
        given().
                when().
                get("/people/all").
                then().
                assertThat()
                .body("id", hasItems(1, 2, 3, 4, 5))
                .body("firstname", contains("Marta", "Matylda", "Patryk", "Marcin", "Baba"));
    }

    @Test
    public void testStatusCodeAfterGetAllPersons() {
        Response response = get("http://localhost:8080/people/all");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }

    @Test
    public void whenMeasureResponseTimeThenOK() {
        Response response = get("/people/all");
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);

        assertEquals(timeInS, timeInMS/1000);
    }

    @Test
    public void getPerson() {
        get("/people/1").then().statusCode(200).assertThat()
                .body(("id"), equalTo(1))
                .body("firstname", equalTo("Marta"))
                .body("lastname", equalTo("Kolorowa"));
    }

    @Test
    public void createPerson() {
        Person person = new Person();
        person.setFirstname("Sylwia");
        person.setLastname("Nowa");
        person.setAge(30);

        given().body(person)
                .when()
                .contentType(ContentType.JSON)
                .post("/people/add");

        get("/people/6").then().statusCode(200).assertThat()
                .body("id", equalTo(6))
                .body("firstname", equalTo("Sylwia"))
                .body("lastname", equalTo("Nowa"))
                .body("age", equalTo(30));
    }

    @Test
    public void deletePerson() {
        given()
                .when()
                .delete("/people/6")
                .then()
                .statusCode(200);
    }

    @Test
    public void updatePerson() {
        Person person = new Person();
        person.setFirstname("Matylda");
        person.setLastname("LastNameUp");
        person.setAge(66);
        person.setId((long) 2);

        given().contentType(ContentType.JSON)
                .body(person)
                .when()
                .put("/people/update")
                .then()
                .statusCode(200);

        get("/people/2").then().statusCode(200).assertThat()
                .body("firstname", equalTo("Matylda"))
                .body("lastname", equalTo("LastNameUp"))
                .body("age", equalTo(66))
                .body("id", equalTo(2));
    }


}
