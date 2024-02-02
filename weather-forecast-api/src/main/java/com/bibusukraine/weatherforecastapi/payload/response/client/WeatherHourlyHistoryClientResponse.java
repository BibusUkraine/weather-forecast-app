package com.bibusukraine.weatherforecastapi.payload.response.client;

import lombok.Data;

import java.util.List;

@Data
public class WeatherHourlyHistoryClientResponse {

    private List<HourlyWeatherData> list;

    @Data
    public static class HourlyWeatherData {

        private long dt;
        private MainData main;

        @Data
        public static class MainData {

            private double temp;

        }

        @Data
        public static class WeatherDescription {

            private String main;

        }

    }

}
