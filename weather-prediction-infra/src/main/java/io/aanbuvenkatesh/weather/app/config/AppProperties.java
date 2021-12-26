package io.aanbuvenkatesh.weather.app.config;

import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

public class AppProperties {

    private static final String WEATHERMAP_BASE_URL = "WEATHERMAP_BASE_URL";
    private static final String WEATHERMAP_APP_ID = "WEATHERMAP_APP_ID";
    private static final String WEATHERMAP_AUTH_TOKEN = "WEATHERMAP_AUTH_TOKEN";

    public static String getWeatherManBaseUrl() {
        return System.getenv(WEATHERMAP_BASE_URL);
    }

    public static String getWeathermanAppId() {
        return System.getenv(WEATHERMAP_APP_ID);
    }

    public static String getWeatherManAuthToken() {
        return ssmClient().getParameter(GetParameterRequest.builder().name(WEATHERMAP_AUTH_TOKEN).build())
                .parameter().value();
    }

    private static SsmClient ssmClient() {
        return SsmClient.builder()
                .build();
    }
}
