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
package com.corn.planner.service;

import com.corn.planner.dto.ItemDTO;
import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.entity.ItemList;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemListMapper {

	private final ItemMapper itemMapper;

	public ItemListMapper(ItemMapper itemMapper) {
		this.itemMapper = itemMapper;
	}

	public ItemList toEntity(ItemListDTO dto) {
		ItemList result = new ItemList();
		BeanUtils.copyProperties(dto,result,"rule");
		result.setRule(RuleMapper.toEntity(dto.getRule()));
		return result;
	}

	public ItemListDTO toDTO(ItemList entity) {
		List<ItemDTO> itemDTOList = null;
		if (entity.getItems() != null) {
			itemDTOList = entity.getItems().stream().map(itemMapper::toDTO).collect(Collectors.toList());
		}

		return ItemListDTO.ItemListDTOBuilder.anItemListDTO()
		                                     .withId(entity.getId())
				.withName(entity.getName())
				.withOrderInList(entity.getOrderInList())
				.withOrderInAgenda(entity.getOrderInAgenda())
				.withRule(RuleMapper.toDTO(entity.getRule()))
				.withShowFirst(entity.getShowFirst())
				.withItems(itemDTOList)
				.build();
	}
}
