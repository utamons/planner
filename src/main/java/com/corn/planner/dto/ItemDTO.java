package com.corn.planner.dto;


public class ItemDTO {
	private final Long id;

	private final String name;

	private final Integer orderInList;

	private final Integer orderInAgenda;

	private final Boolean done;

	private final Long ruleId;

	private final Long itemListId;

	public ItemDTO(Long id,
	               String name,
	               Integer orderInList,
	               Integer orderInAgenda,
	               Boolean done,
	               Long ruleId,
	               Long itemListId) {
		this.id = id;
		this.name = name;
		this.orderInList = orderInList;
		this.orderInAgenda = orderInAgenda;
		this.done = done;
		this.ruleId = ruleId;
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

	public Long getRuleId() {
		return ruleId;
	}

	public Long getItemListId() {
		return itemListId;
	}
}
