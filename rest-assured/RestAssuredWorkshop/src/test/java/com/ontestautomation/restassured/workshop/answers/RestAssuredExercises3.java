package com.ontestautomation.restassured.workshop.answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestAssuredExercises3 {
	
	/*******************************************************
	 * Request an authentication token for the Paypal sandbox
	 * API and write the response to the console
	 * Use Basic authentication
	 * username = AUyqLmmlHyX4Th7BdXpIN-sKu5rARNpWLNtQZabRneRp5eDrKEU5pdiNIOMgc-4OiNu4jX8VJwfwWr1a
	 * password = ECFXJmz2yW0WDf0itUE13jgaBhLkF5kEV9pyzt8iK9vvWgoSBRQ0HCywNIqYftSwXmB6EH_KOGq0nO39
	 * https://api.sandbox.paypal.com/v1/oauth2/token
	 ******************************************************/
	
	@BeforeClass
	public void retrieveOAuthToken() {
		
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
	
	/*******************************************************
	 * Request a list of payments for this account and check
	 * that the number of payments made equals 0.
	 * Use OAuth2 authenticatie with the previously retrieved
	 * authentication token.
	 * https://api.sandbox.paypal.com/v1/payments/payment/
	 ******************************************************/
	
	@Test
	public void checkNumberOfPayments() {
		
		given().
			auth().
			oauth2("A101.Z9Ld87BSuMFSxUxGiUL3FCabpcnr-yURg2S7HYngOc6104_4c0-RIC3CAqyrCjAD.ndfJOqSUk6dDNbGirW7EHU0mtZy").
		when().
			get("https://api.sandbox.paypal.com/v1/payments/payment/").
		then().
			log().
			body().
			and().
			assertThat().
			body("count",equalTo(0));
	}
	
	/*******************************************************
	 * Request the list of all circuits that ever hosted a
	 * Formula 1 race and check that is request is answered
	 * within 500 ms
	 ******************************************************/
	
	@Test
	public void checkResponseTimeForCircuitList() {
		
		given().
		when().
			get("http://ergast.com/api/f1/circuits.json").
		then().
			assertThat().
			time(lessThan(500L),TimeUnit.MILLISECONDS);
	}
}
