package com.ontestautomation.workshops.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockExamples {
		
	public WireMockExamples() {		
	}
	
	public void setupExampleStub() {

		stubFor(post(urlEqualTo("/pingpong"))
				.withRequestBody(matching("<input>PING</input>"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/xml")
						.withBody("<output>PONG</output>")));
	}
}
