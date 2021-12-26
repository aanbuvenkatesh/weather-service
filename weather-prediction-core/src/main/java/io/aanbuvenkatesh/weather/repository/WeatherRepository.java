package io.aanbuvenkatesh.weather.repository;

import io.aanbuvenkatesh.weather.exception.WeatherServiceException;
import io.aanbuvenkatesh.weather.model.Weather;

import java.util.List;

/**
 * Weather Repository provides the data for prediction of weather.
 * It provides the temperature, humidity, wind, and other forecast information.
 *
 * @author aanbuvenkatesh
 */
public interface WeatherRepository {

    /**
     * Retrieves the weather information for the provided city.
     *
     * @param city City for which the weather data is required for prediction
     * @return List of weather data for next five days.
     * @throws WeatherServiceException
     */
    List<Weather> getWeatherForCity(String city) throws WeatherServiceException;

}
