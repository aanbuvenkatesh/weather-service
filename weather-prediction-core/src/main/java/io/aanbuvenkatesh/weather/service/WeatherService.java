package io.aanbuvenkatesh.weather.service;

import io.aanbuvenkatesh.weather.exception.WeatherServiceException;
import io.aanbuvenkatesh.weather.view.PredictionResponse;

public interface WeatherService {

    public PredictionResponse getWeatherPrediction(String city) throws WeatherServiceException;

}
