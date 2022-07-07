package com.corn.planner.dto;


public class ItemListDTO {
	private final Long id;

	private final String name;

	private final Integer orderInList;

	private final Integer orderInAgenda;

	private final Long ruleId;

	private final Integer showFirst;

	public ItemListDTO(Long id, String name, Integer orderInList, Integer orderInAgenda, Long ruleId, Integer showFirst) {
		this.id = id;
		this.name = name;
		this.orderInList = orderInList;
		this.orderInAgenda = orderInAgenda;
		this.ruleId = ruleId;
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

	public Long getRuleId() {
		return ruleId;
	}

	public Integer getShowFirst() {
		return showFirst;
	}
}
