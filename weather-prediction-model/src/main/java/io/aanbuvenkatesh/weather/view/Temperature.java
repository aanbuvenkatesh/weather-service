package io.aanbuvenkatesh.weather.view;

import io.aanbuvenkatesh.weather.model.Weather;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Temperature {

    private Temperature(Float temperature, Float temperatureMaximum, Float temperatureMinimum, LocalDateTime datetime) {
        this.temperature = temperature;
        this.temperatureMaximum = temperatureMaximum;
        this.temperatureMinimum = temperatureMinimum;
        this.datetime = datetime;
    }

    private Float temperature;
    private Float temperatureMaximum;
    private Float temperatureMinimum;
    private LocalDateTime datetime;

    public static List<Temperature> from(List<Weather> weather) {
        return weather.stream().map(statistics -> new Temperature.Builder()
                        .setTemperature(statistics.getTemperature())
                        .setTemperatureMaximum(statistics.getTemperatureMaximum())
                        .setTemperatureMinimum(statistics.getTemperatureMinimum())
                        .setDatetime(statistics.getDatetime()).build())
                .collect(Collectors.toList());
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

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public static class Builder {
        private Float temperature;
        private Float temperatureMaximum;
        private Float temperatureMinimum;
        private LocalDateTime datetime;

        public Builder setTemperatureMaximum(Float temperatureMaximum) {
            this.temperatureMaximum = temperatureMaximum;
            return this;
        }

        public Builder setTemperatureMinimum(Float temperatureMinimum) {
            this.temperatureMinimum = temperatureMinimum;
            return this;
        }

        public Builder setTemperature(Float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder setDatetime(LocalDateTime datetime) {
            this.datetime = datetime;
            return this;
        }

        public Temperature build() {
            return new Temperature(this.temperature, this.temperatureMaximum, this.temperatureMinimum, this.datetime);
        }
    }
}
