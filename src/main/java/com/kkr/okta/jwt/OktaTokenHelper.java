package com.kkr.okta.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OktaTokenHelper {
    public static final String OKTA_TOKEN_QA_URL = System.getenv("OKTA_TOKEN_QA_URL");
    public static final String OKTA_TOKEN_DEV_URL = System.getenv("OKTA_TOKEN_DEV_URL");

    private final String env;
    private final String user;

    public OktaTokenHelper(String env, String user) {
        this.env = env;
        this.user = user;
    }

    public String getAccessToken() throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        AwsSecretsHelper helper = new AwsSecretsHelper();
        TestUsers testUsers = helper.getUsers();
        int usersSize = testUsers.getUsers().size();
        if (usersSize == 0)
            throw new RuntimeException("No users found in AWS Secrets");

        TestUser testUser = testUsers.findUser(user);

        OktaCredentials oktaCredentials = helper.getOktaCredentials(env);

        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(env.equalsIgnoreCase("dev") ? OKTA_TOKEN_DEV_URL : OKTA_TOKEN_QA_URL))
                .headers("Content-type", "application/x-www-form-urlencoded",
                        "Authorization", getAuthString(oktaCredentials),
                        "Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(getBodyString(testUser)))
                .build();

        HttpResponse<?> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        String rawToken = response.body().toString();
        OktaToken oktaToken = mapper.readValue(rawToken,OktaToken.class);
        return oktaToken.getAccess_token();
    }

    private String getAuthString(OktaCredentials oktaCredentials) {
        return "Basic " +
                Base64.getEncoder()
                        .encodeToString(
                                (oktaCredentials.getClientId()+":"+oktaCredentials.getSecret())
                                        .getBytes());
    }

    private String getBodyString(TestUser user) {
        return "grant_type=password&scope=openid&"+
                "username="+user.getUsername()+"&"+
                "password="+URLEncoder.encode(user.getPassword(), StandardCharsets.UTF_8);
    }

}
