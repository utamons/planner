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

import com.corn.planner.entity.TodoItem;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TodoItemDTO {
	final private Long id;
	final private String text;

	@JsonCreator
	public TodoItemDTO(
			@JsonProperty("id")
			Long id,
			@JsonProperty("text")
			String text) {
		this.id = id;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public static TodoItem toEntity(TodoItemDTO dto) {
		TodoItem item = new TodoItem();
		item.setId(dto.getId());
		item.setText(dto.getText());
		return item;
	}

	public static TodoItemDTO toDto(TodoItem item) {
		if (item == null)
			return null;
		return new TodoItemDTO(item.getId(), item.getText());
	}

	public static List<TodoItemDTO> toDtoList(List<TodoItem> items) {
		List<TodoItemDTO> result = new ArrayList<>();
		items.forEach((item -> result.add(toDto(item))));
		return result;
	}
}
