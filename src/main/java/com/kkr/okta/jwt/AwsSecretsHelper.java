package com.kkr.okta.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

public class AwsSecretsHelper {

    public static final String TEST_USERS_CREDS_KEY = "priv-mtks-clientportal-qa-test-users";
    public static final String TEST_APP_CREDS_DEV_KEY = "kkr-devtoken-okta-secret";
    public static final String TEST_APP_CREDS_QA_KEY = "kkr-qatesting-okta-secret";
    public static final Region REGION = Region.US_EAST_1;

    public OktaCredentials getOktaCredentials(String env) throws JsonProcessingException {
        String rawCreds = getValue(env.equalsIgnoreCase("dev") ?
                TEST_APP_CREDS_DEV_KEY : TEST_APP_CREDS_QA_KEY);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(rawCreds, OktaCredentials.class);
    }

    public TestUsers getUsers() throws JsonProcessingException {
        String rawUsers = getValue(TEST_USERS_CREDS_KEY);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(rawUsers, TestUsers.class);
    }

    private String getValue(String secretName) throws SecretsManagerException {
        try (SecretsManagerClient secretsClient = SecretsManagerClient.builder()
                .region(REGION)
                .build()) {
            GetSecretValueRequest valueRequest = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();

            GetSecretValueResponse valueResponse = secretsClient.getSecretValue(valueRequest);
            return valueResponse.secretString();
        }
    }
}
