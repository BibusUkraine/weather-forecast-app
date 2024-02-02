package com.bibusukraine.weatherforecastapi.payload.response.client;

import lombok.Data;

import java.util.List;

@Data
public class WeatherDailyForecastClientResponse {

    private List<Double> data;

}
