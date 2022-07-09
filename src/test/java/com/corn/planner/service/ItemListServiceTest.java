package com.corn.planner.service;

import com.corn.planner.repository.ItemListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.PlannerTest;

@PlannerTest
public class ItemListServiceTest {

	@Autowired
	private ItemListRepository repo;

	@Autowired
	private ItemListService service;

	@Test
	@DisplayName("Error should build")
	public void createTest() {

	}
}
