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
