package com.kkr.okta.jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OktaToken {
    private final String token_type;
    private final int expires_in;
    private final String access_token;
    private final String scope;
    private final String id_token;

    @JsonCreator
    public OktaToken(
            @JsonProperty("token_type")
            String token_type,
            @JsonProperty("expires_in")
            int expires_in,
            @JsonProperty("access_token")
            String access_token,
            @JsonProperty("scope")
            String scope,
            @JsonProperty("id_token")
            String id_token) {
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.access_token = access_token;
        this.scope = scope;
        this.id_token = id_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getScope() {
        return scope;
    }

    public String getId_token() {
        return id_token;
    }
}
