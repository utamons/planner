package com.corn.planner.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemListDTO {
	private final Long id;

	private final String name;

	private final Integer orderInList;

	private final Integer orderInAgenda;

	private final RuleDTO rule;

	private final Integer showFirst;

	@JsonCreator
	public ItemListDTO(@JsonProperty("id")
	                   Long id,
	                   @JsonProperty("name")
	                   String name,
	                   @JsonProperty("orderInList")
	                   Integer orderInList,
	                   @JsonProperty("orderInAgenda")
	                   Integer orderInAgenda,
	                   @JsonProperty("rule")
	                   RuleDTO rule,
	                   @JsonProperty("showFirst")
	                   Integer showFirst) {
		this.id = id;
		this.name = name;
		this.orderInList = orderInList;
		this.orderInAgenda = orderInAgenda;
		this.rule = rule;
		this.showFirst = showFirst;
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
}
