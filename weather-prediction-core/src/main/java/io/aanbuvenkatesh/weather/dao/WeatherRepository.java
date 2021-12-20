package io.aanbuvenkatesh.weather.dao;

import io.aanbuvenkatesh.weather.model.Weather;

import java.util.List;

public interface WeatherRepository {

    public List<Weather> getWeatherForCity(String city);

}
