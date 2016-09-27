package com.ontestautomation.workshops.wiremock;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class WireMockExamplesTests {
	
	WireMockExamples wme = new WireMockExamples();
		
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(9876);
	
	@Test
	public void testPingPongPositive() {
        
	    wme.setupExampleStub();
	         
	    given().
	        body("<input>PING</input>").
	    when().
	        post("http://localhost:9876/pingpong").
	    then().
	    	log().
	    	body().
	    	and().
	        assertThat().
	        statusCode(200).
	        and().
	        body("output", equalTo("PONG"));
	}
}
