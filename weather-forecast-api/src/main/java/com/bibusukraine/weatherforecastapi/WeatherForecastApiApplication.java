package com.bibusukraine.weatherforecastapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WeatherForecastApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherForecastApiApplication.class, args);
    }

}
