package com.bibusukraine.weatherforecastapi.payload.response.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherDailyHistoryClientResponse {

    private Daily daily;

    @Data
    public static class Daily {
        private List<String> time;
        @JsonProperty("temperature_2m_max")
        private List<Double> temperature2mMax;
    }

}
