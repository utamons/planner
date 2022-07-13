package util;

import com.corn.planner.dto.ItemDTO;
import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.dto.RuleDTO;
import com.corn.planner.entity.Item;
import com.corn.planner.entity.ItemList;
import com.corn.planner.entity.Rule;
import liquibase.pro.packaged.I;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.*;

public class TestDataGenerator {

	public static RuleDTO nextRuleDTO() {
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
				.withCompletedAt(nextLocalDateTime())
				.withCreatedAt(nextLocalDateTime())
				.build();
	}

	public static Rule nextRule() {
		Rule rule = new Rule();

		rule.setId(nextLong());
		rule.setCreatedAt(nextLocalDateTime());
		rule.setCompletedAt(nextLocalDateTime());
		rule.setShowAtHour(nextInt());
		rule.setHideAtHour(nextInt());
		rule.setShowAtMinute(nextInt());
		rule.setHideAtMinute(nextInt());
		rule.setRepeatType(randomAlphabetic(10));
		rule.setEvery(nextInt());
		rule.setSun(nextBoolean());
		rule.setMon(nextBoolean());
		rule.setTue(nextBoolean());
		rule.setWed(nextBoolean());
		rule.setThu(nextBoolean());
		rule.setFri(nextBoolean());
		rule.setSat(nextBoolean());
		rule.setOnDayOfMonth(nextInt());
		rule.setOnDayOfMonthWeek(nextInt());
		rule.setOnMonth(nextInt());

		return rule;
	}

	public static LocalDateTime nextLocalDateTime() {
		return LocalDateTime.ofEpochSecond(nextLong(0,365241780471L), nextInt(0,999999999), ZoneOffset.UTC);
	}

	public static ItemListDTO nextItemListDTO() {
		return ItemListDTO.ItemListDTOBuilder
				.anItemListDTO()
				.withShowFirst(nextInt())
				.withRule(nextRuleDTO())
				.withOrderInAgenda(nextInt())
				.withOrderInList(nextInt())
				.withName(randomAlphabetic(10))
				.build();
	}

	public static ItemList nextItemList() {
		ItemList itemList = new ItemList();

		itemList.setId(nextLong());
		itemList.setName(randomAlphabetic(10));
		itemList.setOrderInList(nextInt());
		itemList.setOrderInAgenda(nextInt());
		itemList.setRule(nextRule());
		itemList.setShowFirst(nextInt());
		itemList.setItems(nextList(() -> {
			Item item = nextItem();
			item.setItemList(itemList);
			return item;
		}, 10));
		return itemList;
	}

	public static Item nextItem() {
		Item item = new Item();
		item.setId(nextLong());
		item.setName(randomAlphabetic(10));
		item.setOrderInAgenda(nextInt());
		item.setOrderInList(nextInt());
		item.setDone(nextBoolean());
		item.setRule(nextRule());
		return item;
	}

	public static ItemDTO nextItemDTO() {
		return ItemDTO.ItemDTOBuilder
				.anItemDTO()
				.withId(nextLong())
				.withName(randomAlphabetic(10))
				.withOrderInList(nextInt())
				.withOrderInAgenda(nextInt())
				.withDone(nextBoolean())
				.withRule(nextRuleDTO())
				.withItemListId(nextLong())
				.build();
	}

	public static <T> List<T> nextList(Supplier<T> supplier, int size) {
		List<T> result = new ArrayList<>();
		for (int i=0; i<size; ++i) {
			result.add(supplier.get());
		}
		return result;
	}
}
