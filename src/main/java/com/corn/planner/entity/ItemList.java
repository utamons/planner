package com.corn.planner.entity;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "item_list")
public class ItemList {

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

	@ManyToOne
	private Rule rule;

	@Column(name = "show_first")
	private Integer showFirst;

	@OneToMany
	private List<Item> items;

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

	public void setShowFirst(Integer showFirst) {
		this.showFirst = showFirst;
	}

	public Integer getShowFirst() {
		return showFirst;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}
}
