package com.corn.planner.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDTO {
	private final Long id;

	private final String name;

	private final Integer orderInList;

	private final Integer orderInAgenda;

	private final Boolean done;

	private final RuleDTO rule;

	private final Long itemListId;

	@JsonCreator
	public ItemDTO(@JsonProperty("id")
			       Long id,
	               @JsonProperty("name")
	               String name,
	               @JsonProperty("orderInList")
	               Integer orderInList,
	               @JsonProperty("orderInAgenda")
	               Integer orderInAgenda,
	               @JsonProperty("done")
	               Boolean done,
	               @JsonProperty("rule")
	               RuleDTO rule,
	               @JsonProperty("itemListId")
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

	public Boolean getDone() {
		return done;
	}

	public RuleDTO getRule() {
		return rule;
	}

	public Long getItemListId() {
		return itemListId;
	}
}
