package io.aanbuvenkatesh.weather.model;

import java.time.LocalDate;
import java.util.List;

public class Weather {

    private Float temperature;
    private Float temperatureMaximum;
    private Float temperatureMinimum;
    private LocalDate datetime;
    private List<Prediction> predictions;
    private Wind wind;

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Float getTemperatureMaximum() {
        return temperatureMaximum;
    }

    public void setTemperatureMaximum(Float temperatureMaximum) {
        this.temperatureMaximum = temperatureMaximum;
    }

    public Float getTemperatureMinimum() {
        return temperatureMinimum;
    }

    public void setTemperatureMinimum(Float temperatureMinimum) {
        this.temperatureMinimum = temperatureMinimum;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

}
