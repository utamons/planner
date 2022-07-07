package com.kkr.okta.jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestUsers {
    private final List<TestUser> users;

    @JsonCreator
    public TestUsers(@JsonProperty("users") List<TestUser> users) {
        this.users = users;
    }

    public List<TestUser> getUsers() {
        return users;
    }

    @JsonIgnore
    public TestUser findUser(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No user found with name " + username));
    }
}
