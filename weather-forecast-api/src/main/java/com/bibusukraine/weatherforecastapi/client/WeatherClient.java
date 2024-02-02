package com.bibusukraine.weatherforecastapi.client;

import com.bibusukraine.weatherforecastapi.payload.response.client.CoordinatesClientResponse;
import com.bibusukraine.weatherforecastapi.payload.response.client.CurrentWeatherClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "weather-client", url = "https://api.openweathermap.org")
public interface WeatherClient {

    @GetMapping("/geo/1.0/direct?q={city},{country}&limit=1&appid={apiKey}")
    List<CoordinatesClientResponse> getCoordinates(@PathVariable String city,
                                                   @PathVariable String country,
                                                   @PathVariable String apiKey);

    @GetMapping("/data/2.5/weather?units=metric&lat={lat}&lon={lon}&appid={apiKey}")
    CurrentWeatherClientResponse getWeather(@PathVariable double lat,
                                            @PathVariable double lon,
                                            @PathVariable String apiKey);

}
