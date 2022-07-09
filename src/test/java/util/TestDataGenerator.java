package util;

import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.dto.RuleDTO;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.*;

public class TestDataGenerator {

	public static RuleDTO ruleDTO() {
		return RuleDTO.RuleDTOBuilder
				.aRuleDTO()
				.withOnMonth(nextInt())
				.withOnDayWeek(nextInt())
				.withOnDayOfMonthWeek(nextInt())
				.withOnDayOfMonth(nextInt())
				.withSat(nextBoolean())
				.withFri(nextBoolean())
				.withThu(nextBoolean())
				.withWed(nextBoolean())
				.withTue(nextBoolean())
				.withMon(nextBoolean())
				.withSun(nextBoolean())
				.withEvery(nextInt())
				.withRepeatType(randomAlphabetic(7))
				.withHideAtMinute(nextInt())
				.withShowAtMinute(nextInt())
				.withHideAtHour(nextInt())
				.withShowAtHour(nextInt())
				.withCompletedAt(LocalDateTime.now())
				.withCreatedAt(LocalDateTime.now())
				.build();
	}

	public static ItemListDTO itemListDTO() {
		return ItemListDTO.ItemListDTOBuilder
				.anItemListDTO()
				.withShowFirst(nextInt())
				.withRule(ruleDTO())
				.withOrderInAgenda(nextInt())
				.withOrderInList(nextInt())
				.withName(randomAlphabetic(10))
				.build();
	}
}
