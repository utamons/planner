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
package com.corn.planner.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Entity
@Table(name = "rule")
public class Rule {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "completed_at")
	private LocalDateTime completedAt;

	@Column(name = "show_at_hour")
	private Integer showAtHour;

	@Column(name = "hide_at_hour")
	private Integer hideAtHour;

	@Column(name = "show_at_minute")
	private Integer showAtMinute;

	@Column(name = "hide_at_minute")
	private Integer hideAtMinute;

	@Column(name = "repeat_type")
	private String repeatType;

	@Column(name = "every")
	private Integer every;

	@Column(name = "sun")
	private Boolean sun;

	@Column(name = "mon")
	private Boolean mon;

	@Column(name = "tue")
	private Boolean tue;

	@Column(name = "wed")
	private Boolean wed;

	@Column(name = "thu")
	private Boolean thu;

	@Column(name = "fri")
	private Boolean fri;

	@Column(name = "sat")
	private Boolean sat;

	@Column(name = "on_day_of_month")
	private Integer onDayOfMonth;

	@Column(name = "on_day_of_month_week")
	private Integer onDayOfMonthWeek;

	@Column(name = "on_day_week")
	private Integer onDayWeek;

	@Column(name = "on_month")
	private Integer onMonth;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setShowAtHour(Integer showAtHour) {
		this.showAtHour = showAtHour;
	}

	public Integer getShowAtHour() {
		return showAtHour;
	}

	public void setHideAtHour(Integer hideAtHour) {
		this.hideAtHour = hideAtHour;
	}

	public Integer getHideAtHour() {
		return hideAtHour;
	}

	public void setShowAtMinute(Integer showAtMinute) {
		this.showAtMinute = showAtMinute;
	}

	public Integer getShowAtMinute() {
		return showAtMinute;
	}

	public void setHideAtMinute(Integer hideAtMinute) {
		this.hideAtMinute = hideAtMinute;
	}

	public Integer getHideAtMinute() {
		return hideAtMinute;
	}

	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}

	public String getRepeatType() {
		return repeatType;
	}

	public void setEvery(Integer every) {
		this.every = every;
	}

	public Integer getEvery() {
		return every;
	}

	public void setSun(Boolean sun) {
		this.sun = sun;
	}

	public Boolean isSun() {
		return sun;
	}

	public void setMon(Boolean mon) {
		this.mon = mon;
	}

	public Boolean isMon() {
		return mon;
	}

	public void setTue(Boolean tue) {
		this.tue = tue;
	}

	public Boolean isTue() {
		return tue;
	}

	public void setWed(Boolean wed) {
		this.wed = wed;
	}

	public Boolean isWed() {
		return wed;
	}

	public void setThu(Boolean thu) {
		this.thu = thu;
	}

	public Boolean isThu() {
		return thu;
	}

	public void setFri(Boolean fri) {
		this.fri = fri;
	}

	public Boolean isFri() {
		return fri;
	}

	public void setSat(Boolean sat) {
		this.sat = sat;
	}

	public Boolean isSat() {
		return sat;
	}

	public void setOnDayOfMonth(Integer onDayOfMonth) {
		this.onDayOfMonth = onDayOfMonth;
	}

	public Integer getOnDayOfMonth() {
		return onDayOfMonth;
	}

	public void setOnDayOfMonthWeek(Integer onDayOfMonthWeek) {
		this.onDayOfMonthWeek = onDayOfMonthWeek;
	}

	public Integer getOnDayOfMonthWeek() {
		return onDayOfMonthWeek;
	}

	public void setOnDayWeek(Integer onDayWeek) {
		this.onDayWeek = onDayWeek;
	}

	public Integer getOnDayWeek() {
		return onDayWeek;
	}

	public void setOnMonth(Integer onMonth) {
		this.onMonth = onMonth;
	}

	public Integer getOnMonth() {
		return onMonth;
	}
}
