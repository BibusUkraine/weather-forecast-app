package com.bibusukraine.weatherforecastapi.client;

import com.bibusukraine.weatherforecastapi.payload.response.client.WeatherDailyForecastClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "forecast-ai-client", url = "http://ai-forecast-service:8000")
public interface ForecastAIClient {

    @GetMapping("/get-response?location={location}")
    WeatherDailyForecastClientResponse getResponse(@PathVariable String location);

}
