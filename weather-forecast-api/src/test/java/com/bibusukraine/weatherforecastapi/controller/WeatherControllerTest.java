package com.bibusukraine.weatherforecastapi.controller;

import com.bibusukraine.weatherforecastapi.payload.response.CurrentWeatherResponse;
import com.bibusukraine.weatherforecastapi.payload.response.WeeklyControlResponse;
import com.bibusukraine.weatherforecastapi.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(value = WeatherController.class)
class WeatherControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private WeatherService weatherService;
  private String testWeatherCondition;

  @BeforeEach
  void setUp() {
    testWeatherCondition = "Test weather condition";
  }

  @Test
  @SneakyThrows
  void testGetCurrentWeatherPositiveResult() {
    String testCity = "Kyiv";
    String testCountryCode = "380";
    CurrentWeatherResponse testResponse = CurrentWeatherResponse.builder()
        .temperature(10)
        .feelsLike(11)
        .pressure(73)
        .humidity(97)
        .windSpeed(20)
        .weatherCondition(testWeatherCondition)
        .build();
    when(weatherService.getCurrentWeather(testCity, testCountryCode)).thenReturn(testResponse);
    mockMvc.perform(MockMvcRequestBuilders.get("/weather/now")
            .accept(MediaType.APPLICATION_JSON)
            .param("city", testCity)
            .param("countryCode", testCountryCode)
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(writeJsonStringFromObject(testResponse)));
  }

  @Test
  @SneakyThrows
  void testGetWeeklyControlPositiveResult() {
    String testCity = "Kyiv";
    String testCountryCode = "380";
    List<String> weekDays = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    List<WeeklyControlResponse> testResponses = new ArrayList<>();
    for (String day : weekDays) {
      testResponses.add(new WeeklyControlResponse(day, String.valueOf(Math.random() * 30)));
    }
    when(weatherService.getWeeklyControl(testCity, testCountryCode)).thenReturn(testResponses);
    mockMvc.perform(MockMvcRequestBuilders.get("/weather/weekly-control")
            .accept(MediaType.APPLICATION_JSON)
            .param("city", testCity)
            .param("countryCode", testCountryCode)
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(writeJsonStringFromObject(testResponses)));
  }

  @Test
  @SneakyThrows
  void testGetWeeklyControlNegativeResult() {
    String testCity = "Kyiv";
    String testCountryCode = "380";
    List<String> weekDays = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    List<WeeklyControlResponse> testResponses = new ArrayList<>();
    for (String day : weekDays) {
      testResponses.add(new WeeklyControlResponse(day, String.valueOf(Math.random() * 30)));
    }
    when(weatherService.getWeeklyControl(testCity, testCountryCode)).thenReturn(testResponses);
    mockMvc.perform(MockMvcRequestBuilders.get("/weather/weekly-control")
            .accept(MediaType.APPLICATION_XML)
            .param("city", testCity)
            .param("countryCode", testCountryCode)
        ).andExpect(MockMvcResultMatchers.status().is(406));
  }

  @Test
  @SneakyThrows
  void testGetCurrentWeatherNegativeResult() {
    String testCity = "Kyiv";
    String testCountryCode = "380";
    CurrentWeatherResponse testResponse = CurrentWeatherResponse.builder()
        .temperature(10)
        .feelsLike(11)
        .pressure(73)
        .humidity(97)
        .windSpeed(20)
        .weatherCondition(testWeatherCondition)
        .build();
    when(weatherService.getCurrentWeather(testCity, testCountryCode)).thenReturn(testResponse);
    mockMvc.perform(MockMvcRequestBuilders.get("/weather/now")
            .accept(MediaType.APPLICATION_XML)
            .param("city", testCity)
            .param("countryCode", testCountryCode)
        ).andExpect(MockMvcResultMatchers.status().is(406));
  }

  @SneakyThrows
  private String writeJsonStringFromObject(Object object) {
    return new ObjectMapper().writeValueAsString(object);
  }
}
