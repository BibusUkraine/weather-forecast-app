package com.bibusukraine.weatherforecastapi.service;

import com.bibusukraine.weatherforecastapi.client.ForecastAIClient;
import com.bibusukraine.weatherforecastapi.client.WeatherClient;
import com.bibusukraine.weatherforecastapi.client.WeatherDailyHistoryClient;
import com.bibusukraine.weatherforecastapi.client.WeatherHourlyHistoryClient;
import com.bibusukraine.weatherforecastapi.payload.response.CurrentWeatherResponse;
import com.bibusukraine.weatherforecastapi.payload.response.WeeklyControlResponse;
import com.bibusukraine.weatherforecastapi.payload.response.client.CoordinatesClientResponse;
import com.bibusukraine.weatherforecastapi.payload.response.client.CurrentWeatherClientResponse;
import com.bibusukraine.weatherforecastapi.payload.response.client.WeatherDailyHistoryClientResponse;
import com.bibusukraine.weatherforecastapi.service.impl.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class WeatherServiceTest {

  @InjectMocks
  private WeatherServiceImpl weatherService;

  @Mock
  private WeatherClient weatherClient;

  @Mock
  private WeatherDailyHistoryClient weatherDailyHistoryClient;

  @Mock
  private WeatherHourlyHistoryClient weatherHourlyHistoryClient;

  @Mock
  private ForecastAIClient forecastAIClient;
  private String testApiKey = "testApiKey";

  @BeforeEach
  public void setup() {
    ReflectionTestUtils.setField(weatherService, "apiKey", testApiKey);
  }

  @Test
  public void testGetCurrentWeather() {
    String city = "Kyiv";
    String countryCode = "380";
    CoordinatesClientResponse coordinatesResponse = new CoordinatesClientResponse();
    CurrentWeatherClientResponse weatherResponse = getCurrentWeatherClientResponse();
    when(weatherClient.getCoordinates(city, countryCode, testApiKey)).thenReturn(new ArrayList<>(List.of(coordinatesResponse)));
    when(weatherClient.getWeather(coordinatesResponse.getLat(), coordinatesResponse.getLon(), testApiKey)).thenReturn(weatherResponse);

    CurrentWeatherResponse result = weatherService.getCurrentWeather(city, countryCode);

    assertEquals(21, result.getTemperature());
    assertEquals(20, result.getFeelsLike());
    assertEquals(60, result.getHumidity());
    assertEquals(1013, result.getPressure());
    assertEquals(18, result.getWindSpeed());
    assertEquals("Clear", result.getWeatherCondition());
  }

  @Test
  public void testGetWeeklyControl() {
    String city = "Kyiv";
    String countryCode = "380";
    CoordinatesClientResponse coordinatesResponse = new CoordinatesClientResponse();
    WeatherDailyHistoryClientResponse weatherDailyHistoryClientResponse = new WeatherDailyHistoryClientResponse();
    WeatherDailyHistoryClientResponse.Daily daily = new WeatherDailyHistoryClientResponse.Daily();
    List<String> timeList = Arrays.asList("2022-01-01", "2022-01-02", "2022-01-03", "2022-01-04", "2022-01-05", "2022-01-06", "2022-01-07");
    List<Double> temperatureList = Arrays.asList(20.5, 21.5, 22.5, 23.5, 24.5, 25.5, 26.5);
    daily.setTime(timeList);
    daily.setTemperature2mMax(temperatureList);
    weatherDailyHistoryClientResponse.setDaily(daily);

    when(weatherClient.getCoordinates(city, countryCode, testApiKey)).thenReturn(new ArrayList<>(List.of(coordinatesResponse)));
    when(weatherDailyHistoryClient.getWeatherHistory(anyDouble(), anyDouble(), anyString(), anyString())).thenReturn(weatherDailyHistoryClientResponse);

    List<WeeklyControlResponse> result = weatherService.getWeeklyControl(city, countryCode);

    assertEquals(7, result.size());
    for (int i = 0; i < result.size(); i++) {
      assertEquals(timeList.get(i), result.get(i).getDayOfWeek());
      assertEquals(temperatureList.get(i).toString(), result.get(i).getTemperature());
    }
  }

  private CurrentWeatherClientResponse getCurrentWeatherClientResponse() {
    CurrentWeatherClientResponse weatherResponse = new CurrentWeatherClientResponse();
    CurrentWeatherClientResponse.Main main = new CurrentWeatherClientResponse.Main();
    main.setTemp(20.5);
    main.setFeelsLike(19.5);
    main.setHumidity(60);
    main.setPressure(1013);
    CurrentWeatherClientResponse.Wind wind = new CurrentWeatherClientResponse.Wind();
    wind.setSpeed(5.0);
    weatherResponse.setMain(main);
    weatherResponse.setWind(wind);
    CurrentWeatherClientResponse.Weather weather = new CurrentWeatherClientResponse.Weather();
    weather.setMain("Clear");
    weatherResponse.setWeather(Collections.singletonList(weather));
    return weatherResponse;
  }
}