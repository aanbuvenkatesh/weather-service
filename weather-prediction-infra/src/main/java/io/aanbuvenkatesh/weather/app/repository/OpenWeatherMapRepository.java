package io.aanbuvenkatesh.weather.app.repository;

import io.aanbuvenkatesh.weather.app.config.AppProperties;
import io.aanbuvenkatesh.weather.app.helper.OpenWeatherMapMapper;
import io.aanbuvenkatesh.weather.exception.CityNotFoundException;
import io.aanbuvenkatesh.weather.exception.ServiceErrorException;
import io.aanbuvenkatesh.weather.exception.UnauthenticatedServiceException;
import io.aanbuvenkatesh.weather.exception.WeatherServiceException;
import io.aanbuvenkatesh.weather.model.Weather;
import io.aanbuvenkatesh.weather.repository.WeatherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;


public class OpenWeatherMapRepository implements WeatherRepository {

    private WebClient weatherManClient;

    public OpenWeatherMapRepository(WebClient weatherManClient) {
        this.weatherManClient = weatherManClient;
    }

    @Override
    public List<Weather> getWeatherForCity(String city) throws WeatherServiceException {
        List<Weather> weather;
        try {
            ResponseEntity<String> response = this.weatherManClient.get().uri(uriBuilder ->
                            uriBuilder.path("/forecast").queryParam("q", city)
                                    .queryParam("appid", AppProperties.getWeathermanAppId())
                                    .queryParam("units", "metric").build())
                    .retrieve()
                    .toEntity(String.class)
                    .block();
            weather = OpenWeatherMapMapper.toWeather(response.getBody());
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new CityNotFoundException();
            else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED)
                throw new UnauthenticatedServiceException(e.getMessage(), e);
            else
                throw new ServiceErrorException(e.getMessage(), e);
        }
        return weather;
    }
}
