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

import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.entity.ItemList;
import com.corn.planner.repository.ItemListRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemListService {

	private final ItemListRepository repo;

	public ItemListService(ItemListRepository repository) {
		this.repo = repository;
	}

	public long create(ItemListDTO dto) {
		ItemList itemList = ItemListMapper.toEntity(dto);
		itemList = repo.save(itemList);
		return itemList.getId();
	}
}
