package com.bibusukraine.weatherforecastapi.service.impl;

import com.bibusukraine.weatherforecastapi.client.ForecastAIClient;
import com.bibusukraine.weatherforecastapi.client.WeatherClient;
import com.bibusukraine.weatherforecastapi.client.WeatherDailyHistoryClient;
import com.bibusukraine.weatherforecastapi.client.WeatherHourlyHistoryClient;
import com.bibusukraine.weatherforecastapi.payload.response.CurrentWeatherResponse;
import com.bibusukraine.weatherforecastapi.payload.response.DailyForecastResponse;
import com.bibusukraine.weatherforecastapi.payload.response.WeeklyControlResponse;
import com.bibusukraine.weatherforecastapi.payload.response.client.CoordinatesClientResponse;
import com.bibusukraine.weatherforecastapi.payload.response.client.CurrentWeatherClientResponse;
import com.bibusukraine.weatherforecastapi.payload.response.client.WeatherDailyHistoryClientResponse;
import com.bibusukraine.weatherforecastapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private static final double MS_TO_KMH_COEFFICIENT = 3.6;
    private final WeatherClient weatherClient;
    private final WeatherDailyHistoryClient weatherDailyHistoryClient;
    private final WeatherHourlyHistoryClient weatherHourlyHistoryClient;
    private final ForecastAIClient forecastAIClient;
    @Value("${openweathermap.api-key}")
    private String apiKey;

    @Override
    public CurrentWeatherResponse getCurrentWeather(String city, String countryCode) {
        CoordinatesClientResponse cityCoordinates = weatherClient.getCoordinates(city, countryCode, apiKey).get(0);
        CurrentWeatherClientResponse currentWeatherClientResponse = weatherClient.getWeather(
                cityCoordinates.getLat(),
                cityCoordinates.getLon(),
                apiKey
        );
        CurrentWeatherClientResponse.Main main = currentWeatherClientResponse.getMain();

        return CurrentWeatherResponse.builder()
                .temperature((int) Math.round(main.getTemp()))
                .feelsLike((int) Math.round(main.getFeelsLike()))
                .humidity(main.getHumidity())
                .pressure(main.getPressure())
                .windSpeed((int) Math.round(currentWeatherClientResponse.getWind().getSpeed() * MS_TO_KMH_COEFFICIENT))
                .weatherCondition(currentWeatherClientResponse.getWeather().get(0).getMain())
                .build();
    }

    @Override
    public List<DailyForecastResponse> getWeatherDailyForecast(String city) {
        List<Double> temperatures = forecastAIClient.getResponse(city).getData();
        List<DailyForecastResponse> dailyForecasts = new ArrayList<>();
        for (int i = 0; i < temperatures.size(); i++) {
            DailyForecastResponse dailyForecast = new DailyForecastResponse();
            dailyForecast.setTemperature(temperatures.get(i));
            dailyForecast.setDayOfWeek(getDayOfWeek(LocalDate.now().plusDays(i)));
            dailyForecasts.add(dailyForecast);
        }
        return dailyForecasts;
    }

    @Override
    public List<WeeklyControlResponse> getWeeklyControl(String city, String countryCode) {
        LocalDate currentDate = LocalDate.now();
        CoordinatesClientResponse cityCoordinates = weatherClient.getCoordinates(city, countryCode, apiKey).get(0);
        LocalDate endDate = currentDate.minusDays(3);
        LocalDate startDate = endDate.minusDays(6);
        WeatherDailyHistoryClientResponse weatherDailyHistoryClientResponse = weatherDailyHistoryClient.getWeatherHistory(
                cityCoordinates.getLat(),
                cityCoordinates.getLon(),
                startDate.toString(),
                endDate.toString()
        );
        List<WeeklyControlResponse> weatherResponses = new ArrayList<>();
        WeatherDailyHistoryClientResponse.Daily daily = weatherDailyHistoryClientResponse.getDaily();

        if (daily != null) {
            List<String> timeList = daily.getTime();
            List<Double> temperatureList = daily.getTemperature2mMax();

            for (int i = 0; i < timeList.size(); i++) {
                String dayOfWeek = getDayOfWeek(startDate.plusDays(i));
                Double temperature = temperatureList.get(i);

                WeeklyControlResponse weatherResponse = new WeeklyControlResponse(dayOfWeek, temperature != null ? temperature.toString() : null);
                weatherResponses.add(weatherResponse);
            }
        }

        return weatherResponses;
    }

    private String getDayOfWeek(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

}