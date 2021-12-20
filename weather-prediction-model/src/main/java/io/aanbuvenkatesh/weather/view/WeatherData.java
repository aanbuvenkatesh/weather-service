package io.aanbuvenkatesh.weather.view;

import java.util.List;

public class WeatherData {
    private List<Temperature> temperature;
    private String prediction;

    public WeatherData(List<Temperature> temperature, String prediction) {
        this.temperature = temperature;
        this.prediction = prediction;
    }

    public List<Temperature> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Temperature> temperature) {
        this.temperature = temperature;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
