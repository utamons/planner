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
import org.springframework.beans.BeanUtils;

public interface RuleMapper {

	static Rule toEntity(RuleDTO dto) {
		if (dto == null)
			return null;
		Rule result = new Rule();
		BeanUtils.copyProperties(dto, result);
		return result;
	}

	static RuleDTO toDTO(Rule entity) {
		if (entity == null)
			return null;
		return RuleDTO.RuleDTOBuilder.aRuleDTO()
		                             .withId(entity.getId())
		                             .withCreatedAt(entity.getCreatedAt())
		                             .withCompletedAt(entity.getCompletedAt())
		                             .withShowAtHour(entity.getShowAtHour())
		                             .withHideAtHour(entity.getHideAtHour())
		                             .withShowAtMinute(entity.getShowAtMinute())
		                             .withHideAtMinute(entity.getHideAtMinute())
		                             .withRepeatType(entity.getRepeatType())
		                             .withEvery(entity.getEvery())
		                             .withSun(entity.isSun())
		                             .withMon(entity.isMon())
		                             .withTue(entity.isTue())
		                             .withWed(entity.isWed())
		                             .withThu(entity.isThu())
		                             .withFri(entity.isFri())
		                             .withSat(entity.isSat())
		                             .withOnDayOfMonth(entity.getOnDayOfMonth())
		                             .withOnDayOfMonthWeek(entity.getOnDayOfMonthWeek())
		                             .withOnDayWeek(entity.getOnDayWeek())
		                             .withOnMonth(entity.getOnMonth())
		                             .build();
	}
	static void updateRule(Rule rule, RuleDTO ruleDTO) {
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
		rule.setCompletedAt(ruleDTO.getCompletedAt());
	}
}
