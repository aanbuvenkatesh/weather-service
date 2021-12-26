package io.aanbuvenkatesh.weather.app.controller;

import io.aanbuvenkatesh.weather.exception.WeatherServiceException;
import io.aanbuvenkatesh.weather.service.WeatherService;
import io.aanbuvenkatesh.weather.view.PredictionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather/forecast")
public class WeatherPredictionController {

    private static final String GET_WEATHER_FORECAST = "/{city}";
    private static final String CITY = "city";

    private WeatherService weatherService;

    @Autowired
    public WeatherPredictionController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = GET_WEATHER_FORECAST, produces = "application/json")
    public ResponseEntity<PredictionResponse> getWeatherStatus(@PathVariable(value = CITY) String city) throws WeatherServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(weatherService.getWeatherPrediction(city));
    }

}
