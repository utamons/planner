package com.corn.planner.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RuleDTO {
	private final Long id;

	private final LocalDateTime createdAt;

	private final LocalDateTime completedAt;

	private final Integer showAtHour;

	private final Integer hideAtHour;

	private final Integer showAtMinute;

	private final Integer hideAtMinute;

	private final String repeatType;

	private final Integer every;

	private final Boolean sun;

	private final Boolean mon;

	private final Boolean tue;

	private final Boolean wed;

	private final Boolean thu;

	private final Boolean fri;

	private final Boolean sat;

	private final Integer onDayOfMonth;

	private final Integer onDayOfMonthWeek;

	private final Integer onDayWeek;

	private final Integer onMonth;

	@JsonCreator
	public RuleDTO(@JsonProperty("id")
	               Long id,
	               @JsonProperty("createdAt")
	               LocalDateTime createdAt,
	               @JsonProperty("completedAt")
	               LocalDateTime completedAt,
	               @JsonProperty("showAtHour")
	               Integer showAtHour,
	               @JsonProperty("hideAtHour")
	               Integer hideAtHour,
	               @JsonProperty("showAtMinute")
	               Integer showAtMinute,
	               @JsonProperty("hideAtMinute")
	               Integer hideAtMinute,
	               @JsonProperty("repeatType")
	               String repeatType,
	               @JsonProperty("every")
	               Integer every,
	               @JsonProperty("sun")
	               Boolean sun,
	               @JsonProperty("mon")
	               Boolean mon,
	               @JsonProperty("tue")
	               Boolean tue,
	               @JsonProperty("wed")
	               Boolean wed,
	               @JsonProperty("thu")
	               Boolean thu,
	               @JsonProperty("fri")
	               Boolean fri,
	               @JsonProperty("sat")
	               Boolean sat,
	               @JsonProperty("onDayOfMonth")
	               Integer onDayOfMonth,
	               @JsonProperty("onDayOfMonthWeek")
	               Integer onDayOfMonthWeek,
	               @JsonProperty("onDayWeek")
	               Integer onDayWeek,
	               @JsonProperty("onMonth")
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
}
