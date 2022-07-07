package com.kkr.okta.jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OktaCredentials {
    private final String clientId;
    private final String secret;

    @JsonCreator
    public OktaCredentials(@JsonProperty("clientId") String clientId, @JsonProperty("secret") String secret) {
        this.clientId = clientId;
        this.secret = secret;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecret() {
        return secret;
    }
}
