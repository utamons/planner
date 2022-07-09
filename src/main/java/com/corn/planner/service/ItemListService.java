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
