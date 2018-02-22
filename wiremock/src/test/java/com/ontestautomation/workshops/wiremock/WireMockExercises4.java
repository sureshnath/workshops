package com.ontestautomation.workshops.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.stubbing.Scenario;

public class WireMockExercises4 {

    public WireMockExercises4() {
	}


    private static final String SCENARIO_NAME = "Light bulb";
    private static final String STATE_LIGHT_OFF = "LIGHT_OFF";
	private static final String STATE_LIGHT_ON = "LIGHT_ON";

	public void setupStubExercise401() {

		/************************************************
		 * Create a stub that listens at path
		 * /exercise401 and exerts the following behavior:
		 * - The scenario is called 'Light bulb'
		 * - All responses have HTTP status 200
		 * - 1. A GET returns a body 'No light bulb found'
		 * - 2. A POST with body 'Insert light bulb' returns a body 'Light bulb inserted'
		 * 		and causes a transition to state 'LIGHT_OFF'
		 * - 3. A 2nd GET returns a body 'Light is OFF'
		 * - 4. A 2nd POST with body 'Switch light ON' returns a body 'Light has been turned ON'
		 * 		and causes a transition to state 'LIGHT_ON'
		 * - 5. A 3rd GET returns a body 'Light is ON'
		 ************************************************/

		stubFor(get("/exercise401").inScenario(SCENARIO_NAME)
                .whenScenarioStateIs(Scenario.STARTED)
				.willReturn(ok("No light bulb found")
                )
        );

		stubFor(get("/exercise401").inScenario(SCENARIO_NAME)
                .whenScenarioStateIs(STATE_LIGHT_OFF)
				.willReturn(ok("Light is OFF")
                )
        );

		stubFor(get("/exercise401").inScenario(SCENARIO_NAME)
                .whenScenarioStateIs(STATE_LIGHT_ON)
				.willReturn(ok("Light is ON")
                )
        );

		stubFor(post("/exercise401").inScenario(SCENARIO_NAME)
                .withRequestBody(containing("Insert light bulb"))
                .willSetStateTo(STATE_LIGHT_OFF)
				.willReturn(ok("Light bulb inserted")
                )
        );

		stubFor(post("/exercise401").inScenario(SCENARIO_NAME)
                .withRequestBody(containing("Switch light ON"))
                .willSetStateTo(STATE_LIGHT_ON)
				.willReturn(ok("Light has been turned ON")
                )
        );

    }
}
