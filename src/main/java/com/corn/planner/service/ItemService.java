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
import com.corn.planner.repository.ItemRepository;
import com.corn.planner.repository.RuleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemService {

	private final ItemRepository repo;
	private final ItemListRepository itemListRepository;

	private final RuleRepository ruleRepository;
	private final ItemMapper mapper;

	public ItemService(ItemRepository repo, ItemListRepository itemListRepository, RuleRepository ruleRepository, ItemMapper mapper) {
		this.repo = repo;
		this.itemListRepository = itemListRepository;
		this.ruleRepository = ruleRepository;
		this.mapper = mapper;
	}

	public long create(ItemDTO dto) {
		Item item = mapper.toEntity(dto);
		repo.save(item);
		return item.getId();
	}

	public ItemDTO read(long id) {
		Item item = repo.getReferenceById(id);
		return mapper.toDTO(item);
	}

	public List<ItemDTO> readAllByList(long itemListId) {
		ItemList itemList = itemListRepository.getReferenceById(itemListId);
		return repo.findAllByItemList(itemList)
		           .stream()
		           .map(mapper::toDTO)
		           .collect(Collectors.toList());
	}

	public void update(ItemDTO dto) {
		Item item = repo.getReferenceById(dto.getId());
		if (dto.getRule() == null && item.getRule() != null) {
			ruleRepository.delete(item.getRule());
		}
		mapper.updateItem(item, dto);
		repo.save(item);
	}

	public void delete(long id) {
		Item item = repo.getReferenceById(id);
		repo.delete(item);
	}
}
