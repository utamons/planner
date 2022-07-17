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
package util;

import com.corn.planner.dto.ItemDTO;
import com.corn.planner.dto.ItemListDTO;
import com.corn.planner.dto.RuleDTO;
import com.corn.planner.entity.Item;
import com.corn.planner.entity.ItemList;
import com.corn.planner.entity.Rule;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.*;

public class TestDataUtil {

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
		rule.setRepeatType(randomAlphabetic(7));
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

	public static Rule nextRule(Long id) {
		Rule rule = nextRule();
		rule.setId(id);
		return rule;
	}

	public static LocalDateTime nextLocalDateTime() {
		return LocalDateTime.ofEpochSecond(nextLong(0, 1689497565L), nextInt(0, 999999999), ZoneOffset.UTC);
	}

	public static ItemListDTO nextItemListDTO(Long id, boolean withItems) {
		return ItemListDTO.ItemListDTOBuilder
				.anItemListDTO()
				.withId(id)
				.withShowFirst(nextInt())
				.withRule(nextRuleDTO())
				.withOrderInAgenda(nextInt())
				.withOrderInList(nextInt())
				.withName(randomAlphabetic(10))
				.withItems(withItems ? nextList(() -> nextItemDTO(id), 10) : null)
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
		itemList.setItems(nextList(() -> nextItem(itemList), 10));
		return itemList;
	}

	public static ItemList nextItemList(Long  id, Rule rule, List<Item> items) {
		ItemList itemList = nextItemList();
		itemList.setId(id);
		itemList.setRule(rule);
		itemList.setItems(items);
		return itemList;
	}

	public static Item nextItem(ItemList itemList) {
		Item item = new Item();
		item.setId(nextLong());
		item.setName(randomAlphabetic(10));
		item.setOrderInAgenda(nextInt());
		item.setOrderInList(nextInt());
		item.setDone(nextBoolean());
		item.setRule(nextRule());
		item.setItemList(itemList);
		return item;
	}

	public static ItemDTO nextItemDTO(long itemListId) {
		return ItemDTO.ItemDTOBuilder
				.anItemDTO()
				.withId(nextLong())
				.withName(randomAlphabetic(10))
				.withOrderInList(nextInt())
				.withOrderInAgenda(nextInt())
				.withDone(nextBoolean())
				.withRule(nextRuleDTO())
				.withItemListId(itemListId)
				.build();
	}

	public static <T> List<T> nextList(Supplier<T> supplier, int size) {
		List<T> result = new ArrayList<>();
		for (int i = 0; i < size; ++i) {
			result.add(supplier.get());
		}
		return result;
	}

	public static <T, U> boolean areEqual(List<T> tList, List<U> uList, BiFunction<T, U, Boolean> biEqual) {
		return tList.stream()
		            .map(t -> uList.stream().filter((u -> biEqual.apply(t, u))).findFirst())
		            .noneMatch(Optional::isEmpty);
	}

	public static boolean isEqual(Item item, ItemDTO dto) {
		return Objects.equals(item.getId(), dto.getId()) &&
		       Objects.equals(item.getName(), dto.getName()) &&
		       Objects.equals(item.getOrderInList(), dto.getOrderInList()) &&
		       Objects.equals(item.getOrderInAgenda(), dto.getOrderInAgenda()) &&
		       Objects.equals(item.isDone(), dto.getDone()) &&
		       isEqual(item.getRule(), dto.getRule()) &&
		       Objects.equals(item.getItemList().getId(), dto.getItemListId());
	}

	public static boolean isEqual(Rule rule, RuleDTO ruleDTO) {
		return
				Objects.equals(rule.getId(), ruleDTO.getId()) &&
				Objects.equals(rule.getCreatedAt(), ruleDTO.getCreatedAt()) &&
				Objects.equals(rule.getCompletedAt(), ruleDTO.getCompletedAt()) &&
				Objects.equals(rule.getShowAtHour(), ruleDTO.getShowAtHour()) &&
				Objects.equals(rule.getHideAtHour(), ruleDTO.getHideAtHour()) &&
				Objects.equals(rule.getShowAtMinute(), ruleDTO.getShowAtMinute()) &&
				Objects.equals(rule.getHideAtMinute(), ruleDTO.getHideAtMinute()) &&
				Objects.equals(rule.getRepeatType(), ruleDTO.getRepeatType()) &&
				Objects.equals(rule.getEvery(), ruleDTO.getEvery()) &&
				Objects.equals(rule.isSun(), ruleDTO.getSun()) &&
				Objects.equals(rule.isMon(), ruleDTO.getMon()) &&
				Objects.equals(rule.isTue(), ruleDTO.getTue()) &&
				Objects.equals(rule.isWed(), ruleDTO.getWed()) &&
				Objects.equals(rule.isThu(), ruleDTO.getThu()) &&
				Objects.equals(rule.isFri(), ruleDTO.getFri()) &&
				Objects.equals(rule.isSat(), ruleDTO.getSat()) &&
				Objects.equals(rule.getOnDayOfMonth(), ruleDTO.getOnDayOfMonth()) &&
				Objects.equals(rule.getOnDayOfMonthWeek(), ruleDTO.getOnDayOfMonthWeek()) &&
				Objects.equals(rule.getOnDayWeek(), ruleDTO.getOnDayWeek()) &&
				Objects.equals(rule.getOnMonth(), ruleDTO.getOnMonth());
	}
}
