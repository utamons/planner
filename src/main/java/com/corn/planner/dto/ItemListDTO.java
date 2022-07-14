package com.corn.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@SuppressWarnings("unused")
@JsonDeserialize(builder = ItemListDTO.ItemListDTOBuilder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemListDTO {
	private final Long id;

	private final String name;

	private final Integer orderInList;

	private final Integer orderInAgenda;

	private final RuleDTO rule;

	private final Integer showFirst;

	private final List<ItemDTO> items;

	private ItemListDTO(Long id,
	                    String name,
	                    Integer orderInList,
	                    Integer orderInAgenda,
	                    RuleDTO rule,
	                    Integer showFirst, List<ItemDTO> items) {
		this.id = id;
		this.name = name;
		this.orderInList = orderInList;
		this.orderInAgenda = orderInAgenda;
		this.rule = rule;
		this.showFirst = showFirst;
		this.items = items;
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

	public RuleDTO getRule() {
		return rule;
	}

	public Integer getShowFirst() {
		return showFirst;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public static final class ItemListDTOBuilder {
		private Long    id;
		private String  name;
		private Integer orderInList;
		private Integer orderInAgenda;
		private RuleDTO rule;
		private Integer showFirst;

		private List<ItemDTO> items;

		private ItemListDTOBuilder() {
		}

		public static ItemListDTOBuilder anItemListDTO() {
			return new ItemListDTOBuilder();
		}

		public ItemListDTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public ItemListDTOBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public ItemListDTOBuilder withOrderInList(Integer orderInList) {
			this.orderInList = orderInList;
			return this;
		}

		public ItemListDTOBuilder withOrderInAgenda(Integer orderInAgenda) {
			this.orderInAgenda = orderInAgenda;
			return this;
		}

		public ItemListDTOBuilder withRule(RuleDTO rule) {
			this.rule = rule;
			return this;
		}

		public ItemListDTOBuilder withShowFirst(Integer showFirst) {
			this.showFirst = showFirst;
			return this;
		}

		public ItemListDTOBuilder withItems(List<ItemDTO> items) {
			this.items = items;
			return this;
		}

		public ItemListDTO build() {
			return new ItemListDTO(id, name, orderInList, orderInAgenda, rule, showFirst, items);
		}
	}
}
