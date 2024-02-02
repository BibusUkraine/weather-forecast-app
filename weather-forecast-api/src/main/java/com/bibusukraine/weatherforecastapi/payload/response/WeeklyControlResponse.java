package com.bibusukraine.weatherforecastapi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeeklyControlResponse {

    private String dayOfWeek;
    private String temperature;

}
