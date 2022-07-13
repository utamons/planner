package com.corn.planner.exception;

import org.junit.jupiter.api.Test;
import util.PlannerTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@PlannerTest
public class ValidationExceptionTest {

	@Test
	public void validationExceptionTest() {
		final String expectedMessage = "Test message";
		Exception exception = assertThrows(ValidationException.class, () -> {
			throw new ValidationException(expectedMessage);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage, is(equalTo(expectedMessage)));

	}
}
