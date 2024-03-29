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

import com.corn.planner.dto.RuleDTO;
import com.corn.planner.entity.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PlannerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static util.TestDataUtil.nextRule;
import static util.TestDataUtil.nextRuleDTO;

@PlannerTest
public class RuleMapperTest {

	@Test
	@DisplayName("RuleDTO should be converted to entity")
	public void toEntityTest() {
		RuleDTO dto = nextRuleDTO();
		Rule    rule = RuleMapper.toEntity(dto);

		assertThat(rule.getId(), is(dto.getId()));
		assertThat(rule.getCreatedAt(),is(dto.getCreatedAt()));
		assertThat(rule.getCompletedAt(),is(dto.getCompletedAt()));
		assertThat(rule.getShowAtHour(),is(dto.getShowAtHour()));
		assertThat(rule.getHideAtHour(),is(dto.getHideAtHour()));
		assertThat(rule.getShowAtMinute(),is(dto.getShowAtMinute()));
		assertThat(rule.getHideAtMinute(),is(dto.getHideAtMinute()));
		assertThat(rule.getRepeatType(),is(rule.getRepeatType()));
		assertThat(rule.getEvery(),is(dto.getEvery()));
		assertThat(rule.isSun(),is(dto.getSun()));
		assertThat(rule.isMon(),is(dto.getMon()));
		assertThat(rule.isTue(),is(dto.getTue()));
		assertThat(rule.isWed(),is(dto.getWed()));
		assertThat(rule.isThu(),is(dto.getThu()));
		assertThat(rule.isSat(),is(dto.getSat()));
		assertThat(rule.getOnDayOfMonth(),is(dto.getOnDayOfMonth()));
		assertThat(rule.getOnDayOfMonthWeek(),is(dto.getOnDayOfMonthWeek()));
		assertThat(rule.getOnDayWeek(),is(dto.getOnDayWeek()));
		assertThat(rule.getOnMonth(),is(dto.getOnMonth()));
	}

	@Test
	@DisplayName("Rule entity should be converted to DTO")
	public void toDTOTest() {
		Rule    rule = nextRule();
		RuleDTO dto = RuleMapper.toDTO(rule);

		assertThat(dto.getId(), is(rule.getId()));
		assertThat(dto.getCreatedAt(),is(rule.getCreatedAt()));
		assertThat(dto.getCompletedAt(),is(rule.getCompletedAt()));
		assertThat(dto.getShowAtHour(),is(rule.getShowAtHour()));
		assertThat(dto.getHideAtHour(),is(rule.getHideAtHour()));
		assertThat(dto.getShowAtMinute(),is(rule.getShowAtMinute()));
		assertThat(dto.getHideAtMinute(),is(rule.getHideAtMinute()));
		assertThat(dto.getRepeatType(),is(rule.getRepeatType()));
		assertThat(dto.getEvery(),is(rule.getEvery()));
		assertThat(dto.getSun(),is(rule.isSun()));
		assertThat(dto.getMon(),is(rule.isMon()));
		assertThat(dto.getTue(),is(rule.isTue()));
		assertThat(dto.getWed(),is(rule.isWed()));
		assertThat(dto.getThu(),is(rule.isThu()));
		assertThat(dto.getSat(),is(rule.isSat()));
		assertThat(dto.getOnDayOfMonth(),is(rule.getOnDayOfMonth()));
		assertThat(dto.getOnDayOfMonthWeek(),is(rule.getOnDayOfMonthWeek()));
		assertThat(dto.getOnDayWeek(),is(rule.getOnDayWeek()));
		assertThat(dto.getOnMonth(),is(rule.getOnMonth()));
	}

	@Test
	@DisplayName("Null entity should be converted to null")
	public void toNullDTOTest() {
		assertThat(RuleMapper.toDTO(null), is(nullValue()));
	}

	@Test
	@DisplayName("Null dto should be converted to null")
	public void toNullEntityTest() {
		assertThat(RuleMapper.toEntity(null), is(nullValue()));
	}

}
