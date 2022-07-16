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

	public static ItemList toEntity(ItemListDTO dto) {
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
