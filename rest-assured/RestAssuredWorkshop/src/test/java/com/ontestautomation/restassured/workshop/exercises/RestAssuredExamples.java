package com.ontestautomation.restassured.workshop.exercises;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RestAssuredExamples {

	@DataProvider(name = "drivers")
	public Object[][] createDriverData() {
		return new Object[][] {
				{ "hamilton", "44" },
				{ "max_verstappen", "33"},
		};
	}
	
	@Test
	public void validateCountryForZipcode() {
		
		given().
		when().
			get("http://api.zippopotam.us/us/90210").	// Perform a GET call to the specified resource
		then().
			assertThat().								// Check that the value of the element 'country'
			body("country",equalTo("United States"));	// in the response equals 'United States'
	}

	@Test
	public void useQueryParametersSingleTestcase() {

		given().
			params("text", "testcaseOne").
		when().
			get("http://md5.jsontest.com").
		then().
			body("md5",equalTo("4ff1c9b1d1f23c6def53f957b1ed827f"));
	}

	@Test
	public void useMultipleQueryParameters() {

		given().
			params("q", "Kopenhagen", "mode", "xml").
		when().
			get("http://api.openweathermap.org/data/2.5/weather").
		then().
			body("current.city.country",equalTo("Denmark"));	
	}
	
	@Test
	public void useSinglePathParameter() {

		given().
			pathParam("driverName", "max_verstappen").
		when().
			get("http://ergast.com/api/f1/drivers/{driverName}.json").
		then()
			.body("MRData.DriverTable.Drivers.permanentNumber[0]",equalTo("33"));			
	}
	
	@Test(dataProvider = "drivers")
	public void useSinglePathParameterWithDataProvider(String driverName, String permanentNumber) {

		given().
			pathParam("driverName", driverName).
		when().
			get("http://ergast.com/api/f1/drivers/{driverName}.json").
		then()
			.body("MRData.DriverTable.Drivers.permanentNumber[0]",equalTo(permanentNumber));			
	}

	@Test
	public void useMultiplePathParameters() {

		given().
			pathParam("driverName", "alonso").
			pathParam("constructorName","renault").
		when().
			get("http://ergast.com/api/f1/drivers/{driverName}/constructors/{constructorName}/seasons.json").
		then()
			.body("MRData.SeasonTable.Seasons.season",hasItem("2003"));			
	}	
	
	@Test
	public void useBasicAuthentication() {
		
		given().
			params("grant_type","client_credentials").
			auth().
			preemptive().
			basic("AUyqLmmlHyX4Th7BdXpIN-sKu5rARNpWLNtQZabRneRp5eDrKEU5pdiNIOMgc-4OiNu4jX8VJwfwWr1a","ECFXJmz2yW0WDf0itUE13jgaBhLkF5kEV9pyzt8iK9vvWgoSBRQ0HCywNIqYftSwXmB6EH_KOGq0nO39").
		when().
			post("https://api.sandbox.paypal.com/v1/oauth2/token").
		then().
			log().
			body();
	}
	
	@Test
	public void useOAuth2Authentication() {

		given().
			auth().
			oauth2("auth_token").
		when().
			get("https://api.sandbox.paypal.com/v1/identity/openidconnect/userinfo/?schema=openid").
		then().
			assertThat().
			body("",hasKey("user_id"));
	}
	
	@Test
	public void checkResponseTime() {
		
		given().
		when().
			get("http://ergast.com/api/f1/circuits/monza.json").
		then().
			assertThat().
			time(lessThan(100L), TimeUnit.MILLISECONDS);
	}
	
	@Test
	public void checkResponseHeaders() {
		
		given().
		when().
			get("http://api.zippopotam.us/us/90210").
		then().
			assertThat().
			statusCode(200).
			and().
			contentType("application/json");
	}
}