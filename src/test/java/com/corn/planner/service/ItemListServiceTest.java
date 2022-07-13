package com.corn.planner.service;

import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.dto.RuleDTO;
import com.corn.planner.entity.ItemList;
import com.corn.planner.entity.Rule;
import com.corn.planner.repository.ItemListRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import util.PlannerTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.TestDataGenerator.nextItemListDTO;

@PlannerTest
@Transactional
public class ItemListServiceTest {

	@Autowired
	private ItemListRepository repo;

	@Autowired
	private ItemListService service;

	@Test
	@DisplayName("ItemList should be created")
	public void createTest() {
		ItemListDTO dto = nextItemListDTO();
		long id = service.create(dto);
		assertThat(id,is(notNullValue()));

		ItemList entity = repo.getReferenceById(id);

		assertThat(entity.getOrderInList(), equalTo(dto.getOrderInList()));
		assertThat(entity.getName(), equalTo(dto.getName()));
		assertThat(entity.getOrderInAgenda(), equalTo(entity.getOrderInAgenda()));
		assertThat(entity.getShowFirst(), equalTo(dto.getShowFirst()));

		assertThat(entity.getRule(), is(notNullValue()));

		Rule rule = entity.getRule();
		RuleDTO ruleDTO = dto.getRule();

		assertThat(rule.getId(), is(notNullValue()));
		assertThat(rule.getCreatedAt(),equalTo(ruleDTO.getCreatedAt()));
		assertThat(rule.getCompletedAt(),equalTo(ruleDTO.getCompletedAt()));
		assertThat(rule.getEvery(),equalTo(ruleDTO.getEvery()));
		assertThat(rule.getHideAtHour(), equalTo(ruleDTO.getHideAtHour()));
		assertThat(rule.getHideAtMinute(), equalTo(ruleDTO.getHideAtMinute()));
		assertThat(rule.getOnDayOfMonth(),equalTo(ruleDTO.getOnDayOfMonth()));
		assertThat(rule.getOnDayOfMonthWeek(),equalTo(ruleDTO.getOnDayOfMonthWeek()));
		assertThat(rule.getOnDayWeek(),equalTo(ruleDTO.getOnDayWeek()));
		assertThat(rule.getOnMonth(),equalTo(ruleDTO.getOnMonth()));
		assertThat(rule.getRepeatType(),equalTo(ruleDTO.getRepeatType()));
		assertThat(rule.getShowAtHour(),equalTo(ruleDTO.getShowAtHour()));
		assertThat(rule.getShowAtMinute(),equalTo(ruleDTO.getShowAtMinute()));
		assertThat(rule.isFri(),equalTo(ruleDTO.getFri()));
		assertThat(rule.isMon(),equalTo(ruleDTO.getMon()));
		assertThat(rule.isSat(),equalTo(ruleDTO.getSat()));
		assertThat(rule.isSun(),equalTo(ruleDTO.getSun()));
		assertThat(rule.isThu(),equalTo(ruleDTO.getThu()));
		assertThat(rule.isTue(),equalTo(ruleDTO.getTue()));
		assertThat(rule.isWed(),equalTo(ruleDTO.getWed()));
	}
}
