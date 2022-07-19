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
package com.corn.planner.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@JsonDeserialize(builder = RuleDTO.RuleDTOBuilder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RuleDTO {
	private final Long id;

	private final LocalDateTime createdAt;

	private final LocalDateTime completedAt;

	@Min(0)
	@Max(23)
	private final Integer showAtHour;

	@Min(0)
	@Max(23)
	private final Integer hideAtHour;

	@Min(0)
	@Max(60)
	private final Integer showAtMinute;


	@Min(0)
	@Max(60)
	private final Integer hideAtMinute;

	@Size(min = 5, max = 7)
	private final String repeatType;

	@Min(1)
	@Max(365)
	private final Integer every;

	private final Boolean sun;

	private final Boolean mon;

	private final Boolean tue;

	private final Boolean wed;

	private final Boolean thu;

	private final Boolean fri;

	private final Boolean sat;

	@Min(1)
	@Max(31)
	private final Integer onDayOfMonth;

	@Min(1)
	@Max(7)
	private final Integer onDayOfMonthWeek;

	@Min(1)
	@Max(7)
	private final Integer onDayWeek;

	@Min(1)
	@Max(12)
	private final Integer onMonth;

	private RuleDTO(@JsonProperty("id")
	               Long id,
	               LocalDateTime createdAt,
	               LocalDateTime completedAt,
	               Integer showAtHour,
	               Integer hideAtHour,
	               Integer showAtMinute,
	               Integer hideAtMinute,
	               String repeatType,
	               Integer every,
	               Boolean sun,
	               Boolean mon,
	               Boolean tue,
	               Boolean wed,
	               Boolean thu,
	               Boolean fri,
	               Boolean sat,
	               Integer onDayOfMonth,
	               Integer onDayOfMonthWeek,
	               Integer onDayWeek,
	               Integer onMonth) {
		this.id = id;
		this.createdAt = createdAt;
		this.completedAt = completedAt;
		this.showAtHour = showAtHour;
		this.hideAtHour = hideAtHour;
		this.showAtMinute = showAtMinute;
		this.hideAtMinute = hideAtMinute;
		this.repeatType = repeatType;
		this.every = every;
		this.sun = sun;
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
		this.onDayOfMonth = onDayOfMonth;
		this.onDayOfMonthWeek = onDayOfMonthWeek;
		this.onDayWeek = onDayWeek;
		this.onMonth = onMonth;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public Integer getShowAtHour() {
		return showAtHour;
	}

	public Integer getHideAtHour() {
		return hideAtHour;
	}

	public Integer getShowAtMinute() {
		return showAtMinute;
	}

	public Integer getHideAtMinute() {
		return hideAtMinute;
	}

	public String getRepeatType() {
		return repeatType;
	}

	public Integer getEvery() {
		return every;
	}

	public Boolean getSun() {
		return sun;
	}

	public Boolean getMon() {
		return mon;
	}

	public Boolean getTue() {
		return tue;
	}

	public Boolean getWed() {
		return wed;
	}

	public Boolean getThu() {
		return thu;
	}

	public Boolean getFri() {
		return fri;
	}

	public Boolean getSat() {
		return sat;
	}

	public Integer getOnDayOfMonth() {
		return onDayOfMonth;
	}

	public Integer getOnDayOfMonthWeek() {
		return onDayOfMonthWeek;
	}

	public Integer getOnDayWeek() {
		return onDayWeek;
	}

	public Integer getOnMonth() {
		return onMonth;
	}

	public static final class RuleDTOBuilder {
		private Long          id;
		private LocalDateTime createdAt;
		private LocalDateTime completedAt;
		private Integer       showAtHour;
		private Integer       hideAtHour;
		private Integer       showAtMinute;
		private Integer       hideAtMinute;
		private String        repeatType;
		private Integer       every;
		private Boolean       sun;
		private Boolean       mon;
		private Boolean       tue;
		private Boolean       wed;
		private Boolean       thu;
		private Boolean       fri;
		private Boolean       sat;
		private Integer       onDayOfMonth;
		private Integer       onDayOfMonthWeek;
		private Integer       onDayWeek;
		private Integer       onMonth;

		private RuleDTOBuilder() {
		}

		public static RuleDTOBuilder aRuleDTO() {
			return new RuleDTOBuilder();
		}

		public RuleDTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public RuleDTOBuilder withCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public RuleDTOBuilder withCompletedAt(LocalDateTime completedAt) {
			this.completedAt = completedAt;
			return this;
		}

		public RuleDTOBuilder withShowAtHour(Integer showAtHour) {
			this.showAtHour = showAtHour;
			return this;
		}

		public RuleDTOBuilder withHideAtHour(Integer hideAtHour) {
			this.hideAtHour = hideAtHour;
			return this;
		}

		public RuleDTOBuilder withShowAtMinute(Integer showAtMinute) {
			this.showAtMinute = showAtMinute;
			return this;
		}

		public RuleDTOBuilder withHideAtMinute(Integer hideAtMinute) {
			this.hideAtMinute = hideAtMinute;
			return this;
		}

		public RuleDTOBuilder withRepeatType(String repeatType) {
			this.repeatType = repeatType;
			return this;
		}

		public RuleDTOBuilder withEvery(Integer every) {
			this.every = every;
			return this;
		}

		public RuleDTOBuilder withSun(Boolean sun) {
			this.sun = sun;
			return this;
		}

		public RuleDTOBuilder withMon(Boolean mon) {
			this.mon = mon;
			return this;
		}

		public RuleDTOBuilder withTue(Boolean tue) {
			this.tue = tue;
			return this;
		}

		public RuleDTOBuilder withWed(Boolean wed) {
			this.wed = wed;
			return this;
		}

		public RuleDTOBuilder withThu(Boolean thu) {
			this.thu = thu;
			return this;
		}

		public RuleDTOBuilder withFri(Boolean fri) {
			this.fri = fri;
			return this;
		}

		public RuleDTOBuilder withSat(Boolean sat) {
			this.sat = sat;
			return this;
		}

		public RuleDTOBuilder withOnDayOfMonth(Integer onDayOfMonth) {
			this.onDayOfMonth = onDayOfMonth;
			return this;
		}

		public RuleDTOBuilder withOnDayOfMonthWeek(Integer onDayOfMonthWeek) {
			this.onDayOfMonthWeek = onDayOfMonthWeek;
			return this;
		}

		public RuleDTOBuilder withOnDayWeek(Integer onDayWeek) {
			this.onDayWeek = onDayWeek;
			return this;
		}

		public RuleDTOBuilder withOnMonth(Integer onMonth) {
			this.onMonth = onMonth;
			return this;
		}

		public RuleDTO build() {
			return new RuleDTO(id,
			                   createdAt,
			                   completedAt,
			                   showAtHour,
			                   hideAtHour,
			                   showAtMinute,
			                   hideAtMinute,
			                   repeatType,
			                   every,
			                   sun,
			                   mon,
			                   tue,
			                   wed,
			                   thu,
			                   fri,
			                   sat,
			                   onDayOfMonth,
			                   onDayOfMonthWeek,
			                   onDayWeek,
			                   onMonth);
		}
	}
}
