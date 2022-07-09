package com.corn.planner.service;

import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.entity.ItemList;
import com.corn.planner.entity.Rule;
import com.corn.planner.repository.ItemListRepository;
import com.corn.planner.repository.RuleRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemListService {

	private final ItemListRepository repo;
	private final RuleRepository     ruleRepo;

	public ItemListService(ItemListRepository repository, RuleRepository ruleRepository) {
		this.repo = repository;
		this.ruleRepo = ruleRepository;
	}

	public long create(ItemListDTO dto) {
		ItemList itemList = ItemListMapper.toEntity(dto);
		Rule     rule     = itemList.getRule();
		if (rule != null) {
			rule = ruleRepo.save(rule);
		}
		itemList = repo.save(itemList);
		itemList.setRule(rule);
		repo.save(itemList);
		return itemList.getId();
	}
}
