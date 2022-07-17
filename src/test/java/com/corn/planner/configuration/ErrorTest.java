/* planner
 * Copyleft (C) 2022  Cornknight
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
