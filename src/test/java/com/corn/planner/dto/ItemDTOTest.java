package com.corn.planner.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PlannerTest;
import util.TestDataGenerator;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.*;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.TestDataGenerator.itemDTO;

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
		final RuleDTO ruleDTO       = TestDataGenerator.ruleDTO();
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
