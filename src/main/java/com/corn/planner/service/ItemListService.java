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
import com.corn.planner.dto.RuleDTO;
import com.corn.planner.entity.ItemList;
import com.corn.planner.entity.Rule;
import com.corn.planner.repository.ItemListRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemListService {

	private final ItemListRepository repo;
	private final ItemListMapper     mapper;

	public ItemListService(ItemListRepository repository, ItemListMapper mapper) {
		this.repo = repository;
		this.mapper = mapper;
	}

	public long create(ItemListDTO dto) {
		ItemList itemList = mapper.toEntity(dto);
		itemList = repo.save(itemList);
		return itemList.getId();
	}

	public ItemListDTO read(long id) {
		ItemList itemList = repo.getReferenceById(id);
		return mapper.toDTO(itemList);
	}

	public List<ItemListDTO> readAll() {
		return repo.findAll()
		           .stream()
		           .map(mapper::toDTO)
		           .collect(Collectors.toList());
	}

	public void update(ItemListDTO dto) {
		ItemList itemList = repo.getReferenceById(dto.getId());
		itemList.setName(dto.getName());
		itemList.setOrderInList(dto.getOrderInList());
		itemList.setOrderInAgenda(dto.getOrderInAgenda());
		itemList.setShowFirst(dto.getShowFirst());

		if (dto.getRule() == null) {
			itemList.setRule(null);
		} else {
			Rule    rule    = itemList.getRule();
			RuleDTO ruleDTO = dto.getRule();
			rule.setShowAtHour(ruleDTO.getShowAtHour());
			rule.setHideAtHour(ruleDTO.getHideAtHour());
			rule.setShowAtMinute(ruleDTO.getShowAtMinute());
			rule.setHideAtMinute(ruleDTO.getHideAtMinute());
			rule.setRepeatType(ruleDTO.getRepeatType());
			rule.setEvery(ruleDTO.getEvery());
			rule.setSun(ruleDTO.getSun());
			rule.setMon(ruleDTO.getMon());
			rule.setTue(ruleDTO.getTue());
			rule.setWed(ruleDTO.getWed());
			rule.setThu(ruleDTO.getThu());
			rule.setFri(ruleDTO.getFri());
			rule.setSat(ruleDTO.getSat());
			rule.setOnDayOfMonth(ruleDTO.getOnDayOfMonth());
			rule.setOnDayOfMonthWeek(ruleDTO.getOnDayOfMonthWeek());
			rule.setOnDayWeek(ruleDTO.getOnDayWeek());
			rule.setOnMonth(ruleDTO.getOnMonth());
		}

		repo.save(itemList);
	}

	public void delete(long id) {
		ItemList itemList = repo.getReferenceById(id);
		repo.delete(itemList);
	}
}
