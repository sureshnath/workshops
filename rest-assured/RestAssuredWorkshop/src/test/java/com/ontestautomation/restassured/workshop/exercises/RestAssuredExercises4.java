package com.ontestautomation.restassured.workshop.exercises;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RestAssuredExercises4 {
	
	/*******************************************************
	 * Request an authentication token for the Paypal sandbox
	 * API and write the response to the console
	 * Use Basic authentication
	 * username = AUyqLmmlHyX4Th7BdXpIN-sKu5rARNpWLNtQZabRneRp5eDrKEU5pdiNIOMgc-4OiNu4jX8VJwfwWr1a
	 * password = ECFXJmz2yW0WDf0itUE13jgaBhLkF5kEV9pyzt8iK9vvWgoSBRQ0HCywNIqYftSwXmB6EH_KOGq0nO39
	 * https://api.sandbox.paypal.com/v1/oauth2/token
	 * Store this authentication token in a String variable
	 * for future reference
	 ******************************************************/
	
	@BeforeClass
	public void retrieveOAuthToken() {
		
		given().
		when().
		then();
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
		when().
		then();
	}
}
