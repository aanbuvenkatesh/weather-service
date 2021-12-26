package io.aanbuvenkatesh.weather.app.helper;

import io.aanbuvenkatesh.weather.model.Prediction;
import io.aanbuvenkatesh.weather.model.Weather;
import io.aanbuvenkatesh.weather.model.Wind;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OpenWeatherMapMapper {

    private OpenWeatherMapMapper() {

    }

    public static List<Weather> toWeather(String response) {
        JSONObject responseJson = new JSONObject(response);
        List<Weather> weatherData = new ArrayList<>();
        responseJson.getJSONArray("list").forEach(data -> {
            JSONObject data1 = (JSONObject) data;
            Weather weather = new Weather();
            weather.setDatetime(LocalDateTime.parse(data1.getString("dt_txt"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            JSONObject temperatureData = data1.getJSONObject("main");
            weather.setTemperature(temperatureData.getFloat("temp"));
            weather.setTemperatureMaximum(temperatureData.getFloat("temp_max"));
            weather.setTemperatureMinimum(temperatureData.getFloat("temp_min"));
            JSONArray predictionData = data1.getJSONArray("weather");
            List<Prediction> predictions = new ArrayList<>();
            predictionData.forEach(pData -> {
                JSONObject pData1 = (JSONObject) pData;
                predictions.add(new Prediction(pData1.getInt("id"),
                        pData1.getString("main"),
                        pData1.getString("description")));
            });
            weather.setPredictions(predictions);
            JSONObject windData = data1.getJSONObject("wind");
            weather.setWind(new Wind(windData.getFloat("speed"), windData.getFloat("deg"),
                    windData.getFloat("gust")));
            weatherData.add(weather);
        });
        return weatherData;
    }
}
