package com.corn.planner.service;

import com.corn.planner.dto.ItemDTO;
import com.corn.planner.entity.Item;
import com.corn.planner.entity.ItemList;
import com.corn.planner.repository.ItemListRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.PlannerTest;
import util.TestDataUtil;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.TestDataUtil.*;

@PlannerTest
public class ItemMapperTest {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemListRepository itemListRepo;

	@Test
	@DisplayName("ItemDTO should be converted to entity")
	public void toEntityTest() {
		ItemList itemList = itemListRepo.save(
				nextItemList(null, nextRule(null), null)
		);

		ItemDTO dto = TestDataUtil.nextItemDTO(itemList.getId());
		Item en = itemMapper.toEntity(dto);

		assertThat(en.getId(),is(nullValue()));
		assertThat(en.getName(),is(dto.getName()));
		assertThat(en.getOrderInList(),is(dto.getOrderInList()));
		assertThat(en.getOrderInAgenda(),is(dto.getOrderInAgenda()));
		assertThat("Entity rule is not equal to dto rule",isEqual(en.getRule(),dto.getRule()));
		assertThat(en.getItemList().getId(),is(dto.getItemListId()));
	}

	@Test
	@DisplayName("Item entity should be converted to dto")
	public void toDTOTest() {
		Item en = nextItem(nextItemList());
		ItemDTO dto = itemMapper.toDTO(en);

		assertThat(dto.getId(),is(en.getId()));
		assertThat(dto.getName(),is(en.getName()));
		assertThat(dto.getOrderInList(),is(en.getOrderInList()));
		assertThat(dto.getOrderInAgenda(),is(en.getOrderInAgenda()));
		assertThat("Entity rule is not equal to dto rule",isEqual(en.getRule(),dto.getRule()));
		assertThat(dto.getItemListId(),is(en.getItemList().getId()));
	}

}
