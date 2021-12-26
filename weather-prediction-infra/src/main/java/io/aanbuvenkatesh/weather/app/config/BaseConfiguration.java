package io.aanbuvenkatesh.weather.app.config;

import io.aanbuvenkatesh.weather.app.repository.OpenWeatherMapRepository;
import io.aanbuvenkatesh.weather.service.OpenWeatherMapService;
import io.aanbuvenkatesh.weather.service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BaseConfiguration {

    private WebClient provideWeatherManWebClient() {
        return WebClient.builder().baseUrl(AppProperties.getWeatherManBaseUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, AppProperties.getWeatherManAuthToken())
                .defaultHeader(HttpHeaders.USER_AGENT, "weather-service")
                .build();
    }

    @Bean
    public WeatherService provideWeatherService() {
        return new OpenWeatherMapService(new OpenWeatherMapRepository(provideWeatherManWebClient()));
    }

}
