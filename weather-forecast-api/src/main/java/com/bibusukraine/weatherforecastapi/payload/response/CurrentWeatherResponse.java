package com.bibusukraine.weatherforecastapi.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrentWeatherResponse {

    private int temperature;
    private int feelsLike;
    private int pressure;
    private int humidity;
    private int windSpeed;
    private String weatherCondition;

}
