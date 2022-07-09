package com.corn.planner.service;

import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.entity.ItemList;
import org.springframework.beans.BeanUtils;

public class ItemListMapper {

	public static ItemList toEntity(ItemListDTO dto) {
		ItemList result = new ItemList();
		BeanUtils.copyProperties(dto,result,"rule");
		result.setRule(RuleMapper.toEntity(dto.getRule()));
		return result;
	}

	public static ItemListDTO toDTO(ItemList entity) {
		return ItemListDTO.ItemListDTOBuilder.anItemListDTO()
		                                     .withId(entity.getId())
				.withName(entity.getName())
				.withOrderInList(entity.getOrderInList())
				.withOrderInAgenda(entity.getOrderInAgenda())
				.withRule(RuleMapper.toDTO(entity.getRule()))
				.withShowFirst(entity.getShowFirst())
				.build();
	}
}
