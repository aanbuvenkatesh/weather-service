package io.aanbuvenkatesh.weather.service;

import io.aanbuvenkatesh.weather.exception.WeatherServiceException;
import io.aanbuvenkatesh.weather.model.Weather;
import io.aanbuvenkatesh.weather.repository.WeatherRepository;
import io.aanbuvenkatesh.weather.view.PredictionResponse;
import io.aanbuvenkatesh.weather.view.Temperature;
import io.aanbuvenkatesh.weather.view.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Provides the weather forecast and prediction based on the open weather map service.
 *
 * @author aanbuvenkatesh
 */
public class OpenWeatherMapService implements WeatherService {

    private static final Logger log = LoggerFactory.getLogger(OpenWeatherMapService.class);

    private static final Float HIGHEST_TEMPERATURE = 40f;

    private static final String RAIN_FORECASTED = "Carry umbrella";
    private static final String HIGH_TEMPERATURE_FORECASTED = "Use sunscreen lotion";
    private static final String NO_FORECAST = "Enjoy your day";

    private final WeatherRepository weatherRepository;

    private final Predicate<Weather> days = weather -> weather.getDatetime().isBefore(LocalDateTime.now().plusDays(3));

    public OpenWeatherMapService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public PredictionResponse getWeatherPrediction(String city) throws WeatherServiceException {
        log.info("Forecasting the weather for the city [%s]", city);
        List<Weather> weather = weatherRepository.getWeatherForCity(city);
        String forecast = NO_FORECAST;
        if (isRainForecasted(weather)) {
            forecast = RAIN_FORECASTED;
        } else if (isHighTemperatureForecasted(weather)) {
            forecast = HIGH_TEMPERATURE_FORECASTED;
        }
        log.info("Forecasted the weather for the city [%s]", city);
        return new PredictionResponse(new WeatherData(
                Temperature.from(weather.stream().filter(days).collect(Collectors.toList())),
                forecast));
    }

    private boolean isRainForecasted(List<Weather> weather) {
        log.debug("Checking for Rain Forecast");
        return weather.stream()
                .filter(days)
                .anyMatch(weatherData ->
                        weatherData.getPredictions().stream().anyMatch(prediction -> prediction.getId() >= 500
                                && prediction.getId() < 600)
                );
    }

    private boolean isHighTemperatureForecasted(List<Weather> weather) {
        log.debug("Checking for High Temperature Forecast");
        return weather.stream().filter(days).anyMatch(weatherData ->
                weatherData.getTemperature() > HIGHEST_TEMPERATURE
        );
    }
}
