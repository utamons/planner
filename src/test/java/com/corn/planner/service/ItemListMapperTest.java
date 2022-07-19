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
package com.corn.planner.service;

import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.entity.ItemList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.PlannerTest;
import util.TestDataUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.TestDataUtil.*;

@PlannerTest
public class ItemListMapperTest {

	@Autowired
	private ItemListMapper itemListMapper;

	@Test
	@DisplayName("ItemListDTO should be converted to entity")
	public void toEntityTest() {
		ItemListDTO dto = nextItemListDTO(null, false);
		ItemList en = itemListMapper.toEntity(dto);

		assertThat(en.getId(), is(dto.getId()));
		assertThat(en.getName(), is(dto.getName()));
		assertThat(en.getOrderInList(),is(dto.getOrderInList()));
		assertThat(en.getOrderInAgenda(),is(dto.getOrderInAgenda()));
		assertThat("Rule is equal", isEqual(en.getRule(),dto.getRule()));
		assertThat(en.getShowFirst(),is(dto.getShowFirst()));
	}

	@Test
	@DisplayName("ItemList entity should be converted to DTO")
	public void toDTOTest() {
		ItemList en = nextItemList();
		ItemListDTO dto = itemListMapper.toDTO(en);

		assertThat(dto.getId(), is(en.getId()));
		assertThat(dto.getName(), is(en.getName()));
		assertThat(dto.getOrderInList(),is(en.getOrderInList()));
		assertThat(dto.getOrderInAgenda(),is(en.getOrderInAgenda()));
		assertThat("Rule is equal", isEqual(en.getRule(),dto.getRule()));
		assertThat(dto.getShowFirst(),is(en.getShowFirst()));
		assertThat("items should be equal",areEqual(en.getItems(), dto.getItems(), TestDataUtil::isEqual));
	}

	@Test
	@DisplayName("ItemList entity should be converted to DTO with null items")
	public void toDTONullItemsTest() {
		ItemList en = nextItemList();
		en.setItems(null);
		ItemListDTO dto = itemListMapper.toDTO(en);

		assertThat(dto.getId(), is(en.getId()));
		assertThat(dto.getName(), is(en.getName()));
		assertThat(dto.getOrderInList(),is(en.getOrderInList()));
		assertThat(dto.getOrderInAgenda(),is(en.getOrderInAgenda()));
		assertThat("Rule is equal", isEqual(en.getRule(),dto.getRule()));
		assertThat(dto.getShowFirst(),is(en.getShowFirst()));
		assertThat(dto.getItems(),is(nullValue()));
	}

}
