package com.corn.planner.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import liquibase.pro.packaged.S;

public class OktaRequest {
	private final String env;
	private final String user;

	@JsonCreator
	public OktaRequest(
			@JsonProperty("env")
			String env,
			@JsonProperty("user")
			String user) {
		this.env = env;
		this.user = user;
	}

	public String getEnv() {
		return env;
	}

	public String getUser() {
		return user;
	}
}
