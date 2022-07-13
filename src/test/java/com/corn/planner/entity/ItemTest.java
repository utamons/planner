package com.corn.planner.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PlannerTest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.TestDataGenerator.nextItemList;
import static util.TestDataGenerator.nextRule;

@PlannerTest
public class ItemTest {

	@Test
	@DisplayName("Item should be created")
	public void create() {
		Item item = new Item();
		assertThat(item, is(notNullValue()));
	}

	@Test
	@DisplayName("Item should not contain default values")
	public void defaultTest() {
		Item item = new Item();
		assertThat(item.getId(),is(nullValue()));
		assertThat(item.getName(),is(nullValue()));
		assertThat(item.getOrderInList(),is(nullValue()));
		assertThat(item.getOrderInAgenda(),is(nullValue()));
		assertThat(item.isDone(),is(nullValue()));
		assertThat(item.getRule(),is(nullValue()));
		assertThat(item.getItemList(),is(nullValue()));
	}

	@Test
	@DisplayName("Item should have working getters/setters")
	public void getSetTest() {
		final long id = nextLong();
		final String name = randomAlphabetic(10);
		final int orderInList = nextInt();
		final int orderInAgenda = nextInt();
		final boolean done = nextBoolean();
		final Rule rule = nextRule();
		final ItemList itemList = nextItemList();

		final Item item = new Item();

		itemList.getItems().add(item);

		item.setId(id);
		item.setName(name);
		item.setOrderInList(orderInList);
		item.setOrderInAgenda(orderInAgenda);
		item.setDone(done);
		item.setRule(rule);
		item.setItemList(itemList);

		assertThat(item.getId(),is(id));
		assertThat(item.getName(),is(name));
		assertThat(item.getOrderInList(),is(orderInList));
		assertThat(item.getOrderInAgenda(),is(orderInAgenda));
		assertThat(item.isDone(),is(done));
		assertThat(item.getRule(),is(rule));
		assertThat(item.getItemList(),is(itemList));
	}
}
