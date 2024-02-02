package com.bibusukraine.weatherforecastapi.controller;

import com.bibusukraine.weatherforecastapi.payload.response.CurrentWeatherResponse;
import com.bibusukraine.weatherforecastapi.payload.response.DailyForecastResponse;
import com.bibusukraine.weatherforecastapi.payload.response.WeeklyControlResponse;
import com.bibusukraine.weatherforecastapi.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/now")
    @Operation(summary = "Get current weather")
    public CurrentWeatherResponse getCurrentWeather(@RequestParam String city,
                                                    @RequestParam String countryCode) {
        return weatherService.getCurrentWeather(city, countryCode);
    }

    @GetMapping("/weekly-control")
    @Operation(summary = "Get weather for past week")
    public List<WeeklyControlResponse> getWeeklyControl(@RequestParam String city,
                                                        @RequestParam String countryCode) {
        return weatherService.getWeeklyControl(city, countryCode);
    }

    @GetMapping("/daily-forecast")
    @Operation(summary = "Get weather forecast for hours")
    public List<DailyForecastResponse> getWeatherDailyForecast(@RequestParam String city) {
        return weatherService.getWeatherDailyForecast(city);
    }

}
