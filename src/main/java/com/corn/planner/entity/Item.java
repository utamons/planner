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
package com.corn.planner.entity;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

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

	@ManyToOne(cascade={PERSIST, REMOVE})
	private Rule rule;

	@ManyToOne(cascade={PERSIST, REFRESH})
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
