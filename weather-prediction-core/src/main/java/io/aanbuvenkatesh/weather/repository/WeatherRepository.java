package io.aanbuvenkatesh.weather.repository;

import io.aanbuvenkatesh.weather.exception.WeatherServiceException;
import io.aanbuvenkatesh.weather.model.Weather;

import java.util.List;

public interface WeatherRepository {

    List<Weather> getWeatherForCity(String city) throws WeatherServiceException;

}
