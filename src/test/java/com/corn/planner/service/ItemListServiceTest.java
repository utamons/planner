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
import com.corn.planner.dto.RuleDTO;
import com.corn.planner.entity.Item;
import com.corn.planner.entity.ItemList;
import com.corn.planner.entity.Rule;
import com.corn.planner.repository.ItemListRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import util.PlannerTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.TestDataUtil.*;

@PlannerTest
@Transactional
public class ItemListServiceTest {

	@Autowired
	private ItemListRepository repo;

	@Autowired
	private ItemListService service;

	@Test
	@DisplayName("ItemList should be created")
	public void createTest() {
		ItemListDTO dto = nextItemListDTO(null, false);
		long id = service.create(dto);
		assertThat(id,is(notNullValue()));

		ItemList entity = repo.getReferenceById(id);

		assertThat(entity.getOrderInList(), equalTo(dto.getOrderInList()));
		assertThat(entity.getName(), equalTo(dto.getName()));
		assertThat(entity.getOrderInAgenda(), equalTo(entity.getOrderInAgenda()));
		assertThat(entity.getShowFirst(), equalTo(dto.getShowFirst()));

		assertThat(entity.getRule(), is(notNullValue()));

		Rule rule = entity.getRule();
		RuleDTO ruleDTO = dto.getRule();

		assertThat(rule.getId(), is(notNullValue()));
		assertThat(rule.getCreatedAt(),equalTo(ruleDTO.getCreatedAt()));
		assertThat(rule.getCompletedAt(),equalTo(ruleDTO.getCompletedAt()));
		assertThat(rule.getEvery(),equalTo(ruleDTO.getEvery()));
		assertThat(rule.getHideAtHour(), equalTo(ruleDTO.getHideAtHour()));
		assertThat(rule.getHideAtMinute(), equalTo(ruleDTO.getHideAtMinute()));
		assertThat(rule.getOnDayOfMonth(),equalTo(ruleDTO.getOnDayOfMonth()));
		assertThat(rule.getOnDayOfMonthWeek(),equalTo(ruleDTO.getOnDayOfMonthWeek()));
		assertThat(rule.getOnDayWeek(),equalTo(ruleDTO.getOnDayWeek()));
		assertThat(rule.getOnMonth(),equalTo(ruleDTO.getOnMonth()));
		assertThat(rule.getRepeatType(),equalTo(ruleDTO.getRepeatType()));
		assertThat(rule.getShowAtHour(),equalTo(ruleDTO.getShowAtHour()));
		assertThat(rule.getShowAtMinute(),equalTo(ruleDTO.getShowAtMinute()));
		assertThat(rule.isFri(),equalTo(ruleDTO.getFri()));
		assertThat(rule.isMon(),equalTo(ruleDTO.getMon()));
		assertThat(rule.isSat(),equalTo(ruleDTO.getSat()));
		assertThat(rule.isSun(),equalTo(ruleDTO.getSun()));
		assertThat(rule.isThu(),equalTo(ruleDTO.getThu()));
		assertThat(rule.isTue(),equalTo(ruleDTO.getTue()));
		assertThat(rule.isWed(),equalTo(ruleDTO.getWed()));
	}

	@Test
	@DisplayName("ItemList should read")
	public void readTest() {
		final ItemList entity = nextItemList(null, nextRule(null), null);
		repo.save(entity);
		List<Item> items = nextList(()->nextItem(entity),10);

	}
}
