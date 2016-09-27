package com.ontestautomation.testworksconf.restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class RestAssuredExamples {
	
	@Test
	public void validateCountryForZipCode() {
						
		given().
		when().
			get("http://localhost:9876/us/90210").
		then().
			assertThat().
			body("country", equalTo("United States"));
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
}
