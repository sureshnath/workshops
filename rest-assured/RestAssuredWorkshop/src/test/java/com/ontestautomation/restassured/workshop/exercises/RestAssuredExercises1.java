package com.ontestautomation.restassured.workshop.exercises;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RestAssuredExercises1 {

	@BeforeClass
	public void initPath() {

		RestAssured.baseURI = "http://localhost:9876";
	}

	/*******************************************************
	 * Send a GET request to /api/f1/2016/drivers.json
	 * and check that the answer has HTTP status code 200
	 ******************************************************/

	@Test
	public void checkResponseCodeForCorrectRequest() {

		given().
		when().
				get("/api/f1/2016/drivers.json").
		then().
                assertThat().
                statusCode(200)
        ;
	}

	/*******************************************************
	 * Send a GET request to /api/f1/incorrect.json
	 * and check that the answer has HTTP status code 500
	 ******************************************************/

	@Test
	public void checkResponseCodeForIncorrectRequest() {

		given().
		when().
                get("/api/f1/2016/incorrect.json").
        then().
                assertThat().
                statusCode(404)
        ;

	}

	/*******************************************************
	 * Send a GET request to /api/f1/2016/drivers.json
	 * and check that the response is in JSON format
	 ******************************************************/

	@Test
	public void checkResponseContentTypeJson() {

		given().
		when().
                get("/api/f1/2016/drivers.json").
		then().
                assertThat().
                contentType(ContentType.JSON)
        ;
	}

	/***********************************************
	 * Retrieve circuit information for the first race
	 * of the 2014 season and check the circuitId equals
	 * albert_park
	 * Use /api/f1/2014/1/circuits.json
	 **********************************************/

	@Test
	public void checkTheFirstRaceOf2014WasAtAlbertPark() {

		given().
		when().
                get("/api/f1/2014/1/circuits.json").
		then().
                assertThat()
        .body("MRData.CircuitTable.Circuits.circuitId[0]", equalTo("albert_park"))

        ;
	}

	/***********************************************
	 * Retrieve the list of circuits for the 2014
	 * season and check that it contains silverstone
	 * Use /api/f1/2014/circuits.json
	 **********************************************/

	@Test
	public void checkThereWasARaceAtSilverstoneIn2014() {

		given().
		when().
                get("/api/f1/2014/circuits.json").
        then().
                assertThat()
                .body("MRData.CircuitTable.Circuits.circuitId", hasItem("silverstone"))

        ;
	}

	/***********************************************
	 * Retrieve the list of circuits for the 2014
	 * season and check that it does not contain
	 * nurburgring
	 * USe /api/f1/2014/circuits.json
	 **********************************************/

	@Test
	public void checkThereWasNoRaceAtNurburgringIn2014() {

		given().
		when().
                get("/api/f1/2014/circuits.json").
        then().
                assertThat().
                body("MRData.CircuitTable.Circuits.circuitId", not(hasItem("nurburgring")))
        ;
	}
}
