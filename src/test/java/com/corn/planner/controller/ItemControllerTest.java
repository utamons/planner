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

import com.corn.planner.dto.ItemDTO;
import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.entity.Item;
import com.corn.planner.entity.ItemList;
import com.corn.planner.entity.Rule;
import com.corn.planner.repository.ItemListRepository;
import com.corn.planner.repository.ItemRepository;
import com.corn.planner.repository.RuleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class ItemControllerTest {
	@Autowired
	private RuleRepository ruleRepository;

	@Autowired
	private ItemListRepository itemListRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ItemRepository itemRepository;

	@Test
	@DisplayName("ItemController should create")
	@Transactional
	public void createTest() throws Exception {
		ItemList itemList = nextItemList(null, nextRule(null), null);
		itemList = itemListRepository.save(itemList);
		ItemDTO dto = nextItemDTO(itemList.getId());

		MvcResult result = mockMvc.perform(post("/api/item")
				                                   .contentType(MediaType.APPLICATION_JSON)
				                                   .content(TestDataUtil.asJson(dto)))
		                          .andExpect(status().isCreated())
		                          .andReturn();

		long           id      = Long.parseLong(result.getResponse().getContentAsString());
		Optional<Item> itemOpt = itemRepository.findById(id);
		assertThat(itemOpt.isPresent(), is(true));

		Item item = itemOpt.get();
		assertThat(item.getName(), is(dto.getName()));
		assertThat(item.getOrderInList(), is(dto.getOrderInList()));
		assertThat(item.getOrderInAgenda(), is(dto.getOrderInAgenda()));
		assertThat(item.isDone(), is(dto.isDone()));
		assertThat(item.getItemList(), is(itemList));
		assertThat("Entity rule is not equal to dto rule", isEqualWithoutId(item.getRule(), dto.getRule()));
	}

	@Test
	@DisplayName("ItemController should read")
	@Transactional
	public void readTest() throws Exception {
		ItemList itemList = nextItemList(null, nextRule(null), null);
		itemList = itemListRepository.save(itemList);
		Item item = nextItem(null, itemList, nextRule(null));
		item = itemRepository.save(item);

		MvcResult result = mockMvc.perform(get("/api/item/" + item.getId())
				                                   .contentType(MediaType.APPLICATION_JSON))
		                          .andExpect(status().isOk())
		                          .andReturn();

		ItemDTO dto = TestDataUtil.asObject(result.getResponse().getContentAsString(), ItemDTO.class);
		assertThat(dto.getId(), is(item.getId()));
		assertThat(dto.getName(), is(item.getName()));
		assertThat(dto.getOrderInList(), is(item.getOrderInList()));
		assertThat(dto.getOrderInAgenda(), is(item.getOrderInAgenda()));
		assertThat(dto.isDone(), is(item.isDone()));
		assertThat(dto.getItemListId(), is(itemList.getId()));
		assertThat("Entity rule is not equal to dto rule", isEqualWithoutId(item.getRule(), dto.getRule()));
	}

	@Test
	@DisplayName("ItemController should read all")
	@Transactional
     public void readAllTest() throws Exception {
		itemRepository.deleteAll(itemRepository.findAll());
		itemListRepository.flush();
		ItemList itemList = nextItemList(null, nextRule(null), null);
		itemListRepository.save(itemList);
		itemListRepository.flush();
		List<Item> items = nextList(() -> nextItem(null, itemList, nextRule(null)), 1);
		itemList.setItems(items);
		itemListRepository.save(itemList);
		itemListRepository.flush();

		MvcResult result = mockMvc.perform(get("/api/item/all/"+itemList.getId())
				                                   .contentType(MediaType.APPLICATION_JSON))
		                          .andExpect(status().isOk())
		                          .andReturn();

		List<ItemDTO> dtoList = TestDataUtil.asList(result.getResponse().getContentAsString(), ItemDTO.class);
		assertThat(dtoList.size(), is(items.size()));
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			ItemDTO dto = dtoList.get(i);
			assertThat(dto.getId(), is(item.getId()));
			assertThat(dto.getName(), is(item.getName()));
			assertThat(dto.getOrderInList(), is(item.getOrderInList()));
			assertThat(dto.getOrderInAgenda(), is(item.getOrderInAgenda()));
			assertThat(dto.isDone(), is(item.isDone()));
			assertThat(dto.getItemListId(), is(itemList.getId()));
			assertThat("Entity rule is not equal to dto rule", isEqualWithoutId(item.getRule(), dto.getRule()));
		}
	}


	@Test
	@DisplayName("ItemController should update")
	@Transactional
	public void updateTest() throws Exception {
		ItemList itemList = nextItemList(null, nextRule(null), null);
		itemList = itemListRepository.save(itemList);
		Item en = nextItem(null, itemList, nextRule(null));
		en = itemRepository.save(en);

		ItemDTO dto = nextItemDTO(en.getId(), itemList.getId(), nextRuleDTO());

		mockMvc.perform(put("/api/item")
				                .contentType(MediaType.APPLICATION_JSON)
				                .content(TestDataUtil.asJson(dto)))
		       .andExpect(status().isOk());

		assertThat(en.getName(), is(dto.getName()));
		assertThat(en.getOrderInList(), is(dto.getOrderInList()));
		assertThat(en.getOrderInAgenda(), is(dto.getOrderInAgenda()));
		assertThat(en.isDone(), is(dto.isDone()));
		assertThat(en.getItemList(), is(itemList));
		assertThat("Entity rule is not equal to dto rule", isEqualWithoutId(en.getRule(), dto.getRule()));
	}

	@Test
	@DisplayName("ItemController should delete Rule on update")
	@Transactional
	public void updateTestDeleteRule() throws Exception {
		ItemList itemList = nextItemList(null, nextRule(null), null);
		itemList = itemListRepository.save(itemList);
		Item en = nextItem(null, itemList, nextRule(null));
		en = itemRepository.save(en);

		ItemDTO dto = nextItemDTO(en.getId(), itemList.getId(), null);

		mockMvc.perform(put("/api/item")
				                .contentType(MediaType.APPLICATION_JSON)
				                .content(TestDataUtil.asJson(dto)))
		       .andExpect(status().isOk());

		assertThat(en.getName(), is(dto.getName()));
		assertThat(en.getOrderInList(), is(dto.getOrderInList()));
		assertThat(en.getOrderInAgenda(), is(dto.getOrderInAgenda()));
		assertThat(en.isDone(), is(dto.isDone()));
		assertThat(en.getItemList(), is(itemList));
		assertThat(en.getRule(), is(nullValue()));
	}

	@Test
	@DisplayName("ItemController should delete")
	@Transactional
	public void deleteTest() throws Exception {
		ItemList itemList = nextItemList(null, nextRule(null), null);
		itemList = itemListRepository.save(itemList);
		Item en = nextItem(null, itemList, nextRule(null));
		en = itemRepository.save(en);
		long ruleId = en.getRule().getId();

		mockMvc.perform(delete("/api/item/" + en.getId())
				                .contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk());

		Optional<Item> itemOpt = itemRepository.findById(en.getId());
		assertThat(itemOpt.isPresent(), is(false));
		Optional<Rule> ruleOpt = ruleRepository.findById(ruleId);
		assertThat(ruleOpt.isPresent(), is(false));
	}

	@Test
	@DisplayName("ItemController should process EntityNotFoundException")
	public void entityNotFoundTest() throws Exception {
		mockMvc.perform(get("/api/item/{id}", Long.MAX_VALUE))
		       .andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("ItemController should process IllegalArgumentException")
	public void illegalArgumentTest() throws Exception {
		final ItemDTO dto = nextItemDTO(null, 1L, null);
		mockMvc.perform(put("/api/item")
				                .contentType(MediaType.APPLICATION_JSON)
				                .content(TestDataUtil.asJson(dto)))
		       .andExpect(status().isBadRequest());
	}

}
