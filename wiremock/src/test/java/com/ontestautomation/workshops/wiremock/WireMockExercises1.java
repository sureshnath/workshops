package com.ontestautomation.workshops.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockExercises1 {
	
	public WireMockExercises1() {
	}
	
	public void setupStubExercise101() {

		/************************************************
		 * Create a stub that listens at path
		 * /exercise101
		 * and responds to all GET requests with HTTP status code 200
		 ************************************************/
		stubFor(get(urlEqualTo("/exercise101"))
				.willReturn(aResponse()
						.withStatus(200)));
	}
	
	public void setupStubExercise102() {

		/************************************************
		 * Create a stub that listens at path
		 * /exercise102
		 * and responds to all GET requests with a response
		 * with MIME type text/plain
		 ************************************************/
        stubFor(get(urlEqualTo("/exercise102"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                ));
    }
	
	public void setupStubExercise103() {

		/************************************************
		 * Create a stub that listens at path
		 * /exercise103
		 * and responds to all GET requests with a response
		 * with body 'Exercise 103'
		 ************************************************/
        stubFor(get(urlEqualTo("/exercise103"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Exercise 103")
                ));
	}
}
