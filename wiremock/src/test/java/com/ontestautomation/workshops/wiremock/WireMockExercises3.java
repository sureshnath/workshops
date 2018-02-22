package com.ontestautomation.workshops.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.http.Fault;
import org.apache.http.client.ClientProtocolException;

public class WireMockExercises3 {
	
	public WireMockExercises3() {
	}
	
	public void setupStubExercise301() {

		/************************************************
		 * Create a stub that listens at path
		 * /exercise301
		 * and responds to all GET requests with HTTP
		 * status code 503 and a status message equal to
		 * 'Service unavailable'
		 ************************************************/
		stubFor(get("/exercise301")
                .willReturn(aResponse()
                        .withStatus(503)
                        .withStatusMessage("Service unavailable")
                )
        );

	}
	
	public void setupStubExercise302() {

		/************************************************
		 * Create a stub that listens at path
		 * /exercise302
		 * and responds to all GET requests with a fixed
		 * delay of 2000 milliseconds
		 ************************************************/
        stubFor(get("/exercise302")
                .willReturn(aResponse()
                        .withFixedDelay(2000)
                )
        );

	}
	
	public void setupStubExercise303() {

		/************************************************
		 * Create a stub that listens at path
		 * /exercise303
		 * and responds to all GET requests with garbage
		 ************************************************/
        stubFor(get("/exercise303")
                .willReturn(aResponse()
                        .withFault( Fault.RANDOM_DATA_THEN_CLOSE)
                )
        );


    }
}
