package com.bibusukraine.weatherforecastapi.payload.response;

import lombok.Data;

@Data
public class DailyForecastResponse {

    private String dayOfWeek;
    private double temperature;

}
