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
import com.corn.planner.entity.ItemList;
import com.corn.planner.repository.ItemListRepository;
import com.corn.planner.service.ItemListService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import util.TestDataUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.TestDataUtil.isEqual;
import static util.TestDataUtil.isEqualWithoutId;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemListControllerTest {
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
				                                   .content(TestDataUtil.asJsonString(dto)))
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

}
