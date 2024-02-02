package com.bibusukraine.weatherforecastapi.client;

import com.bibusukraine.weatherforecastapi.payload.response.client.WeatherDailyHistoryClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "weather-daily-history-client", url = "https://archive-api.open-meteo.com")
public interface WeatherDailyHistoryClient {

    @GetMapping("/v1/archive?latitude={lat}&longitude={lon}&start_date={startDate}&end_date={endDate}&daily=temperature_2m_max")
    WeatherDailyHistoryClientResponse getWeatherHistory(@PathVariable double lat,
                                                        @PathVariable double lon,
                                                        @PathVariable String startDate,
                                                        @PathVariable String endDate);

}
