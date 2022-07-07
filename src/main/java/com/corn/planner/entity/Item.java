package com.corn.planner.entity;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "item")
public class Item {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "order_in_list", nullable = false)
	private Integer orderInList;

	@Column(name = "order_in_agenda")
	private Integer orderInAgenda;

	@Column(name = "done")
	private Boolean done;

	@ManyToOne
	private Rule rule;

	@ManyToOne
	private ItemList itemList;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setOrderInList(Integer orderInList) {
		this.orderInList = orderInList;
	}

	public Integer getOrderInList() {
		return orderInList;
	}

	public void setOrderInAgenda(Integer orderInAgenda) {
		this.orderInAgenda = orderInAgenda;
	}

	public Integer getOrderInAgenda() {
		return orderInAgenda;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Boolean isDone() {
		return done;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public ItemList getItemList() {
		return itemList;
	}

	public void setItemList(ItemList itemList) {
		this.itemList = itemList;
	}
}
