package io.aanbuvenkatesh.weather.service;

import io.aanbuvenkatesh.weather.exception.WeatherServiceException;
import io.aanbuvenkatesh.weather.view.PredictionResponse;

/**
 * Weather Service provides forecast of the weather information.
 *
 * @author aanbuvenkatesh
 */
public interface WeatherService {

    /**
     * Predicts the weather for a requested city.
     *
     * @param city City for which the weather prediction is requested
     * @return Prediction data based on the weather information
     * @throws WeatherServiceException
     */
    PredictionResponse getWeatherPrediction(String city) throws WeatherServiceException;

}
