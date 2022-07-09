package com.corn.planner.service;

import com.corn.planner.dto.RuleDTO;
import com.corn.planner.entity.Rule;
import org.springframework.beans.BeanUtils;

public class RuleMapper {

	public static Rule toEntity(RuleDTO dto) {
		if (dto == null)
			return null;
		Rule result = new Rule();
		BeanUtils.copyProperties(dto, result);
		return result;
	}

	public static RuleDTO toDTO(Rule entity) {
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

}
