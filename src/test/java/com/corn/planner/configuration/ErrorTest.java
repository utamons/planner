package com.corn.planner.configuration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PlannerTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@PlannerTest
public class ErrorTest {

	@Test
	@DisplayName("Error should not have default fields")
	public void errorNullTest() {
		Error error = Error.ErrorBuilder.anError().build();
		assertThat(error.getMessage(), is(nullValue()));
		assertThat(error.getStatus(), is(0));
	}

	@Test
	@DisplayName("Error should build")
	public void errorTest() {
		final String testMessage = RandomStringUtils.randomAlphabetic(10);
		final int    testStatus  = RandomUtils.nextInt();
		Error error = Error.ErrorBuilder.anError()
		                                .withMessage(testMessage)
		                                .withStatus(testStatus)
		                                .build();
		assertThat(error.getMessage(), is(testMessage));
		assertThat(error.getStatus(), is(testStatus));
	}
}
