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
import com.corn.planner.entity.Item;
import com.corn.planner.entity.ItemList;
import com.corn.planner.repository.ItemListRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

	private final ItemListRepository repo;

	public ItemMapper(ItemListRepository repo) {
		this.repo = repo;
	}

	public Item toEntity(ItemDTO dto) {
		ItemList itemList = repo.getReferenceById(dto.getItemListId());
		Item item = new Item();
		item.setName(dto.getName());
		item.setOrderInList(dto.getOrderInList());
		item.setOrderInAgenda(dto.getOrderInAgenda());
		item.setDone(dto.getDone());
		item.setRule(RuleMapper.toEntity(dto.getRule()));
		item.setItemList(itemList);
		return item;
	}

	public void updateItem(Item item, ItemDTO dto) {
		ItemList itemList = repo.getReferenceById(dto.getItemListId());
		item.setName(dto.getName());
		item.setOrderInList(dto.getOrderInList());
		item.setOrderInAgenda(dto.getOrderInAgenda());
		item.setDone(dto.getDone());
		if (dto.getRule() == null)
			item.setRule(null);
		else
			RuleMapper.updateRule(item.getRule(), dto.getRule());
		item.setItemList(itemList);
	}

	public ItemDTO toDTO(Item en) {
		return ItemDTO.ItemDTOBuilder
				.anItemDTO()
				.withId(en.getId())
				.withName(en.getName())
				.withOrderInList(en.getOrderInList())
				.withOrderInAgenda(en.getOrderInAgenda())
				.withDone(en.isDone())
				.withRule(RuleMapper.toDTO(en.getRule()))
				.withItemListId(en.getItemList().getId())
				.build();
	}
}
