package io.aanbuvenkatesh.weather.service;

import io.aanbuvenkatesh.weather.dao.WeatherRepository;
import io.aanbuvenkatesh.weather.view.PredictionResponse;
import io.aanbuvenkatesh.weather.model.Weather;
import io.aanbuvenkatesh.weather.view.Temperature;
import io.aanbuvenkatesh.weather.view.WeatherData;

import java.util.List;

public class OpenWeatherMapService implements WeatherService {

    private static final Float HIGHEST_TEMPERATURE = 40f;

    private static final String RAIN_FORECASTED = "Carry umbrella";
    private static final String HIGH_TEMPERATURE_FORECASTED = "Use sunscreen lotion";
    private static final String NO_FORECAST = "Enjoy your day";

    private WeatherRepository weatherRepository;

    public OpenWeatherMapService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public PredictionResponse getWeatherPrediction(String city) {
        List<Weather> weather = weatherRepository.getWeatherForCity(city);
        String forecast = NO_FORECAST;
        if (isRainForecasted(weather)) {
            forecast = RAIN_FORECASTED;
        } else if (isHighTemperatureForecasted(weather)) {
            forecast = HIGH_TEMPERATURE_FORECASTED;
        }
        return new PredictionResponse(new WeatherData(Temperature.from(weather), forecast));
    }

    private boolean isRainForecasted(List<Weather> weather) {
        return weather.stream().anyMatch(weatherData ->
                weatherData.getPredictions().stream().anyMatch(prediction -> prediction.getId() >= 500
                        & prediction.getId() < 600)
        );
    }

    private boolean isHighTemperatureForecasted(List<Weather> weather) {
        return weather.stream().anyMatch(weatherData ->
                weatherData.getTemperature() > HIGHEST_TEMPERATURE
        );
    }
}
