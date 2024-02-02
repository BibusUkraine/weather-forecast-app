package com.bibusukraine.weatherforecastapi.client;

import com.bibusukraine.weatherforecastapi.payload.response.client.WeatherHourlyHistoryClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "weather-hourly-history-client", url = "https://history.openweathermap.org")
public interface WeatherHourlyHistoryClient {

    @GetMapping("/data/2.5/history/city?units=metric&cnt={cnt}&lat={lat}&lon={lon}&appid={apiKey}")
    WeatherHourlyHistoryClientResponse getWeatherHistory(@PathVariable double lat,
                                                         @PathVariable double lon,
                                                         @PathVariable int cnt,
                                                         @PathVariable String apiKey);

}
