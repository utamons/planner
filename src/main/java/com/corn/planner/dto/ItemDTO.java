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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
@JsonDeserialize(builder = ItemDTO.ItemDTOBuilder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDTO {
	private final Long id;

	@NotNull
	private final String name;

	private final Integer orderInList;

	private final Integer orderInAgenda;

	private final Boolean done;

	@Valid
	private final RuleDTO rule;

	@NotNull
	private final Long itemListId;

	private ItemDTO(Long id,
	               String name,
	               Integer orderInList,
	               Integer orderInAgenda,
	               Boolean done,
	               RuleDTO rule,
	               Long itemListId) {
		this.id = id;
		this.name = name;
		this.orderInList = orderInList;
		this.orderInAgenda = orderInAgenda;
		this.done = done;
		this.rule = rule;
		this.itemListId = itemListId;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getOrderInList() {
		return orderInList;
	}

	public Integer getOrderInAgenda() {
		return orderInAgenda;
	}

	public Boolean isDone() {
		return done;
	}

	public RuleDTO getRule() {
		return rule;
	}

	public Long getItemListId() {
		return itemListId;
	}


	public static final class ItemDTOBuilder {
		private Long    id;
		private String  name;
		private Integer orderInList;
		private Integer orderInAgenda;
		private Boolean done;
		private RuleDTO rule;
		private Long    itemListId;

		private ItemDTOBuilder() {
		}

		public static ItemDTOBuilder anItemDTO() {
			return new ItemDTOBuilder();
		}

		public ItemDTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public ItemDTOBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public ItemDTOBuilder withOrderInList(Integer orderInList) {
			this.orderInList = orderInList;
			return this;
		}

		public ItemDTOBuilder withOrderInAgenda(Integer orderInAgenda) {
			this.orderInAgenda = orderInAgenda;
			return this;
		}

		public ItemDTOBuilder withDone(Boolean done) {
			this.done = done;
			return this;
		}

		public ItemDTOBuilder withRule(RuleDTO rule) {
			this.rule = rule;
			return this;
		}

		public ItemDTOBuilder withItemListId(Long itemListId) {
			this.itemListId = itemListId;
			return this;
		}

		public ItemDTO build() {
			return new ItemDTO(id, name, orderInList, orderInAgenda, done, rule, itemListId);
		}
	}
}
