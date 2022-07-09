package com.corn.planner.service;

import com.corn.planner.dto.RuleDTO;
import com.corn.planner.entity.Rule;
import org.springframework.beans.BeanUtils;

public class RuleMapper {

	public static Rule toEntity(RuleDTO dto) {
		if (dto == null) return null;
		Rule result = new Rule();
		BeanUtils.copyProperties(dto, result);
		return result;
	}

	public static RuleDTO toDTO(Rule entity) {
		if (entity == null) return null;
		return new RuleDTO(
				entity.getId(),
				entity.getCreatedAt(),
				entity.getCompletedAt(),
				entity.getShowAtHour(),
				entity.getHideAtHour(),
				entity.getShowAtMinute(),
				entity.getHideAtMinute(),
				entity.getRepeatType(),
				entity.getEvery(),
				entity.isSun(),
				entity.isMon(),
				entity.isTue(),
				entity.isWed(),
				entity.isThu(),
				entity.isFri(),
				entity.isSat(),
				entity.getOnDayOfMonth(),
				entity.getOnDayOfMonthWeek(),
				entity.getOnDayWeek(),
				entity.getOnMonth()
		);
	}

}
