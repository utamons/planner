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
