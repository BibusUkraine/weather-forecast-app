package com.bibusukraine.weatherforecastapi.service;

import com.bibusukraine.weatherforecastapi.payload.response.CurrentWeatherResponse;
import com.bibusukraine.weatherforecastapi.payload.response.DailyForecastResponse;
import com.bibusukraine.weatherforecastapi.payload.response.WeeklyControlResponse;

import java.util.List;

public interface WeatherService {

    CurrentWeatherResponse getCurrentWeather(String city, String countryCode);

    List<DailyForecastResponse> getWeatherDailyForecast(String city);

    List<WeeklyControlResponse> getWeeklyControl(String city, String countryCode);

}
