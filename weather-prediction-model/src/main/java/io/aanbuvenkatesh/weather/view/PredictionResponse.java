package io.aanbuvenkatesh.weather.view;

public class PredictionResponse {

    private WeatherData weather;

    public PredictionResponse(WeatherData weather) {
        this.weather = weather;
    }

    public WeatherData getWeather() {
        return weather;
    }

    public void setWeather(WeatherData weather) {
        this.weather = weather;
    }
}
