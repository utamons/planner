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
package com.corn.planner.controller;

import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.dto.RuleDTO;
import com.corn.planner.entity.ItemList;
import com.corn.planner.entity.Rule;
import com.corn.planner.repository.ItemListRepository;
import com.corn.planner.repository.RuleRepository;
import com.corn.planner.service.ItemListMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import util.TestDataUtil;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static util.TestDataUtil.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ItemListControllerTest {

	@Autowired
	private RuleRepository ruleRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ItemListRepository repository;


	@Test
	@DisplayName("ItemController should create")
	@Transactional
	public void createTest() throws Exception {
		ItemListDTO dto = TestDataUtil.nextItemListDTO(null, false);

		MvcResult result = mockMvc.perform(post("/api/list")
				                                   .contentType(MediaType.APPLICATION_JSON)
				                                   .content(TestDataUtil.asJson(dto)))
		                          .andExpect(status().isCreated())
		                          .andReturn();
		String idStr = result.getResponse().getContentAsString();

		ItemList en = repository.getReferenceById(Long.parseLong(idStr));

		assertThat(en.getName(), is(dto.getName()));
		assertThat(en.getOrderInList(),is(dto.getOrderInList()));
		assertThat(en.getOrderInAgenda(),is(dto.getOrderInAgenda()));
		assertThat("Rule is equal", isEqualWithoutId(en.getRule(),dto.getRule()));
		assertThat(en.getShowFirst(),is(dto.getShowFirst()));
	}

	@Test
	@DisplayName("ItemController should read")
	@Transactional
	public void readTest() throws Exception {
		final ItemList en = TestDataUtil.nextItemList(null, nextRule(null), null);
		repository.save(en);

		en.setItems(nextList(()->nextItem(null, en), 10));

		MvcResult result = mockMvc.perform(get("/api/list/" + en.getId()))
				                                   .andExpect(status().isOk())
				                                   .andReturn();

		String json = result.getResponse().getContentAsString();
		ItemListDTO dto = TestDataUtil.asObject(json, ItemListDTO.class);

		assertThat(dto.getName(), is(en.getName()));
		assertThat(dto.getOrderInList(),is(en.getOrderInList()));
		assertThat(dto.getOrderInAgenda(),is(en.getOrderInAgenda()));
		assertThat("Rule is equal", isEqualWithoutId(en.getRule(),dto.getRule()));
		assertThat(dto.getShowFirst(),is(en.getShowFirst()));

		assertThat("Items are equal", areEqual(en.getItems(), dto.getItems(), TestDataUtil::isEqualWithoutId));
	}

	@Test
	@DisplayName("ItemController should update")
	@Transactional
	public void updateTest() throws Exception {
		ItemList en = TestDataUtil.nextItemList(null, nextRule(null), null);
		repository.save(en);

		final ItemListDTO dto = TestDataUtil.nextItemListDTO(en.getId(), false);

		mockMvc.perform(put("/api/list/")
				                                   .contentType(MediaType.APPLICATION_JSON)
				                                   .content(TestDataUtil.asJson(dto)))
		                          .andExpect(status().isOk());

		en = repository.getReferenceById(en.getId());

		assertThat(en.getName(), is(dto.getName()));
		assertThat(en.getOrderInList(),is(dto.getOrderInList()));
		assertThat(en.getOrderInAgenda(),is(dto.getOrderInAgenda()));
		assertThat(en.getShowFirst(),is(dto.getShowFirst()));

		Rule rule = en.getRule();
		RuleDTO ruleDTO = dto.getRule();

		assertThat(ruleDTO.getCompletedAt(), is(rule.getCompletedAt()));
		assertThat(ruleDTO.getShowAtHour(), is(rule.getShowAtHour()));
		assertThat(ruleDTO.getHideAtHour(), is(rule.getHideAtHour()));
		assertThat(ruleDTO.getShowAtMinute(), is(rule.getShowAtMinute()));
		assertThat(ruleDTO.getHideAtMinute(), is(rule.getHideAtMinute()));
		assertThat(ruleDTO.getRepeatType(), is(rule.getRepeatType()));
		assertThat(ruleDTO.getEvery(), is(rule.getEvery()));
		assertThat(ruleDTO.getSun(), is(rule.isSun()));
		assertThat(ruleDTO.getMon(), is(rule.isMon()));
		assertThat(ruleDTO.getTue(), is(rule.isTue()));
		assertThat(ruleDTO.getWed(), is(rule.isWed()));
		assertThat(ruleDTO.getThu(), is(rule.isThu()));
		assertThat(ruleDTO.getFri(), is(rule.isFri()));
		assertThat(ruleDTO.getSat(), is(rule.isSat()));
		assertThat(ruleDTO.getOnDayOfMonth(), is(rule.getOnDayOfMonth()));
		assertThat(ruleDTO.getOnDayOfMonthWeek(), is(rule.getOnDayOfMonthWeek()));
		assertThat(ruleDTO.getOnDayWeek(), is(rule.getOnDayWeek()));
		assertThat(ruleDTO.getOnMonth(), is(rule.getOnMonth()));
	}

	@Test
	@DisplayName("ItemController should delete Rule")
	@Transactional
	public void deleteRuleTest() throws Exception {
		ItemList en = TestDataUtil.nextItemList(null, nextRule(null), null);
		repository.save(en);
		long ruleId = en.getRule().getId();

		final ItemListDTO dto = TestDataUtil.nextItemListDTO(en.getId(), false, false);

		mockMvc.perform(put("/api/list/")
				                .contentType(MediaType.APPLICATION_JSON)
				                .content(TestDataUtil.asJson(dto)))
		       .andExpect(status().isOk());

		en = repository.getReferenceById(en.getId());
		ruleRepository.flush();
		Optional<Rule> rule = ruleRepository.findById(ruleId);

		assertThat(rule.isEmpty(), is(true));
		assertThat(en.getRule(), is(nullValue()));
	}

	@Test
	@DisplayName("ItemController should delete")
	@Transactional
	public void deleteTest() throws Exception {
		ItemList en = TestDataUtil.nextItemList(null, nextRule(null), null);
		repository.save(en);

		mockMvc.perform(delete("/api/list/" + en.getId()))
		                          .andExpect(status().isOk());

		assertThat(repository.findById(en.getId()), is(Optional.empty()));
	}

	@Test
	@DisplayName("ItemController should read all")
	@Transactional
	public void readAllTest() throws Exception {
		repository.deleteAll(repository.findAll());
		List<ItemList> en = nextList(() -> nextItemList(null, nextRule(null), null), 10);
		repository.saveAll(en); // save all to get ids
		en.forEach(e -> e.setItems(nextList(() -> nextItem(null, e), 10)));

		MvcResult result = mockMvc.perform(get("/api/list/all"))
		                          .andExpect(status().isOk())
		                          .andReturn();

		String            json    = result.getResponse().getContentAsString();
		List<ItemListDTO> dtoList = TestDataUtil.asList(json, ItemListDTO.class);

		assertThat("List size is equal", dtoList.size(), is(en.size()));
		en.forEach(e -> {
			ItemListDTO dto = dtoList.stream()
			                         .filter(d -> d.getId().equals(e.getId()))
			                         .findFirst()
			                         .orElseThrow(()->new RuntimeException("ItemList not found"));
			assertThat(dto.getName(), is(e.getName()));
			assertThat(dto.getOrderInList(), is(e.getOrderInList()));
			assertThat(dto.getOrderInAgenda(), is(e.getOrderInAgenda()));
			assertThat("Rule is equal", isEqualWithoutId(e.getRule(), dto.getRule()));
			assertThat(dto.getShowFirst(), is(e.getShowFirst()));
			assertThat("Items are equal", areEqual(e.getItems(), dto.getItems(), TestDataUtil::isEqualWithoutId));
		});
	}
}
