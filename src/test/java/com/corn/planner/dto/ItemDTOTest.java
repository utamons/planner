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
package com.corn.planner.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PlannerTest;
import util.TestDataUtil;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@PlannerTest
public class ItemDTOTest {

	@Test
	@DisplayName("ItemDTO should not have default fields")
	public void nullTest() {
		ItemDTO dto = ItemDTO.ItemDTOBuilder.anItemDTO().build();
		assertThat(dto.getId(), is(nullValue()));
		assertThat(dto.getName(), is(nullValue()));
		assertThat(dto.getOrderInList(), is(nullValue()));
		assertThat(dto.getOrderInAgenda(), is(nullValue()));
		assertThat(dto.getDone(), is(nullValue()));
		assertThat(dto.getRule(), is(nullValue()));
		assertThat(dto.getItemListId(), is(nullValue()));
	}

	@Test
	@DisplayName("ItemDTO should have all fields")
	public void allTest() {
		final long    id            = nextLong();
		final String  name          = randomAlphabetic(10);
		final int     orderInList   = nextInt();
		final int     orderInAgenda = nextInt();
		final boolean done          = nextBoolean();
		final RuleDTO ruleDTO       = TestDataUtil.nextRuleDTO();
		final long    itemListId    = nextLong();

		final ItemDTO dto = ItemDTO.ItemDTOBuilder
				.anItemDTO()
				.withId(id)
				.withName(name)
				.withOrderInList(orderInList)
				.withOrderInAgenda(orderInAgenda)
				.withDone(done)
				.withRule(ruleDTO)
				.withItemListId(itemListId)
				.build();

		assertThat(dto.getId(), is(id));
		assertThat(dto.getName(), is(name));
		assertThat(dto.getOrderInList(), is(orderInList));
		assertThat(dto.getOrderInAgenda(), is(orderInAgenda));
		assertThat(dto.getDone(), is(done));
		assertThat(dto.getRule(), is(ruleDTO));
		assertThat(dto.getItemListId(), is(itemListId));
	}
}
