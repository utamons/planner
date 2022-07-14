package com.corn.planner.service;

import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.entity.ItemList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PlannerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.TestDataUtil.isEqual;
import static util.TestDataUtil.nextItemListDTO;

@PlannerTest
public class ItemListMapperTest {

	@Test
	@DisplayName("ItemListDTO should be converted to entity")
	public void toEntityTest() {
		ItemListDTO dto = nextItemListDTO();
		ItemList en = ItemListMapper.toEntity(dto);

		assertThat(en.getId(), is(dto.getId()));
		assertThat(en.getName(), is(dto.getName()));
		assertThat(en.getOrderInList(),is(dto.getOrderInList()));
		assertThat(en.getOrderInAgenda(),is(dto.getOrderInAgenda()));
		assertThat("Rule is equal", isEqual(en.getRule(),dto.getRule()));
		assertThat(en.getShowFirst(),is(dto.getShowFirst()));
		assertThat(en.getItems(),is(dto));
	}

}
